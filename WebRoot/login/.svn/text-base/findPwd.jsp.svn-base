<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>369竟彩网_用户中心_找回密码</title>
<meta name="keywords" content="一彩票,网上投注,竞彩软件,竞彩,找回密码 一彩票登陆界面为你提供免费注册，QQ登录。">
<meta name="description" content="网上购彩就上一彩票，实现每个人心中500万的梦。竞彩就上一彩票！中国竞彩第一品牌，竞彩软件几百万球迷共同的选择。">
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="chart/favicon.ico" /> 
<link rel="stylesheet" href="../css/login-change.css">

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/util/login.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		refreshCaptcha(); 
	});
	
	function refreshCaptcha() {
		$('#captchaImg').html("<img src='/jcaptcha.jpg?"+Math.round(Math.random()*100000)
			+" height='40' width='80' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
</script>
</head>

<body>
<!-- head -->
<%@include file="login-head.jsp" %>
<div class="find-form-box">
    <div class="nifty">
       <div class="rtop">
         <div class="r1"></div>
         <div class="r2"></div>
         <div class="r3"></div>
         <div class="r4"></div>
       </div>
            <div class="findback_pwd_frame">
            <strong>一彩票--找回您的密码：</strong>
            <form name="form"  action="register.htm?action=callbackpwd" method="post">
                <table cellpadding="0" cellspacing="0" border="0" style="height:200px; width:650px">
                <tr> 
                    <td colspan="2" class="center"> <span class="red"> ${sysmsg} </span> </td>
                </tr>
                <tr>
                    <td class="right" style="width:110px;"> <span class="red">*</span>用户名：</td>
                    <td> <input type="text" class="user-name" id="nickname" name="nickname" />
                        <span id="imgnickname" class="gray">输入您注册时的用户名</span>
                    </td>
                </tr>
                
                <tr>
                    <td class="right"> <span class="red">*</span>身份证号码：</td>
                    <td> <input type="text" class="user-name" id="credentNo" name="credentNo" /> 
                        <span id="imgcard" class="gray">请输入您资料中的身份证号码，若未填写，请联系客服</span>
                    </td>
                </tr>
                
                <tr>
                    <td class="right"> <span class="red">*</span>电子邮箱：</td>
                    <td> <input type="text" class="user-name" id="email" name="email" /> 
                        <span id="imgemail" class="gray">请输入您注册时的电子邮箱</span>
                    </td>
                </tr>
                
                <tr>
                    <td class="right"> <span class="red">*</span>验证码：</td>
                    <td> <input type="text" class="checkNO" id="mngunm" name="mngunm" />
                        <span id="captchaImg"></span> 
                        <a onclick="refreshCaptcha()" style="cursor:pointer;">
                        <span id="imgmngunm">看不清，换一张</span></a>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2" class="center">
                        <input type="image" src="../images/369caibg/tijiao.png" style="margin-left:30px; margin-top:15px; border:1px solid #f90;" onclick="return checkPwd();" value="提交"/>
                    </td>
                </tr>
                </table>
            </form>
        </div>
        <div class="rtop">
         <div class="r4"></div>
         <div class="r3"></div>
         <div class="r2"></div>
         <div class="r1"></div>
       </div>
    </div>
</div>
<!-- foot --> 
<div class="clear"></div>
<%@include file="login-foot.jsp" %>
</div>
</body>
</html>