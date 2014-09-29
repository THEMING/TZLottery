package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.xsc.lottery.dyj.sendticket.bingocheck.DltBingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.util.FetchlDataUtil;
import com.xsc.lottery.util.FileUtils;
import com.xsc.lottery.util.MathUtil;
import com.xsc.lottery.util.NetWorkUtil;

@Component
public class _dltHandle extends BaseLotteryHandle
{
    private final String FETHCHKEY = "fetch_dlt";

    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.大乐透;
    }

    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> fetchResutl = fetchl.parserHtml(
                Configuration.getInstance().getFormatValue(FETHCHKEY,
                        term.getTermNo().substring(2)), HTML.Tag.TR).iterator();
        String str_0, result = "";
        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(12);
        while (fetchResutl.hasNext()) {
            str_0 = fetchResutl.next().trim();
            if (str_0.indexOf("开奖号码") > -1) {
                for (int i = 0; i < 5; i++) {
                    str_0 = fetchResutl.next().trim();
                    if (i == 4)
                        result = result + str_0;
                    else
                        result = result + str_0 + ",";
                }
                term.setResult(result + "|" + fetchResutl.next().trim() + ","
                        + fetchResutl.next().trim());
            } else if (str_0.indexOf("出球顺序") > -1)
                term.setOrderResult(fetchResutl.next());
            else if (str_0.indexOf("本期销量") > -1)
                term.setTotalSale(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            else if (str_0.indexOf("奖池滚存") > -1)
                term.setPrizePool(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            else if (str_0.indexOf("一等奖") > -1) {
                fetchResutl.next();
                PrizeLevel prizeLevel = new PrizeLevel();
                prizeLevel.setName("一等奖");
                prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setLevel(1);
                prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                /*
                Integer one = 0;
                String oneStr = fetchResutl.next();
                if(oneStr.indexOf("+")!= -1 && oneStr.indexOf("元")!= -1)
                {
                	String[] temp = oneStr.split("\\+");
                	String baseStr = temp[0].substring(0, temp[0].indexOf("元"));
                	String addStr = temp[1].substring(0, temp[1].indexOf("元"));
                	one = Integer.parseInt(baseStr.replaceAll(",", ""))+Integer.parseInt(addStr.replaceAll(",", ""));
                }
                prizeLevel.setPrize(new BigDecimal(String.valueOf(one)));
                */
                fetchResutl.next();
                prizeLevel.setAddBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setAddPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                prizeLevels.add(prizeLevel);
                for (int i = 2; i < 6; i++) {
                    prizeLevel = new PrizeLevel();
                    prizeLevel.setName(fetchResutl.next().trim());
                    fetchResutl.next();
                    prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                    prizeLevel.setLevel(i);
                    prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                            .replaceAll(",", "")));
                    fetchResutl.next();
                    prizeLevel.setAddBetNum(Integer
                            .parseInt(fetchResutl.next()));
                    prizeLevel.setAddPrize(new BigDecimal(fetchResutl.next()
                            .replaceAll(",", "")));
                    prizeLevels.add(prizeLevel);
                }
            } else if (str_0.indexOf("六等奖") > -1) {
                PrizeLevel prizeLevel = new PrizeLevel();
                prizeLevel.setName("六等奖");
                prizeLevel.setLevel(6);
                prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                prizeLevels.add(prizeLevel);
            }
            else if(str_0.indexOf(">>") > -1)
            {
            	break;
            }
        }
        term.setPrizeLevels(prizeLevels);
        return term;
    }
    
    /*
    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> fetchResutl = fetchl.parserHtml(
                Configuration.getInstance().getFormatValue(FETHCHKEY,
                        term.getTermNo().substring(2)), HTML.Tag.TR).iterator();
        String str_0, result = "";
        List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
        while (fetchResutl.hasNext()) {
            str_0 = fetchResutl.next().trim();
            if (str_0.indexOf("开奖号码") > -1) {
                for (int i = 0; i < 5; i++) {
                    str_0 = fetchResutl.next().trim();
                    if (i == 4)
                        result = result + str_0;
                    else
                        result = result + str_0 + ",";
                }
                term.setResult(result + "|" + fetchResutl.next().trim() + ","
                        + fetchResutl.next().trim());
            } else if (str_0.indexOf("出球顺序") > -1)
                term.setOrderResult(fetchResutl.next());
            else if (str_0.indexOf("本期销量") > -1)
                term.setTotalSale(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            else if (str_0.indexOf("奖池滚存") > -1)
                term.setPrizePool(new BigDecimal(fetchResutl.next().replaceAll(
                        "元", "").replaceAll(",", "")));
            else if (str_0.indexOf("一等奖") > -1) {
                fetchResutl.next();
                PrizeLevel prizeLevel = new PrizeLevel();
                prizeLevel.setName("一等奖");
                prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setLevel(1);
                prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                fetchResutl.next();
                prizeLevel.setAddBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setAddPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                prizeLevels.add(prizeLevel);
                for (int i = 2; i < 8; i++) {
                    prizeLevel = new PrizeLevel();
                    prizeLevel.setName(fetchResutl.next().trim());
                    fetchResutl.next();
                    prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                    prizeLevel.setLevel(i);
                    prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                            .replaceAll(",", "")));
                    fetchResutl.next();
                    prizeLevel.setAddBetNum(Integer
                            .parseInt(fetchResutl.next()));
                    prizeLevel.setAddPrize(new BigDecimal(fetchResutl.next()
                            .replaceAll(",", "")));
                    prizeLevels.add(prizeLevel);
                }
            } else if (str_0.indexOf("八等奖") > -1) {
                PrizeLevel prizeLevel = new PrizeLevel();
                prizeLevel.setName("八等奖");
                prizeLevel.setLevel(8);
                prizeLevel.setBetNum(Integer.parseInt(fetchResutl.next()));
                prizeLevel.setPrize(new BigDecimal(fetchResutl.next()
                        .replaceAll(",", "")));
                prizeLevels.add(prizeLevel);
            }
            else if(str_0.indexOf(">>") > -1)
            {
            	break;
            }
        }
        term.setPrizeLevels(prizeLevels);
        return term;
    }
	*/
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

    public List<String> getTermCode(LotteryTerm term, int number)
    {
        List<String> list = new ArrayList<String>(number);
        String termno = term.getTermNo();
        for (int i = 0; i < number; i++) {
            list.add((Integer.valueOf(termno) + 1) + "");
            termno = list.get(i);
        }
        return list;
    }

    @Override
    public LotteryTerm getNextTerm(LotteryTerm term)
    {
        LotteryTerm nextTerm = new LotteryTerm();
        if (term.getTermNo().equals("2011014")) {
            nextTerm.setTermNo("2011015");
            nextTerm.setType(term.getType());
            nextTerm.setStartSaleTime(term.getStopSaleTime());
            nextTerm.setStopSaleTime(DateUtil.parse("2011-02-09 19:30:00",
                    "yyyy-MM-dd HH:mm:ss"));
            nextTerm.setOpenPrizeTime(DateUtil.parse("2011-02-09 21:30:00",
                    "yyyy-MM-dd HH:mm:ss"));
            nextTerm.setStopTogetherSaleTime(DateUtil.parse(
                    "2011-02-09 19:30:00", "yyyy-MM-dd HH:mm:ss"));
            nextTerm.setSendPrizeTime(DateUtil.parse("2011-02-09 23:00:00",
                    "yyyy-MM-dd HH:mm:ss"));
        } 
        else {
            nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");
            nextTerm.setType(term.getType());

            Calendar start = term.getStopSaleTime();
            Calendar stop = Calendar.getInstance();
            Calendar stopTo = Calendar.getInstance();
            Calendar open = Calendar.getInstance();
            Calendar sendPri = Calendar.getInstance();

            stop.setTime(start.getTime());
            // 截止打票时间
            int dayOfWeek = stop.get(Calendar.DAY_OF_WEEK);
            stop.add(Calendar.DAY_OF_MONTH, getDlt(dayOfWeek));
            stop.set(Calendar.HOUR_OF_DAY, 19);
            stop.set(Calendar.MINUTE, 30);

            // 合买截止时间
            stopTo.setTime(stop.getTime());
            stopTo.set(Calendar.HOUR_OF_DAY, 19);
            stopTo.set(Calendar.MINUTE, 30);

            // 开奖时间
            open.setTime(stop.getTime());
            open.set(Calendar.HOUR_OF_DAY, 21);
            open.set(Calendar.MINUTE, 30);

            // 兑奖时间
            sendPri.setTime(stop.getTime());
            sendPri.set(Calendar.HOUR_OF_DAY, 23);
            sendPri.set(Calendar.MINUTE, 0);

            nextTerm.setStartSaleTime(start);
            nextTerm.setStopSaleTime(stop);
            nextTerm.setOpenPrizeTime(open);
            nextTerm.setStopTogetherSaleTime(stopTo);
            nextTerm.setSendPrizeTime(sendPri);
        }
        return nextTerm;
    }

    @Override
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        try {
            if (validataBetNum(code) || validataRepeat(code)) {
                throw new Exception("大乐透投注号码格式错误!");
            }
        } catch (Exception e) {
            throw new Exception("大乐透投注号码格式错误!");
        }
        item.setBetCount(validataBetCount(code));
        item.setContent(code.split("-")[1]);
        item.setPlayType(PlayType.valueOf(code.split("-")[0]));
        item.setOneMoney(oneMoney);
        return item;
    }

    /**
     * 拆票（大赢家）
     */
    @Override
    protected List<Ticket> unpackTicket(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";
        if (item.getPlayType().equals(PlayType.dt)) {
            formatNum = item.getContent().replace(",", " ").replace("|", "-")
                    .replace("#", "$");
        } else {
            formatNum = item.getContent().replace(",", " ").replace("|", "-");
        }
        ticket.setPlayType(item.getPlayType());
        ticket.setCount(item.getBetCount());
        ticket.setMoney(item.getOneMoney().multiply(
                new BigDecimal(item.getBetCount())).multiply(
                BigDecimal.valueOf(order.getMultiple())));
        ticket.setMultiple(order.getMultiple());
        ticket.setOrder(order);
        ticket.setItem(item);
        ticket.setSendTicketTime(Calendar.getInstance());
        ticket.setTermNo(order.getTerm().getTermNo());
        ticket.setType(order.getType());
        ticket.setOneMoney(item.getOneMoney());
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticketList.add(ticket);
        return ticketList;
    }

    /**
     * 拆票（我中了）
     */
    @Override
    protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";//大赢家的投注格式
        String formatNumForWZL = "";//我中了的投注格式
        String string = "";
        if (item.getPlayType().equals(PlayType.dt)) {
            formatNum = item.getContent().replace(",", " ").replace("|", "-")
                    .replace("#", "$");
        } else {
            formatNum = item.getContent().replace(",", " ").replace("|", "-");
            string = changeToWZLContent(item.getContent());
            if (item.getBetCount() == 1) {
            	
                formatNumForWZL = "00-01-" + string;
			}
            else {
            	formatNumForWZL = "00-02-" + string;
			}
        }
        ticket.setPlayType(item.getPlayType());
        ticket.setCount(item.getBetCount());
        ticket.setMoney(item.getOneMoney().multiply(
                new BigDecimal(item.getBetCount())).multiply(
                BigDecimal.valueOf(order.getMultiple())));
        ticket.setMultiple(order.getMultiple());
        ticket.setOrder(order);
        ticket.setItem(item);
        ticket.setSendTicketTime(Calendar.getInstance());
        ticket.setTermNo(order.getTerm().getTermNo());
        ticket.setType(order.getType());
        ticket.setOneMoney(item.getOneMoney());
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticket.setBetContent(formatNumForWZL);
        ticket.setIssue(order.getTerm().getTermNo().substring(2).trim()); 
        //ticket.setIssue(order.getTerm().getTermNo().trim());
        ticketList.add(ticket);
        return ticketList;
    }

    
    @Override
    public List<PlanItem> validateUploadFile(String filePath, 
    		BigDecimal oneMoney, int multiple, BigDecimal totalMoney) throws Exception
    {
    	int sumAmount = 0;
    	List<PlanItem> list = new ArrayList<PlanItem>();
    	
    	try {
        	String [] file_codes = FileUtils.readFile(filePath).split("\n");
        	String [] firstSentence = file_codes[0].split("\\s+");
        	
        	//一彩票软件生成的文件格式验证
        	if(firstSentence[0].equals("369cai") && firstSentence[1].equals("letou") && 
        	    firstSentence[2].equals("ver1"))  
        	{
        		int fileBetNum = Integer.parseInt(firstSentence[3]);
        		int fileRandomN = Integer.parseInt(firstSentence[4]);
        		
        		if(fileBetNum != file_codes.length-1) {
        			throw new RuntimeException("文件格式验证失败，首行注数与文件中注数不一致，请联系客服");
        		}
        		
        		for (int i = 1; i <= fileBetNum; i++) {
        			String code = "fs-";
        			file_codes[i] = file_codes[i].trim();
        			String [] myBetStr = file_codes[i].split("\\s+");
        			
        			if(myBetStr.length != 7) {
        				throw new RuntimeException("文件格式验证失败");
        			}
        			
        			for (int j = 0; j < myBetStr.length-3; j++) {
        				code += number2String(Integer.parseInt(myBetStr[j]) - fileRandomN) + ",";
        			}
        			
        			code += number2String(Integer.parseInt(myBetStr[myBetStr.length-3]) - fileRandomN) + "|";
        			//FIXME 前5个需要减去随机数 后两个不需要减随机数
        			code += number2String(Integer.parseInt(myBetStr[myBetStr.length-2])) + ",";
        			code += number2String(Integer.parseInt(myBetStr[myBetStr.length-1]));
    				
        			try {
	                    PlanItem planitem = perseCodeStringTOPlanItem(code, oneMoney);
	                    sumAmount += planitem.getBetCount();
	                    list.add(planitem);
        			}
                    catch (Exception e) {
                        throw e;
                    }
                }
        	}
        	//普通文件格式验证
        	else {
            	for (String code : file_codes) {
            		code = "fs-" + code;
                    try {
                        PlanItem planitem = perseCodeStringTOPlanItem(code, oneMoney);
                        sumAmount += planitem.getBetCount();
                        list.add(planitem);
                    }
                    catch (Exception e) {
                        throw e;
                    }
                }
        	}
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "\n文件上传失败,请联系客服!");
        }
        
        if (oneMoney.intValue() * multiple * sumAmount != totalMoney.intValue()
                || totalMoney.intValue() <= 0) {
            throw new RuntimeException("方案金额错误, 文件内容金额与您填写金额不符!\n文件上传失败,请联系客服!");
        }
        
        return list;
    }

    @Override
    public boolean validataBetNum(String result)
    {
        String[] codestr = result.split("-");

        if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1]
                    .matches("^([0-3][0-9],){4,34}[0-3][0-9]\\|([0-1][0-9],){1,11}[0-1][0-9]");
        }
        // 05,17,18,30#06|06#07
        if (PlayType.dt.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1]
                    .matches("^[0-3][0-9](,[0-3][0-9]){0,4}#[0-3][0-9](,[0-3][0-9]){0,33}\\|[0-1][0-9]#[0-1][0-9](,[0-1][0-9]){0,10}");
        }

        if (PlayType.dlt_sxlfs.equals(PlayType.valueOf(codestr[0]))) {
            return !codestr[1].matches("^[0-1][0-9](,[0-1][0-9]){1,11}");
        }

        return false;
    }

    @Override
    public int validataBetCount(String code)
    {
        int count = 0;
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            int nr = codestr[0].split("\\,").length;
            int nb = codestr[1].split("\\,").length;
            count = MathUtil.getCombinationCount(nr, 5)
                    * MathUtil.getCombinationCount(nb, 2);
        }
        // 05,17,18,30#06|06#07
        if (PlayType.dt.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            String[] coderdtr = codestr[0].split("#");
            String[] coderdtb = codestr[1].split("#");
            int nrd = coderdtr[0].split("\\,").length;
            int nrt = coderdtr[1].split("\\,").length;
            int nbd = coderdtb[0].split("\\,").length;
            int nbt = coderdtb[1].split("\\,").length;
            count = MathUtil.getCombinationCount(nrt, 5 - nrd)
                    * MathUtil.getCombinationCount(nbt, 2 - nbd);
        }

        if (PlayType.dlt_sxlfs.equals(PlayType.valueOf(code.split("-")[0]))) {
            int codenum = code.split("-")[1].split("\\,").length;

            count = MathUtil.getCombinationCount(codenum, 2);
        }

        return count;
    }

    /**
     * 判断重复
     * */
    @Override
    public boolean validataRepeat(String code)
    {
        if (PlayType.fs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codeStr = code.split("-")[1].split("\\|");
            if (MathUtil.repeatString(codeStr[0], 1, 35)
                    && MathUtil.repeatString(codeStr[1], 1, 12))
                return true;

        }

        if (PlayType.dt.equals(PlayType.valueOf(code.split("-")[0]))) {
            String[] codestr = code.split("-")[1].split("\\|");
            String coderdtr = codestr[0].replace("#", ",");
            String coderdtb = codestr[1].replace("#", ",");
            if (MathUtil.repeatString(coderdtr, 1, 35)
                    && MathUtil.repeatString(coderdtb, 1, 12)) {
                return true;
            }

        }
        if (PlayType.dlt_sxlfs.equals(PlayType.valueOf(code.split("-")[0]))) {
            String codestr = code.split("-")[1];
            if (MathUtil.repeatString(codestr, 1, 12)) {
                return true;
            }

        }
        return false;
    }

    /*
     * 开奖
     * 
     * @see
     * com.xsc.lottery.handle.BaseLotteryHandle#checkAllOrderWin(com.xsc.lottery
     * .entity.business.Ticket, com.xsc.lottery.entity.business.PrizeLevel)
     */
    @Override
    public List<WinDescribeTicket> checkAllOrderWin(Ticket ticket,
            List<PrizeLevel> prizeLevels)
    {
        Map<String, PrizeLevel> bingoMap = new HashMap<String, PrizeLevel>();
        for (PrizeLevel pr : prizeLevels) {
            bingoMap.put(pr.getName(), pr);
        }
        String result = prizeLevels.get(0).getTerm().getResult();
        DltBingoCheck dbc = new DltBingoCheck();
        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
        return list;
    }
    
    @Override
	public void parseXML(Element element) throws Exception
    {
    	throw new RuntimeException("暂不提供");
	}
    
    public LotteryTerm fetchPrize(LotteryTerm term) throws Exception
    {
        FetchlDataUtil fetchl = new FetchlDataUtil();
        Iterator<String> fetchResutl = fetchl.parserHtml(Configuration.getInstance().getFormatValue(FETHCHKEY,term.getTermNo().substring(2)), HTML.Tag.TR).iterator();
        String str_0, result = "";
        while (fetchResutl.hasNext())
        {
            str_0 = fetchResutl.next().trim();
            if(str_0.indexOf("开奖日期") > -1)
            {
            	//String str = "开奖日期：2013年9月28日 兑奖截止日期：2013年11月26日";
            	String dateStr = str_0.split("兑")[0].substring(5, 16).trim();
            	System.out.println(dateStr);
            	DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            	term.setOrderResult(format1.format(format.parse(dateStr)));
            	System.out.println(format1.format(format.parse(dateStr)));
            }
            else if (str_0.indexOf("开奖号码") > -1)
            {
                for (int i = 0; i < 5; i++)
                {
                    str_0 = fetchResutl.next().trim();
                    if (i == 4)
                        result = result + str_0;
                    else
                        result = result + str_0 + ",";
                }
                term.setResult(result + "%23" + fetchResutl.next().trim() + ","+ fetchResutl.next().trim());
            } 
            else if(str_0.indexOf(">>") > -1)
            {
            	break;
            }
        }
        return term;
    }
    
    public static void main(String[] args) throws Exception
    {
    	_dltHandle gameHandle = new _dltHandle();
    	for (int i = 2013113; i <= 2013141; i++)
    	{
    		LotteryTerm term = new LotteryTerm();
    		term.setTermNo(String.valueOf(i));
    		term = gameHandle.fetchPrize(term);
    		String url = "http://chart.yicp.com/receive?date="+term.getOrderResult()+"&source=yicp.com&cptype=cjdlt&qishu="+i+"&hm="+term.getResult()+"";
    		String response = NetWorkUtil.sendRequest(url, "POST");
    		System.out.println("响应信息："+response);
		}
        /*LotteryTerm term = new LotteryTerm();
         term.setTermNo("10110");
         _dltHandle d = new _dltHandle();
         System.out.println(d.validataBetCount("dt-#06,07,31,32,21,11|06#07"));*/
        /*
         * String result = ",05,067";
         * System.out.println(MathUtil.getCombinationCount(4, 2));
         * System.out.println(result.split("\\,")[0].length() * 1);
         * System.out.println(result
         * .matches("^[0-9]{1,10}\\,[0-9]{1,10}\\,[0-9]{1,10}"));
         */

        // try {
        // term = d.fetchPrizeLevel(term);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        /*
         * FetchlDataUtil fetchl = new FetchlDataUtil(); try { List<String>
         * fetchResutl =fetchl.parserHtml(
         * "http://500wan.com/static/info/kaijiang/shtml/dlt/10070.shtml",
         * HTML.Tag.TR); for(String cont : fetchResutl)
         * System.out.println("///----------"+cont); } catch (Exception e) { //
         */
        // System.out.println("2010558".substring(2));
        //System.out.println("2010558".substring(2));
    }
    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.split("\\|");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	String sortString = sort(temp0[i]);
        	if (i == 0) {
				string += sortString; 
			}
        	else {
    			string = string + "#" + sortString;
			}
		}
    	return string;
    }
    
    @Override
    public String sort(String content)
    {
    	String[] s = content.split(",");
    	int[] nums = new int[s.length];
    	int temp = 0;
    	String use = "";
    	String ss = "";
    	for (int i = 0; i < s.length; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
    	for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
			if (nums[i] < 10) {
				ss = "0" + nums[i];
			}
			else {
				ss = "" + nums[i];
			}
			if (i == nums.length - 1) {
				use = use + ss;
			}
			else {
				use = use + ss + ",";
			}
		}
    	return use;
    }

	@Override
	protected List<Ticket> unpackTicketForXG(Order order, PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}
}
