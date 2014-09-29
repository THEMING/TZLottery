<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>软件注册 — 一彩票网</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="../css/common.css" type="text/css" />
	<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="chart/favicon.ico" /> 
	<script type="text/javascript" src="../js/lottery/common.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script src="../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function()
			{
				iframeResize();
			}
		);
	
		function iframeResize() {
			var obj = parent.document.getElementById("cur_content_frame");
			obj.height = $("#recommend_box").height() + 20;
			if(obj.height < 275) obj.height = 275;
		}
	</script>
</head>
<body>
<div id="recommend_box">
	<form action="/customer/MyRecommenders.htm" method="post">
		<div class="zjmx_title">
　            <label>被推荐人：</label>
            <input type="text" name="recommendName" id="recommendName" value="${recommendName}"/>
            <label style="margin-left:100px">日期：</label>
            <input type="text" name="startTime" id="startTime" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
     		<input type="submit" class="btn_ok" value="确 定" />
   		</div>
      	
        <table class="customer_table" style="margin-top:10px" width="100%">
			<tr>
				<td class="title">被推荐人</td>
				<td class="title">交易时间</td>
				<td class="title">交易金额</td>
				<td class="title">推荐级别</td>
				<td class="title">提成比例</td>
				<td class="title">可获提成</td>
			</tr>
            <s:iterator id="rs" value="page.result" >
           	<tr>
				<td class="center">${rs.wallet.customer.nickName}</td>
				<td class="center"><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="center">
	                <s:if test="inMoney>0">-${inMoney}</s:if>
	                <s:elseif test="outMoney>0">${outMoney}</s:elseif>
                </td>
                <c:if test="${rs.wallet.customer.superior == customer}">
                	<td class="center">一级推荐</td>
                	<td class="center">${customer.superRatio}</td>
                	<td class="center"><s:if test="inMoney>0">-${inMoney * customer.superRatio}</s:if>
	                <s:elseif test="outMoney>0">${outMoney * customer.superRatio}</s:elseif></td>
                </c:if>
	            <c:if test="${rs.wallet.customer.ssuperior == customer}">
	            	<td class="center">二级推荐</td>
	            	<td class="center">${customer.ssuperRatio}</td>
	            	<td class="center"><s:if test="inMoney>0">-${inMoney * customer.ssuperRatio}</s:if>
	                <s:elseif test="outMoney>0">${outMoney * customer.ssuperRatio}</s:elseif></td>
	            </c:if>
            </tr>
            </s:iterator>
        </table>
        <div class="page" style="margin-top:10px">
        	<p><div><jsp:include page="../util/page.jsp"></jsp:include></div></p>
            <p>当前页：一级推荐提成：<span class="red">￥${sumMoney1}</span>元，二级推荐提成：<span class="red">￥${sumMoney2}</span>元，总提成：<span class="red">￥${sumMoney1 + sumMoney2}</span>元</p>
            <p>总计 ：一级推荐提成：<span class="red">￥${totalSumMoney1}</span>元，二级推荐提成：<span class="red">￥${totalSumMoney2}</span>元，总提成：<span class="red">￥${totalSumMoney1 + totalSumMoney2}</span>元</p>
        </div>
	</form>
   	<div class="clear"></div>
</div>
</body>
</html>