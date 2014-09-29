package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.LotteryBaseService;

public interface CommunityService extends LotteryBaseService<Community>
{

    /** 保存合买单并生成发起人的合买份额 */
    public void saveAndCreatePart(Community community,
            List<PlanItem> planItemList);

    /** 认购份额   */
    public Community addPart(Long communityId, Customer customer, int partNum)
            throws Exception;
    
    /** 认购份额   */
    public Community addPart(Long communityId, Customer customer, int partNum, Cookie cookie)
            throws Exception;

    public List<Part> getPartByCommunity(Community community);

    /** 合买生成注单后,参与者跟据比例分钱 */
    public boolean returnMoney(Community community, BigDecimal money,
            WalletLogType walletLogType, String description);

    /** 合买截止 */
    public List<Community> stopTogegerSale(LotteryTerm term);
    public List<Community> stopTogegerSale(MatchArrange match);
    
    /**
     * 根据合买状态及期数ID查询
     * */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Community> getCommunityByTermIdAndCommunityType(
            LotteryTerm term, CommunityType communityType);
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Community> getCommunityByMatchAndCommunityType(
            MatchArrange match, CommunityType communityType);
    /** 分页获得客户合买列表信息 */
    public Page<Community> getCommunityPage(Page<Community> page,
            LotteryType type, CommunityType communityType, Customer customer,
            LotteryTerm term);

    /** 我的合买查询 */
    public Page<Community> getTogetherSale(Page<Community> page,
            LotteryType type, Calendar beginTime, Calendar endTime,
            Customer customer);

    public Page<Community> getCommunityPage(Page<Community> page,
            String fSerch, String fValue, String fSerchTerm,
            String fSerchTerm1, Calendar fStime, Calendar fEtime,
            String fAmount1, String fAmount2, String fOkPart1, String fOkPart2,
            String fSstatu, String fType);

    public List<Community> getCommunityList(String fSerch, String fValue,
            String fSerchTerm, String fSerchTerm1, Calendar fStime,
            Calendar fEtime, String fAmount1, String fAmount2, String fOkPart1,
            String fOkPart2, String fSstatu, String fType);

    public Community getCommunityByPlan(Plan plan);

    /**
     * 根据合买ID和用户得到用户参与合买金额
     */
    public List<Part> getPartList(Community c, Customer cu);

    /** 我所有的合买查询 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<Community> getTogetherSaleAndJoin(Page<Community> page,
            Customer customer, LotteryType type, LotteryTerm term,
            CommunityType communityType, String fqr);
    
    public void backoutCommunity(Community community);

	public void saveAndCreatePart(Community community,
			List<PlanItem> planItemList, Cookie cookie);
}
