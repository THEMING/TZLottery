package com.xsc.lottery.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * 日期时间格式化共用类
 * </pre>
 * @author CHARLES
 * @version 1.0, 2010-8-20
 */
public class CalendarUtil
{
	public static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static final DateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final DateFormat LONG_DATE_FORMAT_NOMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static final DateFormat ORDER_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHH");

	public static final DateFormat LONG_DATE_FORMAT_NO_SPLIT = new SimpleDateFormat("yyyyMMddHHmmss");

	public static Date parseLongDateNoSplit(String time)
	{
		if (time == null || time.length() <= 0)
			return null;
		try
		{
			Date date = LONG_DATE_FORMAT_NO_SPLIT.parse(time);
			return date;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("格式化时间错误");
		}
	}

	public static Date parseFormatNoMM(String time)
	{
		if (time == null || time.length() <= 0)
			return null;
		try
		{
			Date date = LONG_DATE_FORMAT_NOMM.parse(time);
			return date;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("格式化时间错误");
		}
	}

	public static Date parseFormat(String time)
	{
		if (time == null || time.length() <= 0)
			return null;
		try
		{
			Date date = LONG_DATE_FORMAT.parse(time);
			return date;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("格式化时间错误", ex);
		}
	}

	public static String formatDateNoMM(Date date)
	{
		return LONG_DATE_FORMAT_NOMM.format(date);
	}

	public static String formatShortDate(Date date)
	{
		return formatDate(date, SHORT_DATE_FORMAT);
	}

	public static Date parseFormatShort(String time)
	{
		if (time == null || time.length() <= 0)
			return null;
		try
		{
			Date date = SHORT_DATE_FORMAT.parse(time);
			return date;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("格式化时间错误", ex);
		}
	}

	public static String formatDate(Date date, DateFormat format)
	{
		return format.format(date);
	}

	public static Date getCurrentDateShort()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
