<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>我发过的短信</title>
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
		$("#f_sTime").val(date.substr(0,10)+" 00:00:00");
		$("#f_eTime").val(date.substr(0,10)+" 23:59:59");
		$("#gotopay").submit();
	}
	function goToRegister(date){
		$("#f_sTime2").val(date.substr(0,10)+" 00:00:00");
		$("#f_eTime2").val(date.substr(0,10)+" 23:59:59");
		$("#gotoreg").submit();
	}goToRegister
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>我发过的短信</strong></h3>
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
	<form id="page" action="mySendSms.htm" method="post" >
	
	<br/>
	发送时间:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
		短信状态：<s:select list="smsLogStateList" name="smsLogState" id="smsLogState" listValue="text" headerValue="请选择..." headerKey=""></s:select>
		<!-- <input type="text" name="state" id="state" />  -->
		<input type="submit" value="查询" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>接收的客户</td>
			<td>短信内容</td>
			<td>短信状态</td>
			<td>发送时间</td>
			<td>成功时间</td>
		</tr>
		<s:iterator value="smsLogPage.result" status="s" id="rs">
			<tr>
				<%--<td><s:if test="#rs.mobile!=''">
				<s:property value="#rs.mobile.substring(0,7)"/>****
				</s:if></td>
				--%>
				<td>${rs.customer.nickName}</td>
				<td>${rs.content}</td>
				<td>${rs.state.text}</td>
				<td>
				<s:date name="#rs.sendTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:date name="#rs.successTime" format="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${smsLogPage.pageSize}条 共${smsLogPage.totalCount}条记录 第${smsLogPage.pageNo}/${smsLogPage.totalPages}页   
	<s:if test="smsLogPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${smsLogPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="smsLogPage.pageNo>=smsLogPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${smsLogPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${smsLogPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${smsLogPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
