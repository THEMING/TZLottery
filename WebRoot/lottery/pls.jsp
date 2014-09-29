<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅足彩排列3购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="排列3,排列3奖池,组三,组六,排列3合买">
<title>一彩票_购彩大厅_排列3</title>
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
<script type="text/javascript" src="../js/lottery/pls.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>

<script type="text/javascript">
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
				$("#playType_z3").hide();
				$("#playType_z6").hide();
				$("#playType_upload").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_STD;
				break;
			case 1: //组三
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#z3_li_1").click();
				$("#playType_z3").show();
				$("#playType_standard_list").show();
				$("#betType_zh").show();
				$("#playType_standard").hide();
				$("#playType_z6").hide();
				$("#playType_upload").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_Z3;
				break;
			case 2: //组六
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#z6_li_1").click();
				$("#playType_z6").show();
				$("#playType_standard_list").show();
				$("#betType_zh").show();
				$("#playType_standard").hide();
				$("#playType_z3").hide();
				$("#playType_upload").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_Z6;
				break;
			case 3: //单式上传
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#playType_standard").hide();
				$("#playType_z3").hide();
				$("#playType_z6").hide();
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
	
	//组三tab
	$(function(){
			var theLast = 1;
			$("#zu3_li li").click(function() {			
				var attra = $(this).index() + 1;
				$(this).addClass("group_current").siblings().removeClass("group_current");
				if(theLast == attra){
					$("#z3_group_" + theLast).css("display","block");
				}
				else{
					$("#z3_group_" + theLast).css("display","none");
					$("#z3_group_" + attra).css("display","block");
				}
				theLast = attra;
				g_subPlayType = attra;
			});
	});
	
	//组六tab
	$(function(){
			var zu6_theLast = 1;
			$("#zu6_li li").click(function() {			
				var zu6_attra = $(this).index() + 1;
				$(this).addClass("group_current").siblings().removeClass("group_current");
				if( zu6_theLast==zu6_attra){
					$("#z6_group_" + zu6_theLast).css("display","block");
				}
				else{
					$("#z6_group_" + zu6_theLast).css("display","none");
					$("#z6_group_" + zu6_attra).css("display","block");
				}
				zu6_theLast = zu6_attra;
				g_subPlayType = zu6_attra;
			});
	});
	
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
<!--组六内包号、和值tab end-->

<body>
<%@include  file="../head.jsp"%>
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
  	<!--left start-->
  	<div class="lotter_left" style="margin-top:0px; ">
    	<!--期次信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/pls_logo.jpg" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每日晚20：40开奖，单注最高奖金&nbsp;&nbsp;<span style=" font-size:16px; font-weight:bold;">1000</span>&nbsp;&nbsp;万元！</li>
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
					
					<li>&nbsp;&nbsp;排列3"由购买者从000-999的数字中选取1个3位数为投注号码进行投注，可直选投注和组选投注，每注2元人民币，单注最高奖金1000元。<a target="_blank" href="/help/help-4-5.htm">[详细规则]</a></li>
					<li>&nbsp;&nbsp;使用排列3快选优化专家，多种快选，及过滤模式，轻松过滤垃圾注，<a href="/download.htm?fileName=3dsetup.zip">[免费下载]</a></li>
				</ul>
			</div>
        </div>
        <!--期次信息 end-->
    	
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
        		<li>玩法选择：</li>
                <li id="play0" class="BMTitleBg_current" onclick="onChangePlayType(0)">普通玩法</li>
                <li id="play1" onclick="onChangePlayType(1)">组三</li>
            	<li id="play2" onclick="onChangePlayType(2)">组六</li>
            	<li id="play3" onclick="onChangePlayType(3)">单式上传</li>
        	</ul>
        </div>
        <!--玩法选择 end-->
        
        <div class="Ball_Method" style="display:block;" id="GG2">       	
            <!--普通玩法 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="BM_select">选择投注:</div>                
           	  	<div class="pailie_title">每位至少选择&nbsp;1&nbsp;个数字</div>
                    <!--位数选择 start-->
                    <div class="pailie_digit_select">
                    <!--百位数 start-->
                    <div class="pailie_digit">百位数：</div>
                    <div class="pailie_select" id="bai">
                    	<ul>
                        	<li id="bai_std_ball_0" class="ball_up_false" onclick="onClickBall(this,0,1)">0</li>
                            <li id="bai_std_ball_1" class="ball_up_false" onclick="onClickBall(this,1,1)">1</li>
                            <li id="bai_std_ball_2" class="ball_up_false" onclick="onClickBall(this,2,1)">2</li>
                            <li id="bai_std_ball_3" class="ball_up_false" onclick="onClickBall(this,3,1)">3</li>
                            <li id="bai_std_ball_4" class="ball_up_false" onclick="onClickBall(this,4,1)">4</li>
                            <li id="bai_std_ball_5" class="ball_up_false" onclick="onClickBall(this,5,1)">5</li>
                            <li id="bai_std_ball_6" class="ball_up_false" onclick="onClickBall(this,6,1)">6</li>
                            <li id="bai_std_ball_7" class="ball_up_false" onclick="onClickBall(this,7,1)">7</li>
                            <li id="bai_std_ball_8" class="ball_up_false" onclick="onClickBall(this,8,1)">8</li>
                            <li id="bai_std_ball_9" class="ball_up_false" onclick="onClickBall(this,9,1)">9</li>
                        </ul>
                    </div>
					<div class="pailie_all">
                    	<div class="btn_1" onclick= "selectall(1)">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_odd">
                    	<div class="btn_1" onclick= "selectodd(1)">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_even">
                    	<div class="btn_1" onclick="selecteven(1)">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_empty">
                    	<div class="btn_1" onclick="selectclear(1)">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                    <!--百位数　end-->
                    <div class="clear"></div>
                    <!--十位数 start-->
                    <div class="pailie_digit">十位数：</div>
                    <div class="pailie_select" id="shi">
                    	<ul>
                        	<li id="shi_std_ball_0" class="ball_up_false" onclick="onClickBall(this,0,2)">0</li>
                            <li id="shi_std_ball_1" class="ball_up_false" onclick="onClickBall(this,1,2)">1</li>
                            <li id="shi_std_ball_2" class="ball_up_false" onclick="onClickBall(this,2,2)">2</li>
                            <li id="shi_std_ball_3" class="ball_up_false" onclick="onClickBall(this,3,2)">3</li>
                            <li id="shi_std_ball_4" class="ball_up_false" onclick="onClickBall(this,4,2)">4</li>
                            <li id="shi_std_ball_5" class="ball_up_false" onclick="onClickBall(this,5,2)">5</li>
                            <li id="shi_std_ball_6" class="ball_up_false" onclick="onClickBall(this,6,2)">6</li>
                            <li id="shi_std_ball_7" class="ball_up_false" onclick="onClickBall(this,7,2)">7</li>
                            <li id="shi_std_ball_8" class="ball_up_false" onclick="onClickBall(this,8,2)">8</li>
                            <li id="shi_std_ball_9" class="ball_up_false" onclick="onClickBall(this,9,2)">9</li>
                        </ul>
                    </div>
					<div class="pailie_all">
                    	<div class="btn_1" onclick= "selectall(2)">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_odd">
                    	<div class="btn_1" onclick= "selectodd(2)">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_even">
                    	<div class="btn_1" onclick="selecteven(2)">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_empty">
                    	<div class="btn_1" onclick="selectclear(2)">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                    <!--十位数 end-->
                    <div class="clear"></div>
                    <!--个位数 start-->
                    <div class="pailie_digit">个位数：</div>
                    <div class="pailie_select">
                    	<ul>
                        	<li id="ge_std_ball_0" class="ball_up_false" onclick="onClickBall(this,0,3)">0</li>
                            <li id="ge_std_ball_1" class="ball_up_false" onclick="onClickBall(this,1,3)">1</li>
                            <li id="ge_std_ball_2" class="ball_up_false" onclick="onClickBall(this,2,3)">2</li>
                            <li id="ge_std_ball_3" class="ball_up_false" onclick="onClickBall(this,3,3)">3</li>
                            <li id="ge_std_ball_4" class="ball_up_false" onclick="onClickBall(this,4,3)">4</li>
                            <li id="ge_std_ball_5" class="ball_up_false" onclick="onClickBall(this,5,3)">5</li>
                            <li id="ge_std_ball_6" class="ball_up_false" onclick="onClickBall(this,6,3)">6</li>
                            <li id="ge_std_ball_7" class="ball_up_false" onclick="onClickBall(this,7,3)">7</li>
                            <li id="ge_std_ball_8" class="ball_up_false" onclick="onClickBall(this,8,3)">8</li>
                            <li id="ge_std_ball_9" class="ball_up_false" onclick="onClickBall(this,9,3)">9</li>
                        </ul>
                    </div>
					<div class="pailie_all">
                    	<div class="btn_1" onclick= "selectall(3)">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_odd">
                    	<div class="btn_1" onclick= "selectodd(3)">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_even">
                    	<div class="btn_1" onclick="selecteven(3)">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="pailie_empty">
                    	<div class="btn_1" onclick="selectclear(3)">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                    <!--个位数 end-->
                    </div>
                    <!--位数选择 end-->
                <div class="ball_total" style="margin-top:15px">
                	您共选择了<strong id="baiCount" class="red">0</strong>个百位数字， 
					<strong id="shiCount" class="blue">0</strong> 个十位数字，
					<strong id="geCount" class="green">0</strong> 个个位数字，共
					<strong id="betCount" class="red">0</strong> 注，￥
					<strong id="betCountMoney" class="red">0 </strong>元     
                </div>
            </div>
            <!--普通玩法 end-->
                   	
            <!--组三 start-->
            <div id="playType_z3" class="BM_body">            	
            	<div class="group_bet">选择投注:</div>
                <div class="group" id="zu3_li">
                    <ul>
                        <li id="z3_li_1" class="group_li group_current" onclick="changeHZandBH(1)">包号选号</li>
                        <li class="group_li" onclick="changeHZandBH(2)">和值选号</li>
                     </ul>
                </div>
                <!--包号 start-->
                <div class="group_bg" style="display:block;" id="z3_group_1">                
                	<div class="bao_title">至少选择2个号码，奖金160元</div>
                    <div class="bao_select" id="zu3_bao">
                    	<ul>
                            <li id="zu3_ball_0" class="ball_up_false" onclick="onClickZu3Ball(this,0)">0</li>
                            <li id="zu3_ball_1" class="ball_up_false" onclick="onClickZu3Ball(this,1)">1</li>
                            <li id="zu3_ball_2" class="ball_up_false" onclick="onClickZu3Ball(this,2)">2</li>
                            <li id="zu3_ball_3" class="ball_up_false" onclick="onClickZu3Ball(this,3)">3</li>
                            <li id="zu3_ball_4" class="ball_up_false" onclick="onClickZu3Ball(this,4)">4</li>
                            <li id="zu3_ball_5" class="ball_up_false" onclick="onClickZu3Ball(this,5)">5</li>
                            <li id="zu3_ball_6" class="ball_up_false" onclick="onClickZu3Ball(this,6)">6</li>
                            <li id="zu3_ball_7" class="ball_up_false" onclick="onClickZu3Ball(this,7)">7</li>
                            <li id="zu3_ball_8" class="ball_up_false" onclick="onClickZu3Ball(this,8)">8</li>
                            <li id="zu3_ball_9" class="ball_up_false" onclick="onClickZu3Ball(this,9)">9</li>
                        </ul>
                    </div>
					<div class="bigHappy_all">
                    	<div class="btn_1" onclick= "zu3_selectall()">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_odd">
                    	<div class="btn_1" onclick= "zu3_selectodd()">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_even">
                    	<div class="btn_1" onclick="zu3_selecteven()">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_empty">
                    	<div class="btn_1" onclick="zu3_selectclear()">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                
                	<div class="ball_total">
                		您选择了共<strong id="zu3_betCount" class="red">0</strong> 注，￥
						<strong id="zu3_betCountMoney" class="red">0 </strong>元     
					</div>
                </div>   
                <!--包号 end-->
                
                <!--和值 start-->
                <div class="group_bg" id="z3_group_2">                
                	<div class="bigHappy_title">玩法提示：开奖号码中3位数各不相同，所选号码与开奖号码的和值相同（顺序不限）。  </div>
                    <div class="group_num" id="zu3_he">
                    	<ul>
                        	<li id="zu3hz_ball_0" class="ball_up_false" onclick="onClickZu3hzBall(this,0)">0</li>
                            <li id="zu3hz_ball_1" class="ball_up_false" onclick="onClickZu3hzBall(this,1)">1</li>
                            <li id="zu3hz_ball_2" class="ball_up_false" onclick="onClickZu3hzBall(this,2)">2</li>
                            <li id="zu3hz_ball_3" class="ball_up_false" onclick="onClickZu3hzBall(this,3)">3</li>
                            <li id="zu3hz_ball_4" class="ball_up_false" onclick="onClickZu3hzBall(this,4)">4</li>
                            <li id="zu3hz_ball_5" class="ball_up_false" onclick="onClickZu3hzBall(this,5)">5</li>
                            <li id="zu3hz_ball_6" class="ball_up_false" onclick="onClickZu3hzBall(this,6)">6</li>
                            <li id="zu3hz_ball_7" class="ball_up_false" onclick="onClickZu3hzBall(this,7)">7</li>
                            <li id="zu3hz_ball_8" class="ball_up_false" onclick="onClickZu3hzBall(this,8)">8</li>
                            <li id="zu3hz_ball_9" class="ball_up_false" onclick="onClickZu3hzBall(this,9)">9</li>
                            <li id="zu3hz_ball_10" class="ball_up_false" onclick="onClickZu3hzBall(this,10)">10</li>
                            <li id="zu3hz_ball_11" class="ball_up_false" onclick="onClickZu3hzBall(this,11)">11</li>
                            <li id="zu3hz_ball_12" class="ball_up_false" onclick="onClickZu3hzBall(this,12)">12</li>
                            <li id="zu3hz_ball_13" class="ball_up_false" onclick="onClickZu3hzBall(this,13)">13</li>
                            <li id="zu3hz_ball_14" class="ball_up_false" onclick="onClickZu3hzBall(this,14)">14</li>
                            <li id="zu3hz_ball_15" class="ball_up_false" onclick="onClickZu3hzBall(this,15)">15</li>
                            <li id="zu3hz_ball_16" class="ball_up_false" onclick="onClickZu3hzBall(this,16)">16</li>
                            <li id="zu3hz_ball_17" class="ball_up_false" onclick="onClickZu3hzBall(this,17)">17</li>
                            <li id="zu3hz_ball_18" class="ball_up_false" onclick="onClickZu3hzBall(this,18)">18</li>
                            <li id="zu3hz_ball_19" class="ball_up_false" onclick="onClickZu3hzBall(this,19)">19</li>
                            <li id="zu3hz_ball_20" class="ball_up_false" onclick="onClickZu3hzBall(this,20)">20</li>
                            <li id="zu3hz_ball_21" class="ball_up_false" onclick="onClickZu3hzBall(this,21)">21</li>
                            <li id="zu3hz_ball_22" class="ball_up_false" onclick="onClickZu3hzBall(this,22)">22</li>
                            <li id="zu3hz_ball_23" class="ball_up_false" onclick="onClickZu3hzBall(this,23)">23</li>
                            <li id="zu3hz_ball_24" class="ball_up_false" onclick="onClickZu3hzBall(this,24)">24</li>
                            <li id="zu3hz_ball_25" class="ball_up_false" onclick="onClickZu3hzBall(this,25)">25</li>
                            <li id="zu3hz_ball_26" class="ball_up_false" onclick="onClickZu3hzBall(this,26)">26</li>
                        </ul>
                    </div>
					<div class="bigHappy_all">
                    	<div class="btn_1" onclick= "zu3hz_selectall()">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_odd">
                    	<div class="btn_1" onclick= "zu3hz_selectodd()">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_even">
                    	<div class="btn_1" onclick="zu3hz_selecteven()">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_empty">
                    	<div class="btn_1" onclick="zu3hz_selectclear()">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                
                	<div class="ball_total">
                		您选择了共<strong id="zu3hz_betCount" class="red">0</strong> 注，￥
						<strong id="zu3hz_betCountMoney" class="red">0 </strong>元     
                	</div>
                </div>   
                <!--和值 end-->        
            </div>
            <!--组三 end-->
            	
            <!--组六 start-->
            <div id="playType_z6" class="BM_body">            	
            	<div class="group_bet">选择投注:</div>
                <div class="group" id="zu6_li">
                    <ul>
                        <li id="z6_li_1" class="group_li group_current" onclick="changeHZandBH(1)">包号选号</li>
                        <li class="group_li" onclick="changeHZandBH(2)">和值选号</li>
                     </ul>
                </div>
                <!--包号 start-->
                <div class="group_bg" style="display:block;" id="z6_group_1">                
                	<div class="bao_title">至少选择2个号码，奖金320元</div>
                    <div class="bao_select" id="zu6_bao">
                    	<ul>
                            <li id="zu6_ball_0" class="ball_up_false" onclick="onClickZu6Ball(this,0)">0</li>
                            <li id="zu6_ball_1" class="ball_up_false" onclick="onClickZu6Ball(this,1)">1</li>
                            <li id="zu6_ball_2" class="ball_up_false" onclick="onClickZu6Ball(this,2)">2</li>
                            <li id="zu6_ball_3" class="ball_up_false" onclick="onClickZu6Ball(this,3)">3</li>
                            <li id="zu6_ball_4" class="ball_up_false" onclick="onClickZu6Ball(this,4)">4</li>
                            <li id="zu6_ball_5" class="ball_up_false" onclick="onClickZu6Ball(this,5)">5</li>
                            <li id="zu6_ball_6" class="ball_up_false" onclick="onClickZu6Ball(this,6)">6</li>
                            <li id="zu6_ball_7" class="ball_up_false" onclick="onClickZu6Ball(this,7)">7</li>
                            <li id="zu6_ball_8" class="ball_up_false" onclick="onClickZu6Ball(this,8)">8</li>
                            <li id="zu6_ball_9" class="ball_up_false" onclick="onClickZu6Ball(this,9)">9</li>
                        </ul>
                    </div>
					<div class="bigHappy_all">
                    	<div class="btn_1" onclick= "zu6_selectall()">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_odd">
                    	<div class="btn_1" onclick= "zu6_selectodd()">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_even">
                    	<div class="btn_1" onclick="zu6_selecteven()">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_empty">
                    	<div class="btn_1" onclick="zu6_selectclear()">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                
                	<div class="ball_total">
                		您选择了共<strong id="zu6_betCount" class="red">0</strong> 注，￥
						<strong id="zu6_betCountMoney" class="red">0 </strong>元     
					</div>
                </div>   
                <!--包号 end-->
                
                <!--和值 start-->
                <div class="group_bg" id="z6_group_2">                
                	<div class="bigHappy_title">玩法提示：所选号码与开奖号码的和值相同(顺序不限)，且开奖号码中有任意两位相同。 </div>
                    <div class="group_num" id="zu6_he">
                    	<ul>
                        	<li id="zu6hz_ball_3" class="ball_up_false" onclick="onClickZu6hzBall(this,3)">3</li>
                            <li id="zu6hz_ball_4" class="ball_up_false" onclick="onClickZu6hzBall(this,4)">4</li>
                            <li id="zu6hz_ball_5" class="ball_up_false" onclick="onClickZu6hzBall(this,5)">5</li>
                            <li id="zu6hz_ball_6" class="ball_up_false" onclick="onClickZu6hzBall(this,6)">6</li>
                            <li id="zu6hz_ball_7" class="ball_up_false" onclick="onClickZu6hzBall(this,7)">7</li>
                            <li id="zu6hz_ball_8" class="ball_up_false" onclick="onClickZu6hzBall(this,8)">8</li>
                            <li id="zu6hz_ball_9" class="ball_up_false" onclick="onClickZu6hzBall(this,9)">9</li>
                            <li id="zu6hz_ball_10" class="ball_up_false" onclick="onClickZu6hzBall(this,10)">10</li>
                            <li id="zu6hz_ball_11" class="ball_up_false" onclick="onClickZu6hzBall(this,11)">11</li>
                            <li id="zu6hz_ball_12" class="ball_up_false" onclick="onClickZu6hzBall(this,12)">12</li>
                            <li id="zu6hz_ball_13" class="ball_up_false" onclick="onClickZu6hzBall(this,13)">13</li>
                            <li id="zu6hz_ball_14" class="ball_up_false" onclick="onClickZu6hzBall(this,14)">14</li>
                            <li id="zu6hz_ball_15" class="ball_up_false" onclick="onClickZu6hzBall(this,15)">15</li>
                            <li id="zu6hz_ball_16" class="ball_up_false" onclick="onClickZu6hzBall(this,16)">16</li>
                            <li id="zu6hz_ball_17" class="ball_up_false" onclick="onClickZu6hzBall(this,17)">17</li>
                            <li id="zu6hz_ball_18" class="ball_up_false" onclick="onClickZu6hzBall(this,18)">18</li>
                            <li id="zu6hz_ball_19" class="ball_up_false" onclick="onClickZu6hzBall(this,19)">19</li>
                            <li id="zu6hz_ball_20" class="ball_up_false" onclick="onClickZu6hzBall(this,20)">20</li>
                            <li id="zu6hz_ball_21" class="ball_up_false" onclick="onClickZu6hzBall(this,21)">21</li>
                            <li id="zu6hz_ball_22" class="ball_up_false" onclick="onClickZu6hzBall(this,22)">22</li>
                            <li id="zu6hz_ball_23" class="ball_up_false" onclick="onClickZu6hzBall(this,23)">23</li>
                            <li id="zu6hz_ball_24" class="ball_up_false" onclick="onClickZu6hzBall(this,24)">24</li>
                        </ul>
                    </div>
					<div class="bigHappy_all">
                    	<div class="btn_1" onclick= "zu6hz_selectall()">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_odd">
                    	<div class="btn_1" onclick= "zu6hz_selectodd()">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_even">
                    	<div class="btn_1" onclick="zu6hz_selecteven()">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_empty">
                    	<div class="btn_1" onclick="zu6hz_selectclear()">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                
                	<div class="ball_total">
                		您选择了共<strong id="zu6hz_betCount" class="red">0</strong> 注，￥
						<strong id="zu6hz_betCountMoney" class="red">0 </strong>元     
                	</div>
                </div>   
                <!--和值 end-->        
            </div>
            <!--组六 end-->
           	
           	<%@include file="/common/upload.jsp" %>
           	<%@include file="/common/lotterylist.jsp" %>
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
           	<div class="history_more"><a href="/direction/fbt.htm?type=pls&tt=js&num=30">更多在线图表</a></div>
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">缩水过滤软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>排列3快选优化专家</h3>
                    太奇快选功能，使您轻松实现个人判断 ；和数快选、奇偶快选、大小快选、比较快选、除3快选等多种快选方式 ；点数过滤、奇偶总体过滤、大小总体过滤、奇偶定位过滤 奇偶定位过滤 、大小定位过滤、重号过滤、连号过滤、断点过滤、 搭配过滤、自由匹配过滤、自由组合过滤、自由定位过滤。。。

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=3dsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
            </div>
        </div>
        <!--统计分析 end-->
        <!--缩水过滤 start-->
        <div class="Stat">
        	<div class="STitleBg">
           	  <div class="STitle">统计分析软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_17.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>排列3统计分析专家</h3>
                    数据网上自动更新功能，直观的号码分布图功能，不同位数的号码分别采用不同的颜色显 示，多样灵活的频率统计功能，多样灵活的走势分析功能，可从和数、奇偶、大小、重号、延续、连 号等多个方面绘制生动的波浪走势图

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=3dsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
<!--Web Body end-->
</div>
</body>
</html>

