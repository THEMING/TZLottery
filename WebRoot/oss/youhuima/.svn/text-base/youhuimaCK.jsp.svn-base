<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../skin/02/taglib.jsp"%>
<html>
	<head>
		<title>优惠码导入</title>
		<script src="../../js/jquery-1.4.4.min.js"></script>
		<script src="../../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/jquery.countdown.pack.js"></script>
		<script src="../js/jquery.countdown-zh-CN.js"></script>
		<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
		<link href="../styles/base.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function yhmtype(){
				window.location.href="/oss/youhuima/yhmCK.htm?type=" + $("#type").val()+"&activityId="+$("#activityId").val();
			}
		</script>
	</head>
	<body>
		<div class="tab">
			<form action="/oss/youhuma/yhmCK.htm">
				选择活动:<s:select name="activityId" list="activityList" listKey="id" listValue="name" onchange="yhmtype()"></s:select>
				选择激活码的状态：<select name="type" id="type" onchange="yhmtype()" >
					<option>请选择</option>
					<option value="sy">所有的</option>
					<option value="wjh">未激活</option>
					<option value="yjh">已激活</option>
					<option value="ydh">已兑换</option>
					<option value="ygq">已过期</option>
				</select>	
			</form>
			<br/>
			<table id="tb" width="90%">
				<tr>
					<th>
						活动
					</th>
					<th>
						优惠码
					</th>
					<th>
						金额（元）
					</th>
					<th>
						状态
					</th>
					<th>
						生成时间
					</th>
				</tr>
				<s:iterator value="youhuimalist">
				<tr id="line_<s:property value='id'/>">
					<td>
						<s:property value="activity.name" />
					</td>
					<td>
						<s:property value="number" />
					</td>
					<td>
						<s:property value="money" />
					</td>
					<td>
						<s:property value="type" />
					</td>
					<td>
						<s:date name="createTime" format="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>