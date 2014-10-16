<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>充值查询</title>
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../../oss/styles/base.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
var postPath = "<%=request.getContextPath()%>";
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("form").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("form").submit();
	}
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>充值查询</strong></h3>
	<form action="recharge.htm" method="post" >
	
	<br/>
		充值时间：<input type="text" name="f_sTime" id="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="f_eTime" id="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		<%-- <input type="text" name="stime" id="stime" value="<s:date name="#stime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="etime" id="etime" value="<s:date name="#etime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		 --%><input type="submit" value="查询" />
		<br/>
		<br/>
		总充值人数：<font color="red">${sumRechargeNum}</font>
		今日充值人数：<font color="red">${sumRechargeNumToday}</font>
		总充值金额：<font color="red">${sumChargeMon}</font>(元)
		今日充值金额：<font color="red">${sumChargeMonToday}</font>(元)
		
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>账号名</td>
			<td>充值金额（元）</td>
			<td>充值时间</td>
		</tr>
		<s:iterator value="walletLogPage.result" status="s" id="walletLog">
			<tr>
				<td>
				${walletLog.wallet.customer.nickName}
				</td>
				<td>${walletLog.inMoney}</td>
				<td>
				<s:date name="#walletLog.time" format="yyyy-MM-dd HH:mm"/>
			      </td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${walletLogPage.pageSize}条 共${walletLogPage.totalCount}条记录 第${walletLogPage.pageNo}/${walletLogPage.totalPages}页   
	<s:if test="walletLogPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${walletLogPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="walletLogPage.pageNo>=walletLogPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${walletLogPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${walletLogPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${walletLogPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
