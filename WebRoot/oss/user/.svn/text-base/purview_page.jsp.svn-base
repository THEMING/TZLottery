<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/jquery-1.3.2.js"></script>
<script language="javascript">
$("document").ready(function(){
	
});
function onclickcheck(obj,str){

	$("input[id='"+str+"']").each(function(){
			$(this).attr("checked",!($(obj).attr("checked")));
	})
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
      <td><span class="title">用户管理 ></span>  管理员</td>
      <td align="right"  nowrap="nowrap"><span class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="mngUser.do?act=getAdmlist" class="blue">返回</a></span></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25">&nbsp;</td>
    </tr>
  </table>  
  <form id="form" name="form" method="post" action="manageRole.aspx?action=saveperm">

  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    
    <tr class="td_bg">
      <th colspan="5"  class="redbold">信息节点管理权限
        <input name="roleId" type="hidden" id="roleId" value='${roleId}'/>
        <input name="chnId" type="hidden" id="chnId" value='${chnId}'/>
        </th>
    </tr>
    <tr  class="td_bg">
    	<td width="36%" align="center"><strong>节点名称</strong></td>
	    <td width="16%" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_view" onclick="onclickcheck(this,'view')"/>
	      <strong>查看</strong></td>
	    <td width="16%" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_add" onclick="onclickcheck(this,'add')"/>
          <strong>添加</strong></td>
	    <td width="16%" align="center"><input name="checkbox" type="checkbox" class="input " value="checkbox"  id="check_delete" onclick="onclickcheck(this,'delete')"/>        
          <strong>删除</strong></td>
    </tr>
    <s:iterator id="permissions" value="permissionsList" >
    <tr class="td_bg">
    <td style="padding-left:10px;">
     <s:if test="#permissions.channel.parentId!=0">
      <img src="skin/01/js/dtree/img/join.gif" />   
     </s:if>  
            ${permissions.channel.channelName}</td>
    <s:if test="#permissions.channel.parentId!=0">
    
    <td align="center">
    <c:if test="${fn:indexOf(permissions.operate_code,'view')>0}">
    <input type="checkbox" name="${permissions.channel.id }" id="view" value="view"  checked="checked" />
    </c:if>
    <c:if test="${fn:indexOf(permissions.operate_code,'view')<0}">
     <input type="checkbox" name="${permissions.channel.id }" id="view" value="view" />
    </c:if>
    </td>
    <td align="center">
    <c:if test="${fn:indexOf(permissions.operate_code,'add')>0}">
    <input type="checkbox" name="${permissions.channel.id }" id="add" value="add"  checked="checked" />
     </c:if>
    <c:if test="${fn:indexOf(permissions.operate_code,'add')<0}">
     <input type="checkbox" name="${permissions.channel.id }" id="add" value="add" />
    </c:if>
    </td>
    <td align="center">
    <c:if test="${fn:indexOf(permissions.operate_code,'delete')>0}">
    <input type="checkbox" name="${permissions.channel.id }" id="delete" value="delete"  checked="checked" />
     </c:if>
    <c:if test="${fn:indexOf(permissions.operate_code,'delete')<0}">
     <input type="checkbox" name="${permissions.channel.id }" id="delete" value="delete"  />
     </c:if>
    </td>
    </s:if>
    </tr>
    </s:iterator>
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