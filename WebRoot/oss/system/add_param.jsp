<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../general/lotteryJs/jquery-1.4.2.js"></script>
<script language="javascript">
	function checkFunction(obj){
		if($(obj).attr("checked"))
			$(obj).val($(obj).attr("attr"));
		else
			$(obj).val("");
	}

function formcheck(){

		if(document.getElementById("name").value.length==0){
			alert("提示：\n\n请正确填写角色名！");
			document.getElementById("name").focus();
			return false;
		}
		if(document.getElementById("description").value.length>100){
			alert("提示：\n\n请正确填写描述！(0～100个汉字或字符)");
			document.getElementById("description").focus();
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
      <td>&nbsp;</td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td width="93%"><span class="title">系统配置 ></span>  系统参数管理</td>
      <td width="7%" align="right"  nowrap class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="systemParamManager.htm" class="blue">返回</a></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
  </table>  
  <form id="form" name="form" method="post" action="systemParamManager.aspx?action=addParam2" >

  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    
    <tr  class="td_bg">
      <th colspan="2" class="redbold">系统参数添加</th>
    </tr>
    <tr class="td_bg">
      <td width="202"  align="center">属性名：</td>
      <td width="652">
	  

	  <input name="name" type="text" id="name" size="50" maxlength="255" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
</td>
    </tr>
    <tr class="td_bg">
      <td width="202"  align="center">值：</td>
      <td width="652">
	  

	  <input name="value" type="text" id="value" size="50" maxlength="255" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"/>
</td>
    </tr>
  
    <tr  class="td_bg">
      <td align="center">描　述：</td>
      <td><textarea name="description" cols="50" rows="5" id="description" class="input" onClick="this.className='focus'"  onmouseover="this.className='hover'" onmouseout="this.className='input'"></textarea></td>
    </tr>
    


  </table>
  <table height="75" border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td align="center"> <input name="Submit2" type="submit" value="保 存" class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'" />　
        <input name="Submit22" type="button" value="取 消"  class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'"  onclick="history.back()"/>　
        </td>
    </tr>
  </table>
</form>
</div>


</body>
</html>