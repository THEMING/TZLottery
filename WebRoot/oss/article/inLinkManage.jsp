<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>新闻内链管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script type="text/javascript">
function add()
{
	this.location.href = "/oss/article/manageInLink.htm?action=edit";
}
</script>
</head>
<body>
<div class="tab">
<table width="60%">
<caption class="redbold">新闻内链管理</caption>
<tr>
<td align="right"><input type="button" value="添加内链" onclick="javascript:add();"/></td>
</tr>
</table>
<br>
<s:form  action="/oss/article/manageInLink.htm" method="post">
<table >
  <tr>
    <td height="25"><div align="center">名称</div></td>
    <td height="25"><div align="center">类别</div></td>
    <td height="25"><div align="center">链接</div></td>
    <td height="25"><div align="center">优先级</div></td>
    <td height="25" colspan="2"><div align="center">操作</div></td>
  </tr>
  	   <s:iterator id="inlink" value="inLinkList">
	   <tr>
      <td height="25" ><a href="${inlink.url}" target="_blank">${inlink.name}</a></td>
      <c:if test="${inlink.category == 0}">
      	<td height="25">网站</td>
      </c:if>
      <c:if test="${inlink.category == 1}">
      	<td height="25">WAP</td>
      </c:if>
      <td height="25" >${inlink.url}</td>
      <td height="25" >${inlink.priority}</td>
      <td align="center"><a href="/oss/article/manageInLink.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
      <td align="center"><a href="/oss/article/manageInLink.htm?action=edit&id=<s:property value="id" />">修改记录</a></td>
      </tr>
      </s:iterator>
</table>
</s:form>
</div>
</body>
