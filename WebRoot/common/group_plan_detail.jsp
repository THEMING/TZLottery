<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一彩票_合买_方案详情</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/group_plan_detail.css" rel="stylesheet" type="text/css" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon"/>
<link href="/css/default.css" type="text/css" rel="stylesheet" />
<link href="/css/css.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="/favicon.ico" /> 
<link rel="stylesheet" href="../css/default.css" type="text/css" />
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/js/ZJ-user.js"></script>
<script type="text/javascript" src="/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="/js/util/tab.js"></script>
<script type="text/javascript" src="/js/lottery/common.js"></script>
<script type="text/javascript" src="/js/lottery/quickBet.js"></script>
<link rel="stylesheet" href="/css/jquery-ui-1.8.16.custom.css"/>
<script src="/js/jquery.bgiframe-2.1.2.js"></script>
<script src="/js/jquery.ui.core.js"></script>
<script src="/js/jquery.ui.widget.js"></script>
<script src="/js/jquery.ui.mouse.js"></script>
<script src="/js/jquery.ui.button.js"></script>
<script src="/js/jquery.ui.position.js"></script>
<script src="/js/jquery.ui.dialog.js"></script>
<script src="/js/jquery.effects.core.js"></script>
<script type="text/javascript" src="/js/util/validate.js"></script>
<script type="text/javascript" src="/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript">
	$(document).ready(function()
		{
			$("#show_plan_detail_dev").attr("class", "hidden");
			indexlogin();
		}
	);
</script>


<script type="text/javascript">
	var show = false;
	function show_plan_detail()
	{
		if(show) {
			$("#show_plan_detail_dev").attr("class", "hidden");
			show = false;
			$("#show_detail").text("点击这里显示内容详情");
		}
		else {
			$("#show_plan_detail_dev").attr("class", "show");
			show = true;
			$("#show_detail").text("点击这里隐藏内容详情");
		}
	}
	
	function commun(per_money)
	{
		part = $("#numpart").val();
		if(parseInt(part) > 0)
		{
			$("#count").val(part * per_money);
		}
		else
		{
			alert("请输入正确的份数(份数必须大于0)");
		}
	}
</script>

<script type="text/javascript">
	  	var submiting = false;
		function toBuy(type,term,buypart,id)
		{
			loginType = 4;
			if("${customer.nickName}" == "") 
			{
				$("#dialog-form").dialog("open");
		  		refreshCaptcha2();
		  		return;
			}	
			else
			{ 
				goBuy(type,term,buypart,id);
			}
		}
   
   		function goBuy(type,term,buypart,id)
   		{
   			   	if($(buypart).val() == ""){
				   $(buypart).val("0");
				}
				if(!submiting) {
					submiting = true;
				}
				else {
				    return;
			    }
			    $.communityJoin.callback = function(json){
			    	ajaxResponse(json);
			    	location.reload() 
			    	issub=false;
			    };
			   	$.communityJoin._request({lotteryType:type,term: term,buyPart:$(buypart).val(),communityId:id});
   		}

   
		function ajaxResponse(json)
		{
	     	if(json.status == "_00000"){
	     		loadlogin();
				alert("请登录!");
				return;
	     	}
	     	switch(json.status)
	     	{
			case "_0001":
				alert(json.message);
			break;
			case "_10000":
				alert(json.message);
			break;
			case "_0000":
				alert("操作成功!");
			break;
			}
			if(parent.needreload==2)
			    parent.location.reload();
		}
</script>

</head>

<body>
<%@include file="/head.jsp"%>
<div class="nifty">
   <div class="rtop">
     <div class="r1"></div>
     <div class="r2"></div>
     <div class="r3"></div>
     <div class="r4"></div>
   </div>
 </div>
<div class="box">	
    <div class="bd">
    	<div class="group_buy">        	
        	<div class= "plan_detail_title">
                <h1>${community.term.type }</h1> 　 
	            <strong class="term">第<span class = "red">${community.term.termNo }</span>期</strong>	
				<strong class="term">合买截止时间：<span class="red"> <s:date name="community.term.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm:ss"/> </span></strong>
			</div>
        	
        	<table class="block-detail" border="0" cellpadding="0" cellspacing="0">
	        	<tr class="title">
		        	<th class="fengexian">方案标题：</th>
		            <td style="padding-left:16px;">
		            	<h4 class="tit">  <strong> <label class = "red">${community.term.type }</label> —— 第<label class = "red">${community.term.termNo }</label>期</strong></h4>
		            </td> 
		        </tr> 
		        <tr class="desc"> 
		        	<th class="fengexian">合买宣言：</th> 
		            <td colspan="3" style="padding-left:16px;">${community.description}</td>
		        </tr> 
		        <tr class="degree"> 
		        	<th class="fengexian">方案进度：</th> 
		        	<td style="padding-left:16px;"> <fmt:formatNumber type="percent" value="${community.okPart/community.totalPart}"  minFractionDigits="2"/>(认购)</td>
		        </tr>
			    <tr class="state"> 
		            <th class="fengexian">方案状态：</th> 
		            <td style="padding-left:16px;"></td> 
			    </tr> 
		        <tr class="prize-state"> 
		            <th class="fengexian">中奖情况：</th> 
		            <td style="padding-left:16px;"><strong class="red">${community.order.orderResult }</strong></td>
		       </tr>
			</table>
        	
        	        	
			<h3 class="block-tit">方案详情<span class="corner"></span></h3>
			<table class="block-detail" border="0" cellpadding="0" cellspacing="0">
				<tr class="creator">
					<th class="fengexian">发起人：</th>
	             	<td colspan="5" style="padding-left:16px;">
	                          	<strong>${community.customer.nickName }　</strong>
	                            <!--<img src="../images/user/level_1.png" />
	                            <img src="../images/user/level_2.png" />
	                            <img src="../images/user/level_3.png" />
	                            <img src="../images/user/level_4.png" />-->
	                </td>
				</tr>
	            <tr class="number">
	            	<th class="fengexian">方案编号：</th>
	            	<td style="padding-left:16px;">${community.plan.numberNo } <s:if test="community.plan.isZh=='追加'">[追加]</s:if></td>
	            </tr>
	            <tr class="date">
	                <th class="fengexian">发起时间：</th>
	                <td style="padding-left:16px;"><s:date name="community.createTimer" format="yyyy-MM-dd HH:mm:ss"/> </td>
	            </tr>
	            <tr class="amount">
	            	<th class="fengexian">方案金额：</th>
	            	<td style="padding-left:16px;"><strong class="red">${community.sumMoney }</strong>元 </td>
	            </tr>
	            <tr class="amount">
	            	<th class="fengexian">方案内容：</th>
	            	<s:if test="tagIndex==1">
	            	<td style="padding-left:16px;"><a href="javascript:show_plan_detail()" /><span id="show_detail">点击这里显示内容详情</span></td>
	           		</s:if>
	           		<s:elseif test="tagIndex==0">
	            	<td style="padding-left:16px;"> ${message}</td>
	           		</s:elseif>
	            </tr>
			</table>
			
			<s:if test="tagIndex==1">
			<div class="faxq_tab" id = "show_plan_detail_dev">
	        	<table class= "xh_detail_tab" cellspacing=0 cellpadding=0 width="100%">
		            <tr class = tit>
		            	<td>序号</td>
					    <td>类型</td>
					    <td>内容</td>
					    <td>注数</td>
					    <td>金额</td>
		            </tr>
		            
	                <s:iterator id="rs" value="community.plan.items" status="st">
	                	<tr>
	                		<td><s:property value="#st.index+1"/></td>
		           			<td>${rs.playType.nick_ne }</td>
		           			<td>${rs.content }</td>		           			
		           			<td>${rs.betCount}</td>
		           			<td>￥ ${rs.betCount*rs.oneMoney}</td>
		           		</tr>
	                </s:iterator>
	            	
	        	</table>
			</div>
			</s:if>
			<div class="clear"></div>
			
			<h3 class="block-tit">合买详情<span class="corner"></span></h3>
			<div class=clear></div>
			<table class= "plan_detail_tab" cellSpacing=0 cellPadding=0 width="100%">
				<tbody>
					<tr class=tit>
					    <td>方案总额</td>
					    <td>倍数</td>
					    <td>总份数</td>
					    <td>每份售价</td>
					    <td>进度+保底</td>
					    <td>发起人自购</td>
					    <td>发起人提成 
					    	<span class="what_is_rebate" href="javascript://"></span>
						    <!--  span style="DISPLAY: none" id=rebate_tooltip>为了奖励发起人发起合买方案，发起人可以设置0%-10%的比例作为奖金提成。提成金额直接派发到用户的“现金账户”
							    <br>提成条件：合买方案中奖且盈利
							    <br>提成计算：(税后中奖金额-合买方案总金额)×提成比例
							    <br>示例：合买方案总金额1000，税后中奖金额2000，发起人设置提成比例10%，则发起人提成=(2000-1000)×10%=100元
						    </span -->
					    </td>
					    <td class=titbg>奖金总额</td>
					    <td class=titbg>每份奖金</td>
					</tr>
					<tr>
						<td><strong class="red">${community.sumMoney }</strong>元 </td>
						<td>${community.multiple }</td>
						<td>${community.totalPart}份</td>
						<td class=red>￥${community.perMoney}元</td>
						<td class= process>
							<span style="DISPLAY: inline; FLOAT: left" class="re_num">
								<fmt:formatNumber type="percent" value="${community.okPart/community.totalPart}" minFractionDigits="2"/>
							   +<fmt:formatNumber type="percent" value="${community.insurePart/community.totalPart}" minFractionDigits="2"/>
							</span>
							
							
							<span style="DISPLAY: inline; FLOAT: left; COLOR: red; MARGIN-LEFT: 2px" class="re_word">(<span class="reserved">保</span>)</span>
							<br/><span class="tag"><i style="WIDTH: 16px"></i></span>
						</td>
						<td>${community.buyPart}</td>
						<td>${community.commision}%</td>
						<td class=jjbg>￥0.00</td>
						<td class=jjbg>￥0.00</td>
					</tr>
				</tbody>
			</table>
			
			
			<table border="0" align="left" cellpadding="0" cellspacing="0" class="block-detail">
				<tbody>
					<tr>
	                	<th width="81" class="fengexian">认购情况：</th>
	                	<td colspan="3" style="line-height:1.5; padding-left:16px;">  ${fn:length(list)}人次参与，已认购${community.okPart}份，共${community.okBuyMoney}元，<strong>还剩余<s:property value="community.totalPart-community.okPart"/>份，共${community.sumMoney-community.okBuyMoney}元</strong></td>
	              	</tr>
	               	<tr>
	                	<th height="33" class="fengexian">我要认购：</th>
	                	<td colspan="3">
		                    <input type="text" id="numpart" class="TZfenshu" style="margin-left:16px;" onkeyup="value=value.replace(/[^\d]/g,'');commun(${community.perMoney});"/> 份，
		                      总金额：<strong id="count">0</strong>元
	                    </td>
	              	</tr>
	                <tr>
	                	  <th>&nbsp;</th>
	                	  <td colspan="3" class="tab_p">
	                	  <input type="hidden" id="lotteryType1" value="${community.term.type.name_EN}"/>
	                	  <input type="hidden" id="termNo" value="${community.term.termNo}"/>
	                	  <input type="hidden" id="communityId" value="${community.id}"/>
	                	  <a type="button" onclick="toBuy('${community.term.type.name_EN}','${community.term.termNo}',$('#numpart'), ${community.id})">
	                      <img src="../images/ok.jpg" class="hand" width="120" height="34" style=" margin-left:16px; margin-top:19px;" /></a> <br />
                          &nbsp; &nbsp; &nbsp;点击"确认投注"则视为您已经阅读并同意《<a href="">用户合买代购协议</a>》                     	
                          </td>
	              	 </tr>
	             </tbody>
	         </table>
	           <div class="clear"></div>                             
	         <!-- JiaThis Button BEGIN -->
                <div id="ckepop">
                    <span class="jiathis_txt">分享到：</span>
                    <a class="jiathis_button_qzone">QQ空间</a>
                    <a class="jiathis_button_tsina">新浪微博</a>
                    <a class="jiathis_button_tqq">腾讯微博</a>
                    <a class="jiathis_button_renren">人人网</a>
                    <a class="jiathis_button_kaixin001">开心网</a>
                    <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">查看更多</a>
                 </div>
          <script type="text/javascript" >
                var jiathis_config={
                    siteNum:15,
                    sm:"qzone,tsina,tqq,renren,kaixin001,tsohu,taobao,tieba,t163,douban,baidu,xiaoyou,msn,hi,qq",
                    summary:"",
                    hideMore:false
                }
                </script>
          <script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
                <!-- JiaThis Button END -->
            <div class="clear"></div>                    
	        <div class="buy_list_info">

		    <h3 class="block-tit">合买记录<span class="corner"></span></h3>
	            <ul class="buy_con_title">
	            	<li>用户名</li>
	                <li>认购份数</li>
	                <li>认购金额 </li>
	                <li>认购时间</li>
	                <li>中奖金额</li>
	            </ul>
	            <div class="buy_con">
	            	<s:iterator id="rs" value="list" status="st">
	                	<ul>
	                        <li>${rs.customer.nickName }</li>
	                        <li>${rs.partNum }</li>
	                        <li>${rs.money }元</li>
	                        <li><s:date name="#rs.joinTime" format="yyyy-MM-dd HH:mm:ss"/></li>
	                        <li>${rs.winTaxMoney }</li>
	                	</ul>
	            	</s:iterator>
	            </div>
     		</div>

        </div>
    </div>
</div>
<div class="ft"></div>
<%@include  file="/foot.jsp"%>
</body>
</html>
