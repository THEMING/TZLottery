package com.xsc.lottery.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.PayOutRequest;
import com.xsc.lottery.service.business.PayOutRequestService;
import com.xsc.lottery.util.DateUtil;

/**
 * purchase action
 */

@Scope("prototype")
@Controller("drawings.query")
@SuppressWarnings({ "unused", "serial" })
public class DrawingsAction extends LotteryClientBaseAction
{	
	private int index;
	
	private int size;
	
	private String scope;
	
	private String processFlag;
	
	private String status;
	
	private String statusDesc;
	
	@Autowired
	private PayOutRequestService payOutRequestService;
	
	public String index()
	{
		return SUCCESS;
	}
	
	//分页查询数据
	public String queryDrawings()
	{
		JSONObject scopes = JSONObject.fromObject(scope);
		JSONObject jsonObject = new JSONObject();
		try {
			Calendar stime = null;
			if (scopes.containsKey("startTime")) {
				String stimeString = scopes.getString("startTime");			
				if (StringUtils.isNotBlank(stimeString)) {
					stime = DateUtil.parse(stimeString, "yyyy-MM-dd");
				}
			}
			Calendar etime = null;
			if (scopes.containsKey("endTime")) {
				String etimeString = scopes.getString("endTime");
				if (StringUtils.isNotBlank(etimeString)) {
					etime = DateUtil.parse(etimeString, "yyyy-MM-dd");
				}
			}
			int status = 0;
			if (scopes.containsKey("ORDER_STATE")) {
				status = Integer.parseInt(scopes.getString("ORDER_STATE"));		
			}
			int progressFlag = 5;
			if (scopes.containsKey("PROCESS_FLAG")) {
				progressFlag = Integer.parseInt(scopes.getString("PROCESS_FLAG"));		
			}
			String realname = "";
			if (scopes.containsKey("BANK_ACCOUNT_NAME")) {
				realname = scopes.getString("BANK_ACCOUNT_NAME");		
			}
			String bankCard = "";
			if (scopes.containsKey("BANK_ACCOUNT_NUMBER")) {
				bankCard = scopes.getString("BANK_ACCOUNT_NUMBER");
			}
			String openSpace = "";
			if (scopes.containsKey("BANK_NAME")) {
				openSpace = scopes.getString("BANK_NAME");
			}
			String city = "";
			if (scopes.containsKey("BANK_AREA")) {
				city = scopes.getString("BANK_AREA");
			}
			String tradeNo = "";
			if (scopes.containsKey("ORDER_SERIAL_NUMBER")) {
				tradeNo = scopes.getString("ORDER_SERIAL_NUMBER");
			}
			BigDecimal money = BigDecimal.ZERO;
			if (scopes.containsKey("AMOUNT_OF_DEBIT")) {
				String moneyString = scopes.getString("AMOUNT_OF_DEBIT");
				if (StringUtils.isNotBlank(moneyString)) {
					double moneyTemp = Double.parseDouble(moneyString);
					money = BigDecimal.valueOf(moneyTemp);
				}
			}
			Calendar order_time = null;
			if (scopes.containsKey("ORDER_STATE_TIME")) {
				String stimeString = scopes.getString("ORDER_STATE_TIME");			
				if (StringUtils.isNotBlank(stimeString)) {
					order_time = DateUtil.parse(stimeString, "yyyy-MM-dd HH:mm:ss:SSS");
				}
			}
			//分页查询得到的数据
			List<PayOutRequest> list = payOutRequestService.getRecordsByRestrications(stime, etime, bankCard, realname, openSpace, city, tradeNo, money, index, size, status, progressFlag, null);
			//根据条件查询出的所有的记录
			List<PayOutRequest> allList = payOutRequestService.getRecordsByRestrications(stime, etime, bankCard, realname, openSpace, city, tradeNo, money, status, progressFlag, null);
			int listSize = list.size();
			Calendar return_time = null;
			if (listSize > 0) {
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				JSONArray jsonArray = new JSONArray();
				BigDecimal pageMoney = BigDecimal.ZERO;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
				for (int i = 0; i < listSize; i++) {
					JSONObject jObject = new JSONObject();
					PayOutRequest payOutRequest = list.get(i);
					BackMoneyRequest bmr = payOutRequest.getBackMoneyRequest();
					jObject.put("BANK_ACCOUNT_NUMBER", bmr.getBankCard());
					jObject.put("BANK_ACCOUNT_NAME", bmr.getRealName());//用户的真实姓名
					jObject.put("AMOUNT_OF_DEBIT", payOutRequest.getMoney());//写在PayOutRequest中的数据   代表真正往用户银行卡打的钱数   是除去手续费的(看手续费怎么处理)
					jObject.put("ORDER_SERIAL_NUMBER", payOutRequest.getYURREF());//订单号
					String time = df.format(payOutRequest.getTime().getTime());
					jObject.put("ORDER_TIME", time);
					jObject.put("REMIND_MOBILE", bmr.getCustomer().getMobileNo());
					jObject.put("SPID", bmr.getCustomer().getId());
					jObject.put("SUMMARY", "彩票直付");
					jObject.put("BANK_NAME", payOutRequest.getCRTBNK());//收方开户行
					jObject.put("BANK_AREA", payOutRequest.getCTYCOD());//开户城市名称
					String statusTime = df.format(payOutRequest.getStateTime().getTime());
					jObject.put("ORDER_STATE_TIME", statusTime);//记录当前状态修改时间
					jObject.put("ORDER_STATE_DESC", payOutRequest.getStateDesc());
					jObject.put("BANK", bmr.getBank());
					jsonArray.add(jObject);
					pageMoney = pageMoney.add(payOutRequest.getMoney());
				}
				jsonObject.put("jsonData", jsonArray);
				BigDecimal totalMoney = BigDecimal.ZERO;
				for (int i = 0; i < allList.size(); i++) {
					totalMoney = totalMoney.add(allList.get(i).getMoney());
				}
				PayOutRequest outRequest = allList.get(allList.size() - 1);
				jsonObject.put("returnTime", df.format(outRequest.getTime().getTime()));
				jsonObject.put("total", allList.size());
				jsonObject.put("pageMoney", pageMoney);
				jsonObject.put("totalMoney", totalMoney);
			}
			else{
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				jsonObject.put("jsonData", null);
				jsonObject.put("total", 0);
				jsonObject.put("pageMoney", 0);
				jsonObject.put("totalMoney", 0);
				jsonObject.put("returnTime", "");
			}
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("code", -1);
			jsonObject.put("desc", e.getMessage());
			jsonObject.put("jsonData", null);
			jsonObject.put("total", 0);
			jsonObject.put("pageMoney", 0);
			jsonObject.put("totalMoney", 0);
			setJsonString(jsonObject.toString());
			return AJAXJSON;
		}
		setJsonString(jsonObject.toString());
		return AJAXJSON;
	}
	
	
	
	public String queryallDrawings() {
		JSONObject scopes = JSONObject.fromObject(scope);
		JSONObject jsonObject = new JSONObject();
		try {
			Calendar stime = null;
			if (scopes.containsKey("startTime")) {
				String stimeString = scopes.getString("startTime");			
				if (StringUtils.isNotBlank(stimeString)) {
					stime = DateUtil.parse(stimeString, "yyyy-MM-dd");
				}
			}
			Calendar etime = null;
			if (scopes.containsKey("endTime")) {
				String etimeString = scopes.getString("endTime");
				if (StringUtils.isNotBlank(etimeString)) {
					etime = DateUtil.parse(etimeString, "yyyy-MM-dd");
				}
			}
			int status = 0;
			if (scopes.containsKey("ORDER_STATE")) {
				status = Integer.parseInt(scopes.getString("ORDER_STATE"));		
			}
			int progressFlag = 5;
			if (scopes.containsKey("PROCESS_FLAG")) {
				progressFlag = Integer.parseInt(scopes.getString("PROCESS_FLAG"));		
			}
			String realname = "";
			if (scopes.containsKey("BANK_ACCOUNT_NAME")) {
				realname = scopes.getString("BANK_ACCOUNT_NAME");		
			}
			String bankCard = "";
			if (scopes.containsKey("BANK_ACCOUNT_NUMBER")) {
				bankCard = scopes.getString("BANK_ACCOUNT_NUMBER");
			}
			String openSpace = "";
			if (scopes.containsKey("BANK_NAME")) {
				openSpace = scopes.getString("BANK_NAME");
			}
			String city = "";
			if (scopes.containsKey("BANK_AREA")) {
				city = scopes.getString("BANK_AREA");
			}
			String tradeNo = "";
			if (scopes.containsKey("ORDER_SERIAL_NUMBER")) {
				tradeNo = scopes.getString("ORDER_SERIAL_NUMBER");
			}
			BigDecimal money = BigDecimal.ZERO;
			if (scopes.containsKey("AMOUNT_OF_DEBIT")) {
				String moneyString = scopes.getString("AMOUNT_OF_DEBIT");
				if (StringUtils.isNotBlank(moneyString)) {
					double moneyTemp = Double.parseDouble(moneyString);
					money = BigDecimal.valueOf(moneyTemp);
				}
			}
			Calendar order_time = null;
			if (scopes.containsKey("ORDER_STATE_TIME")) {
				String stimeString = scopes.getString("ORDER_STATE_TIME");			
				if (StringUtils.isNotBlank(stimeString)) {
					order_time = DateUtil.parse(stimeString, "yyyy-MM-dd HH:mm:ss:sss");
				}
			}
			List<PayOutRequest> allList = payOutRequestService.getRecordsByRestrications(stime, etime, bankCard, realname, openSpace, city, tradeNo, money, status, progressFlag, null);
			int listSize = allList.size();
			if (listSize > 0) {
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				JSONArray jsonArray = new JSONArray();
				BigDecimal pageMoney = BigDecimal.ZERO;
				BigDecimal totalMoney = BigDecimal.ZERO;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
				for (int i = 0; i < listSize; i++) {
					JSONObject jObject = new JSONObject();
					PayOutRequest payOutRequest = allList.get(i);
					BackMoneyRequest bmr = payOutRequest.getBackMoneyRequest();
					jObject.put("BANK_ACCOUNT_NUMBER", bmr.getBankCard());
					jObject.put("BANK_ACCOUNT_NAME", bmr.getRealName());//用户的真实姓名
					jObject.put("AMOUNT_OF_DEBIT", payOutRequest.getMoney());//写在PayOutRequest中的数据   代表真正往用户银行卡打的钱数   是除去手续费的(看手续费怎么处理)
					jObject.put("ORDER_SERIAL_NUMBER", payOutRequest.getYURREF());//订单号
					String time = df.format(payOutRequest.getTime().getTime());
					jObject.put("ORDER_TIME", time);
					jObject.put("REMIND_MOBILE", bmr.getCustomer().getMobileNo());
					jObject.put("SPID", bmr.getCustomer().getId());
					jObject.put("SUMMARY", "彩票直付");
					jObject.put("BANK_NAME", payOutRequest.getCRTBNK());//收方开户行
					jObject.put("BANK_AREA", payOutRequest.getCTYCOD());//开户城市名称
					String statusTime = df.format(payOutRequest.getStateTime().getTime());
					jObject.put("ORDER_STATE_TIME", statusTime);//记录当前状态修改时间
					jObject.put("ORDER_STATE_DESC", payOutRequest.getStateDesc());
					jObject.put("BANK", bmr.getBank());
					jsonArray.add(jObject);
					pageMoney = pageMoney.add(payOutRequest.getMoney());
				}
				jsonObject.put("jsonData", jsonArray);
				jsonObject.put("total", allList.size());
				jsonObject.put("pageMoney", pageMoney);
				jsonObject.put("totalMoney", pageMoney);
				PayOutRequest outRequest = allList.get(listSize - 1);
				jsonObject.put("returnTime", df.format(outRequest.getTime().getTime()));			
			}
			else{
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				jsonObject.put("jsonData", null);
				jsonObject.put("total", 0);
				jsonObject.put("pageMoney", 0);
				jsonObject.put("totalMoney", 0);
				jsonObject.put("returnTime", "");
			}
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("code", -1);
			jsonObject.put("desc", e.getMessage());
			jsonObject.put("jsonData", null);
			jsonObject.put("total", 0);
			jsonObject.put("pageMoney", 0);
			jsonObject.put("totalMoney", 0);
			setJsonString(jsonObject.toString());
			return AJAXJSON;
		}
		setJsonString(jsonObject.toString());
		return AJAXJSON;
	}
	
	public String details() {
		JSONObject scopes = JSONObject.fromObject(scope);
		JSONObject jsonObject = new JSONObject();
		try {
			String tradeNo = "";
			if (scopes.containsKey("ORDER_SERIAL_NUMBER")) {
				tradeNo = scopes.getString("ORDER_SERIAL_NUMBER");
			}
			PayOutRequest pay = payOutRequestService.getRecordByTradeNo(tradeNo);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (pay != null) {
				JSONObject jObject = new JSONObject();
				jObject.put("BANK_ACCOUNT_NUMBER", pay.getBackMoneyRequest().getBankCard());
				jObject.put("BANK_ACCOUNT_NAME", pay.getBackMoneyRequest().getRealName());//用户的真实姓名
				jObject.put("AMOUNT_OF_DEBIT", pay.getMoney());//写在PayOutRequest中的数据   代表真正往用户银行卡打的钱数   是除去手续费的(看手续费怎么处理)
				jObject.put("ORDER_SERIAL_NUMBER", pay.getYURREF());//订单号
				String time = df.format(pay.getTime().getTime());
				jObject.put("ORDER_TIME", time);
				jObject.put("REMIND_MOBILE", pay.getBackMoneyRequest().getCustomer().getMobileNo());
				jObject.put("SPID", pay.getBackMoneyRequest().getCustomer().getId());
				jObject.put("SUMMARY", "彩票直付");
				jObject.put("BANK_NAME", pay.getCRTBNK());//收方开户行
				jObject.put("BANK_AREA", pay.getCTYCOD());//开户城市名称
				String statusTime = df.format(pay.getStateTime().getTime());
				jObject.put("ORDER_STATE_TIME", statusTime);//记录当前状态修改时间
				jObject.put("ORDER_STATE_DESC", pay.getStateDesc());
				
				JSONArray array = new JSONArray();
				array.add(jObject);
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				jsonObject.put("jsonData", array);
				jsonObject.put("total", 1);
				jsonObject.put("pageMoney", pay.getMoney());
				jsonObject.put("totalMoney", pay.getMoney());
				jsonObject.put("returnTime", "");
			}
			else{
				jsonObject.put("code", -1);
				jsonObject.put("desc", "订单号不存");
				jsonObject.put("jsonData", null);
				jsonObject.put("total", 0);
				jsonObject.put("pageMoney", 0);
				jsonObject.put("totalMoney", 0);
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("code", -1);
			jsonObject.put("desc", e.getMessage());
			jsonObject.put("jsonData", null);
			jsonObject.put("total", 0);
			jsonObject.put("pageMoney", 0);
			jsonObject.put("totalMoney", 0);
			setJsonString(jsonObject.toString());
			return AJAXJSON;
		}
		setJsonString(jsonObject.toString());
		return AJAXJSON;
	}
	
	public String update() {
		JSONObject scopes = JSONObject.fromObject(scope);
		JSONObject jsonObject = new JSONObject();
		try {
			String id = "";
			if (scopes.containsKey("id")) {
				id = scopes.getString("id");		
			}
			
			int status = 0;
			if (scopes.containsKey("status")) {
				status = Integer.parseInt(scopes.getString("status"));		
			}
			int progressFlag = 5;
			if (scopes.containsKey("processFlag")) {
				progressFlag = Integer.parseInt(scopes.getString("processFlag"));		
			}
			String stateDesc = "";
			if (scopes.containsKey("status")) {
				stateDesc = scopes.getString("statusDesc");		
			}
			
			if (id != "") {
			    String ids[]= id.split(",");
			    for(int i=0;i<ids.length;i++){
			    	payOutRequestService.updateStatus(ids[i], status, progressFlag, stateDesc);
			    }
			}
			/*
			PayOutRequest pay = payOutRequestService.getRecordByTradeNo(id);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (pay != null) {
				JSONObject jObject = new JSONObject();
				jObject.put("BANK_ACCOUNT_NUMBER", pay.getBackMoneyRequest().getBankCard());
				jObject.put("BANK_ACCOUNT_NAME", pay.getBackMoneyRequest().getRealName());//用户的真实姓名
				jObject.put("AMOUNT_OF_DEBIT", pay.getBackMoneyRequest().getMoney());//写在PayOutRequest中的数据   代表真正往用户银行卡打的钱数   是除去手续费的(看手续费怎么处理)
				jObject.put("ORDER_SERIAL_NUMBER", pay.getYURREF());//订单号
				String applyTime = df.format(pay.getBackMoneyRequest().getApplyTime().getTime());
				jObject.put("ORDER_TIME", applyTime);
				jObject.put("REMIND_MOBILE", pay.getBackMoneyRequest().getCustomer().getMobileNo());
				jObject.put("SPID", pay.getBackMoneyRequest().getCustomer().getId());
				jObject.put("SUMMARY", "彩票直付");
				jObject.put("BANK_NAME", pay.getCRTBNK());//收方开户行
				jObject.put("BANK_AREA", pay.getCTYCOD());//开户城市名称
				String statusTime = df.format(pay.getStateTime().getTime());
				jObject.put("ORDER_STATE_TIME", statusTime);//记录当前状态修改时间
				
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				jsonObject.put("jsonData", jObject);
			}
			else{
				jsonObject.put("code", -1);
				jsonObject.put("desc", "此id不存在");
				jsonObject.put("jsonData", null);
			}
			*/
			jsonObject.put("code", 0);
			jsonObject.put("desc", "成功");
		} 
		catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("code", -1);
			jsonObject.put("desc", e.getMessage());
			setJsonString(jsonObject.toString());
			return AJAXJSON;
		}
		setJsonString(jsonObject.toString());
		return AJAXJSON;
	}

	public String batching() {
		JSONObject scopes = JSONObject.fromObject(scope);
		System.out.println("================================scopes:"+scopes.toString());
	    System.out.println("=======================ORDER_STATE_TIME:"+scopes.getString("ORDER_STATE_TIME"));
		JSONObject jsonObject = new JSONObject();
		try {
			Calendar stime = null;
			if (scopes.containsKey("startTime")) {
				String stimeString = scopes.getString("startTime");			
				if (StringUtils.isNotBlank(stimeString)) {
					stime = DateUtil.parse(stimeString, "yyyy-MM-dd");
				}
			}
			Calendar etime = null;
			if (scopes.containsKey("endTime")) {
				String etimeString = scopes.getString("endTime");
				if (StringUtils.isNotBlank(etimeString)) {
					etime = DateUtil.parse(etimeString, "yyyy-MM-dd");
				}
			}
			int state1 = 0;
			if (scopes.containsKey("ORDER_STATE")) {
				state1 = Integer.parseInt(scopes.getString("ORDER_STATE"));		
			}
			int progressFlag1 = 5;
			if (scopes.containsKey("PROCESS_FLAG")) {
				progressFlag1 = Integer.parseInt(scopes.getString("PROCESS_FLAG"));		
			}
			String realname = "";
			if (scopes.containsKey("BANK_ACCOUNT_NAME")) {
				realname = scopes.getString("BANK_ACCOUNT_NAME");		
			}
			String bankCard = "";
			if (scopes.containsKey("BANK_ACCOUNT_NUMBER")) {
				bankCard = scopes.getString("BANK_ACCOUNT_NUMBER");
			}
			String openSpace = "";
			if (scopes.containsKey("BANK_NAME")) {
				openSpace = scopes.getString("BANK_NAME");
			}
			String city = "";
			if (scopes.containsKey("BANK_AREA")) {
				city = scopes.getString("BANK_AREA");
			}
			String tradeNo = "";
			if (scopes.containsKey("ORDER_SERIAL_NUMBER")) {
				tradeNo = scopes.getString("ORDER_SERIAL_NUMBER");
			}
			BigDecimal money = BigDecimal.ZERO;
			if (scopes.containsKey("AMOUNT_OF_DEBIT")) {
				String moneyString = scopes.getString("AMOUNT_OF_DEBIT");
				if (StringUtils.isNotBlank(moneyString)) {
					double moneyTemp = Double.parseDouble(moneyString);
					money = BigDecimal.valueOf(moneyTemp);
				}
			}
			Calendar order_time = null;
			if (scopes.containsKey("ORDER_STATE_TIME")) {
				String stimeString = scopes.getString("ORDER_STATE_TIME");			
				if (StringUtils.isNotBlank(stimeString)) {
					order_time = DateUtil.parse(stimeString, "yyyy-MM-dd HH:mm:ss:SSS");
				}
			}
			List<PayOutRequest> allList = payOutRequestService.getRecordsByRestrications(null, null, bankCard, realname, openSpace, city, tradeNo, money, Integer.valueOf(state1), Integer.valueOf(progressFlag1), order_time);
																						//(stime, etime, bankCard, realname, openSpace, city, tradeNo, money, status, progressFlag, order_time)	
			if (allList.size() > 0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < allList.size(); i++) {
					payOutRequestService.updateStatus(allList.get(i).getYURREF(), Integer.valueOf(status), Integer.valueOf(processFlag), statusDesc);
					/*
					PayOutRequest pay = payOutRequestService.getRecordByTradeNo(allList.get(i).getYURREF());
					JSONObject jObject = new JSONObject();
					jObject.put("BANK_ACCOUNT_NUMBER", pay.getBackMoneyRequest().getBankCard());
					jObject.put("BANK_ACCOUNT_NAME", pay.getBackMoneyRequest().getRealName());//用户的真实姓名
					jObject.put("AMOUNT_OF_DEBIT", pay.getBackMoneyRequest().getMoney());//写在PayOutRequest中的数据   代表真正往用户银行卡打的钱数   是除去手续费的(看手续费怎么处理)
					jObject.put("ORDER_SERIAL_NUMBER", pay.getYURREF());//订单号
					String applyTime = df.format(pay.getBackMoneyRequest().getApplyTime().getTime());
					jObject.put("ORDER_TIME", applyTime);
					jObject.put("REMIND_MOBILE", pay.getBackMoneyRequest().getCustomer().getMobileNo());
					jObject.put("SPID", pay.getBackMoneyRequest().getCustomer().getId());
					jObject.put("SUMMARY", "彩票直付");
					jObject.put("BANK_NAME", pay.getCRTBNK());//收方开户行
					jObject.put("BANK_AREA", pay.getCTYCOD());//开户城市名称
					String statusTime = df.format(pay.getStateTime().getTime());
					jObject.put("ORDER_STATE_TIME", statusTime);//记录当前状态修改时间
					jsonArray.add(jObject);
					*/
				}
				jsonObject.put("code", 0);
				jsonObject.put("desc", "成功");
				//jsonObject.put("jsonData", jsonArray);
			}
			else {
				jsonObject.put("code", -1);
				jsonObject.put("desc", "此时间段没有数据");
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("code", -1);
			jsonObject.put("desc", e.getMessage());
			//jsonObject.put("jsonData", null);
			setJsonString(jsonObject.toString());
			return AJAXJSON;
		}
		setJsonString(jsonObject.toString());
		return AJAXJSON;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public PayOutRequestService getPayOutRequestService() {
		return payOutRequestService;
	}

	public void setPayOutRequestService(PayOutRequestService payOutRequestService) {
		this.payOutRequestService = payOutRequestService;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	
	
}
