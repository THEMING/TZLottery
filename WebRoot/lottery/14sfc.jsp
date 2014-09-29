<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅竞彩胜负彩购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="14场胜负彩,足彩胜负彩,缩水过滤免费软件,足彩合买">
<title>一彩票_购彩大厅_14场胜负彩</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/lottery.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="favicon.ico" /> 
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
<script type="text/javascript" src="../js/lottery/zc14.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>

<script language="javascript">
	$(document).ready(function()
		{
			getData();
			 login();
			g_plan = new Plan();
			initMatchList();
			//隐藏追号
			$("#betType_zh").hide();
			$("#betType_chase_box").hide();
			onChangePlayType(0);
			
			var termNo = "${termNo}";
			if(termNo != "") {
				$("#termNo").val(termNo)
			}
			
			//term info
			g_plan.term = "${termNo}";
			g_plan.termStatus = "${lotteryTerm.termStatus}";
			var stopSaleTime = "${stopSaleTime}";
			var deadLine = "${deadLine}";
			$("#currentTermStopTime").text(stopSaleTime);
			$("#countTime").countdown({until:deadLine, compact: true, format: 'DHMS',  onExpiry: liftOff});
		}
	);
	
	function changeTerm()
	{
		var termNo = $("#termNo").val();
		this.location.href="/lottery/index.htm?lotteryType=14sfc&termNo="+termNo;
	}
	
	//玩法选择
	var last_play_index = 0;
	function onChangePlayType(index)
	{
		$("#betType_zh").hide(); //隐藏追号
		switch(index)
		{
			case 0: //标准玩法
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				$("#playType_standard").show();
				$("#playType_standard_list").show();
				$("#playType_upload").hide();
				onChangeBetType(0);
				g_playType = PLAY_TYPE_FS;
				break;
			case 1: //单式上传
				$("#play"+last_play_index).toggleClass("BMTitleBg_current");
				$("#play"+index).toggleClass("BMTitleBg_current");
				$("#playType_standard").hide();
				$("#playType_standard_list").hide();
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
        	<div class="ball_img"><img src="../images/lottery/zc14_logo.gif" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周一、三、五21:30开奖，百万奖、期期开</li>
                    <li>正在销售：第&nbsp;&nbsp;
                    	<span>
                    		<select id="termNo" class="red" style="font-size:16px" onchange="changeTerm()">
							<s:iterator value="termList">
								<option value="<s:property value="termNo"/>"><s:property value="termNo"/></option>
							</s:iterator>
						</select>	
                    	</span> &nbsp;&nbsp;期
                    </li>
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
					
					<li>&nbsp;&nbsp;胜负游戏是以中国足球彩票胜负玩法所选择的足球比赛场次为竞猜对象，购买"胜负游戏"时，由购买者从中国足球彩票胜负玩法选择的所有竞猜场次每场比赛在全场90分钟（含伤情补时）比赛的胜平负的结果进行投注，可以单式、复式投注，每注2元人民币，单注彩票中奖奖金最高限额500万元。<a target="_blank" href="/help/help-4-8.htm">[详细规则]</a></li>
					<li>&nbsp;&nbsp;使用叠加过滤及胆拖缩水软件，多种缩水模式，超强过滤功能，灵活复盘功能，流水线式设计，已帮助多位用户夺得百万大奖，<a href="/download.htm?fileName=sfcsetup.zip">[免费下载]</a></li>
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
        <!--标准玩法 start-->
        <div class="Ball_Method" style="display:block;">       	
            <!--标准玩法 start-->
            <div id="playType_standard" class="BM_body">            	
            	<div class="ZC14_select">选择比赛:</div>                
                <div class="ZC14_title">
                	<ul>
                    	<li class="space_changci">场次</li>
                        <li class="space_saishi">赛事</li>
                        <li class="space_bisaishijian">比赛时间</li>
                        <li class="space_duizhen">VS（对阵）</li>
                        <li class="space_bifen">选号区</li>
                        <li class="space_quanbao">全包</li>
                    </ul>
                </div>
                
                <s:iterator value="matchArrangelist">
                <div class="ZC14_tr">
                	<ul>
                    	<li class="space_changci"><s:property value="boutIndex"/></li>
                        <li class="space_saishi"><s:property value="matchName"/></li>
                        <li class="space_bisaishijian"><s:date name="matchTime" format="yyyy-MM-dd HH:mm"/></li>
                        <!--  <li class="space_bisaishijian"><s:property value="sop"/><s:property value="pop"/><s:property value="fop"/></li>-->
                        <li class="space_duizhen"><s:property value="homeTeam"/><span>VS</span><s:property value="awaryTeam"/></li>
                        <li class="space_bifen">
                        	<div class="bifen">
                        		<ul>
                            		<li id="match_<s:property value="boutIndex"/>_3" class="off3" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 3)" />
                                	<li id="match_<s:property value="boutIndex"/>_1" class="off1" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 1)" />
                                	<li id="match_<s:property value="boutIndex"/>_0" class="off0" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 0)" />
                            	</ul>
                            </div>
                        </li>
                        <li class="space_quanbao"><input id="match_checkbox_<s:property value="boutIndex"/>" type="checkbox" class="quanbao" value="" onclick="onSelectAll(this, <s:property value="boutIndex"/>)"/></li>
                    </ul>
                </div>
                </s:iterator>
			<div class="ball_total">您共选择了&nbsp;&nbsp;<span id="betCount">0</span>注&nbsp;&nbsp;，&nbsp;&nbsp;共￥<span id="betCountMoney">0</span>元&nbsp;&nbsp;</div> 
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
			<div id="history_last" class="history_ball_bg"></div>
          	<div id="history_pre_list" class="history_preNum"></div>
           	<!--<div class="history_more">more</div>-->
        </div>
        <div class="history_bottom"></div>
        <!--开奖历史　end-->
    	<!--统计分析 start-->
		<div class="Stat">
        	<div class="STitleBg">
            	<div class="STitle">叠加过滤软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>足彩叠加过滤专家</h3>
                    标准塔式叠加，自由塔式叠加，注水叠加，幸运叠加；场次过滤，连续过滤，专家过滤，赔率过滤，自由过滤，断点过滤， 积分过滤，太奇过滤，场形过滤，连形过滤， 匹配过滤，集合过滤，搭配过滤，延续过滤；多层分组容错功能（太奇容错）；直接组合（方案相加、相减、取公共部分）， 投注调整等。



                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=sfcsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
            </div>
        </div>
        <!--统计分析 end-->
        <!--缩水过滤 start-->
        <div class="Stat">
        	<div class="STitleBg">
           	  <div class="STitle">胆拖缩水软件</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_17.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>足彩胆拖缩水专家</h3>
                     标准胆拖技术， 智能胆拖技术，胆选对8场以上即可中14保14，中14保13优化技术，节省资金最高达27倍；经济优化，虽然不保证100%覆盖，但是可用最少资金覆盖最大可能面积



                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=sfcsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
