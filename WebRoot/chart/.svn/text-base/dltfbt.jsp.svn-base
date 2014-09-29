<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>大乐透分布图</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/Trend.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script src="../js/lottery/chart.js"></script>
<!--nav js start-->
<script>
$(function(){
	$(".navBg li").click(function(){
		$(this).addClass("navCurrentBg").siblings().removeClass("navCurrentBg");
		$(this).next("a").css("color","#fff");		
	});
});

$$(document).ready(function()
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
            	<div class="Titile">大乐透在线分析统计</div>
            </div>
    		<div class="Trend_title_bg">
        		<div class="GGTitile">
					<ul id="Active">
                		<li class="GGTitile1 GGTCurrent_bg"><a href="/direction/fbt.htm?type=dlt&tt=js">大乐透分布图</a></li>
                    	<li class="GGTitile2"><a href="/direction/plt.htm?tt=${tt }&type=dlt">大乐透频率图</a></li>
                    	<li class="GGTitile2"><a href="/direction/zst.htm?tt=${tt }&type=dlt&chartType=zst">大乐透走势图</a></li>
               		</ul>
            	</div>
        	</div>
            <div class="Trend_Con">
        		<ul>
            		<li class="TC_li_Current">大乐透分布图</li>
                	
            	</ul>
        	</div>
            <div class="Trend_intro" id="chart_intro">
            
            </div>
        </div>
        
        	
        <div class="Trend_title_right">
        	<div class="Trend_soft">
            	<div class="Titile">${ball }软件统计分析</div> 
            </div>
            <div class="softCon">
				<div class="T_softPic"><img src="../images/img/369software01.jpg" width="61" height="61" /></div>
              <div class="T_softText">即将推出</div>
          <div class="T_softBtn">
                   	<div class="btn_1"><a href="/software"></a></div>
                    <div class="btn_2"></div>
                    </div>
            </div>
        </div>
        <!--双色球表格图 start-->
        <div class="Trend_table">
        	<div class="Trend_Session">
        		<ul>
        		    <li class="TC_li_Current"><a href="/direction/fbt.htm?type=dlt&num=10&tt=js"">最近10期</a></li>
            		<li ><a href="/direction/fbt.htm?type=dlt&num=30&tt=js"">最近30期</a></li>
                	<li><a href="/direction/fbt.htm?type=dlt&num=50&tt=js"">最近50期</a></li>
            	</ul>
        	</div>
            <div class="Trend_table_con">
       	  		<table width="1300" cellpadding="0" cellspacing="0" bordercolor="#000" border="1" style="border:1px #fbcca5 solid;">
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
    <td class="Trend_title_td">31</td>
    <td class="Trend_title_td">32</td>
    <td class="Trend_title_td">33</td>
    <td class="Trend_title_td">34</td>
    <td class="Trend_title_td">35</td>
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
  </tr>
   <s:iterator  value="lotteryResult" status="a" > 
  <tr>
    <td class="Trend_qiHao">${termNo }</td>
    <td class="${fn:contains(fn:substring(result,0,17),'01')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,17),'02')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,0,17),'03')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,0,17),'04')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,0,17),'05')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,0,17),'06')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,0,17),'07')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,0,17),'08')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,0,17),'09')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">09</td>
    <td class="${fn:contains(fn:substring(result,0,17),'10')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">10</td>
    <td class="${fn:contains(fn:substring(result,0,17),'11')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">11</td>
    <td class="${fn:contains(fn:substring(result,0,17),'12')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">12</td>
    <td class="${fn:contains(fn:substring(result,0,17),'13')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">13</td>
    <td class="${fn:contains(fn:substring(result,0,17),'14')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">14</td>
    <td class="${fn:contains(fn:substring(result,0,14),'15')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">15</td>
    <td class="${fn:contains(fn:substring(result,0,14),'16')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">16</td>
    <td class="${fn:contains(fn:substring(result,0,14),'17')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">17</td>
    <td class="${fn:contains(fn:substring(result,0,14),'18')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">18</td>
    <td class="${fn:contains(fn:substring(result,0,14),'19')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">19</td>
    <td class="${fn:contains(fn:substring(result,0,14),'20')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">20</td>
    <td class="${fn:contains(fn:substring(result,0,14),'21')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">21</td>
    <td class="${fn:contains(fn:substring(result,0,14),'22')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">22</td>
    <td class="${fn:contains(fn:substring(result,0,14),'23')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">23</td>
    <td class="${fn:contains(fn:substring(result,0,14),'24')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">24</td>
    <td class="${fn:contains(fn:substring(result,0,14),'25')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">25</td>
    <td class="${fn:contains(fn:substring(result,0,14),'26')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">26</td>
    <td class="${fn:contains(fn:substring(result,0,14),'27')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">27</td>
    <td class="${fn:contains(fn:substring(result,0,14),'28')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">28</td>
    <td class="${fn:contains(fn:substring(result,0,14),'29')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">29</td>
    <td class="${fn:contains(fn:substring(result,0,14),'30')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">30</td>
    <td class="${fn:contains(fn:substring(result,0,14),'31')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">31</td>
    <td class="${fn:contains(fn:substring(result,0,14),'32')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">32</td>
    <td class="${fn:contains(fn:substring(result,0,14),'33')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">33</td>
    <td class="${fn:contains(fn:substring(result,0,14),'34')?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">34</td>
    <td class="${fn:contains(fn:substring(result,0,14),'35')?'Trend_td_red Trend_ball_orange Trend_border_z':'Trend_td_red Trend_border_z' }">35</td>
    <td class="${fn:contains(fn:substring(result,15,20),'01')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">01</td>
    <td class="${fn:contains(fn:substring(result,15,20),'02')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">02</td>
    <td class="${fn:contains(fn:substring(result,15,20),'03')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">03</td>
    <td class="${fn:contains(fn:substring(result,15,20),'04')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">04</td>
    <td class="${fn:contains(fn:substring(result,15,20),'05')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">05</td>
    <td class="${fn:contains(fn:substring(result,15,20),'06')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">06</td>
    <td class="${fn:contains(fn:substring(result,15,20),'07')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">07</td>
    <td class="${fn:contains(fn:substring(result,15,20),'08')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">08</td>
    <td class="${fn:contains(fn:substring(result,15,20),'09')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">09</td>
    <td class="${fn:contains(fn:substring(result,15,20),'10')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">10</td>
    <td class="${fn:contains(fn:substring(result,15,20),'11')?'Trend_td_blue Trend_ball_blue':'Trend_td_blue' }">11</td>
    <td class="${fn:contains(fn:substring(result,15,20),'12')?'Trend_td_blue Trend_ball_blue Trend_border_z':'Trend_td_blue Trend_border_z' }">12</td>
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
