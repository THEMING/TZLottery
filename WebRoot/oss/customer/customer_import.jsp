<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@ include file="../skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>�û�����</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../styles/base.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="tab" align="center">
	<h3><strong>�û�����</strong></h3>
	<br/>
	<form action="/oss/customer/userImport.htm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="importByQQ">
	<table>
	<tr>
		<td>��������</td><td><input type="radio" name="importBy" value="byQQ" checked/>ͨ��QQ��</td>
	</tr>
	<tr>
		<td>��ѡ���ļ�</td><td><input type="file" name="upload" id="upload"/></td>
	</tr>
	<tr>
		<td></td><td><input type="submit" value="����" /></td>
	</tr>
	</table>
	</form>
</div>
</body>
