package com.xsc.lottery.action.partner;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.partner.PartnerService;
import com.xsc.lottery.web.action.LotteryBaseAction;

@SuppressWarnings("serial")
public class PartnerBaseAction extends LotteryBaseAction
{
    public static final String SESSION_PARTNER_KEY = "partner";
    
    @Autowired
    private PartnerService partnerservice;

    public Partner getCurClient()
    {
        Partner partner = (Partner) getSession().get(SESSION_PARTNER_KEY);
        if (null != partner) {
            partner = partnerservice.findById(partner.getId());
            saveCurClient(partner);
        }
        return partner;
    }

    protected void saveCurClient(Partner partner)
    {
        getSession().put(SESSION_PARTNER_KEY, partner);
    }
}
