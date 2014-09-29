<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>登陆</title>
	<link rel="stylesheet" href="../css/css.css" type="text/css" />
	<link rel="stylesheet" href="../css/default.css" type="text/css" />
	<style type="text/css">
		.text 
		{
			width:130px; height:20px; border:1px #b5b5b5 solid; background:#fff;
		}
    </style>
    <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/lottery/common.js"></script>
</head>

<body>
<div class="login">
<form id="loginForm" action="/login.htm" method="post">
	<input type="hidden" name="action" value="indexLogin"/>
	<div class="loginTitileBg">
    	<div class="lTitile">会员登录</div>
    </div>
    <div class="loginName">用户名：</div>
    <div class="loginInput">
    	<input type="text" name="nickname" id="nickname" class="text" value=""/>
    	<!-- <div id="searchtip" style="position:relative;color:#ccc"> 
   			<div style="position:absolute;top:-20px;left:4px">请输入信息</div> 
		</div>  -->
    </div>
    <div class="loginName">密&nbsp;&nbsp;&nbsp;码：</div>
    <div class="loginInput">
    	<input type="password" name="password" id="password" class="text" />
    </div>
    <div class="loginName">验证码：</div>
    <div class="loginInput">
    	<input type="text" name="mngunm" id="mngunm" class="text" style="width:50px"/>
    	<span id="captchaImg"></span>
    </div>
    <div class="loginNum">
    	<ul>
        	<li class="lNFont1"></li>
            <li><a class="lNFont2" href="javascript:refreshCaptcha()">换一张</a></li>
            <li><a class="lNFont3" href="/register.htm?action=callbackpwd" target="_blank">找回密码</a></li>
        </ul>
    </div>

    <div class="loginBtn">
    	<input type="image" src="../images/369caibg/369cai_25.gif" onclick="return checklogin()"/> 
    </div>
    <div style="margin-left:10px;float:left;height:31px;margin-top:10px; display:inline; "><a onclick="return openQQLogin('/qqlogin.htm', '1')" href="javascript:;"><img src="../images/QQLogo.png" /></a></div>
    <div class="loginBtn regsiterBtn" style="margin-left: 10px">
    	<input type="image" src="../images/369caibg/369cai_27.gif" onclick="return go('/register.htm')"/>
    </div>
</form>
</div>
</body>
</html>

<script type="text/javascript">
	jQuery(document).ready(function() {
		var msg = "${message}";
		if(msg){
			alert(msg);
		}
		$("#loginPage").keypress( function(e) {
			if(e.which==13){
				if(checklogin()) {
					$("#loginForm").submit();
				}
			}
		});
		refreshCaptcha();
	});
	
	function refreshCaptcha() {
		$('#captchaImg').html("<img src='/jcaptcha.jpg?"+Math.round(Math.random()*100000)
			+" height='20' width='50' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
	
	function checklogin() {
		if(!$("#nickname").val()) {
			alert("登录用户名不能为空");
			return false;
		}
		if(!$("#password").val()) {
			alert("登录密码不能为空");
			return false;
		}
		if(!$("#mngunm").val()) {
			alert("验证码不能为空");
			return false;
		}
		return true;
	}
	
	function go(link){
		parent.location.href=link;
		return false;
	}

  </script>
