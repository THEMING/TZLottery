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
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="../css/thickbox.css" />
<link rel="stylesheet" href="../css/login-change.css" />

<style type="text/css">
	input {
	width: 200px;
	height: 20px;
	margin-bottom: 5px;
}
</style>

<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/thickbox.js"></script>
<script type="text/javascript" src="../js/qq_Login.js"></script>
<script language="javascript">
		$(document).ready(function()
		{
			if($("#IDSESS").val() == "openId没有用户")
			{
				$("#qq_zhuce").click();
			}
			$("#qq_zhuce").click();
		});
</script>
</head>
<body >
	<input type="hidden" id = "IDSESS" value="${message }" />
    <div id="qqzhuce" style="display:none;">
    	<input id="qq_zhuce" class="thickbox" type="button" value="显示" title="欢迎来到一彩票" alt="#TB_inline?height=280&width=680&inlineId=hiddenModalContent" />
		<div id="hiddenModalContent" style="display:none;">
			<div>
				<p style="text-align: center; font-size: 16px;margin-top: 10px;"><strong id="tcp_qq_xinxi">这是你第一次登录到一彩票！热烈欢迎</strong></p>
			</div>
			<div style="width: 310px; margin:10px;float: left;" id="tcp_changjian">
				<p style="text-align: center;">创建新的用户:</p>
				<p id="chuangjian_h" style="color: green;text-align: center;">创建成功后，你既可以使用QQ登录，<br />也可使用用户名、密码登录哦！</p>
				<div id="chuangjian_s" style="display: none;color: red;">
				</div>
				请输入用户名：<input id="qq_name" type="text" style="width: 100" /><br />
				请输入密码：&nbsp;&nbsp;&nbsp;<input id="qq_pwd1" type="password" style="width: 100" /><br />
				请输入邮箱：&nbsp;&nbsp;&nbsp;<input id="qq_email" type="text"" style="width: 100" /><br />
				<p style="text-align: center;"><a id="qq_chuangjian" href="javascript:;"><img src="../images/qq101.jpg"></img></a></p>
			</div>
			<div style="width: 310px; margin:10px; float: right;" id="tcp_bangding">
				<p style="text-align: center;margin-top: 15px;">绑定已有的账号:</p>
				<p id="bangding_h" style="color: green;text-align: center;">绑定成功后，您的QQ账号将与<br />该账号绑定，请确定账号是否正确！<br /></p>
				<div id="bangding_s" style="display: none;color: red;">
				</div>
				请输入用户名：<input id="qq_nickname" type="text" style="width: 100"/><br />
				请输入密码：&nbsp;&nbsp;&nbsp;<input id="qq_pwd2" type="password" style="width: 100" /><br />
				<p style="text-align: center;"><a id="qq_bangding" href="javascript:;"><img src="../images/qq102.jpg"></img></a></p>
				
			</div>
		</div>
    </div>
</body>
</html>
