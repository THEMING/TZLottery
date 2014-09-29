<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>修改真实姓名</title>
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
	</head>
	<body>
		<label>用户</label>
		<br/>
		${sysmsg }
		<br />
		<s:form action="manageEditCustomer" method="post">
		<s:hidden name="action" value="edit" />
		<s:hidden name="top" value="rname" />
		<s:hidden name="id" />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<h1>真实姓名：${customer.realName }</h1>
		<br />
		<h1>修改真实姓名：<input type="text" name="realName"/></h1>
		<br/>
		<h1><input type="submit" value="确定"/> <a href="manageCustomer.aspx?action=view&customerId=${id }">返回</a></h1>
		<br/>
		</s:form>
	</body>
</html>