<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>用户提成查询</title>
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../../oss/styles/base.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
var postPath = "<%=request.getContextPath()%>";
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#page").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("#page").submit();
	}
	function goToPayment(date){
		$("#f_sTime").val(date);
		$("#f_eTime").val(date.substr(0,10)+" 23:59:59");
		$("#gotopay").submit();
	}
	function goToRegister(date){
		$("#f_sTime2").val(date);
		$("#f_eTime2").val(date.substr(0,10)+" 23:59:59");
		$("#gotoreg").submit();
	}goToRegister
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>报表查询</strong></h3>
	<form id="gotopay" action="payment.htm" method="post">
		<input type="hidden" id="f_sTime" name="f_sTime">
		<input type="hidden" id="f_eTime" name="f_eTime">
		<input type="hidden" id="needBack" name="needBack" value=1>
	</form>
	<form id="gotoreg" action="register.htm" method="post">
		<input type="hidden" id="f_sTime2" name="f_sTime">
		<input type="hidden" id="f_eTime2" name="f_eTime">
		<input type="hidden" id="needBack" name="needBack" value=1>
	</form>
	<form id="page" action="agentManager.htm" method="post" >
	
	<br/>
		报表时间：<input type="text" name="stime" id="stime" value="${stime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="etime" id="etime" value="${etime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		<%-- <input type="text" name="stime" id="stime" value="<s:date name="#stime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="etime" id="etime" value="<s:date name="#etime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		 --%><input type="submit" value="查询" />
		<br/>
		<br/>
		总注册人数：<font color="red">${sumReg}</font>
		总充值人数：<font color="red">${sumCharge}</font>
		总充值金额：<font color="red">${sumChargeMon}</font>元
		总消费金额：<font color="red">${sumPay}</font>元
		总佣金：<font color="red">${sumComm}</font>元
		
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>报表日期</td>
			<td>佣金(元)</td>
			<td>是否已结算</td>
			<td>结算时间</td>
			<td>注册人数</td>
			<td>消费总额(元)</td>
			<td>消费人数</td>
			<td>充值用户数</td>
			<td>充值金额(元)</td>
		</tr>
		<s:iterator value="cpsDayReportPage.result" status="s" id="rs">
			<tr>
				<td>
				<s:property value="#rs.reportDate.substring(0,10)"/>
				</td>
				<td>${rs.commission}</td>
				<td>
				<s:if test="#rs.isPay==true">
			      是
			      </s:if>
			      <s:if test="#rs.isPay==false">
			      否
			      </s:if>
			      </td>
				<td>
				<s:date name="#rs.payTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:if test="#rs.registerNum>0"><a href="javascript:goToRegister('${rs.reportDate}')"><font color="red">${rs.registerNum}</font></a></s:if><s:else>${rs.registerNum}</s:else>
				</td>
				<td>
				<s:if test="#rs.total>0"><a href="javascript:goToPayment('${rs.reportDate}')"><font color="red">${rs.total}</font></a></s:if><s:else>${rs.total}</s:else>
				</td>
				<td>${rs.salesNum}</td>
				<td>${rs.rechargeNum}</td>
			<td>${rs.rechargeMon}</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${cpsDayReportPage.pageSize}条 共${cpsDayReportPage.totalCount}条记录 第${cpsDayReportPage.pageNo}/${cpsDayReportPage.totalPages}页   
	<s:if test="cpsDayReportPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${cpsDayReportPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="cpsDayReportPage.pageNo>=cpsDayReportPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${cpsDayReportPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${cpsDayReportPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${cpsDayReportPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
