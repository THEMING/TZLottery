package com.xsc.lottery.service.active;

import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.active.ActivityDetail;
import com.xsc.lottery.entity.active.ActivityDetailType;
import com.xsc.lottery.entity.active.ActivityStatus;
import com.xsc.lottery.entity.active.ActivityType;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.LotteryBaseService;

public interface ActivityService extends LotteryBaseService<Activity>
{
    /** 分页获得活动列表 */
    public Page<Activity> getActivityPage(Page<Activity> page,
            ActivityType activeType, ActivityStatus status, LotteryType lotteryType);
    
    /** 分页获得活动明细列表 */
    public Page<ActivityDetail> getActivityDetailPage(Page<ActivityDetail> page, 
            ActivityDetailType type, boolean finished);
    
    public ActivityDetail findActivityDetailById(Long id);
    
    public ActivityDetail saveActivityDetail(ActivityDetail entity);
    
    public List<Activity> getCurrentActivities();
    
    public boolean isFirstOrder(Order order);
    
    public List<ActivityDetail> getActivityDetails(ActivityDetailType type, boolean checked);
    
    /**
     * <pre>
     *  通过活动类型查询活动
     * </pre>
     * @param type 活动类型
     * @return
     */
    public Activity getActivityByType(ActivityType type);
}
