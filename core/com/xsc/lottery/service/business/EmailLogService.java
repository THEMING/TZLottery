/**
 * <pre>
 * Title: 		MailLogService.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-17 下午05:09:55
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business;

import java.util.List;
import java.util.Map;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailType;
import com.xsc.lottery.service.LotteryBaseService;

/**
 * <pre>
 * TODO 邮件日志服务接口
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-17
 */
public interface EmailLogService extends LotteryBaseService<EmailLog>{
	/**
	 * <pre>
	 * 获取没有发送的邮件对象(按级别进行排序)
	 * </pre>
	 * @param count 一次性抓取数量
	 * @return
	 */
	public List<EmailLog> findEmailForNotSend(int count);
	
	/**
	 * <pre>
	 * 重置所有邮件（正在发送中状态改为未发送）
	 * </pre>
	 * @return
	 */
	public int resetAllEmailForSending();
	
	
	/**
	 * <pre>
	 *  发送邮件(此方法只向邮件表里插入一条数据)
	 *  没有具体的业务，只是进行邮件发送
	 * </pre>
	 * @param type 类型
	 * @param sendLevel 级别(值越大越优先)
	 * @param storeId 接收者的会员店ID
	 * @param toEmail 接收者
	 * @param title 标题
	 * @param content 内容
	 * @return
	 */
	public boolean sendEmail(EmailType type, int sendLevel, String storeId, String toEmail, String title, String content);
	
	public Page<EmailLog> findEmailByMap(Page<EmailLog> page,Map map);
	
	/**
	 * <pre>
	 *  保存邮件日志
	 * </pre>
	 * @param emailLog
	 * @return
	 */
	public EmailLog saveOrUpdate(EmailLog emailLog);
}
