package com.xsc.lottery.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.aboutIndexAct")
public class AboutIndexAction extends LotteryClientBaseAction
{
	public String index()
    {
        return SUCCESS;
    }
}
