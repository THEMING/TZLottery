package com.xsc.lottery.service.business;
import java.util.Map;

import org.springside.modules.orm.hibernate.Page;
import com.xsc.lottery.entity.business.AdminSendSomeThingTemplate;
import com.xsc.lottery.service.LotteryBaseService;

public interface AdminSendSomeThingTemplateService extends LotteryBaseService<AdminSendSomeThingTemplate>
{
	public Page<AdminSendSomeThingTemplate> getAdminSendSomeThingTemplatePage(Page<AdminSendSomeThingTemplate> page, Map map);

	public void deleteMany(Map m);
	
}
