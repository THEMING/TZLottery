<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>七乐彩分布图</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/Trend.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/chart.js"></script>
<!--nav js start-->
<script>
$(function(){
	$(".navBg li").click(function(){
		$(this).addClass("navCurrentBg").siblings().removeClass("navCurrentBg");
		$(this).next("a").css("color","#fff");		
	});
});

$(document).ready(function()
{var ltype="${type}";
var tt="${tt}";
var football="${football}";
var chartType="${chartType}"
	changeChartInfo(football,ltype,tt,chartType);
})
</script>
</head>

<body>
<!-- Web Top  start-->
<%@include file="/head.jsp"%>   
<!--Web Nav end-->
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
	<div class="Trend">
    	<div class="Trend_title_left">
        	<div class="Trend_online">
            	<div class="Titile" class="GGTitile1 GGTCurrent_bg">七乐彩在线分析统计</div>
            </div>
    		<div class="Trend_title_bg">
        		<div class="GGTitile">
					<ul id="Active">
                		<li class="GGTitile1 GGTCurrent_bg"><a href="/direction/fbt.htm?type=qlc&tt=js">七乐彩分布图</a></li>
                    	<li class="GGTitile2"><a href="/direction/plt.htm?tt=js&type=qlc">七乐彩频率图</a></li>
                    	<li class="GGTitile2"><a href="/direction/zst.htm?tt=js&type=qlc&chartType=zst">七乐彩走势图</a></li>
               		</ul>
            	</div>
        	</div>
            <div class="Trend_Con">
        		<ul>
            		
                	
            	</ul>
        	</div>
            <div class="Trend_intro" id="chart_intro">
            
            </div>
        </div>
        
        	
        <div class="Trend_title_right">
        	<div class="Trend_soft">
            	<div class="Titile">七乐彩统计分析软件</div> 
            </div>
            <div class="softCon">
				<div class="T_softPic"><img src="../images/img/369software01.jpg" width="61" height="61" /></div>
              <div class="T_softText">免费下载使用七乐彩数据统计分析软件，数据每日更新，几十种数据图标灵活查看</div>
          <div class="T_softBtn">
                   	<div class="btn_1"><a href="/download.htm?fileName=ssqsetup.zip">免费下载</a></div>
                    <div class="btn_2"></div>
                    </div>
            </div>
        </div>
        <!--双色球表格图 start-->
        <div class="Trend_table">
        	<div class="Trend_Session">
        		<ul>
        		    <li ><a href="/direction/fbt.htm?type=qlc&num=10&tt=js">最近10期</a></li>
            		<li ><a href="/direction/fbt.htm?type=qlc&num=30&tt=js">最近30期</a></li>
                	<li><a href="/direction/fbt.htm?type=qlc&num=50&tt=js">最近50期</a></li>
            	</ul>
        	</div>
            <div class="Trend_table_con">
       	  		<table width="996" cellpadding="0" cellspacing="0" bordercolor="#000" border="1" style="border:1px #fbcca5 solid;">
  <tr class="Trend_tr" >
    <td class="Trend_title_qiHao">期号</td>
    <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
    <td class="Trend_title_td">10</td>
    <td class="Trend_title_td">11</td>
    <td class="Trend_title_td">12</td>
    <td class="Trend_title_td">13</td>
    <td class="Trend_title_td">14</td>
    <td class="Trend_title_td">15</td>
    <td class="Trend_title_td">16</td>
    <td class="Trend_title_td">17</td>
    <td class="Trend_title_td">18</td>
    <td class="Trend_title_td">19</td>
    <td class="Trend_title_td">20</td>
    <td class="Trend_title_td">21</td>
    <td class="Trend_title_td">22</td>
    <td class="Trend_title_td">23</td>
    <td class="Trend_title_td">24</td>
    <td class="Trend_title_td">25</td>
    <td class="Trend_title_td">26</td>
    <td class="Trend_title_td">27</td>
    <td class="Trend_title_td">28</td>
    <td class="Trend_title_td">29</td>
    <td class="Trend_title_td">30</td>
  </tr>
   <s:iterator  value="lotteryResult" status="a" > 
  <tr>
    <td class="Trend_qiHao">${termNo }</td>
    <td class="${fn:contains(fn:substring(result,0,24),'01')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,24),'02')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,0,24),'03')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,0,24),'04')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,24),'05')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,0,24),'06')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,0,24),'07')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,0,24),'08')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,0,24),'09')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">09</td>
    <td class="${fn:contains(fn:substring(result,0,24),'10')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">10</td>
    <td class="${fn:contains(fn:substring(result,0,24),'11')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">11</td>
    <td class="${fn:contains(fn:substring(result,0,24),'12')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">12</td>
    <td class="${fn:contains(fn:substring(result,0,24),'13')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">13</td>
    <td class="${fn:contains(fn:substring(result,0,24),'14')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">14</td>
    <td class="${fn:contains(fn:substring(result,0,24),'15')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">15</td>
    <td class="${fn:contains(fn:substring(result,0,24),'16')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">16</td>
    <td class="${fn:contains(fn:substring(result,0,24),'17')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">17</td>
    <td class="${fn:contains(fn:substring(result,0,24),'18')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">18</td>
    <td class="${fn:contains(fn:substring(result,0,24),'19')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">19</td>
    <td class="${fn:contains(fn:substring(result,0,24),'20')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">20</td>
    <td class="${fn:contains(fn:substring(result,0,24),'21')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">21</td>
    <td class="${fn:contains(fn:substring(result,0,24),'22')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">22</td>
    <td class="${fn:contains(fn:substring(result,0,24),'23')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">23</td>
    <td class="${fn:contains(fn:substring(result,0,24),'24')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">24</td>
    <td class="${fn:contains(fn:substring(result,0,24),'25')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">25</td>
    <td class="${fn:contains(fn:substring(result,0,24),'26')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">26</td>
    <td class="${fn:contains(fn:substring(result,0,24),'27')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">27</td>
    <td class="${fn:contains(fn:substring(result,0,24),'28')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">28</td>
    <td class="${fn:contains(fn:substring(result,0,24),'29')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">29</td>
    <td class="${fn:contains(fn:substring(result,0,24),'30')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">30</td>
  </tr>
   </s:iterator>   
  
  
</table>
			</div>

      </div>
        <!--双色球表格图 end-->
        
  </div>
    
</div>
<!--Web Body end-->

<div class="clear"></div>
 <div class="outer">
	<%@include file="/foot.jsp"%>   
	</div>    
</body>
</html>
