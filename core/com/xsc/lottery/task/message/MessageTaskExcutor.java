package com.xsc.lottery.task.message;


import java.io.StringReader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xsc.datasource.JdbcUtilities;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;
import com.xsc.lottery.java.common.SystemWarningNotify;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.NetWorkUtil;

@Component
public class MessageTaskExcutor// implements ApplicationListener
{
	 @Autowired
	private CustomerService customerService;
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected boolean start = false;
    
    protected LinkedBlockingQueue<Customer> notifyCustomerQueue = new LinkedBlockingQueue<Customer>();
   
    protected LinkedBlockingQueue<Order> notifyWinQueue = new LinkedBlockingQueue<Order>();
    
    protected LinkedBlockingQueue<Order> notifyerrorQueue = new LinkedBlockingQueue<Order>();
    
    protected LinkedBlockingQueue<Object[]> notifySMQueue = new LinkedBlockingQueue<Object[]>();
    
    protected String userid=Configuration.getInstance().getValue("message.userid");
    
    protected String account=Configuration.getInstance().getValue("message.account");
    
    protected String password=Configuration.getInstance().getValue("message.password");
    
    protected String url=Configuration.getInstance().getValue("message.url");

    
    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ContextRefreshedEvent && !start) {
            logger.info("一彩票短信服务启动...");
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createNotifyCustomerTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createNotifyWinTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createnotifyerrorTask());
            CommonScheduledThreadPoolExecutor.getInstance().execute(
            		createNotifySMTask());
            start = true;
        }
    }
    
    public void addNotifyCustomer(Customer customer)
    {
    	 notifyCustomerQueue.offer(customer);
    }
    
    public void addWinCustomer(Order order)
    {
    	 notifyWinQueue.offer(order);
    }
    
    public void adderror(Order order) {
		notifyerrorQueue.offer(order);
	}
    
    public void addNotifySM(String mobile, String str) {
    	Object[] obj = new Object[] {mobile, str};
    	notifySMQueue.offer(obj);
	}
    
    private Runnable createnotifyerrorTask() {
    	
    	return new Runnable() {
			
			public void run() {
				
				while(true) {
					try {
						Order order = notifyerrorQueue.take();
						notifyerror(order);
					} catch (InterruptedException e) {
						String description = "短信通知队列异常,请查看日志";
	                    SystemWarningNotify.addWarningDescription(description);
						e.printStackTrace();
					}
				}
			}
		};
    }
    
    private Runnable createNotifyCustomerTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                    	
                    	Customer customer = notifyCustomerQueue.take();
                    	notifyCustomer(customer);
                    }
                    catch (Exception e) {
                        String description = "短信通知队列异常,请查看日志";
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private Runnable createNotifyWinTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                    	Order order = notifyWinQueue.take();
                    	notifyWin(order);
                    }
                    catch (Exception e) {
                        String description = "短信通知队列异常,请查看日志";
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }

    private Runnable createNotifySMTask()
    {
        return new Runnable()
        {
            public void run()
            {
                while (true) {
                    try {
                    	Object[] object = notifySMQueue.take();
                    	notifySM(object);
                    }
                    catch (Exception e) {
                        String description = "短信通知队列异常,请查看日志";
                        SystemWarningNotify.addWarningDescription(description);
                    }
                }
            }
        };
    }
    
    private void notifySM(Object[] objects) {
    	Connection conn = null;
    	try {
			conn = JdbcUtilities.getConnection();
			String sql="insert sm_queue(content,mobile) values('"+objects[1]+"','"+objects[0]+"')";
			JdbcUtilities.saveOrUpdateOrDelete(conn, sql);
		} catch (Exception e) {
			logger.info("Message get exception : " + "短信通知出错");
			e.printStackTrace();
		}finally {
			JdbcUtilities.closeSource(conn, null);
		}
    }
    
    private void notifyerror(Order order) {

    	Connection conn = null;
    	try {
	    	Customer customer = order.getCustomer();
	    	String content = "您好：";
	    	content += "您的购买的" + order.getTerm().getTermNo() + "期" + order.getType() + "，订单号为：" + order.getPlan().getNumberNo() + "的订单，出票失败！";
	    	content += "对此表示抱歉，为了避免您的损失，请重新购彩！";
	    	String mobile = customer.getMobileNo();
			conn = JdbcUtilities.getConnection();
			String sql="insert sm_queue(content,mobile) values('"+content+"','"+mobile+"')";
			JdbcUtilities.saveOrUpdateOrDelete(conn, sql);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Message get exception : " + "短信通知出错");
			e.printStackTrace();
		}finally {
			JdbcUtilities.closeSource(conn, null);
		}
    }
    
    private void notifyWin(Order order){
    	try{
    	   Customer customer=order.getCustomer();
     	   String content ="一彩票恭喜您:";
           content += "您的订单号为" + order.getPlan().getNumberNo() + "的订单" + "中奖了,";
           content += "金额为:"+order.getWinMoney().longValue()+"元";
           String modile=customer.getMobileNo();
           Connection conn=null;
           try {
			    conn= JdbcUtilities.getConnection();
			    String sql="insert sm_queue(content,mobile) values('"+content+"','"+modile+"')";
				JdbcUtilities.saveOrUpdateOrDelete(conn, sql);
			} catch (SQLException e) {
				logger.info("Message get exception : " + "短信通知出错");
				e.printStackTrace();
			}finally{
				JdbcUtilities.closeSource(conn, null);
			}
     	   
    	} catch(Exception e){
    		logger.info("Message get exception : " + "短信通知出错");
    	}       
    }
    
    private void notifyCustomer(Customer customer)
    {  try{
    	   String content ="欢迎使用一彩票，一彩票手机验证码为"+customer.getYanzhenma();
           String s="action=send&userid="+userid+"&account="+account
           +"&password="+password+"&mobile="+customer.getMobileNo()
           +"&content="+URLEncoder.encode(content, "UTF-8")+"&sendTime="
           +""+"&taskName="+"&checkcontent=";
           String xml=NetWorkUtil.getHttpUrlByPostMethod(url, s);
           SAXReader saxReader = new SAXReader();
     	  try {
     		StringReader stringReader=new StringReader(xml);
     		Document document = saxReader.read(stringReader);
     		Iterator iter = document.getRootElement().elementIterator();
     	        while(iter.hasNext()){
     	        	 Element element=(Element)iter.next();
     	        	 logger.info("Message get exception : " + element.getName()+"  "+new String(element.getText().getBytes("GBK"),"utf-8"));  
     	       }  
     	} catch (DocumentException e) {
     		 logger.info("Message get exception : " + "短信通知出错");
     	}
         } catch(Exception e){
        	 logger.info("Message get exception : " + "短信通知出错");
         }       
    }
    
 /*public static void main(String []arg){
	  String content="";
	  try {
		   content = URLEncoder.encode("你好!","UTF-8");
	  } catch (UnsupportedEncodingException e1) {
		e1.printStackTrace();
	  } 
	  String ss="action=send&userid="+"12381"+"&account="+"369cai1"
      +"&password="+"369cai"+"&mobile="+"15101690844"
      +"&content="+content +"&sendTime="+"&taskName="+"&checkcontent=";
      String gg= NetWorkUtil.getHttpUrlByPostMethod("http://116.213.132.8:8888/sms.aspx", ss);
      System.out.println(ss);
      System.out.println(gg);
      SAXReader saxReader = new SAXReader();
	  try {
		StringReader stringReader=new StringReader(gg);
		Document document = saxReader.read(stringReader);
		Iterator iter = document.getRootElement().elementIterator();
	        while(iter.hasNext()){
	        		 Element element=(Element)iter.next();
	               try {
					System.out.println(element.getName()+"  "+new String(element.getText().getBytes("GBK"),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
				}}      
	} catch (DocumentException e) {
		e.printStackTrace();
	}
  }*/
    public static void main(String[] args) {
    	/*try{
     	   //Customer customer=order.getCustomer();
      	   String content ="您好:";
            content += "您的订单号为" + 534534 + "的订单" + "已中";
            content += "金额为:"+2+"元";
            String modile="13718746118";
            Connection conn=null;
           
            System.out.println("=============================content:"+content);
            System.out.println("=============================modile:"+modile);
            try {
 			    conn= JdbcUtilities.getConnection();
 			   System.out.println("==================conn:"+conn);
 				String sql="insert sm_queue(content,modile) values('"+content+"','"+modile+"')";
 				int num=JdbcUtilities.saveOrUpdateOrDelete(conn, sql);
 				if(num>0){
 					System.out.println("=============插入成功！==================");
 				}else{
 					System.out.println("===================插入失败！==============");
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				//logger.info("Message get exception : " + "短信通知出错");
 				e.printStackTrace();
 			}finally{
 				JdbcUtilities.closeSource(conn, null);
 			}
      	   
     	} catch(Exception e){
     		e.printStackTrace();
     		//logger.info("Message get exception : " + "短信通知出错");
     	} */
    	
    	try {
			Connection conn= JdbcUtilities.getConnection();
			System.out.println("===============conn:"+conn);
			String sql="select * from sm_queue";
			
			ResultSet rs=JdbcUtilities.getResultSet(conn, sql);
			System.out.println("===============rs:"+rs);
			while(rs.next()){
				int id=rs.getInt("id");
				String content=rs.getString("content");
				String modile=rs.getString("modile");
				java.util.Date addTime=rs.getDate("add_time"); 
				int status=rs.getInt("status");
				int channel=rs.getInt("channel");
				int retry=rs.getInt("retry");
				java.util.Date sendTime=rs.getDate("send_time");
				System.out.println("===============id:"+id);
				System.out.println("===============content:"+content);
				System.out.println("===============modile:"+modile);
				System.out.println("===============addTime:"+addTime);
				System.out.println("===============status:"+status);
				System.out.println("===============channel:"+channel);
				System.out.println("===============retry:"+retry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
