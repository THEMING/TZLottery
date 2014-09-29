<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户中心 -- 代购查询</title>
	<link href="../css/default.css" rel="stylesheet" type="text/css" />
	<link href="../oss/styles/user.css" rel="stylesheet" type="text/css" />
	<script src="../js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="../js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script>
	$(document).ready(function()
		{
			var obj = parent.document.getElementById("mainFrame");
			obj.height = $("#bd").height() + 20;
		}
	);
</script>
</head>
<body>
	<div id="bd" class="bd">
		<form  action="#" method="post">
			<div class="user_main"> 
				<h3><strong>${userType}-购彩查询</strong></h3>
				<div class="user_bor">
					<p class="u_serch">
						<label>彩种：</label> <s:select list="lotteryTypes" name="lotteryType" id="lotteryType" />
						<label> ｜ 购彩方式:</label>
						<select name="buyType" id="buyType">
							<s:if test="buyType.length()>0">
								<option value="${buyType}" selected="selected">${buyType}</option>
							</s:if>
							<option value="全部" >全部</option>
							<option value="代购" >代购</option>
                           	<option value="合买" >合买</option>
                           	<option value="追号" >追号</option>
                       	</select>
                       	<label> ｜出票状态: </label>
                       	<s:select list="statuses" name="status" headerValue="全部" headerKey=""/>
						<label> ｜购彩时间：</label>
						<input type="text" name="buyStartTime" id="buyStartTime" value="<s:date name="buyStartTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
						<input type="text" name="buyEndTime" id="buyEndTime" value="<s:date name="buyEndTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						<input type="submit" value="查询" onclick=""/>
					</p>
					<!--  <p class="u_serch">
                           ｜方案总额范围:
                        <input type="text" name="f_Amount1" id="f_Amount1" value="${f_Amount1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text" name="f_Amount2" id="f_Amount2" value="${f_Amount2 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
                    </p> -->
                    
                    <div class="bg_w">
						<table>
							<tr>
								<th>用户名</th>
								<th>彩种</th>
                                <th>期次</th>
                                <th>发起人</th>
                                <th>购彩时间</th>
                                <th>方案总额</th>
                                <!-- <th>中奖状态</th>
                                <th>税前奖金</th>
                                <th>税后奖金</th> -->
                                <th>出票状态</th>
                                <th>类型</th>
                                <th>订单号</th>
                            </tr>
							<s:iterator id="rs" value="page.result">
								<tr>
								   <td>${rs.customer.nickName}</td>
									<td>${rs.type}</td>
									<td>${rs.term.termNo}</td>
									<td>
										<s:if test="#rs.community!=null">${rs.community.customer.nickName}</s:if>
										<s:else>${rs.customer.nickName}</s:else>
									</td>
									<td>
										<s:date name="#rs.buildTime" format="yyyy-MM-dd HH:mm"/>
									</td>
									<td>${rs.amount}</td>
									<!-- <td>${rs.orderResult}</td>
									<td>${rs.winMoney}</td>
									<td>${rs.winTaxMoney}</td> -->
									<td>${rs.status}</td>
									<td>
										<s:if test="#rs.community!=null">合买</s:if>
										<s:elseif test="#rs.chaseItem!=null">追号</s:elseif>
										<s:else>代购</s:else>
									</td>
									<td>
										<s:if test="#rs.community!=null">${rs.plan.numberNo}</s:if>
										<s:elseif test="#rs.chaseItem!=null">${rs.plan.numberNo}</s:elseif>
										<s:else>${rs.plan.numberNo}</s:else>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div style="float:right">
						<jsp:include page="../util/page.jsp"></jsp:include>
					</div>
					<div class="clear"></div>
					<p class="u_serch" style="color:blue">
						出票成功金额:<span>${successOutMoney}</span>元　
						出票失败金额:<span>${failOutMoney}</span>元　
						方案总数:<span>${totalPlanNum}</span>　
						成功票数:<span>${successPlanNum}</span>　
						<!-- 中奖方案数:<span id="zjplan">0</span>　
						税前中奖总额:<span id="shuiqian">0</span>元　
						税后中奖总额:<span id="shuihou">0</span>元　 -->
						购彩用户:<span>${customerNum}</span>人
					</p>
					
				</div>
			</div>
		</form>
	</div>
</body>

