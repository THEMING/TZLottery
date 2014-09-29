<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>活动列表</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<link href="../styles/base.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script>
	function sub()
	{
		$("#finished").val($("#finish_check").attr("checked"));
	}
</script>
</head>
<body>
<div class="tab">
<form  action="/oss/active/activityDetailMng.htm" method="post">
	<input type="hidden" id="finished" name="finished" value="${finished}"/>
	<table width="90%">
		<caption class="redbold">活动明细列表</caption>
  		<tr>
   			<td>活动明细类型:<s:select list="activityDetailTypes" name="activityDetailType" headerValue="请选择..." headerKey=""/> </td>
     		<td>是否已处理:<input type="checkbox" id="finish_check" <c:if test="${finished}">checked</c:if> onclick="sub()"/> </td>
     		<td height="25" bgcolor="#ffffff"><div align="left">
	  			<input type="submit" value="查询"/>
	  		</div></td>
  		</tr>
	</table>
	<br>
	<table>
	  <tr>
	     <td height="25"><div align="center">活动明细类型</div></td>
	     <td height="25"><div align="center">活动名</div></td>
	     <td height="25"><div align="center">用户</div></td>
	     <td height="25"><div align="center">金额</div></td>
	     <td height="25"><div align="center">处理状态</div></td>
	    <td height="25"><div align="center">操作</div></td>
	  </tr>
	  	<s:iterator id="rs" value="page.result">
		  <tr>
	       <td height="25" align="center" >${rs.actDetailType}</td>
	       <td height="25" align="center" >${rs.activity.name}</td>
	       <td height="25" align="center" >
	       <c:if test="${rs.actDetailType=='注册活动'}"> ${rs.customer.nickName}</c:if>
	       <c:if test="${rs.actDetailType=='购彩活动'}"> ${rs.order.customer.nickName}</c:if>
	       <c:if test="${rs.actDetailType=='充值活动'}"> ${rs.paymentRequest.customer.nickName}</c:if>
        	</td>
        <td height="25" align="center" >${rs.addmoney}</td>
	      <td height="25" align="center" >
	      	<c:if test="${rs.finished}"> 已处理 </c:if>
	      	<c:if test="${!rs.finished}"> 未处理 </c:if>
	      </td>
	      <td height="25" align="center" ><a href="/oss/active/activityDetailMng.htm?action=ignore&id=${rs.id}">忽略</a></td>
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
