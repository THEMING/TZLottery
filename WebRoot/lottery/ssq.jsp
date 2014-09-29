<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅双色球购买频道为您提供代购投注,缩水过滤软件投注，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="双色球奖池,玩法规则,单式上传,合买,投注,彩票代购">
<title>一彩票_购彩大厅_双色球</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/lottery.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
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
<script type="text/javascript" src="../js/lottery/ssq.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>


<script language="javascript">
	$(document).ready(function()
		{
			g_plan = new Plan();
			initBallList();
			getTermInfo();
			getData();
			onChangePlayType(0);
            login();
		}
	);
	

	//玩法选择
	var last_play_index = 0;
	function onChangePlayType(index)
	{
		switch(index)
		{
			case 0: //标准玩法
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#playType_standard").show();
				$("#playType_standard_list").show();
				$("#betType_zh").show();
				$("#playType_upload").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_FS;
				break;
			case 1: //单式上传
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#playType_standard").hide();
				$("#playType_standard_list").hide();
				$("#betType_zh").hide(); //隐藏追号
				$("#playType_upload").show();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_FILE_UPLOAD;
				break;
			default:
				break;
		}
		last_play_index = index;
	}
	
	//专家推荐
	$(function () {
		var RecomLast=1;
		$(".RecomTitle li").click(function(){			
			var attra = $(this).index() + 1;
			$(this).addClass("RecomCurrent_bg").siblings().removeClass("RecomCurrent_bg");			
			if( RecomLast==attra){
				$("#RR" + RecomLast).css("display","block");
			}
			else{
				$("#RR" + RecomLast).css("display","none");
				$("#RR" + attra).css("display","block");
			}
			RecomLast=attra;
		});
	});
	
	$(function(){
		$(".ball_arrow").click(function(){
			if($(".ball_Remind").css("display")=="block"){
				$(".ball_Remind").slideUp("slow");
				$(".ball_arrow").css("background-position","118px -9px")
				
			}
			if($(".ball_Remind").css("display")=="none"){
				$(".ball_arrow").css("background-position","118px 9px")
			}
		});
	});
	
</script>
</head>

<body>
<%@include  file="/head.jsp"%>


<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
    <!--left start-->
  	<div class="lotter_left" style="margin-top:0px; ">
    	<!--期次信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/cz_18.png" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周二、四、日晚21：30开奖，单注最高奖金&nbsp;&nbsp;<span style=" font-size:16px; font-weight:bold;">1000</span>&nbsp;&nbsp;万元！</li>
                    <li>正在销售：第&nbsp;&nbsp;<span id="currentTerm"></span> &nbsp;&nbsp;期</li>
                    <li>投注截止时间：<span id="currentTermStopTime"></span></li>
                </ul>
            </div>
            <div class="ball_time">
            	<ul>
                	<li><span>距本期销售截止时间还有</span></li>
                    <li class="ball_time_bg"><span id="countTime">00:00:00</span></li>
                    <li class="ball_arrow">玩法规则</li>
                </ul>
            </div>
            <div class="clear"></div>
            <div class="ball_Remind" style="display:block">
				<ul>
					<li><span>玩法说明：</span></li>
					<li>选择6个红球，1个蓝球组成一个单注，最高可中1000万</li>
					<li>可使用软件缩水过滤后单式上传，12个红球原需924注，中6保5缩水后只需38注即可，<a href="/download.htm?fileName=ssqsetup.zip" target="_blank" title="">免费下载</a></li>
				</ul>
			</div>
        </div>
        <!--期次信息 end-->
        <!--双色球软件 start-->
        <div class="shuangseqiuruanjianhoubu">
            <div class="left-suoshuisoft">
              <h1 style="margin:10px 0px 10px 20px;">一彩票双色球缩水过滤软件</h1>
               <ul>
                 <li><img src="../images/soft/suoshuiguolv.jpg"></li>
                 <li style="width:220px;">最全缩水矩阵，最强过滤功能，独家流水线操作设计，2004年曾帮助用户中得百万大奖，现在即可免费下载使用。<a href="/help/help-home-5-3-1.htm" onfocus="this.blur();">[详细功能]</a></li>
               </ul> 
               <div class="clear"></div> 
               <div class="linkofdow"><a href="/download.htm?fileName=ssqsetup.zip" onfocus="this.blur();"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载"/></a></div>
            </div>
            <div class="right-tongjisoft">
                <h1 style="margin:10px 0px 10px 20px;">一彩票双色球统计分析软件</h1>
               <ul>
                 <li><img src="../images/soft/tongjifenxi.jpg"></li>
                 <li style="width:220px;">独家分布图，几十种频率图走势图，可单独查看红球篮球走势或合并查看，数据每日自动更新，现在即可免费下载使用。<a href="/help/help-home-5-3-2.htm" onfocus="this.blur();">[详细功能]</a></li>
               </ul> 
               <div class="clear"></div> 
               <div class="linkofdow"><a href="/download.htm?fileName=ssqsetup.zip" onfocus="this.blur();"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载"/></a></div>
            </div>
        </div>
        <!--双色球软件 end-->
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
            	<li>玩法选择：</li>
            	<li id="play0" class="BMTitleBg_current" onclick="onChangePlayType(0)">标准玩法</li>
            	<li id="play1" onclick="onChangePlayType(1)">方案上传</li>
        	</ul>
        </div>
        <!--玩法选择 end-->
        <div class="Ball_Method" style="display:block;">       	
            <!--标准玩法 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="BM_select">选择投注:</div>
                <div class="BM_redarea">
                	<div class="redarea_title">至少选择6个红色球</div>
                    <div class="redarea_ball">
                    	<ul>
                        	<li id="std_red_ball_1" class="redarea_ball_li" onclick="onClickRedBall(this,1)">01</li>
                            <li id="std_red_ball_2" class="redarea_ball_li" onclick="onClickRedBall(this,2)">02</li>
                            <li id="std_red_ball_3" class="redarea_ball_li" onclick="onClickRedBall(this,3)">03</li>
                            <li id="std_red_ball_4" class="redarea_ball_li" onclick="onClickRedBall(this,4)">04</li>
                            <li id="std_red_ball_5" class="redarea_ball_li" onclick="onClickRedBall(this,5)">05</li>
                            <li id="std_red_ball_6" class="redarea_ball_li" onclick="onClickRedBall(this,6)">06</li>
                            <li id="std_red_ball_7" class="redarea_ball_li" onclick="onClickRedBall(this,7)">07</li>
                            <li id="std_red_ball_8" class="redarea_ball_li" onclick="onClickRedBall(this,8)">08</li>
                            <li id="std_red_ball_9" class="redarea_ball_li" onclick="onClickRedBall(this,9)">09</li>
                            <li id="std_red_ball_10" class="redarea_ball_li" onclick="onClickRedBall(this,10)">10</li>
                            <li id="std_red_ball_11" class="redarea_ball_li" onclick="onClickRedBall(this,11)">11</li>
                            <li id="std_red_ball_12" class="redarea_ball_li" onclick="onClickRedBall(this,12)">12</li>
                            <li id="std_red_ball_13" class="redarea_ball_li" onclick="onClickRedBall(this,13)">13</li>
                            <li id="std_red_ball_14" class="redarea_ball_li" onclick="onClickRedBall(this,14)">14</li>
                            <li id="std_red_ball_15" class="redarea_ball_li" onclick="onClickRedBall(this,15)">15</li>
                            <li id="std_red_ball_16" class="redarea_ball_li" onclick="onClickRedBall(this,16)">16</li>
                            <li id="std_red_ball_17" class="redarea_ball_li" onclick="onClickRedBall(this,17)">17</li>
                            <li id="std_red_ball_18" class="redarea_ball_li" onclick="onClickRedBall(this,18)">18</li>
                            <li id="std_red_ball_19" class="redarea_ball_li" onclick="onClickRedBall(this,19)">19</li>
                            <li id="std_red_ball_20" class="redarea_ball_li" onclick="onClickRedBall(this,20)">20</li>
                            <li id="std_red_ball_21" class="redarea_ball_li" onclick="onClickRedBall(this,21)">21</li>
                            <li id="std_red_ball_22" class="redarea_ball_li" onclick="onClickRedBall(this,22)">22</li>
                            <li id="std_red_ball_23" class="redarea_ball_li" onclick="onClickRedBall(this,23)">23</li>
                            <li id="std_red_ball_24" class="redarea_ball_li" onclick="onClickRedBall(this,24)">24</li>
                            <li id="std_red_ball_25" class="redarea_ball_li" onclick="onClickRedBall(this,25)">25</li>
                            <li id="std_red_ball_26" class="redarea_ball_li" onclick="onClickRedBall(this,26)">26</li>
                            <li id="std_red_ball_27" class="redarea_ball_li" onclick="onClickRedBall(this,27)">27</li>
                            <li id="std_red_ball_28" class="redarea_ball_li" onclick="onClickRedBall(this,28)">28</li>
                            <li id="std_red_ball_29" class="redarea_ball_li" onclick="onClickRedBall(this,29)">29</li>
                            <li id="std_red_ball_30" class="redarea_ball_li" onclick="onClickRedBall(this,30)">30</li>
                            <li id="std_red_ball_31" class="redarea_ball_li" onclick="onClickRedBall(this,31)">31</li>
                            <li id="std_red_ball_32" class="redarea_ball_li" onclick="onClickRedBall(this,32)">32</li>
                            <li id="std_red_ball_33" class="redarea_ball_li" onclick="onClickRedBall(this,33)">33</li>
                        </ul>
                    </div>
                    <div class="redarea_operat">
                          <div class="redarea_listbox">
                          		<select id="randomselect_0" name="select" class="listbox" id="select">
                          			<option> 6</option>
									<option> 7 </option>
									<option> 8 </option>
									<option> 9 </option>
									<option> 10 </option>
									<option> 11 </option>
									<option> 12 </option>
                          		</select>
                          </div>
                          
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_61.gif" onclick="randomSelectBalls(0)"/>
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_63.gif" onclick="clearSelectedBalls(0)"/>
                    </div>
                </div>
                <div class="BM_bluearea">
                	<div class="bluearea_title">至少选择1个蓝色球</div>
                    <div class="bluearea_ball">
                    	<ul>
                        	<li id="std_blue_ball_1" class="bluearea_ball_li" onclick="onClickBlueBall(this,1)">01</li>
                            <li id="std_blue_ball_2" class="bluearea_ball_li" onclick="onClickBlueBall(this,2)">02</li>
                            <li id="std_blue_ball_3" class="bluearea_ball_li" onclick="onClickBlueBall(this,3)">03</li>
                            <li id="std_blue_ball_4" class="bluearea_ball_li" onclick="onClickBlueBall(this,4)">04</li>
                            <li id="std_blue_ball_5" class="bluearea_ball_li" onclick="onClickBlueBall(this,5)">05</li>
                            <li id="std_blue_ball_6" class="bluearea_ball_li" onclick="onClickBlueBall(this,6)">06</li>
                            <li id="std_blue_ball_7" class="bluearea_ball_li" onclick="onClickBlueBall(this,7)">07</li>
                            <li id="std_blue_ball_8" class="bluearea_ball_li" onclick="onClickBlueBall(this,8)">08</li>
                            <li id="std_blue_ball_9" class="bluearea_ball_li" onclick="onClickBlueBall(this,9)">09</li>
                            <li id="std_blue_ball_10" class="bluearea_ball_li" onclick="onClickBlueBall(this,10)">10</li>
                            <li id="std_blue_ball_11" class="bluearea_ball_li" onclick="onClickBlueBall(this,11)">11</li>
                            <li id="std_blue_ball_12" class="bluearea_ball_li" onclick="onClickBlueBall(this,12)">12</li>
                            <li id="std_blue_ball_13" class="bluearea_ball_li" onclick="onClickBlueBall(this,13)">13</li>
                            <li id="std_blue_ball_14" class="bluearea_ball_li" onclick="onClickBlueBall(this,14)">14</li>
                            <li id="std_blue_ball_15" class="bluearea_ball_li" onclick="onClickBlueBall(this,15)">15</li>
                            <li id="std_blue_ball_16" class="bluearea_ball_li" onclick="onClickBlueBall(this,16)">16</li>
                        </ul>
					</div>
                    <div class="bluearea_operat">
                    	 <div class="bluearea_listbox">
                       			<select id="randomselect_1" name="select" class="listbox" id="select">
                          			<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
                          		</select>
                          </div>
                          
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_61.gif" onclick="randomSelectBalls(1)"/>
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_63.gif" onclick="clearSelectedBalls(1)"/>
                    </div>
                </div>
                <div class="ball_total">
                	您共选择了&nbsp;&nbsp;<span id="redBallNum">0</span>&nbsp;&nbsp;个红球号码，
                	&nbsp;&nbsp;<span id="blueBallNum" style="color:blue">0</span>&nbsp;&nbsp;个蓝球号码，
                	共&nbsp;&nbsp;<span id="betCount">0</span>&nbsp;注，&nbsp;
                	共￥<span id="betCountMoney">0</span>元
                </div>      
            </div>
            
            <!--标准玩法 end-->
            <%@include file="/common/lotterylist.jsp" %>
        	<%@include file="/common/upload.jsp" %>
        	<%@include file="/common/buyType.jsp" %>
        </div>
    </div>
    <!--left end-->

    <!--right start-->
  	<div class="lotter_right" style="margin-top:0px">
    	<!--开奖历史　start-->
        <div class="history_top"></div>
        <div class="history_body">
			<div class="history_title">开奖历史</div>
            <div class="history_Last">上期开奖号码</div>
			<div id="history_last" class="history_LastNum"></div>
          	<div id="history_pre_list" class="history_preNum"></div>
           	<div class="history_more"><a href="/direction/fbt.htm?type=ssq&tt=js">更多在线图表</a></div>
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">缩水过滤</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>双色球缩水过滤专家</h3>
                    中6保5，中6保4，中5保5，中5保4，中4保4等多种缩水算法， 节省投注资金几百几十倍。
多种投注过滤如连号过滤、区间过滤、自由过滤、AC过滤、点数过滤、尾数过滤、界限过滤、红蓝搭配过滤等。并可进行灵活投注组合及智能机选功能。

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=ssqsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载"/></a></div>
            </div>
        </div>
        <!--统计分析 end-->
        <!--缩水过滤 start-->
        <div class="Stat">
        	<div class="STitleBg">
           	  <div class="STitle">统计分析</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_17.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>双色球统计分析专家</h3>
                  每期数据自动更新， 几十种数据图表灵活呈现，号码列表，号码分布图，号码频率，连号频率 统计，区间频率，尾数频率，最大间隔，延续频率
连号走势，区间走势，奇数走势，点数走势 AC走势，同尾走势，间隔走势，延续走势，最大数走势，最小数走势

 

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=ssqsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
            </div>
        </div>
        <!--缩水过滤 end-->
        <!--投注页专家推荐 start-->
         <%@include file="../lottery/zjtj.jsp"%>
        <!--投注页专家推荐 end-->
        <!--/*24小时客服*/ start-->
        <div class="hours_24">
        	<a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank">
        	<img src="../images/img/right_28.jpg" width="252" height="75" /></a>
        </div>
        <!--/*24小时客服*/　end-->
  	</div>
    <!--right end-->
</div>
<!--Web Body end-->
<div class="clear"></div>
<%@include file="../foot.jsp"%>
</body>
</html>

