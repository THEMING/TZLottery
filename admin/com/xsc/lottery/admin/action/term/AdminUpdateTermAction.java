package com.xsc.lottery.admin.action.term;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.ChaseItem;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ChaseService;
import com.xsc.lottery.service.business.LotteryTermService;

/** 期次后台管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.updateTerm")
public class AdminUpdateTermAction extends AdminBaseAction
{
    @Autowired
    private LotteryTermService termService;
    
    @Autowired
    private ChaseService chaseService;
    
    private String type;
    
    private String termNo1;
    
    private String termNo2;

    private String message;
    
    private LotteryType[] types = LotteryType.values();

    public String index()
    {
        return SUCCESS;
    }
    
    public String update() {
    	
    	List<ChaseItem> chaseItems = chaseService.getChaseItemByTermNO(termNo1, LotteryType.valueOf(type));
    	
    	if (chaseItems.size() <= 0) {
			message = "没有追号信息！";
		}
    	else {
    		for (ChaseItem chaseItem : chaseItems) {
    			String termNoString = String.valueOf(Integer.valueOf(termNo2) +  Integer.valueOf(chaseItem.getTermNo()) - Integer.valueOf(termNo1));
				chaseItem.setTermNo(termNoString);
				chaseService.saveChaseItem(chaseItem);
			}
    		message = "修改成功，将 " + type + "第" + termNo1 + "期修改为 " + termNo2;
			
		}
    	
		return index();
	}
    
	public LotteryType[] getTypes() {
		return types;
	}

	public void setTypes(LotteryType[] types) {
		this.types = types;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTermNo1() {
		return termNo1;
	}

	public void setTermNo1(String termNo1) {
		this.termNo1 = termNo1;
	}

	public String getTermNo2() {
		return termNo2;
	}

	public void setTermNo2(String termNo2) {
		this.termNo2 = termNo2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
