<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>注册用户查询</title>
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
<h3><strong>注册用户查询</strong></h3>
	<form action="register.htm" method="post" >
	
	<br/>
		注册时间：<input type="text" name="f_sTime" id="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="f_eTime" id="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		<%-- <input type="text" name="stime" id="stime" value="<s:date name="#stime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="etime" id="etime" value="<s:date name="#etime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',readOnly:true});"/>
		 --%><input type="submit" value="查询" /><s:if test="needBack>0"><input type="button" value="返回" onclick="history.back()"/></s:if>
		<br/>
		<br/>
		总注册人数：<font color="red">${sumReg}</font>
		今日注册人数：<font color="red">${sumRegToday}</font>
		
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>账号名</td>
			<td>证件类型</td>
			<td>证件号码</td>
			<td>邮箱</td>
			<td>联系电话</td>
			<td>注册时间</td>
		</tr>
		<s:iterator value="recommenderPage.result" status="s" id="rs">
			<tr>
				<td>
				${rs.nickName}
				</td>
				<td>${rs.credentType}</td>
				<td>
				<s:if test="#rs.credentNo!=''">
				<s:property value="#rs.credentNo.substring(0,12)"/>******
				</s:if>
			      </td>
				<td>
				<s:if test="#rs.email!=''">
				****<s:property value="#rs.email.substring(4)"/>
				</s:if>
				</td>
				<td>
				<s:if test="#rs.mobileNo!=''">
				<s:property value="#rs.mobileNo.substring(0,7)"/>****
				</s:if>
				</td>
				<td><s:date name="#rs.registerTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${recommenderPage.pageSize}条 共${recommenderPage.totalCount}条记录 第${recommenderPage.pageNo}/${recommenderPage.totalPages}页   
	<s:if test="recommenderPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${recommenderPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="recommenderPage.pageNo>=recommenderPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${recommenderPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${recommenderPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${recommenderPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
