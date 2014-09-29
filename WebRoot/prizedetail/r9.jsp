<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任选9场开奖历史_开奖公告-一彩票</title>
<meta name="keywords" content="任选9场开奖,任选9场开奖结果,任选9场开奖号码,任选9场开奖公告"/>
<meta name="description" content="任选9场历史开奖公告频道为广大彩民朋友提供一个查阅近期任选9场开奖结果,任选9场开奖号码,任选9场开奖公告的便捷直观的查询平台,方便用户兑阅任选9场彩票中奖情况,以及其他更多任选9场开奖相关信息。"/>
<link href="../css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css"/>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />

<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/common.js"></script>
<script type="text/javascript">
	function onclickTab(num)
	{
		if(num == 0)
		{
			alert("0");
			$("#navigate_category").show();
			$("#navigate_date").hide();	
		}
		else
		{
			alert("1");
			$("#navigate_category").hide();
			$("#navigate_date").show();
		}
	}
	function onclickSort(num)
	{
		if (num==0) //福彩
		{
			$("#welfare_lott").show();
			$("#sport_lott").hide();
			$("#football_lott").hide();
		}
		else if (num==1) //体彩
		{
			$("#welfare_lott").hide();
			$("#sport_lott").show();
			$("#football_lott").hide();
		}
		else if (num==2) //足彩
		{
			$("#welfare_lott").hide();
			$("#sport_lott").hide();
			$("#football_lott").show();
		}
		else
		{
		}		
	}
</script>
</head>

<body>
<!-- head -->
<%@include  file="../head.jsp"%>

<div class = "container">
	<div class="prize_main">  
        <%@include  file="./left_navi_his.jsp"%>
		<div class="right_box_history">
			<h3>一彩票首页 &gt; 开奖公告 &gt; 福利彩票 &gt; 任选9场开奖历史</h3>
			<div class="right_box_tab">
				<div class="right_box_cont">
					<form action="14sfcindex.htm">
							<table class= "prize_detail_tab" cellSpacing=0 cellPadding=0>
								<tr class="tit">
									<td rowspan="2">期数</td>
									<td rowspan="2">开奖日期</td>
									<td rowspan="2">中奖号码</td>
									<td colspan="1">中奖注数</td>		   
									<td rowspan="2">详情</td>
									<td rowspan="2">走势</td>
								</tr>
								<tr class="tit">
									<td>一等奖</td>
								</tr>
								<s:iterator value="page.result" id="term">
									<tr>
										<td>${termNo}</td>
										<td><s:date name="openPrizeTime" format="yyyy-MM-dd"/></td>
										<td width="208px">
											<ul>
												<li >${fn:substring(result,0,1)}</li>
                            					<li >${fn:substring(result,2,3)}</li>
                            					<li >${fn:substring(result,4,5)}</li>
                            					<li >${fn:substring(result,6,7)}</li>
                            					<li >${fn:substring(result,8,9)}</li>
                            					<li >${fn:substring(result,10,11)}</li>
                            					<li >${fn:substring(result,12,13)}</li>
                            					<li >${fn:substring(result,14,15)}</li>
                            					<li >${fn:substring(result,16,17)}</li>
                            					<li >${fn:substring(result,18,19)}</li>
                            					<li >${fn:substring(result,20,21)}</li>
                            					<li >${fn:substring(result,22,23)}</li>
                            					<li >${fn:substring(result,24,25)}</li>
                            					<li >${fn:substring(result,26,27)}</li>
											</ul>
										</td>
										<td>${prizeLevels[0].betNum}</td>
										<td><a href="/prizedetail/r9_${termNo}.htm" target="_blank"><img style="border:0px" src="../images/prizedetail/magnifier.png"/></a></td>
										<td><a href="/prizedetail/r9/" target="_blank"><img style="border:0px" src="../images/prizedetail/trend.png" /></a></td>
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
    
	<div class = "clear"></div>
    <%@include  file="/foot.jsp"%>
</div>
</body>
</html>
