<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>出票商充值管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script type="text/javascript">
function addForm(){
   //if(window.confirm("您确定要充值吗？")){
	   var money=$("#money").val();
	   var time=$("#time").val();
	   var payer=$("#payer").val();
	   var recorder=$("#recorder").val();
	   if(money==null || money==""){
	       alert("充值金额不能为空！");
	       return;
	   }
	   if(time==null || time==""){
	      alert("充值时间不能为空！");
	      return;
	   }
	   if(payer==null || payer==""){
	       alert("充值人姓名不能为空！");
	       return;
	   }
	   if(recorder==null || recorder==""){
	      alert("记录人姓名不能为空！");
	      return;
	   }                      
		var reg=/(^\d+$)|(^\d+.\d+$)/g;
	    if (!reg.test(money))
	    {
	    	alert("金额格式不正确");
	    	return;
	    }
	
	   
	   $("#myForm").submit();
   //}
}

function seachForm(){
     $("#myForm2").submit();
}
</script>
</head>
<body>
<div class="tab">
<s:form  id="myForm" action="supplierPayManager" method="post" namespace="/oss/term">
<s:hidden id="action" name="action" value="add" />
	<table>
		<tr>
			<td>出票商名称</td>
			<td>充值金额</td>
			<td>充值时间</td>
			<td>充值人姓名</td>
			<td>记录人姓名</td>
			<td>状态</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
		<tr>
			<td>
				<s:select name="name" list="names"></s:select>
			</td>
			<td><input type="text" id="money" name="money" value="${money}"></input></td>
			<td><input type="text" id="time" name="time" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});" value=""/></td>
			<td><input type="text" id="payer" name="payer" value="${payer}"></input></td>
			<td><input type="text" id="recorder" name="recorder" value="${recorder}"></input></td>
			<td>
				<select name="status" id="status">
				    <s:if test="#status==\"0\"">
				       <option value="申请" selected="selected">申请</option>
				    </s:if>
				    <s:elseif test="#status!=\"0\"">
				       <option value="申请">申请</option>
				    </s:elseif>
				    <s:if test="#status==\"1\"">
				       <option value="正在审批" selected="selected">正在审批</option>
				    </s:if>
				    <s:elseif test="#status!=\"1\"">
				       <option value="正在审批">正在审批</option>
				    </s:elseif>
					<s:if test="#status==\"2\"">
				       <option value="审批完成" selected="selected">审批完成</option>
				    </s:if>
				    <s:elseif test="#status!=\"2\"">
				       <option value="审批完成">审批完成</option>
				    </s:elseif>
					<s:if test="#status==\"3\"">
				       <option value="付款完成" selected="selected">付款完成</option>
				    </s:if>
				    <s:elseif test="status!=\"3\"">
				       <option value="付款完成">付款完成</option>
				    </s:elseif>
				</select>
			</td>
			<td><input type="text" id="memo" name="memo" value="${memo}"></input></td>
			<td>
			   <input type="button" value="充 值" onclick="addForm()"></input>&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</s:form>

<br>
<s:form  id="myForm2" action="supplierPayManager" method="post" namespace="/oss/term">
<table>
  <tr>
    <td>出票商名称：</td>
    <td>
      <s:select name="name" list="names" headerKey="" headerValue="全部"></s:select>
    </td>
    <td>充值人姓名：</td>
    <td><input type="text" id="payer2" name="payer" value="${payer}"></input></td>
    <td>充值时间：</td>
    <td>
      <input type="text" id="beginTime2" name="beginTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>-
      <input type="text" id="endTime2" name="endTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
    </td>
    <td>
      <input type="button" value="查 询" onclick="seachForm()">
    </td>
  </tr>
</table>
</s:form>

<br/>

<s:form id="tabForm" action="supplierPayManager" method="post" namespace="/oss/term">
    <s:hidden name="action" value="index"/>
	<s:hidden name="name" id="name3"/>
	<s:hidden name="payer" id="payer3"/>
	<s:hidden name="beginTime" id="beginTime3"/>
	<s:hidden name="endTime" id="endTime3"/>
	<h1 class="redbold"><font color="red">统计总充值：${sumMoney}</font></h1>
	<table >
	  <tr>
	    <td height="25"><div align="center">出票商名称</div></td>
	    <td height="25"><div align="center">充值金额</div></td>
	    <td height="25"><div align="center">充值时间</div></td>
	    <td height="25"><div align="center">充值人姓名</div></td>
	    <td height="25"><div align="center">记录人姓名</div></td>
	    <td height="25"><div align="center">状态</div></td>
	    <td height="25"><div align="center">备注</div></td>
	    <td height="25"><div align="center">操作</div></td>
	  </tr>
	  	   <s:iterator id="rs" value="page.result">
		   <tr>
	      <td height="25" >${rs.supplierName}</td>
	      <td height="25" >${rs.money}</td>
	      <td height="25" ><s:date name="#rs.time" format="yyyy-MM-dd HH:mm:ss"/> </td>
	      <td height="25" >${rs.payer}</td>
	      <td height="25" >${rs.recorder}</td>
	      <td height="25" >
	      <s:if test="#rs.status==\"0\"">
	                         申请
	      </s:if>
	      <s:elseif test="#rs.status==\"1\"">
	                        正在审批
	      </s:elseif>
	       <s:elseif test="#rs.status==\"2\"">
	                        审批完成
	      </s:elseif>
	       <s:elseif test="#rs.status==\"3\"">
	                        付款完成
	      </s:elseif>
	      </td>
	      <td height="25" >${rs.memo}</td>
	      <td height="25"><a href="#" onclick='if(window.confirm("你确定要删除吗？")){window.location.href="supplierPayManager.aspx?action=deleteSupplierPay&id=${rs.id}"}'> 删除</a></td>
	      </tr>
	      </s:iterator>
	</table>
<script>
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#tabForm").submit();
		
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("#tabForm").submit();
	}
</script>
	 <table width="90%" border="0" align="center">
	    <tr>
	    <td>
	      <div>
				<input type="hidden" name="pageNo" id="pageNo" value="1" />
				每页${page.pageSize}条 共${page.totalCount}条记录 第${page.pageNo}/${page.totalPages}页   
				<s:if test="page.pageNo==1">
					<span class="disabled">首页</span> 
					<span class="disabled">前一页</span>
				</s:if>
				<s:else>
					<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
					<a href="javascript:jumpPage(${page.pageNo-1})" class="cr_link">前一页</a>
				</s:else>
				
				<s:if test="page.pageNo>=page.totalPages">
					<span class="disabled">后一页</span> 
					<span class="disabled">末页</span>
				</s:if>
				<s:else>
					<a href="javascript:jumpPage(${page.pageNo+1})" class="cr_link">后一页</a>
					<a href="javascript:jumpPage(${page.totalPages})" class="cr_link">末页</a>
				</s:else>
				转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${page.pageNo}" />页  
				<input type="button" value="跳转" onclick="jumpPage1();"/>
			</div>
	    </td>
	    </tr>
	  </table>
</s:form>
</div>
</body>

<script type="text/javascript">
    var msg="${msg}";
    if(msg!=null && msg!=""){
        alert(msg);
    }
</script>
