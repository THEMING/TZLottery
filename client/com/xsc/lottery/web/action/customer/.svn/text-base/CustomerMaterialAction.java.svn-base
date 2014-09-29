package com.xsc.lottery.web.action.customer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.CredentType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Md5Util;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerMaterial")
public class CustomerMaterialAction extends LotteryClientBaseAction
{

    @Autowired
    private CustomerService customerService;
    
    private Customer customer;
    private String comm;
    private String oldpassword;
    private String password;
    private String repassword;
    private Bank[] bank = Bank.values();
    private String banks;
    private String bankName;
    private String email;
    private String mobileNo;
    private String province;
    private String subbranch;
    private String city;
    private String bankNumber;
    
    private String realName;
    private String credentNo;
   
    private String message1="";
    private String message2="";
    
    private int tabIndex;
    private String yzm;
    private String question;
    
    public String index()
    {
    	tabIndex = 1;
        customer = this.getCurCustomer();
        //return comm;
        return SUCCESS;
    }

    public String editMaterial()
    {
        customer = this.getCurCustomer();
        return null;
    }

    @SuppressWarnings("static-access")
    public String mmxg()
    {
    	tabIndex = 2;
    	message2 = "";
        Customer customer = this.getCurCustomer();
        Md5Util encoder = new Md5Util();

        if(encoder.getMD5ofStr(oldpassword).equals(customer.getPassword())) {
            if(password.equals(repassword)) {
                if (StringUtils.isEmpty(password)
                        || StringUtils.isEmpty(repassword)) {
                	message2 = "输入新密码错误!";
                }
                else {
                    customer.setPassword(encoder.getMD5ofStr(password));
                    customerService.update(customer);
                    message2 = "修改成功!";
                }
            }
            else {
            	message2 = "新密码不一致!";
            }
        } 
        else { 	
        	message2 = "原密码错误!";
        }
        return "mmxg";
    }

    @SuppressWarnings("static-access")
    public String zlxg() //资料修改
    {
    	tabIndex = 1;
    	message1 = "";
        Customer custm = this.getCurCustomer();
        Md5Util encoder = new Md5Util();
        boolean bool = true;
        if (StringUtils.isEmpty(password)) {
        	message1 += "密码不能为空!";
            //addActionMessage("密码不能为空!");
            bool = false;
        }
        if (!encoder.getMD5ofStr(password).equals(custm.getPassword())) {
        	message1 += "密码错误!";
            //addActionMessage("密码错误!");
            bool = false;
        }
        
        if (custm.getRealName() == null) {
	        if (bool && StringUtils.isEmpty(realName)) {
	        	message1 += "真实姓名错误!";
	            bool = false;
	        }
	        if (bool && StringUtils.isEmpty(credentNo)) {
	        	message1 += "身份证错误!";
	        	
	            bool = false;
	        }
	        
	        if(bool) {
	        	custm.setRealName(realName);
	        	custm.setCredentType(CredentType.IDCard);
        		custm.setCredentNo(credentNo);
	        }
        }
        
        if (bool && StringUtils.isEmpty(email)) {
        	message1 += "邮箱格式错误!";
            //addActionMessage("邮箱格式错误!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(mobileNo)) {
        	message1 += "手机号格式错误!";
            //addActionMessage("手机号格式错误!");
            bool = false;
        }
        if (bool) {
        	custm.setEmail(email);
            custm.setMobileNo(mobileNo);
            customerService.update(custm);
            message1 += "修改成功!";
        }
        return "zlxg";
    }
     public String sjyz(){
		 tabIndex = 1;
    	 customer=getCurCustomer();
    	 Customer mobileCustomer = customerService.getCustomerByMobileNo(mobileNo);
    	 if(mobileCustomer != null)
    	 {
    		 if(mobileCustomer.getNickName() != customer.getNickName() && mobileCustomer.getNickName() != null)
        	 {
        		 message1 += "对不起，此号码已经被绑定！";
        		 return "zlxg";
        	 }
    	 }
    	 if (StringUtils.isEmpty(mobileNo)) {
    		 message1 += "手机号格式错误!";
           return "zlxg";
         }
    	 if((customer.getBound()== null)||(!customer.getBound().equals("bound")))
    	 {
    		 customer.setMobileNo(mobileNo);
    		 customer= customerService.boundPhone(customer) ; 
    	 }
	   return "sjyz";
    }
     public String sjbd(){
    	 customer=getCurCustomer();
    	 boolean bool = true;
    	 if (StringUtils.isEmpty(yzm)||!yzm.equals(customer.getYanzhenma())) {
           	 message2 += "验证码错误!";
            	bool=false;
           }
    	 if(bool)
    	 {
    		 customer=customerService.boundPhone1(customer);
    		 message2 += "绑定成功!";	
    	 }
	   return "sjbd";
    }
    public String bdyhk()
    {
        Customer custm = this.getCurCustomer();
        boolean bool = true;
        if (bool && StringUtils.isEmpty(province) && StringUtils.isEmpty(city)) {
            addActionMessage("地区不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(banks)) {
            addActionMessage("开户银行不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(subbranch)) {
            addActionMessage("开户银行支行不能为空!");
            bool = false;
        }
        if (bool && StringUtils.isEmpty(bankNumber)) {
            addActionMessage("银行卡号错误!");
            bool = false;
        }
        if (StringUtils.isEmpty(bankName)) {
            addActionMessage("开户名不能为空!");
            bool = false;
        }
        if (!bankName.equals(custm.getRealName())) {
            addActionMessage("开户名与真实姓名不一至!");
            bool = false;
        }

        if (bool) {
            custm.setCity(city);
            custm.setProvince(province);
            custm.setBank(Bank.valueOf(banks));
            custm.setSubbranch(subbranch);
            custm.setBankNumber(bankNumber);
            custm.setBankName(bankName);
            customerService.update(custm);
            addActionMessage("修改成功!");
        }
        return "bdyhk";
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getComm()
    {
        return comm;
    }

    public void setComm(String comm)
    {
        this.comm = comm;
    }

    public void setOldpassword(String oldpassword)
    {
        this.oldpassword = oldpassword;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRepassword(String repassword)
    {
        this.repassword = repassword;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public Bank[] getBank()
    {
        return bank;
    }

    public String getBanks()
    {
        return banks;
    }

    public void setBanks(String banks)
    {
        this.banks = banks;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setBankNumber(String bankNumber)
    {
        this.bankNumber = bankNumber;
    }

    public void setSubbranch(String subbranch)
    {
        this.subbranch = subbranch;
    }

    public String getProvince()
    {
        return province;
    }

    public String getSubbranch()
    {
        return subbranch;
    }

    public String getCity()
    {
        return city;
    }

    public String getBankNumber()
    {
        return bankNumber;
    }

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getMessage1() {
		return message1;
	}

	public String getMessage2() {
		return message2;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCredentNo() {
		return credentNo;
	}

	public void setCredentNo(String credentNo) {
		this.credentNo = credentNo;
	}
	 public String getYzm() {
			return yzm;
		}

		public void setYzm(String yzm) {
			this.yzm = yzm;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}
}
