<%@ page language="java" import="java.util.*,com.xsc.lottery.entity.business.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="420604765763613116375" />
<meta name="keywords" content="一彩票,竞彩,彩票,彩票合买,彩票软件,双色球,足彩,体彩">
<meta name="description" content="一彩票成立于2002年，十年彩票路，缩水第一人。目前彩票网站涵盖了福彩、体彩、足彩、竞彩各个彩种。提供各期双色球开奖，彩票软件，缩水过滤。彩票改变生活，我们改变彩票。是当前中国在线彩票行业的领导者。">
<title>一彩票_竞彩_彩票_彩票合买_彩票软件_双色球_足彩_体彩</title>
<link href="./css/default.css" type="text/css" rel="stylesheet" />
<link href="./css/css.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="/chart/favicon.ico" /> 
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
<script src="js/jquery.effects.core.js"></script>
<script type="text/javascript" src="js/util/validate.js"></script>
<script type="text/javascript" src="js/jquery.ajaxfileupload.js"></script>
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
            dMarquee("ZJ-user-scroll");
            dMarquee("HMMR");
		}
	);
	function buy(){
	loginType = 0;
	var tt="${customer.nickName}"
	if(tt==""){
	 $( "#dialog-form" ).dialog( "open" );
		  refreshCaptcha2();}
		  else{
		     onQuickBet();
		  }
	}
	function buy1(){
	loginType = 1;
	var tt1="${customer.nickName}"
	if(tt1==""){
	 $( "#dialog-form" ).dialog( "open" );
		  refreshCaptcha2();}
		  else{
		   document.frames('zcdz_iframe').onIndexSubmit();
		  }
	}
	
			
	function buy2(){
	 loginType = 2;
	 $( "#dialog-form" ).dialog( "open" );
	 refreshCaptcha2();
	}
</script>
</head>
 
<body>
<%@include  file="head.jsp"%>
<div class="clear"></div>
<!--浮动QQ客服 start-->
<div class="qqbox" id="divQQbox">
 
  <div class="qqlv" id="meumid" onmouseover="show()"><img src="/images/369caibg/lxkf.png"></div>
 
  <div class="qqkf" style="display:none;" id="contentid" onmouseout="hideMsgBox(event)">
 
    <div class="qqkfbt" onmouseout="showandhide('qq-','qqkfbt','qqkfbt','K',5,1);" id="qq-1" onfocus="this.blur();" >客 服 服 务</div>
 
    <div id="K1">
 
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:2432882152:4" border="0">&nbsp;2432882152</a></div>
 
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=2211985193&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:2211985193:4" border="0">&nbsp;2211985193</a></div>
 
    </div>
 
    <div class="qqkfbt" onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,2);" id="qq-2" onfocus="this.blur();">关 注 微 博</div>
 
    <div id="K2" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="http://www.weibo.com/369cai" target="_blank"><img src="../images/img/callcenter_4.jpg" border="0" width="20" height="20">&nbsp;369CAI新浪</a></div>
 
      <div class="qqkfhm bgdh"> <a href="http://t.qq.com/caipiao369" target="_blank"><img src="../images/img/callcenter_3.gif" border="0" width="15" height="15">&nbsp;369CAI腾讯</a></div>
 
    </div>
 
    <div class="qqkfbt" onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,3);" id="qq-3" onfocus="this.blur();"> 商 务 合 作</div>
    
    <div id="K3" style="display:none">
 
      <div class="qqkfhm bgdh"><img src="../images/img/gif-0393.gif" border="0">&nbsp;010 - 56203469</div>
      <div class="qqkfhm bgdh"> <a href="http://wpa.qq.com/msgrd?V=1&Uin=464623812&Site=" target="_blank"><img src="http://wpa.qq.com/pa?p=1:464623812:4" border="0">&nbsp;464623812</a></div>
      <div class="qqkfhm bgdh">联系人：刘先生</div>
 
    </div>
 
    <div class="qqkfbt"  onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,4);" id="qq-4" onfocus="this.blur();">技 术 支 持</div>
 
    <div id="K4" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="tencent://message/?uin=2211985193"><img src="http://wpa.qq.com/pa?p=1:2211985193:4" border="0">&nbsp;2211985193</a><br/></div>

      <div class="qqkfhm bgdh">联系人：宋先生</div>
 
    </div>
 
    <div class="qqkfbt" onClick="showandhide('qq-','qqkfbt','qqkfbt','K',5,5);" id="qq-5" onfocus="this.blur();">投 诉 建 议</div>
 
    <div id="K5" style="display:none">
 
      <div class="qqkfhm bgdh"> <a href="tencent://message/?uin=2432882152"><img src="http://wpa.qq.com/pa?p=1:2432882152:4" border="0">&nbsp;2432882152</a></div>
 
      <div class="qqkfhm bgdh">联系人：李小姐</div>
 
    </div>
 
  </div>
 
</div>
 
<script language="javascript">
 
function showandhide(h_id,hon_class,hout_class,c_id,totalnumber,activeno) {
 
    var h_id,hon_id,hout_id,c_id,totalnumber,activeno;
 
    for (var i=1;i<=totalnumber;i++) {
 
        document.getElementById(c_id+i).style.display='none';
 
        document.getElementById(h_id+i).className=hout_class;
 
    }
 
    document.getElementById(c_id+activeno).style.display='block';
 
    document.getElementById(h_id+activeno).className=hon_class;
 
}
 
var tips; 
var theTop = 100;
 
var old = theTop;
 
function initFloatTips() 
{ 
        tips = document.getElementById('divQQbox');
 
        moveTips();
 
}
 
function moveTips()
 
{
 
                   var tt=50; 
                  if (window.innerHeight) 
                  {
 
                pos = window.pageYOffset  
 
                  }else if (document.documentElement && document.documentElement.scrollTop) {
 
                pos = document.documentElement.scrollTop  
 
                  }else if (document.body) {
 
                    pos = document.body.scrollTop;  
 
                  }
 
                  //http:
 
                  pos=pos-tips.offsetTop+theTop; 
                  pos=tips.offsetTop+pos/10; 
                  if (pos < theTop){
 
                         pos = theTop;
 
                  }
 
                  if (pos != old) { 
                         tips.style.top = pos+"px";
 
                         tt=10;//alert(tips.style.top);  
 
                  }
 
                  old = pos;
 
                  setTimeout(moveTips,tt);
 
}
 
initFloatTips();
 
        if(typeof(HTMLElement)!="undefined")//给firefox定义contains()方法，ie下不起作用
 
                {  
 
                  HTMLElement.prototype.contains=function (obj)  
 
                  {  
 
                          while(obj!=null&&typeof(obj.tagName)!="undefind"){//
 
           　　 　if(obj==this) return true;  
 
           　　　        　obj=obj.parentNode;
 
           　　          }  
 
                          return false;  
 
                  }
 
        }
 
function show()
 
{
 
        document.getElementById("meumid").style.display="none"
 
        document.getElementById("contentid").style.display="block"
 
}
 
        function hideMsgBox(theEvent){
 
          if (theEvent){
 
                var browser=navigator.userAgent;
 
                if (browser.indexOf("Firefox")>0){//Firefox
 
                    if (document.getElementById("contentid").contains(theEvent.relatedTarget)) {
 
                                return
 
                        }
 
                }
 
                if (browser.indexOf("MSIE")>0 || browser.indexOf("Presto")>=0){
 
                if (document.getElementById('contentid').contains(event.toElement)) {
 
                            return;//结束函式
 
                    }
 
                }
 

          }
 
          document.getElementById("meumid").style.display = "block";
 
          document.getElementById("contentid").style.display = "none";
 
        }
 
</script>
<!--浮动QQ客服 end-->
<!--Web Body start-->
<div class="outer">
	<!--左栏 start-->
	<div class="leftColumn">
		<!--选择彩票 start-->
		<div class="selectLottery">
			<div class="slTitileBg">
				<div class="slTitile">选择彩票</div>
			</div>
			<div class="fuCaiBg">
				<div class="fuCaiTitle">福彩：</div>
                   <div class="jiajiang"></div>
				<div class="fuCaiCon">
					<ul>
						<li><a href="lottery/index.htm?lotteryType=ssq" onfocus="this.blur()">双色球</a></li>
						<li><a href="lottery/index.htm?lotteryType=qlc" onfocus="this.blur()">七乐彩</a></li>
						<li><a href="lottery/index.htm?lotteryType=3d" onfocus="this.blur()">福彩3D</a></li>
					</ul>
				</div>
			</div>
			<div class="tiCaiBg">
				<div class="tiCaiTitle">体彩：</div>
				<div class="tiCaiCon">
					<ul>
						<li><a href="lottery/index.htm?lotteryType=dlt" onfocus="this.blur()">大乐透</a></li>
						<li><a href="lottery/index.htm?lotteryType=pls" onfocus="this.blur()">排列3</a></li>
						<li><a href="lottery/index.htm?lotteryType=qxc" onfocus="this.blur()">七星彩</a></li>
						<li><a href="lottery/index.htm?lotteryType=plw" onfocus="this.blur()">排列5</a></li>
					</ul>
				</div>
			</div>
            <div class="clear"></div>
			<div class="gaoPinBg">
				<div class="gaoPinTitle">足彩：</div>
				<div class="gaoPinCon">
					<ul>
						<li><a href="lottery/index.htm?lotteryType=14sfc" onfocus="this.blur()">14场胜负彩</a></li>
						<li><a href="lottery/index.htm?lotteryType=r9" onfocus="this.blur()">任选9场</a></li>
						<li><a href="lottery/index.htm?lotteryType=4cjq" onfocus="this.blur()">4场进球彩</a></li>
						<li><a href="lottery/index.htm?lotteryType=6cb" onfocus="this.blur()">6场半全场</a></li>
					</ul>
				</div>
			</div>
            <div class="clear"></div>
            <div class="jingCaiBg">
				<div class="jingCaiTitle">竞彩<br />足球：</div>
                <div class="newwanfa"></div>
				<div class="jingCaiCon">
					<ul>
						<li><a href="lottery/JCZQIndex.htm" onfocus="this.blur()">让球胜平负</a><a href="#"></a></li>
						<li><a href="lottery/ZJQSIndex.htm" onfocus="this.blur()">总进球</a> <a href="#"></a></li>
						<li><a href="lottery/BQCIndex.htm" onfocus="this.blur()">半全场</a> <a href="#"></a></li>
						<li><a href="lottery/CBFIndex.htm" onfocus="this.blur()">猜比分</a> <a href="#"></a></li>
					</ul>
				</div>
			</div>
            <div class="clear"></div>
            <div class="lanQIUBg">
				<div class="jingCaiTitle">竞彩<br />篮球：</div>
                <div class="newwanfalanqiu"></div>
				<div class="jingCaiCon">
					<ul>
						<li><a href="lottery/JCLQSFIndex.htm" onfocus="this.blur()">胜负</a><a href="#"></a></li>
						<li><a href="lottery/JCLQRFSFIndex.htm" onfocus="this.blur()">让分胜负</a> <a href="#"></a></li>
						<li><a href="lottery/JCLQSFCIndex.htm" onfocus="this.blur()">胜分差</a> <a href="#"></a></li>
						<li><a href="lottery/JCLQDXFIndex.htm" onfocus="this.blur()">大小分</a> <a href="#"></a></li>
					</ul>
				</div>
			</div>
		</div>
		<!--选择彩票 end-->
        <div class="nothingathere"></div>
       <div class="door_container">
              <div class="TabTitle">
                <ul id="myTab">
                    <li class="active" onmouseover=nTabs(this,0);>正在中奖</li>
                    <li class="normal" onmouseover=nTabs(this,1);>中奖用户</li>
                </ul>
        	  </div>
              <div class="TabContent">
                <div class="none" id=myTab_Content1>
                  <div class="star">
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
                <div id=myTab_Content0>
                     <div class="star">
                       <div class="titieofusers">
                         <ul>
                           <li>用户名</li>
                           <li>彩种</li>
                           <li>时间</li>
                           <li>奖金</li>
                         </ul>
                       </div>
                       <ul id="ZJ-user">
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
						window.onload = function() {
						 
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
                  </div>
                </div>
              </div>
            </div>
        <div class="clear"></div>
        <!--专家推荐 start-->
		<div class="publicModule">
			<div class="pTitileBg">
				<div class="pTitile">专家推荐</div>
				<div class="pMore"><a href="/lotteryInfo/" onfocus="this.blur()">更多&gt;&gt;</a></div>
			</div>
			<div class="pCon">
				<ul>
					<s:iterator value="expertList" status="st">
						<li class="shortenTitle"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">
						<s:property value="title"/></a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<!--专家推荐 end-->
        
	</div>
	<!--左栏 end-->
    <!--main start-->
    <div class="main">
    	<!--banner start-->
    	<div class="banner">
    		<div class="slide">
				<div id="slides">
					<div class="slides_container">
						<div> 
							<a href="/huodong.html"  target="_blank" title=""><img alt="" src="images/index/bnza.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>保你中</p></div>
						</div>
						<div> 
							<a href="/huodong.html"  target="_blank" title=""><img alt="" src="images/index/song100.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>中1000送100</p></div>
						</div>
						<div> 
							<a href="/help/jchelp.htm"  target="_blank" title=""><img alt="" src="images/index/jingsuanshi.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>竞彩精算师软件玩串必备</p></div>
						</div>
						
						<div> 
							<a href="/software/"  target="_blank" title="" onfocus="this.blur()"><img alt="" src="images/index/song.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>充值送软件</p></div>
						</div>

						<div style="display:none;" >
							<a href="/software/index.htm?action=moreWin" target="_blank"  title="" onfocus="this.blur()"><img alt="" src="images/index/zhongjiang.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>软件中奖用户</p></div>         							                           
						</div>
	                    <div style="display:none;" >
							<a href="/software/" target="_blank" title="" onfocus="this.blur()"><img alt="" src="images/index/suoshui.jpg" width="470" height="198"/></a> 
							<div class="caption"><p>缩水概念发起人</p></div>         							                           
						</div>
					</div>
				</div> 
		  </div>
    	</div>
        <!--banner end-->
        <!--会员登陆 start-->
        <div class="loginsite">
        	<iframe src="/login.htm" class="loginFrame" scrolling="no" frameborder="0"></iframe>
        </div>
        <!---会员登陆 end-->
		<div class="clear"></div>
        <!--开奖信息与活动 start-->
         <div class="OpenInforBg">
			<div class="OpenInfor">
            	<div class="GGTitile">
            		<ul id="Active">
                		<li class="GGTitile1 GGTCurrent_bg">活动专区</li>
                    	<li class="GGTitile2" onclick="window.open('/prizedetail/', '_blank')">开奖信息</li>
                	</ul>
            	</div>
            	<div class="GGMore"><a href="/prizedetail/" target="_blank" title="" onfocus="this.blur()">更多&gt;&gt;</a></div>
        	</div>
			<div class="clear"></div>					
			<!-- 活动专区 start -->
	      <div class="OpenInforCon" id="Active1">
                <div class="newhuodong">
                </div>
            	<div class="Active_pic">
                	<a href="/software/" target="_blank"  title="" onfocus="this.blur()"><img src="images/index/ssq.JPG" width="186" height="90" /></a>
                </div>
                <div class="Active_pic">
                	<a href="/software/" target="_blank"  title="" onfocus="this.blur()"><img src="images/index/3d.JPG" width="186" height="90" /></a>
                </div>
                <a href="/software/" target="_blank"  title="" onfocus="this.blur()"><div class="Active_text">双色球,乐透,3D,足彩,任九,竞彩 全系彩票软件 免费下载使用！</div></a>
                <a href="/software/" target="_blank"  title="" onfocus="this.blur()"><div class="Active_text">双色球缩水过滤 3D快选优化，一步到位帮你把握大奖！</div></a>
           </div>
			<!-- 活动专区 end -->
            <!-- 开奖信息 start-->
            <div class="OpenInforCon-shuang" style=" display:none;" id="Active2">
            	<div class="OpenInforList_title">
					<ul>
						<li class="List_title1">彩种</li>
						<li class="List_title2">期号</li>
						<li class="List_title3">开奖号码</li>
					</ul>
				</div>
                <div class="clear"></div>
                
				<!--双色球 start-->
				<%
					Map<String, LotteryTerm> allOpenTermMap = (Map<String, LotteryTerm>)request.getAttribute("allOpenWin");
				%>
				<%
       				String[] openresultArray = allOpenTermMap.get("ssq").getResult().split("\\|");
       				String[] area1=openresultArray[0].split(",");
       				String[] area2=openresultArray[1].split(",");
       			%>
                <div class="lotterylist_content">
                	<div class="lotterylist_t1">双色球</div>
                    <div class="lotterylist_t2"><s:property value="allOpenWin.get('ssq').termNo"/></div>
                    <div class="lotterylist_t3">
                    	<ul>
                        	<li class="ball_up_false ball_down_true" ><%=area1[0] %></li>
                            <li class="ball_up_false ball_down_true" ><%=area1[1] %></li>
                            <li class="ball_up_false ball_down_true" ><%=area1[2] %></li>
                            <li class="ball_up_false ball_down_true" ><%=area1[3] %></li>
                            <li class="ball_up_false ball_down_true" ><%=area1[4] %></li>
                            <li class="ball_up_false ball_down_true" ><%=area1[5] %></li>
                            <li class="ball_up_false blueball_current" ><%=area2[0] %></li>
                        </ul>
                    </div>
                    <div class="lotterylist_t4">
                    	<ul>
                        	<li><a href="lottery/index.htm?lotteryType=ssq" style="color:#FF0033; font-weight:bold" onfocus="this.blur()">投注</a>&nbsp;</li>
							<li> | <a style="color:#FF0033; font-weight:bold" href="/prizedetail/ssq_<s:property value="allOpenWin.get('ssq').termNo"/>.htm" onfocus="this.blur()">详细</a></li>
                        </ul>
                    </div>
					
				</div>       
                <!--双色球 end-->
                <div class="clear"></div>
				<!--福彩3D start-->
				<% openresultArray = allOpenTermMap.get("3d").getResult().split(",");%>
                <div class="lotterylist_content">
                	<div class="lotterylist_t1">福彩3D</div>
                    <div class="lotterylist_t2"><s:property value="allOpenWin.get('3d').termNo"/></div>
                    <div class="lotterylist_t3">
                    	<ul>
                        	<li class="ball_up_false ball_down_true"><%=openresultArray[0] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[1] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[2] %></li>
                        </ul>
                    </div>
                    <div class="lotterylist_t4">
                    	<ul>
                        	<li><a href="lottery/index.htm?lotteryType=3d" style="color:#FF0033; font-weight:bold" onfocus="this.blur()">投注</a>&nbsp;</li>
							<li> | <a style="color:#FF0033; font-weight:bold" href="/prizedetail/3d_<s:property value="allOpenWin.get('3d').termNo"/>.htm" onfocus="this.blur()">详细</a></li>
                        </ul>
                    </div>
					
				</div>       
                <!--福彩3D end-->
                <div class="clear"></div>
				<!--大乐透 start-->
				<% 	openresultArray = allOpenTermMap.get("dlt").getResult().split("\\|");
                	area1=openresultArray[0].split(",");
                    area2=openresultArray[1].split(",");
                %>
                <div class="lotterylist_content">
                	<div class="lotterylist_t1">大乐透</div>
                    <div class="lotterylist_t2"><s:property value="allOpenWin.get('dlt').termNo"/></div>
                    <div class="lotterylist_t3">
                    	<ul>
                        	<li class="ball_up_false ball_down_true"><%=area1[0] %></li>
                            <li class="ball_up_false ball_down_true"><%=area1[1] %></li>
                            <li class="ball_up_false ball_down_true"><%=area1[2] %></li>
                            <li class="ball_up_false ball_down_true"><%=area1[3] %></li>
                            <li class="ball_up_false ball_down_true"><%=area1[4] %></li>
                            <li class="ball_up_false blueball_current"><%=area2[0] %></li>
                            <li class="ball_up_false blueball_current"><%=area2[1] %></li>
                        </ul>
                    </div>
                    <div class="lotterylist_t4">
                    	<ul>
                        	<li><a href="lottery/index.htm?lotteryType=dlt" style="color:#FF0033; font-weight:bold" onfocus="this.blur()">投注</a>&nbsp;</li>
							<li> | <a style="color:#FF0033; font-weight:bold" href="/prizedetail/dlt_<s:property value="allOpenWin.get('dlt').termNo"/>.htm" onfocus="this.blur()">详细</a></li>
                        </ul>
                    </div>
					
				</div>       
                <!--大乐透 end-->
                <div class="clear"></div>
				<!--排列3 start-->
				<% openresultArray = allOpenTermMap.get("pls").getResult().split(",");%>
                <div class="lotterylist_content">
                	<div class="lotterylist_t1">排列3</div>
                    <div class="lotterylist_t2"><s:property value="allOpenWin.get('pls').termNo"/></div>
                    <div class="lotterylist_t3">
                    	<ul>
                        	<li class="ball_up_false ball_down_true"><%=openresultArray[0] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[1] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[2] %></li>
                        </ul>
                    </div>
                    <div class="lotterylist_t4">
                    	<ul>
                        	<li><a href="lottery/index.htm?lotteryType=pls" style="color:#FF0033; font-weight:bold" onfocus="this.blur()">投注</a>&nbsp;</li>
							<li> | <a style="color:#FF0033; font-weight:bold" href="/prizedetail/pls_<s:property value="allOpenWin.get('pls').termNo"/>.htm" onfocus="this.blur()">详细</a></li>
                        </ul>
                    </div>
					
				</div>       
                <!--排列3 end-->
                <div class="clear"></div>
				<!--排列5 start-->
				<% openresultArray = allOpenTermMap.get("plw").getResult().split(",");%>
                <div class="lotterylist_content">
                	<div class="lotterylist_t1">排列5</div>
                    <div class="lotterylist_t2"><s:property value="allOpenWin.get('plw').termNo"/></div>
                    <div class="lotterylist_t3">
                    	<ul>
                        	<li class="ball_up_false ball_down_true"><%=openresultArray[0] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[1] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[2] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[3] %></li>
                            <li class="ball_up_false ball_down_true"><%=openresultArray[4] %></li>
                        </ul>
                    </div>
                    <div class="lotterylist_t4">
                    	<ul>
                        	<li><a href="lottery/index.htm?lotteryType=plw" style="color:#FF0033; font-weight:bold" onfocus="this.blur()">投注</a>&nbsp;</li>
							<li> | <a style="color:#FF0033; font-weight:bold" href="/prizedetail/plw_<s:property value="allOpenWin.get('pls').termNo"/>.htm" onfocus="this.blur()">详细</a></li>
                        </ul>
                    </div>
				</div>       
                <!--排列5 end-->
           	</div>
			<!-- 开奖信息 end-->	
		</div>
        <!--开奖信息和活动 end-->
        <!--网站公告**彩购帮助 start-->
        <div class="GGTitileB">
        	<div class="GGTitile0">
            	<ul>
                	<li class="GGTitile1 GGTCurrent_bg">网站公告</li>
                </ul>
            </div>
            <div class="GGMore"><a href="/help/index.htm" onfocus="this.blur()">更多&gt;&gt;</a></div>
        </div>
        <div class="GGCon" style="display:block;" id="GG1">
        	<ul>
            	<s:iterator value="publicList" status="st">
					<li class="shortenTitle"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">
					<s:property value="title"/></a></li>
				</s:iterator>
            </ul>
        </div>
        <div class="GGCon" id="GG2">
        	<ul>
            	<li>2我们都爱客场进球 西超杯巴萨踏上十</li>
                <li>大乐透同尾号"9"倾巢出动 奖池仍达</li>
                <li>今年3D第五次开出豹子号 全国返奖达</li>
                <li>过把眼瘾：11选5任选五投注实用方法</li>
                <li>低5折起巨惠全城3D电视最高直降买就</li>
            </ul>
        </div>
        <!--网站公告**彩购帮助 end-->
        <div class="clear"></div>
        <!--足彩资讯和竞彩资讯 start-->
         <div class="OpenInforBg">
			<div class="OpenInfor">
            	<div class="GGTitile">
            		<ul id="ZuCai">
                		<li class="GGTitile1 GGTCurrent_bg" onclick="window.open('/lotteryInfo/', '_blank')">足彩资讯</li>
                    	<li class="GGTitile2" onclick="window.open('/lotteryInfo/', '_blank')">竞彩资讯</li>
                	</ul>
            	</div>
            	<div class="GGMore"><a href="/lotteryInfo/" target="_blank" title="" onfocus="this.blur()">更多&gt;&gt;</a></div>
        	</div>
			<div class="clear"></div>					
			<!-- 足彩资讯 start -->
	        <div class="ZC_news" id="ZuCai1">
            	<div class="ZC_news_bg">
            		<ul>
            			<s:iterator value="zcList" status="st">
            			<li><span>[<s:property value="type"/>]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
            			</s:iterator>
            		</ul>
            	</div>
           </div>
			<!-- 足彩资讯 end -->
            <!-- 竞彩资讯 start-->
			<div class="ZC_news"  style="display:none" id="ZuCai2">
            	<div class="ZC_news_bg">
          			<ul>
            			<s:iterator value="jcList" status="st">
            			<li><span>[<s:property value="type"/>]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
            			</s:iterator>
            		</ul>
            	</div>
           </div>
			<!-- 竞彩资讯 end-->	
		</div>
        <!--足彩资讯和竞彩资讯 end-->
        <!--缩水软件 start-->
         <div class="test">
			<div class="tanmer">
				<div class="soft-tanm">
					<span>足彩软件</span>
				</div>
				<div class="soft-more">
				   <span><a href="/software/" onfocus="this.blur()">更多&gt;&gt;</a></span>
				</div>
			</div>
			<div class="WW">
				<img src="images/img/index001_34.png">
			</div>
			<div class="THE-UL">
				<ul>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=9csetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/r9soft.png" width="61" height="61"><br />369任9<br />场软件</a></li>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=sfcsetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/ZCsoft.png" width="61" height="61"/><br />369足彩14<br />场软件 </a></li>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=jqcsetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/ZCsoft.png" width="61" height="61"><br />369进球<br />彩软件 </a></li>
				</ul> 
		  </div>
		</div>     
		
        
        <!--缩水软件-->
        <div class="clear"></div>
        <!--数字彩资讯\双色球资讯和福彩3D资讯 start-->
         <div class="OpenInforBg">
			<div class="OpenInfor">
            	<div class="GGTitile">
            		<ul id="Data">
                		<li class="GGTitile1 GGTCurrent_bg" onclick="window.open('/lotteryInfo/', '_blank')">彩票新闻</li>
                    	<li class="GGTitile2" onclick="window.open('/lotteryInfo/', '_blank')">双色球资讯</li>
                        <li class="GGTitile2" onclick="window.open('/lotteryInfo/', '_blank')">福彩3D资讯</li>
                	</ul>
            	</div>
            	<div class="GGMore"><a href="/lotteryInfo/" target="_blank" title="" onfocus="this.blur()">更多&gt;&gt;</a></div>
        	</div>
			<div class="clear"></div>					
			<!-- 数字彩资讯 start -->
	        <div class="ZC_news" id="Data1">
            	<div class="ZC_news_bg">
          			<ul>
            			<s:iterator value="szcList" status="st">
            			<li><span>[<s:property value="type"/>]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
            			</s:iterator>
            		</ul>
            	</div>
           </div>
			<!-- 数字彩资讯 end -->
            <!-- 双色球资讯 start-->
			<div class="ZC_news"  style="display:none" id="Data2">
            	<div class="ZC_news_bg">
          			<ul>
            			<s:iterator value="ssqList" status="st">
            			<li><span>[<s:property value="type"/>]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
            			</s:iterator>
            		</ul>
            	</div>
           </div>
			<!-- 双色球资讯 end-->
            <!-- 福彩3D资讯 start-->
			<div class="ZC_news"  style="display:none" id="Data3">
            	<div class="ZC_news_bg">
          			<ul>
            			<s:iterator value="fc3dList" status="st">
            			<li><span>[<s:property value="type"/>]</span><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()">&nbsp;<s:property value="title"/></a></li>
            			</s:iterator>
            		</ul>
            	</div>
           </div>
		   <!-- 福彩3D资讯 end-->		
		</div>
        <!--数字彩资讯\双色球资讯和福彩3D资讯  end-->
        <!--缩水软件 start-->
        
         <div class="test-three">
			<div class="tanmer">
				<div class="soft-tanm-two">
					<span>数字彩软件</span>
				</div>
				<div class="soft-more-two">
				   <span><a href="/software/" onfocus="this.blur()">更多&gt;&gt;</a></span>
				</div>
			</div>
			<div class="WW">
				<img src="images/img/index001_34.png">
			</div>
            <div class="THE-UL">
				<ul>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=3dsetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/3Dsoft.png" width="61" height="61"/><br />一彩票3D排列3软件</a></li>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=ssqsetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/ssqsoft.png" width="61" height="61"/><br />一彩票双色球软件 </a></li>
					<li style="margin-left:7px;"><a href="/download.htm?fileName=lottosetup.zip"  style="text-align:center" onfocus="this.blur()"><img src="images/soft/DLTsoft.png" width="61" height="61"/><br />一彩票乐透软件 </a></li>
				</ul>
		    </div>  
		</div>     
		
		
       
        <!--缩水软件-->
    </div>
    <!--main end-->
    <div class="clear"></div>
    <!--通栏广告 start-->
    <div class="outer">
    	<div class="adver"><img src="images/img/kaiye.jpg" width="980" height="70" /></div>
    </div>
    <!--通栏广告 end-->
    <div class="clear"></div>
    <!--左下main start-->
  	<div class="main_left">
    	<!--双色球快速投注 start-->
        <div class="DB_ball">
        	<div class="ZCDZ_titleBg">
				<div class="ZCDZ_title">双色球快速投注</div>
				<div class="ZCDZ_more"><a href="/lottery/index.htm?lotteryType=ssq" onfocus="this.blur()">进入购买大厅</a></div>
			</div>
            <div class="doubleBall">
        	<div class="dBPic"><img src="images/img/369cai_49.gif" alt="双色球" /></div>
            <div class="dBTime">第<span id="ssqCurTerm" class="red"></span>期 投注截止时间：<span class="red" id="ssqCurTermStopTime"></span></div>
            <div class="dBRed">[红球]从1-33中选6个不重复的整数</div>
            <div class="dBRed dBBlue">[蓝球]从1-16中选1个整数</div>
            <div class="dBLeft">
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
            	<div class="dBNumBtn"><img src="images/369caibg/369cai_55.gif" style="cursor:pointer" onclick="buy()"/></div>
                <div class="dBJiangJin">当前奖池奖金：<span id="prizePool"><fmt:formatNumber type="number" value="${ssqLastTerm.prizePool}" pattern="###,###,###"/></span>元 </div>
                <div class="dBNumBtn1"><img src="images/369caibg/369cai_61.gif" style="cursor:pointer" onclick="randomSsq()"/></div>
                <div class="dBNumBtn2"><img src="images/369caibg/369cai_63.gif" style="cursor:pointer" onclick="clearSsq()"/></div>
            </div>
            <div class="dBRight">
            	<ul>
                	<li style="width:140px;">每注2元，最高奖 1000万</li>
                    <li style="width:60px"><a href="/prizedetail/" onfocus="this.blur()">[查看开奖]</a></li>
                    <li style="width:60px"><a href="/lottery/index.htm?lotteryType=ssq" onfocus="this.blur()">[标准投注]</a></li>
                    <li style="width:60px"><a href="/download.htm?fileName=ssqsetup.zip" target="_blank" title="" onfocus="this.blur()">[缩水软件]</a></li>
                    <li style="width:60px"><a href="/direction/fbt.htm?type=ssq&tt=js" onfocus="this.blur()">[图表分析]</a></li>
                </ul>
            </div>
           </div>
        </div>
        <!--双色球快速投注 end-->
        <!--足彩对阵　start-->
        <div class="zcRecom">
			<div class="zcTitileBg">
				<div class="ZCDZ_title">足彩对阵</div>
				<div class="ZCDZ_more"><a href="/lottery/index.htm?lotteryType=14sfc" onfocus="this.blur()">进入购买大厅</a></div>
			</div>
            <div>
			<iframe id="zcdz_iframe" src="/lottery/index.htm?action=listAllMatchArrageForZCDZ" scrolling="no" frameborder="0" style="width:730px"></iframe>
            </div>
        </div>
        <!--足彩对阵　end-->
        <div class="clear"></div>
        <!--合买推荐 start-->
        <div class="togetherRecom">
			<div class="tRTitileBg">
				<div class="ZCDZ_title">合买推荐<img src="images/369caibg/gif-0408.gif" alt="什么是合买？" style="margin-left:20px;" onmouseover="mopen('m2')" onmouseout="mclosetime()">
                      <div id="m2" onmouseover="mcancelclosetime()" onmouseout="mclosetime()" style="visibility:hidden">
                         <ul>
                            <li class="list-title"><img src="images/369caibg/vv-xpincon_35.png" width="15" height="15" style="margin-right:10px;">什么是合买？</li>
                            <li class="listcont">指由两个或两个以上的个人共同出资购买彩票。</li>
                            <li class="list-title"><img src="images/369caibg/vv-xpincon_35.png" width="15" height="15" style="margin-right:10px;">如何参与合买</li>
                            <li class="listcont">方式1:在列表中选择方案—输入认购份数—点击"购买"。<br />方式2:在列表中选择方案—点击操作下的“查看”，进入方案详情页—输入认购份数—点击“立即购买”。</li>
                            <li class="list-title"><img src="images/369caibg/vv-xpincon_35.png" width="15" height="15" style="margin-right:10px;">合买中奖后的奖金分配</li>
                            <li class="listcont">
                             合买中奖后，若方案不盈利(即税后奖金小于方案本金)，税后奖金将按参与份额比例分配到该方案的各参与会员的预付款中。
                             <font face="Barbie" style="letter-spacing:2px">369CAI</font>平台不收取任何佣金或者手续费。
                            </li>
                            <li class="list-title"><img src="images/369caibg/vv-xpincon_35.png" width="15" height="15" style="margin-right:10px;">方案保底</li>
                            <li class="listcont">发起人承诺合买截止后，如果方案还没有满员，发起人再投入先前承诺的金额以最大限度让方案成交。最低保底金额为方案总金额的20%。</li>
                            <li style="margin-top:10px;margin-left:240px;"><a href="/help/help-3-2.htm" target="_blank" onfocus="this.blur()">更多&gt;&gt;</a></li>
                         </ul>
                      </div>
                </div>
			</div>
            <div>
			<iframe id="groupbuy_iframe" src="/lottery/joinGroupBuy.htm?action=listAllGroupBuy" scrolling="no" frameborder="0" style="width:730px"></iframe>
            </div>
        </div>	
        <!--合买推荐  end-->
    </div>
    <!--左下main end-->
    <!--右下右栏 start-->
    <div class="rightColumn">
    	<!--双色球专家 start-->
        <div class="SSQ">
        	<div class="SSQTitle">
            	<ul>
                	<li class="SSQ_Title_item GGTCurrent_bg">双色球专家</li>
                    <li class="SSQ_Title_item1 "><a href="/direction/fbt.htm?type=ssq&tt=js" onfocus="this.blur()">图表分析</a></li>
                </ul>
            </div>
        </div>
        <div class="SSQ_Con">
        	<div class="SSQ_pic"><img src="images/img/shuangseqiu01.jpg" width="63" height="63" alt="XXX" /></div>
            <div class="SSQ_text">
                <ul>
                	<li><span>
               	    <h3>豆丁</h3></span></li>
                    <li><span>资深彩票分析专家</span></li>
                    <!--<li>关注人数：<span>25005人&nbsp;&nbsp;&nbsp;</span></li>-->
            	</ul>
          	</div>
            <div class="clear"></div>
            <div class="fangan">
            	<ul>
                	<li><span>她的推荐:</span></li>
                    <li> &nbsp;</li>
                </ul>
            </div>
            <s:iterator value="authorList1" status="st">
            	<div class="SSQ_date"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()"><s:property value="title"/><br /></a></div>
            	
            </s:iterator>
            <div class="clear"></div>
            <div class="SSQ_More"></div>
        </div>        
        <!--双色球专家 end-->
        <!--专家推荐 start-->
        <div class="SSQ">
        	<div class="SSQTitle">
            	<ul>
                	<li class="SSQ_Title_item GGTCurrent_bg">专家推荐</li>
 
                </ul>
            </div>
        </div>
        <div class="SSQ_Con">
        	<div class="SSQ_pic"><img src="images/img/zucaiduizhen02.jpg" width="63" height="63" alt="XXX" /></div>
            <div class="SSQ_text">
                <ul>
                	<li><span>
           	    <h3>张路</h3></span></li>
                    <li>业界资深的足球评论员</li>
                    <!--<li>关注人数：<span>25005人&nbsp;&nbsp;&nbsp;</span></li>-->
            	</ul>
    </div>
            <div class="clear"></div>
            <div class="fangan">
            	<ul>
                	<li><span>他的评论:</span></li>
                    <li> &nbsp;&nbsp;</li>
                </ul>
            </div>
            <s:iterator value="authorList2" status="st">
            	<div class="SSQ_date"><a href="/lotteryInfo/index.htm?action=detail&id=<s:property value="id"/>" target="_blank" onfocus="this.blur()"><s:property value="title"/><br /></a></div>
            	
            </s:iterator>
            <div class="clear"></div>
            <div class="SSQ_More"></div>
        </div>        
        <!--专家推荐 end-->
        <!--缩水软件 start-->
        <div class="test-two">
			<div class="tanmer">
				<div class="soft-tanm">
					<span>竞彩软件</span>
				</div>
				<div class="soft-more">
				   <span><a href="/software">更多&gt;&gt;</a></span>
				</div>
			</div>
			<div class="WW">
				<img src="images/img/index001_34.png">
			</div>
            <div class="THE-UL">
				<ul>
					<li style="margin-left:7px;"><a href="/software" style="text-align:center" onfocus="this.blur()"><img src="images/soft/jcsf.jpg"  width="61" height="61"/><br />369竞彩胜负软件</a></li>
					<li style="margin-left:8px;"><a href="/software" style="text-align:center" onfocus="this.blur()"><img src="images/soft/jcjq.jpg"  width="61" height="61"/><br />369竞彩进球软件</a></li>
					<li style="margin-left:8px;"><a href="/software"style="text-align:center" onfocus="this.blur()"><img src="images/soft/jcbqc.jpg"  width="61" height="61"/><br />369竞彩半全场软件 </a></li>
				</ul>
		    </div>  
	  </div>
	  
        
        <!--缩水软件-->
        <!--合买名人 start-->
        <div class="SSQ">
        	<div class="SSQTitle">
            	<ul>
                	<li class="SSQ_Title_item GGTCurrent_bg">合买名人</li>
                    <li class="SSQ_Title_item1 "></li>
                </ul>
            </div>
        </div>
        <div class="HM_Con">
            <div class="HM_text">
               <div class="titieofusers">
                 <ul>
                   <li>用户名</li>
                   <li>彩种</li>
                   <li>时间</li>
                   <li>奖金</li>
                 </ul>
               </div>
               <ul id="HMMR">
                  <li>
                    <table width="240" border="0" cellspacing="0" cellpadding="0">
                      <s:iterator value="page1.result">
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
                
                
            </div>   
            <div class="clear"></div>
           
            <!-- 
            <div class="SSQ_date">竞彩足球<span>20110921</span>期<span>￥944</span>&nbsp;进度<span>29%</span></div>
            <div class="SSQ_order"><span>抢购</span></div>
            <div class="clear"></div>
            <div class="SSQ_date">竞彩足球<span>20110921</span>期<span>￥944</span>&nbsp;进度<span>29%</span></div>
            <div class="SSQ_order"><span>抢购<div style="display:none; overflow:hidden;"></div></span></div>
            <div class="clear"></div>
            <div class="SSQ_More">更多&gt;&gt;</div> -->
        </div>        
        <!--合买名人 end-->
        <div class="clear"></div>
        <!--客服-->
        <div class="GGTitileBg">
        	<div class="GGTitile0">
            	<ul>
                	<li class="GGTitile1 GGTCurrent_bg">客服中心</li>
                </ul>
            </div>
        </div>
        <div class="CCon">
				<div class="callcenter_1">
                	<ul>
                    	<li class="callcenter_1_title">在线客服</li>
                        <li class="callcenter_li"><a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank" onfocus="this.blur()">2432882152</a>/<a href="http://wpa.qq.com/msgrd?V=1&Uin=2211985193&Site=" target="_blank" onfocus="this.blur()">2211985193</a></li>
                    </ul>
                </div>
                <div class="callcenter_2">
                	<ul>
                    	<li class="callcenter_2_title">咨询热线</li>
                        <li class="callcenter_li">010 - 56203469</li>
                    </ul>
                </div>
                <div class="callcenter_3">
                	<ul>
                   	  <li class="callcenter_3_title">腾讯微博</li>
                        <li class="callcenter_li"><a href="http://t.qq.com/caipiao369" target="_blank" onfocus="this.blur()">关注369cai微博</a></li>
                    </ul>
                </div>
                <div class="callcenter_4">
                	<ul>
                    	<li class="callcenter_4_title">新浪微博</li>
                        <li class="callcenter_li"><a href="http://www.weibo.com/369cai" target="_blank" onfocus="this.blur()">关注369cai微博</a></li>
                    </ul>
                </div>
		  </div>
        <div class="clear"></div>
        
		<!--客服 end--> 
    </div>
    <!--右下右栏 end-->
    <div class="clear"></div>
    <%@include  file="foot.jsp"%>
</div>
<!--Web Body end-->
</body>
</html>

