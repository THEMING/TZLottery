/**
 * <pre>
 * Title: 		PrintLotteryTermServiceImpl.java
 * Project: 	TZLottery
 * Author:		pengfangliang
 * Create:	 	2014-9-4 下午08:45:29
 * Copyright: 	Copyright (c) 2014
 * Company:		GuangDong Caiso
 * <pre>
 */
package com.xsc.lottery.service.business.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.entity.business.PrintLotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.entity.enumerate.TermStatus;
import com.xsc.lottery.service.business.PrintLotteryTermService;

/**
 * <pre>
 * TODO 终端奖期服务接口实现
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-9-4
 */
@Service("printLotteryTermService")
@Transactional
public class PrintLotteryTermServiceImpl implements PrintLotteryTermService
{
	
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	    public SimpleHibernateTemplate<PrintLotteryTerm, Long> printLotteryTermDao;

	    @Autowired
	    public void setSessionFactory(
	            @Qualifier("sessionFactory") SessionFactory sessionfactory)
	    {
	        this.printLotteryTermDao = new SimpleHibernateTemplate<PrintLotteryTerm, Long>(
	                sessionfactory, PrintLotteryTerm.class);
	    }

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.PrintLotteryTermService#getPrintLotteryTerm(com.xsc.lottery.entity.enumerate.SendTicketPlat, com.xsc.lottery.entity.enumerate.LotteryType, java.lang.String)
	 */
	public PrintLotteryTerm getPrintLotteryTerm(SendTicketPlat print, LotteryType type, String termNo)
	{
		 Criteria criteria = printLotteryTermDao.createCriteria();
		 criteria.add(Restrictions.eq("outPoint", print));
		 criteria.add(Restrictions.eq("type", type));
		 criteria.add(Restrictions.eq("termNo", termNo));
		 criteria.addOrder(Order.desc("termNo"));
		return (PrintLotteryTerm) criteria.uniqueResult();
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.PrintLotteryTermService#getPrintLotteryTermCount(com.xsc.lottery.entity.enumerate.SendTicketPlat, com.xsc.lottery.entity.enumerate.LotteryType, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer getPrintLotteryTermCount(SendTicketPlat print, LotteryType type, String termNo)
	{
		 Criteria criteria = printLotteryTermDao.createCriteria();
		 criteria.setProjection(Projections.projectionList().add(Projections.count("id")));
		 criteria.add(Restrictions.eq("outPoint", print));
		 criteria.add(Restrictions.eq("type", type));
		 criteria.add(Restrictions.eq("termNo", termNo));
		 criteria.addOrder(Order.desc("termNo"));
		 List<Object> list = criteria.list();
		 Integer sumNum= 0;
		 Object obj= list.get(0);
		 if(obj!=null)
		 {
			sumNum=(Integer)obj;
		 }
		return sumNum;
	}

	/* （非 Javadoc）
	 * @see com.xsc.lottery.service.business.PrintLotteryTermService#saveOrUpdate(com.xsc.lottery.entity.business.PrintLotteryTerm)
	 */
	public PrintLotteryTerm saveOrUpdate(PrintLotteryTerm term)
	{
		printLotteryTermDao.save(term);
		return term;
	}
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PrintLotteryTermService service = (PrintLotteryTermService) context.getBean("printLotteryTermService");
		System.out.println("1:"+service.getPrintLotteryTermCount(SendTicketPlat.新冠, LotteryType.广西快3, "20140909001"));
		PrintLotteryTerm temp = new PrintLotteryTerm();
		temp.setOpenPrizeTime(Calendar.getInstance());
		temp.setOutPoint(SendTicketPlat.新冠);
		temp.setStartSaleTime(Calendar.getInstance());
		temp.setStopSaleTime(Calendar.getInstance());
		temp.setTermNo("20140909001");
		temp.setTermStatus(TermStatus.销售中);
		temp.setType(LotteryType.广西快3);
		service.saveOrUpdate(temp);
		PrintLotteryTerm printTerm = service.getPrintLotteryTerm(SendTicketPlat.新冠, LotteryType.广西快3, "20140909001");
		System.out.println("2:"+printTerm.toString());
		System.out.println("3:"+service.getPrintLotteryTermCount(SendTicketPlat.新冠, LotteryType.广西快3, "20140909001"));
	}
	

}
