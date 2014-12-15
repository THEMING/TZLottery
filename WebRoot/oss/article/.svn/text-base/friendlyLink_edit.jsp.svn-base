<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>友情链接编辑</title>
		<meta name="heading" content="友情链接编辑" />
		<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
   <script type="text/javascript">
    function dosubmit(){
       if(!confirm('确定提交?')){
        return false;
       }
       
    }
    </script>
	</head>
	<body>
	<div style="height:20px;padding:10px 0 10px 260px;"><label>**********友情链接编辑**********</label></div>
		<label><s:actionmessage/></label>
		<form action="/oss/article/manageFriendlyLink.htm?action=save" method="post">
			<input type="hidden" value="${friendlyLink.id}" name="friendlyLink.id">
			<table width="60%" border="1" align="left" cellspacing="0">
			<tr>
			<td width="20%">链接名称:</td>
			<td><input type="text" name="name" value="${friendlyLink.name}" size="20"/></td>
			</tr>
			<tr>
			<td width="20%">链接地址:</td>
			<td><input type="text" name="url" value="${friendlyLink.url}" size="40"/></td>
			</tr>
			<tr>
			<td width="20%">友链位置:</td>
			<td>
			 	<select name="type">
			 		<option value="0" <c:if test="${friendlyLink.type == 0 }">selected</c:if>>网站首页</option>
			 		<option value="1" <c:if test="${friendlyLink.type == 1 }">selected</c:if>>资讯首页</option>
			 		<option value="1" <c:if test="${friendlyLink.type == 2 }">selected</c:if>>开奖首页</option>
			 	</select>
			</td>
			</tr>
			<tr>
			<td width="20%">排       序:</td>
			<td><input type="text" name="sort" value="${friendlyLink.sort }" size="10"/>(值越小，排在越前面)</td>
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