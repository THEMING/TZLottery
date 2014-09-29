<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>一彩票_足球数据</title>
<link rel="stylesheet" href="../css/common.css"/>
<link rel="stylesheet" href="../css/css.css"/>
<link rel="stylesheet" href="../css/default.css"/>
<link rel="stylesheet" href="../css/odds-serve.css"/>
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon"/>
<!--[if IE]>
<script src="/excanvas-compressed.js" type="text/javascript" ></script>
<![endif]-->
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/jquery.jqchart.js" type="text/javascript" ></script>
<!--save box js start-->
<script type="text/javascript">
function addBookmark(title,url) {
if (window.sidebar) { 
window.sidebar.addPanel(title, url,""); 
} else if( document.all ) {
window.external.AddFavorite( url, title);
} else if( window.opera && window.print ) {
return true;
}
}
</script> 
<!--save box js end-->
<!--网站导航下拉菜单 start-->
<script type="text/javascript">
<!--
var timeout         = 150;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 
// -->
</script>
<!--网站导航下拉菜单 end-->



<script type="text/javascript">
	var hfix;
	var vfix;
	$(document).ready(
		function()
		{
			fixed(0);
			xuanze(0);
			hostNumSelect(0);
			visitNumSelect(0);
			$("#host").val("10");
			$("#visit").val("10");
			$("#check1")[0].checked = 0;
			$("#check2")[0].checked = 0;
		}
	);
	
	function xuanze(index)
	{
		switch(index)
		{
			case 0:	
			$("#zongti").addClass("tab tab-current");
			$("#zhudui").removeClass("tab tab-current").addClass("tab");
			$("#kedui").removeClass("tab tab-current").addClass("tab");
			$("#jin").removeClass("tab tab-current").addClass("tab");		
			$("#hostAllRound").text("${hostLeagueRank.allRound}");
			$("#hostPoints").text("${hostLeagueRank.points}");
			$("#hostWinRound").text("${hostLeagueRank.winRound}");
			$("#hostDrowRound").text("${hostLeagueRank.drowRound}");
			$("#hostLoseRound").text("${hostLeagueRank.loseRound}");
			$("#hostWinNum").text("${hostLeagueRank.winNum}");
			$("#hostLoseNum").text("${hostLeagueRank.loseNum}");
			$("#hostJing").text("${hostLeagueRank.winNum - hostLeagueRank.loseNum}");
			$("#hostRank").text("${hostLeagueRank.rank}");
			
			$("#visitAllRound").text("${visitLeagueRank.allRound}");
			$("#visitPoints").text("${visitLeagueRank.points}");
			$("#visitWinRound").text("${visitLeagueRank.winRound}");
			$("#visitDrowRound").text("${visitLeagueRank.drowRound}");
			$("#visitLoseRound").text("${visitLeagueRank.loseRound}");
			$("#visitWinNum").text("${visitLeagueRank.winNum}");
			$("#visitLoseNum").text("${visitLeagueRank.loseNum}");
			$("#visitJing").text("${visitLeagueRank.winNum - visitLeagueRank.loseNum}");
			$("#visitRank").text("${visitLeagueRank.rank}");
			$("#canvas1").show();
			$("#canvas2").show();
			$("#canvas3").hide();
			$("#canvas4").hide();
			$("#canvas5").hide();
			$("#canvas6").hide();
			break;
			
			case 1:
			$("#zongti").removeClass("tab tab-current").addClass("tab");
			$("#zhudui").addClass("tab tab-current");
			$("#kedui").removeClass("tab tab-current").addClass("tab");
			$("#jin").removeClass("tab tab-current").addClass("tab");
			$("#hostAllRound").text("${hostLeagueRank.hallRound}");
			$("#hostPoints").text("${hostLeagueRank.hpoints}");
			$("#hostWinRound").text("${hostLeagueRank.hwinRound}");
			$("#hostDrowRound").text("${hostLeagueRank.hdrowRound}");
			$("#hostLoseRound").text("${hostLeagueRank.hloseRound}");
			$("#hostWinNum").text("${hostLeagueRank.hwinNum}");
			$("#hostLoseNum").text("${hostLeagueRank.hloseNum}");
			$("#hostJing").text("${hostLeagueRank.hwinNum - hostLeagueRank.hloseNum}");
			$("#hostRank").text("${hostLeagueRank.hrank}");
			
			$("#visitAllRound").text("${visitLeagueRank.hallRound}");
			$("#visitPoints").text("${visitLeagueRank.hpoints}");
			$("#visitWinRound").text("${visitLeagueRank.hwinRound}");
			$("#visitDrowRound").text("${visitLeagueRank.hdrowRound}");
			$("#visitLoseRound").text("${visitLeagueRank.hloseRound}");
			$("#visitWinNum").text("${visitLeagueRank.hwinNum}");
			$("#visitLoseNum").text("${visitLeagueRank.hloseNum}");
			$("#visitJing").text("${visitLeagueRank.hwinNum - visitLeagueRank.hloseNum}");
			$("#visitRank").text("${visitLeagueRank.hrank}");
			$("#canvas1").hide();
			$("#canvas2").hide();
			$("#canvas3").show();
			$("#canvas4").show();
			$("#canvas5").hide();
			$("#canvas6").hide();
			break;
			
			case 2:
			$("#zongti").removeClass("tab tab-current").addClass("tab");
			$("#zhudui").removeClass("tab tab-current").addClass("tab");
			$("#kedui").addClass("tab tab-current");
			$("#jin").removeClass("tab tab-current").addClass("tab");
			$("#hostAllRound").text("${hostLeagueRank.aallRound}");
			$("#hostPoints").text("${hostLeagueRank.apoints}");
			$("#hostWinRound").text("${hostLeagueRank.awinRound}");
			$("#hostDrowRound").text("${hostLeagueRank.adrowRound}");
			$("#hostLoseRound").text("${hostLeagueRank.aloseRound}");
			$("#hostWinNum").text("${hostLeagueRank.awinNum}");
			$("#hostLoseNum").text("${hostLeagueRank.aloseNum}");
			$("#hostJing").text("${hostLeagueRank.awinNum - hostLeagueRank.aloseNum}");
			$("#hostRank").text("${hostLeagueRank.arank}");
			
			$("#visitAllRound").text("${visitLeagueRank.aallRound}");
			$("#visitPoints").text("${visitLeagueRank.apoints}");
			$("#visitWinRound").text("${visitLeagueRank.awinRound}");
			$("#visitDrowRound").text("${visitLeagueRank.adrowRound}");
			$("#visitLoseRound").text("${visitLeagueRank.aloseRound}");
			$("#visitWinNum").text("${visitLeagueRank.awinNum}");
			$("#visitLoseNum").text("${visitLeagueRank.aloseNum}");
			$("#visitJing").text("${visitLeagueRank.awinNum - visitLeagueRank.aloseNum}");
			$("#visitRank").text("${visitLeagueRank.arank}");
			$("#canvas1").hide();
			$("#canvas2").hide();
			$("#canvas3").hide();
			$("#canvas4").hide();
			$("#canvas5").show();
			$("#canvas6").show();
			break;
			
			default:
				break;
		}
	}
	
	function fixed(index)
	{
		if(index != 0)
		{	
			$("#li1").removeClass("tab tab-current").addClass("tab");;
			$("#li2").addClass("tab tab-current");
			$("#fixed").show();
			$("#notFixed").hide();
			$("#spf1").hide();
			$("#spf2").show();
		}
		else
		{
			$("#li2").removeClass("tab tab-current").toggleClass("tab");
			$("#li1").addClass("tab tab-current");
			$("#fixed").hide();
			$("#notFixed").show();
			$("#spf1").show();
			$("#spf2").hide();
		}
	}
	
	function hostNumSelect(no)
	{
		var num;
		if(no == 0)
		{
			num = 10;
		}
		else
		{
			num = $("#host").val();
		}
		if(num == 10)
		{
			if(hfix == true)
			{
				$("#hten").hide(); 
				$("#hsix").hide();
				$("#htenfix").show(); 
				$("#hsixfix").hide();
				$("#host1").hide();
				$("#host2").show();
				$("#host3").hide();
				$("#host4").hide();
				$("#host11").hide();
				$("#host22").show();
				$("#host33").hide();
				$("#host44").hide();
				$("#canvasMyI2").hide();
				$("#canvasMyI3").hide();
				$("#canvasMyI4").hide();
				$("#canvasMyI5").hide();
				$("#canvasMyI6").show();
				$("#canvasMyI7").show();
				$("#canvasMyI8").show();
				$("#canvasMyI9").show();
				$("#canvasMyI10").hide();
				$("#canvasMyI11").hide();
				$("#canvasMyI12").hide();
				$("#canvasMyI13").hide();
				$("#canvasMyI14").hide();
				$("#canvasMyI15").hide();
				$("#canvasMyI16").hide();
				$("#canvasMyI17").hide();
			}
			else
			{
				$("#hten").show(); 
				$("#hsix").hide();
				$("#htenfix").hide(); 
				$("#hsixfix").hide();
				$("#host1").show();
				$("#host2").hide();
				$("#host3").hide();
				$("#host4").hide();
				$("#host11").show();
				$("#host22").hide();
				$("#host33").hide();
				$("#host44").hide();
				$("#canvasMyI2").show();
				$("#canvasMyI3").show();
				$("#canvasMyI4").show();
				$("#canvasMyI5").show();
				$("#canvasMyI6").hide();
				$("#canvasMyI7").hide();
				$("#canvasMyI8").hide();
				$("#canvasMyI9").hide();
				$("#canvasMyI10").hide();
				$("#canvasMyI11").hide();
				$("#canvasMyI12").hide();
				$("#canvasMyI13").hide();
				$("#canvasMyI14").hide();
				$("#canvasMyI15").hide();
				$("#canvasMyI16").hide();
				$("#canvasMyI17").hide();
			}
		}
		else if(num == 20)
		{
			if(hfix == true)
			{
				$("#hten").hide(); 
				$("#hsix").hide();
				$("#htenfix").hide(); 
				$("#hsixfix").show();
				$("#host1").hide();
				$("#host2").hide();
				$("#host3").hide();
				$("#host4").show();
				$("#host11").hide();
				$("#host22").hide();
				$("#host33").hide();
				$("#host44").show();
				$("#canvasMyI2").hide();
				$("#canvasMyI3").hide();
				$("#canvasMyI4").hide();
				$("#canvasMyI5").hide();
				$("#canvasMyI6").hide();
				$("#canvasMyI7").hide();
				$("#canvasMyI8").hide();
				$("#canvasMyI9").hide();
				$("#canvasMyI10").hide();
				$("#canvasMyI11").hide();
				$("#canvasMyI12").hide();
				$("#canvasMyI13").hide();
				$("#canvasMyI14").show();
				$("#canvasMyI15").show();
				$("#canvasMyI16").show();
				$("#canvasMyI17").show();
			}
			else
			{
				$("#hten").hide(); 
				$("#hsix").show();
				$("#htenfix").hide(); 
				$("#hsixfix").hide();
				$("#host1").hide();
				$("#host2").hide();
				$("#host3").show();
				$("#host4").hide();
				$("#host11").hide();
				$("#host22").hide();
				$("#host33").show();
				$("#host44").hide();
				$("#canvasMyI2").hide();
				$("#canvasMyI3").hide();
				$("#canvasMyI4").hide();
				$("#canvasMyI5").hide();
				$("#canvasMyI6").hide();
				$("#canvasMyI7").hide();
				$("#canvasMyI8").hide();
				$("#canvasMyI9").hide();
				$("#canvasMyI10").show();
				$("#canvasMyI11").show();
				$("#canvasMyI12").show();
				$("#canvasMyI13").show();
				$("#canvasMyI14").hide();
				$("#canvasMyI15").hide();
				$("#canvasMyI16").hide();
				$("#canvasMyI17").hide();
			}
		}
	}
	
	function visitNumSelect(no)
	{
		var num;
		if(no == 0)
		{
			num = 10
		}
		else
		{
			num = $("#visit").val();
		}
		if(num == 10)
		{
			if(vfix == true)
			{
				$("#vten").hide(); 
				$("#vsix").hide();
				$("#vtenfix").show(); 
				$("#vsixfix").hide();
				$("#visit1").hide();
				$("#visit2").show();
				$("#visit3").hide();
				$("#visit4").hide();
				$("#visit11").hide();
				$("#visit22").show();
				$("#visit33").hide();
				$("#visit44").hide();
				$("#canvasMyIv2").hide();
				$("#canvasMyIv3").hide();
				$("#canvasMyIv4").hide();
				$("#canvasMyIv5").hide();
				$("#canvasMyIv6").show();
				$("#canvasMyIv7").show();
				$("#canvasMyIv8").show();
				$("#canvasMyIv9").show();
				$("#canvasMyIv10").hide();
				$("#canvasMyIv11").hide();
				$("#canvasMyIv12").hide();
				$("#canvasMyIv13").hide();
				$("#canvasMyIv14").hide();
				$("#canvasMyIv15").hide();
				$("#canvasMyIv16").hide();
				$("#canvasMyIv17").hide();
			}
			else
			{
				$("#vten").show(); 
				$("#vsix").hide();
				$("#vtenfix").hide(); 
				$("#vsixfix").hide();
				$("#visit1").show();
				$("#visit2").hide();
				$("#visit3").hide();
				$("#visit4").hide();
				$("#visit11").show();
				$("#visit22").hide();
				$("#visit33").hide();
				$("#visit44").hide();
				$("#canvasMyIv2").show();
				$("#canvasMyIv3").show();
				$("#canvasMyIv4").show();
				$("#canvasMyIv5").show();
				$("#canvasMyIv6").hide();
				$("#canvasMyIv7").hide();
				$("#canvasMyIv8").hide();
				$("#canvasMyIv9").hide();
				$("#canvasMyIv10").hide();
				$("#canvasMyIv11").hide();
				$("#canvasMyIv12").hide();
				$("#canvasMyIv13").hide();
				$("#canvasMyIv14").hide();
				$("#canvasMyIv15").hide();
				$("#canvasMyIv16").hide();
				$("#canvasMyIv17").hide();
			}			
		}
		else if(num == 20)
		{
			if(vfix == true)
			{
				$("#vten").hide(); 
				$("#vsix").hide();
				$("#vtenfix").hide(); 
				$("#vsixfix").show();
				$("#visit1").hide();
				$("#visit2").hide();
				$("#visit3").hide();
				$("#visit4").show();
				$("#visit11").hide();
				$("#visit22").hide();
				$("#visit33").hide();
				$("#visit44").show();
				$("#canvasMyIv2").hide();
				$("#canvasMyIv3").hide();
				$("#canvasMyIv4").hide();
				$("#canvasMyIv5").hide();
				$("#canvasMyIv6").hide();
				$("#canvasMyIv7").hide();
				$("#canvasMyIv8").hide();
				$("#canvasMyIv9").hide();
				$("#canvasMyIv10").hide();
				$("#canvasMyIv11").hide();
				$("#canvasMyIv12").hide();
				$("#canvasMyIv13").hide();
				$("#canvasMyIv14").show();
				$("#canvasMyIv15").show();
				$("#canvasMyIv16").show();
				$("#canvasMyIv17").show();
			}
			else
			{
				$("#vten").hide(); 
				$("#vsix").show();
				$("#vtenfix").hide(); 
				$("#vsixfix").hide();
				$("#visit1").hide();
				$("#visit2").hide();
				$("#visit3").show();
				$("#visit4").hide();
				$("#visit11").hide();
				$("#visit22").hide();
				$("#visit33").show();
				$("#visit44").hide();
				$("#canvasMyIv2").hide();
				$("#canvasMyIv3").hide();
				$("#canvasMyIv4").hide();
				$("#canvasMyIv5").hide();
				$("#canvasMyIv6").hide();
				$("#canvasMyIv7").hide();
				$("#canvasMyIv8").hide();
				$("#canvasMyIv9").hide();
				$("#canvasMyIv10").show();
				$("#canvasMyIv11").show();
				$("#canvasMyIv12").show();
				$("#canvasMyIv13").show();
				$("#canvasMyIv14").hide();
				$("#canvasMyIv15").hide();
				$("#canvasMyIv16").hide();
				$("#canvasMyIv17").hide();
			}
		}
	}
	
	function onlyhost(check)
	{
		if(check == 0)
		{
			if($("#check1")[0].checked)
			{
				hfix = true;
				var num = $("#host").val();
				if(num == 10)
				{
					$("#hten").hide(); 
					$("#hsix").hide();
					$("#htenfix").show(); 
					$("#hsixfix").hide();
					$("#host1").hide();
					$("#host2").show();
					$("#host3").hide();
					$("#host4").hide();
					$("#host11").hide();
					$("#host22").show();
					$("#host33").hide();
					$("#host44").hide();
					$("#canvasMyI2").hide();
					$("#canvasMyI3").hide();
					$("#canvasMyI4").hide();
					$("#canvasMyI5").hide();
					$("#canvasMyI6").show();
					$("#canvasMyI7").show();
					$("#canvasMyI8").show();
					$("#canvasMyI9").show();
					$("#canvasMyI10").hide();
					$("#canvasMyI11").hide();
					$("#canvasMyI12").hide();
					$("#canvasMyI13").hide();
					$("#canvasMyI14").hide();
					$("#canvasMyI15").hide();
					$("#canvasMyI16").hide();
					$("#canvasMyI17").hide();
					
				}
				else if(num == 20)
				{
					$("#hten").hide(); 
					$("#hsix").hide();
					$("#htenfix").hide(); 
					$("#hsixfix").show();
					$("#host1").hide();
					$("#host2").hide();
					$("#host3").hide();
					$("#host4").show();
					$("#host11").hide();
					$("#host22").hide();
					$("#host33").hide();
					$("#host44").show();
					$("#canvasMyI2").hide();
					$("#canvasMyI3").hide();
					$("#canvasMyI4").hide();
					$("#canvasMyI5").hide();
					$("#canvasMyI6").hide();
					$("#canvasMyI7").hide();
					$("#canvasMyI8").hide();
					$("#canvasMyI9").hide();
					$("#canvasMyI10").hide();
					$("#canvasMyI11").hide();
					$("#canvasMyI12").hide();
					$("#canvasMyI13").hide();
					$("#canvasMyI14").show();
					$("#canvasMyI15").show();
					$("#canvasMyI16").show();
					$("#canvasMyI17").show();
				}
			}
			else
			{
				hfix = false;
				var num = $("#host").val();
				if(num == 10)
				{
					$("#hten").show(); 
					$("#hsix").hide();
					$("#htenfix").hide(); 
					$("#hsixfix").hide();
					$("#host1").show();
					$("#host2").hide();
					$("#host3").hide();
					$("#host4").hide();
					$("#host11").show();
					$("#host22").hide();
					$("#host33").hide();
					$("#host44").hide();
					$("#canvasMyI2").show();
					$("#canvasMyI3").show();
					$("#canvasMyI4").show();
					$("#canvasMyI5").show();
					$("#canvasMyI6").hide();
					$("#canvasMyI7").hide();
					$("#canvasMyI8").hide();
					$("#canvasMyI9").hide();
					$("#canvasMyI10").hide();
					$("#canvasMyI11").hide();
					$("#canvasMyI12").hide();
					$("#canvasMyI13").hide();
					$("#canvasMyI14").hide();
					$("#canvasMyI15").hide();
					$("#canvasMyI16").hide();
					$("#canvasMyI17").hide();
				}
				else if(num == 20)
				{
					$("#hten").hide(); 
					$("#hsix").show();
					$("#htenfix").hide(); 
					$("#hsixfix").hide();
					$("#host1").hide();
					$("#host2").hide();
					$("#host3").show();
					$("#host4").hide();
					$("#host11").hide();
					$("#host22").hide();
					$("#host33").show();
					$("#host44").hide();
					$("#canvasMyI2").hide();
					$("#canvasMyI3").hide();
					$("#canvasMyI4").hide();
					$("#canvasMyI5").hide();
					$("#canvasMyI6").hide();
					$("#canvasMyI7").hide();
					$("#canvasMyI8").hide();
					$("#canvasMyI9").hide();
					$("#canvasMyI10").show();
					$("#canvasMyI11").show();
					$("#canvasMyI12").show();
					$("#canvasMyI13").show();
					$("#canvasMyI14").hide();
					$("#canvasMyI15").hide();
					$("#canvasMyI16").hide();
					$("#canvasMyI17").hide();
				}
			}
		}
		else if(check == 1)
		{
			if($("#check2")[0].checked)
			{
				vfix = true;
				var num = $("#visit").val();
				if(num == 10)
				{
					$("#vten").hide(); 
					$("#vsix").hide();
					$("#vtenfix").show(); 
					$("#vsixfix").hide();
					$("#visit1").hide();
					$("#visit2").show();
					$("#visit3").hide();
					$("#visit4").hide();
					$("#visit11").hide();
					$("#visit22").show();
					$("#visit33").hide();
					$("#visit44").hide();
					$("#canvasMyIv2").hide();
					$("#canvasMyIv3").hide();
					$("#canvasMyIv4").hide();
					$("#canvasMyIv5").hide();
					$("#canvasMyIv6").show();
					$("#canvasMyIv7").show();
					$("#canvasMyIv8").show();
					$("#canvasMyIv9").show();
					$("#canvasMyIv10").hide();
					$("#canvasMyIv11").hide();
					$("#canvasMyIv12").hide();
					$("#canvasMyIv13").hide();
					$("#canvasMyIv14").hide();
					$("#canvasMyIv15").hide();
					$("#canvasMyIv16").hide();
					$("#canvasMyIv17").hide();
				}
				else if(num == 20)
				{
					$("#vten").hide(); 
					$("#vsix").hide();
					$("#vtenfix").hide(); 
					$("#vsixfix").show();
					$("#host1").hide();
					$("#host2").hide();
					$("#host3").hide();
					$("#host4").show();
					$("#host11").hide();
					$("#host22").hide();
					$("#host33").hide();
					$("#host44").show();
					$("#canvasMyIv2").hide();
					$("#canvasMyIv3").hide();
					$("#canvasMyIv4").hide();
					$("#canvasMyIv5").hide();
					$("#canvasMyIv6").hide();
					$("#canvasMyIv7").hide();
					$("#canvasMyIv8").hide();
					$("#canvasMyIv9").hide();
					$("#canvasMyIv10").hide();
					$("#canvasMyIv11").hide();
					$("#canvasMyIv12").hide();
					$("#canvasMyIv13").hide();
					$("#canvasMyIv14").show();
					$("#canvasMyIv15").show();
					$("#canvasMyIv16").show();
					$("#canvasMyIv17").show();
				}	
			}
			else
			{
				vfix = false;
				var num = $("#visit").val();
				if(num == 10)
				{
					$("#vten").show(); 
					$("#vsix").hide();
					$("#vtenfix").hide(); 
					$("#vsixfix").hide();
					$("#visit1").show();
					$("#visit2").hide();
					$("#visit3").hide();
					$("#visit4").hide();
					$("#visit11").show();
					$("#visit22").hide();
					$("#visit33").hide();
					$("#visit44").hide();
					$("#canvasMyIv2").show();
					$("#canvasMyIv3").show();
					$("#canvasMyIv4").show();
					$("#canvasMyIv5").show();
					$("#canvasMyIv6").hide();
					$("#canvasMyIv7").hide();
					$("#canvasMyIv8").hide();
					$("#canvasMyIv9").hide();
					$("#canvasMyIv10").hide();
					$("#canvasMyIv11").hide();
					$("#canvasMyIv12").hide();
					$("#canvasMyIv13").hide();
					$("#canvasMyIv14").hide();
					$("#canvasMyIv15").hide();
					$("#canvasMyIv16").hide();
					$("#canvasMyIv17").hide();
				}
				else if(num == 20)
				{
					$("#vten").hide(); 
					$("#vsix").show();
					$("#vtenfix").hide(); 
					$("#vsixfix").hide();
					$("#visit1").hide();
					$("#visit2").hide();
					$("#visit3").show();
					$("#visit4").hide();
					$("#visit11").hide();
					$("#visit22").hide();
					$("#visit33").show();
					$("#visit44").hide();
					$("#canvasMyIv2").hide();
					$("#canvasMyIv3").hide();
					$("#canvasMyIv4").hide();
					$("#canvasMyIv5").hide();
					$("#canvasMyIv6").hide();
					$("#canvasMyIv7").hide();
					$("#canvasMyIv8").hide();
					$("#canvasMyIv9").hide();
					$("#canvasMyIv10").show();
					$("#canvasMyIv11").show();
					$("#canvasMyIv12").show();
					$("#canvasMyIv13").show();
					$("#canvasMyIv14").hide();
					$("#canvasMyIv15").hide();
					$("#canvasMyIv16").hide();
					$("#canvasMyIv17").hide();
				}
			}
		}
	}
</script>
</head>
<body>
<%@include file="/head.jsp" %>
<div class="clear"></div>
<!--the web title start-->
  <!--right start-->
  <div class="right-area">
    <div class="zhukebijiao">
        <ul style="float:left;">
           <li style="float:left;">
              <ul class="zhudui">
                 <li><a href="#" onfocus="this.blur();"><s:property value="hostName"/></a></li>
                 <li>联赛排名：<s:property value="host.rank"/></li>
              </ul>
           </li>
           <li style="float:left;">
            <table width="280" class="tt-table" border="1" cellspacing="1" cellpadding="5">
                  <tr>
                    <td width="58">&nbsp;</td>
                    <td width="80" class="biaoshi2">让球</td>
                    <td width="40" class="biaoshi1">胜</td>
                    <td width="40" class="biaoshi">平</td>
                    <td width="40" class="biaoshi">负</td>
                  </tr>
                  <tr bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                    <td class="biaoshi">竞彩赔率</td>
                    <td><s:property value="matchArrange.concede"/></td>
                    <td><s:property value="winPeiLv"/></td>
                    <td><s:property value="drowPeiLv"/></td>
                    <td><s:property value="losePeiLv"/></td>
                  </tr>
                  <tr bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                    <td class="biaoshi1">平均欧赔</td>
                    <td>--</td>
                    <td><s:property value="matchArrange.sop"/></td>
                    <td><s:property value="matchArrange.pop"/></td>
                    <td><s:property value="matchArrange.fop"/></td>
                  </tr>
                  <tr bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                    <td class="biaoshi2">盘口</td>
                    <td><s:property value="aomenOdd.rang"/></td>
                    <td><s:property value="aomenOdd.sheng"/></td>
                    <td>--</td>
                    <td><s:property value="aomenOdd.fu"/></td>

                  </tr>
             </table>
           </li>
<li style="float:left;">
              <ul class="zhudui">
                 <li><a href="#" onfocus="this.blur();"><s:property value="visitName"/></a></li>
                 <li>联赛排名：<s:property value="visit.rank"/></li>
              </ul>
           </li>
      </ul>
    </div>
    <s:if test="hostLeagueRank != null">
      <div class="selectduizheng">
           <ul>
              <li class="left"></li>
              <li class="middle">
                 <ul>
                   <li style="font-size:14px; font-weight:bold;color:#8f0303;float:left;margin-right:150px;">两队比较</li>
                   <li class="tab tab-current" style="float:left;" id="zongti"><a href="javascript:xuanze(0);" onfocus="this.blur();">总体比较</a></li>
                   <li class="tab" id="zhudui" style="float:left;"><a href="javascript:xuanze(1);" onfocus="this.blur();">主队</a></li>
                   <li class="tab" id="kedui" style="float:left;"><a href="javascript:xuanze(2);" onfocus="this.blur();">客队</a></li>
                 </ul> 
              </li>
              <li class="right"></li>
           </ul>
      </div>
      <div class="clear"></div>
      <div class="compare-table">
      <div class="chart" id="canvas1" style="float:left;padding-left:80px;">   
				<canvas id="canvasMyID" height="180" width="300"></canvas>

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
    			 title  : '竟彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:${hmax},gap:${hmax/5}},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [["${hostLeagueRank.winRound}","${hostLeagueRank.drowRound}","${hostLeagueRank.loseRound}"]]
				};
				$('#canvasMyID').jQchart(chartSetting);
	
				</script>      
		</div>
		
		<div class="chart" id="canvas2" style="float:right;padding-right:80px;">   
				<canvas id="canvasMyID1" height="180" width="300"></canvas>
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
    			 title  : '竟彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:${vmax},gap:${vmax/5}},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [["${visitLeagueRank.winRound}","${visitLeagueRank.drowRound}","${visitLeagueRank.loseRound}"]]
				};
				$('#canvasMyID1').jQchart(chartSetting);
	
				</script>      
		</div>
		<div class="clear"></div>
		<div class="chart" id="canvas3" style="float:left;padding-left:80px;">   
            <canvas id="canvasMyIDa" height="180" width="300"></canvas>

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
             title  : '竟彩--胜平负',
             labelX : ["胜","平","负"],
             scaleY : {min: 0,max:${hhmax},gap:${hhmax/5}},
                type:'bar',
             colorSet : $.jQchart.colorSets.red
            },
            data : [["${hostLeagueRank.hwinRound}","${hostLeagueRank.hdrowRound}","${hostLeagueRank.hloseRound}"]]
            };
            $('#canvasMyIDa').jQchart(chartSetting);

            </script>      
		</div>
		
		<div class="chart" id="canvas4" style="float:right; padding-right:80px;">   
				<canvas id="canvasMyIDb" height="180" width="300"></canvas>
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
    			 title  : '竟彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:${vhmax},gap:${vhmax/5}},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [["${visitLeagueRank.hwinRound}","${visitLeagueRank.hdrowRound}","${visitLeagueRank.hloseRound}"]]
				};
				$('#canvasMyIDb').jQchart(chartSetting);
	
				</script>      
		</div>
		<div class="clear"></div>
		<div class="chart" id="canvas5" style="float:left; padding-left:80px;">   
				<canvas id="canvasMyIDc" height="180" width="300"></canvas>

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
    			 title  : '竟彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:${hamax},gap:${hamax/5}},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [["${hostLeagueRank.awinRound}","${hostLeagueRank.adrowRound}","${hostLeagueRank.aloseRound}"]]
				};
				$('#canvasMyIDc').jQchart(chartSetting);
	
				</script>      
		</div>
		<div class="chart" id="canvas6" style="float:right; padding-right:80px;">   
            <canvas id="canvasMyIDd" height="180" width="300"></canvas>
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
             title  : '竟彩--胜平负',
             labelX : ["胜","平","负"],
             scaleY : {min: 0,max:${vamax},gap:${vamax/5}},
                type:'bar',
             colorSet : $.jQchart.colorSets.red
            },
            data : [["${visitLeagueRank.awinRound}","${visitLeagueRank.adrowRound}","${visitLeagueRank.aloseRound}"]]
            };
            $('#canvasMyIDd').jQchart(chartSetting);

            </script>      
		</div>
        <div class="clear"></div>
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
              <tr class="biaogename">
                <td width="95">&nbsp;</td>
                <td width="72">排名</td>
                <td width="70">胜</td>
                <td width="70">平</td>
                <td width="70">负</td>
                <td width="70">进球</td>
                <td width="70">失球</td>
                <td width="70">净</td>
                <td width="70">总场次</td>
                <td width="70">积分</td>
              </tr>
              <tr  style="color:#903"  bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                <td class="table-border" style="background:#330;color:white;line-height:2;"><s:property value="hostName"/></td>
                <td class="table-border" id="hostRank"><s:property value="hostLeagueRank.rank"/></td>
                <td class="table-border" id="hostWinRound"><s:property value="hostLeagueRank.winRound"/></td>
                <td class="table-border" id="hostDrowRound"><s:property value="hostLeagueRank.drowRound"/></td>
                <td class="table-border" id="hostLoseRound"><s:property value="hostLeagueRank.loseRound"/></td>
                <td class="table-border" id="hostWinNum"><s:property value="hostLeagueRank.winNum"/></td>
                <td class="table-border" id="hostLoseNum"><s:property value="hostLeagueRank.loseNum"/></td>
                <td class="table-border" id="hostJing">${hostLeagueRank.winNum - hostLeagueRank.loseNum}</td>
                <td class="table-border" id="hostAllRound"><s:property value="hostLeagueRank.allRound"/></td>
                <td class="table-border" id="hostPoints"><s:property value="hostLeagueRank.points"/></td>
              </tr>
              <tr style="color:#903" bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                <td style="background:#633;color:white;line-height:2;"><s:property value="visitName"/></td>
                <td id="visitRank"><s:property value="visitLeagueRank.rank"/></td>
               
                <td id="visitWinRound"><s:property value="visitLeagueRank.winRound"/></td>
                <td id="visitDrowRound"><s:property value="visitLeagueRank.drowRound"/></td>
                <td id="visitLoseRound"><s:property value="visitLeagueRank.loseRound"/></td>
                <td id="visitWinNum"><s:property value="visitLeagueRank.winNum"/></td>
                <td id="visitLoseNum"><s:property value="visitLeagueRank.loseNum"/></td>
                <td id="visitJing">${visitLeagueRank.winNum - visitLeagueRank.loseNum}</td>
                <td id="visitAllRound"><s:property value="visitLeagueRank.allRound"/></td>
		<td id="visitPoints"><s:property value="visitLeagueRank.points"/></td>
              </tr>
        </table>

      </div>
      </s:if>  
      <div class="selectduizheng">
           <ul>
              <li class="left"></li>
              <li class="middle">
                 <ul>
                   <li style="font-size:14px; font-weight:bold;color:#8f0303;float:left;margin-right:350px;">双方对阵</li>
                    <li class="tab tab-current" style="float:left;" id="li1"><a href="javascript:fixed(0);" onfocus="this.blur();">主客不固定</a></li>
                    <li class="tab" id="li2" style="float:left;"><a href="javascript:fixed(1);" onfocus="this.blur();">主客固定</a></li>
                 </ul>
              </li>
              <li class="right"></li>
           </ul>
      </div>
      <div class="clear"></div>
      <div class="shuangfangduizhen" >
        <div  id="spf1" style="text-align:center">
        	<s:iterator value="battleListMapList">
        		<span style="color:red;font-size:14px;font-weight:bold;"><s:property value="winNum"/></span>胜
        		<span style="color:green;font-size:14px;font-weight:bold;"><s:property value="drowNum"/></span>平
        		<span style="color:#36f;font-size:14px;font-weight:bold;"><s:property value="loseNum"/></span>负
        	</s:iterator>
        </div>
        
        <div  id="spf2" style="text-align:center">
        	<s:iterator value="fixedBattleListMapList">
        		<span style="color:red;font-size:14px;font-weight:bold;"><s:property value="winNum"/></span>胜
        		<span style="color:green;font-size:14px;font-weight:bold;"><s:property value="drowNum"/></span>平
        		<span style="color:#3366ff;font-size:14px;font-weight:bold;"><s:property value="loseNum"/></span>负
        	</s:iterator>
        </div>
      	<div id="notFixed">
   		  <table width="100%" border="1" cellspacing="2" cellpadding="1">
          <tr>
            <th scope="col">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" style="float:left;">
          <tr style="background:#E9EBDE;line-height:2;height:28px;font-weight:bold;">
            <th width="110" scope="col">赛事</th>
            <th width="262" scope="col">主队</th>
            <th width="20"></th>
            <th width="202" scope="col">客队</th>
            <th width="134" scope="col">比分</th>
            <th width="232" scope="col">时间</th>
          </tr>
          <s:iterator value="battleList">
          <tr height="25"bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
			<td id="leagueName" class="table-border"  style="background:#f63;color:#fff;">${league.name}</td>
			<td id="hostName" class="table-border" ><span class="red">${hostTeam.name}</span></td>
            <td><span style="color:#F30">vs</span></td>
			<td id="visitName" class="table-border" ><span class="green">${visitTeam.name}</span></td>
			<td id="bifen" class="table-border" >
				<s:if test="hostScore > visitScore">
					<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
				</s:if>
				<s:elseif test="hostScore == visitScore">
					<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
				</s:elseif>
				<s:else>
					<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
				</s:else>
			</td>
			<td id="time" class="table-border" ><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
          </s:iterator>
        </table></th>
          </tr>
        </table>     
        </div> 
        
        <div id="fixed">
       	  <table width="100%" border="1" cellspacing="2" cellpadding="1">
          <tr>
            <th scope="col"><table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" style="float:left;">
          <tr style="background:#E9EBDE;line-height:2;height:28px;font-weight:bold;">
            <th width="108" scope="col">赛事</th>
            <th width="264" scope="col">主队</th>
            <th width="20"></th>
            <th width="202" scope="col">客队</th>
            <th width="133" scope="col">比分</th>
            <th width="233" scope="col">时间</th>
          </tr>
          <s:iterator value="fixedBattleList">
          <tr height="25" bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
			<td id="leagueName" class="table-border"  style="background:#f63;color:#fff;">${league.name}</td>
			<td id="hostName" class="table-border" ><span class="red">${hostTeam.name}</span></td>
            <td><span style="color:#F30">vs</span></td>
			<td id="visitName" class="table-border" ><span class="green">${visitTeam.name}</span></td>
			<td id="bifen" class="table-border" ><span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span></td>
			<td id="time" class="table-border" ><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
          </s:iterator>
        </table></th>
          </tr>
        </table> 
        </div>  
      </div>
      <div class="clear"></div>
      <div class="selectduizheng">
           <ul>
              <li class="left"></li>
              <li class="middle" style="text-align:center;font-size:14px; font-weight:bold;color:#8f0303;">
                 近期战绩
              </li>
              <li class="right"></li>
           </ul>
      </div>
      <div class="compare-table">
        <table width="100%" border="1" cellspacing="1" cellpadding="0" align="center" valign="top">
          <tr>
            <th scope="col" class="border-left" style="text-align:left" valign="top"><div class="table-left">
             <ul>
               <li class="left-name">
               <s:property value="hostName"/>近期
               <select name="" onchange="hostNumSelect(1);" id="host">
               <option>10</option>
               <option>20</option>
               </select>场战绩&nbsp;&nbsp;&nbsp;<input id="check1" type="checkbox" fix="false" onclick="javascript:onlyhost(0);"/>仅主场
               </li>              
               <li class="zhuzhuangtu">
                	<div id="host1">胜平负
                		<s:iterator value="hostTenOverListTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh1"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh2"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh3"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host11">盘势
                		<s:iterator value="hostTenOverListTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh4"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh5"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh6"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host2">胜平负
                		<s:iterator value="hostTenOverListFixTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh7"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh8"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh9"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host22">盘势
                		<s:iterator value="hostTenOverListFixTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh10"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh11"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh12"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host3">胜平负
                		<s:iterator value="hostSixOverListTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh13"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh14"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh15"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host33">盘势
                		<s:iterator value="hostSixOverListTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh16"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh17"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh18"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host4">胜平负
                		<s:iterator value="hostSixOverListFixTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh19"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh20"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh21"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="host44">盘势
                		<s:iterator value="hostSixOverListFixNoTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanh22"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanh23"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanh24"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                </li>             
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI2">   
				<canvas id="canvasMyID2" height="180" width="230"></canvas>

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
    			 title  : '竟彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh1").text(),$("#spanh2").text(),$("#spanh3").text()]]
				};
				$('#canvasMyID2').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostTenNoResult" id="htnr" status="h">
					<span id="htnr_<s:property value='#h.index'/>"><s:property value="htnr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI3">   
				<canvas id="canvasMyID3" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#htnr_9").text(),$("#htnr_8").text(),$("#htnr_7").text(),$("#htnr_6").text(),$("#htnr_5").text(),$("#htnr_4").text(),$("#htnr_3").text(),$("#htnr_2").text(),$("#htnr_1").text(),$("#htnr_0").text()]]
				};
				$('#canvasMyID3').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI4">   
				<canvas id="canvasMyID4" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh4").text(),$("#spanh5").text(),$("#spanh6").text()]]
				};
				$('#canvasMyID4').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostTenResult" id="htr" status="h">
					<span id="htr_<s:property value='#h.index'/>"><s:property value="htr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI5">   
				<canvas id="canvasMyID5" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#htr_9").text(),$("#htr_8").text(),$("#htr_7").text(),$("#htr_6").text(),$("#htr_5").text(),$("#htr_4").text(),$("#htr_3").text(),$("#htr_2").text(),$("#htr_1").text(),$("#htr_0").text()]]
				};
				$('#canvasMyID5').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI6">   
				<canvas id="canvasMyID6" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh7").text(),$("#spanh8").text(),$("#spanh9").text()]]
				};
				$('#canvasMyID6').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostTenNoFixResult" id="htnfr" status="h">
					<span id="htnfr_<s:property value='#h.index'/>"><s:property value="htnfr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI7">   
				<canvas id="canvasMyID7" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#htnfr_9").text(),$("#htnfr_8").text(),$("#htnfr_7").text(),$("#htnfr_6").text(),$("#htnfr_5").text(),$("#htnfr_4").text(),$("#htnfr_3").text(),$("#htnfr_2").text(),$("#htnfr_1").text(),$("#htnfr_0").text()]]
				};
				$('#canvasMyID7').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI8">   
				<canvas id="canvasMyID8" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh10").text(),$("#spanh11").text(),$("#spanh12").text()]]
				};
				$('#canvasMyID8').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostTenFixResult" id="htfr" status="h">
					<span id="htfr_<s:property value='#h.index'/>"><s:property value="htfr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI9">   
				<canvas id="canvasMyID9" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#htfr_9").text(),$("#htfr_8").text(),$("#htfr_7").text(),$("#htfr_6").text(),$("#htfr_5").text(),$("#htfr_4").text(),$("#htfr_3").text(),$("#htfr_2").text(),$("#htfr_1").text(),$("#htfr_0").text()]]
				};
				$('#canvasMyID9').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI10">   
				<canvas id="canvasMyID10" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh13").text(),$("#spanh14").text(),$("#spanh15").text()]]
				};
				$('#canvasMyID10').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostSixNoResult" id="hsnr" status="h">
					<span id="hsnr_<s:property value='#h.index'/>"><s:property value="hsnr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI11">   
				<canvas id="canvasMyID11" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#hsnr_19").text(),$("#hsnr_18").text(),$("#hsnr_17").text(),$("#hsnr_16").text(),$("#hsnr_15").text(),$("#hsnr_14").text(),$("#hsnr_13").text(),$("#hsnr_12").text(),$("#hsnr_11").text(),$("#hsnr_10").text(),
  						$("#hsnr_9").text(),$("#hsnr_8").text(),$("#hsnr_7").text(),$("#hsnr_6").text(),$("#hsnr_5").text(),$("#hsnr_4").text(),$("#hsnr_3").text(),$("#hsnr_2").text(),$("#hsnr_1").text(),$("#hsnr_0").text()]]
				};
				$('#canvasMyID11').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI12">   
				<canvas id="canvasMyID12" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh16").text(),$("#spanh17").text(),$("#spanh18").text()]]
				};
				$('#canvasMyID12').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostSixResult" id="hsr" status="h">
					<span id="hsr_<s:property value='#h.index'/>"><s:property value="hsr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left">
			<div class="chart" id="canvasMyI13">   
				<canvas id="canvasMyID13" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#hsr_19").text(),$("#hsr_18").text(),$("#hsr_17").text(),$("#hsr_16").text(),$("#hsr_15").text(),$("#hsr_14").text(),$("#hsr_13").text(),$("#hsr_12").text(),$("#hsr_11").text(),$("#hsr_10").text(),
  						$("#hsr_9").text(),$("#hsr_8").text(),$("#hsr_7").text(),$("#hsr_6").text(),$("#hsr_5").text(),$("#hsr_4").text(),$("#hsr_3").text(),$("#hsr_2").text(),$("#hsr_1").text(),$("#hsr_0").text()]]
				};
				$('#canvasMyID13').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
            <div class="clear"></div>
			
			<li style="float:left;"><div class="chart" id="canvasMyI14">   
				<canvas id="canvasMyID14" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh19").text(),$("#spanh20").text(),$("#spanh21").text()]]
				};
				$('#canvasMyID14').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostSixNoFixResult" id="hsnfr" status="h">
					<span id="hsnfr_<s:property value='#h.index'/>"><s:property value="hsnfr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI15">   
				<canvas id="canvasMyID15" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#hsnfr_19").text(),$("#hsnfr_18").text(),$("#hsnfr_17").text(),$("#hsnfr_16").text(),$("#hsnfr_15").text(),$("#hsnfr_14").text(),$("#hsnfr_13").text(),$("#hsnfr_12").text(),$("#hsnfr_11").text(),$("#hsnfr_10").text(),
  						$("#hsnfr_9").text(),$("#hsnfr_8").text(),$("#hsnfr_7").text(),$("#hsnfr_6").text(),$("#hsnfr_5").text(),$("#hsnfr_4").text(),$("#hsnfr_3").text(),$("#hsnfr_2").text(),$("#hsnfr_1").text(),$("#hsnfr_0").text()]]
				};
				$('#canvasMyID15').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			<div class="clear"></div>
			<li style="float:left;"><div class="chart" id="canvasMyI16">   
				<canvas id="canvasMyID16" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanh22").text(),$("#spanh23").text(),$("#spanh24").text()]]
				};
				$('#canvasMyID16').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="hostSixFixResult" id="hsfr" status="h">
					<span id="hsfr_<s:property value='#h.index'/>"><s:property value="hsfr"/></span>
				</s:iterator>				
			</div>
			<li style="float:left;">
			<div class="chart" id="canvasMyI17">   
				<canvas id="canvasMyID17" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#hsfr_19").text(),$("#hsfr_18").text(),$("#hsfr_17").text(),$("#hsfr_16").text(),$("#hsfr_15").text(),$("#hsfr_14").text(),$("#hsfr_13").text(),$("#hsfr_12").text(),$("#hsfr_11").text(),$("#hsfr_10").text(),
  						$("#hsfr_9").text(),$("#hsfr_8").text(),$("#hsfr_7").text(),$("#hsfr_6").text(),$("#hsfr_5").text(),$("#hsfr_4").text(),$("#hsfr_3").text(),$("#hsfr_2").text(),$("#hsfr_1").text(),$("#hsfr_0").text()]]
				};
				$('#canvasMyID17').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div class="clear"></div>
			<li id="hten">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th>                      
                      </tr>
                      <s:iterator value="hostTenOverList" id="htenlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">                      
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#F08080;">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#8B1A1A;">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#548B54;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table>
                   </li>
                </ul>
               </li>
               
               <li id="hsix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                       <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th>                   
                      </tr>
                      <s:iterator value="hostSixOverList" id="hsixlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">                      
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#F08080;">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#8B1A1A;">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#548B54;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table>
                    </li>
                </ul>
               </li>
               
               <li id="htenfix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th>                   
                      </tr>
                      <s:iterator value="hostTenOverListFix" id="htenlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">                      
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#F08080;">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#8B1A1A;">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#548B54;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table>
                    </li>
                </ul>
               </li>
               
               <li id="hsixfix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7"  height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th>                       
                      </tr>
                      <s:iterator value="hostSixOverListFix" id="hsixlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">                      
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#F08080;">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#8B1A1A;">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#548B54;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table></li>
                </ul>
               </li>
             </ul>
          </div></th>
            <th scope="col" style="text-align:left" valign="top"><div class="table-right">
             <ul>
               <li class="left-name">
               <s:property value="visitName"/>近期
               <select name="" onchange="visitNumSelect(1);" id="visit">
               <option>10</option>
               <option>20</option>
               </select>场战绩&nbsp;&nbsp;&nbsp;<input id="check2" type="checkbox" onclick="javascript:onlyhost(1);"/>仅客场</li>
                <li class="zhuzhuangtu">
                	<div id="visit1">胜平负
                		<s:iterator value="visitTenOverListTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv1"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv2"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv3"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit11">盘势
                		<s:iterator value="visitTenOverListTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv4"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv5"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv6"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit2">胜平负
                		<s:iterator value="visitTenOverListFixTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv7"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv8"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv9"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit22">盘势
                		<s:iterator value="visitTenOverListFixTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv10"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv11"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv12"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit3">胜平负
                		<s:iterator value="visitSixOverListTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv13"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv14"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv15"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit33">盘势
                		<s:iterator value="visitSixOverListTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv16"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv17"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv18"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit4">胜平负
                		<s:iterator value="visitSixOverListFixTempMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv19"><s:property value="winNum"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv20"><s:property value="drowNum"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv21"><s:property value="loseNum"/></span>负
        				</s:iterator>
                	</div>
                	<div id="visit44">盘势
                		<s:iterator value="visitSixOverListFixTempNoMapList">
        					<span style="color:red;font-size:14px;font-weight:bold;" id="spanv22"><s:property value="winNo"/></span>胜
        					<span style="color:green;font-size:14px;font-weight:bold;" id="spanv23"><s:property value="drowNo"/></span>平
        					<span style="color:#36f;font-size:14px;font-weight:bold;" id="spanv24"><s:property value="loseNo"/></span>负
        				</s:iterator>
                	</div>
                </li>
               <div class="clear"></div>
               <li style="float:left;">
               <div class="chart" id="canvasMyIv2">   
                <canvas id="canvasMyIDv2" height="180" width="230"></canvas>
    
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
                 title  : '竞彩--胜平负',
                 labelX : ["胜","平","负"],
                 scaleY : {min: 0,max:10,gap:2},
                    type:'bar',
                 colorSet : $.jQchart.colorSets.red
                },
                data : [[$("#spanv1").text(),$("#spanv2").text(),$("#spanv3").text()]]
                };
                $('#canvasMyIDv2').jQchart(chartSetting);
    
                </script>      
            </div>
			   </li>
			
                <div style="display:none">
                    <s:iterator value="visitTenNoResult" id="vtnr" status="h">
                        <span id="vtnr_<s:property value='#h.index'/>"><s:property value="vtnr"/></span>
                    </s:iterator>				
               </div>
               <li style="float:LEFT;">
			   <div class="chart" id="canvasMyIv3">   
				<canvas id="canvasMyIDv3" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vtnr_9").text(),$("#vtnr_8").text(),$("#vtnr_7").text(),$("#vtnr_6").text(),$("#vtnr_5").text(),$("#vtnr_4").text(),$("#vtnr_3").text(),$("#vtnr_2").text(),$("#vtnr_1").text(),$("#vtnr_0").text()]]
				};
				$('#canvasMyIDv3').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
	           <DIV class="clear"></DIV>
			   <li style="float:left">
               <div class="chart" id="canvasMyIv4">   
				<canvas id="canvasMyIDv4" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv4").text(),$("#spanv5").text(),$("#spanv6").text()]]
				};
				$('#canvasMyIDv4').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   
			   <div style="display:none">
                    <s:iterator value="visitTenResult" id="vtr" status="h">
                        <span id="vtr_<s:property value='#h.index'/>"><s:property value="vtr"/></span>
                    </s:iterator>				
			   </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv5">   
				<canvas id="canvasMyIDv5" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vtr_9").text(),$("#vtr_8").text(),$("#vtr_7").text(),$("#vtr_6").text(),$("#vtr_5").text(),$("#vtr_4").text(),$("#vtr_3").text(),$("#vtr_2").text(),$("#vtr_1").text(),$("#vtr_0").text()]]
				};
				$('#canvasMyIDv5').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
            <div class="clear"></div>
			
			   <li style="float:left">
               <div class="chart" id="canvasMyIv6">   
				<canvas id="canvasMyIDv6" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv7").text(),$("#spanv8").text(),$("#spanv9").text()]]
				};
				$('#canvasMyIDv6').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitTenNoFixResult" id="vtnfr" status="h">
					<span id="vtnfr_<s:property value='#h.index'/>"><s:property value="vtnfr"/></span>
				</s:iterator>				
			   </div>
		       <li style="float:left;">
			   <div class="chart" id="canvasMyIv7">   
				<canvas id="canvasMyIDv7" height="180" width="230"></canvas>

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
    			 title  : '竞彩--胜平负',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vtnfr_9").text(),$("#vtnfr_8").text(),$("#vtnfr_7").text(),$("#vtnfr_6").text(),$("#vtnfr_5").text(),$("#vtnfr_4").text(),$("#vtnfr_3").text(),$("#vtnfr_2").text(),$("#vtnfr_1").text(),$("#vtnfr_0").text()]]
				};
				$('#canvasMyIDv7').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   <div class="clear"></div>
			   <li style="float:left;">
               <div class="chart" id="canvasMyIv8">   
				<canvas id="canvasMyIDv8" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:10,gap:2},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv10").text(),$("#spanv11").text(),$("#spanv12").text()]]
				};
				$('#canvasMyIDv8').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitTenFixResult" id="vtfr" status="h">
					<span id="vtfr_<s:property value='#h.index'/>"><s:property value="vtfr"/></span>
				</s:iterator>				
			   </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv9">   
				<canvas id="canvasMyIDv9" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vtfr_9").text(),$("#vtfr_8").text(),$("#vtfr_7").text(),$("#vtfr_6").text(),$("#vtfr_5").text(),$("#vtfr_4").text(),$("#vtfr_3").text(),$("#vtfr_2").text(),$("#vtfr_1").text(),$("#vtfr_0").text()]]
				};
				$('#canvasMyIDv9').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   <div class="clear"></div>
	  		   <li style="float:left;">
               <div class="chart" id="canvasMyIv10">   
				<canvas id="canvasMyIDv10" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv13").text(),$("#spanv14").text(),$("#spanv15").text()]]
				};
				$('#canvasMyIDv10').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitSixNoResult" id="vsnr" status="h">
					<span id="vsnr_<s:property value='#h.index'/>"><s:property value="vsnr"/></span>
				</s:iterator>				
			  </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv11">   
				<canvas id="canvasMyIDv11" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vsnr_19").text(),$("#vsnr_18").text(),$("#vsnr_17").text(),$("#vsnr_16").text(),$("#vsnr_15").text(),$("#vsnr_14").text(),$("#vsnr_13").text(),$("#vsnr_12").text(),$("#vsnr_11").text(),$("#vsnr_10").text(),
  						$("#vsnr_9").text(),$("#vsnr_8").text(),$("#vsnr_7").text(),$("#vsnr_6").text(),$("#vsnr_5").text(),$("#vsnr_4").text(),$("#vsnr_3").text(),$("#vsnr_2").text(),$("#vsnr_1").text(),$("#vsnr_0").text()]]
				};
				$('#canvasMyIDv11').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   <div class="clear"></div>
			   <li style="float:left;">
               <div class="chart" id="canvasMyIv12">   
				<canvas id="canvasMyIDv12" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv16").text(),$("#spanv17").text(),$("#spanv18").text()]]
				};
				$('#canvasMyIDv12').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitSixResult" id="vsr" status="h">
					<span id="vsr_<s:property value='#h.index'/>"><s:property value="vsr"/></span>
				</s:iterator>				
			  </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv13">   
				<canvas id="canvasMyIDv13" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vsr_19").text(),$("#vsr_18").text(),$("#vsr_17").text(),$("#vsr_16").text(),$("#vsr_15").text(),$("#vsr_14").text(),$("#vsr_13").text(),$("#vsr_12").text(),$("#vsr_11").text(),$("#vsr_10").text(),
  						$("#vsr_9").text(),$("#vsr_8").text(),$("#vsr_7").text(),$("#vsr_6").text(),$("#vsr_5").text(),$("#vsr_4").text(),$("#vsr_3").text(),$("#vsr_2").text(),$("#vsr_1").text(),$("#vsr_0").text()]]
				};
				$('#canvasMyIDv13').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   <div class="clear"></div>
			   <li style="float:left;">
               <div class="chart" id="canvasMyIv14">   
				<canvas id="canvasMyIDv14" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv19").text(),$("#spanv20").text(),$("#spanv21").text()]]
				};
				$('#canvasMyIDv14').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitSixNoFixResult" id="vsnfr" status="h">
					<span id="vsnfr_<s:property value='#h.index'/>"><s:property value="vsnfr"/></span>
				</s:iterator>				
			  </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv15">   
				<canvas id="canvasMyIDv15" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vsnfr_19").text(),$("#vsnfr_18").text(),$("#vsnfr_17").text(),$("#vsnfr_16").text(),$("#vsnfr_15").text(),$("#vsnfr_14").text(),$("#vsnfr_13").text(),$("#vsnfr_12").text(),$("#vsnfr_11").text(),$("#vsnfr_10").text(),
  						$("#vsnfr_9").text(),$("#vsnfr_8").text(),$("#vsnfr_7").text(),$("#vsnfr_6").text(),$("#vsnfr_5").text(),$("#vsnfr_4").text(),$("#vsnfr_3").text(),$("#vsnfr_2").text(),$("#vsnfr_1").text(),$("#vsnfr_0").text()]]
				};
				$('#canvasMyIDv15').jQchart(chartSetting);
	
				</script>      
			</div>
			   </li>
			   <div class="clear"></div>
			   <li style="float:left;">
               <div class="chart" id="canvasMyIv16">   
				<canvas id="canvasMyIDv16" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["胜","平","负"],
   				 scaleY : {min: 0,max:20,gap:4},
    				type:'bar',
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#spanv22").text(),$("#spanv23").text(),$("#spanv24").text()]]
				};
				$('#canvasMyIDv16').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
			
			<div style="display:none">
				<s:iterator value="visitSixFixResult" id="vsfr" status="h">
					<span id="vsfr_<s:property value='#h.index'/>"><s:property value="vsfr"/></span>
				</s:iterator>				
			  </div>
			   <li style="float:left;">
			   <div class="chart" id="canvasMyIv17">   
				<canvas id="canvasMyIDv17" height="180" width="230"></canvas>

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
    			 title  : '竞彩--盘势',
   				 labelX : ["","","","","","","","","","","","","","","","","","",""],
   				 scaleY : {min: 0,max:3,gap:1},
   				 colorSet : $.jQchart.colorSets.red
  				},
  				data : [[$("#vsfr_19").text(),$("#vsfr_18").text(),$("#vsfr_17").text(),$("#vsfr_16").text(),$("#vsfr_15").text(),$("#vsfr_14").text(),$("#vsfr_13").text(),$("#vsfr_12").text(),$("#vsfr_11").text(),$("#vsfr_10").text(),
  						$("#vsfr_9").text(),$("#vsfr_8").text(),$("#vsfr_7").text(),$("#vsfr_6").text(),$("#vsfr_5").text(),$("#vsfr_4").text(),$("#vsfr_3").text(),$("#vsfr_2").text(),$("#vsfr_1").text(),$("#vsfr_0").text()]]
				};
				$('#canvasMyIDv17').jQchart(chartSetting);
	
				</script>      
			</div>
			</li>
            <div class="clear"></div>           
            <li id="vten">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th> 
                      </tr>
                      <s:iterator value="visitTenOverList" id="vtenlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#FF6EB4;">${hostTeam.rank }</span>)</td>
                        <td><span class="red">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#CD00CD;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table></li>
                </ul>
                </li>
                
                <li id="vsix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th> 
                      </tr>
                      <s:iterator value="visitSixOverList" id="vsixlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#FF6EB4;">${hostTeam.rank }</span>)</td>
                        <td><span class="red">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#CD00CD;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table></li>
                </ul>
                </li>
                
                <li id="vtenfix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th> 
                      </tr>
                      <s:iterator value="visitTenOverListFix" id="vtenlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#FF6EB4;">${hostTeam.rank }</span>)</td>
                        <td><span class="red">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#CD00CD;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table></li>
                </ul>
                </li>
                
                <li id="vsixfix">
                <ul class="duizhen">
                    <li><table width="100%" border="1" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" height="25">
                        <th width="50" scope="col">赛事</th>                      
                        <th width="60" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="60" scope="col">客队</th>
                        <th width="50" scope="col">比分</th>
                        <th width="80" scope="col">盘口</th>
                        <th width="30" scope="col">盘势</th>
                        <th width="64" scope="col">日期</th> 
                      </tr>
                      <s:iterator value="visitSixOverListFix" id="vsixlist">
                      <tr style="height:38px;"  onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:#36f;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#FF6EB4;">${hostTeam.rank }</span>)</td>
                        <td><span class="red">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#CD00CD;">${visitTeam.rank }</span>)</td>
						<td>
							<s:if test="hostScore > visitScore">
								<span class="red">${hostScore}</span> ：<span class="green">${visitScore}</span>
							</s:if>
							<s:elseif test="hostScore == visitScore">
								<span class="blue">${hostScore}</span> ：<span class="blue">${visitScore}</span>
							</s:elseif>
							<s:else>
								<span class="green">${hostScore}</span> ：<span class="red">${visitScore}</span>
							</s:else>
						</td>
						<td>${pankouOther}</td>
						<td>${panshi}</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd"/></td>
                      </tr>
                      </s:iterator>
                    </table></li>
                </ul>
                </li>
             </ul>
          </div></th>
          </tr>
        </table>
      </div>
      <div class="clear"></div>
      <div class="selectduizheng">
           <ul>
              <li class="left"></li>
              <li class="middle" style="text-align:center;font-size:14px; font-weight:bold;color:#8f0303;">
                 未来赛事
              </li>
              <li class="right"></li>
           </ul>
      </div>
      <div class="compare-table">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col"><div class="table-left">
             <ul>
               <li class="left-name">
               <s:property value="hostName"/>近期
               </li>
               
                <li class="duizhen">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7;" cellspacing="1">
                        <th width="60" height="35" scope="col">赛事</th>                      
                        <th width="100" height="35" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="100" height="35" scope="col">客队</th>
                        <th width="120" height="35" scope="col">日期</th>
                      </tr>
                      <s:iterator value="hostFeatureList" status="st">
                      <s:if test="#st.index >= 1">
                      <tr style="height:35px;" bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:green;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#f60">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#C09">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#698B22">${visitTeam.rank }</span>)</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      </s:if>
                      </s:iterator>
                    </table>

                </li>
             </ul>
          </div></th>
            <th scope="col"><div class="table-right">
             <ul>
               <li class="left-name">
               <s:property value="visitName"/>近期
               </li>
                
                <li class="duizhen">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr style="background:#DDE8D7" cellspacing="1">
                        <th width="60" height="35" scope="col">赛事</th>                      
                        <th width="100" height="35" scope="col">主队</th>
                        <th width="20"></th>
                        <th width="100" height="35" scope="col">客队</th>
                        <th width="120" height="35" scope="col">日期</th>
                      </tr>
                      <s:iterator value="visitFeatureList" status="st">
					  <s:if test="#st.index >= 1">
                      <tr  style="height:35px;" bordercolor="#eeeeee" onmouseout="this.bgColor='#FFFFFF';" onmouseover="this.bgColor='#FAFAD2';">
                        <td style="background:green;color:#fff;">${league.name}</td>
						<td>${hostTeam.name}(<span style="color:#f60">${hostTeam.rank }</span>)</td>
                        <td><span style="color:#C09">vs</span></td>
						<td>${visitTeam.name}(<span style="color:#698B22">${visitTeam.rank }</span>)</td>
						<td><s:date name="matchStartTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      </s:if>
                      </s:iterator>
                    </table>
                </li>
             </ul>
          </div>
          </th>
          </tr>
         </table>
      </div>
      <div class="clear"></div>
</div>

    <!--right end-->
<!--the web title end-->
<%@include file="/foot.jsp" %>
</body>
</html>