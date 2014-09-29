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
<script src="skin/01/js/passwd.js" type=text/javascript></script>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script language="javascript">

function formcheck(){

		if((!isIntAndChar($("#password").val())||$("#password").val().length<4)&&$("#id").val()==""){
			alert("提示：\n\n请正确填写管理员密码！(4-16个英文、数字（A-Z，a-z,0-9!@#$%^&*()）的组合，英文字母请注意大小写)");
			$("#password").focus();
			return false;
		}

		if($("#password").val()!=$("#password2").val()){
			alert("提示：\n\n密码输入不一致！");
			$("#password2").focus();
			return false;
		}

		return true;
}
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
      <td><span class="title">用户管理 ></span>  修改密码</td>
      <td align="right"  nowrap="nowrap"><span class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="mngUser.do?act=getAdmlist" class="blue">返回</a></span></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
  </table>  
  <form id="form" name="form" method="post" action="manageUser.aspx?action=savePwd" onsubmit="return formcheck();">
  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    
    <tr class="td_bg">
      <th colspan="2" class="redbold">修改密码</th>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
     
      <td width="732">
 	  <input name="userId" type="hidden" id="userId"  value="${adminUser.id}"/></td>
    </tr>
    <tr  class="td_bg">
      <td align="center">新密码：</td>
      <td><input id="password" name="password" size="20" maxlength="16" value="" type="password" class="input" />
        请输入4-16个英文、数字（A-Z，a-z,0-9）的组合，英文字母请注意大小写</td>
    </tr>
    <tr  class="td_bg">
      <td align="center">重复密码：</td>
      <td><input name="password2" type="password" id="password2" size="20" maxlength="16" class="input" />
        请再输入一遍密码</td>
    </tr>

  </table>
  <table height="75" border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td align="center"> <input name="Submit2" type="submit" value="保 存" class="button"  />　
        <input name="Submit22" type="button" value="取 消"  class="button"  onclick="history.back()"/>　
        </td>
    </tr>
  </table>
</form>
</div>
<!--编辑部分结束 -->

</body>
</html>