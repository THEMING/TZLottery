<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
			<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
				<script src="skin/01/js/common.js"></script>
	</head>

	<body>
		<!--编辑部分开始 -->
		<div id="edit">
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="table_all">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="red_xx table_all">
				<tr>
					<td width="93%">
						<span class="title">核心功能 ></span> 功能菜单
					</td>
					<td width="7%" align="right" nowrap class="operation">
						<span class="hui">管理导航</span><span class="red"> - </span><a
							href="adminEditTunnel.aspx?action=edit" class="blue">添加</a>
						<!--| <a href="javascript:form.action='channel.do?action=act&flg=0';form.submit()" class="red">锁定</a> | <a href="javascript:form.action='channel.do?action=act&flg=1';form.submit()" class="green">激活</a>-->
					</td>
				</tr>
			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0"
				class="table_all">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<form id="form" name="form" method="post">

				<table width="100%" align="center" cellspacing="1"
					class="table table_all" id="table1">
					<tr>
						<td colspan="5" class="redbold">
							功能菜单列表
						</td>
					</tr>
					<tr onmouseover="over()" onclick="change()" onmouseout="out()"
						class="td_bg">
						<th width="604" align="center">	功能名称</th>
						<th width="93" align="center">属性</th>
						<th width="67" align="center" nowrap="nowrap">排序</th>
						<th width="78" align="center" nowrap="nowrap">状态</th>
						<th width="100" align="center">操作</th>
					</tr>
					<s:iterator id="channel" value="chnTreeList" status="st">
                    <tr onmouseover="over()" onclick="change()" onmouseout="out()"	class="td_bg">
						<td style="padding-left: 10px;">
						<s:if test="#channel.depth==0"><input name="arryid" id="arryid" type="checkbox"  value="<s:property value="id"/>" /></s:if>
						<s:else><img src="skin/01/js/dtree/img/join.gif" width="18" height="18" />
							<input name="arryid" type="checkbox" id="arryid"  value="<s:property value="id"/>" /></s:else>
							<s:property value="channelName"/>
						</td>
						<td align="center">	内部</td>
						<td align="center"><s:property value="priority"/></td>
						<td align="center"><span class=green>
						<s:if test="#channel.ispass==1">√</s:if>
						<s:else>×</s:else>
						</span></td>
						<td align="center" nowrap="nowrap">
							<a href='adminEditTunnel.aspx?action=edit&chnId=<s:property value="id"/>'>改</a> |
							<a href='adminEditTunnel.aspx?action=del' onclick="javascript:return getConfirm('删除后将不能恢复！真的要删除吗？');"
								href=''>删</a>
						</td>
					</tr>
					</s:iterator>
				</table>

			</form>

		</div>
		<!--编辑部分结束 -->
	</body>
</html>