<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script>
		function add()
		{
		   this.location.href = "pictureManage.htm?action=detail";
		}
	</script>
  </head>
  <body>
  <div class="tab">
    <form  id="form_id" action="pictureManage.htm" method="post">
	<s:hidden name="action" value="index" />
	<table width="60%">
	<caption class="redbold">图片管理</caption>
  	<tr>
    <td width="120" height="25" ><div align="center">类别: <s:select list="categoryList" name="category.id" listKey="id" listValue="name"  value="category.id" headerKey="-1" headerValue="--全部--"/></div></td>
    <td width="130" height="25">标题：<input type="text" name="title" id="title" value="${title }" /> </td>
    <td width="170" >创建时间:
    <input type="text" name="startTime" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/> -
    <input type="text" name="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
	<td width="150" height="25" bgcolor="#ffffff">
	关联的代理商：<s:select list="partnerList" name="partner.id" listKey="id" listValue="nickName" value="partner.id" headerValue="全部" headerKey="-1"/>
	</td>
	<td width="50" height="25" bgcolor="#ffffff">
	<input type="submit" value="查询" />&nbsp;&nbsp;<input type="button" name="add_btn" value="添加" onclick="javascript:add();"/></td>
  	</tr>
	</table>
	<br>
	<table width="80%" cellspacing="3">
	 	<tr>
	    <td align="center">标题</td>
	    <td align="center">是否公开</td>
	    <td align="center">创建日期</td>
	    <td align="center">图片类别</td>
	    <td align="center">链接</td>
	    <td align="center">链接</td>
	    <td colspan="3">操作</td>
	    </tr>
	    <tr></tr>
		<s:iterator id="rs" value="page.result" status="s">
	  	<tr>
	  	<td align="center">${rs.title}</td>
	  	<td align="center"><s:if test="#rs.isPublic">是</s:if><s:else>否</s:else></td>
	  	<td align="center"><s:date name="#rs.publishTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  	<td align="center">${rs.category.name}</td>
	  	<td align="center">${rs.jumpUrl}</td>
	  	<td align="center">${rs.jumpUrl}</td>
	  	<td align="center"><a href="pictureManage.htm?action=delete&id=<s:property value="#rs.id" />">删除记录</a></td>
	  	<td align="center"><a href="pictureManage.htm?action=detail&id=<s:property value="#rs.id" />">修改记录</a></td>
	  	<td align="center"><a href="http://www.yicp.com/actinfo/news_<s:property value="#rs.id" />.html">预览</a></td>
	  	</tr>
	  	</s:iterator>
  		</table>
  		<table width="90%" border="0" align="center">
    	<tr><td><jsp:include page="../../util/page.jsp"></jsp:include></td></tr>
  		</table>
  		</form>
    </div>
  </body>
</html>
