<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
	<title>类别编辑</title>
	<meta name="heading" content="类别编辑" />
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
	<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
   <script type="text/javascript">
    function dosubmit()
    {
       if(!confirm('保存确实'))
       {
        return false;
       }
    }
    </script>
	</head>
	<body>
	<div style="height:20px;padding:10px 0 10px 260px;"><label>**********新闻分类编辑**********</label></div>
		<label><s:actionmessage/></label>
		<form action="/oss/article/manageCategory.htm?action=save" method="post">
			<input type="hidden" name="articleCategory.id" id="id" value="${articleCategory.id}"/>
			<table width="60%" border="1" align="left" cellspacing="0">
			<tr>
			<td width="20%">名&nbsp;&nbsp;称:</td>
			<td><input type="text" name="name" value="${articleCategory.name }" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">标&nbsp;&nbsp;题:</td>
			<td><input type="text" name="title" value="${articleCategory.title }" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">关键词:</td>
			<td><input type="text" name="keywords" value="${articleCategory.keywords }" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">描&nbsp;&nbsp;述:</td>
			<td>
			<input type="text" name="description" value="${articleCategory.description }" size="70"/>
			</td>
			</tr>
			</table>
			<div style="clear:both;padding:10px;text-align:center;height:20px;"></div>
			<input type="submit" name="submit" value="保存" onclick="return dosubmit()">
			<input name="Submit22" type="button" value="取 消"  class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'"  onclick="history.back()"/>
		</form>
		<br />
		<br />
		<br />
		<br />
	</body>
</html>