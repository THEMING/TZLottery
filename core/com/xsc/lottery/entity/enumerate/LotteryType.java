package com.xsc.lottery.entity.enumerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LotteryType
{
    全部("all"), 双色球("ssq"), 大乐透("dlt"), 福彩3d("3d"), 排列三("pls"), 排列五("plw"), 重庆时时彩(
            "ssc_CQ"), 上海时时乐("ssl_SH"), 七星彩("qxc"), 七乐彩("qlc"), 足彩14场("14sfc"), 足彩任9(
            "r9"), 竞彩足球("jczq"), 四场进球("4cjq"), 足彩6场半("6cb"), 竞彩篮球("jclq"), 老11选5("l11x5"),
            快乐扑克3("klpk3"),广西快3("gxk3"),上海11选5("sh11x5"),十一运夺金("sd11x5"),广东11选5("gd11x5"),江苏快3("jsk3"),江西时时彩("jxssc");

    public static Map<String, LotteryType> KuaiKaiTypeMap = new HashMap<String, LotteryType>();
    public static Map<String, LotteryType> AdvanceSaleTypeMap = new HashMap<String, LotteryType>();
    
    public static List lotteryTypeList=new ArrayList();

    static {
        KuaiKaiTypeMap.put(重庆时时彩.getName_EN(), 重庆时时彩);
        KuaiKaiTypeMap.put(上海时时乐.getName_EN(), 上海时时乐);
        KuaiKaiTypeMap.put(老11选5.getName_EN(), 老11选5);
        KuaiKaiTypeMap.put(快乐扑克3.getName_EN(), 快乐扑克3);
        KuaiKaiTypeMap.put(广西快3.getName_EN(), 广西快3);
        KuaiKaiTypeMap.put(上海11选5.getName_EN(), 上海11选5);
        KuaiKaiTypeMap.put(十一运夺金.getName_EN(), 十一运夺金);
        KuaiKaiTypeMap.put(广东11选5.getName_EN(), 广东11选5);
        KuaiKaiTypeMap.put(江苏快3.getName_EN(), 江苏快3);
        KuaiKaiTypeMap.put(江西时时彩.getName_EN(), 江西时时彩);
    }

    static {
        AdvanceSaleTypeMap.put(足彩14场.getName_EN(), 足彩14场);
        AdvanceSaleTypeMap.put(足彩任9.getName_EN(), 足彩任9);
    }
    
    static{
    	lotteryTypeList.add("全部");
    	lotteryTypeList.add("双色球");
    	lotteryTypeList.add("大乐透");
    	lotteryTypeList.add("福彩3d");
    	lotteryTypeList.add("排列三");
    	lotteryTypeList.add("排列五");
    	lotteryTypeList.add("重庆时时彩");
    //	lotteryTypeList.add("上海时时乐");
    	lotteryTypeList.add("七星彩");
    	lotteryTypeList.add("七乐彩");
    	lotteryTypeList.add("足彩14场");
    	lotteryTypeList.add("足彩任9");
    	lotteryTypeList.add("竞彩足球");
    	lotteryTypeList.add("四场进球");
    	lotteryTypeList.add("足彩6场半");
    	lotteryTypeList.add("竞彩篮球");
    	lotteryTypeList.add("快乐扑克3");
    	lotteryTypeList.add("广西快3");
    	lotteryTypeList.add("上海11选5");
    	lotteryTypeList.add("十一运夺金");
    }
    private String name_EN;

    private LotteryType(String name_en)
    {
        this.name_EN = name_en;
    }

    public String getName_EN()
    {
        return name_EN;
    }

    public void setName_EN(String name_EN)
    {
        this.name_EN = name_EN;
    }

    static public LotteryType enToType(String en)
    {
        for (LotteryType type : LotteryType.values()) {
            if (type.getName_EN().equals(en))
                return type;
        }
        return null;
    }
}
