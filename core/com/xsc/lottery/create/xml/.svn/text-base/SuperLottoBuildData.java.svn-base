package com.xsc.lottery.create.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xsc.lottery.create.xml.bean.LotteryMiss;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.ContentUtil;
import com.xsc.lottery.util.FileUtils;
import com.xsc.lottery.util.NetWorkUtil;

@Component
public class SuperLottoBuildData extends BaseBuildData
{
    private final String resultList = "dltResultListPath";
    private final String hmyl_fore = "dltBlueMissPath";
    private final String hmyl_back = "dltBlueMissPath";

    private String _500wanRedMissUrl = "http://datachart.500wan.com/dlt/omit/inc/hmyl_fore.php";
    private String _500wanBlueMissUrl = "http://datachart.500wan.com/dlt/omit/inc/hmyl_back.php";
    private String _500wanResultList = "http://www.500wan.com//static/info/kaijiang/xml/dlt/list10.xml";

    @Override
    public void startBuildStaticData() throws Exception
    {
        buildMiss();
        buildLotteryResult();
    }

    public LotteryType getType()
    {
        return LotteryType.大乐透;
    }

    private void buildLotteryResult() throws Exception
    {
        String context = NetWorkUtil.requestNet(_500wanResultList);
        FileUtils.writeFile(ContentUtil.getRealpath()
                + Configuration.getInstance().getFormatValue(resultList, "10"),
                context);
    }

    private void buildMiss() throws Exception
    {
        createRedMiss();
        createBuleMiss();
    }

    private void createRedMiss() throws Exception
    {
        String[] nativedata = super.readNetResout(_500wanRedMissUrl).split("\n");
        int offset = 13;
        List<LotteryMiss> list = new ArrayList<LotteryMiss>();
        LotteryMiss bean = null;
        for (int i = 1; i <= 35; i++) {
            bean = new LotteryMiss();
            bean.setCode(nativedata[offset++].trim());
            bean.setCount(nativedata[offset++].trim());
            bean.setTheoryCount(nativedata[offset++].trim());
            bean.setFrequency(nativedata[offset++].trim());
            bean.setAveMiss(nativedata[offset++].trim());
            bean.setMaxMiss(nativedata[offset++].trim());
            bean.setPreMiss(nativedata[offset++].trim());
            bean.setCurrMiss(nativedata[offset++].trim());
            bean.setOpen(nativedata[offset++].trim());
            bean.setMend(nativedata[offset++].trim());
            bean.setMaxInterval(nativedata[offset++].trim());
            list.add(bean);
        }
        
        Collections.sort(list, new LotteryMiss.MissComparatorByOpen());
        LotteryMiss.listToXml(list, ContentUtil.getRealpath()
                + Configuration.getInstance().getValue(hmyl_fore));
    }

    private void createBuleMiss() throws Exception
    {
        String[] nativedata = super.readNetResout(_500wanBlueMissUrl).split("\n");
        int offset = 13;
        List<LotteryMiss> list = new ArrayList<LotteryMiss>();
        LotteryMiss bean = null;
        for (int i = 1; i <= 12; i++) {
            bean = new LotteryMiss();
            bean.setCode(nativedata[offset++].trim());
            bean.setCount(nativedata[offset++].trim());
            bean.setTheoryCount(nativedata[offset++].trim());
            bean.setFrequency(nativedata[offset++].trim());
            bean.setAveMiss(nativedata[offset++].trim());
            bean.setMaxMiss(nativedata[offset++].trim());
            bean.setPreMiss(nativedata[offset++].trim());
            bean.setCurrMiss(nativedata[offset++].trim());
            bean.setOpen(nativedata[offset++].trim());
            bean.setMend(nativedata[offset++].trim());
            bean.setMaxInterval(nativedata[offset++].trim());
            list.add(bean);
        }
        
        Collections.sort(list, new LotteryMiss.MissComparatorByOpen());
        LotteryMiss.listToXml(list, ContentUtil.getRealpath()
                + Configuration.getInstance().getValue(hmyl_back));
    }

    public static void main(String[] args)
    {
        SuperLottoBuildData dd = new SuperLottoBuildData();
        try {
            dd.buildMiss();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
