<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>亿美短信通道管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.4.2.min.js" type=text/javascript></script>
<script src="../skin/01/js/widgets.js" type="text/javascript"></script>
<script type="text/javascript">
var postPath = "<%=request.getContextPath()%>";
function smsRegistEx(){
	if(window.confirm('需要激活序列号吗？只需激活一次')){
		caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsRegist',null,function(data){
			if(data.result==true){alert('激活成功');
			}else{alert('激活失败');}
		});
	}
}

function smsRegistDetailInfo(){
	if(window.confirm('需要注册公司吗？只需注册一次')){
		caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsRegistCompanyInfo',null,function(data){
			if(data.result==true){alert('注册成功');
			}else{alert('注册失败');}
		});
	}
}

function smsLogout(){
	if(window.confirm('需要注销短信组件吗？注销后需要再次激活才能使用')){
		caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsLogout',null,function(data){
			if(data.result==true){alert('注销成功');
			}else{alert('注销失败');}
		});
	}
}
function smspwdupdate(){
	if(window.confirm('需要修改短信组件密码吗？')){
		caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsPwdUpdate',null,function(data){
			if(data.result==true){alert('修改成功');
			}else{alert('修改失败');}
		});
	}
}

function smsBalance(){
	caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsBalance',null,function(data){
		var moneyBalance=data.result;
		$("#money").html(moneyBalance);
	});
}

function smsTextSend(){
	var test_mobile = document.getElementById("test_mobile").value;
	caiso.fun.send(postPath+'/oss/customer/emaySms.htm?action=smsTextSend&test_mobile=' + test_mobile,null,function(data){
		if(data.result==0){alert('发送成功');
		}else{alert('发送失败');}
	});
}

</script>
</head>
<body>
 <div class="tab">
<table width="99%" align="center" border="1" cellpadding="0"
	cellspacing="0" class="data_table">
	<tr>
		<td class="data_tab_th" colspan="8">短信组件信息</td>
	</tr>
	<tr>
		<td class="data_tab_tdr">激活序列号:</td>
		<td class="data_tab_td" style="text-align: left;"><input type="button" value="·激活·" onclick="javascript:smsRegistEx();" /></td>
		<td class="data_tab_tdr">企业信息注册:</td>
		<td class="data_tab_td" style="text-align: left;"><input type="button" value="·注册·" onclick="javascript:smsRegistDetailInfo();" /></td>
		<td class="data_tab_tdr">软件注销:</td>
		<td class="data_tab_td" style="text-align: left;"><input type="button" value="·注销·" onclick="javascript:smsLogout();" /></td>
		<td class="data_tab_tdr">修改密码:</td>
		<td class="data_tab_td" style="text-align: left;"><input type="button" value="·修改·" onclick="javascript:smspwdupdate();" /></td>
	</tr>
	<tr>
		<td class="data_tab_tdr">查询余额:</td>
		<td class="data_tab_td" style="text-align: left;"><input type="button" value="·查询·" onclick="javascript:smsBalance();" /></td>
		<td class="data_tab_td" colspan="2" style="text-align: left;"><span id="money"></span>元</td>
		<td class="data_tab_tdr">测试手机号:</td>
		<td class="data_tab_td" style="text-align: left;">
			<input type="text" id="test_mobile" />
			<input type="button" value="·测试连接·" onclick="javascript:smsTextSend();" />
		</td>
		<td class="data_tab_tdr"></td>
		<td class="data_tab_td"></td>
	</tr>
</table>
<br />
</div>
</body>
