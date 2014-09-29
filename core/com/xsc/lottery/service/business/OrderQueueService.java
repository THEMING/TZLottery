package com.xsc.lottery.service.business;

import java.util.List;

import com.xsc.lottery.entity.business.OrderQueue;
import com.xsc.lottery.service.LotteryBaseService;

public interface OrderQueueService extends LotteryBaseService<OrderQueue>
{
    /**遍历出所有的orderqueue*/
    public List<OrderQueue> getAllOrderQueueListByPlat(int plat);
    
    /**根据order取出相应的orderqueue*/
    public OrderQueue getOrderQueueByOrderId(Long orderId);
    
    /**根据status取出相应的orderqueue*/
    public List<OrderQueue> getOrderQueueByStatus(int status);
    
    /**删除掉所有状态是1的数据*/
    public void deleteAllStatus1(int plat);
}
