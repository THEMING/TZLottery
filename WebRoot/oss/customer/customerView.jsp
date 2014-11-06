<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>用户详情</title>
		<meta name="heading" content="用户详情" />
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		    body{ text-align:center;}
		</style>
		<script language="JavaScript">
	
		</script>
	</head>
	<body>
	  <div style="width:500px; margin:0 auto;text-align:left;">
		<label>用户详情</label>
		<br />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<h1><a href="manageEditCustomer.aspx?top=password&id=${customer.id }">密码修改</a></h1>
		<br />
		<h1>邮箱：${customer.email }</h1>
		<br />
		<h1>真实姓名：${customer.realName }　　　<a href="manageEditCustomer.aspx?top=rname&id=${customer.id }" >修改</a></h1>
		<br />
		<h1>证件类弄：${customer.credentType }</h1>
		<br />
		<h1>证件号：${customer.credentNo }　　　<a href="manageEditCustomer.aspx?top=credentNo&id=${customer.id }" >修改</a></h1>
		<br />
		<h1>邮箱：${customer.email }　　　　<a href="manageEditCustomer.aspx?top=email&id=${customer.id }" >修改</a></h1>
		<br />
		<h1>手机：${customer.mobileNo }</h1>
		<br />
		<h1>省份：${customer.province }</h1>
		<br />
		<h1>城市：${customer.city }</h1>
		<br />
		<h1>发卡银行：${customer.bank }</h1>
		<br />
		<h1>银行支行：${customer.subbranch }</h1>
		<br />
		<h1>银行卡号：${customer.bankNumber }　　　<a href="manageEditCustomer.aspx?top=bank&id=${customer.id }" >修改</a></h1>
		<br />
		<h1>注册时间：<s:date name="customer.registerTime" format="yyyy-MM-dd HH:mm"/></h1>
		<br />
		<h1>最后登陆时间：<s:date name="customer.lastLoginTime" format="yyyy-MM-dd HH:mm"/></h1>
		<br />
		<h1>最后登陆IP：${customer.customerIp }</h1>
		<br />
		<h1>登陆次数：${customer.loginNum }</h1>
		<br />
		<h1>最后购彩时间：<s:date name="gcTime" format="yyyy-MM-dd HH:mm"/></h1>
		<br />
		<h1>活动获得金额：${customer.ployAccur }</h1>
		<br />
		<h1>账户余额：${customer.wallet.balance }　　　<a href="manageFinanialQuery.aspx?customerId=${customer.id }">账务明细</a></h1>
		<br />
        <h1>冻结金额：${customer.wallet.freezeMoney }</h1>
        <br />
		<h1>充值总额：${paymentMoeny }</h1>
		<br />
		<h1>购彩总额：${outMoney }</h1>
		<br />
		<h1>累计中奖金额：${customer.allWinMoney }</h1>
		<br />
		<h1>提款总额：${backMoeny }</h1>
		<br />
		<h1>提款手续费：${feeMoney }</h1>
		<br />
		<h1>推荐人ID：${customer.superior.id}</h1>
		<br />
		<h1>推荐人用户名：${customer.superior.nickName}</h1>
		<br />
		<br />
		<h1><input type="button" value="返回" onclick="history.back()"/></h1>
		<br />
		</div>
	</body>
</html>