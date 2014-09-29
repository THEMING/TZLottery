package com.xsc.lottery.create.xml.bean;

import java.io.FileWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.Comparator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class LotteryMiss
{
    private String code;
    // 次数
    private String count;
    // 理论次数
    private String theoryCount;
    // 频率
    private String frequency;
    // 平均遗漏
    private String aveMiss;
    // 最大遗漏
    private String maxMiss;
    // 上次遗漏
    private String preMiss;
    // 最后遗漏
    private String currMiss;
    // 欲出几率
    private String open;
    // 回补几率
    private String mend;
    // 最大遗漏期间
    private String maxInterval;

    public static class MissComparatorByOpen implements Comparator<LotteryMiss>
    {
        public int compare(LotteryMiss o1, LotteryMiss o2)
        {
            return Float.valueOf(o1.getOpen()).compareTo(
                    Float.valueOf(o2.getOpen()));
        }
    }

    public static class MissComparatorByCount implements
            Comparator<LotteryMiss>
    {
        public int compare(LotteryMiss o1, LotteryMiss o2)
        {
            return Float.valueOf(o1.getCount()).compareTo(
                    Float.valueOf(o2.getCount()));
        }

    }

    public static void listToXml(List<LotteryMiss> missList, String path)
            throws Exception
    {
        SAXBuilder sb = new SAXBuilder();
        Reader reader = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><xml></xml>");
        Document returnDoc = sb.build(reader);
        Element rootElement = returnDoc.getRootElement();
        LotteryMiss bean;
        for(int i = 0; i < missList.size(); i++) {
            bean = missList.get(i);
            Element row = new Element("sort" + (i + 1));
            row.setAttribute(new Attribute("code", bean.getCode()));
            row.setAttribute(new Attribute("count", bean.getCount()));
            row.setAttribute(new Attribute("theoryCount", bean.getTheoryCount()));
            row.setAttribute(new Attribute("frequency", bean.getFrequency()));
            row.setAttribute(new Attribute("aveMiss", bean.getAveMiss()));
            row.setAttribute(new Attribute("maxMiss", bean.getMaxMiss()));
            row.setAttribute(new Attribute("preMiss", bean.getPreMiss()));
            row.setAttribute(new Attribute("currMiss", bean.getCurrMiss()));
            row.setAttribute(new Attribute("open", bean.getOpen()));
            row.setAttribute(new Attribute("mend", bean.getMend()));
            row.setAttribute(new Attribute("maxInterval", bean.getMaxInterval()));
            rootElement.addContent(row);
        }
        
        XMLOutputter xo = new XMLOutputter();
        Format format = xo.getFormat();
        format.setEncoding("UTF-8");
        format.setLineSeparator("\n");
        
        //缩进
        format.setIndent("  ");
        xo.setFormat(format);
        FileWriter writer = new FileWriter(path);
        xo.output(returnDoc, writer);
        writer.close();
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getTheoryCount()
    {
        return theoryCount;
    }

    public void setTheoryCount(String theoryCount)
    {
        this.theoryCount = theoryCount;
    }

    public String getFrequency()
    {
        return frequency;
    }

    public void setFrequency(String frequency)
    {
        this.frequency = frequency;
    }

    public String getAveMiss()
    {
        return aveMiss;
    }

    public void setAveMiss(String aveMiss)
    {
        this.aveMiss = aveMiss;
    }

    public String getMaxMiss()
    {
        return maxMiss;
    }

    public void setMaxMiss(String maxMiss)
    {
        this.maxMiss = maxMiss;
    }

    public String getPreMiss()
    {
        return preMiss;
    }

    public void setPreMiss(String preMiss)
    {
        this.preMiss = preMiss;
    }

    public String getCurrMiss()
    {
        return currMiss;
    }

    public void setCurrMiss(String currMiss)
    {
        this.currMiss = currMiss;
    }

    public String getOpen()
    {
        return open;
    }

    public void setOpen(String open)
    {
        this.open = open;
    }

    public String getMend()
    {
        return mend;
    }

    public void setMend(String mend)
    {
        this.mend = mend;
    }

    public String getMaxInterval()
    {
        return maxInterval;
    }

    public void setMaxInterval(String maxInterval)
    {
        this.maxInterval = maxInterval;
    }
}
