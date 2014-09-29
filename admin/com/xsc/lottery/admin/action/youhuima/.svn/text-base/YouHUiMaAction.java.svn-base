package com.xsc.lottery.admin.action.youhuima;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.YouHuiMa;
import com.xsc.lottery.entity.business.YouHuiMa.YouHuiMaType;
import com.xsc.lottery.service.active.ActivityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.YouHuiMaService;

@SuppressWarnings({"serial", "unchecked"})
@Scope("prototype")
@Controller("youhuima")
public class YouHUiMaAction extends AdminBaseAction
{
	@Autowired
	private YouHuiMaService youHuiMaService;
	
	@Autowired
	private ActivityService activityService;
	
	private List<Activity> activityList;
	
	@Autowired
    private CustomerService customerService;
    
    private Customer customer;
	
	private  List<YouHuiMa> youhuimalist;
	
	private String type; //优惠码的类型 已激活 未激活 已兑换
	
	private String zxz;  //发放优惠码的最小值
	
	private String zdz; //发放优惠码的最大值
	
	private String ses; //发放是否成功
	
	private String duihuanma; //用户输入的优惠码
	
	private String succ; //兑换是否成功
	
	private String geshu; //生成优惠码的个数
	
	private String qianshu; //生成优惠码的钱数；
	
	private Long activityId;//活动ID
	
	public String index()
    {
		System.out.println("调用index方法");
		activityList = activityService.getCurrentActivities();
		youhuimalist = youHuiMaService.getYouHuiMaAll();
    	return SUCCESS;
    }
	
	/** 优惠码查看*/
	public String list() 
	{
		System.out.println("调用list方法");
		System.out.println("type=" + type + "000000000000000");
		YouHuiMaType yhmtype = null;
		Activity activity = null;
		if (type.equals("wjh")){
			yhmtype = YouHuiMaType.未激活;
		}else if (type.equals("yjh")) {
			yhmtype = YouHuiMaType.已激活;
		}else if (type.equals("ydh")){
			yhmtype = YouHuiMaType.已兑换;
		}
		else if (type.equals("ydh")){
			yhmtype = YouHuiMaType.已过期;
		}
		if(activityId!=null)
		{
			activity = activityService.findById(activityId);
		}
		youhuimalist = youHuiMaService.findYouHuiMa(yhmtype, activity);
		activityList = activityService.getCurrentActivities();
		return SUCCESS;
	}
	
	/** 优惠码发放*/
	public String listFF()
    {
		System.out.println("调用listFF方法");
		youhuimalist = youHuiMaService.getYouHuiMaByFanWei(zxz, zdz);
		if (youhuimalist.size() != 0) {
			for (Iterator iterator = youhuimalist.iterator(); iterator.hasNext();) {
				YouHuiMa youhuima = (YouHuiMa) iterator.next();
				if (youhuima.getType() == YouHuiMaType.未激活) {
					youhuima.setType(YouHuiMaType.已激活);
				}
				youHuiMaService.update(youhuima);
				System.out.println(youhuima.getNumber() + "00000");
				
			}
			ses = "发放成功";
		} else {
			ses = "发放失败，请检查范围是否正确";
		}
    	return SUCCESS;
    }
	
	
	/** 优惠码生成*/
	public String shengcheng()
	{

		System.out.println("调用生成方法");
		System.out.println(geshu);
		System.out.println(qianshu);
		youhuimalist = youHuiMaService.getYouHuiMaAll();
		int yhm;
		if (youhuimalist.size() == 0) {
			yhm = 100000;
		} else {
			YouHuiMa youHuiMa = youhuimalist.get(youhuimalist.size()-1);
			yhm = Integer.valueOf(youHuiMa.getNumber().substring(0, 6));
			System.out.println(yhm);
		}
		for (int i = 0; i <Integer.valueOf(geshu); i++) {
			 Random random = new Random();
			 int x = random.nextInt(999999);
			 while (x<100000) {
			 	x = random.nextInt(999999);
			 }
			 int m = yhm + i + 1;
			 YouHuiMa youhm = new YouHuiMa();
			 youhm.setActivity(activityService.findById(activityId));
			 youhm.setCreateTime(Calendar.getInstance());
			 BigDecimal qian = new BigDecimal(qianshu);
			 youhm.setMoney(qian);
			 youhm.setNumber(m  +"" +  x);
			 System.out.println(m +"" +  x);
			 youhm.setType(YouHuiMaType.未激活);
			 youHuiMaService.save(youhm);
		}
		activityList = activityService.getCurrentActivities();
		succ = "生成成功";
		return SUCCESS;
	}
	
	public List<YouHuiMa> getYouhuimalist() {
		return youhuimalist;
	}

	public void setYouhuimalist(List<YouHuiMa> youhuimalist) {
		this.youhuimalist = youhuimalist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZxz() {
		return zxz;
	}

	public void setZxz(String zxz) {
		this.zxz = zxz;
	}

	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	public String getSes() {
		return ses;
	}

	public void setSes(String ses) {
		this.ses = ses;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDuihuanma() {
		return duihuanma;
	}

	public void setDuihuanma(String duihuanma) {
		this.duihuanma = duihuanma;
	}

	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public String getGeshu() {
		return geshu;
	}

	public void setGeshu(String geshu) {
		this.geshu = geshu;
	}

	public String getQianshu() {
		return qianshu;
	}

	public void setQianshu(String qianshu) {
		this.qianshu = qianshu;
	}
	
	 public List<Activity> getActivityList() {
			return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
}
