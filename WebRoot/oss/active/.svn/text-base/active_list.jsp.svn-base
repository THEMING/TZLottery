<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>活动列表</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<link href="../styles/base.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script>

</script>
</head>
<body>
 <div class="tab">
<form  action="/oss/active/activitiesMng.htm" method="post">
	<table width="90%">
		<caption class="redbold">活动列表</caption>
  		<tr>
   			<td>活动类型:<s:select list="activityTypes" name="activityType" headerValue="请选择..." headerKey=""/> </td>
   			<td>活动状态:<s:select list="activityStatuses" name="activityStatus" headerValue="请选择..." headerKey=""/> </td>
     		<td height="25" bgcolor="#ffffff"><div align="left">
	  			<input type="submit" value="查询" />
	  		</div></td>
  		</tr>
	</table>
	<br>
	<table >
	  <tr><td colspan="8"></td><td><a href="/oss/active/activitiesMng.htm?action=edit"><span style="color:red">添加></span></a></td></tr>
	  <tr>
	    <td height="25"><div align="center">活动名</div></td>
	     <td height="25"><div align="center">活动类型</div></td>
	     <td height="25"><div align="center">活动状态</div></td>
	     <td height="25"><div align="center">订单状态限制</div></td>
	     <td height="25"><div align="center">购彩额度限制</div></td>
	     <td height="25"><div align="center">加奖额度</div></td>
	    <td height="25"><div align="center">开始时间</div></td>
	    <td height="25"><div align="center">截止时间</div></td>
	    <td height="25"><div align="center">操作</div></td>
	  </tr>
	  	<s:iterator id="rs" value="page.result">
		  <tr>
	      <td height="25" align="center">${rs.name}</td>
	       <td height="25" align="center" >${rs.type}</td>
	       <td height="25" align="center" >${rs.status}</td>
	       <td height="25" align="center" >${rs.orderType}</td>
	       <td height="25" align="center" >${rs.threshold}</td>
	       <td height="25" align="center" >${rs.givenMoney}</td>
	      <td height="25" align="center" ><s:date name="#rs.startTime" format="yyyy-MM-dd HH:mm"/></td>
	      <td height="25" align="center" ><s:date name="#rs.endTime" format="yyyy-MM-dd HH:mm"/></td>
	      <td height="25" align="center" ><a href="/oss/active/activitiesMng.htm?action=edit&id=${rs.id}">修改</a></td>
	      </tr>
	    </s:iterator>
	</table>
	  <table width="90%" border="0" align="center">
	    <tr>
	    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
	    </tr>
	 </table>
</form>
</div>

</body>
