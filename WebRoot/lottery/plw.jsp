<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅足彩排列5购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="排列5,排列五奖池,排列五合买,排列五缩水过滤软件">
<title>一彩票_购彩大厅_排列5</title>
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
<script type="text/javascript" src="../js/lottery/plw.js"></script>
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
<%@include  file="../head.jsp"%>
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
    <!--left start-->
  	<div class="lotter_left">
    	<!--期次信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/plw_logo.jpg" width="101" height="86" /></div>
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
					
					<li>&nbsp;&nbsp;"排列5"由购买者从00000-99999的数字中选取1个5位数为投注号码进行投注，每注2元人民币，每天开奖一次，一等奖单注固定奖金100000元。<a target="_blank" href="/help/help-4-7.htm">[详细规则]</a></li>
					
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
        
        <!--直选 start-->
        <div class="Ball_Method" style="display:block;" id="GG2">       	
            <!--玩法选择 start-->
            <div class="BM_body" id="playType_standard">            	
            	<div class="qixingcai_select">选择投注:</div>                
           	  <div class="pailie_title">每位至少选择&nbsp;1&nbsp;个数字</div>
                    <!--位数选择 start-->
                    <div class="pailie_digit_select">
                        <!--万位数 start-->
                    	<div class="pailie_digit">万位数：</div>
                    	<div class="pailie_select" id="wan">
                    		<ul>
                        		<li id="wan_std_red_ball_0" class="ball_up_false" onclick="onClickRedBall(this,0,1)">0</li>
                            	<li id="wan_std_red_ball_1" class="ball_up_false" onclick="onClickRedBall(this,1,1)">1</li>
                            	<li id="wan_std_red_ball_2" class="ball_up_false" onclick="onClickRedBall(this,2,1)">2</li>
                            	<li id="wan_std_red_ball_3" class="ball_up_false" onclick="onClickRedBall(this,3,1)">3</li>
                            	<li id="wan_std_red_ball_4" class="ball_up_false" onclick="onClickRedBall(this,4,1)">4</li>
                            	<li id="wan_std_red_ball_5" class="ball_up_false" onclick="onClickRedBall(this,5,1)">5</li>
                            	<li id="wan_std_red_ball_6" class="ball_up_false" onclick="onClickRedBall(this,6,1)">6</li>
                            	<li id="wan_std_red_ball_7" class="ball_up_false" onclick="onClickRedBall(this,7,1)">7</li>
                            	<li id="wan_std_red_ball_8" class="ball_up_false" onclick="onClickRedBall(this,8,1)">8</li>
                            	<li id="wan_std_red_ball_9" class="ball_up_false" onclick="onClickRedBall(this,9,1)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all" id="wan_all">
                    		<div class="btn_1" onclick= "selectall(1)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd" id="wan_odd">
                    		<div class="btn_1" onclick= "selectodd(1)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even" id="wan_even">
                    		<div class="btn_1" onclick="selecteven(1)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty" id="wan_empty">
                    		<div class="btn_1" onclick="selectclear(1)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--万位数 end-->
                    	<div class="clear"></div>
                        <!--千位数 start-->
                    	<div class="pailie_digit">千位数：</div>
                    	<div class="pailie_select" id="qian">
                    		<ul>
                        		<li id="qian_std_red_ball_0" class="ball_up_false" onclick="onClickRedBall(this,0,2)">0</li>
                            	<li id="qian_std_red_ball_1" class="ball_up_false" onclick="onClickRedBall(this,1,2)">1</li>
                            	<li id="qian_std_red_ball_2" class="ball_up_false" onclick="onClickRedBall(this,2,2)">2</li>
                            	<li id="qian_std_red_ball_3" class="ball_up_false" onclick="onClickRedBall(this,3,2)">3</li>
                            	<li id="qian_std_red_ball_4" class="ball_up_false" onclick="onClickRedBall(this,4,2)">4</li>
                            	<li id="qian_std_red_ball_5" class="ball_up_false" onclick="onClickRedBall(this,5,2)">5</li>
                            	<li id="qian_std_red_ball_6" class="ball_up_false" onclick="onClickRedBall(this,6,2)">6</li>
                            	<li id="qian_std_red_ball_7" class="ball_up_false" onclick="onClickRedBall(this,7,2)">7</li>
                            	<li id="qian_std_red_ball_8" class="ball_up_false" onclick="onClickRedBall(this,8,2)">8</li>
                            	<li id="qian_std_red_ball_9" class="ball_up_false" onclick="onClickRedBall(this,9,2)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all" id="qian_all">
                    		<div class="btn_1" onclick= "selectall(2)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd" id="qian_odd">
                    		<div class="btn_1" onclick= "selectodd(2)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even" id="qian_even">
                    		<div class="btn_1" onclick="selecteven(2)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty" id="qian_empty">
                    		<div class="btn_1" onclick="selectclear(2)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<!--千位数 end-->
                    	<div class="clear"></div>
                      <!--百位数 start-->
                   	  <div class="pailie_digit">百位数：</div>
                    	<div class="pailie_select" id="bai">
                    		<ul>
                        		<li id="bai_std_red_ball_0" class="ball_up_false" onclick="onClickRedBall(this,0,3)">0</li>
                            	<li id="bai_std_red_ball_1" class="ball_up_false" onclick="onClickRedBall(this,1,3)">1</li>
                            	<li id="bai_std_red_ball_2" class="ball_up_false" onclick="onClickRedBall(this,2,3)">2</li>
                            	<li id="bai_std_red_ball_3" class="ball_up_false" onclick="onClickRedBall(this,3,3)">3</li>
                            	<li id="bai_std_red_ball_4" class="ball_up_false" onclick="onClickRedBall(this,4,3)">4</li>
                            	<li id="bai_std_red_ball_5" class="ball_up_false" onclick="onClickRedBall(this,5,3)">5</li>
                            	<li id="bai_std_red_ball_6" class="ball_up_false" onclick="onClickRedBall(this,6,3)">6</li>
                            	<li id="bai_std_red_ball_7" class="ball_up_false" onclick="onClickRedBall(this,7,3)">7</li>
                            	<li id="bai_std_red_ball_8" class="ball_up_false" onclick="onClickRedBall(this,8,3)">8</li>
                            	<li id="bai_std_red_ball_9" class="ball_up_false" onclick="onClickRedBall(this,9,3)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all" id="bai_all">
                    		<div class="btn_1" onclick= "selectall(3)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd" id="bai_odd">
                    		<div class="btn_1" onclick= "selectodd(3)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even" id="bai_even">
                    		<div class="btn_1" onclick="selecteven(3)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty" id="bai_empty">
                    		<div class="btn_1" onclick="selectclear(3)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--百位数　end-->
                    	<div class="clear"></div>
                   	  <!--十位数 start-->
                   	  <div class="pailie_digit">十位数：</div>
                    	<div class="pailie_select" id="shi">
                    		<ul>
                        		<li id="shi_std_red_ball_0" class="ball_up_false" onclick="onClickRedBall(this,0,4)">0</li>
                            	<li id="shi_std_red_ball_1" class="ball_up_false" onclick="onClickRedBall(this,1,4)">1</li>
                            	<li id="shi_std_red_ball_2" class="ball_up_false" onclick="onClickRedBall(this,2,4)">2</li>
                            	<li id="shi_std_red_ball_3" class="ball_up_false" onclick="onClickRedBall(this,3,4)">3</li>
                            	<li id="shi_std_red_ball_4" class="ball_up_false" onclick="onClickRedBall(this,4,4)">4</li>
                            	<li id="shi_std_red_ball_5" class="ball_up_false" onclick="onClickRedBall(this,5,4)">5</li>
                            	<li id="shi_std_red_ball_6" class="ball_up_false" onclick="onClickRedBall(this,6,4)">6</li>
                            	<li id="shi_std_red_ball_7" class="ball_up_false" onclick="onClickRedBall(this,7,4)">7</li>
                            	<li id="shi_std_red_ball_8" class="ball_up_false" onclick="onClickRedBall(this,8,4)">8</li>
                           		<li id="shi_std_red_ball_9" class="ball_up_false" onclick="onClickRedBall(this,9,4)">9</li>
                        	</ul>
                    	</div>
						<div class="pailie_all" id="shi_all">
                    		<div class="btn_1" onclick= "selectall(4)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd" id="shi_odd">
                    		<div class="btn_1" onclick= "selectodd(4)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even" id="shi_even">
                    		<div class="btn_1" onclick="selecteven(4)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty" id="shi_empty">
                    		<div class="btn_1" onclick="selectclear(4)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--十位数 end-->
                    	<div class="clear"></div>
                   	  <!--个位数 start-->
                   	  <div class="pailie_digit">个位数：</div>
                    	<div class="pailie_select" id="ge">
                    		<ul>
                        	<li id="ge_std_red_ball_0" class="ball_up_false" onclick="onClickRedBall(this,0,5)">0</li>
                            <li id="ge_std_red_ball_1" class="ball_up_false" onclick="onClickRedBall(this,1,5)">1</li>
                            <li id="ge_std_red_ball_2" class="ball_up_false" onclick="onClickRedBall(this,2,5)">2</li>
                            <li id="ge_std_red_ball_3" class="ball_up_false" onclick="onClickRedBall(this,3,5)">3</li>
                            <li id="ge_std_red_ball_4" class="ball_up_false" onclick="onClickRedBall(this,4,5)">4</li>
                            <li id="ge_std_red_ball_5" class="ball_up_false" onclick="onClickRedBall(this,5,5)">5</li>
                            <li id="ge_std_red_ball_6" class="ball_up_false" onclick="onClickRedBall(this,6,5)">6</li>
                            <li id="ge_std_red_ball_7" class="ball_up_false" onclick="onClickRedBall(this,7,5)">7</li>
                            <li id="ge_std_red_ball_8" class="ball_up_false" onclick="onClickRedBall(this,8,5)">8</li>
                            <li id="ge_std_red_ball_9" class="ball_up_false" onclick="onClickRedBall(this,9,5)">9</li>
                        </ul>
                    	</div>
						<div class="pailie_all" id="ge_all">
                    		<div class="btn_1" onclick= "selectall(5)">全选</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_odd" id="ge_odd">
                    		<div class="btn_1" onclick= "selectodd(5)">奇</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_even" id="ge_even">
                    		<div class="btn_1" onclick="selecteven(5)">偶</div>
                    		<div class="btn_2"></div>
                    	</div>
                    	<div class="pailie_empty" id="ge_empty">
                    		<div class="btn_1" onclick="selectclear(5)">清除</div>
                    		<div class="btn_2"></div>
                    	</div>
                   	  <!--个位数 end-->
                    </div>
                    <!--位数选择 end-->
                
                
                <div class="ball_total">
                  您共选择了&nbsp;&nbsp;<span id="wanCount">0</span>&nbsp;&nbsp;个万位号码，&nbsp;&nbsp;<span id="qianCount">0</span>&nbsp;&nbsp;个千位号码，<br />
                  &nbsp;&nbsp;<span id="baiCount">0</span>&nbsp;&nbsp;个百位号码，&nbsp;&nbsp;<span id="shiCount">0</span>&nbsp;&nbsp;个十位号码，&nbsp;&nbsp;<span id="geCount">0</span>&nbsp;&nbsp;个个位号码，&nbsp;&nbsp;<span id="betCount">0</span>注&nbsp;&nbsp;，&nbsp;&nbsp;共￥<span id="betCountMoney">0</span>元&nbsp;&nbsp;
                </div>
                           
            </div>
            <!--玩法选择 end-->
            <%@include file="/common/upload.jsp" %>
            <%@include file="/common/lotterylist.jsp" %>
        	<%@include file="/common/buyType.jsp" %>
        	</div>
    </div>
    <!--left end-->
    <!--right start-->
  	<div class="lotter_right">
    	<!--开奖历史　start-->
        <div class="history_top"></div>
        <div class="history_body">
			<div class="history_title">开奖历史</div>
            <div class="history_Last">上期开奖号码</div>
			<div id="history_last" class="history_LastNum"></div>
          	<div id="history_pre_list" class="history_preNum"></div>
           	<div class="history_more"><a href="/direction/fbt.htm?type=plw&tt=js">更多在线图表</a></div>
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
        <div class="hours_24"><a href="http://wpa.qq.com/msgrd?V=1&Uin=2432882152&Site=" target="_blank"><img src="../images/img/right_28.jpg" width="252" height="75" /></a></div>
        <!--/*24小时客服*/　end-->
  </div>
    <!--right end-->
</div>
<!--Web Body end-->
<div class="clear"></div>
<%@include file="../foot.jsp"%>
</body>
</html>
