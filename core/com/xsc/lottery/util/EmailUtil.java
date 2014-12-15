package com.xsc.lottery.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailUtil
{
	static String smtphost = Configuration.getInstance().getValue("mail.smtphost"); // 发送邮件服务器
	static String user = Configuration.getInstance().getValue("mail.from"); // 邮件服务器登录用户名
	static String password = Configuration.getInstance().getValue("mail.password"); // 邮件服务器登录密码
	//以HTML的形式发送邮件
    public static void sendEmail(String to, String title, String content)
            throws Exception
    {
        
    	// 创建信件服务器  
        Properties props = new Properties();
        props.put("mail.smtp.host", smtphost);
        props.put("mail.smtp.auth", "true");
        
        
        Session ssn = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(ssn);
        
        //设置自定义发件人昵称  
        String nickName="";  
        nickName=javax.mail.internet.MimeUtility.encodeText("一彩票网");  

        InternetAddress fromAddress = new InternetAddress(user,nickName);
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
    public static void sendEmail() throws Exception
    {

        String to="306081148@qq.com";  
        String subject="test";  
        String body = "333";  
        Properties props = System.getProperties();  
        // 创建信件服务器  
        props.put("mail.smtp.host",smtphost);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.transport.protocol", "smtp");  
        // 得到默认的对话对象  
        Authenticator a = new Authenticator() {  
            public PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(user, password);  
            }  
        };  
        //创建Session实例  
        Session session = Session.getDefaultInstance(props, a);  
        //创建MimeMessage实例对象  
        MimeMessage msg=new MimeMessage(session);  
        //设置发信人  
//      msg.setFrom(new InternetAddress(from));  
        //设置自定义发件人昵称  
        String nick="";  
        try {  
            nick=javax.mail.internet.MimeUtility.encodeText("一彩票网");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }   
        msg.setFrom(new InternetAddress (user,nick));  
        //设置收信人  
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  
        //设置发送日期  
        msg.setSentDate(new Date());  
        //设置邮件主题  
        msg.setSubject(subject);  
        //设置邮件正文  
        msg.setText(body);  
        Transport.send(msg);  
    }

    public static void main(String[] args) throws Exception
    {
    	sendEmail("306081148@qq.com","hi","hihi");
    	
//    	String code = "3782";
//    	String sign = "CP22d359#5f56!174Mc_5cde2CS";
//    	String baseCode = new String(Base64.encode(code.getBytes()));
//    	sign = MD5.digest(code+sign);
//    	System.out.println(baseCode+"=="+sign);
//    	
//    	return;
//    	try
//		{
////    		EmailUtil.sendEmail();
//    		
//    		
////    		EmailUtil.sendEmail("gjm@yicp.com", "hi","test<iframe src=\"http://localhost:8080/oss/crmSystem/CRMManageAdmin.htm?action=someEmailIsOpened&emailId=52\" style=\"width:1200px; height:800px\"></iframe>" );
////			String mes = TemplateUtil.makeSMSTemplateContent("","","", "test4.ftl");
////			System.out.println(mes);http://192.168.0.168/actinfo/news_1089.html
//    		Map m = new HashMap();
//    		m.put("withdrawid", 111);
//    		m.put("sign", MD5.digest(111+""+TemplateUtil.privateKey));
//    		
////    		String aa = HttpClientUtil.getContentFromUrl(TemplateUtil.BACK_MONEY_REQUEST_CONTENT_URL ,m);
////			EmailUtil.sendEmail("306081148@qq.com", "hi", aa);
//			System.out.println(11);
////			Order o = new Order();
////			o.setNumberNo("1111123");
////			Customer c = new Customer();
////			c.setRealName("mingming");
////			o.setCustomer(c);109862466@qq.com
////			String mes = TemplateUtil.getPaySuccessEmailContent(o);
////			System.out.println(mes);
////			EmailUtil.sendEmail("13802528513@163.com", "您的购彩订单11234455出票成功", mes);
//			
//			
//			
////			Order o = new Order();
////			o.setStatus(OrderStatus.出票成功);
////			Customer c = new Customer();
////			c.setId(new Long(1603));
////			c.setRealName("ming");
////			o.setCustomer(c);
////			o.setId(new Long(2010));
////			String mes = TemplateUtil.getOrderDetailEmailContent(o);
////			System.out.println(mes);
//		}
//		catch (Exception e)
//		{734495655ab806449420e34685027747
//			e.printStackTrace();
//		}
//    	String code = "975560";
//    	String sign = "CP22d359#5f56!174Mc_5cde2CS";
//    	String baseCode = new String(Base64.encode(code.getBytes()));
//    	sign = MD5.digest(code+sign);
//    	String title = "鼠标轻轻一抖，彩金免费到手！";
//    	String to = "pfl@yicp.com";
//    	String content = "尊敬的xxx先生,猛击：http://192.168.0.222:8085/customer/resetpwd.html?code="+baseCode+"&sign="+sign+"";
//    	content = "<iframe src='http://www.yicp.com/about/email.html' frameborder='0' height='800px' width='100%' scrolling='auto'></iframe>";
//    	EmailLogService emailService = ComponentContextLoader.getBean(EmailLogService.class);
//    	EmailLog emailLog = new EmailLog();
//    	emailLog.setContent(content);
//    	emailLog.setEmail(to);
//    	emailLog.setRetryCount(0);
//    	emailLog.setSendLevel(5);
//    	emailLog.setSendTime(new Date());
//    	emailLog.setSendUserName("XXX先生");
//    	emailLog.setState(EmailState.NOTSEND);
//    	emailLog.setTitle(title);
//    	emailLog.setType(EmailType.NORMAL);
//    	emailLog.setUsername("OOO小姐");
//    	emailService.saveOrUpdate(emailLog);
    	
//    	System.out.println(FileUtils.readFile("d:/activate_for_customer2.html"));
//    	sendEmail("109862466@qq.com", "鼠标轻轻一抖，彩金免费到手！", FileUtils.readFile("d:/activate_for_customer2.html"));
    	
    	//sendEmail();
    	
    	/*
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
    	*/
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
