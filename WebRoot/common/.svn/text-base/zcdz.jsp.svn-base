<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/lottery.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script type="text/javascript" src="../js/lottery/zc14.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<link rel="stylesheet" href="../css/jquery-ui-1.8.16.custom.css"/>
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
<script> 
	$(document).ready(function()
		{
			g_plan = new Plan();
			
			var termNo = "${termNo}";
			if(termNo != "") {
				$("#termNo").val(termNo)
			}
			
			g_plan.term = "${termNo}";
			g_plan.termStatus = "${lotteryTerm.termStatus}";
			var stopSaleTime = "${stopSaleTime}";
			var deadLine = "${deadLine}";
			$("#countTime").countdown({until:deadLine, compact: true, format: 'DHMS',  onExpiry: liftOff});
			initMatchList();
			iframeResize();
			//loginw();
		}
	);
	
	function iframeResize() {
		var obj = parent.document.getElementById("zcdz_iframe");
		obj.height = $("#zcdz_box").height() + 30;
	}
		
	$(function(){
		$(".ZCDZ_TR:odd").addClass("ZC_tr_odd");
	});
	
	function changeTerm()
	{
		var termNo = $("#termNo").val();
		this.location.href="/lottery/index.htm?action=listAllMatchArrageForZCDZ&termNo="+termNo;
	}
	function loginz(){
	var betCount = $("#betCount").text();
	if(betCount < 1) {
		if(g_playType != PLAY_TYPE_FILE_UPLOAD) {
			alert("请选择14场比赛...");
			return;
		}
	}
	parent.buy1();
	}
</script>

</head>
<body>
	<div id="zcdz_box">
		<div class="ZCDZ_select">
			第<select id="termNo" class="red" style="font-size:16px" onchange="changeTerm()">
				<s:iterator value="termList">
					<option value="<s:property value="termNo"/>"><s:property value="termNo"/></option>
				</s:iterator>
			</select>期
		</div>
		<div style="float:right; padding:8px; font-size:14px; margin-right:50px">剩余时间：<span id="countTime">00:00:00</span></div>
		<!--  <div class="ZCDZ_select"><img src="../images/369caibg/index001_26.png" /></div> -->
		<div class="clear"></div>
		<div class="TR_title">
			<ul>
		 		<li class="ZCDZ_changci">场次</li>
		     	<li class="ZCDZ_saishi">赛事</li>
		     	<li class="ZCDZ_bisaishijian">比赛时间</li>
		     	<li class="ZCDZ_duizhen">VS（对阵）</li>
		     	<li class="ZCDZ_bifen">比分</li>
		     	<li class="ZCDZ_quanbao">全包</li>
		 	</ul>
		</div>
	    <div class="clear"></div>
	    
	    <s:iterator value="matchArrangelist">	
		    <div class="ZCDZ_TR">
				<ul>
					<li class="ZCDZ_changci"><s:property value="boutIndex"/></li>
		            <li class="ZCDZ_saishi"><s:property value="matchName"/></li>
		            <li class="ZCDZ_bisaishijian"><s:date name="matchTime" format="yyyy-MM-dd HH:mm"/></li>
		            <li class="ZCDZ_duizhen"><s:property value="homeTeam"/> <span>VS</span> <s:property value="awaryTeam"/></li>
		            <li class="ZCDZ_bifen">
			         	<div class="Fraction">
			         		<ul>
			                	<li id="match_<s:property value="boutIndex"/>_3" class="off3" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 3)" />
								<li id="match_<s:property value="boutIndex"/>_1" class="off1" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 1)" />
								<li id="match_<s:property value="boutIndex"/>_0" class="off0" onclick="onClickNumber(this, <s:property value="boutIndex"/>, 0)" />
			                </ul>
			            </div>
		            </li>
		            <li class="ZCDZ_quanbao">
		            	<input id="match_checkbox_<s:property value="boutIndex"/>" type="checkbox" onclick="onSelectAll(this, <s:property value="boutIndex"/>)"/>
		            </li>
				</ul>
		    </div>
	    </s:iterator>
		<div class="clear"></div>
	        
		<div class="ZCDZ_btn1"><input type="image" src="../images/369caibg/369cai_55.gif" onclick="loginz()"/></div>
		<div class="ZCDZ_btn2"><a target="_blank" href="/help/help-home-5-3-11.htm"><img src="../images/369caibg/index001_14.png" width="75" height="29" /></a></div>
		<div class="clear"></div>
		<div class="ZCDZ_TouZhu">您共选择了&nbsp;&nbsp;<span id="betCount">0</span>注&nbsp;&nbsp;，&nbsp;&nbsp;共￥<span id="betCountMoney">0</span>元&nbsp;&nbsp;</div> 
	</div>
</body>