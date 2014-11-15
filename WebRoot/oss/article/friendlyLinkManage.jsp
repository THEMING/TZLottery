<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>友情链接管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script type="text/javascript">
function add()
{
	this.location.href = "/oss/article/manageFriendlyLink.htm?action=edit";
}
</script>
</head>
<body>
<div class="tab">
<table width="60%">
<caption class="redbold">友情链接管理</caption>
<tr>
<td align="right"><input type="button" value="添加友情链接" onclick="javascript:add();"/></td>
</tr>
</table>
<br>
<s:form  action="/oss/article/manageFriendlyLink.htm" method="post">
<table >
  <tr>
    <td height="25"><div align="center">链接名称</div></td>
    <td height="25"><div align="center">链接地址</div></td>
    <td height="25"><div align="center">位置</div></td>
    <td height="25"><div align="center">排序</div></td>
    <td height="25" colspan="2"><div align="center">操作</div></td>
  </tr>
  	   <s:iterator id="friendlylink" value="friendlyLinkList">
	   <tr>
      <td height="25" ><a href="${friendlylink.url}" target="_blank">${friendlylink.name}</a></td>
      <td height="25" >${friendlylink.url}</td>
      <c:if test="${friendlylink.type == 0}">
      	<td height="25" >网站首页</td>
      </c:if>
       <c:if test="${friendlylink.type == 1}">
      	<td height="25" >资讯首页</td>
      </c:if>
       <c:if test="${friendlylink.type == 2}">
      	<td height="25" >开奖首页</td>
      </c:if>
      <td height="25" >${friendlylink.sort}</td>
      <td align="center"><a href="/oss/article/manageFriendlyLink.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
      <td align="center"><a href="/oss/article/manageFriendlyLink.htm?action=edit&id=<s:property value="id" />">修改记录</a></td>
      </tr>
      </s:iterator>
</table>
</s:form>
</div>
</body>
