<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>用户详情</title>
		<meta name="heading" content="用户详情" />
	</head>
	<body>
		<label>用户详情</label>
		<br />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<br />
		<h1>邮箱：${customer.email }</h1>
		<br />
		<br />
		<h1>真实姓名：${customer.nickName }</h1>
		<br />
		<br />
		<h1>证件类弄：${customer.credentType }</h1>
		<br />
		<br />
		<h1>证件号：${customer.credentNo }</h1>
		<br />
		<br />
		<h1>省份：${customer.province }</h1>
		<br />
		<br />
		<h1>省份：${customer.city }</h1>
		<br />
		<br />
		<h1>发卡银行：${customer.bank }</h1>
		<br />
		<br />
		<h1>银行支行：${customer.subbranch }</h1>
		<br />
		<br />
		<h1>银行卡号：${customer.bankNumber }</h1>
		<br />
		<br />
		<h1>注册时间：${customer.registerTime }</h1>
		<br />
		<br />
		<h1>最后登陆时间：${customer.lastLoginTime }</h1>
		<br />
		<br />
		<h1>最后登陆IP：${customer.customerIp }</h1>
		<br />
		<br />
		<h1>活动获得金额：${customer.ployAccur }</h1>
		<br />
		<br />
		<h1>累计中奖金额：${customer.allWinMoney }</h1>
		<br />
	</body>
</html>