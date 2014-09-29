package com.xsc.lottery.service.partner;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.LotteryBaseService;

public interface PartnerService extends LotteryBaseService<Partner>
{
    /** 根据唯一属性得到客户对象 */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    abstract public Partner getUniquePartnerByProperty(String name, Object value);
    
    abstract public Page<Partner> getPartnerPage(Page<Partner> page);
}
