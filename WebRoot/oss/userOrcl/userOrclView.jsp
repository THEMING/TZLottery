<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>三版老用户详情</title>
		<meta name="heading" content="三版老用户详情" />
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
		<script language="JavaScript">
	
		</script>
	</head>
	<body>
		<label>三版用户详情</label>
		<br />
		<br />
		<h1>用户名：${user.nameNick }</h1>
		<br />
		<h1>邮箱：${user.emil }</h1>
		<br />
		<h1>真实姓名：${user.nameReal }　　　</h1>
		<br />
		<h1>状态：<s:if test="user.statu==1">正常</s:if>
                   <s:else>冻结</s:else></h1>
		<br />
		<h1>来源：<s:if test="user.usr_type==0">本地注册</s:if>
		          <s:elseif test="user.usr_type==1">支付宝</s:elseif>
                   <s:else>QQ财付通</s:else></h1>
		<br />
		<h1>证件类弄：身份证</h1>
		<br />
		<h1>证件号：${user.card }　</h1>
		<br />
		<h1>手机：${user.phone }</h1>
		<br />
		<h1>省份：${user.address }</h1>
		<br />
		<h1>城市：${user.city }</h1>
		<br />
		<h1>发卡银行：${user.nameBank }</h1>
		<br />
		<h1>银行支行：${user.bankName }</h1>
		<br />
		<h1>银行卡号：${user.bankNumber }　</h1>
		<br />
		<h1>注册时间：${user.regDate }</h1>
		<br />
		<h1>最后登陆时间：${user.lastDate }</h1>
		<br />
		<h1>登陆次数：${user.login_times }</h1>
		<br />
		<h1>账户余额：￥<fmt:formatNumber value="${user.blacne/100}"  minFractionDigits="2"/></h1>
		<br />
		<h1>优惠金额：￥<fmt:formatNumber value="${user.coupon/100}"  minFractionDigits="2"/></h1>
		<br />
		<h1>代金卷：￥<fmt:formatNumber value="${user.cash_coupon/100}"  minFractionDigits="2"/></h1>
		<br />
		<h1>累计中奖金额：￥${user.zj }</h1>
		<br />
		<br />
		<h1><input type="button" value="返回" onclick="history.back()"/></h1>
		<br />
	</body>
</html>