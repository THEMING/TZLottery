/**
 * <pre>
 * Title: 		ComponentContextLoader.java
 * Project: 	HP-Common
 * Author:		pengfangliang
 * Create:	 	2014-4-22 上午09:03:04
 * Copyright: 	Copyright (c) 2008
 * Company:		GuangDong CaiSo
 * <pre>
 */
package com.xsc.lottery.common;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * <pre>
 * 组件集成服务上下文装载器
 * </pre>
 * @author pengfangliang
 * @version 1.0, 2014-4-22
 */
public class ComponentContextLoader implements ApplicationContextAware
{
	/**
	 * <pre>
	 * 私有静态内部类, 只有当有引用时, 该类才会被装载
	 * </pre>
	 * @author linriqing
	 * @version 1.0, 2009-4-28
	 */
	public static final class LazyInstance
	{
		private static ApplicationContext context = null;

		private static Object lock = new Object();

		/**
		 * <pre>
		 * 设置Spring应用上下文
		 * </pre>
		 * @param applicationcontext Spring应用上下文
		 */
		public synchronized static final void setContext(ApplicationContext applicationcontext)
		{
			if (context == null)
			{
				context = applicationcontext;
			}
			else
			{
				if (!context.equals(applicationcontext))
				{
					throw new RuntimeException("应用正在试图设置不同的ApplicationContext, 已丢弃ApplicationContext实例:"
							+ applicationcontext);
				}
			}
		}

		/**
		 * <pre>
		 * 获取单例的Spring应用上下文
		 * </pre>
		 * @return Spring应用上下文
		 */
		private static final ApplicationContext getContext()
		{
			if (context == null)
			{
				try
				{
					synchronized (lock)
					{
						if (context == null)
						{
							context = new ClassPathXmlApplicationContext(Constants.SYSTEM_COMPONENT_CONFIG_FILEPATH);
						}
					}
				}
				catch (RuntimeException e)
				{
					throw new RuntimeException("应用正在试图创建ApplicationContext时异常", e);
				}
			}
			return context;
		}
	}

	/**
	 * <pre>
	 * 获取Spring上下文中第一个类型为beanType的Bean
	 * </pre>
	 * @param <T> bean类型
	 * @param beanType bean类型
	 * @return bean实例
	 */
	public static final <T> T getBean(Class<T> beanType)
	{
		Map<?, T> map = LazyInstance.getContext().getBeansOfType(beanType, false, false);
		if (map.isEmpty())
		{
			return null;
		}
		else
		{
			return (T) map.values().iterator().next();
		}
	}

	/**
	 * 获取Spring上下文中的Bean
	 * @param beanName bean名称
	 * @return Bean对象
	 * @throws SystemException 系统异常
	 */
	public static final Object getBean(String beanName) throws Exception
	{
		return LazyInstance.getContext().getBean(beanName);
	}

	/**
	 * <pre>
	 * 获取Spring上下文中的Bean
	 * </pre>
	 * @param <T> bean类型
	 * @param beanName bean名称
	 * @param beanType bean类型
	 * @return bean实例
	 */
	public static final <T> T getBean(String beanName, Class<T> beanType)
	{
		return (T) LazyInstance.getContext().getBean(beanName, beanType);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		ComponentContextLoader.LazyInstance.setContext(applicationContext);
	}

	public static final ApplicationContext getApplicationContext()
	{
		return ComponentContextLoader.LazyInstance.getContext();
	}
}
