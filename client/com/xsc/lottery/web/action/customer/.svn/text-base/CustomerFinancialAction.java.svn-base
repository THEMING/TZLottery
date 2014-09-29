package com.xsc.lottery.web.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.business.YouHuiMa;
import com.xsc.lottery.entity.business.YouHuiMa.YouHuiMaType;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.YouHuiMaService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerFinancial")
public class CustomerFinancialAction extends LotteryClientBaseAction
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private LotteryOrderService orderService;

    @Autowired
    private CommunityService communityService;
    
    @Autowired
	private YouHuiMaService youHuiMaService;
    
//    @Autowired
//	private CityNoService cityNoService;

    private Customer customer;

    private Page<WalletLog> page;

    private int pageNo = 1;

    private int pageSize = 20;

    private int totalPages;

    private Calendar beginTime;

    private Calendar endTime;

    private int inORout;

    private int inSum = 0;

    private int outSum = 0;

    private BigDecimal inTotleMoney = new BigDecimal(0);

    private BigDecimal outTotleMoney = new BigDecimal(0);

    private BigDecimal paymentMoeny = new BigDecimal(0);

    private BigDecimal freezeMoney = new BigDecimal(0);

    private BigDecimal outMoney = new BigDecimal(0);

    private WalletLogType[] types = WalletLogType.values();

    private BusinessType[] btypes = BusinessType.values();

    private String stype;

    private String method;

    private List<BackMoneyRequest> bmrList;
    private List<Bank> banksList;
    private String bank;
    private String realName;
    private String province;
    private String city;
    private String subbranch;
    private String bankNumber;
    private String numberNo;

    private String message;
    private int tabIndex;
    private String zhcz;
    private String issucc;
    private String yuanying; //失败的原因
    
    private String duihuanma; //用户输入的优惠码
    private String money;
    private String username;
    private String tcp_succ;
    private String tcp_issucc;
    
    private String succ; //兑换是否成功
    
    public String index()
    {	
 
    	backmq();
    	tabIndex = 1;
        method = StringUtils.isBlank(method) ? "zhzl" : method;
        this.customer = this.getCurCustomer();
        if (method.equals("zhzl")) {
            List<Order> orderList = orderService.getOrder(customer, null);
            List<Order> orderList1 = orderService.getOrderByNotCustomer(customer);
            // 统计充值总额
            paymentMoeny = customerService.getChongZhiSum(customer);
            // 统计购彩总额
            for (Order o : orderList) {
                if (o.getCommunity() != null) {
                    List<Part> listPart = communityService.getPartList(o
                            .getCommunity(), customer);
                    for (Part p : listPart) {
                        if (o.getStatus().equals(OrderStatus.出票成功))
                            outMoney = outMoney.add(p.getMoney());
                    }
                }
                else {
                    outMoney = outMoney.add(o.getOutAmount());
                }
            }
            // 统计不是该用户发起的合买订单金额
            for (Order o : orderList1) {
                List<Part> listPart = communityService.getPartList(o
                        .getCommunity(), customer);
                for (Part p : listPart) {
                    if (o.getStatus().equals(OrderStatus.出票成功))
                        outMoney = outMoney.add(p.getMoney());
                }
            }
        }
        return method;
    }

    //资金明细
    public String fundDetailed()
    {
    	tabIndex = 2;
    	backmq();
    	
        Customer customer = this.getCurCustomer();
        page = new Page<WalletLog>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getWalletLogPage(page, customer.getNickName(),
                numberNo, beginTime, endTime, stype);
        List<WalletLog> list = customerService.getWalletLogByCustomer(customer
                .getWallet());
        for (WalletLog wl : list) {
            if (wl.getBusinessType().equals(BusinessType.收入)) {
                inSum += 1;
                inTotleMoney = inTotleMoney.add(wl.getInMoney());
            }
            if (wl.getBusinessType().equals(BusinessType.支出)) {
                outSum += 1;
                outTotleMoney = outTotleMoney.add(wl.getOutMoney());
            }
        }
        if (list != null && list.size() > 0) {
            freezeMoney = list.get(0).getWallet().getFreezeMoney();
        }
        return "zjmx";
    }

    //帐户充值
    public String chongzhi() {
    	tabIndex = 3;
    	issucc = (String) this.getSession().get("succ");
    	if (issucc.equals("0")) {
    		zhcz = "兑换成功";
		} else {
			zhcz = "兑换失败";
			if (issucc.equals("1")) {
				yuanying = "此优惠码未激活，确认后联系我们！";
			} else if(issucc.equals("2")){
				yuanying = "此优惠劵已兑换！";
			}else if(issucc.equals("3")){
				yuanying = "优惠码不存在";
			}
		}
		return "zhcz";
	}
    
    public BigDecimal getInTotleMoney()
    {
        return inTotleMoney;
    }

    public void setInTotleMoney(BigDecimal inTotleMoney)
    {
        this.inTotleMoney = inTotleMoney;
    }

    public void setInSum(int inSum)
    {
        this.inSum = inSum;
    }
    
    public String payHome()
    {
    	tabIndex = 3;
    	customer = this.getCurCustomer();
    	return "payhome";
    }
    public String backmq()
    {
        customer = this.getCurCustomer();
        banksList = Bank.allList;
        //查询一年的交易记录
        bmrList = customerService.getBackMoneyRequestByRecently(customer, 12);
        return "zhtx";
    }
    
    /** 用户提现 */
    public String drawmoney()
    {
    	tabIndex = 4;
        if (inTotleMoney.intValue() > 0) {
            customer = this.getCurCustomer();
            if (StringUtils.isBlank(customer.getBankNumber())) {
               message = "请先绑定银行卡!"; 
               return backmq();
            }
//            CityNo cn = cityNoService.getByCity(customer.getCity());
            String cityCode = "";
//            if (cn != null) {
//				cityCode = cn.getNo();
//			}
            BackMoneyRequest backMoneyRequest = new BackMoneyRequest(customer,
                    customer.getRealName(), customer.getBank(), 
                    customer.getBankNumber(), inTotleMoney, customer.getSubbranch(), cityCode);
            customerService.saveBackMoneyRequest(backMoneyRequest);
            message = "提款成功，待审核!"; 
        }
        return backmq();
    }

    public String bindBank()
    {
    	tabIndex = 5;
        Customer custm = this.getCurCustomer();
        boolean bool = true;
        if (bool && StringUtils.isEmpty(province) && StringUtils.isEmpty(city)) {
            addActionMessage("地区不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(bank)) {
            addActionMessage("开户银行不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(subbranch)) {
            addActionMessage("开户银行支行不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(bankNumber)) {
            addActionMessage("银行卡号错误!");
            bool = false;
        }
        if (StringUtils.isEmpty(realName)) {
            addActionMessage("开户名不能为空!");
            bool = false;
        }
        if (!realName.equals(custm.getRealName())) {
            addActionMessage("开户名与真实姓名不一至!");
            bool = false;
        }

        if (bool) {
            custm.setCity(city);
            custm.setProvince(province);
            custm.setBank(Bank.valueOf(bank));
            custm.setSubbranch(subbranch);
            custm.setBankNumber(bankNumber);
            custm.setBankName(realName);
            customerService.update(custm);
            addActionMessage("修改成功!");
        }
        return backmq();
    }
    
    /** 优惠码兑换*/
	public String duihuan()
    {
		List<YouHuiMa> youhuimalist;
		System.out.println("调用duihuan方法");
		System.out.println(duihuanma + "000000000");
		youhuimalist = youHuiMaService.getYouHuiMaByNumber(duihuanma);
		if (youhuimalist.size() != 0) {
			if(youhuimalist.get(0).getType().equals("已激活")){
				customer = this.getCurCustomer();
				
				youhuimalist.get(0).setType(YouHuiMaType.已兑换);
				youHuiMaService.update(youhuimalist.get(0));
				
				WalletLog walletLog = new WalletLog(BusinessType.收入,
					youhuimalist.get(0).getMoney(), BigDecimal.ZERO,
					BigDecimal.ZERO, BigDecimal.ZERO, "兑换卷",
					WalletLogType.活动赠送, "");
				customerService.addWalletLog(customer.getWallet().getId(), 
					walletLog);
				succ = "0";
			}else if(youhuimalist.get(0).getType().equals("未激活")){
				succ = "1";
			}else {
				succ = "2";
			}
			
		} else {
			succ = "3";
		}
		this.getSession().put("succ", succ);
    	return SUCCESS;
    }
	
    
    public BigDecimal getFreezeMoney()
    {
        return freezeMoney;
    }

    public String getStype()
    {
        return stype;
    }

    public void setStype(String stype)
    {
        this.stype = stype;
    }

    public WalletLogType[] getTypes()
    {
        return types;
    }

    public Page<WalletLog> getPage()
    {
        return page;
    }

    public void setPage(Page<WalletLog> page)
    {
        this.page = page;
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

    public Calendar getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime)
    {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public int getInORout()
    {
        return inORout;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public int getInSum()
    {
        return inSum;
    }

    public int getOutSum()
    {
        return outSum;
    }

    public BigDecimal getOutTotleMoney()
    {
        return outTotleMoney;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public List<BackMoneyRequest> getBmrList()
    {
        return bmrList;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setSubbranch(String subbranch)
    {
        this.subbranch = subbranch;
    }

    public void setBankNumber(String bankNumber)
    {
        this.bankNumber = bankNumber;
    }

    public List<Bank> getBanksList()
    {
        return banksList;
    }

    public BigDecimal getPaymentMoeny()
    {
        return paymentMoeny;
    }

    public BigDecimal getOutMoney()
    {
        return outMoney;
    }

    public String getNumberNo()
    {
        return numberNo;
    }

    public void setNumberNo(String numberNo)
    {
        this.numberNo = numberNo;
    }

    public String getMessage()
    {
        return message;
    }

    public BusinessType[] getBtypes()
    {
        return btypes;
    }

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getZhcz() {
		return zhcz;
	}

	public void setZhcz(String zhcz) {
		this.zhcz = zhcz;
	}

	public String getIssucc() {
		return issucc;
	}

	public void setIssucc(String issucc) {
		this.issucc = issucc;
	}

	public String getYuanying() {
		return yuanying;
	}

	public void setYuanying(String yuanying) {
		this.yuanying = yuanying;
	}

	public String getDuihuanma() {
		return duihuanma;
	}

	public void setDuihuanma(String duihuanma) {
		this.duihuanma = duihuanma;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTcp_succ() {
		return tcp_succ;
	}

	public void setTcp_succ(String tcp_succ) {
		this.tcp_succ = tcp_succ;
	}

	public String getTcp_issucc() {
		return tcp_issucc;
	}

	public void setTcp_issucc(String tcp_issucc) {
		this.tcp_issucc = tcp_issucc;
	}

	
}
