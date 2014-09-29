<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>3D分布图</title>
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
            	<div class="Titile">${ball }在线分析统计</div>
            </div>
    		<div class="Trend_title_bg">
        		<div class="GGTitile">
					<ul id="Active">
                		<li class="GGTitile1 GGTCurrent_bg"><a href="/direction/fbt.htm?type=3d&tt=js">${ball }分布图</a></li>
                    	<li class="GGTitile2"><a href="/direction/plt.htm?tt=${tt }&type=3d">${ball }频率图</a></li>
                    	<li class="GGTitile2"><a href="/direction/zst.htm?tt=${tt }&type=3d&chartType=zst">${ball }走势图</a></li>
               		</ul>
            	</div>
        	</div>
            <div class="Trend_Con">
        		<ul>
            		<li class="TC_li_Current">${ball }分布图</li>
                	
            	</ul>
        	</div>
            <div class="Trend_intro" id="chart_intro">
            说明：说明说明说明说明说明说明说明说明说明说明说明说明说明
            </div>
        </div>
        
        	
        <div class="Trend_title_right">
        	<div class="Trend_soft">
            	<div class="Titile">3D统计分析软件</div> 
            </div>
            <div class="softCon">
				<div class="T_softPic"><img src="../images/img/369software01.jpg" width="61" height="61" /></div>
              <div class="T_softText">免费下载使用3D数据统计分析软件，数据每日更新，几十种数据图表灵活查看</div>
          <div class="T_softBtn">
                   	<div class="btn_1"><a href="/download.htm?fileName=3dsetup.zip">免费下载</a></div>
                    <div class="btn_2"></div>
                    </div>
            </div>
        </div>
        <!--双色球表格图 start-->
        <div class="Trend_table">
        	<div class="Trend_Session">
        		<ul>
        		    <li class="TC_li_Current"><a href="/direction/fbt.htm?type=3d&num=10&tt=js"">最近10期</a></li>
            		<li ><a href="/direction/fbt.htm?type=3d&num=30&tt=js"">最近30期</a></li>
                	<li><a href="/direction/fbt.htm?type=3d&num=50&tt=js">最近50期</a></li>
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
    <td class="${fn:contains(fn:substring(result,0,1),0)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,0,1),1)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,1),2)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,0,1),3)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,0,1),4)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,0,1),5)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,0,1),6)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,0,1),7)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,0,1),8)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,0,1),9)?'Trend_td_red Trend_ball_orange Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,2,3),0)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,2,3),1)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,2,3),2)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,2,3),3)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,2,3),4)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,2,3),5)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,2,3),6)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,2,3),7)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,2,3),8)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,2,3),9)?'Trend_td_red Trend_ball_red Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,4,5),0)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,4,5),1)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,4,5),2)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,4,5),3)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,4,5),4)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,4,5),5)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,4,5),6)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,4,5),7)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,4,5),8)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,4,5),9)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">09</td>
   
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
