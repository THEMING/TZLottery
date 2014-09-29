<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="一彩票购彩大厅七乐彩购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="七乐彩奖池,复式合买,胆拖过滤,缩水过滤,单式上传">
<title>一彩票_购彩大厅_七乐彩</title>
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
<script type="text/javascript" src="../js/lottery/qlc.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
	
<script language="javascript">

	$(document).ready(function()
		{
		 login();
			g_plan = new Plan();
			initBallList();
			getTermInfo();
			getData();
			onChangePlayType(0);
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
	
	//专家推荐 start
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
	
	//玩法规则
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
<%@include  file="../head.jsp"%>
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
    <!--left start-->
  	<div class="lotter_left" style="margin-top:0px;">
    	<!--期次信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/qlc_logo.jpg" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周一、三、五21:30开奖，百万奖、期期开</li>
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
					
					<li>&nbsp;&nbsp;七乐彩采用组合式玩法，从01—30共30个号码中选择7个号码组合为一注投注号码。每注金额人民币2元，每周销售三期。单注奖金的最高限额为500万元。<a target="_blank" href="/help/help-4-2.htm">[详细规则]</a></li>
					<li>&nbsp;&nbsp;使用七乐彩缩水过滤软件可中7保6，中7保5，中6保6等多种缩水，及多种胆拖过滤模式，<a href="/download.htm?fileName=lottosetup.zip">[免费下载]</a>。</li>
					
				</ul>
			</div>
        </div>
        <!--期次信息 end-->
        
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
            	<li>玩法选择：</li>
            	<li id="play0" class="BMTitleBg_current" onclick="onChangePlayType(0)">标准玩法</li>
            	<li id="play1" onclick="onChangePlayType(1)">单式上传</li>
        	</ul>
        </div>
        <!--玩法选择 end-->
        
        <!--玩法、投注和投注操作 start-->
        <div class="Ball_Method" style="display:block;">
            <!--标准玩法 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="BM_select">选择投注:</div>                
                <div class="qlc_bg">
                	<ul>
                    	<li id="std_ball_1" class="ball_up_false" onclick="onClickBall(this,1)">1</li>
                    	<li id="std_ball_2" class="ball_up_false" onclick="onClickBall(this,2)">2</li>
                    	<li id="std_ball_3" class="ball_up_false" onclick="onClickBall(this,3)">3</li>
                    	<li id="std_ball_4" class="ball_up_false" onclick="onClickBall(this,4)">4</li>
                    	<li id="std_ball_5" class="ball_up_false" onclick="onClickBall(this,5)">5</li>
                    	<li id="std_ball_6" class="ball_up_false" onclick="onClickBall(this,6)">6</li>
                    	<li id="std_ball_7" class="ball_up_false" onclick="onClickBall(this,7)">7</li>
                    	<li id="std_ball_8" class="ball_up_false" onclick="onClickBall(this,8)">8</li>
                    	<li id="std_ball_9" class="ball_up_false" onclick="onClickBall(this,9)">9</li>
                    	<li id="std_ball_10" class="ball_up_false" onclick="onClickBall(this,10)">10</li>
                    	<li id="std_ball_11" class="ball_up_false" onclick="onClickBall(this,11)">11</li>
                    	<li id="std_ball_12" class="ball_up_false" onclick="onClickBall(this,12)">12</li>
                    	<li id="std_ball_13" class="ball_up_false" onclick="onClickBall(this,13)">13</li>
                    	<li id="std_ball_14" class="ball_up_false" onclick="onClickBall(this,14)">14</li>
                    	<li id="std_ball_15" class="ball_up_false" onclick="onClickBall(this,15)">15</li>
                    	<li id="std_ball_16" class="ball_up_false" onclick="onClickBall(this,16)">16</li>
                    	<li id="std_ball_17" class="ball_up_false" onclick="onClickBall(this,17)">17</li>
                    	<li id="std_ball_18" class="ball_up_false" onclick="onClickBall(this,18)">18</li>
                    	<li id="std_ball_19" class="ball_up_false" onclick="onClickBall(this,19)">19</li>
                    	<li id="std_ball_20" class="ball_up_false" onclick="onClickBall(this,20)">20</li>
                    	<li id="std_ball_21" class="ball_up_false" onclick="onClickBall(this,21)">21</li>
                    	<li id="std_ball_22" class="ball_up_false" onclick="onClickBall(this,22)">22</li>
                    	<li id="std_ball_23" class="ball_up_false" onclick="onClickBall(this,23)">23</li>
                    	<li id="std_ball_24" class="ball_up_false" onclick="onClickBall(this,24)">24</li>
                    	<li id="std_ball_25" class="ball_up_false" onclick="onClickBall(this,25)">25</li>
                    	<li id="std_ball_26" class="ball_up_false" onclick="onClickBall(this,26)">26</li>
                    	<li id="std_ball_27" class="ball_up_false" onclick="onClickBall(this,27)">27</li>
                    	<li id="std_ball_28" class="ball_up_false" onclick="onClickBall(this,28)">28</li>
                    	<li id="std_ball_29" class="ball_up_false" onclick="onClickBall(this,29)">29</li>
                    	<li id="std_ball_30" class="ball_up_false" onclick="onClickBall(this,30)">30</li>
                    </ul>
                    
                </div>
                <div class="qlc_operat">
					<div class="redarea_listbox">
                   		<select id="randomselect_0" name="select" class="listbox">
                   			<option> 7 </option>
							<option> 8 </option>
							<option> 9 </option>
							<option> 10 </option>
							<option> 11 </option>
							<option> 12 </option>
							<option> 13 </option>
							<option> 14 </option>
							<option> 15 </option>
                   		</select>
					</div>
                    <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_61.gif" onclick="randomSelectBalls(0)"/>
                    <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_63.gif" onclick="clearSelectedBalls(0)"/>
                </div>
                <div class="ball_total">
                	您共选择了&nbsp;&nbsp;<span id="redBallNum">0</span>&nbsp;&nbsp;个号码，
                	共&nbsp;&nbsp;<span id="betCount">0</span>注&nbsp;&nbsp;，&nbsp;&nbsp;
                	共￥<span id="betCountMoney">0</span>元&nbsp;&nbsp;
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
			<div id="history_last" class="history_ball_bg2"></div>
          	<div id="history_pre_list" class="history_preNum"></div>
           	<div class="history_more"><a href="/direction/fbt.htm?type=qlc&tt=js"></a></div>
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">缩水过滤软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="七乐彩缩水过滤专家家" class="Stat_softWare_pic" />
                    <h3>七乐彩缩水过滤专家</h3>
                    乐透缩水过滤专家适应于所有选7选6选5型彩种，也同样适应于七乐彩彩种，多种缩水模板帮您节省资金扩大覆盖面，并有多种胆拖及过滤玩法。生成方案可上传到本站。

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=lottosetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载7乐彩缩水过滤专家"/></a></div>
            </div>
        </div>
        <!--统计分析 end-->
        <!--缩水过滤 start-->
        <div class="Stat" style="display:none">
        	<div class="STitleBg">
           	  <div class="STitle">缩水过滤</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_17.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>369cai缩水过滤专家</h3>
                    首创彩票缩水概念，
                     2001年11月即推出了全
                     国第一套足彩投注缩水软件——“足球玩玩”软件，在全国各地掀起缩水投注的热潮，直接催生了数千中奖彩民 ...

                </div>
                <div class="Stat_btn"><a href="#"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
    <!-- foot -->
	<div class="clear"></div> 
	<%@include file="../foot.jsp"%>
</div>
<!--Web Body end-->
</body>
</html>

