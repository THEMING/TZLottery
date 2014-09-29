<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>订单详情</title>
		<meta name="heading" content="订单详情" />
	</head>
	<body>
		<label>${order.type}-订单详情</label>
		<br />
		<br />
		<h1>方案内容</h1>
		<s:iterator id="rs" value="order.plan.items">
		${rs.content }
		</s:iterator>
		<br />
		<br />
		<h1>中奖描述：</h1>
		   <s:iterator id="rs" value="order.orderWinDescribes">
		     <br />
		   
			</s:iterator>
		
		<br />
		<br />
		<h1>中奖金额：</h1>
		${order.winMoney}
		<br />
	</body>
</html>
