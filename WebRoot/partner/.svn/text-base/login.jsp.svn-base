<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>伙伴查询系统-一彩票</title>
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
<div class="outer" style="margin-top:150px">
 	<div class="login_top"></div>
	<div class="login_body">
   	<div class="login_titileBg">
       	<div class="login_titile">欢迎登录一彩票伙伴查询系统</div>
	</div>
	
	<form id="login_form" action="/partner/login.htm" method="post">
		<input type="hidden" name="action" value="login"/>
		<div class="login_user" id="Land" style="display:block;">       	
			<div class="user_outer">
				<div class="user_name">用户名：</div>
				<div class="user_inputBg">
					<input id="nickname" name="nickname" class="user_input" type="text" />
				</div>
				<div class="user_input_instro">请输入您的伙伴名</div>
				<div class="clear"></div>
				<div class="user_name">密码：</div>
				<div class="user_inputBg">
					<input id="password" name="password" class="user_input" type="password"/>
				</div>
				<div class="user_input_instro">请输入密码，请注意大小写是否锁定</div>
				<div class="clear"></div>
				<div class="user_name">验证码：</div>
				<div class="user_inputBg">
					<input id="mngunm" name="mngunm" class="user_input" type="text" />
				</div>
				<div class="user_input_instro2"><span id="captchaImg"></span></div>
				<div class="user_input_instro1">看不清？<a href="javascript:refreshCaptcha()">换张图片</a></div>
				<div class="clear"></div>
				<div class="loadingBtn" style="margin-top:10px"><span id="message">${message}</span></div>
				<div class="clear"></div>
				<div class="loadingBtn" style="margin-top:0px">
					<input type="image" src="/images/369caibg/369login_16.gif" width="95" height="36" onclick="return checkLogin();"/>
				</div>
	    	</div>
	    </div>
    </form>
    </div>
   <div class="login_bottom"></div>
</div>
</body>
</html>
