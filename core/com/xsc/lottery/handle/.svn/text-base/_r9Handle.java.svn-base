package com.xsc.lottery.handle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.xsc.lottery.dyj.sendticket.bingocheck.R9BingoCheck;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.util.FetchlDataUtil;
import com.xsc.lottery.util.FileUtils;
import com.xsc.lottery.util.NetWorkUtil;

@Component
public class _r9Handle extends BaseLotteryHandle
{

    /**从大赢家获取开奖数据*/
    @Override
    public LotteryTerm fetchPrizeLevel(LotteryTerm term) throws Exception
    {
        String fetchURl = "http://dc.zs310.com/qh/zcsfc.html";
        String resultString = NetWorkUtil.getHttpUrl(fetchURl, "", "");
        JSONObject jsonObject = JSONObject.fromObject(resultString);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("issues"));
        
        for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			
			if (term.getTermNo().substring(2).equals(object.getString("issue"))) {
//				{"bflast":"","dzlast":"2012-09-16 20:01:18","iscur":"0","issue":"12119","kjlast":"2012-09-20 04:41:01",
//				"kjrs":"122:82857;4127:1049;20901:549","kjst":"1","saletimeb":"2012-09-16 20:00:00","saletimee":"2012-09-18 23:00:00"},
				if (object.getString("kjst").equals("1")) {
					List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
			        String urlString = "http://dc.zs310.com/dz/zcsfc/"
		                + term.getTermNo().substring(2) + ".html";
			        String result = NetWorkUtil.getHttpUrl(urlString, "", "");
			        System.out.println(result);
			        
			        JSONArray json = JSONArray.fromObject(result);
					JSONObject jsonmatch = null;
					String ret = "";
					for ( int j = 0 ; j<json.size(); j++){
	//					{ "rs" : "" , "score" : "" , "matchkey" : "12120_1" , "issue" : "12120" , "st" : "0" , "id" : 1 , "dt" : "2012-09-21 01
	//						:00:00" , "name" : "欧洲联赛" , "home" : "年轻人" , "awary" : "利物浦" , "oh" : "2.71" , "od" : "3.17" , "oa" : "2.50"}, 
						jsonmatch = json.getJSONObject(j);
						if (jsonmatch.getString("st").equals("1")) {
							ret += "*,";
						}
						else {
							ret += jsonmatch.getString("rs") + ",";
						}
						
					}
					term.setResult(ret.substring(0, ret.length() - 1));
					
					String jiangjiString = object.getString("kjrs");
					String[] jiangji = jiangjiString.split(";");
					   
			        prizeLevels.add(new PrizeLevel(1, term, Integer
	                .parseInt(jiangji[2].split(":")[0]), "任九", new BigDecimal(jiangji[2].split(":")[1]),
	                BigDecimal.ZERO));
					
	                term.setPrizeLevels(prizeLevels);
				}
			}
		}
        return term;
    }

	
	

    public LotteryTerm fetchPrizeLevel1(LotteryTerm term) throws Exception
    {
		String fetchURl = "http://500wan.com/static/info/kaijiang/shtml/sfc/"
		        + term.getTermNo().substring(2) + ".shtml";
		FetchlDataUtil fetchl = new FetchlDataUtil();
		Iterator<String> result = fetchl.parserHtml(fetchURl, HTML.Tag.TR)
		        .iterator();
		String resultStr, openResult = "";
		List<PrizeLevel> prizeLevels = new ArrayList<PrizeLevel>(16);
		
		while (result.hasNext()) {
		    resultStr = result.next();
		    if(resultStr.trim().indexOf("兑奖截止日期：")>-1)
		    {
		        for (int i = 0; i < 14; i++) {
		            resultStr = result.next();
		            if (i == 13) {
		                openResult = openResult + resultStr;
		            }
		            else {
		                openResult = openResult + resultStr + ",";
		            }
		        }
		        openResult+="-";
		        for (int i = 0; i < 14; i++) {
		            resultStr = result.next();
		            if (i == 13) {
		                openResult = openResult + resultStr;
		            }
		            else {
		                openResult = openResult + resultStr + ",";
		            }
		        }
		        term.setResult(openResult);
		 
		    }
		    else if (resultStr.trim().indexOf("本期任九销量：") > -1) {
		        term.setTotalSale(new BigDecimal(result.next().replaceAll("元",
		                "").replaceAll(",", "").replace(":", "")));
		       
		    }
		    else if (resultStr.trim().indexOf("足彩胜负奖池滚存：") > -1) {
		        term.setPrizePool(new BigDecimal(result.next().replaceAll(
		                "元", "").replaceAll(",", "")));
		    }
		    else if (resultStr.equals("任九")) {
		        prizeLevels.add(new PrizeLevel(1, term, Integer
		                .parseInt(result.next()), "任九", new BigDecimal(
		                		result.next().replaceAll(",", "")),
		                BigDecimal.ZERO));
		       
		    }
		}
		term.setPrizeLevels(prizeLevels);
		return term;
    }

    @Override
    public LotteryType getLotteryType()
    {
        return LotteryType.足彩任9;
    }

    public List<String> getTermCode(LotteryTerm term, int number)
    {
        List<String> list = new ArrayList<String>(number);
        for (int i = 0; i < number; i++) {
            list.add((Integer.valueOf(term.getTermNo()) + 1) + "");
        }
        return list;
    }

    @Override
    public LotteryTerm getNextTerm(LotteryTerm term)
    {
        /*LotteryTerm nextTerm = new LotteryTerm();
        nextTerm.setTermNo((Integer.valueOf(term.getTermNo()) + 1) + "");
        nextTerm.setType(term.getType());
        Calendar start = term.getStopSaleTime();
        Calendar stop = Calendar.getInstance();
        Calendar stopTo = Calendar.getInstance();
        Calendar open = Calendar.getInstance();
        Calendar sendPri = Calendar.getInstance();

        stop = matchArrangeService.findLastMatchByTermno(term.getTermNo());
        // 截止打票时间

        // 合买截止时间
        stopTo = stop;

        // 开奖时间
        open.setTime(stop.getTime());
        open.set(Calendar.HOUR_OF_DAY, 21);
        open.set(Calendar.MINUTE, 30);

        // 合买截止时间
        sendPri.setTime(stop.getTime());
        sendPri.add(Calendar.HOUR_OF_DAY, 5);

        nextTerm.setStartSaleTime(start);
        nextTerm.setStopSaleTime(stop);
        nextTerm.setOpenPrizeTime(open);
        nextTerm.setStopTogetherSaleTime(stopTo);
        nextTerm.setSendPrizeTime(sendPri);
        return nextTerm;*/
    	
    	return null;
    }

    @Override
    public PlanItem perseCodeStringTOPlanItem(String code, BigDecimal oneMoney)
            throws Exception
    {
        PlanItem item = new PlanItem();
        try {
            if (validataBetNum(code)) {
                throw new Exception("足彩任九场投注号码格式错误!");
            }
        }
        catch (Exception e) {
            throw new Exception("足彩任九场投注号码格式错误!");
        }
        item.setBetCount(validataBetCount(code));
        item.setContent(code.split("-")[1]);
        item.setPlayType(PlayType.valueOf(code.split("-")[0]));
        item.setOneMoney(oneMoney);
        return item;
    }

    @Override
    protected List<Ticket> unpackTicket(Order order, PlanItem item)
    {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        Ticket ticket = new Ticket();
        String formatNum = "";
        if (item.getPlayType().equals(PlayType.ds)
                || item.getPlayType().equals(PlayType.fs)) {
            formatNum = item.getContent();
        }
        ticket.setCount(item.getBetCount());
        ticket.setMoney(item.getOneMoney().multiply(
                new BigDecimal(item.getBetCount())).multiply(
                BigDecimal.valueOf(order.getMultiple())));
        ticket.setMultiple(order.getMultiple());
        ticket.setItem(item);
        ticket.setOrder(order);
        ticket.setSendTicketTime(Calendar.getInstance());
        ticket.setTermNo(order.getTerm().getTermNo());
        ticket.setType(order.getType());
        ticket.setContent(formatNum);
        ticket.setPlayType(item.getPlayType());
        ticket.setType(order.getType());
        ticketList.add(ticket);
        return ticketList;
    }

    @Override
    protected List<Ticket> unpackTicketForWZL(Order order, PlanItem item)
    {
    	 List<Ticket> ticketList = new ArrayList<Ticket>();
         Ticket ticket = new Ticket();
         String formatNumForWZL = "";//我中了的投注格式
         String string = "";
         string = changeToWZLContent(item.getContent());
         if(item.getPlayType() == PlayType.ds || item.getBetCount() == 1)
         {
         	formatNumForWZL = "00-01-" + string;
         }
         else
         {
         	formatNumForWZL = "00-02-" + string;
         }
         ticket.setCount(item.getBetCount());
         ticket.setMoney(item.getOneMoney().multiply(
                 new BigDecimal(item.getBetCount())).multiply(
                 BigDecimal.valueOf(order.getMultiple())));
         ticket.setMultiple(order.getMultiple());
         ticket.setItem(item);
         ticket.setOrder(order);
         ticket.setSendTicketTime(Calendar.getInstance());
         ticket.setTermNo(order.getTerm().getTermNo());
         ticket.setType(order.getType());
         ticket.setContent(item.getContent());
         ticket.setBetContent(formatNumForWZL);
         ticket.setPlayType(item.getPlayType());
         ticket.setType(order.getType());
         ticket.setIssue(order.getTerm().getTermNo().substring(2));
         ticketList.add(ticket);
         return ticketList;
    }
    
    @Override
    public boolean validataBetNum(String result)
    {
    	  String[] codestr = result.split("-");
           if(codestr.length < 2) {
               return true;
           }
           if (PlayType.fs.equals(PlayType.valueOf(codestr[0]))) {
               //System.out.println(codestr[1]);
               String[] se=codestr[1].split("\\,");
               int number=0;
               for(int i=0;i<se.length;i++)
               {
            	   if(se[i].equals("#"))
            		   number++;
               }
               
               if(number != 5) {
            	   return true;
               }
               
               return !(codestr[1].matches("^(([310]{1,3}|#),){13}([310]{1,3}|#)"));
               							   
           }
           return true;
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
        	if(firstSentence[0].equals("369cai") && firstSentence[1].equals("9c") && 
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
        			
        			if(myBetStr.length != 14) {
        				throw new RuntimeException("文件格式验证失败");
        			}
        			
        			for (int j = 0; j < myBetStr.length - 1; j++) {
        				if(myBetStr[j].equals("#")) {
        					code += myBetStr[j] + ",";
        				}
        				else {
        					code += Integer.parseInt(myBetStr[j]) - fileRandomN + ",";
        				}
        			}
        			
        			if(myBetStr[myBetStr.length-1].equals("#")) {
    					code += myBetStr[myBetStr.length-1];
    				}
    				else {
    					code += Integer.parseInt(myBetStr[myBetStr.length-1]) - fileRandomN;
    				}
    				
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

    /**
     * 开奖
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
        R9BingoCheck dbc = new R9BingoCheck();
        List<WinDescribeTicket> list = dbc.execute(ticket, bingoMap, result);
        return list;
    }

    @Override
    public int validataBetCount(String code)
    {
        int count = 1;
        String codestr = code.split("-")[1];
        String codes[] = codestr.split("\\,");
        for(int i = 0; i < codes.length; i++) {
            if(codes[i].length() > 1 && codes[i]!="#") {
                count = count * codes[i].length();
            }
        }
        return count;
    }

    @Override
    public boolean validataRepeat(String code)
    {
        return false;
    }

    @Override
	public void parseXML(Element element) throws Exception
    {
    	throw new RuntimeException("暂不提供");
	}
    
    static public void main(String argv[])
    {
        //_r9Handle h = new _r9Handle();
        String tes1 = "0,0,0,0,0,0,0,0,10,#,#,#,#,#";
        System.out.println(tes1.matches("^(([310]{1,3}|#),){13}([310]{1,3}|#)"));
        //System.out.println(h.validataBetNum(tes1));
    }
    
    
    @Override
    public String changeToWZLContent(String content)
    {
    	String[] temp0 = content.replaceAll("\\#", "\\_").split(",");//,分割生成的字符串组
    	String string = "";
    	for (int i = 0; i < temp0.length; i++) {
        	StringBuilder sb = new StringBuilder();
        	//String sortString = sort(temp0[i]);
        	String[] temp00 = temp0[i].split("");
			for (int j = 0; j < temp00.length; j++) {
				sb.append(temp00[j]);
				if (j < temp00.length - 1) {
					sb.append(" ");	
				}
			}
			if (i < temp0.length - 1) {
				sb.append(",");
			}
			string += sb.toString().trim();
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
