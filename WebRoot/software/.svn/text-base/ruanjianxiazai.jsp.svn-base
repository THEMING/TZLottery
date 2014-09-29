<%@ page language="java" pageEncoding="utf-8"%>

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
	<table id="listTable">
		<tr>
		    <td ><div align="center">软件名称</div></td>
		    <td ><div align="center">下载次数</div></td>
		</tr>
		<s:iterator value="list" id="match">
			<tr>
				<td>${match.fileName}</td>
				<td>${match.count}</td>
			</tr>
		</s:iterator>
	</table>


</body>
</html>