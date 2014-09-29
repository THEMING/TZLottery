/**
 * 
 */
package com.xsc.lottery.web.action.football;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.json.JsonMsgBean;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("fb.football")
public class FootballAction extends LotteryClientBaseAction
{
    @Autowired
    private MatchArrangeService mArrangeService;

    @Autowired
    private LotteryTermService lotteryTermService;

    private String type;

    private String termNo;

    public String ajaxGetVs()
    {
        LotteryTerm term = new LotteryTerm();
        if (termNo != null) {
            term = lotteryTermService.getByTypeAndTermNo(termNo, LotteryType
                    .enToType(type));
        }
        else {
            term = lotteryTermService
                    .getCurrentTerm(LotteryType.enToType(type));
        }
        List<MatchArrange> list = mArrangeService.getMatchArrangeByTerm(term);
        setJsonString(JsonMsgBean.ListToJsonString(list));
        return AJAXJSON;
    }

    public String ajaxGetTerm()
    {
        return AJAXJSON;
    }

    public void setmArrangeService(MatchArrangeService mArrangeService)
    {
        this.mArrangeService = mArrangeService;
    }

    public void setLotteryTermService(LotteryTermService lotteryTermService)
    {
        this.lotteryTermService = lotteryTermService;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setTermNo(String termNo)
    {
        this.termNo = termNo;
    }

}
