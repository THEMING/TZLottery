<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
	<script src="../skin/01/js/jquery-1.3.2.js"></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<title>活动信息</title>
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
	</head>
	<body>
		<label style="font-size:16px">活动信息</label>
		<br/>
		<span style="color:red">${sysmsg }</span>
		<br />
		<form action="/oss/active/activitiesMng.htm" method="post">
		<s:hidden name="action" value="save" />
		<input type="hidden" name="id" value="${id}"/>
		<br />
		<h1>活动名：<input type="text" id="name" name="name" value="${name}"/></h1>
		<br />
		<h1>活动简称：<input type="text" id="shortName" name="shortName" value="${shortName}"/></h1>
		<br />
		<h1>活动类型：<s:select list="activityTypes" name="activityType"/></h1>
		<br />
		<h1>订单状态限制：<s:select list="activityOrderTypes" name="activityOrderType" /></h1>
		<br />
		<h1>购彩额度限制：<input type="text" id="threshold" name="threshold" value="${threshold}"/></h1>
		<br />
		<h1>加奖额度：<input type="text" id="givenMoney" name="givenMoney" value="${givenMoney}"/></h1>
		<br />
		<h1>开始时间： <input type="text" id="startTime" name="startTime" value="${startTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></h1>
		<br />
		<h1>结束时间： <input type="text" id="endTime" name="endTime" value="${endTime}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></h1>
		<br/>
		<h1><input type="submit" value="确定" onclick="return check()"/></h1>
		<br/>
		</form>
	</body>
</html>

<script>
	function check()
	{
		if($("#name").val() == "") {
			alert("请输入活动名");
			$("#name").focus();
			return false;
		}
		
		if($("#shortName").val() == "") {
			alert("请输入活动简称");
			$("#shortName").focus();
			return false;
		}
		
		if($("#threshold").val() == "") {
			alert("请输入采购额度限制");
			$("#threshold").focus();
			return false;
		}
		
		if(!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test($("#threshold").val())) {
			alert("采购额度限制输入有误");
			$("#threshold").focus();
			return false;
		}
		
		if($("#givenMoney").val() == "") {
			alert("请输入加奖额度");
			$("#givenMoney").focus();
			return false;
		}
		
		if(!/^(0|[1-9]\d*)(\.\d{1,2})?$/.test($("#givenMoney").val())) {
			alert("加奖额度输入有误");
			$("#givenMoney").focus();
			return false;
		}
		
		if($("#startTime").val() == "") {
			alert("请输入活动开始时间");
			$("#startTime").focus();
			return false;
		}
		
		if($("#endTime").val() == "") {
			alert("请输入活动结束时间");
			$("#endTime").focus();
			return false;
		}
		
		return true;
	}
</script>