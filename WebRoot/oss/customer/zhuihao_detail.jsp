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
                <div>
                       <strong> <em>${chase.lotteryType }</em> </strong>
              </div>
            </div>
<div class="faxq_tab">
                  <table>
                      <tr>
                          <th>追号期数</th>
                          <td> ${chase.termNum }</td>
                          <th>已追期数</th>
                          <td> ${chase.oktermNum } </td>
                          <th>方案状态</th>
                          <td>${chase.status }</td>
                      </tr>
                        <tr>
                          <th>发起时间</th>
                          <td><s:date name="chase.plan.buildTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
                          <th>总金额</th>
                          <td><strong class="red">${chase.money }</strong>元 </td>
                          <th>停追金额</th>
                          <td>${chase.stopMoney }</td>
                      </tr>
                        <tr>
                          <th>中奖情况</th>
                          <td colspan="5"><strong class="red"><s:if test="order.winprize">中奖</s:if><s:else>未中奖</s:else></strong></td>
                      </tr>
                        <tr>
                          <th>发起人</th>
                          <td colspan="5">
                          	<strong><a href="manageCustomer.aspx?action=view&name=${rs.customer.nickName }">${chase.customer.nickName }</a></strong>
                          </td>
                      </tr>
                        <tr>
                          <th>方案内容</th>
                          <td colspan="5">
                          <s:iterator id="rs" value="chase.plan.items" status="st">
                          	<p> 选号方式：${rs.playType.nick_ne }  号码：${rs.content } 注数：${rs.betCount}  单注金额：${rs.oneMoney}元</p>
                          </s:iterator>
                          </td>
                      </tr>
                  </table>
              </div>
              <div class="faxq_table faxq_table_a">
             	<h4>追号方案各期次汇总</h4>
                <div>
           		  <table>
                	<tr>
                	  <th>序号</th>
                	  <th>期号</th>
                    	<th>金额</th>
                        <th>倍数</th>
                        <th>状态</th>
                        <th>中奖状态</th>
                        <th>追号时间</th>
                        <th>详情</th>
                    </tr>
                    <s:iterator id="rs" value="page.result" status="st">
                    <s:if test="#st.index%2==0">
                	<tr>
                	  <td><s:property value="#st.index+1"/></td>
                	  <td>${rs.termNo }</td>
                	  <td>${rs.money}</td>
                	  <td>${rs.multiple }</td>
                	  <td>${rs.status }</td>
                	  <td>${rs.order.orderResult }</td>
                	  <td><s:date name="#rs.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                	  <td><a href="manageChaseItemQuery.aspx?action=detail&itemId=${rs.id}">查看</a></td>
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
                	   <td><a href="manageChaseItemQuery.aspx?action=detail&itemId=${rs.id}">查看</a></td>
              	  </tr>
              	  </s:else>
              	  </s:iterator>
          
                </table>
                </div>
             </div>
          </div>
        </div>
    </div>
</div>
<div class="ft"></div>
</body>
</html>
