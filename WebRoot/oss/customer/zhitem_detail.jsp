<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案详情 -- 追号</title>
<link href="../../styles/user.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div >
    <div class="bd">
   	  <div class="bd_list">
        	<h3><strong>方案详情</strong></h3>
          <div class="faxq_cont">
          	<div class="faxq_title">
            </div>
<div class="faxq_tab">
                  <table>
                      <tr>
                          <th>方案期数</th>
                          <td> ${item.termNo }</td>
                          <th>彩种</th>
                          <td> ${item.lotteryType } </td>
                           <th>发起人</th>
                          <td >
                          	<strong>${item.customer.nickName }　</strong>
                          </td>
                          
                      </tr>
                        <tr>
                          <th>发起时间</th>
                          <td><s:date name="item.plan.buildTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
                          <th>追号时间</th>
                          <td><s:date name="item.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                          <th>总金额</th>
                          <td><strong class="red">${item.money }</strong>元 </td>
                      </tr>
                        <tr>
                           <th>倍数</th>
                           <td>${item.multiple } </td>
                          <th>中奖情况</th>
                          <td ><strong class="red">${item.order.orderResult }</strong></td>
                          <th>方案状态</th>
                          <td>${item.status }</td>
                      </tr>
                      <tr>
                      <th>中奖号码</th>
                          <td colspan="5">${item.order.term.result }</td>
                      </tr>
                        <tr>
                          <th>方案内容</th>
                          <td colspan="5">
                          <s:iterator id="rs" value="item.plan.items" status="st">
                          	<p> 选号方式：${rs.playType.nick_ne }  号码：${rs.content } 注数：${rs.betCount}</p>
                          </s:iterator>
                          </td>
                          
                      </tr>
                      <tr>
                          <th>中奖明细</th>
                          <td colspan="5">
                         <s:iterator id="rs" value="item.order.orderWinDescribes">
		                    <div>奖级：${rs.prizeLevel.name }  中奖金额：${rs.money } 中奖注数：${rs.number}</div>
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