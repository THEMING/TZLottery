package com.xsc.lottery.web.action.customer;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.RechargeGift;
import com.xsc.lottery.entity.enumerate.SoftwareType;
import com.xsc.lottery.service.business.SoftwareRegisterService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.softwareRegister")
public class SoftwareRegisterAction extends LotteryClientBaseAction
{
	@Autowired
	private SoftwareRegisterService softwareRegisterService;
    
    private List<RechargeGift> rechargeGiftTab;
    private int remainingGiftNum;
    private Long id;
    private String machineNo;
    private SoftwareType[] softTypes = SoftwareType.values();
    private SoftwareType softType;

	public String index()
    {
    	Customer customer = this.getCurCustomer();
    	
    	if(customer == null) {
    		return "login";
    	}
    	
    	rechargeGiftTab = softwareRegisterService.getInfoTab(customer);
    	remainingGiftNum= softwareRegisterService.GetRemainGiftNum(customer);
        return SUCCESS;
    }
    
    public String getRegNo()
    {
    	try {
	    	RechargeGift gift = softwareRegisterService.findById(id);
	    	
	    	if(gift.isReceive()) {
	    		//setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
	            //        AjaxResultStatusType._0001, "该记录已经领取"));
	            return SUCCESS;
	    	}
	    	gift.setReceive(true);
	    	gift.setMachineNo(machineNo);
	    	gift.setSoftType(softType);
	    	gift.setRegisterTime(Calendar.getInstance());
	    	
	    	String registerNo = this.calculateRegNo(softType, machineNo);
	    	
	    	if(registerNo == null) {
	    		return index();
	    	}
	    	
	    	gift.setRegisterNo(registerNo);
	    	softwareRegisterService.save(gift);
    	}
    	catch(Exception e) {
    		//setJsonString(JsonMsgBean.getResultStatusJsonStrByTypeAndMsg(
            //        AjaxResultStatusType._0001, "领取异常"));
            return SUCCESS;
    	}
    	
    	index();
    	//setJsonString(JsonMsgBean.getResultStatusJsonStrByType(AjaxResultStatusType._0000));
    	return SUCCESS;
    }
	public List<RechargeGift> getRechargeGiftTab() {
		return rechargeGiftTab;
	}

	public int getRemainingGiftNum() {
		return remainingGiftNum;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineNo() {
		return machineNo;
	}
	
	public String calculateRegNo(SoftwareType softType, String machineNo) 
	{
		int machine = Integer.parseInt(machineNo);
		int mk,pp;
		
		if(machineNo.charAt(0) == '0') {
			machine = -machine;
		}
		
		if(softType.equals(SoftwareType.双色球缩水过滤)) {
			mk=machine+369;
			pp=(mk%9898)*(mk%369369)*(mk%369369)+(mk%3721)-100;
		}
		else if(softType.equals(SoftwareType.福彩3D排列3快选优化)) {
			mk=machine+369;
			pp=(mk%9897)*(mk%369368)*(mk%369368)+(mk%3721)-100;
		}
		else if(softType.equals(SoftwareType.足彩缩水胆拖)) {
			mk=machine+369;
			pp=(mk%9891)*(mk%369361)*(mk%369369)+(mk%3721)-100;
		}
		else if(softType.equals(SoftwareType.足彩叠加过滤)) {
			mk=machine+369;
			pp=(mk%9891)*(mk%361361)*(mk%361368)+(mk%3721)-100;
		}
		else {
			return null;
		}
		
		if(pp < 0) {
			pp = -pp;
			return "0"+String.valueOf(pp);
		}
		else {
			return String.valueOf(pp);
		}
	}

	public SoftwareType getSoftType() {
		return softType;
	}

	public void setSoftType(SoftwareType softType) {
		this.softType = softType;
	}

	public SoftwareType[] getSoftTypes() {
		return softTypes;
	}
}
