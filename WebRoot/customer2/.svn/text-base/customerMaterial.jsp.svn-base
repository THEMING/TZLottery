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
			var index = "${tabIndex}";
			togOn(index);
			
			var hasRealName = "${customer.realName}";
			if(hasRealName != "") {
				$("#tdRealName").text("${customer.realName}");
				$("#tdCredentNo").text("${customer.credentNo}");
			}
			else {
				$("#tdRealName").html("<input type=\"text\" class=\"text\" name=\"realName\" id=\"realName\"/> <span class=\"red\">领奖和提款的重要依据！填写后不可修改</span>");
				$("#tdCredentNo").html("<input type=\"text\" class=\"text\" name=\"credentNo\" id=\"credentNo\"/> <span class=\"red\">领奖人身份证号码，须与姓名一致，填写后不可修改</span>");
			}
		}
	);
	
	//调整父页面中IFrame的高度为此页面的高度
	function iframeResize(index) {
		var obj = parent.document.getElementById("cur_content_frame");
		obj.height = $("#material_panel").height() + 20;
	}
</script>
</head>
<body>
<div>
	<div id="material_panel" class="tab_box">
		<div class="tab_top">
			<ul id="material_panel_ul"> 
				<li id="li1" onclick="togOn(1)">我的资料</li>
				<li id="li2" onclick="togOn(2)">密码修改</li>
			</ul>
		</div>
        <div class="clear"></div>
        <div id="main1" class="main">
	        <form action="#" method="post">
	        <input type="hidden" name="action" value="zlxg"></input>
	        <span id="message1" class="red"> ${message1} </span>
	        <table cellpadding="0" cellspacing="0" border="0" width="669" height="175"> 
				<tr> 
					<td style="text-align: right; width:150px">用户名：</td>
					<td>${customer.nickName}</td>
				</tr> 
				<tr> 
					<td style="text-align: right;">真实姓名：</td>
					<!--<s:if test="customer.realName != null">
						<td>${customer.realName}</td>
					</s:if>
					<s:else> 
						<td><input type="text" class="text" name="realName" id="realName"/>
						<span class="red">领奖和提款的重要依据！填写后不可修改</span></td>
					</s:else> -->
					<td id="tdRealName"></td>
				</tr> 
				<tr> 
					<td style="text-align: right;">身份证号码：</td>
					<!--<s:if test="customer.credentNo != null">
						<td>${customer.credentNo}</td>
					</s:if>
					<s:else> 
						<td><input type="text" class="text" name="credentNo" id="credentNo"/>
						<span class="red">领奖人身份证号码，须与姓名一致，填写后不可修改</span></td>
					</s:else> -->
					<td id="tdCredentNo"></td>
				</tr> 
				<tr> 
					<td style="text-align: right;">电子邮箱：</td>
					<td>
						<input type="text" class="text" id="email" name="email" value="${customer.email}"></input>
						<span id="">忘记密码时通过邮箱方式取回密码</span>
					</td>
				</tr> 
				<tr> 
					<td style="text-align: right;">手机号码：</td>
					<td>
						<input type="text" class="text" id="mobileNo" name="mobileNo" value="${customer.mobileNo}"></input>
						<span id="">便于您中大奖时及时通知您。</span>
					</td>
				</tr>
				<tr> 
					<td style="text-align: right;">登录密码：</td>
	          		<td>
	              		<input type="password" class="text" name="password" id="password"/>
	              		<span id="">为确保账号安全，请输入登录密码进行验证</span>
              		</td>
            	</tr>
			</table>
			<input type="submit" value="确 定" onclick="return checkChangeMaterial()"/>
			</form>
        	<div class="clear"></div>
        </div>
        
        <div id="main2" class="main hidden">
        	<form action="#" method="post">
	        <input type="hidden" name="action" value="mmxg"></input>
	        <span id="message2" class="red"> ${message2} </span>
        	<table cellpadding="0" cellspacing="0" border="0" width="669" height="150"> 
				<tr> 
					<td style="text-align: right; width:150px">用户名：</td>
					<td>${customer.nickName}</td>
				</tr> 
				<tr> 
					<td style="text-align: right;">原登录密码：</td>
					<td>
						<input type="password" class="text" id="oldpassword" name="oldpassword"></input>
						<span id="">请输入原登录密码</span>
					</td>
				</tr> 
				<tr> 
					<td style="text-align: right;">新登录密码：</td>
					<td>
						<input type="password" class="text" id="newpassword" name="password"></input>
						<span id="" >由6～15位字母和数字组成。建议使用数字和字母的混合密码</span>
					</td>
				</tr> 
				<tr> 
					<td style="text-align: right;">确认新密码：</td>
					<td><input type="password" class="text" id="repassword" name="repassword"></input></td>
				</tr> 
			</table>
			<input type="submit" value="确 定" onclick="return checkChangePwd()"/>
			</form>
        	<div class="clear"></div>
        </div>
    </div>
</div>  
</body>
</html>