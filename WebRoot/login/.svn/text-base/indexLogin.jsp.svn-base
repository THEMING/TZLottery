<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" href="../css/common.css" type="text/css"/>
<link rel="stylesheet" href="../css/login.css" type="text/css"/>
<link rel="stylesheet" href="../css/default.css" type="text/css"/>
<link rel="stylesheet" href="../css/css.css" type="text/css"/>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
var login="${login}";
if(login=="login")
parent.location.href="/";
})
	function go(link) {
		parent.location.href=link;
	}
</script>
</head>
<body>
<div class="loginFrame"> 
	<form action="/login.htm" method="post" id="loginForm">
    	<!--登陆之后 start-->
        <div class="loaded">
        	<div class="loadedName">${customer.nickName}</div>
            <div class="loadedAvailable">${customer.wallet.balance}元</div>
            <div class="RechargeBtn"><input type="image" src="images/369caibg/Recharge.gif" onclick="go('/customer/index.htm?subIndex=3')" value="充值" /></div>
            <div class="getmoneyBtn"><input type='image' src="images/369caibg/getmoney.gif" onclick="go('/customer/index.htm?subIndex=4')" value='提现' /></div>
            <div class="gotoMemberCenter">
            	<ul>
                	<li><a href="/customer/" target="_blank">进入个人中心</a></li>
                    <li><a href="/login.htm?action=indexLogout" style="color:#FF0000;">退出</a></li>
                </ul>
            </div>
        </div>
        <!--登陆之后 end-->
	</form>
</div>
</body>
</html>
