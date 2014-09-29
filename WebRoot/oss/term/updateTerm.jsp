<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp"%>
<head>
	<title>彩期管理</title>
	<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js"
		type=text/javascript></script>
	<script>
	function subm(cmd, strId, type) {

	}
</script>
</head>
<body>
	<div class="tab">
		<div>
			${message }
		</div>
		<form action="/oss/term/updateTerm.htm" method="post">
			<s:hidden value="update" name="action" />
			彩种：<s:select list="types" name="type" id="type"></s:select>
			输入原期号：<input type="text" name="termNo1" />
			修改为：<input type="text" name="termNo2">
			<input type="submit" value="修改" />
		</form>
	</div>

</body>
