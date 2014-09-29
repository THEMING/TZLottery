<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="一彩票购彩大厅竞彩进球彩购买频道为您提供代购投注，缩水过滤软件投注，统计分析软件，机选投注，单式上传，合买投注，追号投注，手机投注。彩票改变生活，我们改变彩票。">
<meta name="keywords" content="进球彩,足彩进球彩,足彩缩水过滤软件,足彩合买，进球彩合买">
<title>一彩票_购彩大厅_4场进球彩</title>
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
<script type="text/javascript" src="../js/lottery/4cjq.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>

<script language="javascript">
	$(document).ready(function()
		{
		 login();
			getData();
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
			for(var i=1; i<=4; i++)
			{
				$("#match_checkbox_" +i+"1").attr("checked", false);
				$("#match_checkbox_" +i+"2").attr("checked", false);
			}
		}
	);
	
	function changeTerm()
	{
		var termNo = $("#termNo").val();
		this.location.href="/lottery/index.htm?lotteryType=4cjq&termNo="+termNo;
	}
	
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
  	<div class="lotter_left">
    	<!--期次信息 start-->
    	<div class="ball">
        	<div class="ball_img"><img src="../images/lottery/zc14_logo.gif" width="101" height="86" /></div>
            <div class="ball_infor">
            	<ul>
                	<li class="jiangchi">奖池金额：<span id="prizePool"></span>元人民币</li>
                    <li>每周一、三、五21:30开奖，百万奖、期期开</li>
                    <li>正在销售：第&nbsp;&nbsp;
                    	<span id="currentTerm">
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
					
					<li>&nbsp;&nbsp;4场进球游戏由购买者对4场比赛8支球队在全场90分钟(含伤情补时)的进球数量(0、1、2、3+)进行投注，可以单式、复式投注，每注2元人民币，单注彩票中奖奖金最高限额500万元。<a target="_blank" href="/help/help-4-10.htm">[详细规则]</a></li>
					<li>&nbsp;&nbsp;使用进球彩投注优化专家软件，多种叠加，过滤模式，功能强大，有效去除垃圾注，<a href="/download.htm?fileName=jqcsetup.zip">[点此下载]</a></li> 
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
            	<div class="ZC4_select" style="height:320px">选择投注:</div>                
                <div class="ZC4_title" style="margin-bottom:7px">
                	<ul>
                    	<li class="space_changci">场次</li>
                        <li class="space_saishi">赛事</li>
                        <li class="space_bisaishijian">比赛时间</li>
                        <li class="space_duizhen">VS（对阵）</li>
                        <li class="space_bifen">比分</li>
                        <li class="space_quanbao">全包</li>
                    </ul>
                </div>
                
                <s:iterator value="matchArrangelist">
                <div class="ZC6_tr">
                	<ul>
                    	<li class="ZC6_changci"><s:property value="boutIndex"/></li>
                        <li class="ZC6_saishi"><s:property value="matchName"/></li>
                        <li class="ZC6_bisaishijian"><s:date name="matchTime" format="yyyy-MM-dd HH:mm"/></li>
                        <li class="ZC6_duizhen">
                        	<div class="ZC6_dz">
                            	<ul>
                                	<li><s:property value="homeTeam"/></li>
                                    <li><s:property value="awaryTeam"/></li>
                                </ul>
                            </div>
                        </li>
                        <li class="ZC6_bifen">
                        	<div class="ZC6_bf">
                        		<div class="ZC4_bf1">
                            		<ul>
                                    	<li id="match_${boutIndex}_01" class="off01" onclick="onClickNumber1(this, ${boutIndex}, 0)" />
										<li id="match_${boutIndex}_11" class="off11" onclick="onClickNumber1(this, ${boutIndex}, 1)" />
										<li id="match_${boutIndex}_21" class="off21" onclick="onClickNumber1(this, ${boutIndex}, 2)" />
										<li id="match_${boutIndex}_31" class="off31" onclick="onClickNumber1(this, ${boutIndex}, 3)" />
                            		</ul>
                                </div>
                                <div class="ZC4_bf2">
                                	<ul>
                            			<li id="match_${boutIndex}_02" class="off02" onclick="onClickNumber2(this, ${boutIndex}, 0)" />
										<li id="match_${boutIndex}_12" class="off12" onclick="onClickNumber2(this, ${boutIndex}, 1)" />
										<li id="match_${boutIndex}_22" class="off22" onclick="onClickNumber2(this, ${boutIndex}, 2)" />
										<li id="match_${boutIndex}_32" class="off32" onclick="onClickNumber2(this, ${boutIndex}, 3)" />
                                    </ul>
                            	</div>
                            </div>
                        </li>
                        <li class="ZC6_quanbao">
                        	<div class="ZC6_qb">
                        		<div class="ZC6_qb1">
                        		    <input id="match_checkbox_${boutIndex}1" type="checkbox" onclick="onSelectAll1(this, ${boutIndex})"/>
                        		</div>
								<div class="ZC6_qb2">
                                	<input id="match_checkbox_${boutIndex}2" type="checkbox" onclick="onSelectAll2(this, ${boutIndex})""/>
                                </div>
                            </div>
                       </li>
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
            	<div class="STitle">缩水过滤</div>
                <div class="Stat_softWare">
				  <img src="../images/img/right_10.jpg" width="60" height="80" alt="369cai统计专家" class="Stat_softWare_pic" />
                    <h3>进球彩投注优化专家</h3>
                   胜负叠加，进球叠加，比分叠加等多种叠加模式，专门针对进球玩法的多种过滤模式，功能强大，有效去除垃圾注！

                </div>
                <div class="Stat_btn"><a href="/download.htm?fileName=jqcsetup.zip"><img src="../images/369caibg/right_14.jpg" width="102" height="29" alt="免费下载369cai统计专家"/></a></div>
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
