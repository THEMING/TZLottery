<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>七乐彩开奖历史_开奖公告-一彩票</title>
<meta name="keywords" content="七乐彩开奖,七乐彩开奖结果,七乐彩开奖号码,七乐彩开奖公告"/>
<meta name="description" content="七乐彩历史开奖公告频道为广大彩民朋友提供一个查阅近期七乐彩开奖结果,七乐彩开奖号码,七乐彩开奖公告的便捷直观的查询平台,方便用户兑阅七乐彩彩票中奖情况,以及其他更多七乐彩开奖相关信息。"/>
<link href="../css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />

<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/common.js"></script>
</head>
<body>
<!-- head -->
<%@include  file="../head.jsp"%>

<div class = "container">
	<div class="prize_main">
	
        <%@include  file="./left_navi_detail.jsp"%>
        
		<div class="right_box_history">
			<h3>一彩票首页 &gt; 开奖公告 &gt; 福利彩票 &gt; 七乐彩开奖历史</h3>
			<div class="right_box_tab">
				<div class="right_box_cont">
					<form action="qlcindex.htm">
						<table class= "prize_detail_tab" cellSpacing=0 cellPadding=0>
							<tr class="tit">
	                        	<td rowspan="2">期数</td>
	                            <td rowspan="2">开奖日期</td>
	                            <td rowspan="2">中奖号码</td>
	                            <td rowspan="2">销售额</td>
	                            <td colspan="2">一等奖</td>
	                            <td colspan="2">二等奖</td>
								<td colspan="2">三等奖</td>
	                            <td rowspan="2">详情</td>
	                            <td rowspan="2">走势</td>
	                        </tr>
	                    	<tr class="tit">
		                    	<td>注数</td>
		                    	<td>金额</td>
								<td>注数</td>
		                    	<td>金额</td>
		                    	<td>注数</td>
		                    	<td>金额</td>
	                   	 	</tr>
							
							
							<s:iterator value="page.result" id="term">
	                   	  		<tr>
		                    		<td>${termNo}</td>
		                    		<td><s:date name="openPrizeTime" format="yyyy-MM-dd"/></td>
		                    		<td>
			                          	<ul>
		                                	<li class="red-ball">${fn:substring(result,0,2)}</li>
		                                    <li class="red-ball">${fn:substring(result,3,5)}</li>
		                                    <li class="red-ball">${fn:substring(result,6,8)}</li>
		                                    <li class="red-ball">${fn:substring(result,9,11)}</li>
		                                    <li class="red-ball">${fn:substring(result,12,14)}</li>
		                                    <li class="red-ball">${fn:substring(result,15,17)}</li>
		                                    <li class="red-ball">${fn:substring(result,18,20)}</li>
		                                    <li class="blue-ball">${fn:substring(result,21,23)}</li>
			                            </ul>
		                          	</td>
			                    	<td><fmt:formatNumber type="number" value="${totalSale}" pattern="###,###,##0.00"/>元</td>
			                    	<td>${prizeLevels[0].betNum}</td>
			                    	<td><fmt:formatNumber type="number" value="${prizeLevels[0].prize}" pattern="###,###,##0.00"/>元</td>
			                    	<td>${prizeLevels[1].betNum}</td>
			                    	<td><fmt:formatNumber type="number" value="${prizeLevels[1].prize}" pattern="###,###,##0.00"/>元</td>
									<td>${prizeLevels[2].betNum}</td>
			                    	<td><fmt:formatNumber type="number" value="${prizeLevels[2].prize}" pattern="###,###,##0.00"/>元</td>
			                    	<td><a href="/prizedetail/qlc_${termNo}.htm" target="_blank"><img style="border:0px" src="../images/prizedetail/magnifier.png"/></a></td>
									<td><a href="/prizedetail/qlc/" target="_blank"><img style="border:0px" src="../images/prizedetail/trend.png" /></a></td>
								</tr>
                   	  		</s:iterator>
						</table>
						<div class="turn_page">
							<jsp:include page="../util/page.jsp"></jsp:include>
						</div>
					</form>       		
            	</div>
            </div>
		</div>
	</div>
    
	<div class = "clear"></div>
    <%@include  file="/foot.jsp"%>
</div>
</body>
</html>