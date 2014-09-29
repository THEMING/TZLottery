<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_用户中心</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script src="../js/jquery-1.4.4.min.js"></script>
<script>
	var last_index = 1;
	$(document).ready(function()
		{
			var login="${login}";
			if(login=="login")
			parent.location.href="/customer/";

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
			<div class="list_main">
				<ul id="customer_fuction_list" class="hidden">
                    <li style="color:#Fff; font-size:16px; font-weight:bold; font:'微软雅黑'; background:url(../images/369caibg/helpcenter_03.gif) repeat-x; margin-top:0px;">我的帐号</li>
					<li id="list1" class="on">
						<a href="#" onclick="tab(1)">资金管理</a>
					</li>
					<li id="list2">
						<a href="#" onclick="tab(2)">账号管理</a>
					</li>
                    <li style="color:#fff; font-size:16px; font-weight:bold; font:'微软雅黑';background:url(../images/369caibg/helpcenter_03.gif) repeat-x;">我的彩票</li>
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
                    <li style="color:#fff; font-size:16px; font-weight:bold; font:'微软雅黑'; background:url(../images/369caibg/helpcenter_03.gif) repeat-x;">我的推广</li>
					<li id="list8">
						<a href="#" onclick="tab(8)">软件注册</a>
					</li>
					<li id="list9">
						<a href="#" onclick="tab(9)">推广查询</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="right">
			<div class="top">
                 <div class="top-left">
                    <img src="../images/img/male.png" alt="一彩票彩民" width="62" height="82">
                 </div>
                 <div class="top-right">
                    <p>欢迎！ <span class="red">${customer.nickName}</span>
                    <c:if test="${customer.usrType != '本地注册用户'}">
                        <span class="gray">${customer.usrType}</span>
                    </c:if>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    您的账户余额：<span class="red">￥${customer.wallet.balance}</span>元
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="tab(1,3)" style="color:#f30"><img src="../images/img/chongzhi.png" alt="提款"> 充值</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="tab(1,4)"style="color:#3366ff"><img src="../images/img/tikuan.png" alt="提款"> 提款</a>&nbsp;&nbsp;&nbsp;&nbsp;
                   
                    <hr style="border:1px dashed #ccc;margin-top:8px; width:600px; float:left;"/><br/>
                    账户总额：<span class="red">￥${customer.wallet.balance+customer.wallet.freezeMoney}</span>元&nbsp;&nbsp;&nbsp;&nbsp;
                    累计中奖：<span class="green">￥${customer.allWinMoney}</span>元<br>
                    <font style="line-height:2;">共推荐<SPAN class="red">${num}</SPAN>人 总计：一级推荐提成：<span class="red">￥${totalSumMoney1}</span>元，二级推荐提成：<span class="red">￥${totalSumMoney2}</span>元，总提成：<span class="red">￥${totalSumMoney1 + totalSumMoney2}</span>元</font><br/>
                       <ul id="hot-newsscroll">
                        <s:iterator value="page.result">
                          <li><img src="../images/img/loud_speaker.png" alt="热点资讯">&nbsp;[中奖公告]${fn:substring(userName,0,2) }**&nbsp;&nbsp;&nbsp;&nbsp;${type}&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="winTime" format="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;<span>${bonus }</span>元</li>
                          </s:iterator>
                        </ul>
                        <script type="text/javascript">
                        window.onload = function() {
                         dMarquee('hot-newsscroll');
                        }
                        
                        function dMarquee(id){
                         var speed=20; //速度
                         var stop=6000; //停止时间 
                        
                         var ul = document.getElementById(id);
                         var rows=ul.getElementsByTagName('li').length;
                         var height = ul.getElementsByTagName('li')[0].offsetHeight;
                        
                         ul.innerHTML += ul.innerHTML;
                        
                         var timeID = false;
                         
                         var play = function() {
                          ul.scrollTop++;
                        
                          if(ul.scrollTop==rows*height){
                           ul.scrollTop=0;
                          }
                        
                          if(ul.scrollTop%height==0) {
                           timeID = setTimeout(play,stop);
                          }else{
                           timeID = setTimeout(play,speed);
                          } 
                         }
                        
                         timeID = setTimeout(play,stop);
                        
                        
                         ul.onmouseover = function() {clearTimeout(timeID);}
                         ul.onmouseout = play;
                        }
                        
                        </script> 
        
                   </div>
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

