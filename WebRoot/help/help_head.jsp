<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_帮助中心</title>
<link rel="stylesheet" href="css/help-index.css"/>
<link rel="shortcut icon" href="../chart/favicon.ico" type="image/x-icon"  />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script type="text/javascript" src="js/search.js"></script>
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
var timeout         = 500;
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
document.onclick = mclose; 
// -->
</script>
<!--网站导航下拉菜单 end-->
<style type="text/css">
#cse-search-form{width:180px;height:22px;margin-top:5px;margin-left:4px;} 
#cse-search-form form input#g_input{width:120px;height:20px;border-top:solid 1px #FFCC99;border-left:solid 1px #FFcc99;border-bottom:solid 1px #FFcc99;border-right:solid 1px #FFcc99} 
#cse-search-form form input#g_submit{width:40px;height:18px;background:#FFcccc;border-top:solid 1px #FFcc99;border-left:solid 1px #FFcc99;border-bottom:solid 1px #FFcc99;border-right:solid 1px #FFcc99;font-size:12px;margin-bottom:2px;}
</style>
</head>
<body>

    <!--top area start-->
    <div class="title">
     <div class="title-box">
          <div class="positin-center">
         <ul>
            <li>您好，欢迎来到一彩票票网！</li>
            <li><a href="/customer/" target="_blank">请登录</a></li>
            <li><a href="/register.htm" target="_blank">免费注册</a></li>
         </ul>
       </div>
       <div class="position-right">
          <ul>
            <li><a href="javascript:window.external.AddFavorite('http://www.369cai.com/','一彩票彩票网-网络购彩就上一彩票')" onclick="window.external.AddFavorite('http://www.369cai.com/','一彩票彩票网-网络购彩就上一彩票')">加入收藏夹</a></li>
            <li id="sddm" style="margin-left:5px"> |
                <a href="#" onmouseover="mopen('m1')" onmouseout="mclosetime()">网站导航</a>
                <div id="m1" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
                    <strong>购彩</strong><br />
                     <a href="lottery/JCZQIndex.htm" target="_blank">胜平负</a> &nbsp;|&nbsp; <a href="lottery/ZJQSIndex.htm" target="_blank">总进球</a> &nbsp;|&nbsp; <a href="lottery/BQCIndex.htm" target="_blank">半全场</a> &nbsp;|&nbsp; <a href="lottery/CBFIndex.htm" target="_blank">猜比分</a>
                 	&nbsp;|&nbsp; <a href="lottery/JCLQSFIndex.htm" target="_blank">篮球胜负</a> &nbsp;|&nbsp; <a href="lottery/JCLQRFSFIndex.htm" target="_blank">篮球让分胜负</a> &nbsp;|&nbsp; <a href="lottery/JCLQSFCIndex.htm" target="_blank">篮球胜分差</a> &nbsp;|&nbsp; <a href="lottery/JCLQDXFIndex.htm" target="_blank">篮球大小分</a>
                   	&nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=ssq" target="_blank">双色球</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=dlt" target="_blank">大乐透&nbsp; |</a><a href="lottery/index.htm?lotteryType=14sfc" target="_blank">14场</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=r9" target="_blank">任9场</a> &nbsp;|&nbsp; <a href="lottery/index.htm?lotteryType=4cjq" target="_blank">进球彩</a> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<a href="/lotteryHall/" target="_blank" style="margin-left:110px;">更多&gt;&gt;</a><br />
                     <hr>
                    <strong>彩票资讯</strong><br />
                    
                    <a href="#" target="_blank">开奖数据</a>  &nbsp;|&nbsp; <a href="#" target="_blank">走势图表</a>  &nbsp;|&nbsp; <a href="#" target="_blank">优惠活动</a>  &nbsp;|&nbsp; 
                    <a href="#" target="_blank">帮助中心</a>  &nbsp;|&nbsp; <a href="#" target="_blank">彩票软件</a><br />
                      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<a href="/lotteryInfo/" target="_blank" style="float:right;">更多&gt;&gt;</a><br />
                     
                    <hr>
                    <strong><a href="/lotteryInfo/">更多内容</a></strong> &nbsp;| &nbsp; <strong><a href="/groupbuy/">合买中心</a></strong>
    
                </div>
            </li>
            <li style="margin-left:5px">|<a href="#" target="_blank"> 帮助中心</a> |</li>
            <li id="ckepop" style="margin-left:5px">
                <a href="#" class="jiathis_txt">收藏到</a>
                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
                 <script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
            </li>
          </ul>
       </div>
     </div>
    </div>
    <!--top area end-->
	<div class="ql-outer">
		<div class="ql-inside">
			<div class="ql-box">
				<div class="ql-box2">
	
					<div class="ql-help2" style="width:110px; height:22px;float:left;font-size:25px;font-weight:bold;color:#efaf5f;">
					帮助中心
					</div>
					<div class="ql-tiaozhuan2" style="width:280px;height:24px;font-size:15px;color:#ffffff;float:right;">
						<a href="/" style="color:#FFFFFF">《返回网站首页</a> &nbsp;&nbsp;&nbsp; <a href="/help/index.htm" style="color:#fff">——返回帮助首页 </a> 
						
					</div>
			</div>
		</div>
			
		</div>
		 
	</div>
</body>
</html>
