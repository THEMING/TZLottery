/**
 * <pre>
 * Title: 		PrintLotteryTermService.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-4 下午08:33:14
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business;

import com.xsc.lottery.entity.business.PrintLotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;

/**
 * <pre>
 * TODO 终端奖期服务接口
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-4
 */
public interface PrintLotteryTermService
{
	PrintLotteryTerm getPrintLotteryTerm(SendTicketPlat print,LotteryType type,String termNo);
	
	Integer getPrintLotteryTermCount(SendTicketPlat print,LotteryType type,String termNo);
	
	PrintLotteryTerm saveOrUpdate(PrintLotteryTerm term);
}
