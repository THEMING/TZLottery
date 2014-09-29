package com.xsc.lottery.util;

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
        try {
            sendEmail("pfl@yicp.com", "一彩票网客服中心", "QQ快登用户，您好！恭喜您在一彩票网购买的双色球中得6000元奖金，继续购买请猛点http://www.yicp.com。");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
