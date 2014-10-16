<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>cps推广</title>
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
<h3><strong></strong></h3>
	
	<table border="0" width="100%" align="center">
		<tr>
			<td>活动</td>
			<td>活动名称</td>
			<td>佣金比例</td>
			<td>推广时间</td>
			<td>操作</td>
		</tr>
		<s:iterator value="activitiesPage.result" status="s" id="rs">
			<tr>
				<td>
				<img alt="${rs.name}" src="../../oss/skin/01/images/logo.gif">
				</td>
				<td>${rs.name}</td>
				<td>
				0.05%起
			      </td>
				<td>
				<s:date name="#rs.startTime" format="yyyy-MM-dd HH:mm"/>/<s:date name="#rs.endTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td><input type="button"  value="我要推广"></td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
