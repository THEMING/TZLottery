<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>快频开奖兑奖</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(strId,type) 
{
    $("#termId").val(strId);
    $("#type").val(type);
	$("form").submit();

}
</script>
</head>
<body>
	<div class="tab">
		<s:form id="form_id" action="kuaipin" method="post" namespace="/oss/term">
			<s:hidden name="action" value="list" />
			<table width="60%" border='1px solid;'>
				<caption class="redbold">彩期管理</caption>
				<tr>
					<td width="120" height="25">
						<div align="center">
							彩种:
								 <s:select list="types" name="type" />
						</div>
					</td>
					<td width="220" height="25">
						期次：
						<input type="text" name="f_term" id="f_term" value="${f_term }" />
					</td>
					<td width="150" height="25" bgcolor="#ffffff">
						<div align="left">
							<input type="submit" name="submit" value="查询" />
						</div>
					</td>
				</tr>
			</table>
		</s:form>
		<br>

		<s:form action="kuaipin" method="post" namespace="/oss/term">
			<s:hidden name="action" value="open" id="action2" />
			<s:hidden name="type" id="type" />
			<s:hidden name="termId" id="termId" />
			<table>
				<tr>
					<td height="25">
						<div align="center">
							期次
						</div>
					</td>
					<td height="25">
						<div align="center">
							彩种
						</div>
					</td>
					<td height="25">
						<div align="center">
							开售时间
						</div>
					</td>
					<td height="25">
						<div align="center">
							停售时间
						</div>
					</td>
					<td height="25">
						<div align="center">
							合买截止时间
						</div>
					</td>
					<td height="25">
						<div align="center">
							开奖时间
						</div>
					</td>
					<td height="25">
						<div align="center">
							开奖号码
						</div>
					</td>
					<td height="25">
						<div align="center">
							彩期状态
						</div>
					</td>
					<td height="25">
						<div align="center">
							操作
						</div>
					</td>
				</tr>
				<s:iterator id="term" value="page.result">
					<tr>
						<td height="25">
							${term.termNo}
						</td>
						<td height="25">
							${term.type}
						</td>
						<td height="25">
							<s:date name="#term.startSaleTime" format="yyyy-MM-dd HH:mm" />
						</td>
						<td height="25">
							<s:date name="#term.stopSaleTime" format="yyyy-MM-dd HH:mm" />
						</td>
						<td height="25">
							<s:date name="#term.stopTogetherSaleTime"
								format="yyyy-MM-dd HH:mm" />
						</td>
						<td height="25">
							<s:date name="#term.openPrizeTime" format="yyyy-MM-dd HH:mm" />
						</td>
						<td height="25">
							${term.result}
						</td>
						<td height="25">
							${term.termStatus}
						</td>
						<td height="25">
							<s:if test="#term.current">
								<s:if test="'暂停销售'==#term.termStatus.toString()">
									销售暂停中
								</s:if>
								<c:if test="${term.termStatus=='销售中'}">
									正在销售中
								</c:if>
							</s:if>
							<c:if test="${term.termStatus!='已兑奖' && term.termStatus!='销售中' && term.termStatus!='暂停销售'}">
								<input type="button" value="获取开奖号码" onclick="subm(${term.id},'${term.type}');" />
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
