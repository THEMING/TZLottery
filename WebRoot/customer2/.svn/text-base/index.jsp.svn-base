<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心-一彩票</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.4.min.js"></script>
<script>
	var last_index = 1;
	$(document).ready(function()
		{
			var index = "${mainIndex}";
			var subIndex = "${subIndex}";
			
			if(index < 1) {
				index = 1;
			}
			if(subIndex < 1) {
				subIndex = 1;
			}
			
			$("#customer_fuction_list").slideToggle();
			
			last_index = index;
			tab(last_index, subIndex);
		}
	);
	
	function tab(index, subIndex) {
		$("#list"+last_index).attr("class","off");
		$("#list"+index).attr("class","on");
		last_index = index;
		
		if(index == 1) {
			if(subIndex > 1) {
				if(subIndex == 3)
					$("#cur_content_frame").attr("src", "/customer/MyFinancialInfo.htm?action=payHome");
				else 
					$("#cur_content_frame").attr("src", "/customer/MyFinancialInfo.htm?action=drawmoney");
			}
			else {
				$("#cur_content_frame").attr("src", "/customer/MyFinancialInfo.htm");
			}
		}
		else if(index == 2) {
			$("#cur_content_frame").attr("src", "/customer/MyInfo.htm");
		}
		else if(index == 3) {
			$("#cur_content_frame").attr("src", "/customer/MyBuyLotteryInfo.htm");
		}
		else if(index == 4) {
			$("#cur_content_frame").attr("src", "/customer/MyJoinGroupBuyInfo.htm");
		}
		else if(index == 5) {
			$("#cur_content_frame").attr("src", "/customer/MyGroupBuyInfo.htm");
		}
		else if(index == 6) {
			$("#cur_content_frame").attr("src", "/customer/MyChaseInfo.htm");
		}
		else if(index == 7) {
			$("#cur_content_frame").attr("src", "/customer/MyWinPrizeQuery.htm");
		}
		else if(index == 8) {
			$("#cur_content_frame").attr("src", "/customer/MySoftware.htm");
		}
		else if(index == 9) {
			$("#cur_content_frame").attr("src", "/customer/MyRecommenders.htm");
		}
	}
</script>
</head>
<body>
    <!-- head begin -->
    <%@include  file="/head.jsp"%>
    <div class="clear"></div>
    <div class="customer_center_main">
		<div class="left">
			<div class="title">用户中心</div>
			<div class="list_main">
				<ul id="customer_fuction_list" class="hidden">
					<li id="list1" class="on">
						<a href="#" onclick="tab(1)">资金管理</a>
					</li>
					<li id="list2">
						<a href="#" onclick="tab(2)">账号管理</a>
					</li>
					<li id="list3">
						<a href="#" onclick="tab(3)">我的代购</a>
					</li>
					<li id="list4">
						<a href="#" onclick="tab(4)">我参与的合买</a>
					</li>
					<li id="list5">
						<a href="#" onclick="tab(5)">我发起的合买</a>
					</li>
					<li id="list6">
						<a href="#" onclick="tab(6)">我的追号</a>
					</li>
					<li id="list7">
						<a href="#" onclick="tab(7)">中奖记录</a>
					</li>
					<li id="list8">
						<a href="#" onclick="tab(8)">软件注册</a>
					</li>
					<li id="list9">
						<a href="#" onclick="tab(9)">推荐人查询</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="right">
			<div class="top">
				当前用户: <span class="red">${customer.nickName}</span>
				<c:if test="${customer.usrType != '本地注册用户'}">
					<span class="gray">${customer.usrType}</span>
				</c:if>
			</div>
			<div class="content">
				<iframe id="cur_content_frame" class="content_style" scrolling="no" frameborder="0">
				</iframe>
			</div>
		</div>
    </div>
                
    <!-- foot begin -->
    <div class="clear"></div>
	<%@include file="/foot.jsp"%>
</body>
</html>

