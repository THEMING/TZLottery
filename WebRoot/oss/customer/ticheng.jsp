<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<html>
	<head>
		<title>提成管理</title>
		<meta name="heading" content="提成管理" />
		<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
  		<script src="../skin/01/js/lottery.js" type=text/javascript></script>
		<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<script type="text/javascript">
			$(function() {
				$("#tcp_ticheng").click(function(){
					$("#xianshi").toggle();
				});
			});
		</script>
	</head>
	<body>
		<div style="margin: 10px; width: 600px;">
			<form action="tichengjisuan.htm" method="post" >
				<table align="center">
					<tr>
						<td colspan="3" align="center"><label>提成计算</label>
						</td>
					</tr>
					<tr>
						<td>
							出票商：
		   							<s:select list="chupiao1" name="chupiao" ></s:select>
						</td>
						<td>
							日期：
			                <input type="text" name="stime" id="beginTime" class="input_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>&nbsp;至
							<input type="text" name="etime" id="endTime" class="input_date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						</td>
						<td>
							<input type="submit"  value="计算" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin: 10px;  width: 500px;">
			<table width="500px;" align="center">
				<tr>
					<td width="25%">出票商:</td>
					<td width="25%"><s:property value="chupiao" /></td>
					<td width="25%">总提成:</td>
					<td width="25%"><s:property value="sum"/> 元</td>
					<td width="25%"><input type="submit" value="详细信息" id="tcp_ticheng" onclick="ticheng();"/></td>
				</tr>
			</table>
		</div>
		<div style="margin: 10px;  width: 400px; display: none;" id="xianshi">
			<table width="400px;" align="center">
				<tr>
					<td></td>
					<td>彩种</td>
					<td>提成</td>
					<td>比例</td>
					<td>销售额</td>
				</tr>
				<s:iterator value="list" id="li">
					<tr>
						<td></td>
						<td>${li[1] }</td>
						<td>${li[0] } 元</td>
						<td>${li[2] } 元</td>
						<td>${li[3] } 元</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>