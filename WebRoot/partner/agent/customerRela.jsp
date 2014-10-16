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
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="../../oss/skin/01/js/jquery-1.3.2.js"></script>
<script src="../../oss/skin/01/js/common.js" type=text/javascript></script>

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
     <td width="93%"><span class="title">代理系统 ></span>  账号关联</td>
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
      <td colspan="9" class="redbold">管理员列表</td>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <th width="42" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/>      </th>
      <th width="173" align="center">帐号</th>
      <th width="137" align="center">角色</th>
      <th width="242" align="center">最后登录时间</th>
      <th width="177" align="center">最后登录IP</th>
      <th width="70" align="center">登录次数</th>
      <th width="145" align="center">用户状态</th>
      <th width="230" align="center">关联的前台账号</th>
      <th width="148" align="center">操作</th>
      </tr>
      <s:iterator id="user" value="adminUserPage.result" status="st">
	  <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td align="center"><input name="id" type="checkbox" id="id" value="${user.id}"/></td>
      <td align="center">${user.adminName}</td>
      <td align="center">${user.role.name}</td>
      <td align="center">${user.lastLoginTime}</td>
      <td align="center">${user.lastLoginIp}</td>
      <td align="center">${user.loginTimes}</td>
      <td align="center" id="td_${user.id}">
	 <s:if test="#user.ipass==0">
	  <font color='#FF0000'><strong>×</strong></font>
	 </s:if>
	 <s:else>
	  <font color='#006600'><strong>√</strong></font>
	</s:else>
	  </td>
	  <td align="center">
	  <input type="text" id="${user.id}_customerRelaNickName" onblur="checkIsFindCustomer(${user.id});" value="${user.customer.nickName}"/><span id="${user.id}_imgNickName" class="gray"></span>
	  </td>
      <td align="center">
        <a onclick="javascript:return checkNickNameIsNull('${user.id}');">关联账号</a>
        </td>
      </tr>
      </s:iterator>
  </table>
   <table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${adminUserPage.pageSize}条 共${adminUserPage.totalCount}条记录 第${adminUserPage.pageNo}/${adminUserPage.totalPages}页   
	<s:if test="adminUserPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${adminUserPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="adminUserPage.pageNo>=adminUserPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${adminUserPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${adminUserPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${adminUserPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table></form>
  <table width="90%" border="0" align="center">
    <tr>
    </tr>
  </table>

</div>
<!--编辑部分结束 -->
</body>
</html>