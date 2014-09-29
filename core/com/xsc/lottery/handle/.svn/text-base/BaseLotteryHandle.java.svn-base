package com.xsc.lottery.handle;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dom4j.Element;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;

/** interface for all lotteries */
public abstract class BaseLotteryHandle
{
    @Resource
    private LotteryHandleFactory handleFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void registerToFactoryMap()
    {
        handleFactory.registerHandleInMap(this);
    }

    /**
     * @功能：获取TYPE方法
     */
    public abstract LotteryType getLotteryType();

    /**
     * @功能：拆票方法
     */
    protected abstract List<Ticket> unpackTicket(Order order, PlanItem item);
    
    /**
     * @功能：为我中了拆票
     */
    protected abstract List<Ticket> unpackTicketForWZL(Order order, PlanItem item);
    
    /**
     * @功能：为新冠拆票
     */
    protected abstract List<Ticket> unpackTicketForXG(Order order, PlanItem item);
    
    /**
     * @功能：抓取开奖结果方法
     */
    public abstract LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception;

    /**
     * 生成下一期
     */
    public abstract LotteryTerm getNextTerm(LotteryTerm term);

    /** 验证文件上传格式 **/
    public abstract List<PlanItem> validateUploadFile(String filePath, 
    		BigDecimal oneMoney, int multiple, BigDecimal totalMoney) throws Exception;

    /** 把客户端传过来的字符串转成方案选号对象 */
    public abstract PlanItem perseCodeStringTOPlanItem(String code,
            BigDecimal oneMoney) throws Exception;

    /**
     * 根据方案内案生成票
     * */
    public List<Ticket> getTicketByPlanItems(Order order, List<PlanItem> items,SendTicketPlat sp)
    {
        List<Ticket> list = new ArrayList<Ticket>();
        if (sp.equals(SendTicketPlat.大赢家)) {
        	for (PlanItem item : items) {
                list.addAll(unpackTicket(order, item));
            }
		}
        if (sp.equals(SendTicketPlat.我中啦))  {
        	for (PlanItem item : items) {
                list.addAll(unpackTicketForWZL(order, item));
            }
		}
        if(sp.equals(SendTicketPlat.新冠))
        {
        	for (PlanItem item : items) {
        		list.addAll(unpackTicketForXG(order, item));
            }
        }
        return list;
    }

    /**
     * 投注号码验证
     * */
    public abstract boolean validataBetNum(String result) throws Exception;

    /**
     * 开奖
     */
    public abstract List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
            List<PrizeLevel> prizeLevels);

    /**
     * 投注注数计算
     * */
    public abstract int validataBetCount(String code) throws Exception;

    /**
     * 判断重复
     * */
    public abstract boolean validataRepeat(String code) throws Exception;

    /**
     * 获取期号
     * */
    public abstract List<String> getTermCode(LotteryTerm term, int number);
    
    /**
     * 根据上传的XML来保存有关数据
     * */
    public abstract void parseXML(Element root) throws Exception;
    
    public String number2String(int number) 
    {
    	if(number < 10) {
    		return "0" + number;
		}
		else {
			return String.valueOf(number);
		}
    }
    
    public String changeEncoding(String s)
    {
    	try {
			s = new String(s.getBytes("utf8"), "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return s;
    }
    
    /**
     * 按照升幂排序
     */
    public abstract String sort(String content);
    
    /**
     * 变成我中了所需要的字符串
     * @param content
     * @return
     */
    public abstract String changeToWZLContent(String content);
    
    public String typeToWZLType(String type)
    {
    	String wzlType = "";
    	if(type.equals("RQSPF"))
    	{
    		wzlType =  "301";
    	}
    	else if(type.equals("SPF"))
    	{
    		wzlType =  "320";
    	}else if(type.equals("CBF"))
    	{
    		wzlType =  "302";
    	}else if(type.equals("JQS"))
    	{
    		wzlType =  "303";
    	}else if(type.equals("BQC"))
    	{
    		wzlType =  "304";
    	}
    	if(type.equals("SF"))
    	{
    		wzlType =  "307";
    	}
    	else if(type.equals("RFSF"))
    	{
    		wzlType =  "306";
    	}
    	else if(type.equals("SFC"))
    	{
    		wzlType =  "308";
    	}
    	else if(type.equals("DXF"))
    	{
    		wzlType =  "309";
    	}
    	return wzlType;
    }
    

}
