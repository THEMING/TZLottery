<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>足彩6场半${result.termNo}期开奖详情_中奖号码-一彩票</title>
<meta name="Keywords" content="足彩6场半${result.termNo}开奖,足彩6场半${result.termNo}中奖号码,足彩6场半${result.termNo}开奖详情" />
<meta name="Description" content="一彩票第一时间为您提供准确的足彩6场半${result.termNo}开奖,全面的足彩6场半${result.termNo}中奖号码信息，以及权威的足彩6场半${result.termNo}开奖详情,中奖分布等方便您查阅兑奖，祝您中大奖！" />
<link href="../css/prize_detail.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/common.js"></script>
</head>
<body>
<!-- head -->
<%@include  file="../head.jsp"%>

<div class = "container">
	<div class="prize_main">
               
        <%@include  file="./left_navi_detail.jsp"%>        

		<div class="right_box_info">
			<h3>一彩票首页 &gt; 开奖公告 &gt; 足彩 &gt; 足彩6场半开奖详情</h3>
           	<div class="right_box_title">
                <h4 class="pic_14sfc"></h4>
                <h4 style="padding-left:15px">足彩6场半开奖信息</h4><h2>&nbsp[不定期开奖]</h2>               
			</div>

			<div class="right_box_cont">
				<div class="right_box_cont_top">
			  		<p class="blue_bor_b">
						<em id="termDate">开奖日期：<s:date name="result.openPrizeTime" format="yyyy年MM月dd日"/></em>
						足彩6场半 第 <strong class="red f_14" id="termNO">${result.termNo}</strong> 期
						<select onchange="onsub()" id="term_no" name="term_no" style="margin-left:10px">
							<option >查看历史开奖记录</option>
							<s:iterator value="termArray" id="rs">
								<option value="${rs }">${rs }</option>
							</s:iterator>
						</select>
					</p>
                    
					<div class="prize_result">
					  	<em>开奖结果：</em>
					  	
					  	<table id="mResult" width="500">
					  				
					  			
					  			<s:iterator value="matchResultList1" status="st">
					  				<tr>
					  					<td id="homeTeam" class="result" width="100">${homeTeam}</td>
					  					<td width="100">VS</td>
					  					<td id="awaryTeam" class="result" width="100">${awaryTeam}</td>
					  					<s:iterator value="list1">
					  					<td id="matchResult" class="result">${fn:substring(result,st.index*4,st.index*4+1)}</td>
					  					</s:iterator>
					  					<s:iterator value="list1">
					  					<td id="matchResult" class="result">${fn:substring(result,st.index*4+2,st.index*4+3)}</td>
					  					</s:iterator>
					  				</tr>
					  			</s:iterator>
					  		
					  	</table>
						<div style="float: right;"><a href="/prizedetail/6cbindex.htm" target="_blank">开奖历史</a> | <a href="/direction/plt.htm?tt=cc&type=6cb&football=football" target="_blank">走势图</a> </div>
			  		</div>
					
					<div class="clear"></div>
                    <div class="operation">
						<input type="image" src="../images/369caibg/369cai_40.gif" class="btn_dg" value="立即投注" onclick="parent.location.href='/lottery/index.htm?lotteryType=6cb'"/>                   	
                    </div>
                    <p>本期销量：<strong  class="red" id="termTotalSale"><fmt:formatNumber type="number" value="${result.totalSale}" pattern="###,###,###"/></strong>元　　　奖池奖金：<strong class="red" id="termPrizePool"><fmt:formatNumber type="number" value="${result.prizePool}" pattern="###,###,###"/></strong>元</p>
                </div>
                
                
                <table class= "prize_detail_tab" cellSpacing=0 cellPadding=0>
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
                </table>
			</div>
            
         </div>
	</div>
    <div class = "clear"></div>
    <%@include  file="/foot.jsp"%>
</div>
</body>
</html>

<script type="text/javascript">
function onsub(){
var term=$("#term_no option:selected").val();

  $.LotteryTerm.callback = function(json){
        
       $("#termNO").text(json.termno);  
       $("#termDate").text("开奖日期："+json.openPrizeTime+" ");
		
		

		
		var str="";
		$(json.matchResultList1).each(function(i){
			str += "<tr><td class=\"result\">"+this.homeTeam.replace("??","")+"</td><td >VS</td><td class=\"result\">"+this.awaryTeam.replace("??","")+"</td><td class=\"result\">" +$(json.result.split(","))[i*2].replace("??","")+"</td><td class=\"result\">"+$(json.result.split(","))[i*2+1].replace("??","")+"</td></tr>";
		})
		$("#mResult").html(str);
		
		
		
		
		$("#termOrderResult").text("出球顺序："+json.orderResult);
		$("#termTotalSale").html(json.totalSale);
		$("#termPrizePool").text(json.prizePool);
		str="";
		var i=1;
			$(json.prizeLevel).each(function(){
				$("#r_name_"+i).text(this.name);
				$("#r_betNum_"+i).text(this.betnum);
				$("#r_prize_"+i).text(this.prize);
				i++;
			});

	};
 $.LotteryTerm._request({action:'ajaxTermInfo',term: term,type:'6cb'});
}
</script>
                           		