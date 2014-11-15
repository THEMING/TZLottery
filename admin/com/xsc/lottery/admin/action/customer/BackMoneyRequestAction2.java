package com.xsc.lottery.admin.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.PayOutRequest;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.PayOutRequestService;
import com.xsc.lottery.util.MathUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.backMoneyRequest2")
public class BackMoneyRequestAction2 extends AdminBaseAction
{
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PayOutRequestService payOutRequestService;

    private Page<BackMoneyRequest> page;

    private Long bmid;

    private Bank[] banks = Bank.values();

    private BackMoneyStatus[] status = BackMoneyStatus.values();

    private String f_statu;

    private String f_nikname;

    private String f_raelname;

    private String f_timeName;

    private Calendar f_sTime;

    private Calendar f_eTime;

    private String f_bank;

    private String f_bankCard;

    private String f_openSpace;

    private int pageNo = 1;

    private int pageSize = 200;

    private int totalPages;
    
    private String memo;
    
    private String betchId;
    
    private int total;
    
    private BigDecimal totalMoney;
    
    private BigDecimal maxMoney;
    
    private BigDecimal minMoney;
    
    private String message;
    
    public String index()
    {
        page = new Page<BackMoneyRequest>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getBackMoneyRequestPage(page, "一级审核",
                f_nikname, f_raelname, f_timeName, f_sTime, f_eTime, f_bank,
                f_bankCard, f_openSpace);
        total = page.getResult().size();
        if (total == 0) {
            maxMoney = BigDecimal.ZERO;
            minMoney = BigDecimal.ZERO;
            totalMoney = BigDecimal.ZERO;
		}
        else {
        	maxMoney = page.getResult().get(0).getMoney();
            minMoney = page.getResult().get(0).getMoney();
            totalMoney = page.getResult().get(0).getMoney();
            for (int i = 1; i < total; i++) {
            	BigDecimal money = page.getResult().get(i).getMoney();
            	totalMoney = totalMoney.add(money);
    			if(maxMoney.compareTo(money) < 0) {
    				maxMoney = money;
    			}
    			if (minMoney.compareTo(money) > 0) {
    				minMoney = money;
    			}
    		}
		}
        return "list";
    }

    public String exit()
    {
        BackMoneyRequest bmr = customerService.findBackMoneyRequest(bmid);
        if(bmr == null)
        {
        	message ="编号为{"+bmid+"}提款申请不存在,请刷新重试";
        }
        if(BackMoneyStatus.一级审核 != bmr.getStatus())
        {
        	message = "编号为{"+bmid+"}提款申请状态为"+bmr.getStatus()+",您没有审核权限";
        }
        else
        {
        	bmr.setUser(this.getCurAdminUser());
            bmr.setSendTime(Calendar.getInstance());
            bmr.setStatus(BackMoneyStatus.已取消);
            bmr.setMemo(memo);
            customerService.updateBackMoneyRequestBackMoney(bmr);
            message = "编号为{"+bmid+"}提款申请取消成功";
        }
        return index();
    }
    
    public String option()
    {
        BackMoneyRequest bmr = customerService.findBackMoneyRequest(bmid);
        if(bmr == null)
        {
        	message ="编号为{"+bmid+"}提款申请不存在,请刷新重试";
        }
        if(BackMoneyStatus.一级审核 != bmr.getStatus())
        {
        	message = "编号为{"+bmid+"}提款申请状态为"+bmr.getStatus()+",您没有审核权限";
        }
        else
        {
        	 bmr.setUser(this.getCurAdminUser());
             bmr.setStatus(BackMoneyStatus.二级审核);
         	BigDecimal feeMoney = calculateFeeMoney(bmr.getMoney());
         	bmr.setFeeMoney(feeMoney);
             customerService.updateBackMoneyRequest(bmr);
         	PayOutRequest por = new PayOutRequest();
         	por.setTime(Calendar.getInstance());
         	por.setBackMoneyRequest(bmr);
         	por.setYURREF(MathUtil.getSerialNumber(16));
         	por.setNUSAGE("账户返款");
         	Customer customer = bmr.getCustomer();
         	String city = customer.getProvince() + customer.getCity();
         	String crtbnk = city + customer.getBank().toString() + customer.getSubbranch();
         	por.setCRTBNK(crtbnk);
         	por.setCTYCOD(bmr.getCode());
         	por.setCRTPVC(city);
         	por.setState(0);
         	por.setStateDesc("等待初(一)审");
         	por.setStateTime(Calendar.getInstance());
         	por.setProgressFlag(5);
     		por.setMoney(bmr.getMoney().subtract(bmr.getFeeMoney()));
         	payOutRequestService.save(por);
         	message = "编号为{"+bmid+"}的提款申请二级审核成功";
        }
        return index();
    }
    
    public String bitchOption()
    {
    	String[] ids = betchId.split(",");
    	BackMoneyRequest bmr = null;
    	for (int i = 0; i < ids.length; i++) {
            bmr = customerService.findBackMoneyRequest(Long.parseLong(ids[i]));
            if(bmr == null)
            {
            	message +="编号为{"+bmid+"}提款申请不存在,请刷新重试";
            }
            if(BackMoneyStatus.一级审核 != bmr.getStatus())
            {
            	message += "编号为{"+bmid+"}提款申请状态为"+bmr.getStatus()+",您没有审核权限";
            }
            else
            {
            	 bmr.setUser(this.getCurAdminUser());
                 bmr.setStatus(BackMoneyStatus.二级审核);
                 BigDecimal feeMoney = calculateFeeMoney(bmr.getMoney());
             	bmr.setFeeMoney(feeMoney);
                 customerService.updateBackMoneyRequest(bmr);
             	PayOutRequest por = new PayOutRequest();
             	por.setTime(Calendar.getInstance());
             	por.setBackMoneyRequest(bmr);
             	por.setYURREF(MathUtil.getSerialNumber(16));
             	por.setNUSAGE("账户返款");
             	Customer customer = bmr.getCustomer();
             	String city = customer.getProvince() + customer.getCity();
             	String crtbnk = city + customer.getBank().toString() + customer.getSubbranch();
             	por.setCRTBNK(crtbnk);
             	por.setCTYCOD(bmr.getCode());
             	por.setCRTPVC(city);
             	por.setState(0);
             	por.setStateDesc("等待初(一)审");
             	por.setStateTime(Calendar.getInstance());
             	por.setProgressFlag(5);
     			por.setMoney(bmr.getMoney().subtract(bmr.getFeeMoney()));
             	payOutRequestService.save(por);
             	message += "编号为{"+bmid+"}的提款申请二级审核成功";
            }
		}
        return index();
    }

    public void setBmid(Long bmid)
    {
        this.bmid = bmid;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

    public BackMoneyStatus[] getStatus()
    {
        return status;
    }

    public String getF_timeName()
    {
        return f_timeName;
    }

    public void setF_timeName(String fTimeName)
    {
        f_timeName = fTimeName;
    }

    public String getF_nikname()
    {
        return f_nikname;
    }

    public void setF_nikname(String fNikname)
    {
        f_nikname = fNikname;
    }

    public String getF_raelname()
    {
        return f_raelname;
    }

    public void setF_raelname(String fRaelname)
    {
        f_raelname = fRaelname;
    }

    public Calendar getF_sTime()
    {
        return f_sTime;
    }

    public void setF_sTime(Calendar fSTime)
    {
        f_sTime = fSTime;
    }

    public Calendar getF_eTime()
    {
        return f_eTime;
    }

    public void setF_eTime(Calendar fETime)
    {
        f_eTime = fETime;
    }

    public String getF_bank()
    {
        return f_bank;
    }

    public void setF_bank(String fBank)
    {
        f_bank = fBank;
    }

    public String getF_bankCard()
    {
        return f_bankCard;
    }

    public void setF_bankCard(String fBankCard)
    {
        f_bankCard = fBankCard;
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

    public Page<BackMoneyRequest> getPage()
    {
        return page;
    }

    public Bank[] getBanks()
    {
        return banks;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public String getF_openSpace()
    {
        return f_openSpace;
    }

    public void setF_openSpace(String fOpenSpace)
    {
        f_openSpace = fOpenSpace;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBetchId() {
		return betchId;
	}

	public void setBetchId(String betchId) {
		this.betchId = betchId;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public BigDecimal getMaxMoney() {
		return maxMoney;
	}

	public BigDecimal getMinMoney() {
		return minMoney;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}
	
	private BigDecimal calculateFeeMoney(BigDecimal applyMoney)
	{
		if (applyMoney == null || applyMoney == BigDecimal.ZERO) {
			return BigDecimal.ZERO;
		}
		else if (applyMoney.compareTo(BigDecimal.valueOf(10000)) < 0) {
			return BigDecimal.valueOf(2);
		}
		else if (applyMoney.compareTo(BigDecimal.valueOf(100000)) < 0) {
			return BigDecimal.valueOf(5);
		}
		else if (applyMoney.compareTo(BigDecimal.valueOf(500000)) < 0) {
			return BigDecimal.valueOf(8);
		}
		else if (applyMoney.compareTo(BigDecimal.valueOf(1000000)) < 0) {
			return BigDecimal.valueOf(10);
		}
		else {
			return BigDecimal.valueOf(100);
		}
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
