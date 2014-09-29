<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>伙伴查询系统</title>
	<link href="../css/default.css" rel="stylesheet" type="text/css"/>
	<link href="../css/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<style>   
		#foldheader{cursor:hand; font-weight:bold;list-style-image:url(images/fold.gif); } 
		#foldinglist {margin:0px; padding-left:12px; padding-bottom:10px;list-style-image:url(images/list.gif); line-height:18px; } 
	</style>   
</head>

<body style="text-align:center">
<div style="width: 1100px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;">
	<div style="float:left; margin-top:20px; margin-bottom:20px;">
		<span style="font-size:20px;">欢迎！</span>
	</div>
	<div class="clear"></div>
    <div style="width:120px; float:left">
	   <div style="font-size:16px; padding:10px"><span>操作中心</span></div>
	   <div class="menu" style="width:158px;" align="left">
	   <table width="147" align="center" border="0" cellpadding="0" cellspacing="0">
			<tbody>
			  <tr>
				<td width="10%" align="center"><img src="../oss/skin/01/images/arrow_add.gif" width="7" height="12"></td>
				<td style="padding-top: 5px;" valign="bottom" width="90%">
					<a href="/partner/login.htm?action=password" target="mainframe">密码修改</a>
				</td>
			  </tr>
			  <tr>
				<td width="10%" align="center"><img src="../oss/skin/01/images/arrow_add.gif" width="7" height="12"></td>
				<td style="padding-top: 5px;" valign="bottom" width="90%">
					<a href="/partner/buyLotteryQuery.htm" target="mainframe">购彩查询</a>
				</td>
			  </tr>
			</tbody>
		</table>
	</div>
	</div>
	<div style="float:left; width:980px">
    	<iframe width="100%" name="mainframe" id="mainFrame" frameborder="0" scrolling="no"></iframe>
   	</div>
</div>
</body>
</html>