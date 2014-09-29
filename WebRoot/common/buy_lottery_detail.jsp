<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_代购_方案详情</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/buy_lottery_detail.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
	
<script src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script type="text/javascript">
	function show_plan_detail()
	{
		if( $("#show_detail").attr("checked"))
			$("#show_plan_detail_dev").attr("class", "show");
		else
			$("#show_plan_detail_dev").attr("class", "hidden");
	}
	
	jQuery(document).ready(function() {
		var type = "${order.type}";
		//alert(type);
		if(type == "七星彩")
		{
			$("#lotteryLogo").html("<img src=\"../images/lottery/pic_qxc.jpg\" width=\"60\" height\"60\"/><span style=\"margin-left:15px\"> ${order.type}</span>");
		}
	});
	
</script>


</head>
<body>
<%@include file="/head.jsp"%>
<div class="box">
	<h3><strong>您的代购方案详情如下：</strong></h3>
	<div class="plan_detail">
		
		<div class="buy_lottery_detail_tab">
			<table class="buy_lottery_table">
				<caption style="padding-left:20px">
					
					<div class="plan_detail_title">
						
						<h1 id="lotteryLogo"> <img src="../images/lottery/pic_ssq.jpg" width="60" height"60" /><span style="margin-left:15px"> ${order.type}</span></h1>
						<c:choose>
						<c:when test="${order.type != '竞彩足球'}">
							<strong class="term">第<span class = "red">${order.term.termNo }</span>期</strong><strong class="term">本期开始时间：<span class="red"><s:date name="order.term.startSaleTime" format="yyyy-MM-dd HH:mm:ss"/></span></strong><strong class="term">认购截止时间：<span class="red"><s:date name="order.term.stopSaleTime" format="yyyy-MM-dd HH:mm:ss"/></span></strong>
						</c:when>
						
					</c:choose>
						
						 
					</div>
				</caption>
				
				<tr>
					<th class="buy_lottery_table_grid">方案编号</th>
					<td class="buy_lottery_table_grid">${order.plan.numberNo } <s:if test="order.plan.oneMoney==3"><strong class="red">[追加]</strong></s:if></td>
					<th class="buy_lottery_table_grid">购买人</th>
					<td class="buy_lottery_table_grid"><strong>${order.customer.nickName }</strong>
					</td>
					<th class="buy_lottery_table_grid">购买时间</th>
					<td class="buy_lottery_table_grid"><s:date name="order.buildTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
					
				</tr>
				<tr>
					<th class="buy_lottery_table_grid">购买方式</th>
					<td class="buy_lottery_table_grid">代购</td>
					<th class="buy_lottery_table_grid">倍数</th>
					<td class="buy_lottery_table_grid">${order.multiple }</td>
					<th class="buy_lottery_table_grid">总金额</th>
					<td class="buy_lottery_table_grid"><strong class="red">${order.amount }</strong>元 </td>
				</tr>
				<tr>
					<th class="buy_lottery_table_grid">出票状态</th>
					<td class="buy_lottery_table_grid"> ${order.status } </td>
					<th class="buy_lottery_table_grid">出票金额</th>
					<td class="buy_lottery_table_grid"><strong class="red">${order.outAmount }</strong>元</td>
					<th class="buy_lottery_table_grid">中奖情况</th>
					<td class="buy_lottery_table_grid"><strong class="red">${order.orderResult }</strong></td>
				</tr>
				<tr>
					<th class="buy_lottery_table_grid">开奖号码</th>
					<td class="buy_lottery_table_grid" colspan="5"> ${order.term.result }</td>
				</tr>
				<tr>
					<th class="buy_lottery_table_grid">中奖金额</th>
					<td class="buy_lottery_table_grid" colspan="5"><strong class="red"><s:property value="order.winTaxMoney"/></strong>元</td>
				</tr>
				
				<tr>
					<th class="buy_lottery_table_grid">中奖明细</th>
					<td class="buy_lottery_table_grid" colspan="5">
						<s:iterator id="rs" value="order.orderWinDescribes">
							<div>奖级：${rs.prizeLevel.name }  中奖金额：${rs.money } 中奖注数：${rs.number}</div>
						</s:iterator>
					</td>
				</tr>
				
				<tr>
					<th class="buy_lottery_table_grid">方案内容 <br/><input type="checkbox" id= "show_detail" checked="checked" onclick="show_plan_detail()" /> <label for="show_detail" style="color:#0000FF">显示/隐藏</label>
					</th>
					
					<td colspan="5" class="table_in_table">
						<div id="show_plan_detail_dev">
							<table class= "xh_detail_tab" cellspacing=0 cellpadding=0 width="100%">
								<tr class = tit>
									<td width="5%">序号</td>
									<td width="5%">类型</td>
									<td width="50%">内容 </td>
									<td width="5%">注数 </td>
									<td width="5%">金额 </td>
								</tr>
								
								<s:iterator id="rs" value="order.plan.items" status="st">
									<tr>
										<td><s:property value="#st.index+1"/></td>		            		
										<td>${rs.playType.nick_ne }</td>
										<!-- <td>${rs.content }</td>  -->
										<td>${rs.comments}</td>     			
										<td>${rs.betCount}</td>
										<td>￥ ${rs.betCount*rs.oneMoney}</td>
									</tr>
								</s:iterator>
								
							</table>
						</div>
					</td>
				</tr>
				
			</table>
		</div>
	</div>
</div>
<div class="clear"></div>
<%@include  file="/foot.jsp"%>
</body>
</html>
