<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的追号-一彩票</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="chart/favicon.ico" /> 
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script src="../js/util/tab.js"></script>
<script>
	$(document).ready(function()
		{
			iframeResize();
		}
	);
	
	function iframeResize() {
		var obj = parent.document.getElementById("cur_content_frame");
		obj.height = $("#material_panel").height() + 20;
	}
</script>
</head>
<body>
<div>
<div id="material_panel" class="tab_box">
	<form action="#" method="post">
		<div class="zjmx_title">
			<label>彩种：</label>
			<s:select list="types" id="f_type" name="f_type"></s:select>
			<label>状态：</label>
			<select name="f_statu" id="f_statu">
				<s:if test="f_statu.length()>0">
					<option value="${f_statu}" selected="selected">${f_statu}</option>
				</s:if>
				<option value="">全部</option>
				<s:iterator id="rs" value="status" >
					<option value="${rs}" >${rs}</option>
				</s:iterator>
			</select> 
			<label>日期：</label>
			<input type="text" name="f_stime" id="f_stime" value='<s:date name="f_stime" id="f_stime" format="yyyy-MM-dd HH:mm:ss"/>'  onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="f_etime" id="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
			<input type="submit" class="btn_ok" value="确 定" />
		</div>
        <table class="customer_table" style="width:100%">
			<tr>
				<td class="title">彩种</td>
				<td class="title">投注时间</td>
				<td class="title">方案金额</td>
				<td class="title">期数</td>
				<td class="title">追号状态</td>
				<td class="title">中奖状态</td>
				<td class="title"></td>
			</tr>
			<s:iterator id="rs" value="page.result" status="st">
				<tr>
					<td class="center">${rs.lotteryType}</td>
					<td class="center"><s:date name="#rs.plan.buildTime" format="yyyy-MM-dd HH:mm"/></td>
					<td class="center">${rs.money}</td>
					<td class="center">${rs.oktermNum}/${rs.termNum}</td>
					<td class="center">${rs.status}</td>
					<td class="center">
						<s:if test="#rs.winprize">已中奖</s:if>
						<s:else>未中奖</s:else>
					</td>
					<td class="center"><a target="_blank" href="cusChasesaleinfo.htm?chaseId=${rs.id }">追号详情</a></td>
             	</tr>
             </s:iterator>
         </table>
		<div ><jsp:include page="../util/page.jsp"></jsp:include></div>
	</form>                
</div>
</div>  
</body>
</html>

