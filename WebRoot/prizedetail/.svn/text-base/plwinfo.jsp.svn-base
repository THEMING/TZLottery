<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>排列5第${result.termNo}期开奖详情_排列五中奖号码-一彩票</title>
<meta name="Keywords" content="排列5第${result.termNo}开奖,排列五${result.termNo}开奖号码,排列五${result.termNo}开奖详情" />
<meta name="Description" content="一彩票第一时间为您提供准确的排列5第${result.termNo}期开奖详情,全面的排列五${result.termNo}期开奖信息，方便您查阅兑奖，祝您中大奖！" />
<link href="../css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />

<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/common.js"></script>
<script type="text/javascript">
function onsub(){
var term=$("#term_no option:selected").val();

  $.LotteryTerm.callback = function(json){
        
       $("#termNO").text(json.termno);  
       $("#termDate").text("开奖日期："+json.openPrizeTime+" "); 
       var code=json.result;
       var str="";
       if(code.split("|").length>0){
				$(code.split("|")[0].split(",")).each(function(){
					str +="<li class=\"red-ball\">"+this+"</li>";
				});
		}
		$("#termResult").html(str);
		$("#termTotalSale").html(json.totalSale);
		$("#termPrizePool").text(json.prizePool);
		str="";
		var i=1;
			$(json.prizeLevel).each(function(){
				$("#r_betNum_1").text(this.betnum);
				$("#r_prize_1").text(this.prize);
			});
	};
 $.LotteryTerm._request({action:'ajaxTermInfo',term: term,type:'plw'});
}
</script>

</head>
<body>
<!-- head -->
<%@include  file="../head.jsp"%>

<div class = "container">
	<div class="prize_main">
               
        <%@include  file="./left_navi_detail.jsp"%>                

		<div class="right_box_info">
			<h3>一彩票首页 &gt; 开奖公告 &gt; 体育彩票 &gt; 排列5开奖详情</h3>
           	<div class="right_box_title">
                <h4 class="pic_plw"></h4>
                <h4 style="padding-left:15px">排列5开奖信息</h4><h2>&nbsp[每日开奖]</h2>               
			</div>

			<div class="right_box_cont">
				<div class="right_box_cont_top">
			  		<p class="blue_bor_b">
						<em id="termDate">开奖日期：<s:date name="result.openPrizeTime" format="yyyy年MM月dd日"/></em>
						排列5 第 <strong class="red f_14" id="termNO">${result.termNo}</strong> 期
						<select onchange="onsub()" id="term_no" name="term_no" style="margin-left:10px">
							<option >查看历史开奖记录</option>
							<s:iterator value="termArray" id="rs">
								<option value="${rs }">${rs }</option>
							</s:iterator>
						</select>
					</p>
                    
					<div class="prize_result">
					  	<em>开奖结果：</em>
	                    <ul id="termResult">
	                        <li class="red-ball">${fn:substring(result.result,0,1)}</li>
                            <li class="red-ball">${fn:substring(result.result,2,3)}</li>
                            <li class="red-ball">${fn:substring(result.result,4,5)}</li>
                            <li class="red-ball">${fn:substring(result.result,6,7)}</li>
                            <li class="red-ball">${fn:substring(result.result,8,9)}</li>
	                    </ul>
						<div style="float: right;"><a href="/prizedetail/plwindex.htm" target="_blank">开奖历史</a> | <a href="direction/fbt.htm?type=plw" target="_blank">走势图</a> </div>
			  		</div>
					
					<div class="clear"></div>
                    <div class="operation">
						<input type="image" src="../images/369caibg/369cai_40.gif" class="btn_dg" value="立即投注" onclick="parent.location.href='/lottery/index.htm?lotteryType=plw'"/>                   	
                    </div>
                    <p>本期销量：<strong  class="red" id="termTotalSale"><fmt:formatNumber type="number" value="${result.totalSale}" pattern="###,###,###"/></strong>元</p>
                </div>
                
                
                <table class= "prize_detail_tab" cellSpacing=0 cellPadding=0>
                   	<caption style="caption-side:top">开奖详情</caption>
					<tr class = tit>
                       	<td width="21%">奖项</td>
                        <td width="20%">中奖注数</td>
                        <td width="59%">每注奖金</td>
                    </tr>
                   	<tr>
                    	<td id="r_name_1">直选</td>
                        <td id="r_betNum_1">${result.prizeLevels[0].betNum}</td>
                        <td id="r_prize_1">100,000元</td>
                    </tr>
                </table>
			</div>            
         </div>
	</div>
    <div class = "clear"></div>
    <%@include  file="/foot.jsp"%>
</div>
</body>
</html>