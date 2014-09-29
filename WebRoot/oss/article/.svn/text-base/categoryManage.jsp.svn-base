<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>新闻类别管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script type="text/javascript">
function add()
{
	this.location.href = "/oss/article/manageCategory.htm?action=edit";
}
</script>
</head>
<body>
<div class="tab">
<table width="60%"><caption class="redbold">新闻类别管理</caption>
<tr>
<td align="right"><input type="button" value="添加新闻类别" onclick="javascript:add();"/></td>
</tr>
</table>
<br>
<s:form  action="manageCategory" method="post" namespace="/oss/article">
<table >
  <tr>
  	<td height="25" width="5%"><div align="center">ID</div></td>
    <td height="25" width="10%"><div align="center">名称</div></td>
    <td height="25" width="20%"><div align="center">标题</div></td>
    <td height="25" width="20"><div align="center">关键词</div></td>
    <td height="25" width="35%"><div align="center">描述</div></td>
    <td height="25" width="10%"><div align="center">操作</div></td>
  </tr>
  	   <s:iterator id="category" value="categoryList">
	   <tr>
	  <td height="25" >${category.id}</td>
      <td height="25" >${category.name}</td>
      <td height="25" >${category.title}</td>
      <td height="25" >${category.keywords}</td>
      <td height="25" >${category.description}</td>
      <!-- 
      <td align="center"><a href="/oss/article/manageCategory.htm?action=delete&id=<s:property value="id" />">删除记录</a></td>
       -->
      <td align="center"><a href="/oss/article/manageCategory.htm?action=edit&id=<s:property value="id" />">修改记录</a></td>
      </tr>
      </s:iterator>
</table>
</s:form>
</div>

</body>
