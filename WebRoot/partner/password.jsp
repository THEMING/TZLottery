<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>合作查询系统-一彩票</title>
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<link rel="stylesheet" href="../css/weblogin.css" type="text/css" />

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#loginPage").keypress( function(e) {
			if(e.which == 13){
				$("#login_form").submit();
			}
		});
		refreshCaptcha();
	});
	
	function refreshCaptcha() {
		$('#captchaImg').html("<img src='/jcaptcha.jpg?"+Math.round(Math.random()*100000)
			+" height='20' width='50' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
</script>
</head>

<body>
<div class="main">
	<form action="/partner/login.htm" method="post">
		<input type="hidden" name="action" value="savepwd"></input>
		<table cellpadding="0" cellspacing="0" border="0" width="669" height="150"> 
		<tr> 
			<td style="text-align: right; width:150px">用户名：</td>
			<td>${partner.nickName}</td>
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
			
			<tr >
				<td ></td>
				<td align="left">
					<span id="message2" class="red"> ${message} </span>
				</td>
			</tr>
			
			<tr >
				<td ></td>
				<td align="left">
					<input type="submit" value="确 定" onclick="return checkChangePwd()"/>
				</td>
			</tr>
		</table>
	</form>
	<div class="clear"></div>
</div>
</body>
</html>