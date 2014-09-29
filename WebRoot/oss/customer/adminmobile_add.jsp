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
						<span class="title">系统配置 ></span> 管理员手机管理 > 添加
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="table_all">
				<tr>
					<td height="10"></td>
				</tr>
			</table>
			<div style="width: 50%" align="center">
				<form action="/oss/customer/adminMobile.aspx">
					<input type="hidden" name="action" value="add" />
					<table	width="80%">
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="name" id="name" value="" /></td>
						</tr>
						<tr>
							<td>邮箱：</td>
							<td><input type="text" name="email" id="email" value="" /></td>
						</tr>
						<tr>
							<td>手机号</td>
							<td><input type="text" name="mobile" id="mobile" value="" /></td>
						</tr>
						<tr>
							<td>状态：</td>
							<td><input type="text" name="active" id="active" value="" /></td>
						</tr>
						<tr>
							<td>级别：</td>
							<td><input type="text" name="content" id="content" value="" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="增加" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>	
  </body>
</html>
