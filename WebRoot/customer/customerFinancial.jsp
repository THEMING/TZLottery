<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户中心-一彩票</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/util/validate.js"></script>
<script src="../js/util/area.js"></script>
<script>
	var tab_num = 5;
	
	function allTogOff() {
		for(var i = 1; i <= tab_num; i++) {
			$("#li"+i).attr("class", "off");
			$("#main"+i).hide();
		}
	}
	
	function togOn(index) {
		allTogOff();
		$("#li"+index).attr("class", "on");
		$("#main"+index).show();
		iframeResize(index);
	}
	
	$(document).ready(function()
		{
			var index = "${tabIndex}";
			togOn(index);
		}
	);
	
	//调整父页面中IFrame的高度为此页面的高度
	function iframeResize(index) {
		var obj = parent.document.getElementById("cur_content_frame");
		obj.height = $("#material_panel").height() + 20;
	}
</script>


<script type="text/javascript">
	function submit() {
		if(!$("#money").val() || $("#money").val()<2){
			alert("充值金额不能小于2元");
			return false;
		}
		$("#deposit_money").val($("#money").val());
		$("#bank").val($("input:checked").attr("param"));
		$("#czform").attr("action",$("input:checked").val());
		$("#czform").submit();
	}
	var msg="${actionmsg}";
	if(msg) {
		alert(msg);
	}
</script>

<script>
	function vlidateBank() 
	{
		//if(${customer.bankNumber == null}) {
			if($("#bank_tx").val()==0){
				alert("提款银行不能为空");
				$("#bank_tx").focus();
				return false;
			}
			if(!$("#realName").val()) {
				alert("开户名不能为空");
				$("#realName").focus();
				return false;
			}
			if(!$("#subbranch").val()) {
				alert("开户支行不能为空");
				$("#subbranch").focus();
				return false;
			}
			if(!$("#bankNumber").val() || !isCard($.trim($("#bankNumber").val()))) {
				alert("银行账号不能为空或格式错误");
				$("#bankNumber").focus();
				return false;
			}	
		//}
		return true;
	}
	
	function vlidateMoney() 
	{	
		if(${customer.bankNumber == null}) {
			alert("请先绑定银行卡");
			return false;
		}
		
		var isNum = /^[1-9]+.?[0-9]*$/;
		if(!isNum.test($("#inTotleMoney").val())) {
			alert("提现金额输入不合法");
			$("#inTotleMoney").val("");
			$("#inTotleMoney").focus();
			return false;
		}

		if(!($("#inTotleMoney").val() && $("#inTotleMoney").val()>0) || 
			$("#inTotleMoney").val()>Number('${customer.wallet.balance}')) {
			alert("提现金额必须小于可用余额");
			$("#inTotleMoney").val("");
			$("#inTotleMoney").focus();
			return false;
		}
		return true;	
	}
	
	var show = 0;
	function showHistory()
	{
		if(show == 0) {
			$("#historyInfo").show();
			show = 1;
			$("#showHis").text("隐藏提现历史");
		}
		else {
			$("#historyInfo").hide();
			show = 0;
			$("#showHis").text("显示提现历史");
		}
		
		iframeResize();
	}
	
	jQuery(document).ready(function() 
	{
		if(${customer.bankNumber != null}) {
			$("#realName").val("${customer.realName}");
			$("#bank_tx").val("${customer.bank}");
			$("#province").val("${customer.province}");
			$("#city").val("${customer.city}");
			$("#subbranch").val("${customer.subbranch}");
			$("#bankNumber").val("${customer.bankNumber}");
		}
	});
	
	function saveBindBank() {
		togOn(5);
	}
	
	function cancelBind() {
		togOn(4);
	}
	
	function yuerenyhm() {
		var input1 = $("#tcp_input1").val();
		var input2 = $("#tcp_input2").val();
		var input3 = $("#tcp_input3").val();
		$("#tcp_input3_ms").css("color","green");
		if(input1 == null || input1 == "")
		{
			$("#tcp_input1_ms").html("你没有输入正确的用户名！");
			return false;
		}
		$("#tcp_input1_ms").css("color","green");
		if($("#tcp_input1").val() == $("#tcp_input2").val())
		{
			alert("456");
			return confirm("确定给用户名为：" + $("#tcp_input1").val() +  "\n充值：" + $("#tcp_input3").val() + "元");
		}
		$("#tcp_input2_ms").html("你输入的用户名不一致，请重新输入！");
		return false;
	}
</script>

</head>
<body>
<div>
	<div id="material_panel" class="tab_box">
		<div class="tab_top">
			<ul id="material_panel_ul">
				<li id="li1" onclick="togOn(1)">账户总览</li>
				<li id="li2" onclick="togOn(2)">资金明细</li>
				<li id="li3" onclick="togOn(3)">账户充值</li>
				<li id="li4" onclick="togOn(4)">账户提现</li>
				<li id="li5" onclick="togOn(5)">绑定银行卡</li>
			</ul>
		</div>
        <div class="clear"></div>
        
        <!-- 账户总览 -->
        <div id="main1" class="main">
			<table cellspacing="0" cellpadding="0" class="customer_table">
				<!-- <tr class="title">
					<td colspan="4"><h1><strong>账户总览</strong></h1></td>
				</tr> -->
				<tr class="title">
					<td colspan="2">
						<h3><strong>账户信息</strong></h3>
					</td>
					<!--  
					<td colspan="2">
						<h3><strong>历史消费</strong></h3>
					</td>-->
				</tr>
				
				<tr>
					<td class="right" width="80px" bgcolor="#cccccc">用户ID：</td>
					<td width="120px" class="left"><strong class="red">${customer.nickName}</strong></td>
					<!--  <td class="right" width="100px" bgcolor="#FFCCFF">累计投注：</td>
					<td width="120px" class="left">￥${outMoney}元</td>-->
				</tr>
				<tr>
					<td class="right" bgcolor="#cccccc">账户总额：</td>
					<td class="left">${customer.wallet.balance+customer.wallet.freezeMoney}元</td>
					
				</tr>
				<tr>
					<td class="right" bgcolor="#cccccc">累计中奖：</td>
					<td class="left">￥${customer.allWinMoney}元</td>
				
				</tr>
				<tr>
					<td class="right" bgcolor="#cccccc">可用余额：</td>
					<td class="left">${customer.wallet.balance}元</td>
					<!--  <td class="right" bgcolor="#FFCCFF">累计充值：</td>
					<td class="left">￥${paymentMoeny}元</td> -->
				</tr>
			</table>
        	<div class="clear"></div>
        </div>
        
        <!-- 资金明细 -->
        <div id="main2" class="main hidden">
	        <form id="zjmx_form" action="/customer/MyFinancialInfo.htm" method="post">
		        <input type="hidden" name="action" value="fundDetailed"></input>
                <div class="zjmx_title">
					<label>状态：</label>
	                <select name="stype" id="stype" >
	                	<s:if test="stype.length()>0">
	                  		<option value="${stype}" selected="selected">${stype}</option>
	                   	</s:if>
                    	<option value="" >全部..</option>
                    	<optgroup label="支出">
			            	<option value="代购费用" >代购费用</option>
			            	<option value="合买发起费用" >合买发起费用</option>
			            	<option value="合买参与费用" >合买参与费用</option>
			            	<option value="合买保底费用" >合买保底费用</option>
			            	<option value="合买保底冻结" >合买保底冻结</option>
			            	<option value="追号费用" >追号费用</option>
			            	<option value="追号冻结" >追号冻结</option>
			            	<option value="提款扣款" >提款扣款</option>
			            	<option value="提款冻结" >提款冻结</option>
			            	<option value="手续费冻结" >手续费冻结</option>
	                    </optgroup>
	                    <optgroup label="收入">	                        
	                        <option value="账户充值" >账户充值</option>
			            	<option value="账户返奖" >账户返奖</option>
			            	<option value="代购退款" >代购退款</option>
			            	<option value="代购部分退款" >代购部分退款</option>			            	
			            	<option value="合买提成" >合买提成</option>
			            	<option value="合买退款" >合买退款</option>
			            	<option value="合买部分退款" >合买部分退款</option>
			            	<option value="合买保底解冻" >合买保底解冻</option>
			            	<option value="合买保底部分解冻" >合买保底部分解冻</option>			            	
			            	<option value="追号退款" >追号退款</option>
			            	<option value="提款失败返款">提款失败返款</option>
			            	<option value="手续费扣款" >手续费扣款</option>
	                   	</optgroup>
	               	</select>
	　                <label>订单号：</label>
	                <input type="text" name="numberNo" id="numberNo" value="${numberNo }"/>
	                <label>日期：</label>
	                <input type="text" name="beginTime" id="beginTime" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/>" class="input_date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
					<input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" class="input_date" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
	         		<input type="submit" class="btn_ok" value="确 定" />
         		</div>
           	
	            <table class="customer_table">
					<tr>
						<td class="title" width="130px">交易时间</td>
						<td class="title" width="70px">收入/支出</td>
						<td class="title" width="70px">交易类型</td>
						<td class="title" width="70px">交易金额</td>
						<td class="title" width="80px">余额</td>
						<td class="title" width="75px">冻结余额</td>
						<td class="title" width="130px">方案号</td>
						<td class="title" width="80px">备注</td>
					</tr>
	                <s:iterator id="rs" value="page.result" >
	                	<tr>
							<td class="center"><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/></td>
	                        <td class="center">${businessType}</td>
	                        <td class="center">${type}</td>
	                        <td class="red center">
	                        	<s:if test="inMoney>0">
	                            	${inMoney}
	                            </s:if>
	                            <s:elseif test="outMoney>0">
	                                ${outMoney}
	                            </s:elseif>
	                           	<s:elseif test="inFreezeMoney>0">
	                           		${inFreezeMoney}
	                           	</s:elseif>
	                           	<s:elseif test="outFreezeMoney>0">
	                           		${outFreezeMoney}
	                           	</s:elseif>
	                        </td>
	                        <td class="red center">${currentMoney}</td>
	                        <td class="red center">${currentFreezeMoney}</td>
	                        <td class="center">
								<c:if test="${fn:indexOf(rs.type,'追号') >-1 or fn:indexOf(rs.description,'追号') >-1}"><a  target="_blank" href="MyChaseInfoinfo.htm?action=list&numberNo=${rs.serialNumber}"> ${serialNumber}</a></c:if>
	                            <c:if test="${fn:indexOf(rs.type,'合买') >-1 or fn:indexOf(rs.description,'合买') >-1}"><a target="_blank" href="MyGroupBuyInfo.htm?action=detail&numberNo=${rs.serialNumber}"> ${rs.serialNumber}</a></c:if>
	                            <c:if test="${fn:indexOf(rs.type,'购买') >-1 or fn:indexOf(rs.description,'代购') >-1 or fn:indexOf(rs.type,'代购') >-1}"><a target="_blank" href="MyBuyLotteryInfo.htm?action=detail&numberNo=${rs.serialNumber}">${serialNumber}</a></c:if>
	                            <c:if test="${fn:indexOf(rs.type,'套餐') >-1 or fn:indexOf(rs.description,'套餐') >-1}"><a target="_blank" href="customerMealChase.htm?action=detail&numberNo=${rs.serialNumber}">${serialNumber}</a></c:if>
	                        </td>
	                        <td class="center">${description}</td>
	                    </tr>
	                </s:iterator>
	            </table>
	            <div class="page">
	            	<p><div><jsp:include page="../util/page1.jsp"></jsp:include></div></p>
	                <p>支出交易笔数：${outSum }  收入交易笔数：${inSum }   支出金额合计：￥${outTotleMoney }元   收入金额合计：￥${inTotleMoney }元   冻结：￥${freezeMoney }元</p>
	            </div>
            </form>
        	<div class="clear"></div>
        </div>
        
        <!-- 充值 -->
        <div id="main3" class="main hidden">
        	<p>
        		当前账户可用余额为：<strong class="red"><fmt:formatNumber value="${customer.wallet.balance}" type="currency"/>元</strong>
       		</p>
       		
       		
        	
       		<p>
       			<h1>1.请输入充值金额</h1>
       			<input type="text" id="money"  class="text" size="12" />  <!-- onkeyup="value=value.replace(/[^\d]/g,'');" -->
                <span>元</span><br />
                <span class="red" style="line-height:2;">1.（充值金额至少2元,免手续费） </span> <br />
        	    <span class="red">2.充值超过50元，即可获得软件注册码，进入用户中心->软件注册界面可领取</span>
        	<div class="clear"></div>
   			</p>
   			
   			<p>
   				<h1>2.请选择充值方式</h1>
   			</p>
   			
            <div class="pay_box">
            	<input name="bankId" type="radio" id="bankId" value="/customer/alipayrequest.htm" checked/> <img src="../images/banks/pay_a.jpg" alt="支付宝支付" width="100" height="30" style="margin-left:15px;"/> 
				<span style="margin-left:15px" class="red">(没有支付宝账号将为您自动创建账号)</span>        	
        	</div>
        	<div class="clear"></div>
        	<br/>
        	<div class="pay_box">
            	<input name="bankId" type="radio" id="bankId" value="/customer/kuaiqianrequest.htm"/> <img src="../images/banks/kuaiqian.jpg" alt="快钱支付" width="100" height="30"/> 
				
        	</div>
        	
        	
        	<div class="clear"></div>
        	<p style="margin-top:30px">
        	
        		<input type="button" value="下一步&gt;" onclick="submit()"/>
        	</p>
        	
        	<div class="clear"></div>
        	<br/>
        	<p>
   				<h1>3.银行汇款</h1>
   			</p>
        	<div class="pay_box">
            	户名：北京比特太奇科技有限公司<br/> 账号：11001018400059261241 <br/>开户行：中国建行北京市西三旗支行 <br/>备注：1.无网上支付方式的用户也可通过银行汇款<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.请填写网站用户名，汇款后请尽快联系客服查验入账 010-56203469, QQ2432882152
				
        	</div>
        	
        	<div class="clear"></div>
        	<br/>
        	<p>
        		<h1>4.优惠劵兑换</h1>
        	</p>
        	<div class="pay_box">
        		<form action="/customer/youhuimaDH.htm" method="post">
        			请输入优惠码：<input type="text" name="duihuanma" style="width: 150px" />
        			<input type="submit" value="兑换" />
        		</form>	
				<div>
					<c:if test="${zhcz eq '兑换成功' }">
						<div style="font-size: 16px;color: green;width: 300px;text-align: center;margin-top: 20px;">
							<strong>恭喜你兑换成功！你钱包剩余${customer.wallet.balance+customer.wallet.freezeMoney}元!</strong>
							<p><a href="/lotteryHall/" style="color: red;" >继续进行购彩</a></p>
						</div>
					</c:if>
					<c:if test="${zhcz eq '兑换失败' }">
						<div style="font-size: 16px;color: red;width: 300px;text-align: center;margin-top: 20px;">
							<strong>兑换失败</strong>
							<p>${yuanying }</p>
						</div>
					</c:if>
				</div>	
     
        	</div>
        	
        	<div class="clear"></div>
        	<br/>
        	<p>
        		<h1>5.给朋友充值</h1>
        	</p>
        	<div class="pay_box">
        		<form action="/customer/gljchongzhi.htm" method="post">
        			<table>
        				<tr>
        					<td>请输入充值金额：</td>
        					<td><input id="tcp_input3" type="text" name="money" style="width: 100px; height: 18px;margin-bottom: 5px;" onkeyup="value=value.replace(/[^\d.]/g,'');"/></td>
        					<td><span id="tcp_input3_ms" style="color: red; margin-left: 5px;">*请输入您需要给其充值的金额</span></td>
        				</tr>
        				<tr>
        					<td>请输入朋友用户名：</td>
        					<td><input id="tcp_input1" type="text" name="username" style="width: 100px; height: 18px;margin-bottom: 5px;" /></td>
        					<td><span id="tcp_input1_ms" style="color: red; margin-left: 5px;">*输入对方的账号</span></td>
        				</tr>
        				<tr>
        					<td>请确认朋友用户名：</td>
        					<td><input id="tcp_input2" type="text" name="username1" style="width: 100px; height: 18px;margin-bottom: 5px;" /></td>
        					<td><span id="tcp_input2_ms" style="color: red; margin-left: 5px;">*确认对方的账号</span></td>
        				</tr>
        			</table>
        			
        			<input type="submit" value="确定" onclick="return yuerenyhm();" style="margin-left: 100px;"/>
        		</form>	
				<div>
					<c:if test="${tcp_issucc eq '成功' }">
						<div style="font-size: 16px;color: green;width: 300px;text-align: center;margin-top: 20px;">
							<strong>恭喜你为${username }充值成功！你钱包剩余${customer.wallet.balance+customer.wallet.freezeMoney}元!</strong>
							<p><a href="/lotteryHall/" style="color: red;" >继续进行购彩</a></p>
						</div>
					</c:if>
					<c:if test="${tcp_issucc eq '失败' }">
						<div style="font-size: 16px;color: red;width: 300px;text-align: center;margin-top: 20px;">
							<strong>充值失败</strong>
							<p>${tcp_succ }</p>
						</div>
					</c:if>
				</div>	
     
        	</div>
        	
      		<form id="czform" method="post" target="_blank">
                <input type="hidden" name="bank" id="bank"/>
                <input type="hidden" name="deposit_money" id="deposit_money"/>
            </form>
        	<div class="clear"></div>
        </div>
        
        <!--  提现 -->
        <div id="main4" class="main hidden">
        	<p>
        		当前账户可用余额为：<strong class="red"><fmt:formatNumber value="${customer.wallet.balance}" type="currency"/>元</strong>
       			可提现金额：<strong class="red">${customer.wallet.balance}元</strong>
       		</p>
       		<span class="red">${message}</span>
       		<s:if test="customer.realName != null">
	       		<form action="#" onsubmit="return vlidateMoney()" method="post">
	            	<input type="hidden" name="action" value="drawmoney"/>
	            	<h1>1.核对提款账号</h1>
	            	<s:if test="customer.bankNumber != null">
	            		<table cellspacing="0" cellpadding="0" class="customer_table">
			       			<tr>
				              	<td class="right">开户人姓名：</td>
				              	<td >${customer.bankName} </td>
			              	</tr>
			              	<tr>
				              	<td class="right">开户银行：</td>
				              	<td >${customer.bank}</td>
			              	</tr>
			              	<tr>
				              	<td class="right">开户地址：</td>
				              	<td >${customer.province}--${customer.city}--${customer.subbranch} </td>
			              	</tr>
			              	<tr>
				              	<td class="right">银行账号：</td>
				              	<td >${customer.bankNumber} </td>
			              	</tr>
			              	<tr>
			              		<td colspan="2"><input type="button" value="修改绑定银行账号" onclick="saveBindBank()"/></td>
			              	</tr>
		              	</table>
					</s:if>
					<s:else>
						您还没有绑定银行卡，<a href="javascript:saveBindBank()">点击这里绑定</a>
					</s:else> 
	
	   				<h1>2.输入提现金额</h1>
	   				<p>
						<label>提现金额：</label>
						<input type="text" class="text" name="inTotleMoney" id="inTotleMoney"/> 元
						<span style="margin-left:50px;"><input type="submit" value="确定提款>" /></span>
					</p>
	   			</form>
	   			
				<p style="margin-top:20px">
					<a href="javascript:showHistory()"><span id="showHis">显示提现历史</span></a>
					
					<table id="historyInfo" class="customer_table hidden">
						<tr>
							<td class="title" width="135">提现时间</td>
							<td class="title" width="100">提现金额</td>
							<td class="title" width="120">开户人</td>
							<td class="title" width="180">银行名称</td>
							<td class="title" width="135">银行账号</td>
						</tr>
						<s:iterator value="bmrList">
							<tr>
								<td class="center"><s:date name="applyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="center">${money}元</td>
								<td class="center">${customer.realName}</td>
								<td class="center">${customer.city}-${bank}-${openSpace}</td>
								<td class="center">${bankCard}</td>
							</tr>
		                </s:iterator>
					</table>
				</p>
			</s:if>
	   		<s:else>
	   			<div style="padding:30px">
	   			您还没有完善个人信息，请到账号信息处完善！
	   			</div>
	   		</s:else>
        	
        	<div class="clear"></div>
        </div>
        
         <!--  绑定银行卡 -->
        <div id="main5" class="main hidden">
        	<span class="red"><s:actionmessage/></span>
        	<form action="#" onsubmit="return vlidateBank()" method="post">
            	<input type="hidden" name="action" value="bindBank"/>
	        	<table cellspacing="0" cellpadding="0" class="customer_table">
	             	<tr>
	            		<td class="right">提款银行：</td>
	            		<td >
	            			<select name="bank" id="bank_tx">
								<option value="0">请选择银行...</option>
								<s:iterator value="banksList">
									<option><s:property/></option>
								</s:iterator>
							</select> 
	            		</td>
	           		</tr>
		                
	             	<tr>
	           			<td class="right">开户名：</td>
	           			<td >
	           				<input type="text" name="realName" id="realName"/>
	           				<span class="red">开户名与您个人资料的真实姓名必须一致</span>
	           			</td>
	          		</tr>
	                	
	               	<tr>
	              		<td class="right">开户支行：</td>
	              		<td >
	              			省：<select name="province" id="province">
	              			<option value="">请选择</option><option value="北京">北京</option><option value="上海">上海</option>
	              			<option value="福建">福建</option><option value="甘肃">甘肃</option><option value="广东">广东</option>
	              			<option value="广西">广西</option><option value="贵州">贵州</option><option value="海南">海南</option>
	              			<option value="河北">河北</option><option value="河南">河南</option><option value="黑龙江">黑龙江</option>
	              			<option value="湖北">湖北</option><option value="湖南">湖南</option><option value="吉林">吉林</option>
	              			<option value="江苏">江苏</option><option value="江西">江西</option><option value="辽宁">辽宁</option>
	              			<option value="内蒙古">内蒙古</option><option value="宁夏">宁夏</option><option value="青海">青海</option>
	              			<option value="山东">山东</option><option value="山西">山西</option><option value="陕西">陕西</option>
	                        <option value="安徽">安徽</option><option value="四川">四川</option><option value="天津">天津</option>
	              			<option value="西藏">西藏</option><option value="新疆">新疆</option><option value="云南">云南</option>
	              			<option value="浙江">浙江</option><option value="重庆">重庆</option>
	                       	</select>
	                       	 市：<input type="text" id="city" name="city"/>
	                       	开户支行：<input type="text" name="subbranch" id="subbranch"/>
	              		</td>
	           		</tr>
	                    
	                <tr>
	              		<td class="right">银行卡号：</td>
	              		<td >
	            			<input type="text" name="bankNumber" id="bankNumber"/>
	              		</td>
	           		</tr>
	           		
	           		<tr>
	              		<td colspan="2" class="center">
	              			<input type="submit" value="保存"/>
	              			<input type="button" value="取消" onclick="cancelBind()"/>
	              		</td>
	           		</tr>
	   			</table>
   			</form>
        </div>
    </div>
</div>  
</body>
</html>