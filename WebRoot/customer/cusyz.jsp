<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心-一彩票</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/util/validate.js"></script>
<script>
	var tab_num = 2;	
	function allTogOff() {
		for(var i = 1; i <= tab_num; i++) {
			$("#li"+i).attr("class", "off");
			$("#main"+i).hide();
		}
	}
	
	function togOn(index) {
		allTogOff();
		$("#li"+index).attr("class", "on");
		$("#main"+index).show();
		iframeResize(index);
	}
	
	$(document).ready(function()
		{
			togOn(1);
			var message="${message2}"
			if(message=="绑定成功!"){
			alert(message);
			location.href="/customer/MyInfo.htm"}
		}
	);
	
	//调整父页面中IFrame的高度为此页面的高度
	function iframeResize(index) {
		var obj = parent.document.getElementById("cur_content_frame");
		obj.height = $("#material_panel").height() + 20;
	}
	function bound(){
	if(!checkPhone($("#mobileNo1").val())){
	alert("提示：请正确填写手机号码!")
	return false;}
	location.href="/customer/MyInfo.htm?action=sjbd"
	}
</script>
</head>
<body>
<div>
	<div id="material_panel" class="tab_box">
		<div class="tab_top">
			<ul id="material_panel_ul"> 
				<li id="li1" onclick="togOn(1)">手机验证</li>
			</ul>
		</div>
        <div class="clear"></div>
        <div id="main1" class="main">
	        <form action="/customer/MyInfo.htm" method="post">
	        <input type="hidden" name="action" value="sjbd"></input>
	         <span id="message1" class="red"> ${message2} </span>
	        <table cellpadding="0" cellspacing="0" border="0" width="669" height="75"> 
				<tr> 
					<td style="text-align: right;">手机号码:</td>
					<td>
						${customer.mobileNo }
					</td>		
				</tr>
				<tr>
				<td style="text-align: right;">验证码:</td>
				<td ><input type="text"  class="text" id="yzm" name="yzm"/>
				</td>	
			</table>
			<input type="submit" value="确 定" />
			</form>
        	<div class="clear"></div>
        </div>
    </div>
</div>  
</body>
</html>