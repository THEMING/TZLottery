<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="双色球,大乐透,福彩3D,竞彩,体彩,足彩,任9场,胜负彩">
<meta name="description" content="一彩票购彩大厅投注大厅频道覆盖足彩、体彩、福彩的各彩种，包括双色球，大乐透，14场胜负彩，任9场，胜平负，总进球，半全场猜比分。打造中国中奖率最高的网络彩票平台，持续为客户创造最大价值。">
<title>一彩票_购彩大厅_投注大厅_双色球_大乐透</title>
<link href="/css/common.css" rel="stylesheet" type="text/css" />
<link href="/css/default.css" type="text/css" rel="stylesheet" />
<link href="/css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/Trend.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/BUY.css"/>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="css/imgscroll.css"/>
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<SCRIPT src="js/xixi.js" type=text/javascript></SCRIPT>
<!--TPwareMenu js start-->
<link rel="stylesheet" href="../css/jquery-ui-1.8.16.custom.css"/>
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/jquery.bgiframe-2.1.2.js"></script>
<script src="../js/jquery.ui.core.js"></script>
<script src="../js/jquery.ui.widget.js"></script>
<script src="../js/jquery.ui.mouse.js"></script>
<script src="../js/jquery.ui.button.js"></script>
<script src="../js/jquery.ui.position.js"></script>
<script src="../js/jquery.ui.dialog.js"></script>
<script src="../js/jquery.effects.core.js"></script>
<script type="text/javascript" src="../js/util/validate.js"></script>
<script type="text/javascript" src="../js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<script src="js/lotteryHall.js" type="text/javascript"></script>
<script>
$(function(){
		$(".TPShrinkRel li").click(function(){
		var liid=parseInt($(this).attr("id"));
		$(this).addClass("TPShrinkMenu5").siblings().removeClass("TPShrinkMenu5");
		lh(liid);
		})

});
function login1(){
$( "#dialog-form" ).dialog( "open" );
		refreshCaptcha2();
}
$().ready(function(){
lotteyHalllogin();
$.LotteryTerm._request({type: "ssq"});
$.LotteryTerm._request({type: "dlt"});
})
</script>
<script>if(parent.frames.length>0)parent.location=location</script>
<!--flash start-->
<script type="text/javascript" src="../js/webwidget_slideshow_dot.js"></script>

<style type="text/css">
<!--
.STYLE1 {font-size: xx-large}
-->
</style>
</head>
<body>
<%@include  file="/head.jsp"%>   
<div class="clear"></div> 
<!--Cont start-->
<div class="ql-upCont">
    <!--ContLeft start-->
	<div class="ql-Cont-Left" >
	    <div class="ql-history">
		    <div class="ql-Tem">
			    <span>近期购买${ssq.prizePool}</span> 
			</div>
			<c:if test="${customer.nickName == null}">
			<div class="ql-Lgn">
			    <a href="#" onclick="login1()">登录</a>后可显示最近<br />
                购买的彩种
			</div>
			</c:if>
			<c:if test="${customer.nickName != null}">
				<%
				out.print("<div style='padding-left:20px; padding-top:10px; line-height:1.7; letter-spacing:4px;'>");
				Set<String> set=(Set)request.getAttribute("ll");
				for(String o:set)
				{String s1=o.split("\\,")[0];
				String s2=o.split("\\,")[1];
				out.print("<a  style='color:#FE5300;' href='"+(s2.equals("jczq")?"/lottery/JCZQIndex.htm?":"/lottery/JCLQSFIndex.htm?lotteryType=")+s2+"'>"+o.split("\\,")[0]+"</a>"+"<br/>");	}
				out.print("</div>");
				 %>	
			</c:if>
		</div>
		<div class="ql-active">
            <div class="ql-Tem">
                <span>活动专区</span> 
            </div>
            <div class="ql-AC"><a href="/huodong.html"><img src="images//AC1.jpg" height="150" width="165"></a></div>
            </div>
        </div>
        <div class="ql-Cont-right">
            <div class="ql-UP"> 
             <DIV class=ql-WInner>
                <DIV class=ql-Tem1><SPAN>我们的大赢家</SPAN> </DIV>
                <DIV class=ql-WinMny>
                  <ul>
                     <li><img src="images//ASQXC.jpg" width="70" height="75"></li>
                     <li><img src="images//CCDT.jpg" width="70" height="75"></li>
                     <li><img src="images//dl.jpg" width="70" height="75"></li>
                     <li><img src="images//ASQXC.jpg" width="70" height="75"></li>
                  </ul>
                </DIV>
				<div class="ql-WiPLE">
				    <span>他们正在中奖</span>
				</div>
                <ul id="scroll">
               <s:iterator value="page.result">
                          <li>${fn:substring(userName,0,2) }**&nbsp;&nbsp;&nbsp;&nbsp;${type}&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="winTime" format="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;<span>${bonus }</span>元</li>
                </s:iterator>
				</ul>
				<script type="text/javascript">
				window.onload = function() {
				 dMarquee('scroll');
				}
				
				function dMarquee(id){
				 var speed=20; //速度
				 var stop=500; //停止时间 
				
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
		  </DIV>
          <DIV class=ddindex_content_lz id=__E_lunzhuan>
                <DIV id=lantern>
                    <DIV id=lanternMain>
                    <DIV id=lanternImg></DIV>
                    </DIV>
                    <DIV style="BORDER-TOP: #ffffff 1px solid; FLOAT: left; BORDER-BOTTOM: #ffffff 1px solid"><IMG onclick=Lantern.moveprevious(); src="images//index_banner_lz_02_left.gif"></DIV>
                    <DIV id=lanternNavy></DIV>
                    <DIV style="BORDER-TOP: #ffffff 1px solid; FLOAT: left; BORDER-BOTTOM: #ffffff 1px solid"><IMG onclick=Lantern.movenext(); src="images//index_banner_lz_02_right.gif"></DIV>
                    <SCRIPT type=text/javascript>
                         document.lanterninfo=function(){
                       Lanterninfo=new Array();
                       Lanterninfo=[       
                           ['images//jingsuanshi.jpg','竞彩精算师隆重优化上线、抢先试用！','http://www.369cai.com/software/'],['images//bnza.jpg','特惠活动之保你中！','http://www.369cai.com/huodong.html'],['images//song100.jpg','特惠活动之 加奖！','http://www.369cai.com/huodong.html'],['images//zhuce1.jpg','注册就免费送软件','http://www.369cai.com/software/'],['images//jiesheng.jpg','软件投注，最高可节省80%！','http://www.369cai.com/software/']
                           ];
                           
                           return Lanterninfo;
                       } 
                       Lantern.info=new Array();
                       Lantern.info=document.lanterninfo();
                       Lantern.init();
                    </SCRIPT>
                </DIV>
             </DIV>
	    </div>
        <div class="clear"></div>
        <div class="ql-HOT">
		    <div class="ql-Tem2">
				<span>热卖彩种</span> 
			</div>
			<div class="ql-SQ">
			    <div class="ql-SSQ">
				   <img src="images//SQ.gif"/>
				</div>
				<div class="ql-ad">
				   <ul>
				      <li style="width:60px;height:15px;margin-left:8px;margin-top:8px;"><a href="/lottery/index.htm?lotteryType=ssq">双色球</a></li>
					  <li style="width:80px;height:15px;margin-left:8px; margin-top:8px;">第${ssq.termNo }期</li><br/>
					  <li style="width:175px;height:15px;margin-top:15px;">本期开奖日期&nbsp;&nbsp;&nbsp;${ssqdate}</li>
				   </ul>
				</div>
				<div class="ql-TIME">
				    <ul>
				      <li style="width:170px;height:15px;font-size:13px;font-weight:bold;margin-left:8px;margin-top:8px; letter-spacing:8px;">&nbsp;当前奖池奖金</li>
					  <li style="width:175px;height:25px;margin-top:8px; letter-spacing:0px; text-align:center;">
					        <a href="/prizedetail/ssq_.htm">${ssqpool } </a>
					  </li>
				   </ul>
				</div>
				<div class="ql-TOUZjiajiang">
				    <ul>
					   <li style=" width:200px;height:20px; font-size:13px; letter-spacing:2px; padding-left:5px;">投注倒计时：<span id="countTime"  style="color:red; font-size:13px; font-weight:bold;">02:03:45</span></li>
				      <li style="padding-left:2px;padding-top:5px;"><a href="/help/help-home-5-3-12.htm"><img src="images//ql-ruan.jpg"></a></li>
					  <li style="margin-left:10px;padding-top:5px;"><a href="/lottery/index.htm?lotteryType=ssq" onfocus="this.blur()"><img src="images//ql-li.jpg"></a></li>
				   </ul>
				</div>
			</div>
			
			<div class="ql-SUPER">
			    <div class="ql-SUPER-IMG">
				   <img src="images//SUPER.gif"/>
				</div>
				<div class="ql-adTWO">
				   <ul>
				      <li style="width:80px;height:15px;margin-left:8px;margin-top:8px;"><a href="/lottery/index.htm?lotteryType=dlt">超级大乐透</a></li>
					  <li style="width:80px;height:15px;margin-left:8px; margin-top:8px;">第${dlt.termNo }期</li><br/>
					  <li style="width:175px;height:15px;margin-top:15px;">本期开奖日期&nbsp;&nbsp;&nbsp;${dltdate}</li>
				   </ul>
				</div>
				<div class="ql-TIMET">
				    <ul>
				      <li style="width:170px;height:15px;font-size:13px;font-weight:bold;margin-left:8px;margin-top:8px; letter-spacing:8px;">&nbsp;当前奖池奖金</li>
					  <li style="width:175px;height:25px;margin-top:8px; letter-spacing:0px; text-align:center;">
					        <a href="/prizedetail/dlt_.htm">${dltpool }</a>
					  </li>
				   </ul>
				</div>
				<div class="ql-TOUZjiajiang">
				    <ul>
					  <li style=" width:200px;height:20px; font-size:13px; letter-spacing:2px; padding-left:5px;">投注倒计时：<span id="countTimed" style="color:red; font-size:13px; font-weight:bold;">02:03:45</span></li>
				      <li style="padding-left:2px;padding-top:5px;"><a href="/help/help-3-10.htm"><img src="images//ql-ruan.jpg"></a></li>
					  <li style="margin-left:10px;padding-top:5px;"><a href="/lottery/index.htm?lotteryType=dlt"><img src="images//ql-li.jpg"></a></li>
				   </ul>
				</div>
			</div>
		</div>
</div>
<div class="clear" style="clear:both;"></div>

</DIV><!--UP end-->
<!--TYPE start-->
<div class="ql-TYPE">
    <div class="TPShrinkMenu">
	  <div class="TPShrinkRel">
			<div class="ql-Temy">
			<span>彩种分类</span> 
			</div>
			<ul>
				<li id="1TP" class="TPShrinkMenu1 TPShrinkMenu5">全部彩种</li>
				<li id="2TP" class="TPShrinkMenu2">明日开奖</li>
				<li id="3TP" class="TPShrinkMenu3">星期二</li>
				<li id="4TP" class="TPShrinkMenu4">星期日</li>
				<li id="6TP" class="TPShrinkMenu6">最热卖</li>
				<li id="7TP" class="TPShrinkMenu7">今日开奖</li>
				<li id="8TP" class="TPShrinkMenu8">星期三</li>
				<li id="9TP" class="TPShrinkMenu9">星期四</li>
				<li id="10TP" class="TPShrinkMenu10">星期五</li>
				<li id="11TP" class="TPShrinkMenu11">星期一</li>
				<li id="12TP" class="TPShrinkMenu12">星期六</li>
			</ul>
	  </div>       
    </div>

	
	 	<div class="TPShrinkCon" style="display:block;" id="TP22">
      <div class="ql-FUCAI">
		    <div class="ql-Tem4">
				<span>竞彩篮球</span>			</div>
            <div id="jinType" class="ql-jinType">
			    <div id="q1-CBF" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/JCLQSFIndex.htm"><img src="images//lqsf.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/JCLQSFIndex.htm">胜负</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/JCLQSFIndex.htm"><img src="images//ql-li.jpg" /></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/lqsshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-SPF" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/JCLQRFSFIndex.htm"><img src="images//lqrfsf.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/JCLQRFSFIndex.htm">让分胜负</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/JCLQRFSFIndex.htm"><img src="images//ql-li.jpg" /></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/lqsshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-BQC" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/JCLQSFCIndex.htm"><img src="images//lqsfc.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/JCLQSFCIndex.htm">胜分差</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/JCLQSFCIndex.htm"><img src="images//ql-li.jpg" /></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/lqsshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-ZJQS" class="ql-PL5">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/JCLQDXFIndex.htm"><img src="images//lqdxf.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/JCLQDXFIndex.htm">大小分</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/JCLQDXFIndex.htm"><img src="images//ql-li.jpg" /></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/lqsshc.htm">开奖公告</a></li>
					</ul>
			    </div>
		    </div>
	  </div>
          <div class="ql-TICAI">
		    <div class="ql-Tem4">
				<span>竞彩足球</span>			</div>
            <div id="jinType" class="ql-jinType">
			    <div id="q1-CBF" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/JCZQIndex.htm"><img src="images//jcspf.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/JCZQIndex.htm">让球胜平负</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   <li style=" width:85px; height:23px; margin-top:4px;padding-left:5px;"><a href="/help/jchelp.htm"><img src="images//ql-ruan.jpg" /></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/JCZQIndex.htm"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/sshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-SPF" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/ZJQSIndex.htm"><img src="images//jczjq.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/ZJQSIndex.htm">总进球</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/ZJQSIndex.htm"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/sshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-BQC" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/BQCIndex.htm"><img src="images//jcbqc.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/BQCIndex.htm">半全场</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/BQCIndex.htm"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/sshc.htm">开奖公告</a></li>
					</ul>
			    </div>
				<div id="q1-ZJQS" class="ql-PL5">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/CBFIndex.htm"><img src="images//jccbf.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/CBFIndex.htm">猜比分</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每日开奖 <br />&nbsp;&nbsp;&nbsp;看球赚钱</li>
					   
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/CBFIndex.htm"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/lottery/sshc.htm">开奖公告</a></li>
					</ul>
			    </div>
		    </div>
	  </div>
         <div class="ql-TICAI">
		    <div class="ql-Tem4">
				<span>福彩</span>
            </div>
            <div id="fuType" class="ql-fuType">
               
			    <div id="q1-SHUANG" class="ql-SHUANG">
				    <ul>
					   
					   <li style="width:61px;height:78px;"><a href="/lottery/index.htm?lotteryType=ssq"><img src="images//SHUANG.gif"></a></li>
					   <li style=" width:100px; height:55px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=ssq">双色球</a></li>
					   <li style=" width:188px; height:40px; margin-top:5px; line-height:1.7;">每周二、四、日晚21：30开奖<br/>单注最高奖金&nbsp;1000&nbsp;万元！</li>
					   <li style=" width:100px; height:23px; margin-top:4px;"><a href="/help/help-home-5-3-12.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style="margin-left:5px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=ssq"><img src="images//ql-li.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/ssq_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=ssq">走势图</a></li>
					</ul>
				
			     </div>
				 <div id="q1-3D" class="ql-3D">
				    <ul>
					   <li style="width:61px;height:78px;"><a href="/lottery/index.htm?lotteryType=3d"><img src="images//33.gif" /></a></li>
					   <li style=" width:100px; height:55px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=3d">福彩3D</a></li>
					   <li style=" width:188px; height:40px; margin-top:5px; line-height:1.7; ">每天20：30开奖 <br />
				       猜对3个号就有奖</li>
					   <li style=" width:100px; height:23px; margin-top:4px;"><a href="/help/help-home-5-3-13.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style="margin-left:5px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=3d"><img src="images//ql-li.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/3d_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=3d">走势图</a></li>
					</ul>
			     </div>
				<div id="q1-QLC" class="ql-QLC">
				    <ul>
					   <li style="width:61px;height:78px;"><a href="/lottery/index.htm?lotteryType=qlc"><img src="images//77.gif"></a></li>
					   <li style=" width:100px; height:55px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=qlc">七乐彩</a></li>
					   <li style=" width:188px; height:40px; margin-top:5px; line-height:1.7;">每周一、三、五21:30开奖<br/>百万奖、期期开</li>
					   <li style=" width:100px; height:23px; margin-top:4px;"><a href="/help/help-3-10.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style="margin-left:5px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=qlc"><img src="images//ql-li.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/qlc_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=qlc">走势图</a></li>
					</ul>
			  </div>
			</div>
      </div>
	  <div class="ql-TICAI">
		    <div class="ql-Tem4">
				<span>体彩</span>			</div>
            <div id="TiType" class="ql-TiType">
			     <div id="q1-DLT" class="ql-DLT">
				    <ul>
					   <li style="width:61px;height:78px;"><a href="/lottery/index.htm?lotteryType=dlt"><img src="images//SUPER.gif"></a></li>
					   <li style=" width:100px; height:55px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=dlt">大乐透</a></li>
					   <li style=" width:188px; height:40px; margin-top:5px; line-height:1.7;">每周二、四、日晚21：30开奖<br/>单注最高奖金&nbsp;1000&nbsp;万元！</li>
					   <li style=" width:100px; height:23px; margin-top:4px;"><a href="/help/help-home-5-3-15.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style="margin-left:5px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=dlt"><img src="images//ql-li.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/dlt_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=dlt">走势图</a></li>
					</ul>
				</div>
				<div id="q1-QXC" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=qxc"><img src="images//xx.gif" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=qxc">七星彩</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">每周二、五、日开奖<br />
				       2元赢取500万</li>
					   <li style=" width:85px; height:23px; margin-top:4px;padding-left:5px;"><a href="/help/help-3-10.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=qxc"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/qxc_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=qxc">走势图</a></li>
					</ul>
		      </div>
				<div id="q1-PLS" class="ql-DLT">
				    <ul>
					   <li style="width:67px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=pls"><img src="images//3.gif" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=pls">排列3</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">天天排列三<br />
				       不用愁吃穿</li>
					   <li style=" width:85px; height:23px; margin-top:4px; padding-left:5px;"><a href="/help/help-home-5-3-13.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=pls"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/pls_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=pls">走势图</a></li>
					</ul>
		      </div>
				<div id="q1-PLW" class="ql-PL5">
				    <ul>
					   <li style="width:67px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=plw"><img src="images//5.gif" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=plw">排列5</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">数字彩，天天开<br />
				       最高奖金10万元</li>
					   <li style=" width:85px; height:23px; margin-top:4px; padding-left:5px;"><a href="/help/help-3-10.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=plw"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/plw_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/fbt.htm?type=plw">走势图</a></li>
					</ul>
			    </div>
		    </div>
	  </div>
	  <div class="ql-TICAI">
		    <div class="ql-Tem4">
				<span>足彩</span>			</div>
            <div id="zuType" class="ql-zuType">
			    <div id="q1-14SFC" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=14sfc"><img src="images//14.gif" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=14sfc">14场胜负彩</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">不定期开奖 <br />&nbsp;&nbsp;&nbsp;2元赢500万</li>
					   <li style=" width:85px; height:23px; margin-top:4px;padding-left:5px;"><a href="/help/help-home-5-3-11.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=14sfc"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/14sfc_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/plt.htm?tt=cc&type=14sfc&football=football">走势图</a></li>
					</ul>
			    </div>
				<div id="q1-R9" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=r9"><img src="images//9.gif" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=r9">任选9场</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">不定期开奖 <br />&nbsp;&nbsp;&nbsp;2元赢500万</li>
					   <li style=" width:85px; height:23px; margin-top:4px;padding-left:5px;"><a href="/help/help-home-5-3-14.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=r9"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/r9_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/zst.htm?tt=cc&type=r9&chartType=zst&football=football">走势图</a></li>
					</ul>
			    </div>
				<div id="q1-4CJQ" class="ql-DLT">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=4cjq"><img src="images//zqjqc.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=4cjq">4场进球彩</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">不定期开奖 <br />&nbsp;&nbsp;&nbsp;2元赢500万</li>
					   <li style=" width:85px; height:23px; margin-top:4px; padding-left:5px;"><a href="/help/help-home-5-3-16.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=4cjq"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/4cjq_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/plt.htm?tt=cc&type=4cjq&football=football">走势图</a></li>
					</ul>
			    </div>
				<div id="q1-6CB" class="ql-PL5">
				    <ul>
					   <li style="width:64px;height:80px;margin-left:10px;"><a href="/lottery/index.htm?lotteryType=6cb"><img src="images//zqlcb.jpg" /></a></li>
					   <li style=" width:100px; height:80px; font-size:13px;font-weight:bold;color:red; text-align:center; line-height:5;"><a href="/lottery/index.htm?lotteryType=6cb">6场半全场</a></li>
					   <li style=" width:150px; height:40px; margin-top:5px; line-height:1.7; margin-left:20px;">不定期开奖 <br />&nbsp;&nbsp;&nbsp;2元赢500万</li>
					   <li style=" width:85px; height:23px; margin-top:4px; padding-left:5px;"><a href="/help/help-3-10.htm"><img src="images//ql-ruan.jpg"></a></li>
					   <li style=" margin-left:10px; margin-top:4px;"><a href="/lottery/index.htm?lotteryType=6cb"><img src="images//ql-li.jpg"></a></li>
					   <li style=" text-align:center; margin-top:5px; width:110px; height:15px; font-size: 12px; color:#000000"><a href="/prizedetail/6cb_.htm">开奖公告</a></li>
					   <li style="margin-top:5px;"><a href="/direction/plt.htm?tt=cc&type=6cb&football=football">走势图</a></li>
					</ul>
			    </div>
		    </div>
	  </div>
	  
   	</div>
	
	
	
<!--Cont end-->
<div class="clear"></div> 
 <div class="outer">
	<%@include file="/foot.jsp"%>   
	</div>  
</div> 
</body>
</html>
