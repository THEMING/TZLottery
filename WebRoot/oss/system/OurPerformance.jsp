<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="../skin/01/js/jquery-1.3.2.js"></script>
<script src="../skin/01/js/common.js" type=text/javascript></script>
<style type="text/css">
<!--
.STYLE2 {color: #999999}
-->
</style>
<script src="../../util/chartJs/Chart.js"></script>
<script language="javascript">
var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

window.onload = function(){
	$.ajax({
		type:"post",
		url: "ourPerformance.htm?action=getData",
		data:{},
		success: function(data, textStatus){
		var a=eval("("+data+")");
		//map.put("regNumA", regNumA);
		//map.put("rechargeNumA", rechargeNumA);
		//map.put("rechargeMonA", rechargeMonA);
		
		var regNumA = a.regNumA;
		var rechargeNumA = a.rechargeNumA;
		var rechargeMonA = a.rechargeMonA;
		var dataA = a.dataA;
		
		var lineChartData1 = {
				labels : dataA,//["January","February","March","April","May","June","July"],
				datasets : [
					{
						label: "reg",
						fillColor : "rgba(220,220,220,0.2)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(220,220,220,1)",
						data:regNumA
					}
				]

			}
		var lineChartData2 = {
				labels : dataA,//["January","February","March","April","May","June","July"],
				datasets : [
					{
						label: "charge",
						fillColor : "rgba(151,187,205,0.2)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(151,187,205,1)",
						data : rechargeNumA
					}
				]

			}
		var lineChartData3 = {
				labels : dataA,//["January","February","March","April","May","June","July"],
				datasets : [
					{
						label: "chargeMon",
						fillColor : "rgba(220,187,205,0.2)",
						strokeColor : "rgba(220,187,205,1)",
						pointColor : "rgba(220,187,205,1)",
						pointStrokeColor : "#fff",
						pointHighlightFill : "#fff",
						pointHighlightStroke : "rgba(220,187,205,1)",
						data : rechargeMonA
					}
				]

			}
		
		var ctx1 = document.getElementById("canvas1").getContext("2d");
		window.myLine = new Chart(ctx1).Line(lineChartData1, {
			responsive: true
		});
		var ctx2 = document.getElementById("canvas2").getContext("2d");
		window.myLine = new Chart(ctx2).Line(lineChartData2, {
			responsive: true
		});
		var ctx3 = document.getElementById("canvas3").getContext("2d");
		window.myLine = new Chart(ctx3).Line(lineChartData3, {
			responsive: true
		});
	}})
	

}
</script>

</head>
<body>
<br />
<br />
<div align="center"><font size="32" >我们的成绩</font></div>

<br />

		<div style="position: absolute;top: 100px;left: 50px;height: 450px;width: 30%;">
			<div>
				<canvas id="canvas1" height="450" width="600"></canvas><br /><br /><br />
				<font size="6" >注册总人数：<font size="6" color="red">${regNumAll}</font> 人</font>
				<br /><br />
				<font size="6" >月注册人数：<font size="6" color="red">${regNumM}</font> 人</font>
				<br /><br />
				<font size="6" >周注册人数：<font size="6" color="red">${regNumW}</font> 人</font>
			</div>
		</div>
		<div style="position: absolute;top: 100px;left: 500px;height: 450px;width: 30%;" >
			<div>
				<canvas id="canvas2" height="450" width="600"></canvas><br /><br /><br />
				<font size="6" >充值总人数：<font size="6" color="red">${rechargeNumAll}</font> 人</font>
				<br /><br />
				<font size="6" >月充值人数：<font size="6" color="red">${rechargeNumM}</font> 人</font>
				<br /><br />
				<font size="6" >周充值人数：<font size="6" color="red">${rechargeNumW}</font> 人</font>
			</div>
		</div>
		<div style="position: absolute;top: 100px;left: 950px;height: 450px;width: 30%;">
			<div>
				<canvas id="canvas3" height="450" width="600"></canvas><br /><br /><br />
				<font size="6" >充值总金额：<font size="6" color="red">${rechargeMonAll}</font> 元</font>
				<br /><br />
				<font size="6" >月充值金额：<font size="6" color="red">${rechargeMonM}</font> 元</font>
				<br /><br />
				<font size="6" >周充值金额：<font size="6" color="red">${rechargeMonW}</font> 元</font>
			</div>
		</div>
</body>
</html>