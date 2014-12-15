<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>提款二级审核管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(cmd,strId) {
    $("#bmid").val(strId);
    $("#action").val(cmd);
    $("#memo").val($("#message_"+strId).val());
	$("form").submit();
}

function doSelectAll()
{
	str = $("#selectall").val();
	if(str == '全选')
	{
		$("#selectall").val("取消");
		$("[name=mycheckbox]:checkbox").attr("checked",true);
	}
	if(str == '取消')
	{
		$("#selectall").val("全选");
		$("[name=mycheckbox]:checkbox").attr("checked",false);
	}

}

function dosub()
{
	ids = "";
	str = $("#selectall").val();
	for(i=0; i<200; i++)
	{
		ifcheck = $("#mycheckbox_"+i).attr("checked");
		ifstatus = $("#stat_"+i).text();
		if(ifcheck)
		{
			if(ifstatus == '一级审核')
			{
				if(i == 199)
				{
					ids += $("#myhidden_"+i).val();
				}
				else
				{
					ids += $("#myhidden_"+i).val() + ",";	
				}
			}
		}
	}
	if(ids == '')
	{
		if(str == '全选')
		{
			alert("请选择全选之后再点击");
		}
		else
		{
			alert("都已审核通过，请勿重复修改");
		}
	}
	else
	{
		$("#betchId").val(ids);
    	$("#action").val('bitchOption');
		$("form").submit();
	}
}
</script>
</head>
<body>
 <div class="tab">
 <span style="color:red;font-size:18px">${message}</span>
<s:form  action="manageWithdrawMonney2" method="post" >
<s:hidden name="action" id="action" value="index"></s:hidden>
<s:hidden name="bmid" id="bmid"/>
<s:hidden name="betchId" id="betchId"/>
<s:hidden name="memo" id="memo"></s:hidden>
<div align="left">
	  <input type="button" value="提交" onclick="dosub();" />
</div>
<table>
	<tr>
		<td>申请记录总数:${total }</td>
		<td>总金额:${totalMoney }</td>
		<td>单笔最高:${maxMoney }</td>
		<td>单笔最低:${minMoney }</td>
	</tr>
</table>
<br />
<table >
  <tr><!--（成功，失败）-->
  	<td height="25"><div align="center"><input type="button" value="全选" onclick="doSelectAll();" id="selectall"></input></div></td>
    <td height="25"><div align="center">编号</div></td>
    <td height="25"><div align="center">提款时间</div></td>
    <td height="25"><div align="center">用户名</div></td>
    <td height="25"><div align="center">姓名</div></td>
    <td height="25"><div align="center">账号</div></td>
    <td height="25"><div align="center">所属银行</div></td>
    <td height="25"><div align="center">开户行</div></td>
    <td height="25"><div align="center">提款金额</div></td>
    <td height="25"><div align="center">状态</div></td>
    <td height="25"><div align="center">操作人</div></td>
    <td height="25"><div align="center">操作时间</div></td>
    <td height="25" ><div align="center">失败原因</div></td>
    <td height="25" ><div align="center">操作</div></td>
  </tr>
  	<s:iterator id="rs" value="page.result" status="st">
	  <tr>
	  <input type="hidden" value="${rs.id }" id="myhidden_<s:property value='#st.index'/>"></input>
	  <td><input type="checkbox" name="mycheckbox" id="mycheckbox_<s:property value='#st.index'/>"></input></td>
      <td height="25" >${rs.id}</td>
      <td height="25" ><s:date name="#rs.applyTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }"  target="blank">${rs.customer.nickName}</a></td>
      <td height="25" >${rs.realName}</td>
      <td height="25" >${rs.bankCard}</td>
      <td height="25" >${rs.bank} </td>
      <td height="25" >${rs.openSpace }</td>
      <td height="25" ><strong class="red">￥${rs.money}</strong></td>
      <td height="25" id="stat_<s:property value='#st.index'/>">${rs.status}</td>
      <td height="25" >${rs.user.adminName}</td>
      <td height="25" ><s:date name="#rs.sendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
      <td height="25" >
      	<c:if test="${rs.status=='一级审核'}">
      		<input type="text" id="message_${rs.id}" value="${rs.memo }"></input>
        </c:if>
        ${rs.memo }
      </td>
      <td height="25" >
       <a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }" target="blank">|审核|</a>
       <c:if test="${rs.status=='一级审核'}">
         <input type="button" value="已审" onclick="subm('option',${rs.id});"/><input type="button" value="取消" onclick="subm('exit',${rs.id});" />　
        </c:if>
        <c:if test="${rs.status=='二级审核'}">
                       已审核
        </c:if>
      </td>
      </tr>
    </s:iterator>
</table>
  <table width="90%" border="0" align="center">
    <tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
    </tr>
  </table>
</s:form>
</div>

</body>
