package com.xsc.lottery.admin.action.active;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.active.ActivityDetail;
import com.xsc.lottery.entity.active.ActivityDetailType;
import com.xsc.lottery.service.active.ActivityService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("activity.activityDetailManager")
public class ActivityDetailAction extends AdminBaseAction
{
    @Autowired
    private ActivityService activityService;
    
    private Page<ActivityDetail> page;

    private Long id;

    private int pageNo = 1;

    private int pageSize = 10;
    
    private ActivityDetailType[] activityDetailTypes = ActivityDetailType.values();

    private ActivityDetailType activityDetailType;
    
    boolean finished = false;

    public String index()
    {
        page = new Page<ActivityDetail>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = activityService.getActivityDetailPage(page, activityDetailType, finished);
        return SUCCESS;
    }
    
    public String ignore()
    {
        if(id != null && id >= 0) {
            ActivityDetail detail = activityService.findActivityDetailById(id);
            detail.setFinished(true);
            activityService.saveActivityDetail(detail);
        }
        return index();
    }
    
    public Page<ActivityDetail> getPage()
    {
        return page;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public ActivityDetailType getActivityDetailType()
    {
        return activityDetailType;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public void setActivityDetailType(ActivityDetailType activityDetailType)
    {
        this.activityDetailType = activityDetailType;
    }

    public ActivityDetailType[] getActivityDetailTypes()
    {
        return activityDetailTypes;
    }
}
