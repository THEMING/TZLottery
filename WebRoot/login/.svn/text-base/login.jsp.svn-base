<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>369竟彩网__用户登录</title>
<meta name="keywords" content="369竟彩网,网上投注,竞彩软件,竞彩,用户登录 369竟彩网登陆界面为你提供免费注册，QQ登录。">
<meta name="description" content="网上购彩就上369竟彩网，实现每个人心中500万的梦。竞彩就上369竟彩网！中国竞彩第一品牌，竞彩软件几百万球迷共同的选择。">
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/login-change.css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
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
			+" height='30' width='60' onclick='refreshCaptcha()' style='cursor:hand'/>");
	}
	
</script>
</head>
<body topmargin=0 leftmargin=0>
<center>
<!--top start-->
<div class="body-bg">
    <div class="logo-cont">
        <div class="login-logoCont">
           <ul>
              <li><a href="/" target="_blank" onfocus="this.blur()"><img src="../images/img/hc_logo.jpg" alt="一彩票LOGO" /></a></li>
              <li class="login-NAME">用户登录</li>
           </ul>
</div>
         <div class="login-link-INDEX">
            <a href="/" style="color:#f60;" onfocus="this.blur()">369CAI首页</a> | <a href="#" style="color:#f60;" onfocus="this.blur()">在线客服</a></div></div></div>
<!--top end-->
<div class="clear"></div>
<!--cont start-->
<div class="login-main" id="main-bg">
  <div class="login-box">
    <div class="login-title"> 369CAI用户登录 </div>
    <form id="login_form" class="login-form" action="/login.htm" method="post">
      <ul>
        <li>
          <input type="hidden" name="action" value="login"/>
        </li>
        <li>用户名：
          <input name="nickname" type="text" id="nickname" class="user-name"/>
        </li>
        <li>密&nbsp;&nbsp;&nbsp;码：
          <input name="password" type="password" id="password" class="user-name"/>
        </li>
        <li>验证码：
          <input id="mngunm" name="mngunm" class="checkNO" type="text" />
          <span id="captchaImg"></span><span style="font-size:12px; color:#3366ff;"><a href="javascript:refreshCaptcha()" style="color:#3366ff;" onfocus="this.blur()">换张图片</a></span></li>
        <input type="image" src="../images/login-BTN.png" style="margin-left:30px; margin-top:15px;" onclick="return checkLogin();" onfocus="this.blur()"/>
        <a href="/register.htm?action=callbackpwd" target="_blank" style="color:#f60;margin-top:90px; margin-left:50px;" onfocus="this.blur()">忘记密码？</a>
     	<li>你还可以使用QQ登录：<a onclick="return openQQLogin('/qqlogin.htm', '2')" href="javascript:;"><img src="../images/qqlogo_3.png" /></li>
      </ul>
      <div class="loadingBtn" style="margin-top:10px"><span id="message">${message}</span></div>
    </form>
    <div class="login-register">还没有369CAI账户？立即<a href="/customer/register.htm" style="color:red;" onfocus="this.blur()">免费注册&gt;&gt;</a></div>
  </div>
</div>
<script type="text/javascript">
//产生随机数。
rnd.today=new Date(); 
rnd.seed=rnd.today.getTime(); 
    function rnd() { 
　　　　rnd.seed = (rnd.seed*9301+49297) % 233280; 
　　　　return rnd.seed/(233280.0);
    }; 
    function rand(number) { 
　　　　return Math.ceil(rnd()*number); 
    }; 

//通过随机数改变main-bg的背景图片
var n=rand(4);//实例产生了一个0-3的随机数
if(n==0){//判断产生的随机数，然后更换即可。
document.getElementById("main-bg").style.backgroundImage="url(../images/login-main_07.png)";
}
else if (n==1){
document.getElementById("main-bg").style.backgroundImage="url(../images/login-main_01.png)";
}else if (n==2){
document.getElementById("main-bg").style.backgroundImage="url(../images/login-main_06.png)";
}else if (n==3){
document.getElementById("main-bg").style.backgroundImage="url(../images/login-main_07.png)";
}else if (n==4){
document.getElementById("main-bg").style.backgroundImage="url(../images/login-main_08.jpg)";
};
</script>
<!--cont end-->
<div class="clear"></div>
<!--bottom start-->
<div class="login-bottom">
    COPYRIGHT 2002-2011 369cai ALL RIGHT RESERVED<br/>  

版权所有©北京比特太奇科技有限公司   ICP许可：京ICP 备05022195 著作权声明 隐私保护<br/>

郑重提示：彩票有风险，投资需谨慎，不向未满18周岁的青少年出售彩票 
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fbd0a40a63edb615722f22f4239f42596' type='text/javascript'%3E%3C/script%3E"));
</script><br/>
</div>
</center>
</body>
</html>
