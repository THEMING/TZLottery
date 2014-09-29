<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>

<head>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script src="../../js/jquery-1.4.4.min.js"></script>
<script src="../../js/My97DatePicker/WdatePicker.js"></script>
<script src="../../js/lottery/common.js"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function openPrize(matchNo)
	{
		$("#id_action").val("openPrize");
		$("#op_matchNo").val(matchNo);
		$("#form2").submit();
	}
	
	function openBatchPrize()
	{
		var op_matchNos = "";
		$("#listTable :checkbox").each(function(i) {
			if($(this).attr("checked")) {
				if(op_matchNos == "") {
					op_matchNos += $(this).val();
				}
				else {
					op_matchNos += "^" + $(this).val();
				}
			}
		});
		alert(op_matchNos);
		$("#op_matchNos").val(op_matchNos);
		$("#id_action").val("openBatchPrize");
		$("#form2").submit();
	}
	
	function duiPrize(matchNo)
	{
		$("#id_action").val("duiPrize");
		$("#op_matchNo").val(matchNo);
		$("#form2").submit();
	}
	
	function duiBatchPrize()
	{
		var op_matchNos = "";
		$("#listTable :checkbox").each(function(i) {
			if($(this).attr("checked")) {
				if(op_matchNos == "") {
					op_matchNos += $(this).val();
				}
				else {
					op_matchNos += "^" + $(this).val();
				}
			}
		});
		alert(op_matchNos);
		$("#op_matchNos").val(op_matchNos);
		$("#id_action").val("duiBatchPrize");
		$("#form2").submit();
	}
	
	function quxiao()
	{
		var op_matchNos = "";
		$("#listTable :checkbox").each(function(i) {
			if($(this).attr("checked")) {
				if(op_matchNos == "") {
					op_matchNos += $(this).val();
				}
				else {
					op_matchNos += "^" + $(this).val();
				}
			}
		});
		alert(op_matchNos);
		$("#op_matchNos").val(op_matchNos);
		$("#id_action").val("cancel");
		$("#form2").submit();
	}
	
	function quer()
	{
		$("#start").val($("#st").val());
		$("#over").val($("#ot").val());
		$("#stat").val($("#sele").val());
		$("#quer").submit();
	}
</script>
</head>
<body>

<div class="tab" style="width:1200px">
<span style="color:red;font-size:18px">${message}</span>

<form id="quer" action="/oss/term/manageSendOrder.htm?action=list" method="post">
<s:hidden name="startTime" id="start" />
<s:hidden name="overTime" id="over" />
<s:hidden name="stat" id="stat" />
	<table border="1">
		<tr>
			<td>开始日期<input type="text" id="st" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"></td>
			<td>结束日期<input type="text" id="ot" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"></td>
			<td>选择状态<select id="sele"><option>请选择</option><option>未中奖</option><option>已中奖</option><option>已兑奖</option></select></td>
			<td><input type="button" value="查询" onclick="quer();" /></td>
		</tr>
	</table>
</form>

<form id="form2" action="/oss/football/manageJCZQOpenPrize.aspx" method="post">
	<s:hidden name="action" value="index" id="id_action" />
	<s:hidden name="op_matchNo" id="op_matchNo" />
	<s:hidden name="op_matchNos" id="op_matchNos" />
	<s:hidden name="type" />
	<s:hidden name="f_term" />
	<s:hidden name="termId" id="termId"/>
	<s:hidden name="f_openWinTime" />
	<table id="listTable">
		<tr align="center">
			<td colspan="19">
				<span style="color:red;font-size:15px">开奖/兑奖</span>
				<input type="button" style="border:1px #FFCCCC solid; width:80px; cursor: pointer" value="批量开奖" onclick="openBatchPrize()"/>
				<input type="button" style="border:1px #FFCCCC solid; width:80px; cursor: pointer" value="批量兑奖" onclick="duiBatchPrize()"/>
				<input type="button" style="border:1px #FFCCCC solid; width:80px; cursor: pointer" value="取消" onclick="quxiao();">
			</td>
		</tr>
		<tr>
			<td ></td>
		    <td ><div align="center">场次编号</div></td>
		    <td ><div align="center">赛事</div></td>
		    <td ><div align="center">主队</div></td>
		    <td ><div align="center">让球</div></td>
		    <td ><div align="center">客队</div></td>
		    <td ><div align="center">开赛时间</div></td>
		    <td ><div align="center">状态</div></td>
		    <td ><div align="center">赛果</div></td>
		    <td ><div align="center">全场比分</div></td>
		    <td ><div align="center">半场比分</div></td>
		    <td ><div align="center">让分胜负SP</div></td>
		    <td ><div align="center">胜负SP</div></td>
		    <!-- 
		    <td ><div align="center">比分SP</div></td>
		     -->
		    <td ><div align="center">进球SP</div></td>
		    <td ><div align="center">半全场SP</div></td>
		    <td ><div align="center">开奖</div></td>
		    <td ><div align="center">兑奖</div></td>
		</tr>
		<s:iterator value="page.result" id="match">
			<tr>
				<td><input value="${match.boutIndex}" type="checkbox"/></td>
				<td>${match.boutIndex}</td>
				<td>${match.matchName}</td>
				<td>${match.homeTeam}</td>
				<td>${match.concede}</td>
				<td>${match.awaryTeam}</td>
				<td><s:date name="#match.matchTime" format="yyyy-MM-dd HH:mm"/></td>			
				<td>${match.status}</td>
				<td>${match.matchResult}</td>
				<td>${match.wholeScore}</td>
				<td>${match.halfScore}</td>
				<td>${match.spRangfenSfp}</td>
				<td>${match.spSfp}</td>
				<!-- 
				<td>${match.spBf}</td>
				 -->
				<td>${match.spJqs}</td>
				<td>${match.spBcsfp}</td>		
				<td>
					<input type="button" value="开奖" onclick="openPrize('${match.boutIndex}')"/> 
				</td>
				<td>
					<c:if test="${match.status=='已开奖'}"> 
						<input type="button" value="兑奖" onclick="duiPrize('${match.boutIndex}')"/> 
					</c:if>
					<c:if test="${match.status!='已开奖'}"> 兑奖 </c:if>
				</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
		<tr> <td><jsp:include page="../../util/page.jsp"></jsp:include></td> </tr>
	</table>
</form>


</body>
</html>