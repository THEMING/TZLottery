<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>369竞彩_开奖公告_福彩3D开奖历史</title>
<meta name="keywords" content="福彩3D开奖,福彩3D开奖结果,福彩3D开奖号码,福彩3D开奖公告"/>
<meta name="description" content="福彩3D历史开奖公告频道为广大彩民朋友提供一个查阅近期福彩3D开奖结果,福彩3D开奖号码,福彩3D开奖公告的便捷直观的查询平台,方便用户兑阅福彩3D彩票中奖情况,以及其他更多福彩3D开奖相关信息。"/>
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
			<h3>一彩票首页 &gt; 开奖公告 &gt; 福利彩票 &gt; 福彩3D开奖历史</h3>
			<div class="right_box_tab">
				<div class="right_box_cont">
					<form action="3dindex.htm">
						<table class= "prize_detail_tab" cellSpacing=0 cellPadding=0>
							<tr class="tit">
	                        	<td rowspan="2">期数</td>
	                            <td rowspan="2">开奖日期</td>
	                            <td rowspan="2">中奖号码</td>
	                            <td rowspan="2">试机号</td>
	                            <td rowspan="2">销售额</td>
	                            <td colspan="2">直选</td>
	                            <td colspan="2">组三</td>
	                            <td colspan="2">组六</td>
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
		                                	<li class="red-ball">${fn:substring(result,0,1)}</li>
		                                    <li class="red-ball">${fn:substring(result,2,3)}</li>
		                                    <li class="red-ball">${fn:substring(result,4,5)}</li>
			                            </ul>
		                          	</td>
			                        <td>${orderResult}${prizeLevels[1].name}</td>
			                    	<td><fmt:formatNumber type="number" value="${totalSale}" pattern="###,###,##0.00"/>元</td>
		                    	 	<td>${prizeLevels[0].betNum}</td>
				                   	<td><fmt:formatNumber type="number" value="${prizeLevels[0].prize}" pattern="###,###,##0.00"/>元</td>
		                    	  	<s:if test="prizeLevels[1].name=='组三'">
				                    	<td>${prizeLevels[1].betNum}</td>
				                    	<td><fmt:formatNumber type="number" value="${prizeLevels[1].prize}" pattern="###,###,##0.00"/>元</td>
				                    	<td>0</td>
				                    	<td>0.00元</td>
		                    	  	</s:if>
		                    	   	<s:else>
				                    	<td>0</td>
				                    	<td>0.00元</td>
				                    	<td>${prizeLevels[1].betNum}</td>
				                    	<td><fmt:formatNumber type="number" value="${prizeLevels[1].prize}" pattern="###,###,##0.00"/>元</td>
		                    	   	</s:else>
		                    	 	<td><a href="/prizedetail/3d_${termNo}.htm" target="_blank"><img style="border:0px" src="../images/prizedetail/magnifier.png"/></a></td>
									<td><a href="/direction/fbt.htm?tt=js&type=3d" target="_blank"><img style="border:0px" src="../images/prizedetail/trend.png" /></a></td>
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
