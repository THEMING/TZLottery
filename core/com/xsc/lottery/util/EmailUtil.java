package com.xsc.lottery.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.xsc.lottery.common.ComponentContextLoader;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.entity.business.EmailLog.EmailType;
import com.xsc.lottery.service.business.EmailLogService;

public class EmailUtil
{
	//以HTML的形式发送邮件
    public static void sendEmail(String to, String title, String content)
            throws Exception
    {
        String smtphost = Configuration.getInstance().getValue("mail.smtphost"); // 发送邮件服务器
        String user = Configuration.getInstance().getValue("mail.from"); // 邮件服务器登录用户名
        String password = Configuration.getInstance().getValue("mail.password"); // 邮件服务器登录密码
        
        // 以下为发送程序，用户无需改动
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.smtp.auth", "true");
        Session ssn = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(ssn);

        InternetAddress fromAddress = new InternetAddress(user);
        message.setFrom(fromAddress);
        InternetAddress toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
		message.setSubject(MimeUtility.encodeText(title, "UTF-8", "B"));
		
		Multipart mainPart = new MimeMultipart();
		BodyPart html = new MimeBodyPart();
		html.setContent(content, "text/html; charset=UTF-8");
		mainPart.addBodyPart(html);
		message.setContent(mainPart);
		
		//message.setText(content);

        Transport transport = ssn.getTransport("smtp");
        transport.connect(smtphost, user, password);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public static void main(String[] args)
    {
    	String code = "975560";
    	String sign = "CP22d359#5f56!174Mc_5cde2CS";
    	String baseCode = new String(Base64.encode(code.getBytes()));
    	sign = MD5.digest(code+sign);
    	String title = "鼠标轻轻一抖，彩金免费到手！";
    	String to = "pfl@yicp.com";
    	String content = "尊敬的xxx先生,猛击：http://192.168.0.222:8085/customer/resetpwd.html?code="+baseCode+"&sign="+sign+"";
    	content = "<iframe src='http://www.yicp.com/about/email.html' frameborder='0' height='800px' width='100%' scrolling='auto'></iframe>";
    	EmailLogService emailService = ComponentContextLoader.getBean(EmailLogService.class);
    	EmailLog emailLog = new EmailLog();
    	emailLog.setContent(content);
    	emailLog.setEmail(to);
    	emailLog.setRetryCount(0);
    	emailLog.setSendLevel(5);
    	emailLog.setSendTime(new Date());
    	emailLog.setSendUserName("XXX先生");
    	emailLog.setState(EmailState.NOTSEND);
    	emailLog.setTitle(title);
    	emailLog.setType(EmailType.NORMAL);
    	emailLog.setUsername("OOO小姐");
    	emailService.saveOrUpdate(emailLog);
    	/*
        try {
            sendEmail("gjj@yicp.com", "鼠标轻轻一抖，彩金免费到手！", "尊敬的xxx先生,猛击：http://192.168.0.222:8085/customer/resetpwd.html?code="+baseCode+"&sign="+sign+"");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
