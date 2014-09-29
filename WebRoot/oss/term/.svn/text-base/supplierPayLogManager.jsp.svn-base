<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>出票商充值记录查询</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script type="text/javascript">
	function dosub(which)
	{
		if(which == 1)
		{
			$("#action").val("querySupplier");
			$("#form").submit();
		}
		else if(which == 2)
		{
			$("#action").val("queryOrder");
			$("#form").submit();
		}
		else if(which == 3)
		{
			$("#action").val("queryWinOrder");
			$("#form").submit();
		}
	}
</script>
</head>
<body>
<div class="tab">
<s:form  id="form" action="supplierPayLogManager" method="post" namespace="/oss/term">
<s:hidden name="action" value="query" id="action"/>
	<table>
		<tr>
			<td>出票商名称</td>
			<td>彩种</td>
			<td>开始时间</td>
			<td>结束时间</td>
		</tr>
		<tr>
			<td>
				<select name="name" id="name">
  					<s:iterator id="rs" value="names">
  						<option value="${rs }">${rs }</option>
  					</s:iterator>
  				</select>
			</td>
			<td><input type="text" id="type" name="type" value="${type }"></input></td>
			<td><input type="text" name="stime" value="${stime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
			<td><input type="text" name="etime" value="${etime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
			<td><input type="submit" value="确定"></input></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>充值金额</td>
			<td>投注金额</td>
			<td>中奖金额</td>
		</tr>
		<s:iterator>
			<tr>
				<td><a href="#" onclick="dosub(1);">${changeMoney }</a></td>
				<td><a href="#" onclick="dosub(2);">${spendMoney }</a></td>
				<td><a href="#" onclick="dosub(3);">${winMoney }</a></td>
			</tr>
		</s:iterator>
	</table>
</s:form>
</div>

</body>
