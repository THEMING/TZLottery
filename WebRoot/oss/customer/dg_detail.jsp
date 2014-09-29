<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案详情 -- 代购</title>
<link href="../../styles/user.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div >
    <div class="bd">
   	  <div class="bd_list">
        	<h3><strong>方案详情</strong></h3>
          <div class="faxq_cont">
          	<div class="faxq_title">
          	 <strong> <em>${order.type }</em> 　　　 第<em>${order.term.termNo }</em>期</strong>
                              本期开始时间：<s:date name="order.term.startSaleTime" format="yyyy-MM-dd HH:mm:ss"/>       　　　　认购截止时间：<s:date name="order.term.stopSaleTime" format="yyyy-MM-dd HH:mm:ss"/>
            </div>
<div class="faxq_tab">
                  <table>
                      <tr>
                          <th>方案号</th>
                          <td colspan="5">${order.plan.numberNo }</td>
                      </tr>
					   <tr>
                            <th>用户名</th>
                          <td >
                          	<strong>${order.customer.nickName }　</strong>
                          </td>
                          <th>购买类型</th>
                          <td>代购</td>
                          <th>倍数</th>
                          <td>${order.multiple } </td>
                      </tr>
					  <tr>
                            <th>出票状态</th>
                          <td >
                          	<strong>${order.status }　</strong>
                          </td>
                          <th>开奖状态</th>
                          <td>${order.orderResult }</td>
                          <th>注单付款情况</th>
                          <td>${order.payStatus } </td>
                      </tr>
					   <tr>
                            <th>方案金额</th>
                          <td >
                          	<strong>${order.plan.money }</strong>
                          </td>
                          <th>出票金额</th>
                          <td>${order.outAmount }</td>
                          <th>税后金额</th>
                          <td>${order.winTaxMoney }</td>
                      </tr>
					   <tr>
					    <th>中奖号码</th>
                          <td>
                          	${order.term.result}
                          </td>
                            <th>出票接口</th>
                          <td >
                          	${order.ticketThirdName }
                          </td>
                      </tr>
					   <tr>
                          <th>方案内容</th>
                          <td colspan="5">
                          <s:iterator id="rs" value="order.plan.items">
                          	<p>选号方式：${rs.playType.nick_ne }    号码：${rs.content }   注数：${rs.betCount} 单注金额：${rs.oneMoney}</p>
                          </s:iterator>
                          </td>
                          
                      </tr>
					   <tr>
                          <th>中奖描述：</th>
                          <td colspan="5">
                         <s:iterator id="rs" value="order.orderWinDescribes">
                          	<p>奖级：${rs.prizeLevel.name }    中奖金额：${rs.money }    中奖注数：${rs.number}</p>
                          </s:iterator>
                          </td>
                      </tr>
                  </table>
              </div>
          </div>
        </div>
    </div>
</div>
<div class="ft"></div>
</body>
</html>
