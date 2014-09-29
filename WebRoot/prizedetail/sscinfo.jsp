<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超级大乐透${result.termNo}期开奖详情_中奖号码-一彩票</title>
<meta name="Keywords" content="超级大乐透${result.termNo}开奖, 大乐透${result.termNo}开奖号码,大乐透${result.termNo}开奖详情" />
<meta name="Description" content="一彩票第一时间为您提供准确的大乐透${result.termNo}期开奖详情,全面的大乐透${result.termNo}期开奖信息，方便您查阅兑奖，祝您中大奖！" />
<link href="../styles/user.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script src="../js/jquery-1.4.2.min.js"></script>
<script src="../js/general/lotteryTerm.js"></script>
</head>
<body>
	<div class="wrap">
    	<%@include  file="/head.jsp"%>
        <div class="bd">
       	  <div class="draw_sd">
            	<h3>开奖详情</h3>
                <div class="draw_sd_t">
               	  <h4 class="hover">按分类查看</h4>
                </div>
                <div class="c_l"></div>
                <div class="">
                	<dl>
                   	  <dt>全国福利彩票</dt>
                          <dd>
                            	<p><a href="/drawlott/ssq_.htm">双色球</a></p>
                                <p><a href="/drawlott/3d_.htm">福彩3D</a></p>
                      </dd>
                      <dt>全国体育彩票</dt>
                            <dd>
                            	<p><a href="/drawlott/dlt_.htm">超级大乐透</a></p>
                            	<p><a href="/drawlott/pls_.htm">排列3</a></p>
                           		<p><a href="/drawlott/plw_.htm">排列5</a></p>
                            </dd>
                      <dt>高频彩种</dt>
					  	<dd>
							<p>重庆时时彩</p>
							<p><a href="/drawlott/ssl_.htm">上海时时乐</a></p>
						</dd>
                    </dl>
                </div>
          </div>
          <div class="draw_cont">
            	<h3>一彩票首页 &gt; 开奖公告 &gt;高频彩种 &gt; 重庆时时彩开奖详情</h3>
       	    <div class="draw_cont_t">
				<h4 class="d_ssc">时时彩开奖信息</h4>
				[每日开奖时间：10:00至22:00，10分钟一期；22:00至次日01:55，每5分钟一期]
            </div>
			<div class="draw_cont_f">
			  	<em>期号：
				<input type="text" class="input_nom" value="输入期号" size="18" />
				<input type="button" class="btn_hm" value="查询" onclick="location.href='/lottery/3d/'"/>
				</em>
				<span>日期：
			  	  <select onchange="onsub()" id="term_no" name="term_no">
					<option >2010-09-20</option>
					<s:iterator value="termArray" id="rs">
					<option value="${rs }">${rs }</option>
					</s:iterator>
				</select>
				</span>
			</div>
                 <div class="draw_main" style="width:665px;">
                 		<!--<div class="draw_main_top">
                        <p class="blue_bor_b"><em id="termDate">开奖日期：<s:date name="result.openPrizeTime" format="yyyy年MM月dd日"/> 
                        </em>时时彩 第 <strong class="red f_14" id="termNO">${result.termNo}</strong> 期</p>
                        <div class="draw_kj">
                        	<em>开奖号码：</em>
                            <ul id="termResult">
                                	<li class="org-ball">${fn:substring(result.result,0,2)}</li>
                                    <li class="org-ball">${fn:substring(result.result,3,5)}</li>
                                    <li class="org-ball">${fn:substring(result.result,6,8)}</li>
                                    <li class="org-ball">${fn:substring(result.result,9,11)}</li>
                                    <li class="org-ball">${fn:substring(result.result,12,14)}</li>
                            </ul>
                          </div>
						   <div class="draw_kj_a"><ul><li id="termOrderResult">出球顺序：${result.orderResult}</li></ul></div>
						<div class="c_l"></div>
                      <div class="draw_his">
                       	<input type="button" class="btn_dg" value="代购" onclick="location.href='/lottery/dlt/'"/>   
                           <input type="button" class="btn_hm" value="合买" onclick="location.href='/lottery/joinGroupBuy.htm?lotteryType=dlt'"/>
                            <a href="/drawlott/dltindex.htm" target="_blank">开奖历史</a> | <a href="/direction/dlt/" target="_blank">走势图</a> 
                        </div>
                        <p>本期销量：<strong  class="red" id="termTotalSale"><fmt:formatNumber type="number" value="${result.totalSale}" pattern="###,###,###"/></strong>元　　12选2销量：<strong class="red" id="termOthertotalSale"><fmt:formatNumber type="number" value="${result.otherTotalSale}" pattern="###,###,###"/></strong>元　　奖池滚存：<strong class="red" id="termPrizePool"><fmt:formatNumber type="number" value="${result.prizePool}" pattern="###,###,###"/></strong>元</p>
                        </div>-->
                    	<table>
                        	<caption>开奖详情</caption>
                        	<tr>
                            	<th width="20%">期号</th>
                                <th width="30%">开奖时间</th>
                                <th width="30%">中奖号码</th>
								<th width="12%">和值</th>
                                <th width="12%">走势</th>
                            </tr>
                        	<tr>
                            	<td>20110101001</td>
                                <td>2011-01-11 01:15 </td>
                                <td>
									<ul>
										<li class="org-ball">1</li>
										<li class="org-ball">2</li>
										<li class="org-ball">3</li>
										<li class="org-ball">4</li>
										<li class="org-ball">5</li>
									</ul>
								</td>
								<td>36</td>
                                <td><a href="/direction/ssq/" target="_blank"><img src="../images/user/kj_c.png"/></a></td>
                            </tr>
                        	
                        </table>
                        <p class="f_t"><input name="button" type="button" class="btn_l_b" onclick="location.href='/lottery/3d/'" value="在线购买"/></p>
            </div>
			
            </div>
            <div class="c_l"></div>
        </div>
    </div>
    <%@include  file="/foot.jsp"%>
</body>
</html>
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
		if(code.split("|")[1]){
					$(code.split("|")[1].split(",")).each(function(){
						str +="<li class=\"blue-ball\">"+this+"</li>";
					});
		}
		$("#termResult").html(str);
		$("#termOrderResult").text("出球顺序："+json.orderResult);
		$("#termTotalSale").html(json.totalSale);
		$("#termPrizePool").text(json.prizePool);
		$("#termOthertotalSale").text(json.othertotalSale);
		str="";
		var i=1;
			$(json.prizeLevel).each(function(){
				$("#r_betNum_"+i).text(this.betnum);
				$("#r_prize_"+i).text(this.prize);
				$("#r_betNum_"+i+""+i).text(this.addbetnum);
				$("#r_prize_"+i+""+i).text(this.addprize);
				i++;
			});
	};
 $.LotteryTerm._request({action:'ajaxTermInfo',term: term,type:'dlt'});
}
</script>
