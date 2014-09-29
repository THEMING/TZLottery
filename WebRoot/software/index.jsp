<%@ page language="java" import="java.util.*,com.xsc.lottery.entity.business.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>369竟彩网_彩票软件</title>
<link rel="stylesheet" href="../css/css.css">
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/softchange.css">
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script type="text/javascript" src="../js/softpageslides.js"></script>
</head>

<body>
<%@include  file="../head.jsp"%>
<!--main cont start-->
<div class="topwholebox">
    <div class="slidesbox">
        <div id="index-splash-block" class="index-splash-block">
            <div id="feature-slide-block" class="feature-slide-block">
                <div class="feature-slide-preview" style="display: none; "> 
                  <img src="../images/soft/jingsuanshi.jpg" alt="竞彩精算师">
                </div>
                <div class="feature-slide-preview" style="display: none; ">
                  <img src="../images/soft/android-banner.jpg" alt="手机客户端">
                </div>
                <div class="feature-slide-preview" style="display: none; ">
                  <img src="../images/soft/prizeuser.jpg" alt="软件用户中奖实例">
                </div>
                <div class="feature-slide-preview" style="display: none; ">
                   <img src="../images/soft/songruanjian.jpg" alt="注册就送软件">
                </div>
                <div class="feature-slide-preview" style="display: none; ">
                    <img src="../images/soft/zhong100.jpg" alt="中1000送100">
                </div>
                <div id="feature-slide-list" class="feature-slide-list">
                    <a href="#" id="feature-slide-list-previous" class="feature-slide-list-previous"></a>
                    <div id="feature-slide-list-items" class="feature-slide-list-items">
                    </div>
                    <a href="#" id="feature-slide-list-next" class="feature-slide-list-next"></a>
                </div>
            </div>
            <script type="text/javascript">
                initFeatureSlide();
            </script>
        </div>
        <div class="softupdatenews">
            <b class="b1"></b><b class="b2"></b><b class="b3"></b>
          <div class="bc">
            <div class="bt "><font style="color:#000;">软件更新提示</font></div>
            <div class="bf">
               <ul>
                   <li class="shortenTitle">一彩票竞彩精算师软件隆重推出，功能强大,抢先试用</li>
                   <li class="shortenTitle">双色球缩水过滤专家 软件已经升级，缩水过滤后可直接投注</li>
                   <li class="shortenTitle">3D排列3快选优化 软件已经升级，缩水过滤后可直接投注</li>
                   <li class="shortenTitle">进球彩投注优化专家 软件已经升级，缩水过滤后可直接投注</li>
                   <li class="shortenTitle">任九场投注优化专家 软件已经升级，缩水过滤后可直接投注</li>
                   <li class="shortenTitle">任九场投注优化专家 软件已经升级，缩水过滤后可直接投注</li>
               </ul>
            </div>
          </div>
          <b class="b3"></b><b class="b2"></b><b class="b1"></b>
        </div>
    </div>
    <!--slides end ====== soft introduce start-->
    <div class="softintroduce">
         <div class="title">
             <h1>软件介绍</h1>
         </div>
         <div class="contentintrobox">
           <p>一彩票系列彩票软件的前身是玩玩系列软件。足球玩玩是全国第一个足彩缩水软件，诞生于2001年11月，并在实战中取得辉煌战绩，多位用户中得百万大奖。(<a href="http://localhost:8000/software/index.htm?action=moreWin" target="_blank">查看部分中奖彩票传真</a>)</p>
           <p>    通过长期的足彩操盘实践，北京比特太奇科技有限公司进一步研究总结出一套系统科学的足彩投注优化理论，并与科学普及出版社携手推出了《智赢足彩--宏观足彩投注优化组合》一书。</p>
           <p>    一彩票系列软件涵盖足彩，双色球，乐透，3D等全系列彩种，集缩水，过滤，统计，分析于一体，秉承科学，客观，服务用户的宗旨，结合一彩票网络购彩平台，力争打造中奖率最高的彩票综合服务平台。</p>
           <p>    一彩票推广活动期间多款软件可免费使用，部分软件现在只需注册充值50元即可获得注册码。</p>
             <p><font style="color:#f60;">【智赢足彩－－宏观足彩投注优化组合】</font>
             足球玩玩软件发明人苏东峰与科学普及出版社携手推出《智赢足彩——宏观足彩投注优化组合》一书，详细阐述宏观足彩投注优化组合理念，具体讲解各种足彩投注优化技术，并结合技术讲解，对五大联赛的数据以及足彩数据进行了多方面详细统计分析，深入揭示大奖背后的奥秘！！</p>
         </div>
    </div>
    <!--soft introduce end ====== ZHONGJIANG USER start-->
    <div class="rightactive">
         <div class="spantitle">
             中奖用户
         </div>
         <div class="zhongjiangbox">
               <div class="titieofusers">
                 <ul>
                   <li>用户名</li>
                   <li>彩种</li>
                   <li>时间</li>
                   <li>奖金</li>
                 </ul>
               </div>
               <ul id="tablescrool">
                  <li>
                     <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				window.onload = function() {
				 dMarquee('tablescrool');
				}
				
				function dMarquee(id){
				 var speed=20; //速度
				 var stop=1500; //停止时间 
				
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
               <div class="clear"></div>
               <!--JS图片过渡效果 start-->
               <div style="width:300px; margin:0 auto; margin-top:10px; text-align:center;"> 
				<a href="javascript:gotoshow()"><img src="images/fax1.jpg" name="slide" border=0 style="filter:progid:DXImageTransform.Microsoft.Pixelate(MaxSquare=15,Duration=1);width:300px;height:280px;border:1px solid #ccc;"></a>
				<script language="JavaScript1.1">
				<!--
				var whichlink=0
				var whichimage=0
				var pixeldelay=(ie55)? document.images.slide.filters[0].duration*1000 : 0
				function slideit(){
				if (!document.images) return
				if (ie55) document.images.slide.filters[0].apply()
				document.images.slide.src=imageholder[whichimage].src
				if (ie55) document.images.slide.filters[0].play()
				whichlink=whichimage
				whichimage=(whichimage<slideimages.length-1)? whichimage+1 : 0
				setTimeout("slideit()",slidespeed+pixeldelay)
				}
				slideit()
				//-->
				</script>
               </div>
            <p style="margin:10px 0px 0px 10px;"><a href="#" target="_blank">查看更多中奖实例</a></p>
         </div>
    </div>
    <div class="clear"></div>
    <div class="softstallstep">
       <img src="../images/soft/softstepbanner.jpg" alt="软件安装使用、三步轻松搞定">
    </div>
    <div class="clear"></div>
    <!--ZHONGJIANG USER end ====== 福彩软件 start-->
    <div class="fucaisoftware">
        <div class="spantitle">
           福彩软件
        </div>
        <!--<div class="softseparate">
             <ul>
                <li class="tab" id="soft_to_1" onclick="soft('soft',4,1)">双色球缩水过滤软件</li>
                <li id="soft_to_2" onclick="soft('soft',4,2)">双色球统计分析软件</li>
                <li id="soft_to_3" onclick="soft('soft',4,3)">3D排列3快选优化专家</li>
                <li id="soft_to_4" onclick="soft('soft',4,4)">3D排列3统计分析软件</li>
             </ul>
        </div>-->
        <div class="soft-contbox" id="soft_mo_1">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/ssqssgl.jpg" alt="双色球缩水过滤软件" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>双色球之缩水过滤专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=ssqsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118821.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                        <s:iterator value="ssqList">
                           <li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                        </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [双色球之缩水过滤专家]主要有如下功能：<br/>
			
			（1）超大容量，最完备的缩水旋转矩阵数据库。中6保5，中6保4，中5保5，中5保4，中4保4等多种标准缩水算法， 全部可实现33个号码全包缩水，节省投注资金几百几十倍。<br/>
			
			（2）超快计算引擎，33个号码全包拆分只需数秒即可，速度超快。<br/> 
			
			（3）多种投注过滤功能，如连号过滤、区间过滤、自由过滤、AC过滤、点数过...<br/>
                 <a href="/help/help-home-5-3-1.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft_mo_2">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/ssqtjfx.jpg" alt="双色球统计分析专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>双色球统计分析专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=ssqsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118821.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="ssqList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [双色球统计分析专家]具备以下功能：<br/>
			
			（1）数字统计，包括号码列表，号码分布图，号码频率统计，连号频率 统计，区间频率统计，尾数频率统计，最大间隔统计，延续频率统计。 <br/>
			
			（2）特征走势，包括连号走势，区间走势，奇数个数走势，点数走势 AC走势，同尾走势，最大间隔走势，延续走势，最大数走势，最小数走势。 <br/> 
			
			（3）自动数据下载，每期数据自动更新...<br />
            <a href="/help/help-home-5-3-2.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft_mo_3">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/sdpstjfx.jpg" alt="3D排列3统计分析专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>3D排列3统计分析专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=3dsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118825.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="sanDList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [3D统计分析专家]主要有如下功能：<br/>
			
			（1）数据网上自动更新功能，用户只需点击数据下载按钮。<br/>
			
			（2）直观的号码分布图功能，不同位数的号码分别采用不同的颜色显示，号码的走向趋势一目了然，并可单独显示特定位的号码 。<br/> 
			
			（3）多样灵活的频率统计功能，可从号码频率，和数频率，间隔频率，奇 偶频率，大小频率...<br />
                <a href="/help/help-home-5-3-4.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft_mo_4">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/3DP3.jpg" alt="3D排列3快选优化专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1> 3D排列3快选优化专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=3dsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118825.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="sanDList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [3D快选优化专家]主要有如下功能：<br />
                        （1）强大的太奇快选功能，使您轻松实现个人判断<br />
                        （2）和数快选、奇偶快选、大小快选、比较快选、除3快选等多种快选方式<br />
                        （3）强大的过滤功能，如点数过滤、奇偶总体过滤、大小总体过滤、奇偶定位过滤 奇偶定位过滤 、大小定位过滤、重号过滤、连号过滤、断点过滤、 搭配过滤、自由匹配过滤、自由组合过滤、自由定...<br />
                        <a href="/help/help-home-5-3-3.htm" target="_blank" style="color:#f60">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>   
    </div>
    <!--福彩软件 end ====== 网站活动1 start-->
    
    <!--选择彩票 start-->
    <div class="selectLOTTERY">
      <b class="hotwanfa">
      
      </b>
      <b class="border1"></b><b class="border2"></b><b class="border3"></b>
      <div class="borderc">
         <div class="bordert "><font style="color:#000;">选择彩票</font></div>
         <div class="borderTT">
            <ul>
              <li class="fucai">
                <ul>
                    <li>福彩：</li>
                    <li><a href="/lottery/index.htm?lotteryType=ssq" onfocus="this.blur()">双色球</a></li>
                    <li><a href="/lottery/index.htm?lotteryType=qlc" onfocus="this.blur()">七乐彩</a></li>
                    <li><a href="/lottery/index.htm?lotteryType=3d" onfocus="this.blur()">福彩3D</a></li>
                </ul>
              </li>
              <li class="ticai">
                 <ul>
                    <li>体彩：</li>
                    <li><a href="/lottery/index.htm?lotteryType=dlt" onfocus="this.blur()">大乐透</a></li>
                    <li><a href="/lottery/index.htm?lotteryType=pls" onfocus="this.blur()">排列3</a></li>
                    <li><a href="/lottery/index.htm?lotteryType=qxc" onfocus="this.blur()">七星彩</a></li>
                    <li style="padding-left:62px;"><a href="/lottery/index.htm?lotteryType=plw" onfocus="this.blur()">排列5</a></li>
                </ul>
              </li>
              <li class="zucai">
                <ul>
                    <li style="width:60px;">足彩：</li>
                    <li><a href="/lottery/index.htm?lotteryType=14sfc" onfocus="this.blur()">14场胜负彩</a></li>
                    <li style="width:60px;"><a href="/lottery/index.htm?lotteryType=r9" onfocus="this.blur()">任选9场</a></li>
                    <li><a href="/lottery/index.htm?lotteryType=4cjq" onfocus="this.blur()">4场进球彩</a></li>
                    <li style="padding-left:62px;"><a href="/lottery/index.htm?lotteryType=6cb" onfocus="this.blur()">6场半全场</a></li>
                </ul>
              </li>
              <li class="jingcaizuqiu">
                  <ul>
                    <li>竞彩<br />足球:</li>
                    <li style="width:80px;"><a href="/lottery/JCZQIndex.htm" onfocus="this.blur()">让球胜平负</a><a href="#"></a></li>
                    <li><a href="/lottery/ZJQSIndex.htm" onfocus="this.blur()">总进球</a> <a href="#"></a></li>
                    <li><a href="/lottery/BQCIndex.htm" onfocus="this.blur()">半全场</a> <a href="#"></a></li>
                    <li><a href="/lottery/CBFIndex.htm" onfocus="this.blur()">猜比分</a> <a href="#"></a></li>
                </ul>
              </li>
              <li class="jingcailanqiu">
                 <ul>
                    <li>竞彩<br />篮球:</li>
                    <li><a href="/lottery/JCLQSFIndex.htm" onfocus="this.blur()">胜负</a><a href="#"></a></li>
                    <li><a href="/lottery/JCLQRFSFIndex.htm" onfocus="this.blur()">让分胜负</a> <a href="#"></a></li>
                    <li><a href="/lottery/JCLQSFCIndex.htm" onfocus="this.blur()">胜分差</a> <a href="#"></a></li>
                    <li><a href="/lottery/JCLQDXFIndex.htm" onfocus="this.blur()">大小分</a> <a href="#"></a></li>
                </ul>
              </li>
            </ul>
         </div>  
      </div>
    </div>
	<!--选择彩票 end-->
    <div class="webactiveone">
          <b class="border1"></b><b class="border2"></b><b class="border3"></b>
          <div class="borderc">
            <div class="bordert "><font style="color:#000;">优惠活动</font></div>
            <div class="borderf">
                <div class="activefrist">
                      <ul>
                         <li><a href="#" target="_blank"><img src="../images/soft/songruanjian.jpg" alt="注册会员送软件" width="90" height="70"></a></li>
                         <li style="width:170px;">
                            <a href="#" target="_blank"><h2>注册充值 软件免费送</h2></a>
                            会员展精湛过滤技术，猎杀双色球头奖1注，吸金844万<a href="#" target="_blank" style="color:#36648B;margin-left:10px;">[详细]</a>
                         </li>
                      </ul>
                </div>
                <div class="activefrist">
                      <ul>
                         <li><a href="#" target="_blank"><img src="../images/soft/shouci.jpg" alt="注册会员送软件" width="90" height="70"></a></li>
                         <li style="width:170px;">
                            <a href="#" target="_blank"><h2>首次购彩保你中</h2></a>
                            会员展精湛过滤技术，猎杀双色球头奖1注，吸金844万<a href="#" target="_blank" style="color:#36648B;margin-left:10px;">[详细]</a>
                         </li>
                      </ul>
                </div>
                <div class="activefrist">
                      <ul>
                         <li><a href="#" target="_blank"><img src="../images/soft/zhong100.jpg" alt="注册会员送软件" width="90" height="70"></a></li>
                         <li style="width:170px;">
                            <a href="#" target="_blank"><h2>369竞彩 中1000送100</h2></a>
                            会员展精湛过滤技术，猎杀双色球头奖1注，吸金844万<a href="#" target="_blank" style="color:#36648B;margin-left:10px;">[详细]</a>
                         </li>
                      </ul>
                </div>
                <div class="huoongintro">
                    <ul>
                     <s:iterator value="ssqZJTJList">
                     	<li>[双色球]<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                     </s:iterator>
                  </ul>
                </div>
            </div>
          </div>
    </div>
    <div class="professor-forecast">
         <b class="border1"></b><b class="border2"></b><b class="border3"></b>
          <div class="borderc">
            <div class="bordert" style="color:#000">竞彩帮助</div>
            <div class="borderRR">
               <ul class="helplist">
                  <li class="li1">
                     <h2>代购</h2>
                     <a target="_blank" href="/help/help-home-4-1-1.htm">单式</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-2.htm">复式</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-1-3-2.htm">机选</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-3.htm">追号</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-5.htm">和值</a> &nbsp;|&nbsp; 
                     <a target="_blank" href="/help/help-home-4-1-6.htm">直选</a> <a target="_blank" href="/help/help-home-4-1-7.htm">组三</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-1-8.htm">组六 </a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-11.htm">单式上传</a> &nbsp;|&nbsp;  
                    <a target="_blank" href="/help/help-home-4-1-10.htm">胆拖</a>
                  </li>
                  <li class="li2">
                      <h2>合买</h2>
                     <a target="_blank" href="/help/help-3-2.htm">发起合买</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-2.htm"> 参与合买</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-2-3.htm">资金冻结</a> &nbsp;|&nbsp; 
                     <a target="_blank" href="/help/help-home-4-2-4.htm">发起人提成</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-3-2.htm">奖金分配</a> &nbsp;|&nbsp; 
                     <a target="_blank" href="/help/help-3-2.htm">尽快满员</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-2-7.htm">方案保密</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-2-8.htm">方案流产</a>
                  </li>
                  <li class="li3">
                       <h2>账户</h2>
                     <a target="_blank" href="/help/help-1-1.htm">注册</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-1.htm">忘记密码</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-1.htm">修改资料</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-2-3.htm">身份验证</a> &nbsp;&nbsp;  <a target="_blank" href="/help/help-home-3-3.htm">支付宝登录</a> &nbsp;|&nbsp;  
                     <a target="_blank" href="/help/help-home-3-3.htm">QQ登录</a>
                  </li>
                  <li class="li4">
                      <h2>兑奖</h2>
                     <a target="_blank" href="/help/help-1-5.htm">查看中奖</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-1-6.htm">如何兑奖</a> &nbsp;|&nbsp; <a target="_blank" href="/help/help-home-4-5-3.htm">派奖时间</a> &nbsp;|&nbsp; 
                  <a target="_blank" href="/help/help-home-1-4-4.htm">奖金扣税</a>
                  </li>
               </ul>
            </div>
          </div>
    </div>
    <div class="clear"></div>
    <!--网站活动1  end ====== 足彩软件 start-->
    <div class="fucaisoftware">
        <div class="spantitle">
           足彩软件
        </div>
        <!--<div class="softseparate">
             <ul>
                <li class="tab" id="soft2_to_1" onclick="soft('soft2',4,1)">双色球缩水过滤软件</li>
                <li id="soft2_to_2" onclick="soft('soft2',4,2)">双色球统计分析软件</li>
                <li id="soft2_to_3" onclick="soft('soft2',4,3)">3D排列3快选优化专家</li>
                <li id="soft2_to_4" onclick="soft('soft2',4,4)">3D排列3统计分析软件</li>
             </ul>
        </div>-->
        <div class="soft-contbox" id="soft2_mo_1">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/zqsstd.jpg" alt="足彩缩水胆拖过滤专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>足彩缩水胆拖过滤专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=sfcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118829.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="zucai14List">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; width:320px; padding-left:20px;">
                  <strong>[功能介绍]</strong><br/>
                    <b style="font-weight:normal; line-height:26px;">
                        （1） 标准胆拖技术，设定胆选正确数目进行优化，中14保14，节省资金2-20 倍甚至更多。<br/>
                        （2） 智能胆拖技术，胆选对8场以上即可中14保14，即使中8场以下也可中14 保13！节省资金最高可达14倍<br />
                        （3） 复式保留和智能优化可以将复式作为胆选，而不仅仅是首选...
                        <a href="/help/help-home-5-3-8.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br /> 
                        无须注册即可使用基本功能，如需更多更功能只需注册369账号
            并充值50元即可免费获得注册码。</span> <a href="http://www.369cai.com/help/help-home-5-1-3.htm">点击查看具体办法</a>
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft2_mo_2">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/zqdjgl.jpg" alt="足彩叠加过滤专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>足彩叠加过滤专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=sfcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118829.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="zucai14List">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px;  padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">[足彩叠加过滤专家]主要有如下功能：<br />
                    （1）投注叠加系列功能，包括标准塔式叠加，自由塔式叠加，注水
                    叠加，幸运叠加   。<br />
                    （2）投注过滤系列功能，包括场次过滤，连续过滤，专家过滤，赔
                    率过滤，自由过滤，断点过滤，积分过滤，太奇过滤，场形过滤，连
                    形过滤，匹配过滤，集合过滤，搭配过滤，延续过滤。<br />
                    （3）多层分组容错功能（太奇容错），容错管理，条件分组，检验
                    条件， 条件保存，条件装入， 一次性过滤...<br />
                    <a href="/help/help-home-5-3-7.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
      </div>
        <div class="soft-contbox" id="soft2_mo_3">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/tzyh.jpg" alt="进球彩投注优化专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>进球彩投注优化专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=jqcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118823.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="jczqList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [进球彩投注优化专家]主要有如下功能：<br/>
			
			（1）胜平负叠加，比分叠加，进球叠加多种叠加方式。<br/>
			
			（2）多种过滤功能，包括胜平负过滤，进球过滤，净胜球过滤，场次过滤，连续过滤，自由过滤，断点过滤， 积分过滤，太奇过滤，比分过滤，总体比分过滤等。<br/> 
			
			（3）投注组合系列功能，包括直接组合（方案相加、相减、取公共部分）等...<br/> 
                 <a href="/help/help-home-5-3-9.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft2_mo_4">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/r9yh.jpg" alt="任9场投注优化专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>任9场投注优化专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=jqcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118828.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="zucair9List">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [任9场投注优化专家]主要有如下功能：<br/>
			
			（1）复式拆分，塔式叠加功能。<br/>
			
			（2）多种过滤功能包括场次过滤，连续过滤，专家过滤，赔率过滤，自由过滤，断点过滤， 积分过滤，太奇过滤，场形过滤，连形过滤， 匹配过滤，集合过滤，搭配过滤，延续过滤。<br/> 
			
			（3）投注组合系列功能，包括直接组合（方案相加、相减、取公共部分）等...<br/>
                 <a href="/help/help-home-5-3-10.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>   
    </div>
    <!--专家预测-->
    <div class="professor-forecast" style="margin:0px;">
          <b class="border1"></b><b class="border2"></b><b class="border3"></b>
          <div class="borderc">
            <div class="bordert "><font style="color:#000;">专家预测</font></div>
            <div class="borderRR">
               <div class="benzhanzhuanjia">本站专家</div>
               <div class="professorSSQ">
                   <ul>
                      <li><img src="../images/img/right_26.jpg" width="59" height="61" alt="双色球专家"><br />豆丁</li>
                      <li><img src="../images/img/zucaiduizhen02.jpg" width="59" height="61" alt="双色球专家"><br />张璐</li>
                      <li><img src="../images/img/right_26.jpg" width="59" height="61" alt="双色球专家"><br />豆丁</li>
                      <li><img src="../images/img/zucaiduizhen02.jpg" width="59" height="61" alt="双色球专家"><br />张璐</li>
                   </ul>
               </div>
               <!--<div class="professorSSQ" style="margin-top:10px;">
                   <ul>
                      <li style="background:#eee;text-align:center;color:#000;"><img src="../images/img/zucaiduizhen02.jpg" width="59" height="61" alt="双色球专家"></li>
                      <li style="width:190px;">
                         <h1>利物浦有望大胜 </h1>
                         <p>理论上应该初盘低水一球，利物浦最近萎靡，升水对上盘有利，可以回避追反弹的大热<a href="javascript:void();" target="_blank" style="color:#36648B;margin-left:20px;">[详细]</a></p>
                      </li>
                   </ul>
               </div>-->
               <div class="playSkill">
                  <ul>
                  		<s:iterator value="jczqZJTJList">
                           		<li>[竟彩推荐]<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                  		</s:iterator>
                  </ul>
               </div>
            </div>
          </div>
    </div>
    <!---->
    <div class="clear"></div>
    <!-- 足彩软件  end ====== 体彩软件 start-->
    <div class="fucaisoftware">
        <div class="spantitle">
           体彩软件
        </div>
        <!--<div class="softseparate">
             <ul>
                <li class="tab" id="soft3_to_1" onclick="soft('soft3',4,1)">双色球缩水过滤软件</li>
                <li id="soft3_to_2" onclick="soft('soft3',4,2)">双色球统计分析软件</li>
                <li id="soft3_to_3" onclick="soft('soft3',4,3)">3D排列3快选优化专家</li>
                <li id="soft3_to_4" onclick="soft('soft3',4,4)">3D排列3统计分析软件</li>
             </ul>
        </div>-->
        <div class="soft-contbox" id="soft3_mo_1">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/qxcyhzj.jpg" alt="七星彩投注优化专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>七星彩投注优化专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=qxcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118827.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="qxcList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [七星彩投注优化专家]主要有如下功能：<br/>
			
			（1）独创保值缩水算法，完备的缩水旋转矩阵数据库。中7保6，中7保5，中7保4，并提供胆拖投注方式，实现灵活胆量优化。<br/>
			
			（2）超大容量，超快计算引擎，全包拆分1000万注，只需数十秒，速度超快，无需等待。<br/> 
			
			（3）多种投注过滤功能，如点数过滤、奇偶总体过滤、大小总体过滤、重号过滤、断点过滤、连号过滤、搭配过滤、自由匹配过...<br/> 
                 <a href="/help/help-home-5-3-6.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
        <div class="soft-contbox" id="soft3_mo_2">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/dltruji.jpg" alt="大乐透投注优化专家" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>大乐透投注优化专家</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=lottosetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <a href="http://www.onlinedown.net/soft/118824.htm" style="color:#060">华军下载</a>
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="dltList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [大乐透投注优化专家]主要有如下功能：<br/>
                        （1）适应所有选7选6选5型彩票，免费下载使用。<br />
                        （2）复式拆分及多种复式缩水功能，几十种缩水模板。大大减少复式
                        投入，而保持覆盖面积基本一样。<br />
                        （3）设胆功能：包括有外胆功能和内胆功能，应用与复式拆分和复式
                        缩水中，有内胆拆分，外胆拆分，内胆缩水和外胆缩水功能。<br />
                        （4）投注过滤。包括区间过滤、连续过滤、奇偶过滤、点数过滤...<br />
                 <a href="/help/help-home-5-3-5.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
    </div>
     <!---->
        <!---->
        	<!--缺少右部内容-->
        <!---->
    <!---->
    <div class="clear"></div>
    <!-- 体彩软件  end ====== 竞彩软件 start-->
    <div class="fucaisoftware">
        <div class="spantitle">
           竞彩软件
        </div>
        <!--<div class="softseparate">
             <ul>
                <li class="tab" id="soft4_to_1" onclick="soft('soft4',4,1)">双色球缩水过滤软件</li>
                <li id="soft4_to_2" onclick="soft('soft4',4,2)">双色球统计分析软件</li>
                <li id="soft4_to_3" onclick="soft('soft4',4,3)">3D排列3快选优化专家</li>
                <li id="soft4_to_4" onclick="soft('soft4',4,4)">3D排列3统计分析软件</li>
             </ul>
        </div>-->
        <div class="soft-contbox" id="soft4_mo_1">
            <ul>
               <li>
                 <div class="div1">
                     <ul>
                        <li><img src="../images/soft/jncazq.jpg" alt="369竞彩精算师" style="border:1px solid #ececec" width="100" height="100"></li>
                        <li style="width:150px;line-height:30px;margin-left:10px;">
                            <h1>369竞彩精算师</h1>
                            <a href="http://www.369cai.com/download.htm?fileName=jcsetup.zip" style="color:#060">本地下载</a><a href="#" target="_blank" style="margin-left:30px;">软件投注指南</a><br />
                            <!--<a href="http://www.onlinedown.net/soft/118821.htm" style="color:#060">华军下载</a>-->
                        </li>
                     </ul>  
                 </div>
                 <div style="margin-top:10px;">
                    <fieldset class="fieldset">
                        <legend class="legend"><em>玩法技巧</em></legend>
                        <ul>
                           <s:iterator value="jclqList">
                           		<li>·<a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur();"><s:property value="title"/></a></li>
                           </s:iterator>
                        </ul>
                  </fieldset>
                 </div>
              </li>
               <li style="border-left:1px dotted #666;margin-left:7px; 
               padding-left:20px;">
                  <strong>[功能介绍]</strong><br />
                    <b style="font-weight:normal; line-height:26px;">
                       [369竞彩精算师软件]具备几大独创功能： </p>
				  <p>（1）贯彻始终的总体投资收益回报计算和模拟，在拆分和过滤的任何时刻都不仅计算单注的奖金情况，而且计算总体的最大和最少奖金情况，必能够模拟在特定的赛果情况下的投资收益，使您在投入的同时心中有数，不至于中奖了依然亏损。 </p>
				  <p>（2）独创三层塔式叠加，结合竞彩的串玩法，将您的投注选择最多可分个人判断...</p>
                 <a href="/help/jchelp.htm" target="_blank" style="color:#f60;">[详细介绍]</a><br />
                 
                    </b>
               </li>
            </ul>
        </div>
    </div>
    
    <!--main box end-->
    <div class="clear"></div>
</div>
<%@include  file="../foot.jsp"%>
</body>
</html>
