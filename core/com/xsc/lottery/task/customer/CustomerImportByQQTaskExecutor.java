package com.xsc.lottery.task.customer;

import java.math.BigDecimal;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xsc.lottery.common.Constants;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.enumerate.CustomerStatus;
import com.xsc.lottery.entity.enumerate.RegChannel;
import com.xsc.lottery.entity.enumerate.RegSource;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.util.FileUtils;
import com.xsc.lottery.util.Md5Util;

/**
 * <pre>
 * TODO 通过QQ导入用户
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-10-10
 */
@Component
public class CustomerImportByQQTaskExecutor implements ApplicationListener
{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SysParamService sysParamService;
	
	public void onApplicationEvent(ApplicationEvent event)
	{
		 CommonScheduledThreadPoolExecutor.getInstance().execute(createCustomerImportByQQTask());
	}
	
	public Runnable createCustomerImportByQQTask()
	{
		return new Runnable()
		{
			public void run()
			{
				while(true) 
				{
					if("0".equals(sysParamService.getSysParamByName(Constants.ISOPEN_CUSTOMER_IMPORT_TASK).getValue()))
					{
						logger.info("开始导入用户...");
						String strs[] = FileUtils.readFile(sysParamService.getSysParamByName(Constants.CUSTOMER_IMPORT_FILENAME).getValue()).split("\n");
						for (String str : strs)
						{
							String nickname = str;
							String email = str+"@qq.com";
							String password = "123456";
							Customer customer = customerService.getUniqueCustomerByProperty("nickName", nickname);
					        if (customer != null) {
					            continue;
					        }
					        customer = customerService.getUniqueCustomerByProperty("email", email);
					        if (customer != null) {
					            continue;
					        }
					        customer = new Customer();
					        customer.setNickName(nickname);
					        customer.setPassword(Md5Util.getMD5ofStr(password));
					        customer.setEmail(email);
					        customer.setUsrType(UserType.本地注册用户);
					        customer.setRegSource(RegSource.导入);
					        customer.setRegChannel(RegChannel.腾讯QQ);
					        customer.setStatus(CustomerStatus.未激活);
					        customer.setRegisterTime(Calendar.getInstance());
					        customer.setLastLoginTime(Calendar.getInstance());
					        Wallet wallet = new Wallet();
					        wallet.setCustomer(customer);
					        wallet.setBalance(new BigDecimal(0));
					        customer.setWallet(wallet);
					        customer = customerService.save(customer);
					        customerService.saveWalletSummary(customer.getWallet());
					       
						}	
						SystemParam sysParam =   sysParamService.getSysParamByName(Constants.ISOPEN_CUSTOMER_IMPORT_TASK);
					    sysParam.setValue("1");
					    sysParamService.update(sysParam);
					    logger.info("导入用户结束...");
					}
					else
					{
						try
						{
							Thread.sleep(10*60*1000);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		};
	}

}
