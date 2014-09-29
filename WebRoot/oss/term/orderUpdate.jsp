<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp"%>
<head>
	<title>订单手动修改</title>
	<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js"
		type=text/javascript></script>
	<script>
		function edit(orderid ,id, statue) {
			$("#ticketid").val(id);
			$("#orderid").val(orderid);
			$("#statue").val(statue);
			$("#action").val("edit");
			$("form").submit();
		}
		
		function updateOrder(orderid) {
			$("#orderid").val(orderid);
			$("#action").val("updateOrder");
			$("form").submit();
		}
	</script>
</head>
<body>
	<div style="margin: 20px;">
		<div style="margin-bottom: 10px;">
			<form action="/oss/term/orderUpdate.htm" method="post">
				<s:hidden value="search" name="action" id="action"/>
				<s:hidden value="" name="ticketid" id="ticketid"/>
				<s:hidden value="" name="statue" id="statue" />
				请输入订单ID:<input type="text" name="orderid" id="orderid" value='<s:property value="orderid"/>'>
				<input type="submit" value="查询">
			</form>
		</div>
		<div style="margin-bottom: 10px;">
			<table style="width: 80%; border: solid 1px;" align="center" >
				<tr style="border: solid 1px;" align="center">
					<td>订单ID</td>
					<td>彩种</td>
					<td>内容</td>
					<td>订单号</td>
					<td>出票状态</td>
					<td>操作</td>
					
				</tr>
				<s:iterator value="list" var="ticket">
					<tr align="center">
						<td>${ticket.order.id }</td>
						<td>${ticket.type }
						<td>${ticket.content }
						<td>${ticket.otherOrderID }</td>
						<td>${ticket.status }</td>
						<td>
							<input type="button" value="出票失败" onclick="edit( ${ticket.order.id },${ticket.id },0);">
							<input type="button" value="出票成功" onclick="edit( ${ticket.order.id },${ticket.id },1);">
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div style="margin-left: 280px;" >
				<s:if test="size == 0">
					<input type="button" value="提交" onclick="updateOrder(<s:property value="orderid"/>);"/>
				</s:if>
				<s:else>
					<input type="button" value="提交" disabled="true"/>
				</s:else>
		</div>
	</div>
</body>
