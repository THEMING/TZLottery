<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅足彩大乐透购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="大乐透,大乐透奖池,大乐透缩水过滤,生肖乐">
<title>一彩票_购彩大厅_大乐透 </title>
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
<script type="text/javascript" src="../js/lottery/dlt.js"></script>
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
				$("#playType_shengxiaole").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_FS;
				break;
			case 1: //生肖乐
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#playType_standard").hide();
				$("#playType_standard_list").show();
				$("#betType_zh").hide(); //隐藏追号
				$("#playType_upload").hide();
				$("#playType_shengxiaole").show();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_SXL;
				break;
				
			case 2: //单式上传
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				
				$("#playType_standard").hide();
				$("#playType_standard_list").hide();
				$("#betType_zh").hide(); //隐藏追号
				$("#playType_shengxiaole").hide();
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
				$(".ball_Remind").slideDown("slow");
				$(".ball_arrow").css("background-position","118px -9px")
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
        	<div class="ball_img"><img src="../images/lottery/dlt_logo.jpg" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周一、三、六晚21：30开奖，单注最高奖金&nbsp;&nbsp;<span style=" font-size:16px; font-weight:bold;">1000</span>&nbsp;&nbsp;万元！</li>
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
					
					<li>&nbsp;&nbsp;超级大乐透是指由购买者从01—35共35个号码中选取5个号码为前区号码，并从01—12共12个号码中选取2个号码为后区号码组合为一注彩票进行的基本投注。每注金额人民币2元，可以单式、拖胆、复式投注。每周销售三期。单注最高限额封顶500万元。<a target="_blank" href="/help/help-4-4.htm">[详细规则]</a></li>
					<li>&nbsp;&nbsp;使用乐透缩水过滤软件，多种缩水，胆拖，过滤模式帮您节省资金，方案可直接上传到本站。<a href="/download.htm?fileName=lottosetup.zip">[免费下载]</a></li> 
				</ul>
			</div>
        </div>
        <!--期次信息 end-->
        
        <!--玩法选择 start-->
        <div class="BMTitleBg">
        	<ul>
            	<li>玩法选择：</li>
            	<li id="play0" class="BMTitleBg_current" onclick="onChangePlayType(0)">标准玩法</li>
            	<li id="play1" onclick="onChangePlayType(1)">生肖乐</li>
            	<li id="play2" onclick="onChangePlayType(2)">单式上传</li>
        	</ul>
        </div>
        <!--玩法选择 end-->
        
        
        <div class="Ball_Method" style="display:block;" id="GG2">       	
            <!--标准玩法 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="BM_select">选择投注:</div>                
                
                <div class="BM_redarea">
                	<div class="redarea_title">红色区域至少选择&nbsp;5&nbsp;个球</div>
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
                            <li id="std_red_ball_34" class="redarea_ball_li" onclick="onClickRedBall(this,34)">34</li>
                            <li id="std_red_ball_35" class="redarea_ball_li" onclick="onClickRedBall(this,35)">35</li>
                        </ul>
                    </div>
                    <div class="redarea_operat">
                          <div class="redarea_listbox">
                            <select id="randomselect_0" name="select" class="listbox" >
                              <option>5</option>
                              <option>6</option>
                              <option>7</option>
                              <option>8</option>
                              <option>9</option>
                              <option>10</option>
                              <option>11</option>
                              <option>12</option>
                            </select>
                          </div>
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_61.gif" onclick="randomSelectBalls(0)" />
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_63.gif" onclick="clearSelectedBalls(0)" />
                    </div>
                </div>
                <div class="BM_bluearea">
                	<div class="bluearea_title">蓝色区域至少选择&nbsp;2&nbsp;个球</div>
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
                        </ul>                    </div>
                    <div class="bluearea_operat">
                    	 <div class="bluearea_listbox">
                          	<select name="select" class="listbox" id="randomselect_1">
                              <option selected="selected">2</option>
                              <option>3</option>
                              <option>4</option>
                              <option>5</option>
                              <option>6</option>
                              <option>7</option>
                              <option>8</option>
                              <option>9</option>
                              <option>10</option>
                              <option>11</option>
                              <option>12</option>
                            </select>
                          </div>
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_61.gif" onclick="randomSelectBalls(1)" />
                          <input name="" class="jixuan_empty" type="image" src="../images/369caibg/369cai_63.gif" onclick="clearSelectedBalls(1)" />
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
                  	
            <!--生肖乐 start-->
            <div id="playType_shengxiaole"class="BM_body">            	
            	<div class="bigHappy_bet">选择投注:</div>                
                	<div class="bigHappy_title">至少选&nbsp;2&nbsp;个号码，与后区开奖号码一致即中奖，固定奖金60元</div>
                    <div class="bigHappy_select">
                    	<ul>
                            <li id="sxl_std_red_ball_1" class="ball_up_false" onclick="onClickSxlRedBall(this,1)">01</li>
                            <li id="sxl_std_red_ball_2" class="ball_up_false" onclick="onClickSxlRedBall(this,2)">02</li>
                            <li id="sxl_std_red_ball_3" class="ball_up_false" onclick="onClickSxlRedBall(this,3)">03</li>
                            <li id="sxl_std_red_ball_4" class="ball_up_false" onclick="onClickSxlRedBall(this,4)">04</li>
                            <li id="sxl_std_red_ball_5" class="ball_up_false" onclick="onClickSxlRedBall(this,5)">05</li>
                            <li id="sxl_std_red_ball_6" class="ball_up_false" onclick="onClickSxlRedBall(this,6)">06</li>
                            <li id="sxl_std_red_ball_7" class="ball_up_false" onclick="onClickSxlRedBall(this,7)">07</li>
                            <li id="sxl_std_red_ball_8" class="ball_up_false" onclick="onClickSxlRedBall(this,8)">08</li>
                            <li id="sxl_std_red_ball_9" class="ball_up_false" onclick="onClickSxlRedBall(this,9)">09</li>
                            <li id="sxl_std_red_ball_10" class="ball_up_false" onclick="onClickSxlRedBall(this,10)">10</li>
                            <li id="sxl_std_red_ball_11" class="ball_up_false" onclick="onClickSxlRedBall(this,11)">11</li>
                            <li id="sxl_std_red_ball_12" class="ball_up_false" onclick="onClickSxlRedBall(this,12)">12</li>
                        </ul>
                    </div>
					<div class="bigHappy_all">
                    	<div class="btn_1" onclick="sxl_selectall()">全选</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_odd">
                    	<div class="btn_1" onclick="sxl_selectodd()">奇</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_even">
                    	<div class="btn_1"  onclick="sxl_selecteven()">偶</div>
                    	<div class="btn_2"></div>
                    </div>
                    <div class="bigHappy_empty">
                    	<div class="btn_1" onclick="sxl_selectclear()">清除</div>
                    	<div class="btn_2"></div>
                    </div>
                
                <div class="ball_total">
                	您共选择了&nbsp;&nbsp;<span id="sxl_Count" style="color:red">0</span>&nbsp;&nbsp;个号码，
                	共&nbsp;&nbsp;<span id="sxl_betCount">0</span>&nbsp;注，&nbsp;
                	共￥<span id="sxl_betCountMoney">0</span>元
                </div>
                           
            </div>
            <!--生肖乐 end-->
            
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
           	<div class="history_more"><a href="/direction/fbt.htm?type=dlt&tt=js">更多在线图表</a></div>
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">乐透软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>大乐透缩水过滤专家</h3>
                    乐透缩水过滤专家适应于所有选7选6选5型彩票，多种缩水模式，多种胆拖模式，以及多种过滤模式，轻松实现您的个人判断并过滤掉垃圾注，帮您节省资金。

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=lottosetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
        <div class="RecomTitleBg">
        	<div class="RecomTitle">
            	<ul>
               	  <li class="RecomTitle1 RecomCurrent_bg">专家推荐</li>
                  <li class="RecomTitle2">相关资讯</li>
                </ul>
            </div>
        </div>
        <div class="Recom_Expert" style="display:block;" id="RR1">
        	<div class="Expert_pic">
        		<ul>
            		<li><img src="../images/img/right_26.jpg" width="59" height="61" /></li>
					<li>大乐透</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
                	<li><span></span></li>
					<li></li>
					<li></li>
                </ul>
            </div>
            <div class="Expert_pic">
        		<ul>
            		<li><img src="../images/img/right_26.jpg" width="59" height="61" /></li>
					<li>大乐透</li>
            	</ul>
            </div>
            <div class="Expert_con">
            	<ul>
                	<li><span></span></li>
					<li></li>
					<li></li>
                </ul>
            </div>
        </div>
  <div class="RecomCon" id="RR2">
        	<ul>
            	<li></li>
				<li></li>
				<li></li>
				<li></li>
            </ul>
        </div>  
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

