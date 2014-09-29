<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>


<head>
	<title>竞彩足球彩期编辑</title>
	<meta name="heading" content="彩期编辑" />
	<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
	<script src="../../js/jquery-1.4.4.min.js"></script>
	<script src="../../js/lottery/common.js"></script>
	<script src="../js/jquery.countdown.pack.js"></script>
	<script src="../js/jquery.countdown-zh-CN.js"></script>
	<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/base.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function()
			{
				$("#addNew_tab").attr("class", "hidden");
				$("#openPrize_tab").attr("class", "hidden");
			}
		);
		
		function subm(num)
	   	{	
			var teamList;
			// teamList = trim($("#arrayMatchName_"+num).val()) + "|" + trim($("#arrayHomeTeam_"+num).val())+ "|" + trim($("#historyHomeTeam_"+num).val())+ "|"  + trim($("#arrayAwaryTeam_"+num).val())+ "|"  + trim($("#historyAwaryTeam_"+num).val());
			teamList = trim($("#arrayMatchName_"+num).html()) + "|" + trim($("#arrayHomeTeam_"+num).html())+ "|" + trim($("#historyHomeTeam_"+num).val())+ "|"  + trim($("#arrayAwaryTeam_"+num).html())+ "|"  + trim($("#historyAwaryTeam_"+num).val());
			var url = "/oss/term/manageJCZQTerm.htm?action=saveMapping";
			var data = {
				teamList : teamList
			};
			$.getJSON(url, data, callbackFunc());
		}
		
		function clkAdd()
		{
			$("#addNew_tab").attr("class", "show");
		}
		
		function clkOpenPrize()
		{
			$("#addNew_tab").attr("class", "hidden");
			$("#openPrize_tab").attr("class", "show");
		}
		
		/* 开奖相关json*/
		(function ($) {
			$.OpenPrize = {
				url : "/oss/term/manageJCZQTerm.htm?action=getMatchInfoByMatchIndex",
				_request : function(param) {
					$.getJSON($.OpenPrize.url, param, $.OpenPrize.callback);
				},
				callback : function(){}
			};
		})(jQuery);
		
		function getMatchInfo()
		{
		alert("aaa")
			$.OpenPrize.callback = function(json) {
				if (json.have=="Yes")
				{
					$("#op_matchName").text(json.matchName);
					$("#op_homeTeam").text(json.homeTeam);
					$("#op_guestTeam").text(json.guestTeam);
					$("#op_rang").text(json.rang);
					$("#op_status").text(json.status);
					//alert(json.matchResult);
	    			$("#op_matchResult").val(json.matchResult);
					$("#op_wholeScore").val(json.wholeScore);
					$("#op_halfScore").val(json.halfScore);
					$("#op_spfsp").val(json.sfp_sp);
					$("#op_cbfsp").val(json.bf_sp);
					$("#op_jqssp").val(json.jq_sp);
					$("#op_bqcsp").val(json.bqc_sp);
				}
			};
			$.OpenPrize._request({matchNo: $("#op_matchNo").val()});
		}
		
		function openPrize()
		{
			if(""==$("#op_matchNo").val())
			{
				alert("请输入场次编号！");
				return;
			}
			if(1==$("#op_special").val())
			{
				$.post("/oss/term/manageJCZQOpenPrize.htm?action=openPrize", {op_matchNo: $("#op_matchNo").val(), op_special: $("#op_special").val()}, openPrizeDuiPrizeResponse);
			}
			else
			{				
				if(""==$("#op_matchResult").val())
				{
					alert("请选择比赛结果！");
					return;
				}
				if(""==$("#op_wholeScore").val())
				{
					alert("请输入全场比分！");
					return;
				}
				if(""==$("#op_halfScore").val())
				{
					alert("请输入半场比分！");
					return;
				}
				if(""==$("#op_spfsp").val())
				{
					alert("请输入胜平负开奖SP值！");
					return;
				}
				if(""==$("#op_cbfsp").val())
				{
					alert("请输入猜比分开奖SP值！");
					return;
				}
				if(""==$("#op_jqssp").val())
				{
					alert("请输入进球数开奖SP值！");
					return;
				}
				if(""==$("#op_bqcsp").val())
				{
					alert("请输入半全场开奖SP值！");
					return;
				}
				
				var data = {
					op_matchNo     : $("#op_matchNo").val(),
					op_special     : $("#op_special").val(),
					op_matchResult : $("#op_matchResult").val(),
					op_wholeScore  : $("#op_wholeScore").val(),
					op_halfScore   : $("#op_halfScore").val(),
					op_spfsp       : $("#op_spfsp").val(),
					op_cbfsp	   : $("#op_cbfsp").val(),
					op_jqssp	   : $("#op_jqssp").val(),
					op_bqcsp	   : $("#op_bqcsp").val()
				}
				$.post("/oss/term/manageJCZQOpenPrize.htm?action=openPrize", data, openPrizeDuiPrizeResponse);
			}
		}
		
		function duiPrize()
		{
			if(""==$("#op_matchNo").val())
			{
				alert("请输入场次编号！");
				return;
			}
			$.post("/oss/term/manageJCZQOpenPrize.htm?action=duiPrize", {op_matchNo: $("#op_matchNo").val()}, openPrizeDuiPrizeResponse);
			
		}
		function openPrizeDuiPrizeResponse(response, status)
		{
			response = eval('(' + response + ')');
			alert(response.message);
		}
		
		function submitMatch()
	   	{
	   		var match; 
			var time = /\d{4}-\d{2}-\d{2} \d{2}:\d{2}/;
			var date = /\d{4}-\d{2}-\d{2}/; 
			
			if(trim($("#saleDate").val() == "")) {
				alert("销售日期不能为空");
				return false; 
			}
			else if(!date.test($("#saleDate").val())) 
			{
				alert("销售日期格式错误（例：2011-04-12）");
				return false;
			}
			
			if(trim($("#matchNo").val()) == "") {
	            alert("场次不能为空");
	            return false;
	        }
	        
			if(trim($("#matchName").val()) == "") {
	            alert("赛事不能为空");
	            return false;                 
	        }
	        
	        if(trim($("#homeTeam").val()) == "") {
	            alert("主队不能为空");
	            return false;
			}
			
			if(trim($("#rang").val()) == "") {
	            alert("让球不能为空");
	            return false;
			}
			
			if(trim($("#guestTeam").val()) == "") {
	            alert("客队不能为空");
	            return false;                 
	        }
	        
	        if(trim($("#matchTime").val()) == "") {
	            alert("开赛时间不能为空");
	            return false;
	        }    
	        else if(!time.test($("#matchTime").val())) 
			{
				alert("开赛时间格式错误（例：2011-04-12 06:25）");
				return false;
			}
			
			if($("#newRaceStatus").val()=="")
			{
				alert("比赛状态不能为空");
	            return false;
			}
			
			if(trim($("#stopSaleTime").val()) == "") {
	            alert("销售截止时间不能为空");
	            return false; 
	        }
	        else if(!time.test($("#stopSaleTime").val())) 
			{
				alert("销售截止时间格式错误（例：2011-04-12 06:25）");
				return false;
			}
	        
	        if(trim($("#openPrizeTime").val()) == "") {
	            alert("开奖时间不能为空");
	            return false; 
	        }
	        else if(!time.test($("#openPrizeTime").val())) 
			{
				alert("开奖时间格式错误（例：2011-04-12 06:25）");
				return false;
			}
			
			match = trim($("#saleDate").val()) + "|" + trim($("#matchNo").val()) + "|" + trim($("#matchName").val()) + "|" 
				+ trim($("#homeTeam").val()) + "|" + trim($("#rang").val()) + "|" + trim($("#guestTeam").val()) + "|"
				+ trim($("#matchTime").val()) + "|" + trim($("#newRaceStatus").val()) + "|" + trim($("#stopSaleTime").val()) + "|" 
				+ trim($("#openPrizeTime").val()) + "|" + $("#matchResult").val() + " |" + $("#wholeScore").val() + " |" + $("#halfScore").val() + " |" 
				+ $("#sop").val() + " |" + $("#pop").val() + " |" + $("#fop").val() + " |" 
				+ $("#stzb").val() + " |" + $("#ptzb").val() + " | " + $("#ftzb").val();
			
			var url = "/oss/term/manageJCZQTerm.htm?action=saveMatch";
			var data = {
				"match" : match
			};
			$.getJSON(url, data, callbackFunc(json));
		}
		function callbackFunc(json)
		{
			// location.reload();
			alert(json);
		}
	</script>
</head>
<body>

<div class="tab">
<s:form action="manageJCZQTerm" method="post" namespace="/oss/term">
	<s:hidden name="action" value="list" />
	<table>
		<caption class="redbold">竞彩足球场次管理</caption>
		<tr>
			<td width="150" >销售日期:<input type="text" name="saleDate" value="${saleDate}" style="width:90" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true});"/></td>
	    	
		    <td width="130" height="25">状态：
			    <s:select list="raceStatus" name="status" headerValue="请选择..." headerKey=""/>
		    </td>
	    	<td width="150" height="25">场次：<input type="text" name="matchIndex" id="matchIndex" value="${matchIndex}" /></td>

			<td width="200" height="25" bgcolor="#ffffff">
				<span style="margin-right:15px"><input type="submit" name="submit" value="查询" /></span>
				<span style="margin-right:15px"><input type="button" value="添加" onclick="clkAdd()"/></span>
				<span style="margin-right:15px"><input type="button" value="开奖/兑奖" onclick="clkOpenPrize()"/></span>
			</td>
		</tr>
	</table>
</s:form>
<br>



<table id="addNew_tab" style="margin-bottom:30px">
	<tr>
	  	<td height="25"><div align="center">销售日期</div></td>
	    <td height="25"><div align="center">场次编号</div></td>
	    <td height="25"><div align="center">赛事</div></td>
	    <td height="25"><div align="center">主队</div></td>
	    <td height="25"><div align="center">让球</div></td>
	    <td height="25"><div align="center">客队</div></td>
	    <td height="25"><div align="center">开赛时间</div></td>
	    <td height="25"><div align="center">状态</div></td>
	    <td height="25"><div align="center">销售截止</div></td>
	    <td height="25"><div align="center">开奖时间</div></td>
	    <td height="25"><div align="center">赛果</div></td>
	    <td height="25"><div align="center">全场比分</div></td>
	    <td height="25"><div align="center">半场比分</div></td>
	    <td height="25"><div align="center">胜欧赔</div></td>
	    <td height="25"><div align="center">平欧赔</div></td>
	    <td height="25"><div align="center">负欧赔</div></td>
	    <td height="25"><div align="center">胜投比</div></td>
	    <td height="25"><div align="center">平投比</div></td>
	    <td height="25"><div align="center">负投比</div></td>
	    <td height="25"><div align="center">编辑</div></td>
	</tr>
	<tr>
		<td height="25"><input id="saleDate" type="text" style="width:65px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true});"/></td>
	    <td height="25"><input id="matchNo" type="text" style="width:65px"/></td>
	    <td height="25"><input id="matchName" type="text" style="width:80px"/></td>
	    <td height="25"><input id="homeTeam" type="text" style="width:80px"/></td>
	    <td height="25"><input id="rang" type="text" style="width:15px" value="0""/></td>
	    <td height="25"><input id="guestTeam" type="text" style="width:80px"/></td>
	    <td height="25"><input id="matchTime" type="text" style="width:100px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></td>
	    <!-- <td height="25"><input id="raceStatus" type="text" style="width:45px"/></td>  -->
	    <td height="25">
	    	<select id="newRaceStatus">
    			<option value="">请选择..</option>
			    <s:iterator id="ss" value="raceStatus">
			    	<option value="${ss}">${ss}</option>
			    </s:iterator>
			</select>
		</td>
		<td height="25"><input id="stopSaleTime" type="text" style="width:100px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></td>
	    <td height="25"><input id="openPrizeTime" type="text" style="width:100px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/></td>
	    <td height="25"><input id="matchResult" type="text" style="width:30px"/></td>
	    <td height="25"><input id="wholeScore" type="text" style="width:30px"/></td>
	    <td height="25"><input id="halfScore" type="text" style="width:30px"/></td>
	    <td height="25"><input id="sop" type="text" style="width:30px"/></td>
	    <td height="25"><input id="pop" type="text" style="width:30px"/></td>
	    <td height="25"><input id="fop" type="text" style="width:30px"/></td>
	    <td height="25"><input id="stzb" type="text" style="width:30px"/></td>
	    <td height="25"><input id="ptzb" type="text" style="width:30px"/></td>
	    <td height="25"><input id="ftzb" type="text" style="width:30px"/></td>
	    <td height="25"><input type="button" value="保存" onclick="submitMatch()"></td>
	</tr>
</table>


<table id="openPrize_tab" style="margin-bottom:30px">
	<tr>
	    <td height="25"><div align="center">场次编号</div></td>
	    <td height="25"><div align="center">赛事</div></td>
	    <td height="25"><div align="center">主队</div></td>
	    <td height="25"><div align="center">让球</div></td>
	    <td height="25"><div align="center">客队</div></td>
	    <td height="25"><div align="center">状态</div></td>
	    <td height="25"><div align="center">特殊？</div></td>
	    <td height="25"><div align="center">赛果</div></td>
	    <td height="25"><div align="center">全场比分</div></td>
	    <td height="25"><div align="center">半场比分</div></td>
	    <td height="25"><div align="center">胜平负SP</div></td>
	    <td height="25"><div align="center">猜比分SP</div></td>
	    <td height="25"><div align="center">进球数SP</div></td>
	    <td height="25"><div align="center">半全场SP</div></td>
	    <td height="25"><div align="center">编辑</div></td>
	</tr>
	<tr>
	    <td height="25"><input id="op_matchNo" name="op_matchNo" type="text" style="width:65px" onblur="getMatchInfo()"/></td>
	    <td height="25"><label id="op_matchName" type="text" style="width:80px"/></td>
	    <td height="25"><label id="op_homeTeam" type="text" style="width:80px"/></td>
	    <td height="25"><label id="op_rang" type="text" style="width:15px" value="0""/></td>
	    <td height="25"><label id="op_guestTeam" type="text" style="width:80px"/></td>
	    <td height="25"><label id="op_status"/></td>
	    <td height="25">
    		<select id="op_special" name="op_special">
    			<option value = "0">否</option>
				<option value = "1">是</option>
			</select>
		</td>
	    <td height="25">
			<select id="op_matchResult" name="op_matchResult">
				<option value = "">请选择..</option>
				<option value = "3">胜</option>
				<option value = "1">平</option>
				<option value = "0">负</option>
			</select>
		</td>
	    <td height="25"><input id="op_wholeScore" name="op_wholeScore" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==58"/></td>
	    <td height="25"><input id="op_halfScore" name="op_halfScore" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==58"/></td>
	    <td height="25"><input id="op_spfsp" name="op_spfsp" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==46"/></td>
	    <td height="25"><input id="op_cbfsp" name="op_cbfsp" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==46"/></td>
	    <td height="25"><input id="op_jqssp" name="op_jqssp" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==46"/></td>
	    <td height="25"><input id="op_bqcsp" name="op_bqcsp" type="text" style="width:30px" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57 || event.keyCode==46"/></td>
	    <td height="25">
	    	<span style="margin-right:15px"><input type="button" value="开奖" onclick="openPrize();"></span>
	    	<span style="margin-right:15px"><input type="button" value="兑奖" onclick="duiPrize();"></span>
	    </td>
	</tr>
</table>


<s:form  action="manageJCZQTerm" method="post" namespace="/oss/term">
<s:hidden name="action" value="list" id="action2" />
<s:hidden name="type" />
<!--<s:hidden name="statusStr" /> -->
<s:hidden name="f_term" />
<s:hidden name="termId" id="termId"/>
<s:hidden name="f_openWinTime" />
<table >
	<tr>
	  	<td height="25"><div align="center">销售日期</div></td>
	    <td height="25"><div align="center">场次编号</div></td>
	    <td height="25"><div align="center">赛事</div></td>
	    <td height="25"><div align="center">主队</div></td>
	    <td height="25"><div align="center">主队历史队名</div></td>
	    <td height="25"><div align="center">让球</div></td>
	    <td height="25"><div align="center">客队</div></td>
	    <td height="25"><div align="center">客队历史队名</div></td>
	    <td height="25"><div align="center">开赛时间</div></td>
	    <td height="25"><div align="center">状态</div></td>
	    <td height="25"><div align="center">赛果</div></td>
	    <td height="25"><div align="center">全场比分</div></td>
	    <td height="25"><div align="center">半场比分</div></td>
	    <td height="25"><div align="center">编辑</div></td>
	</tr>
	<s:iterator value="page.result" id="match" status="st">
		<tr>
			<td height="25" ><s:date name="saleDate" format="yyyy-MM-dd"/></td>		
			<td height="25" >${match.boutIndex}</td>
			<!-- <td height="25" ><input type="text" value="${match.matchName}" id="arrayMatchName_${st.index}" /></td>  -->
			<td height="25" id="arrayMatchName_${st.index}">${match.matchName}</td>
			<!--<td height="25" ><input type="text" value="${match.homeTeam}" id="arrayHomeTeam_${st.index}" /></td> -->
			<td height="25" id="arrayHomeTeam_${st.index}">${match.homeTeam}</td>
			<td height="25" ><input type="text" value="${match.historyHomeTeam}" id="historyHomeTeam_${st.index}" /></td>
			<td height="25" >${match.concede}</td>
			<!--<td height="25" ><input type="text" value="${match.awaryTeam}" id="arrayAwaryTeam_${st.index}" /></td> -->
			<td height="25" id="arrayAwaryTeam_${st.index}">${match.awaryTeam}</td>
			<td height="25" ><input type="text" value="${match.historyAwaryTeam}" id="historyAwaryTeam_${st.index}" /></td>
			<td height="25" ><s:date name="#match.matchTime" format="yyyy-MM-dd HH:mm"/></td>			
			<td height="25" >${match.status}</td>
			<td height="25" >${match.matchResult}</td>
			<td height="25" >${match.wholeScore}</td>
			<td height="25" >${match.halfScore}</td>			
			<td><input type="button" value="保存" onclick="subm(${st.index});"></input></td>
		</tr>
	</s:iterator>
</table>
<table width="90%" border="0" align="center">
	<tr>
		<td><jsp:include page="../../util/page.jsp"></jsp:include></td>
	</tr>
</table>
</s:form>
</div>

</body>
</html>