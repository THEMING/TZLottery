package com.xsc.lottery.ntalker.action;

import java.util.Calendar;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.util.DateUtil;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings( { "serial" })
@Scope("prototype")
@Controller("Other.queryAct")
public class QueryAction extends LotteryClientBaseAction
{
	private String queryResult;
	private String src;
	private String startTime;
	private String endTime;
	private String adid;
	
	
	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	@Autowired
    private LotteryOrderService lotteryOrderService;
	
	public String index()
	{
		
		if(src != null) {
			if(src.equals("ntkad")) {
				generateXMLQueryResultForNTalker();
			} else
				return FAILURE;
		}
		return SUCCESS;
	}
	
	private void generateQueryResultForNTalker()
	{
		List<WalletLog> list = lotteryOrderService.finddWalletLog("1", startTime, "finish");
		queryResult = "";
		for(WalletLog walletLog : list) {
			queryResult += walletLog.getPid();
			queryResult += "||" + DateUtil.toTimeStamp1(walletLog.getTime());
			queryResult += "||" + walletLog.getSerialNumber();
			queryResult += "||" + "1";
			queryResult += "||" + "1";
			queryResult += "||" + "caipiao";
			queryResult += "||" + "1";
			queryResult += "||" + walletLog.getOutMoney();
			queryResult += "\n";
		}
	}
	private void generateXMLQueryResultForNTalker()
	{
		
		List<WalletLog> list = lotteryOrderService.finddWalletLog(adid, startTime, "finish");
		Document document = DocumentHelper.createDocument();
	     Element ordersElement = document.addElement("orders");
	    for(WalletLog walletLog : list) {
	    	Element orderElement =  ordersElement.addElement("order");
	    	Element adidElement=orderElement.addElement("adid");
	    	if(walletLog.getAdid()!=null)
	    		adidElement.setText(walletLog.getAdid());
	    	else
	    		adidElement.setText("29");
	    	Element pidElement=orderElement.addElement("pid");
	    	if(walletLog.getPid()!=null)
	    		pidElement.setText(walletLog.getPid());
	    	else
	    		pidElement.setText("19");
	    	Element order_time=orderElement.addElement("order_time");
	    	String date1 =getdate(walletLog.getTime());
	    	order_time.setText(date1);
	    	Element order_no=orderElement.addElement("order_no");
	    	order_no.setText(walletLog.getSerialNumber());
	    	Element prod_no=orderElement.addElement("prod_no");
	    	prod_no.setText("1");
	    	Element prod_name=orderElement.addElement("prod_name");
	    	prod_name.setText("caipiao");
	    	Element prod_type=orderElement.addElement("prod_type");
	    	prod_type.setText("1");
	    	Element amount=orderElement.addElement("amount");
	    	amount.setText("1");
	    	Element price=orderElement.addElement("price");
	    	price.setText(walletLog.getOutMoney().toString());
	    }
	    queryResult=document.asXML();
	     
	}
	
	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	public String getEndTime() {
		return endTime;
	}



	public String getQueryResult()
	{
		return queryResult;
	}

	public void setQueryResult(String queryResult)
	{
		this.queryResult = queryResult;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getdate( Calendar  date) 
    {
		String str;
		Calendar date1 = date;
		str = "" + date.get(Calendar.YEAR);
		
		int ii = date1.get(Calendar.MONTH) + 1;
		if (ii < 10) {
			str = str + "0" + ii;
		}
		else {
			str = str + ii;
		}
		
		int m = date1.get(Calendar.DAY_OF_MONTH);
		if (m < 10) {
			str = str + "0" + m;
		}
		else {
			str = str + m;
		}
		return str;
	}
}
