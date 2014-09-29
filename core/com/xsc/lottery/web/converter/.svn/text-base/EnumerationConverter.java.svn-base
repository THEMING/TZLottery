package com.xsc.lottery.web.converter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

@SuppressWarnings("unchecked")
public class EnumerationConverter extends DefaultTypeConverter
{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object convertValue(Map context, Object value, Class toType)
    {
        if (toType.isEnum()) {
            logger.debug(toType.getSuperclass() + "convertValue: " + value
                    + " => " + toType);
            if (value == null)
                return null;
            if (value instanceof String[]) {
                String[] ss = (String[]) value;
                if (ss.length == 1) {
                    return Enum.valueOf(toType, ss[0]);
                }
                else {
                    Object[] oo = new Object[ss.length];
                    for (int i = 0; i < ss.length; i++) {
                        oo[i] = Enum.valueOf(toType, ss[i]);
                    }
                    return oo;
                }
            }
        }

        return super.convertValue(context, value, toType);
    }

}
