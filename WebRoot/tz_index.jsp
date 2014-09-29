<%@ page language="java" import="java.util.*,com.xsc.lottery.entity.business.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投注网_彩票娱乐门户网站_彩票_足彩_竞彩开奖</title>
<meta name="Keywords" content="彩票、足彩、竞彩、彩票开奖、足彩开奖" />
<meta name="Description" content="投注网是一家提供彩票、足彩、竞彩、彩票开奖、足彩开奖的资讯、推荐、工具开发的服务中国彩票用户的互联网平台，是中国彩票行业互联网高端运营商。" />
<link href="css/base.css" type="text/css" rel="stylesheet" >
<script type="text/javascript" src="js/Jquery/jquery-1.5.min.js"></script>
<script> 
 function bookmark(url, title) {
        if (document.all)
            window.external.AddFavorite(url, title);
        else if (window.sidebar)
            window.sidebar.addPanel(title, url, "")
    }  
	
function tabToggle(obj,n,classOn,div){
	if(!$(obj).hasClass(classOn)){
		$(obj).addClass(classOn).siblings().removeClass(classOn);
		$("."+div+":eq("+n+")").show().siblings("."+div).hide();
	}
}  
function tabChange(obj,id ){
		var tabLi = obj.parentNode.getElementsByTagName("li");
		var tabContents = document.getElementById(id).getElementsByTagName("ul");
		for(var i=0; i<tabContents.length; i++){
			if(obj == tabLi[i]){
				tabLi[i].className = "content";
				tabContents[i].className = "";
			}
			else{
				tabLi[i].className="";
				tabContents[i].className = "none";
			}
		}
	} 
$(function () {
            //所有隔行变色
            $("table.tablecp tr:even td").css("backgroundColor", "#f6f6f6");
        })
</script>
</head>
<body>
<div id="tzfloatHeader">
    <div class="tzfloatHeader">
        <div class="tnTab">
            <a href=""  target="_blank" > 购彩大厅</a>|
            <a href=""  target="_blank">购彩记录</a>|
            <a href="" target="_blank">账户充值</a>|
            <a href="" target="_blank" >提款</a>
        </div>        
        <div class="Ptopright">
            <span class="span">|</span><a href="#" onclick="bookmark('http://www.touzhu.cn/','投注,彩票,专家推荐,足球彩票,体育彩票,福利彩票,竞彩,双色球,超级大乐透,福彩3D,排列3,排列5,开奖信息,中奖资讯,即时比分,球队数据')">加入收藏</a><span
                class="span">|</span><a href="" target="_blank">帮助中心</a><span class="span">|</span>
            <li onmouseover="$('#tzShare').show()" onmouseout="$('#tzShare').hide()">分享
                <div id="tzShare">
                    <a title="分享到QQ空间" href="" class="qqzone" target="_blank"></a>
                    <a title="分享到QQ微博" href="" class="qqweibo" target="_blank"></a>
                    <a title="分享到开心网" href="" class="kaixin" target="_blank"></a>
                    <a title="分享到新浪微博" href="" class="sinaweibo" target="_blank"></a>
                    <a title="分享到人人网" href="" class="renren" target="_blank"></a>
                </div>
            </li>
        </div>        
        <div class="PtopMid">
            <a href="#"  class="loginbottons">登录</a><span class="span">|</span><a href="#"  class="red">注册</a>         
        </div>        
    </div>
</div>
<!--漂浮条end-->
<div id="tznTop">
	<div class="nHeader">
    	<div class="nHeaderTop">
            <div id="nLogo">
                <a href="#"></a>
            </div>
            <div class="TZ_nbanner"><img src="images/TZ_banner.jpg" width="210" height="93" /></div>
            <div class="nService">
                <dl>
                    <dd>7x24小时服务客服</dd>
                    <dd class="pdl20">400-0088-310</dd>
                    <dd class="pdl20"><a href="#" target="_blank" class="linka">在线客服</a></dd> 
              </dl>                                  	
             </div> 
        </div>
        <div class="tzmainNav"> 
        	<ul>
            	<li><a href="/" class="Selected">首页</a></li>
                <li><a href="/lotteryHall/">购彩中心</a></li>
                <li><a href="/groupbuy/">合买跟单</a></li>
                <li><a href="/prizedetail/">开奖公告</a></li>
            </ul>
        </div>
	</div>
</div>
<!--头部end-->
<div id="tzcontent">
	<div class="tzcontentleft">
    	<div class="ConLdiv01">
        	<h3 class="numtitle">购买彩票</h3>
            <ul>
            	<li class="zyGame"><a href="lottery/index.htm?lotteryType=ssq"><em class="logo35_ssq"></em><strong>双色球</strong><span class="grayWords">奖池3.1亿元</span><span class="redWords">热卖</span></a></li>
            	<li class="zyGame"><a href="lottery/index.htm?lotteryType=qlc"><em class="logo35_qlc"></em><strong>七乐彩</strong></a></li>
                <li class="zyGame"><a href="lottery/index.htm?lotteryType=3d"><em class="logo35_fc3d"></em><strong>福彩3D</strong></a></li>
                <li class="zyGame"><a href="lottery/index.htm?lotteryType=dlt"><em class="logo35_dlt"></em><strong>大乐透</strong><span class="grayWords">奖池5.2亿元</span><span class="redWords">中2亿</span></a></li>
            	<li class="zyGame"><a href="lottery/index.htm?lotteryType=pls"><em class="logo35_p3"></em><strong>排列三</strong></a></li>
                <li class="zyGame"><a href="lottery/index.htm?lotteryType=plw"><em class="logo35_p5"></em><strong>排列五</strong></a></li>
                <li class="zyGame"><a href="lottery/index.htm?lotteryType=qxc"><em class="logo35_qxc"></em><strong>七星彩</strong></a></li>
                 <li class="otherGames clearfix">
                	<h3 class="title02">足彩</h3>
                    <div class="nabcon">
                        <em class="left"><a href="lottery/index.htm?lotteryType=14sfc" title="" >14场胜负彩</a></em>
                        <em><a href="lottery/index.htm?lotteryType=r9" >任选9场</a></em>
                        <em class="left"><a href="lottery/index.htm?lotteryType=4cjq" >四场进球</a></em>
                        <em><a href="lottery/index.htm?lotteryType=6cb">六场半全场</a></em>
                        <div class="clear"></div> 
                    </div>
                    <div class="clear"></div>  
                </li>
                <li class="otherGames clearfix">
                	<h3 class="title02">竞彩足球</h3>
                    <div class="nabcon">
                        <em class="left"><a href="lottery/JCZQIndex.htm" title="" >让球胜平负</a></em>
                        <em><a href="lottery/ZJQSIndex.htm" >总进球</a></em>
                        <em class="left"><a href="lottery/BQCIndex.htm" >半全场</a></em>
                        <em><a href="lottery/CBFIndex.htm">猜比分</a></em>
                        <div class="clear"></div> 
                    </div>
                    <div class="clear"></div>  
                </li>
                <li class="otherGames clearfix">
                	<h3 class="title02">竞彩篮球</h3>
                    <div class="nabcon">
                        <em class="left"><a href="" title="lottery/JCLQSFIndex.htm" >胜负</a></em>
                        <em><a href="lottery/JCLQRFSFIndex.htm" >让分胜负</a></em>
                        <em class="left"><a href="lottery/JCLQSFCIndex.htm" >胜分差</a></em>
                        <em><a href="lottery/JCLQDXFIndex.htm">大小分</a></em>
                        <div class="clear"></div> 
                    </div>
                    <div class="clear"></div>  
                </li>
                <div class="clear"></div> 
            </ul>
        </div>
    	<div class="ConLdiv02" >
        	<div class="divtop">
            	<h3 class="h3title">彩票开奖</h3>
            	<ul class="subnav"><li class="content"  onmouseover="tabChange(this,'kjcont')">福彩</li><li onmouseover="tabChange(this,'kjcont')">体彩</li></ul>
            </div>
        	<div class="divlist" id="kjcont" >
            	<ul>
                    <li>
                    <%
						Map<String, LotteryTerm> allOpenTermMap = (Map<String, LotteryTerm>)request.getAttribute("allOpenWin");
					%>
					<%
       					String[] openresultArray = allOpenTermMap.get("ssq").getResult().split("\\|");
       					String[] area1=openresultArray[0].split(",");
       					String[] area2=openresultArray[1].split(",");
       				%>
                        <a target="_blank"  href=""><strong>双色球</strong></a>
                        <a target="_blank"  href="">第<span class="red"><s:property value="allOpenWin.get('ssq').termNo.substring(2)"/></span>期</a><a target="_blank"  href="lottery/index.htm?lotteryType=ssq" class="pl20 huicolor">投注</a><a target="_blank"  href="/prizedetail/ssq_<s:property value="allOpenWin.get('ssq').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=area1[0] %></span> 
                            <span class="rball"><%=area1[1] %></span> 
                            <span class="rball"><%=area1[2] %></span> 
                            <span class="rball"><%=area1[3] %></span> 
                            <span class="rball"><%=area1[4] %></span> 
                            <span class="rball"><%=area1[5] %></span> 
                            <span class="bball"><%=area2[0] %></span>
                        </div>
                    </li>
                    <li>
                    	<% openresultArray = allOpenTermMap.get("3d").getResult().split(",");%>
                        <a target="_blank" href=""><strong>福彩3D</strong></a>
                        <a target="_blank" href="">第<span class="red"><s:property value="allOpenWin.get('3d').termNo.substring(2)"/></span>期</a><a target="_blank" href="lottery/index.htm?lotteryType=3d" class="pl20 huicolor">投注</a><a target="_blank" href="/prizedetail/3d_<s:property value="allOpenWin.get('3d').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=openresultArray[0] %></span> 
                            <span class="rball"><%=openresultArray[1] %></span> 
                            <span class="rball"><%=openresultArray[2] %></span>
                        </div>
                    </li>
                    <li>
						<% openresultArray = allOpenTermMap.get("qlc").getResult().split(",");%>
                        <a target="_blank" href=""><strong>七乐彩</strong></a>
                        <a target="_blank" href="">第<span class="red"><s:property value="allOpenWin.get('qlc').termNo.substring(2)"/></span>期</a><a target="_blank" href="lottery/index.htm?lotteryType=qlc" class="pl20 huicolor">投注</a><a target="_blank" href="/prizedetail/3d_<s:property value="allOpenWin.get('qlc').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=openresultArray[0] %></span> 
                            <span class="rball"><%=openresultArray[1] %></span> 
                            <span class="rball"><%=openresultArray[2] %></span> 
                            <span class="rball"><%=openresultArray[3] %></span> 
                            <span class="rball"><%=openresultArray[4] %></span> 
                            <span class="rball"><%=openresultArray[5] %></span>
                            <span class="rball"><%=openresultArray[6] %></span>
                        </div>
                    </li>                
                    <div class="morebar"><a href="/prizedetail/" target="_blank" class="huicolor">查看更多彩种</a></div>
                </ul>                   
        	<!--福彩结束-->
            	<ul  class="none" >
                    <li>
						<% 	openresultArray = allOpenTermMap.get("dlt").getResult().split("\\|");
                			area1=openresultArray[0].split(",");
							area2=openresultArray[1].split(",");
						 %>
                        <a target="_blank"  href=""><strong>大乐透</strong></a>
                        <a target="_blank"  href="">第<span class="red"><s:property value="allOpenWin.get('dlt').termNo.substring(2)"/></span>期</a><a target="_blank"  href="lottery/index.htm?lotteryType=dlt" class="pl20 huicolor">投注</a><a target="_blank"  href="/prizedetail/dlt_<s:property value="allOpenWin.get('dlt').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=area1[0] %></span> 
                            <span class="rball"><%=area1[1] %></span> 
                            <span class="rball"><%=area1[2] %></span> 
                            <span class="rball"><%=area1[3] %></span> 
                            <span class="rball"><%=area1[4] %></span>                        
                            <span class="bball"><%=area2[0] %></span>
                            <span class="bball"><%=area2[1] %></span>
                        </div>
                    </li>
                   
                    <li>
					<% openresultArray = allOpenTermMap.get("pls").getResult().split(",");%>
                        <a target="_blank" href=""><strong>排列3</strong></a>
                        <a target="_blank" href="">第<span class="red"><s:property value="allOpenWin.get('pl3').termNo.substring(2)"/></span>期</a><a target="_blank" href="lottery/index.htm?lotteryType=pls" class="pl20 huicolor">投注</a><a target="_blank" href="/prizedetail/pls_<s:property value="allOpenWin.get('pls').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=openresultArray[0] %></span> 
                            <span class="rball"><%=openresultArray[1] %></span> 
                            <span class="rball"><%=openresultArray[2] %></span>
                        </div>
                    </li> 
                    <li>
					<% openresultArray = allOpenTermMap.get("plw").getResult().split(",");%>
                        <a target="_blank" href=""><strong>排列5</strong></a>
                        <a target="_blank" href="">第<span class="red"><s:property value="allOpenWin.get('plw').termNo.substring(2)"/></span>期</a><a target="_blank" href="lottery/index.htm?lotteryType=plw" class="pl20 huicolor">投注</a><a target="_blank" href="/prizedetail/pls_<s:property value="allOpenWin.get('plw').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=openresultArray[0] %></span> 
                            <span class="rball"><%=openresultArray[1] %></span> 
                            <span class="rball"><%=openresultArray[2] %></span>
                            <span class="rball"><%=openresultArray[3] %></span> 
                            <span class="rball"><%=openresultArray[4] %></span>
                        </div>
                    </li>  
					 <li>
						<% openresultArray = allOpenTermMap.get("qxc").getResult().split(",");%>
                        <a target="_blank" href="lottery/index.htm?lotteryType=qxc"><strong>七星彩</strong></a>
                        <a target="_blank" href="">第<span class="red"><s:property value="allOpenWin.get('qxc').termNo.substring(2)"/></span>期</a><a target="_blank" href="" class="pl20 huicolor">投注</a><a target="_blank" href="/prizedetail/dlt_<s:property value="allOpenWin.get('qxc').termNo"/>.htm" class="huicolor">查看详细</a>
                        <div class="kj_num">
                            <span class="rball"><%=openresultArray[0] %></span> 
                            <span class="rball"><%=openresultArray[1] %></span> 
                            <span class="rball"><%=openresultArray[2] %></span> 
                            <span class="rball"><%=openresultArray[3] %></span> 
                            <span class="rball"><%=openresultArray[4] %></span> 
                            <span class="rball"><%=openresultArray[5] %></span>
                            <span class="rball"><%=openresultArray[6] %></span>
                        </div>
                    </li>
                    <div class="morebar"><a href="/prizedetail/" target="_blank" class="huicolor">查看更多彩种</a></div>
            	</ul>
            </div>        
        	<!--体福彩结束-->
        </div>
    	<div class="ConLdiv03">
        	<div class="divtop">
            	<h3 class="h3title">分析工具</h3>
                <ul class="subnav">
                	<li class="content"  onmouseover="tabChange(this,'gjcont')">走势图</li>
                    <li onmouseover="tabChange(this,'gjcont')">双色球</li>
                    <li onmouseover="tabChange(this,'gjcont')">大乐透</li>
                    <li onmouseover="tabChange(this,'gjcont')">福彩3D</li>
                </ul>
            </div>
            <div class="divlist03" id="gjcont">
            	<ul>
                	<li><a href="" target="_blank">总体走势</a>|<a href="" target="_blank">红球走势</a>|<a href="" target="_blank">红球和值</a></li>
                    <li><a href="" target="_blank">蓝球走势</a>|<a href="" target="_blank">红球位数</a>|<a href="" target="_blank">红球奇偶</a></li>
                    <li><a href="" target="_blank">蓝球奇偶</a>|<a href="" target="_blank">红球012路</a>|<a href="" target="_blank">蓝球012路</a></li>
                </ul>
                <ul class="none">
                	<li><a href="" target="_blank">双色球走势</a>|<a href="" target="_blank">红球走势</a>|<a href="" target="_blank">红球和值</a></li>
                    <li><a href="" target="_blank">蓝球走势</a>|<a href="" target="_blank">红球位数</a>|<a href="" target="_blank">红球奇偶</a></li>
                    <li><a href="" target="_blank">蓝球奇偶</a>|<a href="" target="_blank">红球012路</a>|<a href="" target="_blank">蓝球012路</a></li>
                </ul>
                <ul class="none">
                	<li><a href="" target="_blank">大乐透走势</a>|<a href="" target="_blank">红球走势</a>|<a href="" target="_blank">红球和值</a></li>
                    <li><a href="" target="_blank">蓝球走势</a>|<a href="" target="_blank">红球位数</a>|<a href="" target="_blank">红球奇偶</a></li>
                    <li><a href="" target="_blank">蓝球奇偶</a>|<a href="" target="_blank">红球012路</a>|<a href="" target="_blank">蓝球012路</a></li>
                </ul>
                <ul class="none">
                	<li><a href="" target="_blank">福彩3D走势</a>|<a href="" target="_blank">红球走势</a>|<a href="" target="_blank">红球和值</a></li>
                    <li><a href="" target="_blank">蓝球走势</a>|<a href="" target="_blank">红球位数</a>|<a href="" target="_blank">红球奇偶</a></li>
                    <li><a href="" target="_blank">蓝球奇偶</a>|<a href="" target="_blank">红球012路</a>|<a href="" target="_blank">蓝球012路</a></li>
                </ul> 
            </div>
        	<div class="divtjtitle"><a href="/lotteryInfo/" class="mores">更多>></a>专家推荐</div>
            <div class="divtjlist">
            	<ul>
				<!--5条-->
				<s:iterator value="expertList" status="st">
				<li><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>"><s:property value="title"/></a></li>
				</s:iterator>

                </ul>
            </div>
        </div>
    </div> 
    <!--tzcontentleft end-->
    <div class="tzcontentright">
    	<div class="ConRdivleft" >
        	<div class="tzbanner"><img src="images/img/banner.jpg" width="480" height="185" /></div>
            <div class="tzjujiao ">
            	<div class="tzjjtop">
                	<span class="tzjjnav"><a href="">开奖</a> | <a href="">概率分析</a></span>
                    竞彩聚焦
                </div>
              <div class="tzjjlist">
                	<ul>
                    	<li>
                        	<span class="namelink"><a href="" target="_blank">分析</a><a href="" target="_blank">亚赔</a><a href="" target="_blank">欧赔</a><a href="" target="_blank">推荐</a></span>
                            <span class="h3title">柏林联合   02:30   凯泽斯劳滕</span> 
                            <a href="" class="right clear buybottom">购买</a>
                            <span class="qdname">德乙：动力不足 柏林联合无心恋战</span>
                        </li>
                        <li>
                        	<span class="namelink"><a href="" target="_blank">分析</a><a href="" target="_blank">亚赔</a><a href="" target="_blank">欧赔</a><a href="" target="_blank">推荐</a></span>
                            <span class="h3title">柏林联合   02:30   凯泽斯劳滕</span> 
                            <a href="" class="right clear buybottom">购买</a>
                            <span class="qdname">德乙：动力不足 柏林联合无心恋战</span>
                        </li>
                        <li class="last">
                        	<span class="namelink"><a href="" target="_blank">分析</a><a href="" target="_blank">亚赔</a><a href="" target="_blank">欧赔</a><a href="" target="_blank">推荐</a></span>
                            <span class="h3title">柏林联合   02:30   凯泽斯劳滕</span> 
                            <a href="" class="right clear  buybottom">购买</a>
                            <span class="qdname">德乙：动力不足 柏林联合无心恋战</span>
                        </li>
                        
                    </ul>                
                </div>
                
          </div>
        	<div class="tzkjdiv">
            	<li>
                	<a href="" target="_blank" class="right tzbottom">立即投注</a>
                    <span class="left"><img src="images/img/tz_img_ss.jpg" width="60" height="60" alt="" /></span>
                    <span class="contitle left">
                    	<strong>双色球</strong> 第<strong class="red">2014045</strong>期<br/>
                        当前奖池：<strong><span class="red">2</span>亿<span class="red">34560</span>万元</strong>                 	
                    </span>
                    <strong class="contentdiv left">
                    	截止还剩<br/>
                        0天05：48:22
                    </strong>
                	<div class="clear"></div>
                </li>
                <li>
                	<a href="" target="_blank" class="right tzbottom">立即投注</a>
                    <span class="left"><img src="images/img/tz_img_115.jpg" width="60" height="60" alt="" /></span>
                    <span class="contentdiv left">
                    	<strong>11选5--彩民最爱的快频彩种</strong><br/>
                        猜对1个号就能中奖,简单易中,<span class="red">返奖率59%</span>               	
                    </span>                   
                	<div class="clear"></div>
                </li>
                <li class="last">
                	<a href="" target="_blank" class="right tzbottom">立即投注</a>
                    <span class="left"><img src="images/img/tz_img_zq.jpg" width="60" height="60" alt="" /></span>
                    <span class="contentdiv left">
                    	<strong>竞彩足球</strong> <span class="font12">当前有<strong class="red">34</strong>场比赛/<span class="colorhui">已结束17场</span></span><br/>
                        <span class="font12 line20">欧联杯 4-24 塞维利亚 VS 巴伦西亚<br/>
						欧联杯 4-24 本菲卡 VS 尤文图斯</span>              	
                    </span>                   
                	<div class="clear"></div>
                </li>                
            </div>
        </div>
        <!--ConRdivleft end-->
        <div class="ConRdivright" >
        	<div class="logindiv">
            	<a href="/customer/" target="_blank" class="tzredbottom">用户登录</a>
                <a href="/register.htm" target="_blank" class="tzredbottom mleft15">免费注册</a>
            </div>
        	<div class="tznotice">
            	<div class="tznoticetop"><a href="/help/index.htm" target="_blank" class="right">更多>></a><h3 class="h3title">网站公告</h3></div>
            	<ul class="tznoticelist">
            	<!-- 6条 -->
            	<s:iterator value="publicList" status="st">
            		<li><span class="times"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="">[公告]<s:property value="title"/></a></li>
            	</s:iterator>
                </ul>
            </div>
            <div class="tzcondiv">
            	<div class="tzcondivtop">                	
                    <li class="content" onclick="tabToggle(this,0,'content','gmcontet')">购买流程</li>
                    <li onclick="tabToggle(this,1,'content','gmcontet')">新手帮助</li>
                    <li onclick="tabToggle(this,2,'content','gmcontet')">安全承诺</li>                    
                </div>
                <div class="tzcondivlist" id="gmcontet">
                	<div class="subnav">
                        <ul >
                            <li class="content" onmouseover="tabChange(this,'subcont')">1免费注册</li>
                            <li onmouseover="tabChange(this,'subcont')">2充值购彩</li>
                            <li onmouseover="tabChange(this,'subcont')">3中奖提款</li>                    
                        </ul>
                    </div>
                    <div id="subcont" class="subcontent">
                    	<ul>
                        	<a href="">中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                            <a href="">中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                        </ul>
                        <ul class="none">
                        	<a href="">2中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                            <a href="">2中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                        </ul>
                        <ul class="none">
                        	<a href="">3中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                            <a href="">3中奖后如何领取奖金？</a><a href="">使用手机购买彩票？</a>
                        </ul>                    
                    </div>
                </div>
            	<!--tzcondivlist end-->
            </div>
            <!--tzcondiv end-->
            <div class="tzLive" >
            	<h3 class="h3title">大奖直播室</h3>
                <ul class="ulcontent">
				    <!--6条-->
					<s:iterator value="page.result">
					<li><span class="right red">${bonus}元</span>[${type}]  ${fn:substring(userName,0,2) }**</li>
					 </s:iterator>
                	
                </ul>
            </div>
          <div class="tzLive" >
           	<h3 class="h3title">手机客户端</h3>
              <ul class="sj_download">
               	<span class="imgspan">
                 	投注彩票
                 </span>
                 <span class="titlespan">
                 	<span class="titlename">下载到电脑安装</span><br/>
                    <a href="http://www.anzhi.com/dl_app.php?s=1493019" class="Phone01">Android</a>
                    <a href="https://itunes.apple.com/us/app/cai-sou-cai-piao/id779279710?ls=1&mt=8" class="Phone02">iPhone</a>
                 </span>
              </ul>
          </div>
          <!--tzLive end--> 
        </div>
    	<!--ConRdivright end-->
        <div class="ConRconter01">
        	<div class="ConRconter01top">
            	<a href="/groupbuy/" class="right">进入彩票合买大厅 >></a>
            	<ul>
                	<li class="content" onclick="tabToggle(this,0,'content','hotbuydiv')" >热门彩票方案</li>
					<!--
                    <li onclick="tabToggle(this,1,'content','hotbuydiv')">双色球</li>
                    <li onclick="tabToggle(this,2,'content','hotbuydiv')">大乐透</li>
                    <li onclick="tabToggle(this,3,'content','hotbuydiv')">胜负彩</li>
                    <li onclick="tabToggle(this,4,'content','hotbuydiv')">任选九</li>
					-->
                </ul>
            </div>
			<iframe id="groupbuy_iframe" src="/lottery/joinGroupBuy.htm?action=listAllGroupBuy" scrolling="no" frameborder="0" style="width:730px"></iframe>
            <div class="ConRconter01list" id="hotbuydiv" style="display:none;">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablecp">
                  <tr>
                    <td>彩种</td>
                    <td>奖期</td>
                    <td>发起人</td>
                    <td>方案进度</td>
                    <td>方案金额</td>
                    <td>每份</td>
                    <td>剩余分数</td>
                    <td>参与</td>
                  </tr>
                  <tr>
                    <td>双色球</td>
                    <td>2014045</td>
                    <td>qq141055112051</td>
                    <td>
                    	<div class="loadingdiv">25%+0%<span class="fontzi">保</span>
                        	<div class="loadingbar"><span style="width:25px;"></span></div>
                        </div>
                    </td>
                    <td><span class="red">8</span></td>
                    <td>1.00</td>
                    <td>6</td>
                    <td><a href="" class="tzgmbottom">快去购买</a></td>
                   </tr>
                   <tr>
                    <td>双色球</td>
                    <td>2014045</td>
                    <td>qq141055112051</td>
                    <td>
                    	<div class="loadingdiv">25%+0%<span class="fontzi">保</span>
                        	<div class="loadingbar"><span style="width:25px;"></span></div>
                        </div>
                    </td>
                    <td><span class="red">8</span></td>
                    <td>1.00</td>
                    <td>6</td>
                    <td><a href="" class="tzgmbottom">快去购买</a></td>
                   </tr>
                   <tr>
                    <td>双色球</td>
                    <td>2014045</td>
                    <td>qq141055112051</td>
                    <td>
                    	<div class="loadingdiv">25%+0%<span class="fontzi">保</span>
                        	<div class="loadingbar"><span style="width:25px;"></span></div>
                        </div>
                    </td>
                    <td><span class="red">8</span></td>
                    <td>1.00</td>
                    <td>6</td>
                    <td><a href="" class="tzgmbottom">快去购买</a></td>
                   </tr>
                   <tr>
                    <td>双色球</td>
                    <td>2014045</td>
                    <td>qq141055112051</td>
                    <td>
                    	<div class="loadingdiv">25%+0%<span class="fontzi">保</span>
                        	<div class="loadingbar"><span style="width:25px;"></span></div>
                        </div>
                    </td>
                    <td><span class="red">8</span></td>
                    <td>1.00</td>
                    <td>6</td>
                    <td><a href="" class="tzgmbottom">快去购买</a></td>
                   </tr>
                   <tr>
                    <td>双色球</td>
                    <td>2014045</td>
                    <td>qq141055112051</td>
                    <td>
                    	<div class="loadingdiv">25%+0%<span class="fontzi">保</span>
                        	<div class="loadingbar"><span style="width:25px;"></span></div>
                        </div>
                    </td>
                    <td><span class="red">8</span></td>
                    <td>1.00</td>
                    <td>6</td>
                    <td><a href="" class="tzgmbottom">快去购买</a></td>
                   </tr>
                </table>
            </div>  
        	<!--ConRconter01list end-->
        </div>        
        <!--ConRconter01 end-->
        <div class="ConRconter02">
        	<div class="ConRnew left">
            	<div class="ConRnewtop">
                	<ul>
                    	<li class="content" onclick="tabToggle(this,0,'content','ConRnewlist')" >双色球资讯</li>
                        <li onclick="tabToggle(this,1,'content','ConRnewlist')">彩票新闻</li>
                        <li onclick="tabToggle(this,2,'content','ConRnewlist')">福彩3D资讯</li>
                    </ul>
                </div>
                <div class="ConRnewlist" id="ConRnewlist01_ssq">
                	<ul>
					<s:iterator value="ssqList" status="st">
					<li><span class="right"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>">[<s:property value="type"/>]<s:property value="title"/></a></li>
					</s:iterator>
                    	
                    </ul>                	
                </div>    
				<div class="ConRnewlist" id="ConRnewlist01_cp" style="display:none;">
                	<ul>
                    	<s:iterator value="szcList" status="st">
					<li><span class="right"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>">[<s:property value="type"/>]<s:property value="title"/></a></li>
					</s:iterator>
                    </ul>                	
                </div>    
				<div class="ConRnewlist" id="ConRnewlist01_3d" style="display:none;">
                	<ul>
					<s:iterator value="fc3dList" status="st">
					<li><span class="right"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>">[<s:property value="type"/>]<s:property value="title"/></a></li>
					</s:iterator>
						<!--
                    	<li><span class="right">5/13</span><a href="">[胜负彩]清远惊现足彩"犀利哥" 4个月内三获435万</a></li>
                        <li><span class="right">5/13</span><a href="">[德国杯]拜仁6-1再进德国杯决赛 梦之队推83.33%</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]阿森纳主场0比0战平埃弗顿 仍领先切2分</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]特里2球托雷斯助攻 切尔西客场以3-0领阿1分</a></li>
                        <li><span class="right">5/13</span><a href="">[竞彩]吾生何球：奥斯达哥德堡两队战意不缺</a></li>
						-->
                    </ul>                	
                </div>    
            </div>	
        	<!--ConRnew end-->
            <div class="ConRnew right">
            	<div class="ConRnewtop">
                	<ul>
                    	<li class="content" onclick="tabToggle(this,0,'content','ConRnewlist2')" >竞彩资讯</li>
                        <li onclick="tabToggle(this,1,'content','ConRnewlist2')">足球资讯</li>                        
                    </ul>
                </div>
                <div class="ConRnewlist2" id="ConRnewlist02">
                	<ul>
					<s:iterator value="jcList" status="st">
					<li><span class="right"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>">[<s:property value="type"/>]<s:property value="title"/></a></li>
					</s:iterator>
						<!--
                    	<li><span class="right">5/13</span><a href="">[胜负彩]清远惊现足彩"犀利哥" 4个月内三获435万</a></li>
                        <li><span class="right">5/13</span><a href="">[德国杯]拜仁6-1再进德国杯决赛 梦之队推83.33%</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]阿森纳主场0比0战平埃弗顿 仍领先切2分</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]特里2球托雷斯助攻 切尔西客场以3-0领阿1分</a></li>
                        <li><span class="right">5/13</span><a href="">[竞彩]吾生何球：奥斯达哥德堡两队战意不缺</a></li>
						-->
                    </ul>                	
                </div> 
				<div class="ConRnewlist2 none" id="ConRnewlist02" >
                	<ul>
					<s:iterator value="zcList" status="st">
					<li><span class="right"><s:date name="publishTime" format="M/d"/></span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>">[<s:property value="type"/>]<s:property value="title"/></a></li>
					</s:iterator>
						<!--
                    	<li><span class="right">5/13</span><a href="">[胜负彩]清远惊现足彩"犀利哥" 4个月内三获435万</a></li>
                        <li><span class="right">5/13</span><a href="">[德国杯]拜仁6-1再进德国杯决赛 梦之队推83.33%</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]阿森纳主场0比0战平埃弗顿 仍领先切2分</a></li>
                        <li><span class="right">5/13</span><a href="">[英超]特里2球托雷斯助攻 切尔西客场以3-0领阿1分</a></li>
                        <li><span class="right">5/13</span><a href="">[竞彩]吾生何球：奥斯达哥德堡两队战意不缺</a></li>
						-->
                    </ul>                	
                </div>    
            </div>	
        </div>
        <!--ConRconter02 end-->
        
    </div>
    <!--tzcontentright end-->   
    <div class="clear"></div>
</div>

<div id="tzfooter">	
    <div class="tz_footerdiv01">
        <ul>
            <li class="ico01"><b>客服电话</b>400-0088-310</li>
            <li class="ico02"><b>在线资讯</b>工作日   9:30-18:00</li>
            <li class="ico03"><b>专家在线</b>为您提供一对一解决方案</li>
            <li class="ico04"><b>投注网官方微博</b>您的专业彩票顾问</li>
            <div class="clear"></div>
        </ul>        
    </div>
    <div class="tz_footerdiv02">
    	<ul>
        	<li>
            	<h3>注册登录</h3>
                <a href="#">快速注册</a><br/>
                <a href="#">免费注册登录</a><br/>
                <a href="#">身份验证</a><br/>
                <a href="#">账户安全</a>
            </li>
            <li>
            	<h3>账户充值</h3>
                <a href="#">在线充值</a><br/>
                <a href="#">用户资金查询</a><br/>
                <a href="#">中奖提现说明</a><br/>
                <a href="#">银行转账</a>
            </li>
            <li>
            	<h3>选号投注</h3>
                <a href="#">发起方案</a><br/>
                <a href="#">参与合买</a><br/>
                <a href="#">方案流产</a><br/>
                <a href="#">方案保密</a>
            </li>
            <li>
            	<h3>快速提款</h3>
                <a href="#">免费提款</a><br/>
                <a href="#">提款密码</a><br/>
                <a href="#">开户行名称填写</a><br/>
                <a href="#">提款遇到了问题</a>
            </li>
            <li class="lastimg">
            	<img src="images/TZ_ftimg01.jpg" width="204" height="124" alt="" border="0" />
            </li>
            <div class="clear"></div>
        </ul>
    </div>
	<div class="tz_footerdiv03">
    	<strong>合作伙伴:</strong>
        <a href="" target="_blank"><img src="images/img/img_01.jpg" width="73" height="30" border="0" alt="" /></a>
        <a href="" target="_blank"><img src="images/img/img_02.jpg" width="88" height="30" border="0" alt=""/></a>
        <a href="" target="_blank"><img src="images/img/img_03.jpg" width="90" height="30" border="0" alt=""/></a>
        <a href="" target="_blank"><img src="images/img/img_04.jpg" width="44" height="30" border="0" alt=""/></a>
        <a href="" target="_blank"><img src="images/img/img_05.jpg" width="86" height="30" border="0" alt=""/></a>
        <a href="" target="_blank"><img src="images/img/img_06.jpg" width="86" height="30" border="0" alt=""/></a>
        <a href="" target="_blank">更多>></a>
    </div>
    <div class="tz_footerdiv04">
    	<strong>友情链接：</strong>
        <a href="#" target="_blank">爱波网论坛</a><a href="#" target="_blank">爆棚网</a><a href="#" target="_blank">捷报比分</a><a href="#" target="_blank">吻球网</a><a href="#" target="_blank">八一体育网</a><a href="#" target="_blank">80体育</a><a href="#" target="_blank">五星体育直播</a><a href="#" target="_blank">cctv5在线直播</a><a href="#" target="_blank">足球直播</a><a href="#" target="_blank">飞翔体育</a><a href="#" target="_blank">90体育网</a>    
        <a href="" target="_blank">更多>></a>
    </div>
    <div class="tz_footerdiv05">
   		 <h5>
            <a href="" target="_blank">广告服务</a> | 
            <a href="" target="_blank">关于投注网</a> | 
            <a href="" target="_blank">免责声明</a> | 
            <a href="" target="_blank">投资与合作</a> | 
            <a href="" target="_blank">联系我们</a> | 
            <a href="" target="_blank">招贤纳士</a>
        </h5>
        <span>Copyright 2004--2013 Touzhu.cn All rights reserved</span><br />
        <span>京ICP备12023316号 京ICP证120466号 版权所有 投注网</span><br />        
        <span>投注网郑重提示：本站内容多涉及国内体、福彩相关信息，谢绝18岁以下用户访问<br />
            彩票有风险，投注需谨慎。 不向未满18周岁的青少年出售彩票。</span><br />
        <span style="color:#FF0000">建议使用1024*768以上分辨率，IE6.0以上版本浏览</span>

    </div>
</div>



</body>
</html>
