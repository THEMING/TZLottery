<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/common.js" type=text/javascript></script>
<script src="skin/01/js/passwd.js" type=text/javascript></script>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script language="javascript">

</script>
</head>

<body>
<!--编辑部分开始 -->
<div id="edit">
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td><input name="chnId" type="hidden" id="chnId" value='${chnId}'/></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td><span class="title">用户管理 >></span><span class="title"> 用户管理 >></span> 功能权限</td>
      <td align="right"  nowrap="nowrap"></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
  </table>  
  <form id="form" name="form" method="post" action="adminEditTunnel.php?action=savemode" >
   <input type="hidden" name="chnId" value="${chnId}"/>
  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    <tr class="td_bg">
      <th colspan="2" class="redbold">功能权限编辑</th>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td width="181"  align="center">权限名：</td>
      <td width="732">
	  <input name="mode.cname" type="hidden"  value="${mode.cname}" /></td>
    </tr>
     <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td width="181"  align="center">权限：</td>
      <td width="732">
	  <input name="mode.ename" type="hidden"  value="${mode.ename}" /></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td align="center"> <input name="Submit2" type="submit" value="保 存" class="button"  />　
        <input name="Submit22" type="button" value="取 消"  class="button" onclick="history.back()"/>　
        </td>
    </tr>
  </table>
</form>
</div>
<!--编辑部分结束 -->

</body>
</html>