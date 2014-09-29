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
<script src="skin/01/js/common.js" type=text/javascript></script>
<style type="text/css">
<!--
.STYLE2 {color: #999999}
-->
</style>
</head>

<body>
<!--编辑部分开始 -->
<div id="edit">

  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td width="93%"><span class="title">用户管理 ></span>  功能权限</td>
      <td width="7%" align="right"  class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="adminEditTunnel.php?action=editmode" class="blue">添加</a></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td height="10"></td>
    </tr>
  </table>
  
  <form id="form" name="form" method="post" action="">
  <input type="hidden" name="chnId" value="${chnId}"/>
  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    <tr>
      <td colspan="4" class="redbold">功能权限</td>
    </tr>
    <tr class="td_bg">
      <th width="65" align="center">序号</th>
      <th width="144" align="center">权限名</th>
      <th width="596" align="center">权限</th>
      <th width="102" align="center" nowrap="nowrap">操作</th>
      </tr>
 <s:iterator id="rs" value="modeList" status="st">
	  <tr onclick="change()" class="td_bg">
	    <td align="center">${rs.id}</td>
      <td align="center">${rs.cname}</td>
      <td align="center">${rs.ename}</td>
      <td align="center">
       <a href='adminEditTunnel.php?action=editmode&modeId=${rs.id}'>改</a> | <a onclick="javascript:return getConfirm('该角色中的管理员将一并被删除且删除后将不能恢复！真的要删除吗？');" href='adminEditTunnel.php?action=editmode&delmode=${rs.id}'>删</a></td>
	  </tr>
</s:iterator>
  </table>
  </form>
</div>
<!--编辑部分结束 -->
</body>
</html>