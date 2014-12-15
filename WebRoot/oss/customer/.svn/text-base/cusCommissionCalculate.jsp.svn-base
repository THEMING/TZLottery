<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@ include file="../skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>用户提成计算</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
	function jumpPage(pageNo) {
		$("#pageNoo").val(pageNo);
		$("#formQuery").submit();
	}
	function jumpPage1() {
		$("#pageNoo").val($("#pageNum").val());
		$("#formQuery").submit();
	}
	function queryCommission()
	{
		$("#nname").val($(nickName).val());
		$("#formQuery").submit();
	}
</script>
</head>
<body>
<div class="tab">
	<form action="/oss/customer/queryCusCommission.htm" method="post" id="formQuery">
		<input type="hidden" name="nickName" id="nname" />
		<input type="hidden" name="pageNo" id="pageNoo" />
	</form>
	<form action="/oss/customer/calculateCusCommission.htm" method="post">
	<br/>
		用户昵称：<input type="text" name="nickName" value="${nickName}" id="nickName" />
		选择时间：<input type="text" name="startTime" id="startTime" value="<s:date name="startTime" format="yyyy-MM-dd HH-mm-dd" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
		<input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH-mm-dd" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
		<input type="button" value="查询" onclick="queryCommission();" />
		<input type="submit" value="结算" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>用户名</td>
			<td>姓名</td>
			<td>电话</td>
			<td>提成比率</td>
			<td>提成</td>
			<!-- 
			<td>二级提成</td>
			 -->
		</tr>
		<s:iterator value="customerPage.result" status="s" id="rs">
			<tr>
				<td>${rs.nickName}</td>
				<td>${rs.realName}</td>
				<td>${rs.channel.linkPhone}</td>
				<td>${rs.superRatio}</td>
				<td>${rs.superCommission }</td>
				<!-- 
				<td>${rs.ssuperCommission }</td>
				-->
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${customerPage.pageSize}条 共${customerPage.totalCount}条记录 第${customerPage.pageNo}/${customerPage.totalPages}页   
	<s:if test="customerPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${customerPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="customerPage.pageNo>=customerPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${customerPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${customerPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${customerPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
	
</div>
</body>
