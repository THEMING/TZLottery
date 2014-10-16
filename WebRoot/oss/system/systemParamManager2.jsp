<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js"></script>
<script src="../skin/01/js/common.js" type=text/javascript></script>

<script language="javascript">
$("document").ready(function(){
     
	$("#check_all").toggle(function(){
                $("[name='id']").attr("checked",'true');
          },
          function(){

                $("[name='id']").removeAttr("checked");
	
          })

});

function checkNickNameIsNull(id){
	
	$.ajax({
		type:"post",
		url: "agentManager.htm?action=customerRelaChange",
		data:{adminUserId:id,changeCustomerRelaNickName:$("#"+id+"_customerRelaNickName").val()},
		success: function(data, textStatus){
		var a=eval("("+data+")");
	
		 	$("#"+id+"_imgNickName").html("<font style='color:red'>"+a.message+"</font>");
	}})
	
}

function checkIsFindCustomer(id){
	$.ajax({
		type:"post",
		url: "register.htm?action=cheacksuperior",
		data:{superior:$("#"+id+"_customerRelaNickName").val()},
		success: function(data, textStatus){
		var a=eval("("+data+")");
	
		if(a.message=="推荐人不存在")
		{
		 	$("#"+id+"_imgNickName").html("<font style='color:red'>找不到对应的前台账号</font>")
		}else{
			$("#"+id+"_imgNickName").html("")
		}
	}})
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("form").submit();
}
function jumpPage1() {
	$("#pageNo").val($("#pageNum").val());
	$("form").submit();
}

</script>

<style type="text/css">
<!--
.STYLE1 {color: #999999}
-->
</style>
</head>

<body>
<div id="edit">
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td> <input name="chnId" type="hidden" id="chnId" value='${chnId}'/></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
     <td width="93%"><span class="title">系统配置 ></span>  系统参数管理</td>
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
      <td colspan="9" class="redbold">系统参数列表</td>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <th width="42" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/>      </th>
      <th width="173" align="center">名称</th>
      <th width="137" align="center">值</th>
      <th width="242" align="center">描述</th>
      <th width="177" align="center">操作</th>
      </tr>
      <%--<s:iterator id="systemParam" value="systemParamList" status="st">
	  <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td align="center"><input name="id" type="checkbox" id="id" value="${systemParam.id}"/></td>
      <td align="center">${systemParam.name}</td>
      <td align="center">${systemParam.value}</td>
      <td align="center">${systemParam.description}</td>
	  <td align="center">
	  <input type="text" id="${user.id}_customerRelaNickName" onblur="checkIsFindCustomer(${user.id});" value="${user.customer.nickName}"/><span id="${user.id}_imgNickName" class="gray"></span>
	  </td>
      <td align="center">
        <a onclick="javascript:return checkNickNameIsNull('${user.id}');">修改</a>
        </td>
      </tr>
      </s:iterator>
  --%></table>
   </form>
  <table width="90%" border="0" align="center">
    <tr>
    </tr>
  </table>

</div>
<!--编辑部分结束 -->
</body>
</html>