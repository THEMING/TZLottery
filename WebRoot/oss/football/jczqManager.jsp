<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>

<head>
<title>竞彩足球管理</title>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script src="../../js/jquery-1.4.4.min.js"></script>
<script src="../../js/lottery/common.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function tab(index) 
	{
		if(index == 1) {
			$("#td_title").html("<span style=\"color:red;font-size:15px; margin-right:15px\">开奖兑奖管理</span>"+
				"<a href=\"javascript:tab(0)\">比赛对阵管理模式</a>");
			$("#openPrize_box").show();
			$("#query").hide();
			$("#matchInfo_box").hide();
		}
		else {
			$("#td_title").html("<span style=\"color:red;font-size:15px;margin-right:15px\">比赛对阵管理</span>"+
				"<a href=\"javascript:tab(1)\">切换到开奖兑奖模式</a>");
			$("#openPrize_box").hide();
			$("#query").show();
			$("#matchInfo_box").show();
		}
	}
	
	function getopenresult() {
		if ($("#saleDate").val() == null || $("#saleDate").val() == "") {
			return false;
		}
	$("#action").val("open");
		alert($("#action").val());
	}
</script>
</head>
<body>

<div class="tab" style="width:1200px">

<form id="form1" action="/oss/football/jczqManager.aspx" method="post">
	<input id="action" type="hidden" name="action" value="list"/>
	<table>
		<tr align="center"><td id="td_title" colspan="4">
			<span style="color:red;font-size:15px;margin-right:15px">比赛对阵管理</span>
			<a href="javascript:tab(1)">切换到开奖兑奖模式</a>
		</td></tr>
		<tr id="query">
			<td >销售日期:<input id="saleDate" type="text" name="saleDate" value="${saleDate}" style="width:90" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true});"/></td>
		    <td >状态：<s:select list="raceStatus" name="status" headerValue="请选择..." headerKey=""/> </td>
	    	<td >场次：<input type="text" name="matchIndex" id="matchIndex" value="${matchIndex}" /></td>
			<td >
				<span style="margin-right:10px"><input type="submit" style="border:1px #e4e4e4 solid; width:80px; cursor: pointer" name="submit" value="查询" /></span>
				<span ><input type="button" style="border:1px #e4e4e4 solid; width:80px; cursor: pointer" value="添加" onclick="clkAdd()"/></span>
				<span><input type="submit" style="border:1px #e4e4e4 solid; width:80px; cursor: pointer" name="submit" value="获取开奖结果" onclick="return getopenresult()"/></span>
			</td>
		</tr>
		<tr></tr>
	</table>
</form>

<br/>

<div id="matchInfo_box">
<form id="form2" action="/oss/football/jczqManager.aspx" method="post">
	<s:hidden name="action" value="index" id="action2" />
	<s:hidden name="type" />
	<s:hidden name="f_term" />
	<s:hidden name="termId" id="termId"/>
	<s:hidden name="f_openWinTime" />
	<table >
		<tr align="center"><td colspan="18"><span style="color:red;font-size:15px">比赛信息</span></td></tr>
		<tr>
		  	<td height="25"><div align="center">销售日期</div></td>
		    <td height="25"><div align="center">场次编号</div></td>
		    <td height="25"><div align="center">赛事</div></td>
		    <td height="25"><div align="center">主队</div></td>
		    <td height="25"><div align="center">让球</div></td>
		    <td height="25"><div align="center">客队</div></td>
		    <td height="25"><div align="center">开赛时间</div></td>
		    <td height="25"><div align="center">状态</div></td>
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
		<s:iterator value="page.result" id="match">
			<tr>
				<td height="25" ><s:date name="saleDate" format="yyyy-MM-dd"/></td>		
				<td height="25" >${match.boutIndex}</td>
				<td height="25" >${match.matchName}</td>
				<td height="25" >${match.homeTeam}</td>
				<td height="25" >${match.concede}</td>
				<td height="25" >${match.awaryTeam}</td>
				<td height="25" ><s:date name="#match.matchTime" format="yyyy-MM-dd HH:mm"/></td>			
				<td height="25" >${match.status}</td>
				<td height="25" >${match.matchResult}</td>
				<td height="25" >${match.wholeScore}</td>
				<td height="25" >${match.halfScore}</td>
				<td height="25" >${match.sop}</td>
				<td height="25" >${match.pop}</td>
				<td height="25" >${match.fop}</td>
				<td height="25" >${match.stzb}</td>
				<td height="25" >${match.ptzb}</td>
				<td height="25" >${match.ftzb}</td>			
				<td><input type="button" style="border:1px #e4e4e4 solid; width:50px; cursor: pointer" value="编辑" onclick="subm('edit',${term.id});"></input></td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
		<tr> <td><jsp:include page="../../util/page.jsp"></jsp:include></td> </tr>
	</table>
</form>
</div>

<div id="openPrize_box" style="display:none">
	<iframe id="fun_iframe" src="/oss/football/manageJCZQOpenPrize.aspx" scrolling="no" frameborder="0" style="width:1200px; height:800px"></iframe>
</div>

</body>
</html>