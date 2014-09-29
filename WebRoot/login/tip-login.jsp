<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="../css/common.css" type="text/css"/>
<link rel="stylesheet" href="../css/login-change.css" type="text/css"/>
<link rel="stylesheet" href="../css/default.css" type="text/css"/>
<link rel="stylesheet" href="../css/css.css" type="text/css"/>
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
			+" height='40' width='80' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
	
</script>
</head>

<body>
<div class="tip-login-box">
  <div class="tip-login-title">
    <ul>
      <li class="login-words">用户登录</li>
      <li class="close-box"><img src="../images/cross.png" class="img-change" width="30" height="30" alt="关闭" /></li>
    </ul>
  </div>
  <div class="clear"></div>
  <form id="login_form" class="tip-form" action="/login.htm" method="post">
     <p>用户名： <input name="input" type="text" id="nickname" class="user-name" /><br /></p>
     <p>密&nbsp;&nbsp;&nbsp;码： <input name="TIPnickname" type="text" id="nickname" class="user-name" /><br /></p>
     验证码： <input name="input" type="text" id="nickname" class="checkNO" /><span id="captchaImg"></span><span style="font-size:12px; color:#3366ff;">看不清？<a href="javascript:refreshCaptcha()">换张图片</a></span><br/>
      <div class="loadingBtn" style="margin-top:15px"><span id="message">${message}</span></div>
       <input type="image" src="../images/login-BTN.png" style="margin-left:30px; margin-top:15px;" onclick="return checkLogin();"/><a href="/register.htm?action=callbackpwd" target="_blank" style="color:#f60;margin-top:90px; margin-left:50px;">忘记密码？</a><br />
       <hr style=" margin:0 auto; width:350px; border:1px dashed #ccc;">
 	   <div class="tip-login-register">还没有369CAI账户？立即<a href="/customer/register.htm" style="color:red;">免费注册&gt;&gt;</a> | 合作登录：<img src="../images/img/callcenter_1.gif" alt="QQ登录" style="width:15px; height:18px; paddingleft:10px;"/> <a href="#" target="_blank" style="color:#0f3f94;padding-top:10px;">QQ登录</a></div>
  </form>
</div>
</body>
</html>