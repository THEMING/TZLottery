<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xsc.lottery.entity.business.LotteryTerm"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票票网_开奖数据_彩票开奖信息</title>
<meta name="Keywords" content="彩票开奖数据,双色球开奖号码,彩票开奖 " />
<meta name="Description" content="一彩票票网开奖数据频道为您提供几十年的彩票开奖数据，双色球开奖、福彩3D开奖、体彩开奖、福彩开奖，竞彩开奖，开奖信息查询，兑奖号码查询，彩票开奖公告让你第一时间了解全国最新的彩票开奖结果。" />
<link href="../styles/user.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />

</head>
<body>
	<div class="wrap">
    	<%@include  file="/head.jsp"%>
        <div class="bd">
        	<div class="bd_list">
        		<h3><strong>开奖公告</strong></h3>
                <div class="bd_list_t">
               	  <h4 class="hover">全国彩种开奖</h4>
                  <h4>高频彩种开奖</h4>
                </div>
                <div class="c_l"></div>
               <%
               	Map<String,LotteryTerm>  allOpenTermMap= (Map<String,LotteryTerm>)request.getAttribute("allOpenTerm");
               %>
                <div class="kj_cont">
                	<table>
                    	<tr>
                        	<th>彩种</th>
                            <th>期号</th>
                            <th>开奖日期</th>
                            <th>开奖号码</th>
                            <th>开奖日</th>
                            <th>详情</th>
                            <th>走势</th>
                            <th>历史</th>
                            <th>在线购买</th>
                        </tr>
                        <tr>
                          <td colspan="9" class="kj_fc">福利彩票开奖信息</td>
                        </tr>
                        <tr>
                        	 <td><a href="/drawlott/ssq_<s:property value="allOpenTerm.get('ssq').termNo"/>.htm">双色球</a></td>
                             <td><s:property value="allOpenTerm.get('ssq').termNo"/>期</td>
                             <td><s:date name="allOpenTerm.get('ssq').openPrizeTime" format="yyyy-MM-dd"></s:date></td>
                          <td>
                             	<ul>
                             		<% 
                             			String[] openresultArray = allOpenTermMap.get("ssq").getResult().split("\\|");
                             			String[] area1=openresultArray[0].split(",");
                             			String[] area2=openresultArray[1].split(",");
                             		%>
                                	<li class="red-ball"><%=area1[0] %></li>
                                    <li class="red-ball"><%=area1[1] %></li>
                                    <li class="red-ball"><%=area1[2] %></li>
                                    <li class="red-ball"><%=area1[3] %></li>
                                    <li class="red-ball"><%=area1[4] %></li>
                                    <li class="red-ball"><%=area1[5] %></li>
                                    <li class="blue-ball"><%=area2[0] %></li>
                                </ul>                          </td>
                             <td>二 四 日</td>
                             <td><a href="/drawlott/ssq_<s:property value="allOpenTerm.get('ssq').termNo"/>.htm" target="_blank"><img src="../images/user/kj_b.png" /></a></td>
                             <td><a href="/direction/ssq/" target="_blank"><img src="../images/user/kj_c.png"/></a></td>
                             <td><a href="/drawlott/ssqindex.htm" target="_blank"><img src="../images/user/kj_d.png" /></a></td>
                             <td>
                             	<input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/ssq/'"/>
                                <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=ssq'"/>                             </td>
                        </tr>
                        <tr>
                          <td><a href="/drawlott/3d_<s:property value="allOpenTerm.get('3d').termNo"/>.htm">福彩3D</a></td>
                          <td><s:property value="allOpenTerm.get('3d').termNo"/>期</td>
                             <td><s:date name="allOpenTerm.get('3d').openPrizeTime" format="yyyy-MM-dd"></s:date></td>
                          <td>
                          	<ul>
                          		<% 
                             			openresultArray = allOpenTermMap.get("3d").getResult().split(",");
                             		%>
                                	<li class="org-ball"><%=openresultArray[0] %></li>
                                    <li class="org-ball"><%=openresultArray[1] %></li>
                                    <li class="org-ball"><%=openresultArray[2] %></li>
                            </ul>                          </td>
                          <td>每日</td>
                           <td><a href="/drawlott/3d_<s:property value="allOpenTerm.get('3d').termNo"/>.htm" target="_blank"><img src="../images/user/kj_b.png" /></a></td>
                             <td><a href="/direction/3d/" target="_blank"><img src="../images/user/kj_c.png" /></a></td>
                             <td><a href="/drawlott/3dindex.htm" target="_blank"><img src="../images/user/kj_d.png" /></a></td>
                             <td>
                             <input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/3d/'"/>
                                <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=3d'"/>                             </td>
                        </tr>
                        <tr>
                          <td colspan="9" class="kj_fc kj_tc">体育彩票开奖信息</td>
                        </tr>
                        <tr>
                          <td><a href="/drawlott/dlt_<s:property value="allOpenTerm.get('dlt').termNo"/>.htm">超级大乐透</a></td>
                           <td><s:property value="allOpenTerm.get('dlt').termNo"/>期</td>
                             <td><s:date name="allOpenTerm.get('dlt').openPrizeTime" format="yyyy-MM-dd"></s:date></td>
                          <td>
                          <ul>
                          			<% 
                             			openresultArray = allOpenTermMap.get("dlt").getResult().split("\\|");
                             			area1=openresultArray[0].split(",");
                             			area2=openresultArray[1].split(",");
                             		%>
                                	<li class="red-ball"><%=area1[0] %></li>
                                    <li class="red-ball"><%=area1[1] %></li>
                                    <li class="red-ball"><%=area1[2] %></li>
                                    <li class="red-ball"><%=area1[3] %></li>
                                    <li class="red-ball"><%=area1[4] %></li>
                                    <li class="blue-ball"><%=area2[0] %></li>
                                    <li class="blue-ball"><%=area2[1] %></li>
                            </ul>                          </td>
                          <td>一 三 六</td>
                           <td><a href="/drawlott/dlt_<s:property value="allOpenTerm.get('dlt').termNo"/>.htm" target="_blank"><img src="../images/user/kj_b.png" /></a></td>
                             <td><a href="/direction/dlt/" target="_blank"><img src="../images/user/kj_c.png" /></a></td>
                             <td><a href="/drawlott/dltindex.htm" target="_blank"><img src="../images/user/kj_d.png" /></a></td>
                             <td>
                             	<input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/dlt/'"/>
                                <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=dlt'"/>                             </td>
                        </tr>
                        <tr>
                          <td><a href="/drawlott/pls_<s:property value="allOpenTerm.get('pls').termNo"/>.htm">排列3</a></td>
                         <td><s:property value="allOpenTerm.get('pls').termNo"/>期</td>
                             <td><s:date name="allOpenTerm.get('pls').openPrizeTime" format="yyyy-MM-dd"></s:date></td>
                          <td><ul>
                           <% 
                             			openresultArray = allOpenTermMap.get("pls").getResult().split(",");
                             		%>
                                	<li class="org-ball"><%=openresultArray[0] %></li>
                                    <li class="org-ball"><%=openresultArray[1] %></li>
                                    <li class="org-ball"><%=openresultArray[2] %></li>
                          </ul>                          </td>
                          <td>每日</td>
                          <td><a href="/drawlott/pls_<s:property value="allOpenTerm.get('pls').termNo"/>.htm" target="_blank"><img src="../images/user/kj_b.png" /></a></td>
                             <td><a href="/direction/pls/" target="_blank"><img src="../images/user/kj_c.png" /></a></td>
                             <td><a href="/drawlott/plsindex.htm" target="_blank"><img src="../images/user/kj_d.png" /></a></td>
                             <td>
                             	<input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/pls/'"/>
                                <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=pls'"/>                             </td>
                        </tr>
                        <tr>
                          <td><a href="/drawlott/plw_<s:property value="allOpenTerm.get('plw').termNo"/>.htm">排列5</a></td>
                          <td><s:property value="allOpenTerm.get('plw').termNo"/>期</td>
                             <td><s:date name="allOpenTerm.get('plw').openPrizeTime" format="yyyy-MM-dd"></s:date></td>
                          <td><ul>
                             <% 
                             			openresultArray = allOpenTermMap.get("plw").getResult().split(",");
                             		%>
                                	<li class="org-ball"><%=openresultArray[0] %></li>
                                    <li class="org-ball"><%=openresultArray[1] %></li>
                                    <li class="org-ball"><%=openresultArray[2] %></li>
                                    <li class="org-ball"><%=openresultArray[3] %></li>
                                    <li class="org-ball"><%=openresultArray[4] %></li>
                          </ul>                          </td>
                          <td>每日</td>
                          <td><a href="/drawlott/plw_<s:property value="allOpenTerm.get('plw').termNo"/>.htm" target="_blank"><img src="../images/user/kj_b.png" /></a></td>
                             <td><a href="/direction/plw/" target="_blank"><img src="../images/user/kj_c.png" /></a></td>
                             <td><a href="/drawlott/plwindex.htm" target="_blank"><img src="../images/user/kj_d.png" /></a></td>
                             <td>
                             	<input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/plw/'"/>
                                <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=plw'"/>                             </td>
                        </tr>
                    </table>
                    <p>一彩票第一时间为您提供准确、全面的彩票开奖信息，祝您多中大奖。</p>
              </div>
            </div>
        </div>
        <div class="ft"></div>
    </div>
    <%@include  file="/foot.jsp"%>
</body>
</html>
