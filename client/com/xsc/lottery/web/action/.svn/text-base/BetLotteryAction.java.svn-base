package com.xsc.lottery.web.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Chase;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.ChaseItermStatus;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.task.ticket.TicketBusinessFactory;
import com.xsc.lottery.task.ticket.TicketTreatmentWork;
import com.xsc.lottery.web.action.json.AjaxResultStatusType;
import com.xsc.lottery.web.action.json.JsonMsgBean;
import com.xsc.lottery.web.validate.BetValidate;

/**
 * purchase action
 */

@Scope("prototype")
@Controller("buyLottery.betIndeAct")
@SuppressWarnings({ "unused", "serial" })
public class BetLotteryAction extends LotteryClientBaseAction
{
    // 期数
    private String term;
    
    // 彩种类型
    private String lotteryType;
    
    // 总金额
    private BigDecimal money;
    
    // 单注金额 乐透玩法为3元.默认2元
    private BigDecimal oneMoney = new BigDecimal(2);
    
    // 倍数
    private int multiple;
    
    // 文件上传
    private File upload;
    private String filePath;
    
    // 投注方案
    // playtype-01,02,03,04,05|04,05^playtype-01,02,03,04,05|04,05
    private String codes;
    
    // 合买-描述
    private String description;

	// 合买-每份金额
    private BigDecimal perMoney;

	// 合买-总份数 可以不要
    private int totalPart;

	// 合买-发起人购买的份数
    private int buyPart;

	// 合买-所保份数
    private int insurePart;

	// 合买-中奖佣金 实际是百分比
    private int commision;

	// 合买-保密状态
    private String secretType;
    
	// 参与合买-合买ID
    private Long communityId;
    
	// 追号-总金额
    private BigDecimal chaseMoney;
    
	// 追号-期次列表
    private String chaseTerm;

	// 追号-倍数列表
    private String chaseMultiple;
    
	// 追号-停追金额
    private BigDecimal chaseStopMoney;
    
	// 追号中奖金额（如：达到1000）停止
    private BigDecimal winStopped;
    
    private Cookie cookie;
    @Autowired
    private LotteryTermService lotteryTermService;

    @Autowired
    private ChaseService chaseService;

    @Autowired
    private BetValidate betValidate;

    @Autowired
    private LotteryOrderService lotteryOrderService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private TicketBusinessFactory ticketBusinessFactory;

    @Autowired
    public CustomerService customerService;
    
    @Autowired
    private MatchArrangeService matchArrangeService;
    
    //默认 用户代购
    public String index()
    {
    	//System.out.println(this.getCurCustomerFrom());
        LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo(term,
                LotteryType.enToType(lotteryType));
        
        /** 判断期次是否过期 */
        if(!betValidate.validataTerm(lotteryTerm)) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "期数已过期或者已停售"));
            return AJAXJSON;
        }
        
        /** 判断竞彩足球投注是否过期 */
        if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩足球) && 
        		!betValidate.validateMatches(codes.split("\\^"), LotteryType.竞彩足球)) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您选择的部分比赛已过期或者已停售"));
        	return AJAXJSON;
        }
        /** 判断竞彩篮球投注是否过期 */
        if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球) && 
        		!betValidate.validateMatches(codes.split("\\^"), LotteryType.竞彩篮球)) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您选择的部分比赛已过期或者已停售"));
        	return AJAXJSON;
        }
        if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球) && 
        		!betValidate.validateMatchesJCMethod(codes.split("\\^"), LotteryType.竞彩篮球)) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您选择的部分比赛不允许该种竞彩!"));
        	return AJAXJSON;
        }
		
        
        /** 生成方案描述集合并且验证每个方案描述 **/
        List<PlanItem> planItemList;
        try {
        	/*文件上传*/
        	if(upload != null) {
        		filePath = upload.getPath();
        	}
            planItemList = betValidate.validateReckon(LotteryType
                    .enToType(lotteryType), codes.split("\\^"), money,
                    filePath, multiple, oneMoney);
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        
        /** 检查用户钱包 */
        Customer customer = this.getCurCustomer();
        if(customer.getWallet().getStatus() == 1) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您的钱包已被冻结，请联系管理员!"));
            return AJAXJSON;
        }
        
        if(customer.getWallet().getBalance().subtract(money).intValue() < 0) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "钱包余额不足"));
            return AJAXJSON;
        }
        int multiple1;
        
		for(int k=0;k<multiple/99+1;k++){
        	if(k<multiple/99)
        	{
        		 multiple1=99;
        	}
        	else
        	{
       		     multiple1=multiple%99;
				 if(multiple1==0) continue;
       		}
        			
        	List<PlanItem> planItemList1;
            try {
            	/*文件上传*/
            	if(upload != null) {
            		filePath = upload.getPath();
            	}
                planItemList1 = betValidate.validateReckon(LotteryType
                        .enToType(lotteryType), codes.split("\\^"), money,
                        filePath, multiple, oneMoney);
            }
            catch (Exception e) {
                setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                        AjaxResultStatusType._0001, e.getMessage()));
                return AJAXJSON;
            }
            
        Plan plan = new Plan(LotteryType.enToType(lotteryType), new BigDecimal(
                money.intValue() / multiple), oneMoney);
        Order order;
        cookie=checkCookies();
        try {
            WalletLog walletLog = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, plan.getMoney().multiply(
                            new BigDecimal(multiple1)), BigDecimal.ZERO,
                    BigDecimal.ZERO, plan.getType().name(), WalletLogType.代购费用,
                    plan.getNumberNo());
            if(cookie!=null){
            	if(cookie.getValue().split("!")[1]!=null)
            		walletLog.setPid(cookie.getValue().split("!")[1]);
            	if(cookie.getValue().split("!")[2]!=null)
            		walletLog.setAdid(cookie.getValue().split("!")[2]);
            }
            customerService.addWalletLog(customer.getWallet().getId(),
                    walletLog);
            
            order = lotteryOrderService.createBetOrder(customer, LotteryType
                    .enToType(lotteryType), plan, planItemList1, lotteryTerm,
                    multiple1);
            
            if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩足球)
            		|| LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球)) {
            	MatchArrange lastMatch = matchArrangeService.getLastMatchInOrder(codes, LotteryType.enToType(lotteryType));
            	order.setLastMatch(lastMatch);
            	lotteryOrderService.save(order);
            }
            
            TicketTreatmentWork ttw = ticketBusinessFactory
            	.getTreatmentTicketByType(lotteryTerm.getOutPoint());
            //ttw.addTaker(order);
            ttw.putOrderToQueue(order);
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndBalance(AjaxResultStatusType._0000,customer.getWallet().getBalance()));
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        }
        return AJAXJSON;
    }

    // 发起合买
    public String togetherSale()
    {
        LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo(term,
                LotteryType.enToType(lotteryType));
        Customer customer = this.getCurCustomer();
        BigDecimal sumMoney = new BigDecimal((buyPart * perMoney.intValue())
                + (insurePart * perMoney.intValue()));
        if(customer.getWallet().getStatus() == 1) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您的钱包已被冻结，请联系管理员!"));
            return AJAXJSON;
        }
        if (customer.getWallet().getBalance().subtract(sumMoney).intValue() < 0) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "钱包余额不足"));
            return AJAXJSON;
        }
        
        /** 判断期次是否过期 */
        if (!betValidate.validataTogetherTerm(lotteryTerm)) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "合买已截止"));
            return AJAXJSON;
        }
        
        /** 判断竞彩足球投注是否过期 */
        if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩足球) && 
        		!betValidate.validateMatches(codes.split("\\^"), LotteryType.竞彩足球)) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您选择的部分比赛已过期或者已停售"));
        	return AJAXJSON;
        }
		
		if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球) && 
        		!betValidate.validateMatches(codes.split("\\^"), LotteryType.竞彩篮球)) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您选择的部分比赛已过期或者已停售"));
        	return AJAXJSON;
        }
        
        /** 生成合买方案描述集合并且验证每个方案描述 **/
        List<PlanItem> planItemList;
        try {
        	/*文件上传*/
        	if(upload != null) {
        		filePath = upload.getPath();
        	}
            planItemList = betValidate.validateReckon(LotteryType.enToType(lotteryType),
            		codes.split("\\^"), money, filePath, multiple, oneMoney);
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        
        Plan plan = new Plan(LotteryType.enToType(lotteryType), new BigDecimal(
                money.intValue() / multiple), oneMoney);
        
        /** 生成方案描述集合并且验证每个方案描述 **/
        Community community;
        try {
            community = betValidate.validateCommunity(plan, lotteryTerm,
                    customer, description, multiple, perMoney, totalPart,
                    insurePart, commision, buyPart, secretType);
            
            /** 竞彩足球特殊处理（添加最早和最晚一场比赛） */
            if(LotteryType.enToType(lotteryType).equals(LotteryType.竞彩足球)
			||LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球)) {
            	MatchArrange firstMatch = matchArrangeService.getFirstMatchInOrder(codes);
            	MatchArrange lastMatch = matchArrangeService.getLastMatchInOrder(codes, LotteryType.enToType(lotteryType));
            	community.setFirstMatch(firstMatch);
            	community.setLastMatch(lastMatch);
            }
			
		
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
         
        Cookie cookie=checkCookies();
    	communityService.saveAndCreatePart(community, planItemList,cookie);
     	//判断方案是否已经满员
        try {
        	buyPart = 0;
    		Order order = lotteryOrderService.joinCommunity(community.getId(), customer, buyPart,cookie);
    	 	if (null != order) {
    	 		TicketTreatmentWork ttw = ticketBusinessFactory
    	                    .getTreatmentTicketByType(lotteryTerm.getOutPoint());
    	 		ttw.addTaker(order);
    	  	}
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        
        setJsonString(JsonMsgBean.getResultStatusJsonStrByType(AjaxResultStatusType._0000));
        return AJAXJSON;
    }

    // 追号
    public String chaseSale()
    {
        LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo(term,
                LotteryType.enToType(lotteryType));
        
        Customer customer = this.getCurCustomer();
        if(customer.getWallet().getStatus() == 1) {
        	setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您的钱包已被冻结，请联系管理员!"));
            return AJAXJSON;
        }
        if (customer.getWallet().getBalance().subtract(chaseMoney).intValue() < 0) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "钱包余额不足"));
            return AJAXJSON;
        }
        
        /** 判断期次是否过期 */
        if (!betValidate.validataTerm(lotteryTerm)) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "期数已过期或者已停售"));
            return AJAXJSON;
        }
        
        /** 足球不能追号 */

        /** 生成方案描述集合并且验证每个方案描述 */
        List<PlanItem> planItemList;
        try {
        	filePath = null;
            planItemList = betValidate.validateReckon(LotteryType
                    .enToType(lotteryType), codes.split("\\^"), money,
                    filePath, multiple, oneMoney);
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        Plan plan = new Plan(LotteryType.enToType(lotteryType), new BigDecimal(
                money.intValue() / multiple), oneMoney);
        
        /** 生成追号方案描述并且验证 */
        List<ChaseItem> chaseItemList;
        try {
            chaseItemList = betValidate.validateChase(plan, LotteryType
                    .enToType(lotteryType), customer, chaseTerm.split("\\,"),
                    chaseMultiple.split("\\,"), chaseStopMoney, chaseMoney);
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        boolean currenTerm = false;
        Order order = null;
  
        if (chaseItemList.get(0).getTermNo().equals(lotteryTerm.getTermNo())) {
            try {
                WalletLog walletLog = new WalletLog(BusinessType.支出,
                        BigDecimal.ZERO, money.multiply(new BigDecimal(
                                chaseItemList.get(0).getMultiple())),
                        BigDecimal.ZERO, BigDecimal.ZERO, "用户当前期追号",
                        WalletLogType.追号费用, plan.getNumberNo());
                customerService.addWalletLog(customer.getWallet().getId(),
                        walletLog);
                order = lotteryOrderService.createBetOrder(customer,
                        LotteryType.enToType(lotteryType), plan, planItemList,
                        lotteryTerm, chaseItemList.get(0).getMultiple());
                chaseMoney = chaseMoney.subtract(money.multiply(new BigDecimal(
                        chaseItemList.get(0).getMultiple())));
            }
            catch (Exception e) {
                setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                        AjaxResultStatusType._0001, e.getMessage()));
                return AJAXJSON;
            }
            TicketTreatmentWork ttw = ticketBusinessFactory
                    .getTreatmentTicketByType(lotteryTerm.getOutPoint());
            ttw.addTaker(order);
            chaseItemList.get(0).setStatus(ChaseItermStatus.已追号);
            chaseItemList.get(0).setOrder(order);
            currenTerm = true;
        }
        else {
            plan = lotteryOrderService.createPlanAndItem(plan, planItemList);
        }
        if (chaseMoney.intValue() > 0) {
            // 追号扣款冻结
            WalletLog walletLog = new WalletLog(BusinessType.支出,
                    BigDecimal.ZERO, chaseMoney, chaseMoney, BigDecimal.ZERO,
                    "用户追号冻结", WalletLogType.追号冻结, plan.getNumberNo());
            customerService.addWalletLog(customer.getWallet().getId(),
                    walletLog);
        }
        // 保存追号 每期一个订单
        if (currenTerm) {
            chaseMoney = chaseMoney.add(money.multiply(new BigDecimal(
                    chaseItemList.get(0).getMultiple())));
        }
        Chase chase = new Chase(customer, lotteryTerm.getType(), chaseMoney,
                chaseStopMoney, chaseItemList.size(), plan);
        chase.setItems(chaseItemList);
        chaseService.saveChaseAndIterm(chase);
        setJsonString(JsonMsgBean.getResultStatusJsonStrByType(AjaxResultStatusType._0000));
        return AJAXJSON;
    }

    // 参与合买action
    public String communityjoin()
    {
        LotteryTerm lotteryTerm = lotteryTermService.getByTypeAndTermNo(term,
                LotteryType.enToType(lotteryType));
        Customer customer = this.getCurCustomer();

        if(buyPart <= 0) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "认购数不能小于/等于0!"));
            return AJAXJSON;
        }
        
        /** 判断期次是否过期 */
        if (!betValidate.validataTogetherTerm(lotteryTerm)) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "合买已截止"));
            return AJAXJSON;
        }
        
        /** 判断竞彩足球和竞彩篮球的合买是否截止 */
        if((LotteryType.enToType(lotteryType).equals(LotteryType.竞彩足球) 
        		|| LotteryType.enToType(lotteryType).equals(LotteryType.竞彩篮球))
        		&& !betValidate.validateCommunity(communityId)) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, "您参与的合买已经截止"));
            return AJAXJSON;
        }
        
        Order order;
        try {
        	Cookie cookie=checkCookies();
            order = lotteryOrderService.joinCommunity(communityId, customer, buyPart,cookie);
            if (null != order) {
                TicketTreatmentWork ttw = ticketBusinessFactory
                        .getTreatmentTicketByType(lotteryTerm.getOutPoint());
                ttw.addTaker(order);
            }
        }
        catch (Exception e) {
            setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
                    AjaxResultStatusType._0001, e.getMessage()));
            return AJAXJSON;
        }
        setJsonString(JsonMsgBean.getResultStatusJsonStrByType(AjaxResultStatusType._0000));
        return AJAXJSON;
    }
    
    private  Cookie checkCookies(){
     Cookie[] cookies = ServletActionContext.getRequest().getCookies();
     for(Cookie cookie : cookies){  
        	 if(cookie.getName().equals("from"))
        	 {
        		 if(cookie.getValue().split("!")[0].equals("nTalker")==false) return null;
        		 if(cookie.getValue().split("!").length<3) return null;

        		 
        		 return cookie;
        	 }
     } 
     return null;
    }

    public void setTerm(String term)
    {
        this.term = term;
    }

    public void setLotteryType(String lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public void setMultiple(int multiple)
    {
        this.multiple = multiple;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public void setLotteryTermService(LotteryTermService lotteryTermService)
    {
        this.lotteryTermService = lotteryTermService;
    }

    public void setCodes(String codes)
    {
        this.codes = codes;
    }

    public void setWinStopped(BigDecimal winStopped)
    {
        this.winStopped = winStopped;
    }

    public void setLotteryOrderService(LotteryOrderService lotteryOrderService)
    {
        this.lotteryOrderService = lotteryOrderService;
    }

    public void setDescription(String description)
    {
        try {
            this.description = URLDecoder.decode(description, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            this.description = description;
        }
    }

    public void setPerMoney(BigDecimal perMoney)
    {
        this.perMoney = perMoney;
    }

    public void setTotalPart(int totalPart)
    {
        this.totalPart = totalPart;
    }

    public void setBuyPart(String buyPart)
    {
        if (StringUtils.isEmpty(buyPart)) {
            buyPart = "0";
        }
        this.buyPart = Integer.parseInt(buyPart);
    }

    public void setInsurePart(int insurePart)
    {
        this.insurePart = insurePart;
    }

    public void setCommision(int commision)
    {
        this.commision = commision;
    }

    public void setSecretType(String secretType)
    {
        this.secretType = secretType;
    }

    public void setBetValidate(BetValidate betValidate)
    {
        this.betValidate = betValidate;
    }

    public void setTicketBusinessFactory(
            TicketBusinessFactory ticketBusinessFactory)
    {
        this.ticketBusinessFactory = ticketBusinessFactory;
    }

    public void setChaseMoney(BigDecimal chaseMoney)
    {
        this.chaseMoney = chaseMoney;
    }

    public void setChaseTerm(String chaseTerm)
    {
        this.chaseTerm = chaseTerm;
    }

    public void setChaseMultiple(String chaseMultiple)
    {
        this.chaseMultiple = chaseMultiple;
    }

    public void setChaseStopMoney(BigDecimal chaseStopMoney)
    {
        this.chaseStopMoney = chaseStopMoney;
    }

    public void setCommunityId(Long communityId)
    {
        this.communityId = communityId;
    }

    public BigDecimal getOneMoney()
    {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney)
    {
        this.oneMoney = oneMoney;
    }

	public void setUpload(File upload) {
		this.upload = upload;
	}
}
