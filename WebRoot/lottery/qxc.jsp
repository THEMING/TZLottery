<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅足彩七星彩购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="七星彩,七星彩奖池,七星彩合买,单式上传">
<title>一彩票_购彩大厅_七星彩</title>
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
<script type="text/javascript" src="../js/lottery/qxc.js"></script>
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
    	<!--球的种类信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/qxc_logo.jpg" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周二、五、日晚20:30开奖，单注最高奖金&nbsp;&nbsp;<span style=" font-size:16px; font-weight:bold;">500</span>&nbsp;&nbsp;万元！</li>
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
					
					<li>&nbsp;&nbsp;7星彩是指从0000000-9999999中选择任意7位自然数进行的投注，一组7位数的排列称为一注，每注金额人民币2元。购买者可进行多倍投注，投注倍数范围为2-99倍。按期销售，7星彩每周二、五、日开奖,单注彩票中奖奖金最高限额500万元。<a target="_blank" href="/help/help-4-6.htm">[详细规则]</a></li>
					
				</ul>
			</div>
        </div>
        <!--球的种类信息 end-->
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
            	<li>玩法选择：</li>
            	<li id="play0" class="BMTitleBg_current" onclick="onChangePlayType(0)">标准玩法</li>
            	<li id="play1" onclick="onChangePlayType(1)">单式上传</li>
        	</ul>
        </div>
        
        <!--直选 start-->
        <div class="Ball_Method" style="display:block;" id="GG2">       	
            <!--玩法选择 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="qixingcai_select">选择投注:</div>                
           	  <div class="pailie_title">每位至少选择&nbsp;1&nbsp;个数字</div>
                    <!--位数选择 start-->
                    <div class="pailie_digit_select">
                    	<!--百万位数 start-->
                    	<div class="pailie_digit">百万位数：</div>
                    	<div class="pailie_select" id="baiwan">
                    		<ul>
                        		<li id="baiwan_ball_0" class="ball_up_false" onclick="onClickBall(this,0,11)">0</li>
                        		<li id="baiwan_ball_1" class="ball_up_false" onclick="onClickBall(this,1,11)">1</li>
                        		<li id="baiwan_ball_2" class="ball_up_false" onclick="onClickBall(this,2,11)">2</li>
                        		<li id="baiwan_ball_3" class="ball_up_false" onclick="onClickBall(this,3,11)">3</li>
                        		<li id="baiwan_ball_4" class="ball_up_false" onclick="onClickBall(this,4,11)">4</li>
                        		<li id="baiwan_ball_5" class="ball_up_false" onclick="onClickBall(this,5,11)">5</li>
                        		<li id="baiwan_ball_6" class="ball_up_false" onclick="onClickBall(this,6,11)">6</li>
                        		<li id="baiwan_ball_7" class="ball_up_false" onclick="onClickBall(this,7,11)">7</li>
                        		<li id="baiwan_ball_8" class="ball_up_false" onclick="onClickBall(this,8,11)">8</li>
                            	<li id="baiwan_ball_9" class="ball_up_false" onclick="onClickBall(this,9,11)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(11)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(11)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(11)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(11)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--百万位数 end-->
                    	<div class="clear"></div>
                        <!--十万位数 start-->
                    	<div class="pailie_digit">十万位数：</div>
                    	<div class="pailie_select" id="shiwan">
                    		<ul>
                        		<li id="shiwan_ball_0" class="ball_up_false" onclick="onClickBall(this,0,12)">0</li>
                        		<li id="shiwan_ball_1" class="ball_up_false" onclick="onClickBall(this,1,12)">1</li>
                        		<li id="shiwan_ball_2" class="ball_up_false" onclick="onClickBall(this,2,12)">2</li>
                        		<li id="shiwan_ball_3" class="ball_up_false" onclick="onClickBall(this,3,12)">3</li>
                        		<li id="shiwan_ball_4" class="ball_up_false" onclick="onClickBall(this,4,12)">4</li>
                        		<li id="shiwan_ball_5" class="ball_up_false" onclick="onClickBall(this,5,12)">5</li>
                        		<li id="shiwan_ball_6" class="ball_up_false" onclick="onClickBall(this,6,12)">6</li>
                        		<li id="shiwan_ball_7" class="ball_up_false" onclick="onClickBall(this,7,12)">7</li>
                        		<li id="shiwan_ball_8" class="ball_up_false" onclick="onClickBall(this,8,12)">8</li>
                            	<li id="shiwan_ball_9" class="ball_up_false" onclick="onClickBall(this,9,12)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(12)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(12)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(12)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(12)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--十万位数 end-->
                    	<div class="clear"></div>
                        <!--万位数 start-->
                    	<div class="pailie_digit">万位数：</div>
                    	<div class="pailie_select" id="wan">
                    		<ul>
                        		<li id="wan_ball_0" class="ball_up_false" onclick="onClickBall(this,0,13)">0</li>
                        		<li id="wan_ball_1" class="ball_up_false" onclick="onClickBall(this,1,13)">1</li>
                        		<li id="wan_ball_2" class="ball_up_false" onclick="onClickBall(this,2,13)">2</li>
                        		<li id="wan_ball_3" class="ball_up_false" onclick="onClickBall(this,3,13)">3</li>
                        		<li id="wan_ball_4" class="ball_up_false" onclick="onClickBall(this,4,13)">4</li>
                        		<li id="wan_ball_5" class="ball_up_false" onclick="onClickBall(this,5,13)">5</li>
                        		<li id="wan_ball_6" class="ball_up_false" onclick="onClickBall(this,6,13)">6</li>
                        		<li id="wan_ball_7" class="ball_up_false" onclick="onClickBall(this,7,13)">7</li>
                        		<li id="wan_ball_8" class="ball_up_false" onclick="onClickBall(this,8,13)">8</li>
                            	<li id="wan_ball_9" class="ball_up_false" onclick="onClickBall(this,9,13)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(13)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(13)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(13)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(13)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--万位数 end-->
                    	<div class="clear"></div>
                        <!--千位数 start-->
                    	<div class="pailie_digit">千位数：</div>
                    	<div class="pailie_select" id="qian">
                    		<ul>
                        		<li id="qian_ball_0" class="ball_up_false" onclick="onClickBall(this,0,14)">0</li>
                        		<li id="qian_ball_1" class="ball_up_false" onclick="onClickBall(this,1,14)">1</li>
                        		<li id="qian_ball_2" class="ball_up_false" onclick="onClickBall(this,2,14)">2</li>
                        		<li id="qian_ball_3" class="ball_up_false" onclick="onClickBall(this,3,14)">3</li>
                        		<li id="qian_ball_4" class="ball_up_false" onclick="onClickBall(this,4,14)">4</li>
                        		<li id="qian_ball_5" class="ball_up_false" onclick="onClickBall(this,5,14)">5</li>
                        		<li id="qian_ball_6" class="ball_up_false" onclick="onClickBall(this,6,14)">6</li>
                        		<li id="qian_ball_7" class="ball_up_false" onclick="onClickBall(this,7,14)">7</li>
                        		<li id="qian_ball_8" class="ball_up_false" onclick="onClickBall(this,8,14)">8</li>
                            	<li id="qian_ball_9" class="ball_up_false" onclick="onClickBall(this,9,14)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(14)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(14)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(14)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(14)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--千位数 end-->
                    	<div class="clear"></div>
                      <!--百位数 start-->
                   	  <div class="pailie_digit">百位数：</div>
                    	<div class="pailie_select" id="bai">
                    		<ul>
                        		<li id="bai_ball_0" class="ball_up_false" onclick="onClickBall(this,0,15)">0</li>
                        		<li id="bai_ball_1" class="ball_up_false" onclick="onClickBall(this,1,15)">1</li>
                        		<li id="bai_ball_2" class="ball_up_false" onclick="onClickBall(this,2,15)">2</li>
                        		<li id="bai_ball_3" class="ball_up_false" onclick="onClickBall(this,3,15)">3</li>
                        		<li id="bai_ball_4" class="ball_up_false" onclick="onClickBall(this,4,15)">4</li>
                        		<li id="bai_ball_5" class="ball_up_false" onclick="onClickBall(this,5,15)">5</li>
                        		<li id="bai_ball_6" class="ball_up_false" onclick="onClickBall(this,6,15)">6</li>
                        		<li id="bai_ball_7" class="ball_up_false" onclick="onClickBall(this,7,15)">7</li>
                        		<li id="bai_ball_8" class="ball_up_false" onclick="onClickBall(this,8,15)">8</li>
                            	<li id="bai_ball_9" class="ball_up_false" onclick="onClickBall(this,9,15)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(15)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(15)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(15)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(15)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--百位数　end-->
                    	<div class="clear"></div>
                   	  <!--十位数 start-->
                   	  <div class="pailie_digit">十位数：</div>
                    	<div class="pailie_select" id="shi">
                    		<ul>
                        		<li id="shi_ball_0" class="ball_up_false" onclick="onClickBall(this,0,16)">0</li>
                        		<li id="shi_ball_1" class="ball_up_false" onclick="onClickBall(this,1,16)">1</li>
                        		<li id="shi_ball_2" class="ball_up_false" onclick="onClickBall(this,2,16)">2</li>
                        		<li id="shi_ball_3" class="ball_up_false" onclick="onClickBall(this,3,16)">3</li>
                        		<li id="shi_ball_4" class="ball_up_false" onclick="onClickBall(this,4,16)">4</li>
                        		<li id="shi_ball_5" class="ball_up_false" onclick="onClickBall(this,5,16)">5</li>
                        		<li id="shi_ball_6" class="ball_up_false" onclick="onClickBall(this,6,16)">6</li>
                        		<li id="shi_ball_7" class="ball_up_false" onclick="onClickBall(this,7,16)">7</li>
                        		<li id="shi_ball_8" class="ball_up_false" onclick="onClickBall(this,8,16)">8</li>
                            	<li id="shi_ball_9" class="ball_up_false" onclick="onClickBall(this,9,16)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(16)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(16)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(16)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(16)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--十位数 end-->
                    	<div class="clear"></div>
                   	  <!--个位数 start-->
                   	  <div class="pailie_digit">个位数：</div>
                    	<div class="pailie_select" id="ge">
                    		<ul>
                        		<li id="ge_ball_0" class="ball_up_false" onclick="onClickBall(this,0,17)">0</li>
                        		<li id="ge_ball_1" class="ball_up_false" onclick="onClickBall(this,1,17)">1</li>
                        		<li id="ge_ball_2" class="ball_up_false" onclick="onClickBall(this,2,17)">2</li>
                        		<li id="ge_ball_3" class="ball_up_false" onclick="onClickBall(this,3,17)">3</li>
                        		<li id="ge_ball_4" class="ball_up_false" onclick="onClickBall(this,4,17)">4</li>
                        		<li id="ge_ball_5" class="ball_up_false" onclick="onClickBall(this,5,17)">5</li>
                        		<li id="ge_ball_6" class="ball_up_false" onclick="onClickBall(this,6,17)">6</li>
                        		<li id="ge_ball_7" class="ball_up_false" onclick="onClickBall(this,7,17)">7</li>
                        		<li id="ge_ball_8" class="ball_up_false" onclick="onClickBall(this,8,17)">8</li>
                            	<li id="ge_ball_9" class="ball_up_false" onclick="onClickBall(this,9,17)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all">
                    		<div class="btn_1" onclick= "selectall(17)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd">
                    		<div class="btn_1" onclick= "selectodd(17)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even">
                    		<div class="btn_1" onclick="selecteven(17)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty">
                    		<div class="btn_1" onclick="selectclear(17)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--个位数 end-->
                    </div>
                    <!--位数选择 end-->
                <div class="ball_total">
                  	您共选择了&nbsp;&nbsp;<span id="baiwanCount">0</span>&nbsp;&nbsp;个百万位号码，
                  	&nbsp;&nbsp;<span id="shiwanCount">0</span>&nbsp;&nbsp;个十万位号码，
                  	&nbsp;&nbsp;<span id="wanCount" >0</span>&nbsp;&nbsp;个万位号码，
                  	&nbsp;&nbsp;<span id="qianCount" >0</span>&nbsp;&nbsp;个千位号码，<br />
                  	&nbsp;&nbsp;<span id="baiCount" >0</span>&nbsp;&nbsp;个百位号码，
                  	&nbsp;&nbsp;<span id="shiCount" >0</span>&nbsp;&nbsp;个十位号码，
                  	&nbsp;&nbsp;<span id="geCount" >0</span>&nbsp;&nbsp;个个位号码，
                  	&nbsp;&nbsp;<span id="betCount" >0</span>&nbsp;&nbsp;注，
                  	&nbsp;&nbsp;共￥<span id="betCountMoney" >0</span>&nbsp;&nbsp;元
                </div>  
            </div>
            <!--玩法选择 end-->
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
           	
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat" style="display:none">
        	<div class="STitleBg">
            	<div class="STitle">统计分析</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>369cai统计分析专家</h3>
                    首创彩票缩水概念，
                     2001年11月即推出了全
                     国第一套足彩投注缩水软件——“足球玩玩”软件，在全国各地掀起缩水投注的热潮，直接催生了数千中奖彩民 ...

                </div>
                <div class="Stat_btn"><a href="#"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
</div>
</body>
</html>

