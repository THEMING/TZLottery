<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>双色球${result.termNo}期开奖详情_中奖号码-一彩票</title>
<meta name="Keywords" content="双色球${result.termNo}开奖,双色球${result.termNo}中奖号码,双色球${result.termNo}开奖详情" />
<meta name="Description" content="一彩票第一时间为您提供准确的双色球${result.termNo}开奖,全面的双色球${result.termNo}中奖号码信息，以及权威的双色球${result.termNo}开奖详情,中奖分布等方便您查阅兑奖，祝您中大奖！" />
<link href="./css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./css/common.css" type="text/css" />
<LINK href="./chart/favicon.ico" type=image/x-icon rel="shortcut icon"/>
<link rel="stylesheet" href="./css/default.css" type="text/css" />
<link rel="stylesheet" href="./css/css.css" type="text/css" />

<script src="./js/jquery-1.4.4.min.js"></script>
<script src="./js/lottery/common.js"></script>
<script type="text/javascript">
	(function ($) {
	$.LotteryTest = {
		url : "/lottery/lotteryterm.htm",
		_request : function(param) {
			$.getJSON($.LotteryTerm.url, param, $.LotteryTerm.callback);
		},
		callback : function(){}
	};
	})(jQuery);
	
	function onsub()
	{
		$.LotteryTerm.callback = function(json){
			$("#time").text(json.stopSendPrizeTime);
			var i=1;
			$(json.prizeLevel).each(function(){
				$("#r_name_"+i).text(this.name);
				$("#r_betNum_"+i).text(this.betnum);
				$("#r_prize_"+i).text(this.prize);
				i++;
			});
		};
		$.LotteryTerm._request({action:'ajaxKaiJiangDetailForPhone',type:'ssq'});
	}
</script>
</head>
<body>
	<input type="button" onclick="onsub();" value="AJAX"/>
	<div id="time"></div>
	<table class= "prize_detail_tab" cellspacing=0 cellpadding=0>
                   	<caption style="caption-side:top">开奖详情</caption>
					<tr class = tit>
                       	<td width="21%">奖项</td>
                        <td width="20%">中奖注数</td>
                        <td width="59%">每注奖金</td>
                    </tr>
                   	<tr>
                       	<td id="r_name_1">一等奖</td>
                        <td id="r_betNum_1">${result.prizeLevels[0].betNum}</td>
                        <td id="r_prize_1"><fmt:formatNumber type="number" value="${result.prizeLevels[0].prize}" pattern="###,###,##0.00"/>元</td>
                    </tr>
                   	<tr>
                   	    <td id="r_name_2">二等奖<br /></td>
                   	    <td id="r_betNum_2">${result.prizeLevels[1].betNum}</td>
                   	    <td id="r_prize_2"><fmt:formatNumber type="number" value="${result.prizeLevels[1].prize}" pattern="###,###,##0.00"/>元</td>
                 	</tr>
                   	<tr>
                   	    <td id="r_name_3">三等奖<br /></td>
                   	    <td id="r_betNum_3">${result.prizeLevels[2].betNum}</td>
                   	    <td id="r_prize_3"><fmt:formatNumber type="number" value="${result.prizeLevels[2].prize}" pattern="###,###,##0.00"/>元</td>
                 	</tr>
                   	<tr>
                   	    <td id="r_name_4">四等奖<br /></td>
                   	    <td id="r_betNum_4">${result.prizeLevels[3].betNum}</td>
                   	    <td id="r_prize_4"><fmt:formatNumber type="number" value="${result.prizeLevels[3].prize}" pattern="###,###,##0.00"/>元</td>
                 	</tr>
                   	<tr>
                   	    <td id="r_name_5">五等奖<br /></td>
                   	    <td id="r_betNum_5">${result.prizeLevels[4].betNum}</td>
                   	    <td id="r_prize_5"><fmt:formatNumber type="number" value="${result.prizeLevels[4].prize}" pattern="###,###,##0.00"/>元</td>
                 	</tr>
                   	<tr>
                   	    <td id="r_name_6">六等奖<br /></td>
                   	    <td id="r_betNum_6">${result.prizeLevels[5].betNum}</td>
                   	    <td id="r_prize_6"><fmt:formatNumber type="number" value="${result.prizeLevels[5].prize}" pattern="###,###,##0.00"/>元</td>
                 	</tr>
                </table>
</body>
</html>

