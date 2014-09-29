<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>申请详情</title>
		<meta name="heading" content="申请详情" />
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		    body{ text-align:center;}
		</style>
	</head>
	<body>
	  <div style="width:500px; margin:0 auto;text-align:left;">
		<label>申请详情</label>
		<br />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<h1>真实姓名：${customer.channel.realName }</h1>
		<br />
		<h1>身份证号：${customer.channel.idCardNo }</h1>
		<br />
		<h1>联系电话：${customer.channel.linkPhone }</h1>
		<br />
		<h1>邮箱：${customer.channel.email }</h1>
		<br />
		<h1>QQ：${customer.channel.QQ }</h1>
		<br />
		<h1>申请时间：<s:date name="customer.channel.createTime" format="yyyy-MM-dd HH:mm"/></h1>
		<br />
		<h1>渠道名称：${customer.channel.name }</h1>
		<br />
		<h1>渠道地址：${customer.channel.url}</h1>
		<br />
		<h1>备注：${customer.channel.description }</h1>
		<br />
		<br/>
		<h1><input type="button" value="返回" onclick="history.back()"/></h1>
		<br />
		</div>
	</body>
</html>