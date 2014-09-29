package com.xsc.lottery.create.xml;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.java.common.CommonScheduledThreadPoolExecutor;

/**
 * data-generating factory
 */
public class BuildDataFactory
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static BuildDataFactory factory = new BuildDataFactory();
    public Map<LotteryType, BaseBuildData> map = new HashMap<LotteryType, BaseBuildData>();

    private BuildDataFactory()
    {
    }

    public static BuildDataFactory getInstance()
    {
        return factory;
    }

    public BaseBuildData getBuildClass(LotteryType key)
    {
        return map.get(key);
    }

    public void registerBuildClass(BaseBuildData builddata)
    {
        logger.info(builddata.getType().name() + 
                " register into data-generating factory ...");
        if(!map.containsKey(builddata.getType())) {
            map.put(builddata.getType(), builddata);
        }
    }

    public void startBuildStaticDataByType(final LotteryTerm term)
    {
        CommonScheduledThreadPoolExecutor.getInstance().execute(
            new Runnable() {
                public void run()
                {
                    //当前期开始抓取数据
                    Calendar stopcalendar = term.getStopSaleTime();
                    int d = getDlt(stopcalendar.get(Calendar.DAY_OF_WEEK));
                    stopcalendar.add(Calendar.DAY_OF_MONTH, d);
                    if(stopcalendar.compareTo(Calendar.getInstance()) <= 0) {
                        logger.debug(term.toString() + " 开始生成静态数据...");
                        try {
                            map.get(term.getType()).startBuildStaticData();
                        } 
                        catch (Exception e) {
                            logger.warn(term.toString() + " 开奖抓取数据静态生成异常", e);
                        }
                    }
                }
            }
        );
    }

    private int getDlt(int dayOfWeek)
    {
        switch (dayOfWeek) {
        case 4:
            return 3;
        case 7:
            return 2;
        default:
            return 2;
        }
    }
}
