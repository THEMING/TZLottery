package com.xsc.lottery.admin.action.term;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Restricted;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.Restrictedstatus;
import com.xsc.lottery.service.business.RestrictedService;

/** 限制销售action */
@SuppressWarnings( { "serial", "unused" })
@Scope("prototype")
@Controller("Admin.restricted")
public class AdminRestrictedAction extends AdminBaseAction{

	@Autowired
	private RestrictedService restrictedService;
	
    private LotteryType[] types = LotteryType.values();
    
    private LotteryType type = LotteryType.全部;
    
    private Restrictedstatus[] status = Restrictedstatus.values();
    
    private Restrictedstatus statue;
    
    private Calendar startTime;

    private Calendar endTime;
    
    private String desc;
    
    private List<Restricted> list;

    public String index()
    {
    	System.out.println("=====================精+++++++++++++++++");
    	list = restrictedService.getDataRestricteds(type);
        return SUCCESS;
    }
    
    public String update() {

    	Restricted restricted = restrictedService.getRestrictedByType(type);
    	if (statue.toString() != "" && statue != null) {
			restricted.setRestrictedstatus(statue);
		}
    	if (desc != "" && desc != null) {
			restricted.setDesc(desc);
		}
    	if (startTime != null) {
			restricted.setStartTime(startTime);
		}
    	if (endTime != null) {
			restricted.setEndTime(endTime);
		}
    	restrictedService.update(restricted);
		return index();
	}

	public LotteryType[] getTypes() {
		return types;
	}

	public void setTypes(LotteryType[] types) {
		this.types = types;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public Restrictedstatus[] getStatus() {
		return status;
	}

	public void setStatus(Restrictedstatus[] status) {
		this.status = status;
	}

	public Restrictedstatus getStatue() {
		return statue;
	}

	public void setStatue(Restrictedstatus statue) {
		this.statue = statue;
	}


	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Restricted> getList() {
		return list;
	}

	public void setList(List<Restricted> list) {
		this.list = list;
	}
	
}
