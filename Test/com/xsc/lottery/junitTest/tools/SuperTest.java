package com.xsc.lottery.junitTest.tools;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SuperTest
{
    public static ApplicationContext springContext;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        String[] contextPaths = new String[] { "applicationContext.xml" };
        springContext = new ClassPathXmlApplicationContext(contextPaths);
    }
}
