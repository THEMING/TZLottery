<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>一彩票票网</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <link rel="stylesheet" href="../css/common.css" type="text/css" />
    <link rel="stylesheet" href="../css/groupbuy.css" type="text/css" />
    
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
    	var group_table_ltype;
		var group_table_lterm;
		var group_table_lbuypart;
		var group_table_lid;
		var group_table_submiting = false;
		function toBuy(type, term, buypart,id)
		{
			if("${customer.nickName}" == "") {
				group_table_ltype=type;
				group_table_lterm=term;
				group_table_lbuypart=buypart;
				group_table_lid=id;
				buy2();
				return;
			}	else{ 
				group_table_ltype=type;
				group_table_lterm=term;
				group_table_lbuypart=buypart;
				group_table_lid=id;}
				group_table_buy();
		}
	
		function group_table_buy(){
		    if($(group_table_lbuypart).prev().val() == ""){
			   $(group_table_lbuypart).prev().val("0");
			}
		    
		    $.communityJoin.callback = function(json){
		    	ajaxResponse_join(json);
		    };
		    
		    if(group_table_lterm == "------")
		    {
		    	if(group_table_ltype == "竞彩足球")
		    	{
		    		group_table_lterm = "999999";
		    	}
		    	else
		    	{
		    		group_table_lterm = "888888";
		    	}
		    }
		    
		    if(confirm("您确定要参与该合买？")) {
				$.communityJoin._request({lotteryType:group_table_ltype, term: group_table_lterm, buyPart:$(group_table_lbuypart).prev().val(),communityId:group_table_lid});
			}
		}
		
		function ajaxResponse_join(json)
		{
			submiting = false;
	     	if(json.status == "_00000") {
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
				alert("购买成功，祝您好运!");
				break;
			}
			if(needreload == 2)
			{
				location.reload();
			}		    
		}
		
		$(document).ready(function()
			{
				indexlogin();
			}
		);
		
		function buy2(){
	 		loginType = 3;
	 		$( "#dialog-form" ).dialog( "open" );
	 		refreshCaptcha2();
		}
	</script>
</head>

<body>
<div id="table_box">
	<table cellspacing="0" cellpadding="0" class="groupbuy_table" style="width:100%;">
		<tbody>
			<tr class="title">
               	<td>序</td>
               	<td>彩种</td>
               	<td>期数</td>
               	<td>发起人</td>
				<td>方案</td>
				<td>总金额</td>
               	<td>剩余份数</td>
               	<td>进度+保底</td>
               	<td>购买</td>
			</tr>
			
			<s:iterator value="page.result" status="st">
				<tr>
                    <td><s:property value="#st.index+1"/></td>
                    <td><s:property value="term.type"/></td>
  					<c:choose>
						<c:when test="${term.termNo == '999999'}">
							<td >------</td>
						</c:when>
						<c:when test="${term.termNo == '888888'}">
							<td >------</td>
						</c:when>
						<c:otherwise>
							<td >${term.termNo}</td>
						</c:otherwise>
					</c:choose>		
                    <td><s:property value="customer.nickName"/></td>
                    <td><a target="blank" href="/custogetherSaleinfo.htm?id=${id}">查看</a></td>
                    <td>￥<s:property value="sumMoney"/></td>
                    <td>${totalPart-okPart }</td>
					<td>
						<span>
							<fmt:formatNumber type="percent" value="${okPart/totalPart}" minFractionDigits="2"/>
						   +<fmt:formatNumber type="percent" value="${insurePart/totalPart}" minFractionDigits="2"/>
						</span>
						<span class="red">(保)</span>
					</td>
				  	<td>
				  		<s:if test="communityType.toString().equals('已满员')">已满员</s:if>
				  		<s:else>
				  		<input type="hidden" id="lotteryType0" value="${term.type.name_EN}"/>
						<input type="text" id="" class="text follow_parts" />	
				  		<input type="button" value="购买" onclick="toBuy('${term.type.name_EN}','${term.termNo}',this,${id})"/>
						</s:else>
				  	</td>              
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
</body>
</html>