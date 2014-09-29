<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>一彩票</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<LINK href="/chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<!--[if IE 6]><script type="text/javascript" src="/js/ie6.js"></script><![endif]-->
<!--改变jiathis名称-->
<!-- JiaThis Button BEGIN -->
<script type="text/javascript">
	var jiathis_config = {
		siteName:"一彩票"
	}
</script>
<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
<!-- JiaThis Button END -->
<!--save box js start-->
<script type="text/javascript">
function addBookmark(title,url) {
if (window.sidebar) { 
window.sidebar.addPanel(title, url,""); 
} else if( document.all ) {
window.external.AddFavorite( url, title);
} else if( window.opera && window.print ) {
return true;
}
}
</script> 
<!--save box js end-->

<!--网站导航下拉菜单 start-->
<script type="text/javascript">
<!--
var timeout         = 150;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out

// -->
</script>
<!--网站导航下拉菜单 end-->
</head>
 
<body onload="selectNav()">


<div id="dialog-form" title="登录" style="display:none">
 
<FIELDSET><LABEL for=nickname>用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="nickname2" class="text ui-widget-content ui-corner-all" name="nickname2" value=""/> <br/> <LABEL 
for=password>密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="password2" class="text ui-widget-content ui-corner-all" type=password name="password2"/> <br/>
<LABEL for=mngunm>验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="mngunm2" class="text ui-widget-content ui-corner-all" type="text" name="mngunm2"/> 
<span id="captchaImg2"></span><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:refreshCaptcha2()">换张图片</a><br/><span id="message2">${message}</span>
</FIELDSET> <a href="/customer/register.htm">没有账号？现在就注册</a>
</div>
<!--Web Nav end-->
<!--top area start-->
<div id="page">
	<div class="chl-poster simple" id=header>
		<div id=site-nav>
            <UL class="CUSTOMER">
                <c:if test="${customer.nickName == null}">
                    <li>您好，欢迎来到一彩票票网！</li>
                    <li><a href="/customer/" target="_blank" style="color:#3A5FCD">请登录</a> |</li>
                    <li><a onclick="return openQQLogin('/qqlogin.htm', '1')" href="javascript:;"><img src="../images/qqlogo_1.png" /></a></li>
                    <li><a href="/register.htm" target="_blank" style="color:#FF7F00">免费注册</a></li>
                </c:if>
                <c:if test="${customer.nickName != null}">
                    <li>您好，<span>${customer.nickName}</span>，欢迎来到一彩票票网！</li>
                    <li><a href="/login.htm?action=logout" style="color:#FF0000;">注销</a></li>
                </c:if>
            </UL>
			<UL class=quick-menu>
			  <LI class=home><A href="/">369竞彩首页</A> </LI>
			  <LI><A href="javascript:window.external.AddFavorite('http://www.369cai.com/','一彩票彩票网-网络购彩就上一彩票')" onclick="window.external.AddFavorite('http://www.369cai.com/','一彩票彩票网-网络购彩就上一彩票')">加入收藏</A></LI>
			  <LI class="mytaobao menu-item">
				  <div class=headmenu>
					  <A class=menu-hd href="/customer/" target=_top rel=nofollow>用户中心<B></B></A> 
					  <div class=menu-bd>
						  <div class=menu-bd-panel>
							  <div>
								  <A href="/customer/" rel=nofollow>账户余额</A><br />
								  <A href="/customer/" rel=nofollow>中奖记录</A> 
							  </div>
						  </div>

					  </div>
				  </div>
			  </LI>
			  <LI class="favorite"><A href="/help/index.htm" target="_blank" rel=nofollow>帮助中心</A></LI>
			  <LI class="services menu-item">
				  <div class=headmenu>
					  <A class=menu-hd href="#" target=_top>网站导航<B></B></A> 
					  <div class=menu-bd style="WIDTH: 210px; HEIGHT: 242px; _width: 202px;">
						  <div class=menu-bd-panel>
                              <div style="padding-bottom:10px; margin-top:15px;"><strong style="color:#F60;font-weight:bold;">购彩</strong><br />
                 <a href="lottery/JCZQIndex.htm" target="_blank">足球胜平负</a> &nbsp;|&nbsp; <a href="lottery/ZJQSIndex.htm" target="_blank">足球总进球</a> &nbsp;|&nbsp; <a href="lottery/BQCIndex.htm" target="_blank">足球半全场</a> &nbsp;|&nbsp; <a href="lottery/CBFIndex.htm" target="_blank">足球猜比分</a>
                 &nbsp;|&nbsp; <a href="lottery/JCLQSFIndex.htm" target="_blank">篮球胜负</a> &nbsp;|&nbsp; <a href="lottery/JCLQRFSFIndex.htm" target="_blank">篮球让分胜负</a> &nbsp;|&nbsp; <a href="lottery/JCLQSFCIndex.htm" target="_blank">篮球胜分差</a> &nbsp;|&nbsp; <a href="lottery/JCLQDXFIndex.htm" target="_blank">篮球大小分</a>
                  &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=ssq" target="_blank">双色球</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=dlt" target="_blank">大乐透</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=14sfc" target="_blank">14场</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=r9" target="_blank">任9场</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=4cjq" target="_blank">进球彩</a></div>
                  <div style="padding-top:10px; border-top:1px solid #eee;"><strong style="color:#F60;font-weight:bold;">彩票资讯</strong><br />
                
                <a href="/prizedetail/" target="_blank">开奖数据</a>  &nbsp;|&nbsp; <a href="/direction/fbt.htm?type=ssq" target="_blank">走势图表</a>  &nbsp;|&nbsp; <a href="/huodong.html" target="_blank">优惠活动</a>  
                <a href="/help/" target="_blank">帮助中心</a>  &nbsp;|&nbsp; <a href="/software/" target="_blank">彩票软件</a></div>

                <dd style="margin-top:25px;"><strong><a href="/lotteryInfo/">更多内容</a></strong> &nbsp;| &nbsp; <strong><a href="/groupbuy/">合买中心</a></strong></dd>
						  </div>
						  <S class=r></S><S class=rt></S><S class=lt></S><S class=b style="WIDTH: 169px"></S><S class="b b2" style="WIDTH: 169px"></S><S class=rb></S><S class=lb></S>
					  </div>
				  </div>
			  </LI>
              <li id="ckepop" class="last">
                <a href="#" class="jiathis_txt">收藏到</a>
                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
                 <script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
              </li>
		    </UL>
		</div>
	</div>
</div>
<SCRIPT type="text/javascript">
	TB.Header.init();
</SCRIPT>
<!--top area end-->
<div class="clear"></div>
<!--logo and nav start-->
<div class="menu-nav">
    <div class="web-logo">
        <a href="/" onfocus="this.blur();"><img src="/images/img/hc_logospring.jpg" alt="一彩票彩票网站logo"></a>
    </div>
    <div class="nav-area">
       <ul>
          <li>
            <ul class="shouye">
               <li class="left-bg"></li>
               <li><a href="/">首页</a></li> 
            </ul>
            <div class="right-bg"></div>
          </li>
          <li>
             <ul class="sljziye">
                <li class="left-bg"></li>
                <li class="nav-list"><a href="/lotteryHall/">购彩大厅</a></li>
                <li class="nav-list"><a href="/groupbuy/">合买中心</a></li>
                <li class="nav-list"><a href="/software/">彩票软件</a></li>
                <li style="padding:5px 5px 0px 5px; font-size:13px; "><a href="/lotteryInfo/">彩票资讯</a></li> 
             </ul>
             <div class="righgt-ziji"></div>
          </li>
          <li>
             <ul class="last-nav">
                <li class="left-bg"></li>
                <li><a href="/about-us/">关于我们</a></li>
             </ul>
             <div class="rrrr-bg"></div>
          </li>
          <li style="float:left; margin-left:20px;-margin-left:10px;padding-top:14px;"></li>
          <li style="float:left; margin-left:10px;-margin-left:5px;padding-top:8px;""><img src="/images/navbg_13.gif" alt="4008 188 369 "></li>
       </ul>
    </div>
    <div class="clear"></div>
    <ul class="erjinav">
       <li class="leftbg"></li>
       <li class="listmenu"><a href="/prizedetail/" target="_blank" style="color:#fff;">开奖数据</a></li>
       <li class="listmenu"><a href="/help/index.htm" target="_blank" style="color:#fff;">帮助中心</a></li>
       <li class="listmenu"><a href="/customer/" target="_blank" style="color:#fff;">用户中心</a></li>
       <li class="listmenu"><a href="/direction/fbt.htm?type=ssq" target="_blank" style="color:#fff;">数据图表</a></li>
       <li class="listmenu"><a href="/huodong.html" target="_blank" style="color:#fff;">优惠活动</a></li>
       <li class="listmenu"><a href="/lottery/JCZQIndex.htm" target="_blank" style="color:#fff;">竞彩足球</a></li>
       <li class="listmenu"><a href="/lottery/JCLQSFIndex.htm" target="_blank" style="color:#fff;">竞彩篮球</a></li>
       <li class="listmenu"><a href="/help/help-mobile.htm" style="color:#fff;">手机软件</a></li>
       <li class="listmenu1">
       <a href="javascript:void();" class="hover" style="background-color:#fff;" onmouseover="mopen('m3')" onmouseout="mclosetime()">比分直播</a>
         <div class="zhibobox" id="m3" onmouseover="mcancelclosetime()" onmouseout="mclosetime()" style="visibility:hidden">
          <a href="/help/help-6-6.htm" target="_blank">足球直播</a><br/>
          <a href="/help/helplanqiu.htm" target="_blank">篮球直播</a>
         </div>
       </li>
       <li class="listmenu"><a href="/help/help-home-5-2-1.htm" target="_blank" style="color:#fff;">投注策略</a></li>
       <li class="listmenu"><a href="http://www.369cai.com/download.htm?fileName=zyzq.pdf" target="_blank" style="color:#fff;">智赢足球</a></li>
       <li class="listmenu"><a href="/about-us/" target="_blank" style="color:#fff;">联系我们</a></li>
    </ul>
    <div class="rightbg"></div>
</div>
<!--logo and nav end-->
</body>
</html>
