package com.xsc.lottery.javatest.tools;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher
{

    private static transient final Logger logger = LoggerFactory
            .getLogger(Launcher.class);
    private static AbstractApplicationContext springContext = null;

    public static void main(String[] args) throws ScriptException
    {
        new Launcher();
        /*
         * //LotteryTermService lotteryTermService=(LotteryTermService)
         * getBean("lotteryTermService"); //Map<String,Integer>
         * map=lotteryTermService
         * .getCurrentYilouByLotteryType(LotteryType._双色球); // LotteryTerm
         * term=lotteryTermService.getLastStopSaleTerm(LotteryType._排列3);
         * Set<String> keys=map.keySet(); for(String key:keys){
         * System.out.println(key+":"+map.get(key)); }
         */
    }

    public Launcher()
    {
        logger.info("spring 启动...");
        String[] contextPaths = new String[] { "applicationContext.xml" };

        try {
            springContext = new ClassPathXmlApplicationContext(contextPaths);
            springContext.registerShutdownHook();
            logger.info("spring 启动完成");
        }
        catch (Exception e) {
            logger.error("spring启动失败!", e);
        }
    }

    public static Object getBean(String beanId)
    {
        if (springContext == null) {
            try {
                new Launcher();
            }
            catch (Exception e) {
                logger.error("getBean失败", e);
            }
        }
        return springContext.getBean(beanId);
    }

}
