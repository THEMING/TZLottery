<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${ball }走势图</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/Trend.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<!--[if IE]>
<script src="/excanvas-compressed.js" type="text/javascript" ></script>
<![endif]-->
<script src="../js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="../js/jquery.jqchart.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
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
                    	<li class="GGTitile2 GGTCurrent_bg"><a href="/direction/plt.htm?tt=cc&type=${type }&football=football">${ball }频率图</a></li>
                    	<li class="GGTitile2"><a href="/direction/zst.htm?tt=cc&type=${type }&chartType=zst&football=football">${ball }走势图</a></li>
               		</ul>
            	</div>
        	</div>
            <div class="Trend_Con">
        		<ul>
            		<li ><a href="/direction/zst.htm?tt=cc&type=${type }&football=football">场次</a></li>
                	<li ><a href="/direction/zst.htm?tt=lianxue&type=${type }&football=football">连续</a></li>
                	<li ><a href="/direction/zst.htm?tt=jifen&type=${type }&football=football">积分</a></li>
                	<li ><a href="/direction/zst.htm?tt=duandian&type=${type }&football=football">断点</a></li>
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
              <div class="T_softText">免费下载使用数据统计分析软件，数据每日更新，几十种数据图标灵活查看</div>
          <div class="T_softBtn">
                   	<div class="btn_1"><a href="/software">免费下载</a></div>
                    <div class="btn_2"></div>
                    </div>
            </div>
        </div>
        <!--双色球表格图 start-->
        <div class="Trend_table">
        	<div class="Trend_Session">
        		<ul>
        		   <li ><a href="/direction/zst.htm?type=${type }&num=10&tt=${tt}&football=football">最近10期</a></li>
            		<li ><a href="/direction/zst.htm?type=${type }&num=30&tt=${tt}&football=football">最近30期</a></li>
                	<li><a href="/direction/zst.htm?type=${type }&num=50&tt=${tt}&football=football">最近50期</a></li>
            	</ul>
        	</div>
            <div class="Trend_table_con">
<canvas id="canvasMyID" height="200" width="780"></canvas>
<script type="text/javascript">

 $.extend($.jQchart.colorSets,{ 
    cai : [
          'blue','red','red','red','red','red','red','red','red','red','red','red',
          'red','red','red','red','red','red','red','red','red','red','red','red',
          'red','red','red','red','red','red','red','red','red','blue','blue','blue',
          'blue','blue','blue','blue','blue','blue','blue','blue','blue','blue','blue',
          'blue','blue'        
    ]
  });

chartSetting={
  config:{
     title  : '${lotteryType}',
    labelX : ["${labelX}"],
    scaleY : {min: 0,max:<%=request.getAttribute("hmax")%>,gap:<%=(Integer)request.getAttribute("hmax")/5%>},
    colorSet : $.jQchart.colorSets.red
  },
  data : [["${shengq}"],["${pinq}"],["${fuq}"]]
};
$('#canvasMyID').jQchart(chartSetting);	
</script>
<img src="/images/lottery/red.JPG"  width="20" height="1"/>胜
<img src="/images/lottery/yellow.JPG" width="20" height="1"/>平
<img src="/images/lottery/green.JPG" width="20" height="1"/>负
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
