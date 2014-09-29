<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我发起的合买-一彩票</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
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
	
	function backout(id, ratio, status) {
		if(ratio >= 0.5) {
			alert("已经有一半或一半以上的份数被购买，不能撤单!");
			return;
		}
		
		if(status == "已撤单") {
			alert("不能撤单,已撤单!");
			return;
		}
		
		if(status == "已流产") {
			alert("不能撤单,已流产!");
			return;
		}
		
		this.location.href = "/customer/MyGroupBuyInfo.htm?action=backout&id=" + id;
	}
</script>
</head>
<body>
<div>
<div id="material_panel" class="tab_box">	
	<form action="#" method="post">
 		<div class="zjmx_title">
			<label>彩种：</label>
			<s:select list="typeList" id="type" name="type"></s:select>
			<label>状态：</label>
			<select name="status" id="status">
				<s:if test="status.length()>0">
					<option value="${status}" selected="selected">${status}</option>
				</s:if>
	            <option value="" >全部</option>
	            <s:iterator id="statu" value="statusList" >
					<option value="${statu}" >${statu}</option>
				</s:iterator>
			</select> 
			<label>日期：</label>
			<input type="text" name="beginTime" id="beginTime" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>
			<label>　至：</label>
			<input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
			<input type="submit" value="确 定" />
		</div>
		
		<table class="customer_table" style="width:100%">
			<tr>
				<td class="title" >彩种</td>
				<td class="title" >期号</td>
				<td class="title" >交易时间</td>
				<td class="title" >方案总额</td>
				<td class="title" >认购份数/保底份数</td>
				<td class="title" >投入金额</td>
				<td class="title" >合买状态</td>
				<td class="title" >中奖状态</td>
				<td class="title" >方案进度</td>
				<td class="title" >中奖金额</td>
				<td class="title" >方案号</td>
				<td class="title" ></td>
          	</tr>
			<s:iterator id="rs" value="page.result" >
				<tr>
					<td class="center">${rs.term.type }</td>
					<c:choose>
						<c:when test="${rs.term.termNo == '999999'}">
							<td class="center">------</td>
						</c:when>
						<c:when test="${rs.term.termNo == '888888'}">
							<td class="center">------</td>
						</c:when>
						<c:otherwise>
							<td class="center">${rs.term.termNo}</td>
						</c:otherwise>
					</c:choose>	
					<td class="center"><s:date name="#rs.createTimer" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="center">￥${rs.sumMoney }</td>
					<td class="center">${rs.buyPart }/${rs.insurePart }</td>
					<td class="center">${rs.buyMoney}</td>
					<td class="center">${rs.communityType }</td>
					<td class="center">
						<c:if test="${rs.communityType=='已流产' || rs.communityType=='已撤单'}">未出票</c:if>
						<c:if test="${rs.communityType!='已流产' && rs.communityType!='已撤单'}">${rs.order.orderResult }</c:if>
					</td>
					<td class="center"><fmt:formatNumber type="percent" value="${rs.okPart/rs.totalPart}"  minFractionDigits="2"/>
					</td>
					<td class="center">${rs.order.winTaxMoney }</td>
					<td class="center"><a target="_blank" href="MyGroupBuyInfo.htm?action=detail&numberNo=${rs.plan.numberNo }">${rs.plan.numberNo }</a></td>
					<!-- <td class="center"><a href="javascript:void(0)" onclick="backout(${rs.id}, ${rs.okPart/rs.totalPart}, '${rs.communityType}')">撤单</a></td> -->
				</tr>
			</s:iterator>
		</table>
		<div><jsp:include page="../util/page.jsp"></jsp:include></div>
	</form>
</div>
</div>  
</body>
</html>
