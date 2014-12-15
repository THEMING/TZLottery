<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js"></script>
<script src="../skin/01/js/common.js" type=text/javascript></script>
<style type="text/css">
<!--
.STYLE2 {color: #999999}
-->
</style>

<script language="javascript">
function changeParam(id){
	$.ajax({
		type:"post",
		url: "systemParamManager.htm?action=change",
		data:{paramId:id,name:$("#"+id+"_name").val(),value:$("#"+id+"_value").val(),description:$("#"+id+"_description").val()},
		success: function(data, textStatus){
		var a=eval("("+data+")");
	
		 	$("#"+id+"_message").html("<font style='color:red'>"+a.message+"</font>");
	}})
	
}
		
function addParam(id){
			$.ajax({
				type:"post",
				url: "systemParamManager.htm?action=delete",
				data:{paramId:id},
				success: function(data, textStatus){
					var a=eval("("+data+")");
					alert(a.message);
					$.ajax({
						type:"post",
						url: "systemParamManager.htm",
						data:{},
						success: function(data, textStatus){
					}})
			}})
			
		}
</script>

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
      <td width="93%"><span class="title">系统配置 ></span>  系统参数管理</td>
      <td width="7%" align="right"  nowrap class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="systemParamManager.aspx?action=addParam" class="blue">添加</a></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td height="10"></td>
    </tr>
  </table>
  

  <form id="form" name="form" method="post" action="">

  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    <tr>
      <td colspan="4" class="redbold">系统管理管理</td>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <th width="65" align="center">属性名</th>
      <th width="144" align="center">值</th>
      <th width="596" align="center">描述</th>
      <th width="102" align="center" nowrap="nowrap">操作</th>
      </tr>
 <s:iterator id="systemParam" value="systemParamList" status="st">
	  <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td align="center"><input id="${systemParam.id}_name" type="text" value="${systemParam.name}"/></td>
      <td align="center"><input id="${systemParam.id}_value" type="text" value="${systemParam.value}"/></td>
      <td><input type="text" id="${systemParam.id}_description" size="100" value="${systemParam.description}"/></td>
      <td align="center"><span id="${systemParam.id}_message" class="gray"></span>
       <a onclick="javascript:return changeParam('${systemParam.id}');">修改</a> 
	  </td>
	  </tr>
</s:iterator>
  </table>
  </form>
</div>
<!--编辑部分结束 -->
</body>
</html>