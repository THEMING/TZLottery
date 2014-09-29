<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<html>
	<head>
		<title>彩种管理</title>
		<meta name="heading" content="彩种配置" />
		<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="../../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				$("input:checkbox").each(function(){
					oncheck(this);
				});
				
				var selectValue = "${currentTerm.termNo}"
				$("#termNoList").val(selectValue);
			});
			
			function oncheck(obj){
				$(obj).val($(obj).attr("checked"));
			}
			
			function checkonsubmit() {
				if("全部"==$("#type").val()) {
					alert("请选择投注彩种！");
					return false;
				}
				
				if(findCurrent != 1) {
					if(""==$("#termNo").val()) {
						alert("请输入彩期期号！");
						return false;
					}
					
					if(""==$("#startSaleTime").val()) {
						alert("请输入开始销售时间！");
						return false;
					}
					
					if(""==$("#stopTogetherSaleTime").val()) {
						alert("请输入合买截止时间！");
						return false;
					}
					
					if(""==$("#stopSaleTime").val()) {
						alert("请输入销售截止时间！");
						return false;
					}
					
					if(""==$("#openPrizeTime").val()) {
						alert("请输入开奖时间！");
						return false;
					}
					
					if(""==$("#sendPrizeTime").val()) {
						alert("请输入派奖时间！");
						return false;
					}
				}
				
				if($("#newTermCheckBox").attr("checked")) {
					if(""==$("#newTermNo").val()) {
						alert("请输入新彩期！");
						return false;
					}
				}
				return true;
			}
			
			var findCurrent = 0;
 			function findsub() {
 				$("#termconfig_action").val("index");
 				findCurrent = 1;
 				$("#sub").click();
 				findCurrent = 0;
 			}
 			
 				
 			function onAddNewTerm(obj) {
 				var checked = $(obj).attr("checked");
 				if(checked) {
 					$("#addNewTerm").val("true");
 					alert($("#addNewTerm").val());
 				}
 				else {
 					$("#addNewTerm").val("false");
 				}
 			}
 			
 			function onChooseTerm()
 			{
 				$("#addNewTerm").val("false");
 				
 				$("#termconfig_action").val("findByTerm");
 				$("#sub").click();
 			}
		</script>
	</head>
	<body>
		<s:form action="configTerm" method="post" namespace="/oss/term" onsubmit="return checkonsubmit()">
			<s:hidden id="termconfig_action" name="action" value="saveTerm" />
			<s:hidden id="addNewTerm" name="addNewTerm" value="false" />
			<s:hidden name="termConfig.id" id="termConfig_id"/>
			<s:hidden name="currentTerm.id" id="currentTerm_id"/>
			<table cellpadding="5" border="1"  style="border: solid 2px #ff0099;">
				<tr>
					<td colspan="4" align="center">
						<label><s:property value="type"/>彩期配置(当前彩期修改、录入)</label><br>
						<span style="color:red"><s:actionmessage/></span>
					</td>
				</tr>
				<tr>
					<td>彩 种:</td>
					<td><s:select list="typeList" name="type" id="type" onchange="findsub()"/></td>
					<td></td>
					<td rowspan="4" class="gray"></td>
				</tr>
				<tr>
					<td>出票点:</td>
					<td><s:select list="sendTicketPlats" name="termConfig.outPoint" /></td>
				</tr>
				<tr>
					<td>
						自动开奖:<input type="checkbox"
						<s:if test="termConfig.autoOpen">checked</s:if>
						name="termConfig.autoOpen" onchange="oncheck(this)" />
					</td>
					<td>
						自动兑奖:<input type="checkbox"
						<s:if test="termConfig.autoCheckWin">checked</s:if>
						name="termConfig.autoCheckWin" onchange="oncheck(this)" />
					</td>
					<td>是否停运:<input type="checkbox" <s:if test="termConfig.stop">checked</s:if>
					name="termConfig.stop" onchange="oncheck(this)" /></td>
				</tr>
				<tbody>
					<tr align="center">
						<td colspan="4">当前期信息：</td>
					</tr>
					<tr>
						<td>彩期期号:</td> 
						<c:if test="${currentTermList != null}">
							<td>
								<span> <select id="termNoList" name="currentTerm.termNo" onchange="onChooseTerm()">
									<s:iterator value="currentTermList">
										<option value="<s:property value="termNo"/>"><s:property value="termNo"/></option>
									</s:iterator>
								</select>
								</span>
							</td>
							<td>
								<input type="text" name="termNo" id="newTermNo"/> 
								<input id="newTermCheckBox" type="checkbox" onclick="onAddNewTerm(this)"/>添加新期
							</td>
						</c:if>
						<c:if test="${currentTermList == null}">
							<td><s:textfield id="termNo" name="currentTerm.termNo" size="20" /></td>
						</c:if>
					</tr>
					<tr>
						<td>彩期开始销售时间:</td> 
						<td><input type="text" class="calendarFocus" size="15" name="startSaleTime" 
							id="startSaleTime" value="<s:date name="currentTerm.startSaleTime" format="yyyy-MM-dd HH:mm"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/>
						</td>
						<td>彩期合买截止时间:</td> 
						<td><input type="text" class="calendarFocus" size="15" name="stopTogetherSaleTime" 
							id="stopTogetherSaleTime" value="<s:date name="currentTerm.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm"/>"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/>
						</td>
					</tr>
					<tr>
						<td>彩期销售截止时间:</td> 
						<td><input type="text" class="calendarFocus" size="15" name="stopSaleTime" id="stopSaleTime"
							value="<s:date name="currentTerm.stopSaleTime" format="yyyy-MM-dd HH:mm"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/>
						</td>
					</tr>
					<tr>
						<td>彩期开奖时间:</td> 
						<td><input type="text" class="calendarFocus" size="15" name="openPrizeTime" id="openPrizeTime"
							value="<s:date name="currentTerm.openPrizeTime" format="yyyy-MM-dd HH:mm"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/>
						</td>
						<td>彩 期 派 奖时间:</td> 
						<td><input type="text" class="calendarFocus" size="15" name="sendPrizeTime" id="sendPrizeTime"
							value="<s:date name="currentTerm.sendPrizeTime" format="yyyy-MM-dd HH:mm"/>" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});"/>
						</td>
					</tr>
				</tbody>
				<tr>
					<td colspan="3" align="center"><input type="submit"  value="提  交" width="50" id="sub"></td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
