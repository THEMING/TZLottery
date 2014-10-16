package com.xsc.lottery.common;

public interface Constants
{
	/*
	 * CPS最后一次统计日期(系统参数)
	 */
    public final static String CPS_LAST_DATE = "cps_last_date";
    
    /*
     *cps任务(任务名) 
     */
    public final static String CPS_TASK = "cps_task";
    
    /*
     * 上下文配置文件 
     */
    public final static String SYSTEM_COMPONENT_CONFIG_FILEPATH = "applicationContext.xml";
    
    //高频加奖比率
    
    /*
     * 重庆时时彩加奖比率 
     */
    public final static String CQSSC_BOUNS_RATE = "cqssc_bouns_rate";
    
    /*
     * 广西快3加奖比率 
     */
    public final static String GXK3_BOUNS_RATE = "gxk3_bouns_rate";
    
    /*
     * 山东11运夺金加奖比率 
     */
    public final static String SD11X5_BOUNS_RATE = "sd11x5_bouns_rate";
    
    /*
     * 上海11选5加奖比率 
     */
    public final static String SH11X5_BOUNS_RATE = "sh11x5_bouns_rate";
    
    /*
     * 江西11选5（老11选5）加奖比率 
     */
    public final static String JX11X5_BOUNS_RATE = "jx11x5_bouns_rate";
    
    /*
     * 山东快乐扑克3加奖比率 
     */
    public final static String SDKLPK3_BOUNS_RATE = "sdklpk3_bouns_rate";
    
    /*
     * 通过QQ导入文件名 
     */
    public final static String CUSTOMER_IMPORT_FILENAME = "customer_import_filename";
    
    /*
     * 是否开启用户导入任务    0-开启  1-关闭
     */
    public final static String ISOPEN_CUSTOMER_IMPORT_TASK = "isopen_customer_import_task";
    
}
