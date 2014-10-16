/**
 * <pre>
 * Title: 		CustomerImportAction.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-10-9 下午09:33:21
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.admin.action.customer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.enumerate.CustomerStatus;
import com.xsc.lottery.entity.enumerate.RegChannel;
import com.xsc.lottery.entity.enumerate.RegSource;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Md5Util;

/**
 * <pre>
 * TODO 用户导入
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-10-9
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.import")
public class CustomerImportAction extends AdminBaseAction
{
	 @Autowired
	 private CustomerService customerService;
	 
	 private File upload;
	 
	 private static final int BUFFER_SIZE = 4096 ;
	 
	/**
	 * <pre>
	 *   用户导入页面
	 * </pre>
	 * @return
	 */
	public String index()
	{
		return SUCCESS;
	}
	
	/**
	 * <pre>
	 *  通过QQ导入用户
	 * </pre>
	 * @return
	 */
	public String importByQQ()
	{
		InputStream stream = null;
		InputStreamReader sr = null;
		BufferedReader br = null;
		try
		{
			stream = new BufferedInputStream(new FileInputStream(upload),BUFFER_SIZE);
			sr = new InputStreamReader(stream, "GBK");
			br = new BufferedReader(sr);
			String result = "";
			String text = br.readLine();
			while (text != null && text != "")
			{
				if (!text.trim().equals(""))
				{
					result = text.trim();
					String nickname = result;
					String email = text+"@qq.com";
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
				text = br.readLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
				sr.close();
				stream.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return index();
	}

	public File getUpload()
	{
		return upload;
	}

	public void setUpload(File upload)
	{
		this.upload = upload;
	}
}
