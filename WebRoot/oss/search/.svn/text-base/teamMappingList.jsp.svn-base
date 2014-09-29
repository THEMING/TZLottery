<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<form action="/oss/search/searchMatch.htm?action=teamMappingList" method="post">
		历史队名关键字<input type="text" name="keyWord" />
		赛事类型<input type="text" name="leagueType" />
		<input type="submit" value="查找"></input>
	
		<table border="1" width="100%">
		<tr>
			<td>联赛名称</td>
			<td>历史队名</td>
			<td>竟彩队名</td>
			<td>14场队名</td>
			<td>6场队名</td>
			<td>4场队名</td>
			<td>别名</td>
		</tr>
		<s:iterator value="mappingPage.result">
			<tr>
				<td><s:property value="matchName" /></td>
				<td><s:property value="matchHistoryName" /></td>
				<td><s:property value="matchJCZQName" /></td>
				<td><s:property value="match14CName" /></td>
				<td><s:property value="match6CBName" /></td>
				<td><s:property value="match4CJQName" /></td>
				<td><s:property value="originalTeamName" /></td>
			</tr>
		</s:iterator>
		</table>
		<jsp:include page="/util/page3.jsp"></jsp:include>
	</form>
</body>
</html>