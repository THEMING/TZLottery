package com.xsc.lottery.admin.action.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.service.business.AdminMobileService;
import com.xsc.lottery.task.message.MessageTaskExcutor;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("admin.mobileaction")
public class AdminMobileAction extends AdminBaseAction{

    private String userid;

    private String mobile;

    private String name;

    private  String active;
    
    private String content;

    private String email;

    @Autowired
    private AdminMobileService adminMobileService;
    
    private AdminMobile adminMobile;
    
    private List<AdminMobile> list;
    
    @Autowired
    private MessageTaskExcutor messageTaskExcutor;
    
    public String index() {
    	
        list = adminMobileService.getAllAdminMobile();
		return SUCCESS;
	}
    
    public String addjsp() {
    	return  "input";
    }
    
    public String editjsp() {
    	adminMobile = adminMobileService.findById(Long.valueOf(userid));
		return "list";
	}
    
    public String delete() {
    	adminMobile = adminMobileService.findById(Long.valueOf(userid));
    	adminMobileService.delete(adminMobile);
    	return  index();
    }
    
    public String deleteselects() {
    	return  index();
    }
    
    public String add() {
    	AdminMobile amobile = new AdminMobile();
    	if (active != null && active != "") {
    		amobile.setActive(Integer.valueOf(active));
		}
    	if (content != null && content != "") {
    		amobile.setContent(content);
		}
    	if (email != null && email != "") {
    		amobile.setEmail(email);
		}
    	if (mobile != null && mobile != "") {
    		amobile.setMobile(mobile);
		}
    	if (name != null && name != "") {
    		amobile.setName(name);
		}
    	if (active != "" || content != "" || email != "" || mobile != "" || name != "") {
    		adminMobileService.save(amobile);
		}
    	return  index();
    }
    
    public String edit() {
    	adminMobile = adminMobileService.findById(Long.valueOf(userid));
    	adminMobile.setActive(Integer.valueOf(active));
    	adminMobile.setContent(content);
    	adminMobile.setEmail(email);
    	adminMobile.setMobile(mobile);
    	adminMobile.setName(name);
    	adminMobileService.update(adminMobile);
    	return  index();
    }

	public AdminMobile getAdminMobile() {
		return adminMobile;
	}

	public void setAdminMobile(AdminMobile adminMobile) {
		this.adminMobile = adminMobile;
	}

	public List<AdminMobile> getList() {
		return list;
	}

	public void setList(List<AdminMobile> list) {
		this.list = list;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
    
	public static void main(String[] args) {
		Object[] objects = new Object[] {1, 2};

		System.out.println(objects.length);
//		for (int i = 0; i < objects.length - 1; i++) {
//			System.out.println(objects[i]);
//		}
	}
    
}
