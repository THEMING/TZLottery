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
		<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	</head>

	<body>
		<div id="edit">
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="red_xx table_all">
				<tr>
					<td width="93%">
						<span class="title">财务管理 ></span> 拉手余额明细
					</td>
				</tr>
			</table>
			<form action="/oss/customer/adminfanli.aspx">
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					class="table_all" width="60%">
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td width="20%"></td>
						<td height="10">起始时间：<input type="text" name="stime" value="<s:date name="stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
						<td height="10">截止时间：<input type="text" name="etime" value="<s:date name="etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
						<td><input type="submit" value="提交"/></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
					<tr><td>共计 <s:property value="size" /> 笔数据</td></tr>
				</table>
			</form>
				<table width="90%" align="center" cellspacing="1"
					class="table table_all" id="table1" bgcolor="wite">
					<tr>
						<td colspan="8" class="redbold">
							拉手余额明细
						</td>
					</tr>
					<tr onmouseover="over()" onclick="change()" onmouseout="out()"
						class="td_bg">
						<th width="173" align="center">
							总计金额
						</th>
						<th width="137" align="center">
							余额返利
						</th>
						<th width="242" align="center">
							真钱充值
						</th>
					</tr>
					<tr class="td_bg">
						<td align="center">	
							<s:property value="money" />
						</td>
						<td align="center">
							<s:property value="u_money" />
						</td>
						<td align="center">
							<s:property value="c_money" />
						</td>
					</tr>
				</table>
		</div>
		<!--编辑部分结束 -->
	</body>
</html>