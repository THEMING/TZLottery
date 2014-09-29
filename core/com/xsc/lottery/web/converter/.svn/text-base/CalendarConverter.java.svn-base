package com.xsc.lottery.web.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

@SuppressWarnings("unchecked")
public class CalendarConverter  extends DefaultTypeConverter 
{
	 protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	 public Object convertValue(Map context, Object value, Class toType)
	 {
		 logger.debug(toType.getSuperclass() + "convertValue: " + value + " => " + toType);
		 if (value == null)
             return null;
		 if (value instanceof String[])
		 {
			 String[] values = (String[]) value;
			 if(values.length == 0|| StringUtils.isEmpty(values[0].trim()))
				 return null;
			 Calendar cal = Calendar.getInstance();
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
             try
             {
            	 if (values.length == 1)
                 {
                	 Date date = dateFormat.parse(values[0]);  
                     cal.setTime(date);  
                     return cal;
                 }
                 else
                 {
                     Object[] oo = new Object[values.length];
                     for (int i = 0; i < values.length; i++) {
                    	 Date date = dateFormat.parse(values[0]);  
                         cal.setTime(date);  
                         oo[i] = cal;
                     }
                     return oo;
                 }
			} catch (Exception e) {
				logger.info("TypeConversion 出错啦!!!");  
		         e.printStackTrace();  
		         return cal;  
			}
             
         }
	     return super.convertValue(context, value, toType);
	 }
}
