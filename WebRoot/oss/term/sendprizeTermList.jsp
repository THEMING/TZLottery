<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>兑奖管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function doSubmit(cmd,strId) {
    if(!confirm('确实兑奖')){
        return false;
      }
    $("#termId").val(strId);
    $("#action").val(cmd);
	$("form").submit();
}
</script>
</head>
<body>
 <div class="tab">
<s:form  action="manageDistributePrize" method="post" namespace="/oss/term">
<table width="60%">
<caption class="redbold">兑奖管理</caption>
<caption class="redbold"><s:actionmessage/></caption>
  <tr>
    <td width="120" height="25" ><div align="center">
   彩种: <s:select list="types" name="type" id="type"/></div></td>
     <td width="220" height="25">期次：<input type="text" name="f_term" id=""/></td>
     <td>状态: <select name="f_status" id="f_status">
        <s:if test="f_status.length()>0"><option value="${f_status}" selected="selected">${f_status}</option></s:if>
       <option value="" >全部</option>
       <s:iterator id="rs" value="status" >
       <option value="${rs}" >${rs}</option>
       </s:iterator>
      </select> </td>
     <td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" name="submit" value="查询" />
	  </div></td>
  </tr>
</table>
</s:form>
<br>

<s:form  action="manageDistributePrize" method="post" namespace="/oss/term">
<s:hidden name="action" id="action" value="index"/>
<s:hidden name="termId" id="termId"/>
<s:hidden name="f_status" id="f_status"/>
<s:hidden name="type" id="type"/>
<h1 class="redbold">注：以下为可开奖期次（没有开奖号码不可开奖）</h1>
<table >
  <tr>
    <td height="25"><div align="center">彩种</div></td>
    <td height="25"><div align="center">期次</div></td>
    <td height="25"><div align="center">开奖结果</div></td>
    <td height="25"><div align="center">状态</div></td>
    <td height="25"><div align="center">操作</div></td>
    <td height="25"><div align="center">详情</div></td>
  </tr>
  	   <s:iterator id="rs" value="page.result">
	   <tr>
      <td height="25" >${rs.type}</td>
      <td height="25" >${rs.termNo}</td>
      <td height="25" >${rs.result}</td>
      <td height="25" >${rs.termStatus }</td>
      <td height="25" >
      <c:if test="${rs.termStatus=='未兑奖' or rs.termStatus=='已开奖'}"><input type="button" value="兑奖" onclick="doSubmit('sendWin',${rs.id});"/></c:if>
     <c:if test="${rs.termStatus!='未兑奖' and rs.termStatus!='已开奖'}">兑奖</c:if>
     </td>
      <td height="25"><a href="manageSendOrder.aspx?termId=${rs.id}">查看</a></td>
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
