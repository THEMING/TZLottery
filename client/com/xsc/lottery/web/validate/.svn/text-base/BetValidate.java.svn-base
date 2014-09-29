package com.xsc.lottery.web.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SecretType;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.MatchArrangeService;

@Component
public class BetValidate
{
    @Autowired
    private LotteryHandleFactory handleFactory;
    
    @Autowired
    public MatchArrangeService matchArrangeService;
    
    @Autowired
    private CommunityService communityService;
    
    /** 期数验证。是否过期。是否停售 */
    public boolean validataTerm(LotteryTerm term)
    {
        if (null == term) {
            return false;
        }

        // 是否为当前期
        if (!term.isCurrent()) {
            return false;
        }

        // 判断类型是否在销售中
        if (!(TermStatus.销售中.equals(term.getTermStatus()) || TermStatus.合买截止
                .equals(term.getTermStatus()))) {
            return false;
        }

        // 判断销售截止时间
        if (term.getStopSaleTime().compareTo(Calendar.getInstance()) <= 0) {
            return false;
        }

        return true;
    }

    /** 是否合买 */
    public boolean validataTogetherTerm(LotteryTerm term)
    {
        if (null == term) {
            return false;
        }

        // 是否为当前期
        if (!term.isCurrent()) {
            return false;
        }

        // 判断类型是否在销售中
        if (!TermStatus.销售中.equals(term.getTermStatus())) {
            return false;
        }

        // 判断销售截止时间
        if (term.getStopTogetherSaleTime().compareTo(Calendar.getInstance()) <= 0) {
            return false;
        }

        return true;
    }

    // 将客户端传过来的字符串转化成planitem集合
    public List<PlanItem> validateReckon(LotteryType type, String[] codes,
            BigDecimal money, String filePath, int multiple, BigDecimal oneMoney)
            throws Exception
    {
    	List<PlanItem> list = new ArrayList<PlanItem>();
        int sumAmount = 0;
        if (StringUtils.isBlank(filePath)) {
            for (String code : codes) {
                // 号码验证
                try {
                    PlanItem planitem = handleFactory.getLotteryHandleFactory(
                            type).perseCodeStringTOPlanItem(code, oneMoney);

                   
                    sumAmount += planitem.getBetCount();
                    list.add(planitem);
                }
                catch (Exception e) {
                    throw e;
                }
            }
            
            if (oneMoney.intValue() * multiple * sumAmount != money.intValue()
                    || money.intValue() <= 0) {
                throw new RuntimeException("方案金额错误");
            }
        }
        else {
        	list = handleFactory.getLotteryHandleFactory(type).validateUploadFile(
        			filePath, oneMoney, multiple, money);
        }
        
        return list;
    }

    /** 验证合买方案    */
    // 将客户端传过来的字符串转化成 -合买方案描述
    public Community validateCommunity(Plan plan, LotteryTerm lotteryTerm,
            Customer customer, String description, int multiple,
            BigDecimal perMoney, int totalPart, int insurePart, int commision,
            int buyPart, String secretType) throws Exception
    {
        Community community = new Community();
        if ((buyPart + insurePart) > totalPart) {
            throw new RuntimeException("请求方案购买份数错误");
        }

        if ((buyPart * 100) / totalPart < 20) {
            throw new RuntimeException("请求方案购买份数不底20%错误");
        }

        if (StringUtils.isBlank(description)) {
            description = "一彩票给大家带来好运！";
        }

        if (StringUtils.isBlank(secretType)) {
            throw new RuntimeException("内容是否公开没有值");
        }

        try {
            community.setBuyPart(buyPart);
            community.setCommision(commision);
            if (buyPart == totalPart) {
                community.setCommunityType(CommunityType.已满员);
            }
            community.addOkPart(buyPart);
            community.setCreateTimer(Calendar.getInstance());
            community.setCustomer(customer);
            community.setDescription(description);
            community.setInsurePart(insurePart);
            community.setMultiple(multiple);
            community.setPerMoney(perMoney);
            community.setPlan(plan);
            community.setSecretType(SecretType.getByNikeName(secretType));
            community.setTerm(lotteryTerm);
            community.setTotalPart(totalPart);
        }
        catch (Exception e) {
            throw e;
        }
        return community;
    }

    /** 验证追号方案   */
    // 将客户端传过来的字符串转化成 -追号方案描述
    public List<ChaseItem> validateChase(Plan plan, LotteryType lotteryType,
            Customer customer, String[] chaseTerm, String[] chaseMultiple,
            BigDecimal chaseStopMoney, BigDecimal chaseMoney) throws Exception
    {
        List<ChaseItem> chaseList = new ArrayList<ChaseItem>();
        if (chaseMultiple.length > 0 && chaseTerm.length > 0
                && !StringUtils.isEmpty(chaseTerm[0])) {
            if (chaseMultiple.length != chaseTerm.length) {
                throw new RuntimeException("请求追号方案错误");
            }
            BigDecimal chaseTotleMoney = new BigDecimal(0);
            for (String cm : chaseMultiple) {
                if (!StringUtils.isEmpty(cm) && Integer.parseInt(cm) > 0)
                    chaseTotleMoney = chaseTotleMoney.add(new BigDecimal(cm)
                            .multiply(plan.getMoney()));
            }
            if (chaseTotleMoney.intValue() != chaseMoney.intValue()) {
                throw new RuntimeException("请求追号方案金额错误");
            }
            for (int i = 0; i < chaseTerm.length; i++) {
                if (!StringUtils.isEmpty(chaseMultiple[i])
                        && !StringUtils.isEmpty(chaseTerm[i])) {
                    BigDecimal multiple = new BigDecimal(chaseMultiple[i]);
                    ChaseItem chaseItem = new ChaseItem();
                    chaseItem.setCustomer(customer);
                    chaseItem.setLotteryType(lotteryType);
                    chaseItem.setMoney(multiple.multiply(plan.getMoney()));
                    chaseItem.setMultiple(Integer.parseInt(chaseMultiple[i]));
                    chaseItem.setPlan(plan);
                    chaseItem.setTermNo(chaseTerm[i]);
                    chaseList.add(chaseItem);
                }
            }
        }
        else {
            throw new RuntimeException("请选择追号期次");
        }
        return chaseList;
    }
    
    public boolean validateCommunity(long id)
    {
    	try {
    		Community community = communityService.findById(id);
    		if(community.getFirstMatch().getStopSaleTime().compareTo(Calendar.getInstance()) <= 0) {
    			return false;
    		}
    	}
    	catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    
    public boolean validateMatches(String[] bets, LotteryType type)
    {
    	for (int i=0; i<bets.length; i++) {
    		String[] matches = bets[i].split("\\|")[1].split(",");
    		for (int j=0; j<matches.length; j++) {
    			String matchNo = matches[j].split("=")[0];
    			Calendar s = Calendar.getInstance();
	        	if (matchArrangeService.getMatchMatchByNoAndType(matchNo, type).getStopSaleTime().before(s)) 	{
	        		return false; 
	        	}
    		}
    	}
    	return true;
    }
    /*
     * 验证篮球竞猜是否允许该种玩法。
     * 
     */
    public boolean validateMatchesJCMethod(String[] bets, LotteryType type)
    {
    	for (int i=0; i<bets.length; i++) {
    		String jcMethod = bets[i].split("\\|")[0];
    		String danduoflag = bets[i].split("\\|")[2];
    		String danduo = "2";
    		if(danduoflag.equals("1*1"))	danduo = "1";
    		String[] matches = bets[i].split("\\|")[1].split(",");
    		for (int j=0; j<matches.length; j++) {
    			String matchNo = matches[j].split("=")[0];
    			MatchArrange matchArrange = matchArrangeService.getMatchMatchByNoAndType(matchNo, type);
    			if(!matchArrange.allowJCLQTypes(jcMethod, danduo))
	        	{
	        		return false; 
	        	}
    		}
    	}
    	return true;
    }
    
    public static void main(String[] args)
    {
        String num = "1,1,1,1,1";
        String[] a = num.split("\\,");
        System.out.println(a.length);
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
