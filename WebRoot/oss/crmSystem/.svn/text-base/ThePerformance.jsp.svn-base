<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>业务员业绩</title>
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
<h3><strong>业务员业绩</strong></h3>
	<form id="page" action="thePerformance.htm" method="post" >
	
	<br/>
	业务员:<input type="text" name="businessMan" value="${businessMan}">
	统计时间:<input type="text" name="f_sTime" value="<s:date name="f_sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_eTime" value="<s:date name="f_eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
		
		<input type="submit" value="查询" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
		<td>业务员</td>
			<td>累计佣金</td>
			<td>其客户总购彩金额</td>
			<td>其客户总充值金额</td>
			<td>发送邮件数</td>
			<td>发送短信数</td>
		</tr>
		<s:iterator value="thePerformanceLi" status="s" id="rs">
			<tr>
			<td>${rs[0]}</td>
			<td>${rs[1]}</td>
			<td>${rs[2]}</td>
			<td>${rs[3]}</td>
			<td>${rs[4]}</td>
			<td>${rs[5]}</td>
			</tr>
		</s:iterator>
	</table>
	<%--<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${emailLogPage.pageSize}条 共${emailLogPage.totalCount}条记录 第${emailLogPage.pageNo}/${emailLogPage.totalPages}页   
	<s:if test="emailLogPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${emailLogPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="emailLogPage.pageNo>=emailLogPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${emailLogPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${emailLogPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${emailLogPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	--%></form>
</div>
</body>
