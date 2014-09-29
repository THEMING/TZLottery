<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	function onclickTab(num)
	{
		if(num == 0)
		{
			alert("0");
			$("#navigate_category").show();
			$("#navigate_date").hide();	
		}
		else
		{
			alert("1");
			$("#navigate_category").hide();
			$("#navigate_date").show();
		}
	}
	function onclickSort(num)
	{
		if (num==0) //福彩
		{
			$("#welfare_lott").show();
			$("#sport_lott").hide();
			$("#football_lott").hide();
		}
		else if (num==1) //体彩
		{
			$("#welfare_lott").hide();
			$("#sport_lott").show();
			$("#football_lott").hide();
		}
		else if (num==2) //足彩
		{
			$("#welfare_lott").hide();
			$("#sport_lott").hide();
			$("#football_lott").show();
		}
		else
		{
		}
	}
	
	$(document).ready(function()
		{
			var type="${type}";
			if(type=="ssq" || type=="3d" || type=="qlc")
			{
				$("#welfare_lott").show();
				$("#sport_lott").hide();
				$("#football_lott").hide();
			}
			else if(type=="dlt" || type=="qxc" || type=="pls" || type=="plw")
			{
				$("#welfare_lott").hide();
				$("#sport_lott").show();
				$("#football_lott").hide();				
			}
			else
			{
				$("#welfare_lott").hide();
				$("#sport_lott").hide();
				$("#football_lott").hide();	
			}
			$("#item_"+type).attr("class", "on");
		}
	);
</script>
</head>
<body>
<div class="left_box">
      
         
                     
                     
          <div class="left_box_tab" id="navigate_category">
              <h2 class="tit"><a onclick="onclickSort(0)" class="fucai">全国福利彩票</a></h2>
              <ul id="welfare_lott" class="item">
                  <li id="item_ssq"><h1><img class="small_img" src="../images/lottery/pic_ssq.jpg"/><a href="/prizedetail/ssqindex.htm">&nbsp; 双色球</a></h1></li>
                  <li id="item_3d"><h1><img class="small_img" src="../images/lottery/pic_fc3d.jpg"/><a href="/prizedetail/3dindex.htm">&nbsp; 福彩3D</a></h1></li>
                  <li id="item_qlc"><h1><img class="small_img" src="../images/lottery/pic_qlc.jpg"/><a href="/prizedetail/qlcindex.htm">&nbsp; 七乐彩</a></h1></li>
              </ul>
              <h2 class="tit"><a onclick="onclickSort(1)" class="ticai">全国体育彩票</a></h2>
              <ul id="sport_lott" class="item">
                  <li id="item_dlt"><h1><img class="small_img" src="../images/lottery/pic_dlt.jpg"/><a href="/prizedetail/dltindex.htm">&nbsp; 超级大乐透</a></h1></li>
                  <li id="item_qxc"><h1><img class="small_img" src="../images/lottery/pic_qxc.jpg"/><a href="/prizedetail/qxcindex.htm">&nbsp; 七星彩</a></h1></li>
                  <li id="item_pls"><h1><img class="small_img" src="../images/lottery/pic_pls.jpg"/><a href="/prizedetail/plsindex.htm">&nbsp; 排列3</a></h1></li>
                  <li id="item_plw"><h1><img class="small_img" src="../images/lottery/pic_plw.jpg"/><a href="/prizedetail/plwindex.htm">&nbsp; 排列5</a></h1></li>
              </ul>
              <h2 class="tit"><a onclick="onclickSort(2)" class="zucai">足彩</a></h2>
              <ul id="football_lott" class="item">
                  <li><h1><img class="small_img" src="../images/lottery/pic_sfc.jpg"/><a href="/prizedetail/14sfcindex.htm">&nbsp; 14场胜负彩</a></h1></li>
                  <li><h1><img class="small_img" src="../images/lottery/pic_rx9.jpg"/><a href="/prizedetail/r9index.htm">&nbsp; 任选9场</a></h1></li>
                  <li><h1><img class="small_img" src="../images/lottery/pic_scj.jpg"/><a href="/lottery/draw/view/9">&nbsp; 四场进球</a></h1></li>
                  <li><h1><img class="small_img" src="../images/lottery/pic_lcb.jpg"/><a href="/prizedetail/6cbindex.htm">&nbsp; 六场半全场</a></h1></li>
                  <li><h1><img class="small_img" src="../images/lottery/pic_225.jpg"/><a href="/lottery/draw/view/5">&nbsp; 22选5</a></h1></li>
              </ul>
              <h2 class="tit"><a onclick="onclickSort(3)" class="gaopincai">高频彩种</a></h2>
              <ul class="item" style="display: none;">
              </ul>
          </div>
          
          
          
          <div class="left_box_tab" id="navigate_date" style="display: none;">
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期一</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/1?menu=time">超级大乐透</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>                    
                  <li><h1><a href="/lottery/draw/view/51?menu=time">七乐彩</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期二</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/50?menu=time">双色球</a></h1><br /><br /><br /></li>
                 <li><h1><a href="/lottery/draw/view/2?menu=time">七星彩</a></h1><br /><br /><br /></li>
                 <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期三</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/1?menu=time">超级大乐透</a></h1><br /><br /><br /></li>
                 <li><h1><a href="/lottery/draw/view/51?menu=time">七乐彩</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期四</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/50?menu=time">双色球</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期五</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/2?menu=time">七星彩</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>
                 <li><h1><a href="/lottery/draw/view/51?menu=time">七乐彩</a></h1><br /><br /><br /></li>                  
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期六</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/1?menu=time">超级大乐透</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>
                
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">星期日</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/50?menu=time">双色球</a></h1><br /><br /><br /></li>
                 <li><h1><a href="/lottery/draw/view/2?menu=time">七星彩</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/5?menu=time">22选5</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">高频开奖</a></h2>
              <ul class="item" style="display: none;">                    
                  <li><h1><a href="/lottery/draw/view/201?menu=time">上海时时乐</a></h1><br /><br /><br /></li>                   
              </ul>
              <h2 class="tit"><a href="javascript://" class="gaopincai">其他开奖</a></h2>
              <ul class="item" style="display: none;">
                  <li><h1><a href="/lottery/draw/view/7?menu=time">14场胜负彩</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/8?menu=time">任选9场</a></h1><br /><br /><br /></li>
                  <li><h1><a href="/lottery/draw/view/9?menu=time">四场进球</a></h1><br /><br /><br /></li>
                  <li>&gt;h《<h1><a href="/lottery/draw/view/10?menu=time">六场半全场</a></h1></li>
              </ul>
          </div>
      </div>
</body>
</html>