<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>内链编辑</title>
		<meta name="heading" content="内链编辑" />
		<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
   <script type="text/javascript">
    function dosubmit(){
       if(!confirm('保存确实')){
        return false;
       }
       
    }
    </script>
	</head>
	<body>
	<div style="height:20px;padding:10px 0 10px 260px;"><label>**********新闻内链编辑**********</label></div>
		<label><s:actionmessage/></label>
		<form action="/oss/article/manageInLink.htm?action=save" method="post">
			<input type="hidden" value="${articleInLink.id}" name="articleInLink.id">
			<table width="60%" border="1" align="left" cellspacing="0">
			<tr>
			<td width="20%">内链名称:</td>
			<td><input type="text" name="name" value="${articleInLink.name}" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">类别:</td>
			<td>
			 	<select name="category">
			 		<option value="0" <c:if test="${articleInLink.category == 0 }">selected</c:if>>网站</option>
			 		<option value="1" <c:if test="${articleInLink.category == 1 }">selected</c:if>>WAP</option>
			 	</select>
			</td>
			</tr>
			<tr>
			<td width="20%">链接:</td>
			<td><input type="text" name="url" value="${articleInLink.url}" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">优先级:</td>
			<td><input type="text" name="priority" value="${articleInLink.priority }"/></td>
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