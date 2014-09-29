<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代购信息-一彩票</title>
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
</script>
</head>
<body>
<div>
	<div id="material_panel" class="tab_box">
	<form action="#" method="post">
    	<div class="zjmx_title">
			<label>彩种：</label>
			<s:select list="typeList" id="type" name="type"></s:select>
			<label>起始日期：</label>
            <input type="text" name="beginTime" id="beginTime" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/>" class="input_date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>
            <label>终止日期：</label>
            <input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" class="input_date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
			<input type="submit" class="btn_ok" value="确 定" />
		</div>
        
        <table class="customer_table" style="width:100%">
			<tr>
				<td class="title">彩种</td>
				<td class="title">方案类型</td>
				<td class="title">期号</td>
				<td class="title">发起人</td>
				<td class="title">中奖时间</td>
				<td class="title">消费金额</td>
				<td class="title">中奖金额</td>
				<td class="title">方案号</td>
				<td class="title"></td>
			</tr>
			<s:iterator id="rs" value="page.result" status="st">
				<tr>
					<td class="center"><s:property value="type"/></td>
					<td class="center"><s:property value="playStatus"/></td>
					<td class="center"><s:property value="term"/></td>
					<td class="center"><s:property value="useName"/></td>
					<td class="center"><s:date name="winTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="red center">￥<s:property value="money"/></td>
					<td class="center"><span class="red">￥<s:property value="winMoney"/></span></td>
					<td class="center">
						<span class="red">
							<s:if test="playStatus.toString().equals('合买')">
								<a target="_blank" href="MyGroupBuyInfo.htm?action=detail&numberNo=${rs.planNo }">${rs.planNo }</a>
							</s:if>
							<s:if test="playStatus.toString().equals('追号')">
								<a target="_blank" href="cusChasesaleinfo.htm?action=list&numberNo=${rs.planNo }">${rs.planNo }</a>
							</s:if>
							<s:if test="playStatus.toString().equals('代购')">
								<a target="_blank" href="MyBuyLotteryInfo.htm?action=detail&numberNo=${rs.planNo}">${rs.planNo}</a>
							</s:if>
						</span>
					</td>
					<td class="center">
						<s:if test="playStatus.toString().equals('合买')">
							<a target="_blank" href="MyGroupBuyInfo.htm?action=detail&numberNo=${rs.planNo }">明细</a>
						</s:if>
						<s:if test="playStatus.toString().equals('追号')">
							<a target="_blank" href="cusChasesaleinfo.htm?action=list&numberNo=${rs.planNo }">明细</a>
						</s:if>
						<s:if test="playStatus.toString().equals('代购')">
							<a target="_blank" href="MyBuyLotteryInfo.htm?action=detail&numberNo=${rs.planNo}">明细</a>
						</s:if>	
     				</td>
   				</tr>
			</s:iterator>
		</table>
		<div class="page"><jsp:include page="../util/page.jsp"></jsp:include></div>
	</form>
</div>
</div>  
</body>
</html>