package com.xsc.lottery.service.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.PayOutRequest;
import com.xsc.lottery.service.LotteryBaseService;

public interface PayOutRequestService extends LotteryBaseService<PayOutRequest>
{
	/**得到符合条件的全部信息记录*/
    public List<PayOutRequest> getRecordsByRestrications(Calendar stime, Calendar etime, String cardNo, String realName, String openSpace, String city, String tradeNo, BigDecimal money, int status, int flag, Calendar order_time);
    
    /**分页得到符合条件的全部信息记录*/
    public List<PayOutRequest> getRecordsByRestrications(Calendar stime, Calendar etime, String cardNo, String realName, String openSpace, String city, String tradeNo, BigDecimal money, int index, int size, int status, int flag, Calendar order_time);
    
    /**根据流水号找到相应的数据详情*/
    public PayOutRequest getRecordByTradeNo(String tradeNo);
    
    /**更新记录的状态*/
    public void updateStatus(String tradeNo, int state, int progressFlag, String memo);
    
    /***/
    public Page<PayOutRequest> getDatapage(Page<PayOutRequest> page, Calendar stime, Calendar etime, String name, String realname, int processFlag);

    public PayOutRequest getPayOutRequestByBMR(BackMoneyRequest backMoneyRequest);
}
