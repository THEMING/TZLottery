package com.xsc.lottery.service.business.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.CpsDayReport;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.SmsLog;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.WalletService;
import com.xsc.lottery.util.DateUtil;

@Transactional
@Service("cpsReportServiceImpl")
public class CpsReportServiceImpl implements CpsReportService
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	public SimpleHibernateTemplate<CpsDayReport, Long> cpsReportDao;

	private PagerHibernateTemplate<CpsDayReport, Long> cpsReportDaoo;

	public SimpleHibernateTemplate<SystemParam, Long> systemParamDao;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private LotteryOrderService lotteryOrderService;

	@Autowired
	private WalletService walletLogService;

	@Autowired
	public void setSessionFactory(@Qualifier("sessionFactory")
	SessionFactory sessionfactory)
	{
		this.cpsReportDao = new SimpleHibernateTemplate<CpsDayReport, Long>(sessionfactory, CpsDayReport.class);
		this.cpsReportDaoo = new PagerHibernateTemplate<CpsDayReport, Long>(sessionfactory, CpsDayReport.class);
		this.systemParamDao = new SimpleHibernateTemplate<SystemParam, Long>(sessionfactory, SystemParam.class);
	}

	public void delete(CpsDayReport entity)
	{

	}

	public CpsDayReport findById(Long id)
	{
		return null;
	}

	public CpsDayReport load(Long id)
	{
		return null;
	}

	public CpsDayReport save(CpsDayReport entity)
	{
		cpsReportDao.save(entity);
		return entity;
	}

	public CpsDayReport update(CpsDayReport entity)
	{
		cpsReportDao.save(entity);
		return entity;
	}

	/** 获取提成情况 */
	public Map getCommissionPage(Page<CpsDayReport> page, String stime, String etime, Customer customer)
	{
		Map m = new HashMap();

		List<Criterion> list = new ArrayList<Criterion>();

		list.add(Restrictions.eq("customer", customer));
		if (stime != null && !"".equals(stime))
		{
			list.add(Restrictions.ge("reportDate", stime));
		}
		if (etime != null && !"".equals(etime))
		{
			list.add(Restrictions.le("reportDate", etime));
		}
		Criterion[] c = new Criterion[list.size()];
		list.toArray(c);

		page = cpsReportDaoo.findByCriteria(page, c);
		m.put("page", page);

		Criteria criteria = cpsReportDaoo.createCriteria();
		Criterion[] c2 = new Criterion[list.size()];
		list.toArray(c2);
		for (Criterion cc : c2)
		{
			criteria.add(cc);
		}

		int registerNum = 0;
		int rechargeNum = 0;
		BigDecimal rechargeMon = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		BigDecimal commission = new BigDecimal(0);

		Object o = criteria.setProjection(Projections.sum("registerNum")).uniqueResult();
		if (o != null)
		{
			registerNum = Integer.parseInt(o.toString());
		}
		o = criteria.setProjection(Projections.sum("rechargeNum")).uniqueResult();
		if (o != null)
		{
			rechargeNum = Integer.parseInt(o.toString());
		}
		o = criteria.setProjection(Projections.sum("rechargeMon")).uniqueResult();
		if (o != null)
		{
			rechargeMon = (BigDecimal) o;
		}
		o = criteria.setProjection(Projections.sum("total")).uniqueResult();
		if (o != null)
		{
			total = (BigDecimal) o;
		}
		o = criteria.setProjection(Projections.sum("commission")).uniqueResult();
		if (o != null)
		{
			commission = (BigDecimal) o;
		}

		m.put("registerNum", registerNum);
		m.put("rechargeNum", rechargeNum);
		m.put("rechargeMon", rechargeMon);
		m.put("total", total);
		m.put("commission", commission);

		return m;
	}

	/**
	 * 统计当前每个推广人员的销量以及佣金
	 * @throws ParseException
	 */
	public void doCpsDayReport() throws ParseException
	{
		// 从系统参数表中查询出上次统计的日期
		SystemParam systemParam = systemParamDao.get(1l);
		String lastDateStr = systemParam.getValue();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curDate = Calendar.getInstance();
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(format.parse(lastDateStr));
		int interval = DateUtil.interval(curDate, lastDate);
		
		for (int i = interval; i > 0; i--)
		{
			Calendar curReportDate = Calendar.getInstance();
			curReportDate.add(Calendar.DATE, -i);
			List<Customer> cpsCustomerList = customerService.getCpsCustomerList();
			for (Customer customer : cpsCustomerList)
			{
				// 判断是否已有记录
				if (getCpsDayReportByDateAndCustomerId(format.format(curReportDate.getTime()), customer) == null)
				{
					Calendar startTime = Calendar.getInstance();
					Calendar overTime = Calendar.getInstance();
					startTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH),
							curReportDate.get(Calendar.DATE), 0, 0, 0);
					overTime.set(curReportDate.get(Calendar.YEAR), curReportDate.get(Calendar.MONTH),
							curReportDate.get(Calendar.DATE), 23, 59, 59);
					// 总消费额
					BigDecimal total = lotteryOrderService.getSumMoneyByCustomer(startTime, overTime, customer);
					// 佣金
					BigDecimal commission = total.multiply(customer.getSuperRatio());

					// 总注册数
					Long regNum = customerService.getRecommendorsPage2(null, customer, startTime, overTime);

					// 消费人数
					Long payNum = lotteryOrderService.getSumPayByCustomer(startTime, overTime, customer);

					// 充值人数
					Long rechargeNum = walletLogService.getRechargeNum(startTime, overTime, customer);

					// 充值金额
					BigDecimal rechargeMon = walletLogService.getRechargeMon(startTime, overTime, customer);

					CpsDayReport cpsDayReport = new CpsDayReport();
					cpsDayReport.setCustomerId(customer);
					cpsDayReport.setCommission(commission);
					cpsDayReport.setCreateTime(Calendar.getInstance());
					cpsDayReport.setIsPay(false);
					cpsDayReport.setTotal(total);
					cpsDayReport.setReportDate(format.format(curReportDate.getTime()));
					cpsDayReport.setRegisterNum(regNum);
					cpsDayReport.setSalesNum(payNum);
					cpsDayReport.setRechargeNum(rechargeNum);
					cpsDayReport.setRechargeMon(rechargeMon);
					cpsReportDao.save(cpsDayReport);
					// customer.setSuperCommission(customer.getSuperCommission().add(commission));
					// customerService.save(customer);
				}
			}
		}
		systemParam.setValue(format.format(new Date()));
		systemParamDao.save(systemParam);
	}

	public static void main(String[] args)
	{
		String[] contextPaths = new String[] { "applicationContext.xml" };
		ApplicationContext springContext = new ClassPathXmlApplicationContext(contextPaths);
		CpsReportService cpsReportService = (CpsReportService) springContext.getBean("cpsReportServiceImpl");
		try
		{
			cpsReportService.doCpsDayReport();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public CpsDayReport getCpsDayReportByDateAndCustomerId(String reportDate, Customer customer)
	{
		Criteria criteria = cpsReportDao.createCriteria();
		criteria.add(Restrictions.eq("reportDate", reportDate));
		criteria.add(Restrictions.eq("customer", customer));
		List<CpsDayReport> list = criteria.list();
		CpsDayReport cpsDayReport = null;
		if (list != null && list.size() != 0)
		{
			cpsDayReport = list.get(0);
		}
		return cpsDayReport;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal sumMoneyByStateAndCustomerId(Calendar stime, Calendar etime, Boolean isPay, Customer customer)
	{
		Criteria criteria = cpsReportDao.createCriteria();
		criteria.setProjection(Projections.projectionList().add(Projections.sum("commission")));
		if (isPay != null)
		{
			criteria.add(Restrictions.eq("isPay", isPay));
		}
		if (customer != null)
		{
			criteria.add(Restrictions.eq("customer", customer));
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (stime != null)
		{
			criteria.add(Restrictions.ge("reportDate", format.format(stime.getTime())));
		}
		if (etime != null)
		{
			criteria.add(Restrictions.le("reportDate", format.format(etime.getTime())));
		}
		List<Object> list = criteria.list();
		BigDecimal sumNum = new BigDecimal(0.00);
		Object obj = list.get(0);
		if (obj != null)
		{
			sumNum = (BigDecimal) obj;
		}
		return sumNum;
	}

	@SuppressWarnings("unchecked")
	public List<CpsDayReport> findReportByStateAndCustomerId(Calendar stime, Calendar etime, Boolean isPay,
			Customer customer)
	{
		Criteria criteria = cpsReportDao.createCriteria();
		if (isPay != null)
		{
			criteria.add(Restrictions.eq("isPay", isPay));
		}
		if (customer != null)
		{
			criteria.add(Restrictions.eq("customer", customer));
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (stime != null)
		{
			criteria.add(Restrictions.ge("reportDate", format.format(stime.getTime())));
		}
		if (etime != null)
		{
			criteria.add(Restrictions.le("reportDate", format.format(etime.getTime())));
		}
		List<CpsDayReport> list = criteria.list();

		return list;
	}

	public void calculate(Calendar stime, Calendar etime, Boolean isPay, List<Customer> customerList)
	{
		for (Customer customer : customerList)
		{
			BigDecimal sumMoney = new BigDecimal(0.00);
			List<CpsDayReport> list = findReportByStateAndCustomerId(stime, etime, isPay, customer);
			for (CpsDayReport cpsDayReport : list)
			{
				cpsDayReport.setIsPay(true);
				update(cpsDayReport);
				sumMoney = sumMoney.add(cpsDayReport.getCommission());
			}
			customer.setSuperCommission(customer.getSuperCommission().add(sumMoney));
			customerService.update(customer);
		}
	}
}
