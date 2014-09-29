<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>软件注册 — 一彩票网</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="../css/common.css" type="text/css" />
	<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/lottery/common.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function()
			{
				iframeResize();
			}
		);
	
		function iframeResize() {
			var obj = parent.document.getElementById("cur_content_frame");
			obj.height = $("#software_box").height() + 20;
		}
	
		function onSubmit(id)
		{
			var machineNo = $("#text_"+id).val();
			var softType =  $("#select_"+id).val();
			
			machineNo = trim(machineNo);

			if(!machineNo || machineNo == "") {
				alert("请输入机器码!");
				return;
			}
			
			var url = "/customer/MySoftware.htm?action=getRegNo";
			var data = {
				"id" : id,
				"machineNo" : machineNo,
				"softType" : softType
			};
			$.post(url, data, callbackFunc()); 
		}
		
		function callbackFunc(response, status) {
			location.reload();
		}
	</script>
</head>
<body>
<!-- head -->
<div id="software_box">
	<div style="font-size:14px; padding:10px">
		您有<span class="red"> ${remainingGiftNum}</span> 个软件注册码尚未领取。
	</div>
	
	<div>
		<table class="customer_table" style="width:100%">
			<tr class="title red"><td colspan="6">您的软件注册码领取记录</td></tr>
			<tr>
				<td class="title">充值单号</td>
				<td class="title">软件类型</td>
				<td class="title">机器码</td>
				<td class="title">注册码</td>
				<td class="title">领取</td>
				<td class="title">领取日期</td>	
			</tr>
			<s:iterator value="rechargeGiftTab">
               	<tr>
               		<td class="center">
               			<s:property value="paymentrequest.serialNumber"/>
               		</td>
               		<td class="center">
						<s:if test="receive">
							<s:property value="softType"/>
						</s:if>
						<s:else>
							<select id="select_<s:property value="id"/>">
								<s:iterator id="type" value="softTypes" >
									<option>${type}</option>
								</s:iterator>
							</select>					
						</s:else>
					</td>
               		<td class="center">
               			<s:if test="receive">
               				<s:property value="machineNo"/>
              			</s:if>
              			<s:else>
                			<input type="text" id="text_<s:property value="id"/>" name="machineNo" />
                		</s:else>
					</td>
					<td class="center"> 
						<s:property value="registerNo"/> 
					</td>
               		<td class="center">
                		<s:if test="receive">
                			<span class="red">已领取</span>
                		</s:if>
                		<s:else>
                			<!-- <a href="/software/index.htm?id=<s:property value="id"/>machineNo=>&action=getRegNo">领取</a>  -->
                			<a href="javascript:onSubmit(<s:property value="id"/>)">领取</a>
                		</s:else>
               		</td>
               		<td class="center">
               			<s:date name="registerTime" format="yyyy年MM月dd日"/>
              		</td>
               	</tr>
               </s:iterator>
		</table>
	</div>
</div>
</body>
</html>