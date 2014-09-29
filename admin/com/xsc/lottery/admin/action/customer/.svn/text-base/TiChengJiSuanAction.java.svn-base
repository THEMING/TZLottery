package com.xsc.lottery.admin.action.customer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.PayOutRequest;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.PayOutRequestService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.LotteryTypeUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("ticheng.customer")
public class TiChengJiSuanAction extends AdminBaseAction{

	private String stime;
	private String etime;
	private String chupiao;
	private String sum;
	private List<Object[]> list;
	private SendTicketPlat[] chupiao1 = SendTicketPlat.values();
	private String processFlag;
	private String realname;
	private String nickname;
	
    private Page<PayOutRequest> page;
    private int pageNo = 1;
    private int pageSize = 10;
    private int totalPages;
	
    @Autowired
    private LotteryOrderService lotteryOrderService;
    
    @Autowired
    private PayOutRequestService outRequestService;
    
	public String index()
    {
		return SUCCESS;
    }
	
	@SuppressWarnings("unchecked")
	public String jisuan() {
		try {
			Calendar starttime = null;
			if (stime != null && stime != "" && StringUtils.isNotBlank(stime)) {	
				starttime = DateUtil.parse(stime, "yyyy-MM-dd HH:mm:ss");
			}
			Calendar endtime = null;
			if (etime != null && etime != "" && StringUtils.isNotBlank(etime)) {
				endtime = DateUtil.parse(etime, "yyyy-MM-dd HH:mm:ss");
			}
			list = lotteryOrderService.get_out_amount_by_time(starttime, endtime, chupiao);
			Double count = 0.00;
			String s = "";
			NumberFormat nf = NumberFormat.getNumberInstance();
        	nf.setMaximumFractionDigits(2);
			List changeList=new ArrayList();
			
			for (Object[] objects : list) {
				
				Object [] obj= new Object[4];
				//排列3 4、排列5 5、七星彩8、超级大乐透2、22选5、湖南幸运赛车、江西多乐彩（江西11选5） 0.09
				obj[3] = Double.valueOf(String.valueOf(objects[0]));
				if(Integer.valueOf(String.valueOf(objects[1])) == 4 || Integer.valueOf(String.valueOf(objects[1])) == 5 || Integer.valueOf(String.valueOf(objects[1])) == 8 || Integer.valueOf(String.valueOf(objects[1])) == 2 || Integer.valueOf(String.valueOf(objects[1])) == 16) {
					obj[2] = 0.09;
					objects[0] = Double.valueOf(String.valueOf(objects[0])) * 0.09;
				}
				//胜负彩、进球彩、任选9场11、六场半全14、竞彩足球12和竞彩篮球15  0.08
				if (Integer.valueOf(String.valueOf(objects[1])) == 10 || Integer.valueOf(String.valueOf(objects[1])) == 11 || Integer.valueOf(String.valueOf(objects[1])) == 12 || Integer.valueOf(String.valueOf(objects[1])) == 14 ||Integer.valueOf(String.valueOf(objects[1])) == 15 ) {
					obj[2] = 0.08;
					objects[0] = Double.valueOf(String.valueOf(objects[0])) * 0.08;
				}
				//双色球1、七乐彩9、福彩3D3和江西时时彩 0.095
				if (Integer.valueOf(String.valueOf(objects[1])) == 1 || Integer.valueOf(String.valueOf(objects[1])) == 3 || Integer.valueOf(String.valueOf(objects[1])) == 9 ) {
					obj[2] = 0.095;
					objects[0] = Double.valueOf(String.valueOf(objects[0])) * 0.095;
				}
				
				
				/*全部("all")0, 双色球("ssq")1, 大乐透("dlt")2, 福彩3d("3d")3, 排列三("pls")4, 排列五("plw")5, 
				 * 重庆时时彩("ssc_CQ")6, 上海时时乐("ssl_SH")7, 七星彩("qxc")8, 七乐彩("qlc")9, 足彩14场("14sfc")10, 
				 * 足彩任9("r9")11, 竞彩足球("jczq")12, 四场进球("4cjq")13, 足彩6场半("6cb")14, 竞彩篮球("jclq")15 ;*/
				obj[0] = nf.format(objects[0]); 
				obj[1] =LotteryTypeUtil.changeLotteryTypeToName(Integer.valueOf(String.valueOf(objects[1])));

				changeList.add(obj);
				count += Double.valueOf(objects[0].toString());
			}
			list=changeList;
			sum = nf.format(count);
		} catch (Exception e) {
			// TODO: handle exception
		  
			System.out.println(e.getMessage());
		}	
		return SUCCESS;
	}
	
	public String searche() {
		System.out.println("1===============================1");
		try {
			Calendar starttime = null;
			if (stime != null && stime != "" && StringUtils.isNotBlank(stime)) {	
				starttime = DateUtil.parse(stime, "yyyy-MM-dd HH:mm:ss");
			}
			Calendar endtime = null;
			if (etime != null && etime != "" && StringUtils.isNotBlank(etime)) {
				endtime = DateUtil.parse(etime, "yyyy-MM-dd HH:mm:ss");
			}
			page = new Page<PayOutRequest>();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setAutoCount(true);
			page = outRequestService.getDatapage(page, starttime, endtime, nickname, realname, Integer.valueOf(processFlag));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public LotteryOrderService getLotteryOrderService() {
		return lotteryOrderService;
	}
	public void setLotteryOrderService(LotteryOrderService lotteryOrderService) {
		this.lotteryOrderService = lotteryOrderService;
	}

	public String getChupiao() {
		return chupiao;
	}

	public void setChupiao(String chupiao) {
		this.chupiao = chupiao;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public List<Object[]> getList() {
		return list;
	}

	public void setList(List<Object[]> list) {
		this.list = list;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Page<PayOutRequest> getPage() {
		return page;
	}

	public void setPage(Page<PayOutRequest> page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public PayOutRequestService getOutRequestService() {
		return outRequestService;
	}

	public void setOutRequestService(PayOutRequestService outRequestService) {
		this.outRequestService = outRequestService;
	}

	
	public SendTicketPlat[] getChupiao1() {
		return chupiao1;
	}

	public void setChupiao1(SendTicketPlat[] chupiao1) {
		this.chupiao1 = chupiao1;
	}

	public static void main(String[] args) {
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(0.18));
	}
}
