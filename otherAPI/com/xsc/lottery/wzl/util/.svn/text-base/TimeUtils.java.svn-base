package com.xsc.lottery.wzl.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils 
{
	/**
	 * 将date转为字符串类型的
	 */
	public static String getDateToString(Date date) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		if (null != date && ("".equals(date) == false)) {
			String ret = sdf.format(date);
			return ret;
		} 
		else {
			return sdf.format(new Date());
		}
	}

	/**
	 * 得到系统时间的年份
	 */
	public static String getYear()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return format.format(date);
	}
	
	/**
	 * 得到系统时间的年月份
	 */
	public static String getMonth()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String year=format.format(date);
		String[] years=year.split("-");
		return years[1]; 
	}
	
	/**
	* 取得系统时间年份，月份的最后一天
	* 用JAVA的类来实现，方法是取得下个月第一天，然后再取前一天，
	*/
	public static String getStartTimeOfMonth(String mon) 
	{ 
		 int year=Integer.parseInt(getYear());
		 GregorianCalendar d1=new GregorianCalendar(year,Integer.parseInt(mon)-1,1); 
		 GregorianCalendar d2=(GregorianCalendar)d1.clone(); 
		 d2.add(Calendar.MONTH,1); 
		 int day = (int)((d2.getTimeInMillis()-d1.getTimeInMillis())/3600/1000/24);
		 return getYear()+"-"+mon+"-"+day+" 00:00:00";	
	}
	
	public static String getEndTimeOfMonth(String mon)
	{ 
		 int year=Integer.parseInt(getYear());
		 GregorianCalendar d1=new GregorianCalendar(year,Integer.parseInt(mon)-1,1); 
		 GregorianCalendar d2=(GregorianCalendar)d1.clone(); 
		 d2.add(Calendar.MONTH,1); 
		 int day = (int)((d2.getTimeInMillis()-d1.getTimeInMillis())/3600/1000/24);
		 return year+"-"+mon+"-"+day+" 23:59:59";
		
	}
}
