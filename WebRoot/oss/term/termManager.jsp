<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>彩期管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(cmd,strId,type) 
{
	if(type != "竞彩足球") {
	    $("#termId").val(strId);
	    $("#action2").val(cmd);
		$("form").submit();
	}
	else {
		this.location.href = "/oss/term/manageJCZQTerm.htm?action=list";
	}
}

function opendetail(strId) 
{
 	window.open("manageTerm.aspx?action=detailByTerm&termId="+strId,'newwindow','height=700,width=600,top=100,left=200,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
}
</script>
</head>
<body>
<div class="tab">
<s:form  id="form_id" action="manageTerm" method="post" namespace="/oss/term">
<s:hidden name="action" value="list" />
<table width="60%">
<caption class="redbold">彩期管理</caption>
  <tr>
    <td width="120" height="25" ><div align="center">
   彩种: <s:select list="types" name="type" /></div></td>
    <td width="130" height="25">状态：
    <select name="statusStr" id="statusStr">
    <s:if test="statusStr.length()>0"><option value="${statusStr}" selected="selected">${statusStr}</option></s:if>
    <option value="" >请选择..</option>
    <s:iterator id="term" value="termStatus" >
     <option value="${term}" >${term}</option>
      </s:iterator>
    </select>
    </td>
    <td width="220" height="25">期次：<input type="text" name="f_term" id="f_term" value="${f_term }" /></td>
    <td width="220" >开奖时间:<input type="text" name="f_openWinTime" value="<s:date name="f_openWinTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/></td>
	<td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="submit" name="submit" value="查询" />
	  </div></td>
  </tr>
</table>
</s:form>
<br>
<s:form  action="manageTerm" method="post" namespace="/oss/term">
<s:hidden name="action" value="list" id="action2" />
<s:hidden name="type" />
<s:hidden name="statusStr" />
<s:hidden name="f_term" />
<s:hidden name="termId" id="termId"/>
<s:hidden name="f_openWinTime" />
<table >
  <tr>
    <td height="25"><div align="center">期次</div></td>
    <td height="25"><div align="center">彩种</div></td>
    <td height="25"><div align="center">开售时间</div></td>
    <td height="25"><div align="center">停售时间</div></td>
    <td height="25"><div align="center">合买截止时间</div></td>
    <td height="25"><div align="center">开奖时间</div></td>
    <td height="25"><div align="center">开奖号码</div></td>
    <td height="25"><div align="center">开奖明细</div></td>
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
       <td height="25" ><a onclick="opendetail(${term.id});" >查看</a></td>
      <td height="25" >${term.termStatus} </td>
      <td height="25" >
               <input type="button" value="编辑" onclick="subm('edit',${term.id},'${term.type}');"></input>　
               <s:if test="#term.current">
               <s:if test="'暂停销售'==#term.termStatus.toString()"><input type="button" value="开始销售" onclick="subm('startWin',${term.id});"></input></s:if>
               <c:if test="${term.termStatus=='销售中'}"><input type="button" value="暂停销售" onclick="subm('suspendWin',${term.id});">　</c:if>
               </s:if></td>
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
