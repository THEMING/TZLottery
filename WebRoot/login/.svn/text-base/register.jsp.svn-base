<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票票网_注册</title>
<meta name="keywords" content="一彩票票网,网上投注,竞彩软件,竞彩,免费注册 一彩票票网登陆界面为你提供免费注册，QQ登录。">
<meta name="description" content="网上购彩就上一彩票票网，实现每个人心中500万的梦。竞彩就上一彩票票网！中国竞彩第一品牌，竞彩软件几百万球迷共同的选择。">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<link rel="stylesheet" href="../css/weblogin.css" type="text/css" />

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/util/login.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		refreshCaptcha(); 
	});
	
	function refreshCaptcha() {
		$('#captchaImg').html("<img src='/jcaptcha.jpg?"+Math.round(Math.random()*100000)
			+" height='20' width='50' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
	
</script>
</head>

<body>
<div class="register">
<!-- head -->
<%@include  file="../head.jsp"%>
<div class="clear"></div>
<div class="outer">
	<!--用户登陆和注册 start-->
 	<div class="login_top">
   		<div class="login_welcome">欢迎来到一彩票票网</div>
	</div>
	<div class="login_body">
		<div class="login_titileBg">
	       	<div class="login_titile">欢迎您注册成为一彩票用户，请填写以下信息完成注册：</div>
		</div>
	    <!--注册 start-->
	    <form name="form" action="register.htm?action=reg" method="post">
	    <div class="login_user">
	     	<div class="Reg_outer">
			<div class="user_name">用户名：</div>
			<div class="user_inputBg">
				<input class="user_input" type="text" id="nickname" name="nickname" onblur="checkName();" value="${nickname}"/>
			</div>
			<div class="user_input_instro"><span id="imgnickname" class="gray">6-16位字符，可由中英文、数字及&quot;_&quot;、&quot;-&quot;组成</span></div> 
			<div class="clear"></div>
			<c:if test="${superiorCus == null}">
				<div class="user_name" style="color:gray">推荐人：</div>
				<div class="user_inputBg">
					<input class="user_input" type="text" id="superior" name="superior" onblur="checkSuperior();" value="${superior}"/>
				</div>
				<div class="user_input_instro"><span id="imgsuperior" class="gray">(选填)输入推荐您的用户的用户名</span></div> 
				<div class="clear"></div>
			</c:if>
			<c:if test="${superiorCus != null}">
				<input type="hidden" name="superiorCus.id" value="${superiorCus.id}"/>
			</c:if>
			<div class="user_name">密码：</div>
			<div class="user_inputBg">
				<input class="user_input" type="password" id="password" name="password" />
			</div>
			<div class="user_input_instro"><span id="imgpwd" class="gray">6-16位字符，可由中英文、数字及&quot;_&quot;、&quot;-&quot;组成</span></div>
			<div class="clear"></div>
			<div class="user_name">再次输入密码：</div>
			<div class="user_inputBg">
				<input class="user_input" type="password" id="repassword" name="repassword" />
			</div>
			<div class="user_input_instro"><span id="imgrepwd" class="gray">请再次输入您的登录密码</span></div>
			<div class="clear"></div>
			<div class="user_name">邮箱：</div>
			<div class="user_inputBg">
				<input class="user_input" type="text" id="email" name="email" value="${email}"/>
			</div>
			<div class="user_input_instro"><span id="imgemail" class="gray">接收账户信息、订单通知、促销活动、优惠券等</span></div>
			<div class="clear"></div>
			<div class="user_name">验证码：</div>
			<div class="user_inputBg">
				<input class="user_input" type="text" id="mngunm" name="validnum" />
			</div>
	    	<div class="user_input_instro2"><span id="captchaImg"></span></div>
	 	  	<div class="user_input_instro1">看不清？<a href="javascript:refreshCaptcha()" style="cursor:pointer;">换张图片</a></div>  
	    	<div class="clear"></div>
			
			<div class="loadingBtn" style="margin-top:0px"> <span class="red"> ${sysmsg} </span></div>
			<div class="clear"></div>
			
			<div class="loadingBtn">
				<input type="image" src="/images/369caibg/369login_20.gif" onclick="return registerCheck();"/>
			</div>
			<div class="check_agreement">
				<input id="agree_rule" type="checkbox" value="" checked="checked"/>
			</div>
			<div class="check_agreementtext"><a href="register.htm?action=read">我已阅读注册协议</a></div>
		</div>
	    </div>
	    </form>
	    
	    <!--注册 end-->
	</div>
	<div class="login_bottom"></div>
</div>


<div class="clear"></div>
<%@include file="../foot.jsp"%>
</div>
</body>
</html>
