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
		var type = "${chase.lotteryType}";
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
	<h3><strong>您的追号方案详情如下：</strong></h3>
	<div class="plan_detail">
		
		<div class="buy_lottery_detail_tab">
			<table class="buy_lottery_table">
				<caption style="padding-left:20px">
					
					<div class="plan_detail_title">
						<h1 id="lotteryLogo"> <img src="../images/lottery/pic_ssq.jpg" width="60" height"60" /><span style="margin-left:15px">${chase.lotteryType }</span></h1>
						<strong class="term">方案编号：${chase.plan.numberNo  }</strong>
					</div>
				</caption>
				
				<tr>
					<th class="buy_lottery_table_grid">发起人</th>
					<td class="buy_lottery_table_grid"><strong>${chase.customer.nickName }　</strong>
					</td>
					<th class="buy_lottery_table_grid">发起时间</th>
					<td class="buy_lottery_table_grid"><s:date name="chase.plan.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<th class="buy_lottery_table_grid">中奖情况</th>
					<td class="buy_lottery_table_grid"><strong class="red"><s:if test="chase.winprize">中奖</s:if><s:else>未中奖</s:else></strong>
					</td>
				</tr>
				<tr>
					<th class="buy_lottery_table_grid">总金额</th>
					<td class="buy_lottery_table_grid"><strong class="red">${chase.money }</strong>元 </td>
					<th class="buy_lottery_table_grid">进行状态</th>
					<td class="buy_lottery_table_grid"> 已追 ${chase.oktermNum } 期/共 ${chase.termNum } 期 </td>
					<th class="buy_lottery_table_grid">方案状态</th>
					<td class="buy_lottery_table_grid">${chase.status }</td>
				</tr>
			   <tr>
                    <th class="buy_lottery_table_grid">停止条件</th>
                 	 <td class="buy_lottery_table_grid" colspan="5"> 方案完成后停止
                    <s:if test="chase.stopMoney>'0.00'">方案中奖金额为：￥${chase.stopMoney }后停止</s:if>
                    </td>
               </tr>
			   <tr>
                    <th class="buy_lottery_table_grid">方案内容</th>
                   		<td class="buy_lottery_table_grid" colspan="5">
                          <s:iterator id="rs" value="chase.plan.items" status="st">
                          	<p> ${rs.content }　[共${rs.betCount}注　 每注:${rs.oneMoney}元　${rs.playType.nick_ne }]</p>
                          </s:iterator>
                         </td>
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
					<td class="buy_lottery_table_grid" colspan="6">
							追号方案各个期次查询
					</td>
				</tr>
				<tr>
					<td colspan="8" class="table_in_table">
						<div id="show_plan_detail_dev">
							<table class= "xh_detail_tab" cellSpacing=0 cellPadding=0 width="100%">
								<tr class = tit>
									<td>序号</td>
									<td>期号</td>
									<td>金额</td>
									<td>倍数</td>
									<td>状态</td>
									<td>中奖状态</td>
									<td>追号时间</td>
									<td>详情</td>
								</tr>
								
							   <s:iterator id="rs" value="page.result" status="st">
					             <s:if test="#st.index%2==0">
					         	<tr>
					         	  <td><s:property value="#st.index+1"/></td>
					         	  <td>${rs.termNo }</td>
					         	  <td>${rs.money }</td>
					         	  <td>${rs.multiple }</td>
					         	  <td>${rs.status }</td>
					         	  <td>${rs.order.orderResult }</td>
					         	  <td><s:date name="#rs.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					         	  <td><a href="cusChasesaleinfo.htm?action=info&itemId=${rs.id }" target="_blank">查看</a></td>
					       	  </tr>
					       	  </s:if>
					       	  <s:else>
					       	   <tr class="bg_gray">
					         	  <td><s:property value="#st.index+1"/></td>
					         	  <td>${rs.termNo }</td>
					         	  <td>${rs.money }</td>
					         	  <td>${rs.multiple }</td>
					         	  <td>${rs.status }</td>
					         	  <td>${rs.order.orderResult }</td>
					         	  <td><s:date name="#rs.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					         	  <td><a target="_blank" href="cusChasesaleinfo.htm?action=info&itemId=${rs.id }">查看</a></td>
					       	  </tr>
					       	  </s:else>
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
