<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css" />
		<script src="../skin/01/js/jquery-1.3.2.js"></script>
		<script src="../skin/01/js/common.js" type=text/javascript></script>
	</head>

	<body>
		<div id="edit">
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="red_xx table_all">
				<tr>
					<td width="93%">
						<span class="title">系统配置 ></span> 管理员手机管理
					</td>
					<td width="7%" align="right" nowrap class="operation">
						<span class="hui">管理导航</span><span class="red"> - </span>
						<a href="/oss/customer/adminMobile.aspx?action=addjsp" class="blue">添加</a>
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="table_all">
				<tr>
					<td height="10"></td>
				</tr>
			</table>
			<form id="form" name="form" method="post" action="deleteselect">

				<table width="90%" align="center" cellspacing="1"
					class="table table_all" id="table1" bgcolor="wite">
					<tr>
						<td colspan="8" class="redbold">
							管理员手机列表
						</td>
					</tr>
					<tr onmouseover="over()" onclick="change()" onmouseout="out()"
						class="td_bg">
						<th width="173" align="center">
							帐号
						</th>
						<th width="137" align="center">
							状态
						</th>
						<th width="242" align="center">
							邮箱
						</th>
						<th width="177" align="center">
							手机号
						</th>
						<th width="147" align="center" nowrap="nowrap">
							级别
						</th>
						<th width="148" align="center" nowrap="nowrap">
							操作
						</th>
					</tr>
					<s:iterator id="user" value="#request.list">
					<tr class="td_bg">
						<td align="center">	
							<s:property value="#user.name" />
						</td>
						<td align="center">
							<s:property value="#user.active" />
						</td>
						<td align="center">
							<s:property value="#user.email" />
						</td>
						<td align="center">
							<s:property value="#user.mobile" />
						</td>
						<td align="center">
							<s:property value="#user.content" />
						</td>
						<td align="center">
							<a href='/oss/customer/adminMobile.aspx?action=editjsp&userid=<s:property value="#user.id" />'/> 修 改 </a> / 
							<a onclick="javascript:return getConfirm('删除后将不能恢复！真的要删除吗');" href='/oss/customer/adminMobile.aspx?action=delete&userid=<s:property value="#user.id" />'> 删 除 </a>
						</td>
					</tr>
					</s:iterator>
				</table>
			</form>
		</div>
		<!--编辑部分结束 -->
	</body>
</html>