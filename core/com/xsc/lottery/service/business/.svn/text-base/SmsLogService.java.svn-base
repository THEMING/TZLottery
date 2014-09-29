package com.xsc.lottery.service.business;

import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.business.SmsLog.SmsLogType;

/**
 * TODO 短信日志服务接口
 * @author pengfangliang
 */
public interface SmsLogService
{

	/**
	 * 获取服务器上的上行短信信息
	 */
	public void smsMoFromServer();

	/**
	 * 短信优先通道接口(立即发送，不经过定时器扫描，优先级最高)
	 * @param sms	短信
	 * @param type	0 发新信息 1重发信息
	 * @return
	 */
	public int smsImmediatelySend(SmsLog sms, int type);

	/**
	 * 激活短信通道
	 * @return
	 */
	public boolean smsRegistEx();

	/**
	 * 短信通道企业信息注册
	 * @return
	 */
	public boolean smsRegistDetail();

	/**
	 * 注销短信通道
	 * @return
	 */
	public boolean smsLogout();

	/**
	 * 获取短信通道剩余金额
	 * @return
	 */
	public double smsBalance();

	/**
	 * 提供外部调用接口接收短信来源
	 * @param digest
	 * @param isfrist
	 * @param mobile
	 * @param content
	 * @param priority
	 * @param sendstate
	 * @param smsType
	 * @param scheduledTime
	 * @param userId
	 * @return
	 */
	public int receiveSms(String digest, String isfrist, String mobile, String content, String priority,
			String sendstate, String smsType, String scheduledTime, String userId);

	/**
	 * 修改短信通道密码
	 * @return
	 */
	public boolean smsPwdUpdate();
	
	/**
	 * @param state
	 * @return
	 */
	public List<SmsLog>  findSmsLogByState(SmsLogState state);
	
	/**
	 *  修改短信日志
	 * @param smsLog
	 */
	public SmsLog updateSmsLog(SmsLog smsLog);
	
	/**
	 * 保存短信日志
	 * @param mobile
	 * @param content
	 * @param customerId
	 * @param logType
	 * @return
	 */
	public SmsLog saveSmsLog(String mobile,String content,Long customerId,SmsLogType logType); 
	
	/**
	 * 获取短信日志记录
	 * @param page
	 * @param mobile
	 * @param state 
	 * @return
	 */
	public Page<SmsLog> getSmsLogPage(Page<SmsLog> page,String mobile, SmsLogState state,Long id);
	
	/**
	 * 根据id获取短信日志记录
	 * @param id 
	 * @return
	 */
	public SmsLog getSmsLogById(Long id);

}
