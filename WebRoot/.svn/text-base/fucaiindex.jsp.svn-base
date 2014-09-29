<%@ page language="java" import="java.util.*,com.xsc.lottery.entity.business.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>369竟彩网_福彩首页</title>
<link href="./css/default.css" type="text/css" rel="stylesheet" />
<link href="./css/css.css" type="text/css" rel="stylesheet" />
<link href="./css/fucaiindex.css" type="text/css" rel="stylesheet" />
<LINK href="chart/favicon.ico" type=image/x-icon rel="shortcut icon"> 
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ZJ-user.js"></script>
<script type="text/javascript" src="js/slides.min.jquery.js"></script>
<script type="text/javascript" src="js/util/tab.js"></script>
<script type="text/javascript" src="js/lottery/common.js"></script>
<script type="text/javascript" src="js/lottery/quickBet.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.8.16.custom.css"/>
<script src="js/jquery.bgiframe-2.1.2.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<script src="js/jquery.ui.mouse.js"></script>
<script src="js/jquery.ui.button.js"></script>
<script src="js/jquery.ui.position.js"></script>
<script src="js/jquery.ui.dialog.js"></script>
<script src="js/FAX-picscroll.js"></script>
<script src="js/jquery.effects.core.js"></script>
<script type="text/javascript" src="js/util/validate.js"></script>
<script type="text/javascript" src="js/FAX-picscroll.js"></script>
<script type="text/javascript" src="js/jquery.ajaxfileupload.js"></script>
<SCRIPT type=text/javascript>
$(document).ready(function(){
	$(".close").click(function(){
		$("#js_ads_banner_top").hide();
	 })
	});

</SCRIPT>
<!--网站公告与购彩帮助tab start-->
<script language="javascript">
	$(function(){
        $('#slides').slides({
            preload: true,
            preloadImage: 'images/loading.gif',
            play: 8000,
            pause: 5000,
            hoverPause: true, 
            animationStart: function(){
                $('.caption').animate({
                    bottom:-35
                },100);
            },
            animationComplete: function(current){
                $('.caption').animate({
                    bottom:0
                },200);
            }
        });
    });
	    
	$(function () {
		var theLast=1;
		$(".GGTitile li").mouseover(function() {			
			var attra = $(this).index() + 1;
			$(this).addClass("GGTCurrent_bg").siblings().removeClass("GGTCurrent_bg");	
			var div_id=$(this).parent().attr("id");
			var div_length=$("#"+div_id+" li").length;
			for(var i=1;i<=div_length;i++){
				$("#"+div_id +i).css("display","none");
				$("#"+div_id + attra).css("display","block");
			}
		});
	});
	
	$(document).ready(function()
		{	
			initQuickBet();
			indexlogin();
			loginw();
			hemailogin();
                        dMarquee("ZJ-user-scroll");
                        dMarquee("HMMR");
		}
	);
	function buy(){
	var tt="${customer.nickName}"
	if(tt==""){
	 $( "#dialog-form" ).dialog( "open" );
		  refreshCaptcha2();}
		  else{
		     onQuickBet();

		  }
	}
	function buy1(){
	var tt1="${customer.nickName}"
	if(tt1==""){
	 $( "#dialog-form1" ).dialog( "open" );
		  refreshCaptcha3();}
		  else{
		   document.frames('zcdz_iframe').onIndexSubmit();
		  }
	}
	function buy2(){
	 $( "#dialog-form2" ).dialog( "open" );
		  refreshCaptcha4();
	}
</script>
<script type="text/javascript">
function tab(a,b,c)
{
for(i=1;i<=b;i++){
if(c==i)
{
// 判断选择模块
document.getElementById(a+"_mo_"+i).style.display = "block";  // 显示模块内容
document.getElementById(a+"_to_"+i).className = "ontab";   // 改变菜单为选中样式
}
else{
// 没有选择的模块
document.getElementById(a+"_mo_"+i).style.display = "none"; // 隐藏没有选择的模块
document.getElementById(a+"_to_"+i).className = "";  // 清空没有选择的菜单样式
}
}
}
</script>
</head>
 
<body>
<div class="bigbox">
    <DIV class="lablebanner-box" id=js_ads_banner_top>
        <a class=close href="#"><img src="images/369caibg/gif-0135.gif"></a>
    </DIV>
    
    <DIV class="mainbanner-box" id=js_ads_banner_top_slide>
    <IMG src="images/img/mainbanner.jpg">
    </DIV>
</div>
<SCRIPT type=text/javascript src="js/lablebanner.js"> </SCRIPT>
<div id="dialog-form1" title="登录" style="display:none">
 
<FIELDSET><LABEL for=nickname3>用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="nickname3" class="text ui-widget-content ui-corner-all" name="nickname3" value=""/> <br/> <LABEL 
for=password3>密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="password3" class="text ui-widget-content ui-corner-all" type=password name="password3"/> <br/>
<LABEL for=mngunm3>验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="mngunm3" class="text ui-widget-content ui-corner-all" type="text" name="mngunm3"/> 
<span id="captchaImg3"></span><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:refreshCaptcha3()" onfocus="this.blur()">换张图片</a><br/><span id="message3">${message}</span>
</FIELDSET> <a href="/customer/register.htm" onfocus="this.blur()">注册</a>
</div>
<div id="dialog-form2" title="登录" style="display:none">
 
<FIELDSET><LABEL for=nickname4>用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="nickname4" class="text ui-widget-content ui-corner-all" name="nickname4" value=""/> <br/> <LABEL 
for=password4>密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="password4" class="text ui-widget-content ui-corner-all" type=password name="password4"/> <br/>
<LABEL for=mngunm4>验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</LABEL> <INPUT id="mngunm4" class="text ui-widget-content ui-corner-all" type="text" name="mngunm4"/> 
<span id="captchaImg4"></span><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:refreshCaptcha4()" onfocus="this.blur()">换张图片</a><br/><span id="message4">${message}</span>
</FIELDSET> <a href="/customer/register.htm" onfocus="this.blur()">注册</a>
</div>
<%@include  file="head.jsp"%>
<!-- top content  start-->
<div class="kaijiang-index-cont">
    <!--left box start-->
    <div class="fucai-left-box">
        <b class="b1"></b><b class="b2"></b><b class="b3"></b>
      <div class="bc">
        <div class="bt1 " onclick="window.open('/prizedetail/', '_blank')">开奖信息</div>
        <div class="bb">
           <ul>
             <li>
               <div>双色球开奖信息
               <%
					Map<String, LotteryTerm> allOpenTermMap = (Map<String, LotteryTerm>)request.getAttribute("allOpenWin");
				%>
				<%
       				String[] openresultArray = allOpenTermMap.get("ssq").getResult().split("\\|");
       				String[] area1=openresultArray[0].split(",");
       				String[] area2=openresultArray[1].split(",");
       			%>
               	<span style="margin-left:30px;">第<s:property value="allOpenWin.get('ssq').termNo"/>期</span>
               </div>
               <div class="NO-list1">
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><%=area1[0] %></td>
                        <td><%=area1[0] %></td>
                        <td><%=area1[0] %></td>
                        <td><%=area1[0] %></td>
                        <td><%=area1[0] %></td>
                        <td><%=area1[0] %></td>
                        <td class="last-lanqiu"><%=area2[0] %></td>
                      </tr>
                   </table>
               </div>
               <div class="touzhu-xiangqing">
                 <a href="lottery/index.htm?lotteryType=ssq" onfocus="this.blur();" style="color:red">我要投注</a> | <a href="/prizedetail/ssq_<s:property value="allOpenWin.get('ssq').termNo"/>.htm" onfocus="this.blur();" style="color:red">详情</a>
               </div>
             </li>
             <li>
               <div>福彩3D开奖信息
               <% openresultArray = allOpenTermMap.get("3d").getResult().split(",");%>
               <span style="margin-left:30px;">第<s:property value="allOpenWin.get('3d').termNo"/>期</span>
               </div>
               <div class="NO-list1">
                   <table width="45%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><%=openresultArray[0] %></td>
                        <td><%=openresultArray[1] %></td>
                        <td><%=openresultArray[2] %></td>
                      </tr>
                   </table>
               </div>
               <div class="touzhu-xiangqing">
                 <a href="lottery/index.htm?lotteryType=3d" onfocus="this.blur();" style="color:red">我要投注</a> | <a href="/prizedetail/3d_<s:property value="allOpenWin.get('3d').termNo"/>.htm" onfocus="this.blur();" style="color:red">详情</a>
               </div>
             </li>
             <li>
               <div>大乐透开奖信息
               <% 	openresultArray = allOpenTermMap.get("dlt").getResult().split("\\|");
                	area1=openresultArray[0].split(",");
                    area2=openresultArray[1].split(",");
                %>
               	<span style="margin-left:30px;">第<s:property value="allOpenWin.get('dlt').termNo"/>期</span>
               </div>
               <div class="NO-list1">
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><%=area1[0] %></td>
                        <td><%=area1[1] %></td>
                        <td><%=area1[2] %></td>
                        <td><%=area1[3] %></td>
                        <td><%=area1[4] %></td>
                        <td><%=area2[0] %></td>
                        <td class="last-lanqiu"><%=area2[1] %></td>
                      </tr>
                   </table>
               </div>
               <div class="touzhu-xiangqing">
                 <a href="lottery/index.htm?lotteryType=dlt" onfocus="this.blur();" style="color:red">我要投注</a> | <a href="/prizedetail/dlt_<s:property value="allOpenWin.get('dlt').termNo"/>.htm" onfocus="this.blur();" style="color:red">详情</a>
               </div> 
             </li> 
           </ul>
        </div>
      </div>
    </div>
    <!--left box end-->
    <!--middle box start-->
    <div class="middle-kaijiang-news">
       <!---->
        <div class="title" style="margin-bottom:10px;"><h1>软件用户中奖实例</h1></div>
       <!--<!-- picrotate_left start  -->
        <DIV class=blk_18>
            <A onmouseup=ISL_StopUp_1() class=LeftBotton onmousedown=ISL_GoUp_1() onmouseout=ISL_StopUp_1() href="javascript:void(0);" target=_self onfocus="this.blur();"></A>
            <DIV class=pcont id=ISL_Cont_1>
                <DIV class=ScrCont>
                    <DIV id=List1_1>
                        <!-- piclist begin -->
                        <A class=pl href="http://localhost:8000/software/index.htm?action=moreWin" target=_blank><IMG src="software/images/ASQXC.jpg" WIDTH="130" height="80"></A>
                        <A class=pl href="http://u563k1.chinaw3.com/htdocs2/bd.jpg" target=_blank>湛江一用户使用陈女士<br />所在投注站湛的乐透玩玩<br />软件仅用12元喜中500万<br />大奖!详情请见当地报道。</A>
                        <A class=pl href="http://localhost:8000/software/index.htm?action=moreWin" target=_blank><IMG src="software/images/JL.jpg" WIDTH="130" height="80"></A>
                        <A class=pl href="#" target=_blank>200323期吉林用户中得该<br />省唯一一等奖及若干二等<br />奖500余万元!</A>
                        <A class=pl href="http://localhost:8000/software/index.htm?action=moreWin" target=_blank><IMG src="software/images/ASLIANGZHONG.jpg" WIDTH="130" height="80"></A>
                        <A class=pl href="#" target=_blank>200401期、200408期鞍<br />山满天星投注站使用足球<br />玩玩系列软件两月两中百<br />万大奖，投注单见上!</A>
                        <A class=pl href="http://localhost:8000/software/index.htm?action=moreWin" target=_blank><IMG src="software/images/fax7.jpg" WIDTH="130" height="80"></A>
                        <A class=pl href="#" target=_blank>200317期焦作代理投注站<br />使用足球玩玩叠加过滤专<br />家软件中得一等奖一注,<br />见上图，每注奖金达46万!</A>
                      <!-- piclist end -->
                    </DIV>
                    <DIV id=List2_1></DIV>
                </DIV>
            </DIV>
            <A onmouseup=ISL_StopDown_1() class=RightBotton onmousedown=ISL_GoDown_1() onmouseout=ISL_StopDown_1() href="javascript:void(0);" target=_self onfocus="this.blur();"></A>
        </DIV>
      <SCRIPT type=text/javascript>
        <!--
        picrun_ini()
        //-->
      </SCRIPT>
        <!-- picrotate_left end -->
      <div class="clear" style="padding-top:10px;"></div>
       <div class="benzhousuoshuimiji">
            <div class="zuojiaobig">
            </div>
            <div class="tiltboxcont">
            本周缩水秘笈
            </div>
            <div class="clear"></div>
            <div class="mijicontent">
            · <a href="#" target="_blank">今年3D第五次开出豹子号 全国返奖达</a><br />
            · <a href="#" target="_blank">今年3D第五次开出豹子号 全国返奖达</a>
            </div>
            <div class="zhongwubaowu">
              <img src="images/369caibg/huodong111_21.png" width="120" height="80">
            </div>
       </div>
       <div class="clear"></div>
       <div class="companyintroduce">
           <p class="jsrtxt" onclick="window.open('/about-us/','_blank')"><img src="images/img/bitouch.jpg" alt="北京比特太奇科技有限公司">
   2001年11月，公司创始人首创彩票缩水概念，在北京正式推出了全国第一套足彩投注缩水软件——"足球玩玩"软件，并在全国范围内建立起软件代理销售网络。随后几年，在全国各地掀...
         </p>
       </div>
    </div>
    <!--middle box end-->
    <!--right box start-->
    <div class="odds-box">
       <div class="oddstitle">
           数据图表
       </div>
       <div class="tubiaobox">
           <ul>
              <li>
                  <a href="/direction/plt.htm?tt=js&type=ssq" onfocus="this.blur();">
                     <img src="images/369caibg/tubiao_06.png" alt="双色球分布图" style="width:80px;height:70px; border:1px solid #960000;"><br />[双色球分布图]
                  </a>
              </li>
              <li style="margin-left:20px">
                  <a href="/direction/fbt.htm?tt=js&type=3d" onfocus="this.blur();">
                     <img src="images/369caibg/tubiao_03.png" alt="双色球分布图" style="width:80px;height:70px;border:1px solid #960000;"><br />[福彩3D分布图]
                  </a>
              </li>
           </ul>
       </div>
       <div class="tubiaofenlei">
           <ul>
             <li>
              <h4>双色球图表</h4>
              &nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/fbt.htm?tt=js&type=ssq" target="_blank">双色球分布图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=js&type=ssq&chartType=zst" target="_blank">奇数走势图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=lh&type=ssq&chartType=zst" target="_blank">连号走势图</a><br/>  
              &nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=zds&type=ssq&chartType=zst" target="_blank">最大数走势图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/plt.htm?tt=haoma&type=ssq" target="_blank">号码频率图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/plt.htm?tt=qj3&type=ssq" target="_blank">三区频率图</a> &nbsp;

             </li>
             <li>
                <h4>福彩3D图表</h4>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/fbt.htm?tt=js&type=3d" target="_blank">3D分布图</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=js&type=3d&chartType=zst" target="_blank">奇数走势图</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=lh&type=3d&chartType=zst" target="_blank">连号走势图</a><br/>  
              &nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/zst.htm?tt=yanxu&type=3d&chartType=zst" target="_blank">延续走势图</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/plt.htm?tt=haoma&type=3d" target="_blank">号码频率图</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/direction/plt.htm?tt=qj3&type=3d" target="_blank">三区频率图</a> &nbsp;

             </li>
           </ul>
       </div>
       <div class="clear"></div>
       <div style="margin-top:15px; text-align:CENTER;"><a href="/download.htm?fileName=ssqsetup.zip" onfocus="this.blur()" style="color:#ff0000">免费下载双色球系列软件</a> | <a href="/download.htm?fileName=3dsetup.zip" onfocus="this.blur()" style="color:#ff0000">免费下载福彩3D系列软件</a></div>
    </div>
    <!--right box end-->
    <div class="clear" style="padding-top:10px;"></div>
    <!-- huodong content  start-->
    <b class="b1"></b><b class="b2"></b><b class="b3"></b>
    <div class="huodongbox">
       <ul>
         <li style="margin-left:0px;"><a href="/huodong.html" target="_blank" onfocus="this.blur();"><img src="images/369caibg/huodong1.png" width="158" height="90"></a></li>
         <li style="text-align:center;padding-top:14px;"><a href="/huodong.html" onfocus="this.blur();" style="color:#fff;">注册用户首次购彩，一彩票送您5元<br /><br />彩金，保您开门红！
</a></li>
         <li><a href="/huodong.html" target="_blank" onfocus="this.blur();"><img src="images/369caibg/huodong2.png" width="158" height="90"></a></li>
         <li style="text-align:center;padding-top:14px;"><a href="/huodong.html" onfocus="this.blur();" style="color:#fff;">活动期间凡单一方案中奖满1000元<br />,即加奖100元，满2000元<br />，即可加奖200元！</a></li>
       </ul>
    </div>
     <b class="b3 bb2"></b><b class="b2 bb2"></b><b class="b1 bb1"></b>
    <!-- huodong content  end-->
    <div class="clear"></div>
    <!-- soft content  start-->
    <div class="soft-introduce">
      <b class="b1"></b><b class="b2"></b><b class="b3"></b>  
      <div class="bc">
        <div class="bt ">软件介绍</div>
      </div>
      <div class="ssqsoft-content">
         <p class="jsrtxt"><img src="images/soft/369software01.jpg"/>
    一彩票系列软件涵盖足彩，双色球，乐透，3D等全系列彩种，集缩水，过滤，统计，分析于一体，秉承科学，客观，服务用户的宗旨，结合一彩票网络购彩平台，力争打造中奖率最高的彩票综合服务平台。

    一彩票推广活动期间多款软件可免费使用，部分软件现在只需注册充值50元即可获得注册码。 <a href="#" target="_blank">[详细]</a>
         </p>
         <ul>
           <li><a href="http://www.369cai.com/download.htm?fileName=jcsetup.zip" style="color:#36F">一彩票 竞彩精算师本地下载</a></li>
           <li><a href="http://www.369cai.com/download.htm?fileName=sfcsetup.zip" style="color:#36F">一彩票 足彩缩水胆拖过滤专家免费下载</a></li>
           <li><a href="http://www.369cai.com/download.htm?fileName=jqcsetup.zip" style="color:#36F">一彩票 任9场投注优化专家本地下载</a></li>
           <li><a href="http://www.369cai.com/download.htm?fileName=ssqsetup.zip" style="color:#36F">一彩票 双色球统计分析软件本地下载</a></li>
           <li><a href="http://www.369cai.com/download.htm?fileName=3dsetup.zip" style="color:#36F">一彩票 3D排列3统计分析专家本地下载</a></li>
           <li><a href="http://www.369cai.com/download.htm?fileName=3dsetup.zip" style="color:#36F">一彩票 3D排列3快选优化专家本地下载</a></li>
           <li style="list-style:none;"><a href="http://www.369cai.com/software/" target="_blank" style="color:#f60">更多系列软件</a></li>
         </ul>
      </div>
    </div>
    <div class="touzhucecelue" style="display:block">
        <ul>
          <li class="ssqtouzhucelue">
            <b style="color:#f30;">双色球投注策略</b>
            <hr/>
            <ul class="shuangseqiujiqiao">
               <s:iterator value="ssqtzcllist" status="st">
            		<li><span>[双色球]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
               </s:iterator>
            </ul>
          </li>
          <li class="ssqtouzhucelue">
            <b style="color:#f30;">福彩3D投注策略</b>
            <hr/>
            <ul class="shuangseqiujiqiao">
               <s:iterator value="fc3dtzcllist" status="st">
            		<li><span>[福彩3D]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
               </s:iterator>
            </ul>
          </li>
        </ul>
    </div>
    <div class="qitasoft-jieshao">
        <b class="b1"></b><b class="b2"></b><b class="b3"></b>  
          <div class="bc">
            <div class="bt ">其他软件</div>
          </div>
        <div class="qitaruanjian-box">
            <ul>
            <li class="toptilte">一彩票&nbsp;&nbsp;双色球缩水过滤软件</li>
            <li style="text-align:center; margin:0 auto;margin-top:15px;width:170px;height:100px;border:1px solid #F93;"><img src="software/image/SOFT-SSQGL.jpg" border="1" width="170" height="100"></li>
            <li style="line-height:25px;padding:3px;">
              <strong>[双色球之缩水过滤专家]有如下功能：</strong><br />
                （1）超大容量，最完备的缩水旋转矩阵数据库。中6保5，中6保4，
                中5保5，中5保4，中4保4等多种标准缩水算法， 全部可实现33个
                号码全包缩水，节省投注资金几百几十倍。<br />
                （2）超快计算引擎，33个号码全包拆分只需数秒即可，速度超快。<br />
                （3）多种投注过滤功能，如连号过滤、区间过滤、自由过滤、AC过
                滤、点数过滤、尾数过滤、界限过滤、红蓝搭配过滤等。<br />
                <a href="#" onfocus="this.blur();" style="color:#636">[详细介绍]</a>
            </li>
            <li style="text-align:right;padding-right:30px;"><a href="/download.htm?fileName=ssqsetup.zip" onfocus="this.blur()" style="color:#ff0000">免费下载</a></li>         
            </ul>
        </div>
    </div>
    <div class="clear"></div>
    <!-- soft content  end-->
    <div class="clear"></div>
    <!-- tonglan content  start-->
    <div class="ql-zhiyin">
		<div class="ql-zhuce">
			<div class="ql-retb">
				<img src="images/ql-retb.gif"/>
			</div>
			<div class="ql-detail"><a href="register.htm">注册登录</a><br />
简单信息填写，30秒<br />
成为本站会员 </div>
			<div class="ql-jiantou">
				<img src="images/ql-jiantou.gif"/>
			</div>
		</div>
		<div class="ql-zhuce">
			<div class="ql-retb">
				<img src="images/ql-retb2.gif"/>
			</div>
			<div class="ql-detail"><a href="/oss/customer/manageHandAdd.htm">充值</a><br />
多种充值渠道：支付<br />
宝，银行卡 </div>
			<div class="ql-jiantou">
				<img src="images/ql-jiantou.gif"/>
			</div>
		</div>
		<div class="ql-zhuce">
			<div class="ql-retb">
				<img src="images/ql-retb3.gif"/>
			</div>
			<div class="ql-detail"><a href="#">购买彩票</a><br />
彩种全面，追号、合买<br />
定制跟单玩儿法更多 </div>
			<div class="ql-jiantou">
				 <img src="images/ql-jiantou.gif" />
			</div>
		</div>
		<div class="ql-zhuce2">
			<div class="ql-retb">
				<img src="images/ql-retb4.gif"/>
			</div>
			<div class="ql-detail"><a href="#">中奖提现</a><br />
提现24小时内到账，绑<br />
定银行卡资金更安全</div>
		</div>
	</div>
    <div class="clear"></div>
    <!-- tonglan content  end--> 
    <!-- goucai content  start-->
    <div class="selcet-caizhong">
         <b class="b1"></b><b class="b2"></b><b class="b3"></b>
          <div class="bc">
            <div class="bt ">选择彩票</div>
          </div>
          <div class="caizhongbox">
              <div class="list1-fucai">
                  <div class="jiajiang"></div>
                  <ul>
                 <li class="fucailist">福彩：</li>
                     <li><a href="lottery/index.htm?lotteryType=ssq" onfocus="this.blur()">双色球</a></li>
                     <li><a href="lottery/index.htm?lotteryType=qlc" onfocus="this.blur()">七乐彩</a></li>
                     <li><a href="lottery/index.htm?lotteryType=3d" onfocus="this.blur()">福彩3D</a></li>
                </ul>
</div> 
              <div class="clear"></div>
              <div class="list1-fucai">
                  <ul>
                     <li class="ticailist">体彩：</li>
                     <li><a href="lottery/index.htm?lotteryType=dlt" onfocus="this.blur()">大乐透</a><br /><a href="lottery/index.htm?lotteryType=plw" onfocus="this.blur()">排列5</a></li>
                     <li><a href="lottery/index.htm?lotteryType=pls" onfocus="this.blur()">排列3</a></li>
                     <li><a href="lottery/index.htm?lotteryType=qxc" onfocus="this.blur()">七星彩</a></li>
                  </ul>
              </div> 
              <div class="clear"></div>
              <div class="list1-fucai">
                  <ul>
                     <li class="zucailist">足彩：</li>
                     <li><a href="lottery/index.htm?lotteryType=14sfc" onfocus="this.blur()">14场胜负彩</a><br /><a href="lottery/index.htm?lotteryType=4cjq" onfocus="this.blur()">4场进球彩</a></li>
                     <li><a href="lottery/index.htm?lotteryType=r9" onfocus="this.blur()">任选9场</a><br /><a href="lottery/index.htm?lotteryType=6cb" onfocus="this.blur()">6场半全场</a></li>
                  </ul>
              </div> 
              <div class="clear"></div>
              <div class="list1-fucai" style="border-bottom:0px;">
                  <div class="newwanfa"></div>
                  <ul>
                    <li class="jingcailist">竟彩：</li>
                    <li><a href="lottery/JCZQIndex.htm" onfocus="this.blur()">让球胜平负</a><br /><a href="lottery/BQCIndex.htm" onfocus="this.blur()">半全场</a></li>
                    <li><a href="lottery/ZJQSIndex.htm" onfocus="this.blur()">总进球</a><br /><a href="lottery/CBFIndex.htm" onfocus="this.blur()">猜比分</a></li>
                  </ul>
              </div> 
          </div>
    </div>
    <!-- goucai content  end-->
    <!-- kuaitou content  start-->
    <div class="kuaitoubox">
       <div class="oddstitle">
           双色球快速投注
       </div>
       <div class="shuangseqiubox">
           <ul>
             <li class="SSQlogo"></li>
             <li style="margin-left:30px;">
             当前奖池奖金：<span style="font-size:14px; color:red;" id="prizePool"><fmt:formatNumber type="number" value="${ssqLastTerm.prizePool}" pattern="###,###,###"/></span>元
             <br />
             <br />
             第<span id="ssqCurTerm"></span>期 投注截止时间：<span id="ssqCurTermStopTime"></span>
             </li>
           </ul>
           <div class="clear"></div>
           <div style="color:#999; margin-top:15px;">[红球]从1-33中选6个不重复的整数 &nbsp; [蓝球]从1-16中选1个整数</div>
           <div class="clear"></div>
            <div style="text-align:center;padding-left:80px;">
              <div class="dBNum">
                <ul>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_1" maxlength="2"/></li>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_2" maxlength="2"/></li>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_3" maxlength="2"/></li>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_4" maxlength="2"/></li>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_5" maxlength="2"/></li>
                    <li title="此处可输入"><input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_redball_6" maxlength="2"/></li>
                    <li title="此处可输入" style="margin-left:20px;background:url(images/369caibg/369cai_54.gif) center no-repeat;">
                        <input class="inputNum" type="text" onkeyup="value=value.replace(/[^\d]/g,'');" onblur="changeValue(this)" id="ssq_blueball_1" maxlength="2"/>
                    </li>
                </ul>
            </div>
            </div>
            <div class="clear"></div>
            <div class="clear"></div>
            <div class="btn-box">
               <ul>
                 <li class="fristbtn"><img src="images/369caibg/369cai_55.gif" style="cursor:pointer" onclick="buy()"/></li>
                 <li><a href="/help/help-home-5-3-1.htm" onfocus="this.blur();"><img src="images/369caibg/index001_14.png" alt="软件投注"></a></li>
                 <li class="secondbtn"><img src="images/369caibg/369cai_61.gif" style="cursor:pointer" onclick="randomSsq()"/></li>
                 <li><img src="images/369caibg/369cai_63.gif" style="cursor:pointer" onclick="clearSsq()"/></li>
               </ul>
            </div>
       </div>
    </div>
    <!-- kuaitou content  end-->
    <!-- zhongjianguser content  start-->
   	<div class="zhongjianguser">
      <b class="b1"></b><b class="b2"></b><b class="b3"></b>
      <div class="bc">
        <div class="bt ">中奖排行</div>
      </div>
      <div class="paihang-box">
          <div class="paihangtabchange">
              <ul>
                <li class="ontab" id="tab_to_1" onmouseover="tab('tab',2,1)">正在中奖</li>
                <li id="tab_to_2" onmouseover="tab('tab',2,2)">中奖用户</li>
              </ul>
          </div>
          <div id="tab_mo_1" style="display:block">
               <div class="titieofusers">
                 <ul>
                   <li>用户名</li>
                   <li>彩种</li>
                   <li>时间</li>
                   <li>奖金</li>
                 </ul>
               </div>
               <ul id="ZJ-user-scroll">
                  <li>
                    <table width="240" border="0" cellspacing="0" cellpadding="0">
                      <s:iterator value="page.result">
                      <tr>
                        <td>${fn:substring(userName,0,2) }**</td>
                        <td>${type}</td>
                        <td><s:date name="winTime" format="yyyy-MM-dd"/></td>
                        <td>${bonus }元</td>
                      </tr>
                      </s:iterator>
                    </table>
                  </li>
                </ul>
                <script type="text/javascript">
                                       function dMarquee(id){
                var scrollnews = document.getElementById(id);
                var lis = scrollnews.getElementsByTagName('li');
                var ml = 0;
                var timer1 = setInterval(function(){
                    var liHeight = lis[0].offsetHeight;
                    var timer2 = setInterval(function(){
                     scrollnews.scrollTop = (++ml);
                     if(ml == liHeight){
                        clearInterval(timer2);
                        scrollnews.scrollTop = 0;
                        ml = 0;
                        lis[0].parentNode.appendChild(lis[0]);
                     }
                  },10);
                },2000);}
                </script>
        </div>
        <div id="tab_mo_2" style="display:none;padding-left:30px;">
                <div class="ZJ_pic">
                    <img src="images/img/200415taiyuan.jpg" width="63" height="63" alt="XXX" />
                </div>
                <div class="ZJ_text">
                    <ul>
                        <li><span>
                          <h3>吉林用户</h3></span></li>
                        <li>胜负彩03023中奖：<span>500万元</span></li>
                        <li></li>
                    </ul>
                </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">北京用户</li>
                        <li style=" width:102px;padding-left:10px;">双色球0428期：</li>
                        <li style="color:red;">430万</li>
                    </ul>
                </div>
                <div class="clear"></div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">湛江用户</li>
                        <li style=" width:102px;padding-left:10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;南粤风采：</li>
                        <li style="color:red;">430万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">武汉用户</li>
                        <li style=" width:110px;padding-left:10px;">胜负彩04036期：</li>
                        <li style="color:red;">23万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">赤峰用户</li>
                        <li style=" width:110px;padding-left:10px;">胜负彩04023期：</li>
                        <li style="color:red;">107万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">焦作用户</li>
                        <li style=" width:105px;padding-left:10px;">进球彩0407期：</li>
                        <li style="color:red;">9万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">太原用户</li>
                        <li style=" width:102px;padding-left:10px;">双色球0426期：</li>
                        <li style="color:red;">28万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">武汉用户</li>
                        <li style=" width:102px;padding-left:10px;">胜负彩0317期：</li>
                        <li style="color:red;">46万</li>
                    </ul>
              </div>
                <div class="ceshi1">
                    <ul>
                        <li style="color:red; font-size:14px; font-weight:bold;">鞍山用户</li>
                        <li style=" width:102px;padding-left:10px;">胜负彩0302期：</li>
                        <li style="color:red;">80万</li>
                    </ul>
              </div>
              <div class="ceshi1">
                    <a href="http://www.369cai.com/software/index.htm?action=moreWin" onfocus="this.blur()">查看更多用户中奖实录</a>
              </div>
        </div>
      </div>
    </div>
    <!-- zhongjianguser content  end-->
    <div class="clear"></div>
    <!-- tonglan content  start-->
    <div class="banner-six">
       <a href="#" target="_blank" onfocus="this.blur();"><img src="images/ad/sixad.jpg" alt="一彩票六大优势"></a>
    </div>
    <div class="clear"></div>
    <!-- tonglan content  end-->
    <!-- zhuanjia content  start-->
    <div class="jingcaihuodong">
      <b class="b1"></b><b class="b2"></b><b class="b3"></b>  
      <div class="bc">
        <div class="bt ">竟彩玩法介绍</div>
      </div>
      <div class="jingcaihuodongbox">
        <div class="wanfabox">
          <p class="jsrtxt"><img src="lotteryHall/images/jc.png"/>竞彩是经财政部批准发行的竞猜型体育彩票。竞彩足球是以竞彩彩票所选择的足球比赛为对象，由购买者选择竞猜场次并预测比赛结果，以实际比赛结果为竞彩彩票兑奖依据的一种体育彩票游戏玩法。<a href="#" target="_blank">[详细]</a>
</p>
        </div>
        <div class="jingcaiguize">
            <ul>
              <li><a href="#" target="_blank">竟彩足球玩法规则说明</a></li>
              <li><a href="#" target="_blank">竟彩足球名词/术语解释</a></li>
              <li><a href="#" target="_blank">竟彩足球投注流程</a></li> 
            </ul>
        </div>
      </div>
    </div>
    <div class="zhuanjiatuijian">
       <ul class="spantop">
         <li style="font-size:14px;font-weight:bold;color:#960000;padding-left:10px;">竞彩专家预测</li>
         <li style="text-align:right; width:184px; padding-right:15px;"><a href="#">全部预测</a></li>
       </ul>
       <div class="clear"></div>
       <ul class="listofyuce"> 
       		<s:iterator value="jczqzjtjlist" status="st">
                    <div class="clear"></div>
					<li ><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">
					<s:property value="title"/></a><span style="float:right;"><s:date name="publishTime" format="yyyy-MM-dd HH:mm:ss"/></span></li>
			</s:iterator>
       </ul> 
    </div>
    <div class="zucaihudong">
       <b class="b1"></b><b class="b2"></b><b class="b3"></b> 
       <div class="bc">
            <div class="bt ">竟彩活动</div>
          </div>
          <div class="zucaihuodongbox">
             <div class="toptubox">
                 <img src="images/img/jingcai_35.png" alt="佬牛解盘：玻利维亚迎10场">
             </div>
             <div style="text-align:center; width:200px; margin:0 auto; margin-top:10px; border-bottom:1px dotted #ccc;"><a href="#" onfocus="this.blur();">【佬牛解盘：玻利维亚迎10场】</a></div>
             <ul class="listof-jingcai">
                <li><a href="#">英锦赛:看低有升可能级队低有升可能级队</a></li>
                <li><a href="#">英锦赛:看低有升可能级队低有升可能级队</a></li>
                <li><a href="#">英锦赛:看低有升可能级队低有升可能级队</a></li>
                <li><a href="#">英锦赛:看低有升可能低有升可能级队级队</a></li>
                <li><a href="#">英锦赛:看低有升可能级队低有升可能级队</a></li>
             </ul>
          </div>
    </div>
    <!-- zhuanjia content  end-->
</div>
    <div class="clear"></div>
    <%@include  file="foot.jsp"%>
</body>
</html>
