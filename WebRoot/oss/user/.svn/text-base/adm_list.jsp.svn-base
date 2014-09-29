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
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script src="skin/01/js/common.js" type=text/javascript></script>

<script language="javascript">
$("document").ready(function(){
     
	$("#check_all").toggle(function(){
                $("[name='id']").attr("checked",'true');
          },
          function(){

                $("[name='id']").removeAttr("checked");
	
          })

});

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
     <td width="93%"><span class="title">用户管理 ></span>  管理员</td>
      <td width="7%" align="right"  nowrap class="operation"><span class="hui">管理导航</span><span class="red"> - </span> 
      <c:if test="${fn:indexOf(permit,'add')>0}">
      <a href="manageUser.aspx?action=edit&chnId=${chnId}" class="blue">添加</a> | 
      </c:if>
      <a onclick="return getConfirm('删除后将不能恢复！真的要删除吗？')" href="javascript:document.form.action='mngUser.do?act=delAdmUser';document.form.submit();" class="red">批量删除</a> | <a href="javascript:document.form.action='mngUser.do?act=statusAdmUserAll&stat=0';document.form.submit()" class="red">批量锁定</a> | <a href="javascript:document.form.action='mngUser.do?act=statusAdmUserAll&stat=1';document.form.submit()" class="red">批量激活</a></td>
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
      <td colspan="8" class="redbold">管理员列表</td>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <th width="42" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_all"/>      </th>
      <th width="173" align="center">帐号</th>
      <th width="137" align="center">角色</th>
      <th width="242" align="center">最后登录时间</th>
      <th width="177" align="center">最后登录IP</th>
      <th width="147" align="center" nowrap="nowrap">登录次数</th>
      <th width="145" align="center" nowrap="nowrap">用户状态</th>
      <th width="148" align="center" nowrap="nowrap">操作</th>
      </tr>
      <s:iterator id="user" value="page.result" status="st">
	  <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <td align="center"><input name="id" type="checkbox" id="id" value="${user.id}"/></td>
      <td align="center">${user.adminName}</td>
      <td align="center">${user.role.name}</td>
      <td align="center">${user.lastLoginTime}</td>
      <td align="center">${user.lastLoginIp}</td>
      <td align="center">${user.loginTimes}</td>
      <td align="center" id="td_${user.id}"><a href='javascript:void(0);' onclick="chgStatus_ajax('mngUser.do?act=statusAdmUser&id=${user.id}&stat=${users.ipass}','td_${user.id}')">
	 <s:if test="#user.ipass==0">
	  <font color='#FF0000'><strong>×</strong></font>
	 </s:if>
	 <s:else>
	  <font color='#006600'><strong>√</strong></font>
	</s:else>
	  </a></td>
      <td align="center">
      <c:if test="${fn:indexOf(permit,'view')>0}">
        <a href='manageUser.aspx?action=edit&userId=${user.id}&chnId=${chnId}'>改</a>  
      </c:if>
      <c:if test="${fn:indexOf(permit,'delete')>0}">  
        <a onclick="javascript:return getConfirm('删除后将不能恢复！真的要删除吗');" href='manageUser.aspx?action=delAdmin&userId=${user.id}&chnId=${chnId}'>删</a> 
      </c:if>  
       <c:if test="${fn:indexOf(permit,'add')>0}">  
        <a href='manageUser.aspx?action=chagPwd&userId=${user.id}&chnId=${chnId}'>密改</a> 
      </c:if>  
       </td>
      </tr>
      </s:iterator>
  </table>
   <table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
  </form>
  <table width="90%" border="0" align="center">
    <tr>
    </tr>
  </table>

</div>
<!--编辑部分结束 -->
</body>
</html>