<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../skin/02/taglib.jsp"%>
<html>
	<head>
		<title>优惠码查看</title>
		<script src="../../js/jquery-1.4.4.min.js"></script>
		<script src="../../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/jquery.countdown.pack.js"></script>
		<script src="../js/jquery.countdown-zh-CN.js"></script>
	</head>
	<body>
		<form action="/oss/youhuima/yhmDR.htm" enctype="multipart/form-data" method="POST">
	 选择活动:<s:select name="activityId" list="activityList" listKey="id" listValue="name"></s:select>	
            生成优惠码的个数：<input name="geshu" type="text" size="15" />
            生成优惠码的钱数：<input name="qianshu" type="text" size="15"/>
			<input type="submit" value="生成">
		</form>
		<span style="color:red;"><s:property value="succ" /></span>
		
	</body>
</html>