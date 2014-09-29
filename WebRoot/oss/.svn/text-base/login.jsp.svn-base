<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>一彩票 - 彩票网站后台管理系统</title>
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="skin/01/js/betvalidate.js"></script>
<script language="javascript">
</script>
<style>
	html{ overflow-y:hidden;}
</style>
</head>
<body>
<div id="login">
 <form id="form" name="form" method="post" action="adminLogin.htm" >
  <div class="">
    <div class="dd">
	<table width="100%" border="0">
		  <tr class = "input">
			<td>用户名：</td>
			<td><input name="adminName" type="text" class="input" id="adminName"  value="" size="10" maxlength="50"  /></td>
			<td>密　码：</td>
			<td><input name="adminPwd" type="password" class="input" id="adminPwd" value="" size="10" maxlength="50" /></td>
			<td>验证码：</td>
			<td><input type="text" class="input" id="validnum" name="validnum" style="width:30px"/></td>
                            <td><img src="/jcaptcha.jpg" class="hand" id="captchaImg" width="50" height="28" onclick="refreshCaptcha()"/> 
                            </td>
			<td> <s:actionerror/> </td>
			<td><input src="skin/01/images/btlogin.gif" type="submit" name="login" id="login" style="width:55px; height:21px;margin:0;padding:0;" /></td>
		  </tr>
	  </table>
    </div>
  </div>
   </form>
</div>
</body>
