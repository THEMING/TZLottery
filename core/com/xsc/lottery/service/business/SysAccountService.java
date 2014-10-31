/**
 * <pre>
 * Title: 		SysAccountService.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-26 下午05:19:53
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business;

import com.xsc.lottery.entity.business.SysAccount;
import com.xsc.lottery.entity.business.SysAccountLog;
import com.xsc.lottery.service.LotteryBaseService;

/**
 * <pre>
 * TODO 系统账户服务接口
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-26
 */
public interface SysAccountService extends LotteryBaseService<SysAccount>
{
	public void addSysAccountLog(Long id, SysAccountLog sysAccountLog);
}
