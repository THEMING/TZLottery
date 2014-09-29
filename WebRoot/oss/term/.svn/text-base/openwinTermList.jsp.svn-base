<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>开奖管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
	function subm(cmd,strId) 
	{  
	    if(!confirm("开奖")){
	    	return false;
	    }
	    $("#termId").val(strId);
	    $("#action").val(cmd);
	    
	    //alert($("#action").val());
		$("#termList_form").submit();
	}
</script>
</head>
<body>
 <div class="tab">
<s:form id="query_form" action="manageOpenPrize" method="post" namespace="/oss/term">
<table width="60%">
<caption class="redbold">开奖管理</caption>
<caption class="redbold"><s:actionmessage/></caption>
  <tr>
    <td width="120" height="25" ><div align="center">
   彩种: <s:select list="types" name="type" /></div></td>
    <td width="220" height="25">期次：<input type="text" name="f_term" value="${f_term }" /></td>
    <td width="150" height="25" bgcolor="#ffffff">
      <div align="left">
	    <input type="submit" name="button" value="查询" />
	  </div>
	</td>
  </tr>
</table>
</s:form>
<br/>
<h1 class="redbold">注：以下为可开奖期次（没有开奖号码不可开奖）</h1>
<s:form id="termList_form" action="manageOpenPrize" method="post" namespace="/oss/term">
<s:hidden name="action" id="action" value="index"/>
<s:hidden name="termId" id="termId"/>
<s:hidden name="type" id="type"/>
<s:hidden name="f_term" id="f_term"/>
<table >
  <tr>
    <td height="25"><div align="center">期次</div></td>
    <td height="25"><div align="center">彩种</div></td>
    <td height="25"><div align="center">开售时间</div></td>
    <td height="25"><div align="center">停售时间</div></td>
    <td height="25"><div align="center">合买截止时间</div></td>
    <td height="25"><div align="center">开奖时间</div></td>
    <td height="25"><div align="center">开奖号码</div></td>
    <td height="25"><div align="center">彩期状态</div></td>
    <td height="25"><div align="center">操作</div></td>
  </tr>
  	   <s:iterator id="term" value="page.result">
	   <tr>
      <td height="25" >${term.termNo}</td>
      <td height="25" >${term.type}</td>
      <td height="25" ><s:date name="#term.startSaleTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><s:date name="#term.stopSaleTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><s:date name="#term.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" ><s:date name="#term.openPrizeTime" format="yyyy-MM-dd HH:mm"/></td>
      <td height="25" >${term.result}</td>
      <td height="25" >${term.termStatus} </td>
      <td height="25" >
               <s:if test="result.length()>0">
               		<input type="button" value="开奖" onclick="subm('openwin',${term.id});"></input>
               </s:if>
               <s:else>
                       开奖
               </s:else>
      </td>
      </tr>
      </s:iterator>
</table>
<tr>
    <td><jsp:include page="../../util/page.jsp"></jsp:include></td>
</tr>
</s:form>
</div>

</body>
