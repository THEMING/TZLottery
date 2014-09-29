package com.xsc.lottery.admin.action.lottery;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.ChaseStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PloyType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.zhuihaoCx")
public class ZhuihaoCxAction extends AdminBaseAction
{
    @Autowired
    private LotteryOrderService orderService;
    
    @Autowired
    private ChaseService chaseService;
    
    @Autowired
    private CustomerService customerService;

    private Page<Chase> page;

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;

    private String f_name;

    private String f_type;

    private LotteryType[] types = LotteryType.values();

    private Calendar f_stime;// 时间查询（开始时间）

    private Calendar f_etime;// 时间查询（结束时间）

    private String f_serchTerm;// 期次范围

    private String f_serchTerm1;// 期次范围

    private String ployType = "all";

    private ChaseStatus[] status = ChaseStatus.values();

    private String f_statu;// 追号状态
    
    private String chaseids; //需要停止追号的chase ID;
    
    private String lotType;
    
    private String termNo;

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	
	public String getLotType() {
		return lotType;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	 @Autowired
	 private LotteryTermService termService;
	public String  zhuihao()
	{
		LotteryTerm term = termService.getByTypeAndTermNo(termNo, LotteryType.enToType(lotType));
		termService.startChase(term);
		return "list";
	}

    public String index()
    {
        page = new Page<Chase>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = orderService.getChasePage(page, f_name, f_type, f_stime,
                f_etime, f_statu, PloyType.enToType(ployType));
        return "list";
    }

    public String tj()
    {
        try {
            f_name = URLDecoder.decode(f_name, "UTF-8");
            f_statu = URLDecoder.decode(f_statu, "UTF-8");
            f_type = URLDecoder.decode(f_type, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            f_name = "";
            f_statu = "";
            f_type = "";
        }
        List<Chase> list = orderService.getChaseList(f_name, f_type, f_stime,
                f_etime, f_serchTerm, f_serchTerm1, f_statu);
        BigDecimal buymoney = new BigDecimal(0);// 中奖金额
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Chase c : list) {
            buymoney = buymoney.add(c.getMoney());
        }
        resultMap.put("buymoney", buymoney.toString());
        setJsonString(JsonMsgBean.MapToJsonString(resultMap));
        return AJAXJSON;
    }

    public String stop() {
		
    	chaseids = chaseids.substring(0, chaseids.length() - 1);
    	String[] chases = chaseids.split(",");
    	
    	for (int i = 0; i < chases.length; i++) {
    		Chase chase = chaseService.findById(Long.valueOf(chases[i]));

			chase.setStatus(ChaseStatus.追号终止);
			chaseService.save(chase);
			List<ChaseItem> chaseItems = chaseService.getChaseItemByChase(chase);
			
			for (ChaseItem chaseItem2 : chaseItems) {
				if (chaseItem2.getStatus() == ChaseItermStatus.待追号) {
					chaseItem2.setStatus(ChaseItermStatus.已撤销);
					chaseService.saveChaseItem(chaseItem2);
				}
			}
			
            WalletLog returnWalletLog = new WalletLog(BusinessType.收入,
                    chase.getPlan().getMoney().multiply(new BigDecimal(chase.getTermNum() - chase.getOktermNum())), 
                    BigDecimal.ZERO, BigDecimal.ZERO, 
                    chase.getPlan().getMoney().multiply(new BigDecimal(chase.getTermNum() - chase.getOktermNum())),
                    "手动终止次追号！",
                    WalletLogType.追号退款, chase.getPlan().getNumberNo());
            customerService.addWalletLog(chase.getCustomer().getWallet().getId(), returnWalletLog);
		}
    	
    	
    	return index();
	}
    
    public ChaseStatus[] getStatus()
    {
        return status;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public String getF_name()
    {
        return f_name;
    }

    public void setF_name(String fName)
    {
        f_name = fName;
    }

    public String getF_type()
    {
        return f_type;
    }

    public void setF_type(String fType)
    {
        f_type = fType;
    }

    public Calendar getF_stime()
    {
        return f_stime;
    }

    public void setF_stime(Calendar fStime)
    {
        f_stime = fStime;
    }

    public Calendar getF_etime()
    {
        return f_etime;
    }

    public void setF_etime(Calendar fEtime)
    {
        f_etime = fEtime;
    }

    public String getF_serchTerm()
    {
        return f_serchTerm;
    }

    public void setF_serchTerm(String fSerchTerm)
    {
        f_serchTerm = fSerchTerm;
    }

    public String getF_serchTerm1()
    {
        return f_serchTerm1;
    }

    public void setF_serchTerm1(String fSerchTerm1)
    {
        f_serchTerm1 = fSerchTerm1;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

    public Page<Chase> getPage()
    {
        return page;
    }

    public LotteryType[] getTypes()
    {
        return types;
    }

    public void setOrderService(LotteryOrderService orderService)
    {
        this.orderService = orderService;
    }

    public void setPloyType(String ployType)
    {
        this.ployType = ployType;
    }

}
