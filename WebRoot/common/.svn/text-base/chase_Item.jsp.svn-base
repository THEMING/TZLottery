<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_追号_方案详情</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/buy_lottery_detail.css" rel="stylesheet" type="text/css" />

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
		var type = "${chase.type}";
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
	<h3><strong>方案详情</strong></h3>
	<div class="plan_detail">
		
		<div class="buy_lottery_detail_tab">
			<table class="buy_lottery_table">
				<caption style="padding-left:20px">
					
					<div class="plan_detail_title">
						
						<h1 id="lotteryLogo"> <img src="../images/lottery/pic_ssq.jpg" width="60" height"60" /><span style="margin-left:15px">${item.lotteryType }</span></h1>
						<strong class="term">购彩方式：追号</strong>
						<strong class="term">第<span class = "red">${item.termNo }</span>期</strong>
					</div>
				</caption>
				
				<tr>
				 	<th class="buy_lottery_table_grid">方案编号</th>
                    <td class="buy_lottery_table_grid">${item.plan.numberNo } <s:if test="item.plan.isZh=='追加'">[追加]</s:if></td>
					<th class="buy_lottery_table_grid">发起人</th>
					<td class="buy_lottery_table_grid"><strong>${item.customer.nickName }　</strong></td>
					<th class="buy_lottery_table_grid">发起时间</th>
					<td class="buy_lottery_table_grid"><s:date name="item.plan.buildTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
					
				</tr>
				<tr>
				   <th class="buy_lottery_table_grid">出票状态</th>
                   <td class="buy_lottery_table_grid"> ${item.order.status } </td>
					<th  class="buy_lottery_table_grid">总金额</th>
                    <td  class="buy_lottery_table_grid"><strong class="red">${item.money }</strong>元 </td>
					 <th class="buy_lottery_table_grid">追号时间</th>
                     <td class="buy_lottery_table_grid"><s:date name="item.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			   <tr>
			   		<th class="buy_lottery_table_grid">中奖状态</th>
					<td class="buy_lottery_table_grid"><strong class="red">${item.order.orderResult }</strong></td>
					<th class="buy_lottery_table_grid">中奖金额</th>
					<td class="buy_lottery_table_grid"><strong class="red">${item.order.winTaxMoney }</strong>元 </td>
			  		<th class="buy_lottery_table_grid">倍数</th>
                    <td class="buy_lottery_table_grid">${item.multiple }</td>
               </tr>
               
			   <tr>
                 	<th class="buy_lottery_table_grid">开奖号码</th>
                    <td class="buy_lottery_table_grid" colspan="5" class="buy_lottery_table_grid"> ${item.order.term.result }</td>
               </tr>
				<tr>
                     <th class="buy_lottery_table_grid" >方案内容</th>
                     <td class="buy_lottery_table_grid" colspan="5" >
                     <s:iterator id="rs" value="item.plan.items" status="st">
                     	<p>${rs.content }　[共${rs.betCount}注　 每注:${rs.oneMoney}元　${rs.playType.nick_ne }]</p>
                     </s:iterator>
                     </td>
                 </tr>
                   <tr>
                       <th class="buy_lottery_table_grid">中奖明细</th>
                       <td class="buy_lottery_table_grid" colspan="5">
                     	 <s:iterator id="rs" value="item.order.orderWinDescribes">
                   		<div>奖级：${rs.prizeLevel.name }  中奖金额：${rs.money } 中奖注数：${rs.number}</div>
               			 </s:iterator>
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
