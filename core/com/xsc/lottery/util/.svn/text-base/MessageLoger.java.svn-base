package com.xsc.lottery.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageLoger
{
	private static final Log logger = LogFactory.getLog(MessageLoger.class);

	public static void print(Class<?> c, String str)
	{
		try
		{
			String fileName = "AccessHttpRequest.log";
			StringBuffer sb = new StringBuffer();
			File file = new File(fileName);
			if (file.exists())
			{
				sb.append("\r\n");
				// 当文件大小大于100M的情况下产生新的文件,旧文件备份
				if (file.length() > 100 * 1024 * 1024)
				{
					fileName = "AccessHttpRequest.log." + StringHelper.getStringFromDate(new Date(), "yyyyMMddHHmmss");
					File backUpFile = new File(fileName);
					file.renameTo(backUpFile);
					file = new File("AccessHttpRequest.log");
				}
			}
			FileWriter fw = new FileWriter(file, true);
			sb.append(StringHelper.getStringFromDate(new Date(), "yy-MM-dd HH:mm:ss") + " [INFO] ");
			sb.append(c.getName() + " - ");
			sb.append(str);
			fw.append(sb.toString());
			fw.close();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
}
