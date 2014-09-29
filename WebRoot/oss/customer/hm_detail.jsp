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
          	 <strong> <em>${community.term.type }</em> 　　　 第<em>${community.term.termNo }</em>期</strong>
                        本期开始时间：<s:date name="community.term.startSaleTime" format="yyyy-MM-dd HH:mm:ss"/>       　　　　合买截止时间：<s:date name="community.term.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm:ss"/>
            </div>
<div class="faxq_tab">
                  <table>
                      <tr>
                          <th>方案号</th>
                          <td>${community.plan.numberNo }</td>
						   <th>完成进度</th>
                          <td> <fmt:formatNumber type="percent" value="${community.okPart/community.totalPart}"  minFractionDigits="2"/> </td>
                           <th>完成份数</th>
                          <td> ${community.okPart}/${community.totalPart}</td>
                         
                          
                      </tr>
					   <tr>
                            <th>用户名</th>
                          <td >
                          	<strong>${community.customer.nickName }　</strong>
                          </td>
                          <th>购买类型</th>
                          <td>合买</td>
                          <th>倍数</th>
                          <td>${community.multiple } </td>
                      </tr>
					  <tr>
                            <th>状态</th>
                          <td >
                          	<strong>${community.communityType}</strong>
                          </td>
						   <th>出票状态</th>
                          <td >
                          	<strong>${community.order.status }</strong>
                          </td>
                          <th>开奖状态</th>
                          <td>${community.order.orderResult }</td>
                      </tr>
					   <tr>
                            <th>方案金额</th>
                          <td >
                          	<strong>${communityr.plan.money }</strong>
                          </td>
                          <th>出票金额</th>
                          <td>${community.order.outAmount }</td>
                          <th>中奖金额</th>
                          <td>${community.order.winTaxMoney }</td>
                      </tr>
					   <tr>
					    <th>中奖号码</th>
                          <td>
                          	${community.term.result}
                          </td>
                            <th>出票接口</th>
                          <td >
                          	${community.order.ticketThirdName }
                          </td>
						   <th>生成订单时间</th>
                          <td ><s:date name="community.order.buildTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
					   <tr>
                          <th>方案内容</th>
                          <td colspan="5">
                          <s:iterator id="rs" value="community.plan.items">
                          	<p>选号方式：${rs.playType.nick_ne }  号码：${rs.content } 注数：${rs.betCount}  单注金额：${rs.oneMoney}元</p>
                          </s:iterator>
                          </td>
                          
                      </tr>
					   <tr>
                          <th>中奖描述</th>
                          <td colspan="5">
                       <s:iterator id="rs" value="community.order.orderWinDescribes">
                          	<p>奖级：${rs.prizeLevel.name }    中奖金额：${rs.money }    中奖注数：${rs.number}</p>
                          </s:iterator>
                          </td>
                      </tr>
                  </table>
              </div>
			  <div class="faxq_table faxq_table_a">
             	<h4>参与合买</h4>
                <div>
           		  <table>
                	<tr>
                	  <th>序号</th>
                	  <th>参与者</th>
                    	<th>购买份数</th>
                        <th>购买金额</th>
                        <th>认购时间</th>
                        <th>中奖金额</th>
                    </tr>
                   <s:iterator id="rs" value="partList" status="st">
                    <s:if test="#st.index%2==0">
                	<tr>
                	  <td><s:property value="#st.index+1"/></td>
                	  <td>${rs.customer.nickName }</td>
                	  <td>${rs.partNum }</td>
                	  <td>${rs.money} </td>
                	  <td><s:date name="rs.joinTime" format="yyyy-MM-dd HH:mm"/></td>
                	  <td>${rs.winTaxMoney}</td>
              	  </tr>
              	  </s:if>
              	  <s:else>
              	   <tr class="bg_gray">
                	  <td><s:property value="#st.index+1"/></td>
                	  <td>${rs.customer.nickName }</td>
                	  <td>${rs.partNum }</td>
                	  <td>${rs.money} </td>
                	  <td><s:date name="rs.joinTime" format="yyyy-MM-dd HH:mm"/></td>
                	  <td>${rs.winTaxMoney}</td>
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
