<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
<head><title>足彩14场编辑</title>
<script src="../../js/jquery-1.4.4.min.js"></script>
<script src="../../js/My97DatePicker/WdatePicker.js"></script>
 <script src="../js/jquery.countdown.pack.js"></script>
<script src="../js/jquery.countdown-zh-CN.js"></script>
<script type="text/javascript">
var termNo;
	$(document).ready(function()
		{
			termNo = "${termNo}";
			for(var i = 0; i < $("#termNo")[0].options.length; i++) {
				if(termNo == $("#termNo")[0].options[i].value) {
					$("#termNo")[0].options[i].selected = "selected";
				}
			}
		}
	);
	
	function doSubmit()
   	{
		var dv = []; 
		var reg = /\d{4}-\d{2}-\d{2} \d{2}:\d{2}/; 
		for(var m = 1; m < 15; m++){
			if($("#gametime" + m).val() == "") {
				alert("第"+m+"场比赛日期不能为空");
				return false; 
			}
			else {
				if(!reg.test($("#gametime"+m).val())) {
				
					alert("第"+m+"场比赛日期格式错误（例：2011-04-12 06:25）");
					return false; 
				}
			}
	        if($("#lsname"+m).val() == "") {
	            alert("第"+m+"联赛名称不能为空");
	            return false;
			}
			if($("#hname"+m).val() == "") {
	            alert("第"+m+"场主队不能为空");
	            return false;                 
	        }
	        if($("#vname"+m).val() == "") {
	            alert("第"+m+"场客队不能为空");
	            return false; 
	        }
			dv.push(m+"|"+$("#lsname"+m).val()+"|"+$("#gametime"+m).val()+"|"+$("#hname"+m).val()+"|"+$("#vname"+m).val() + "|" + $("#sop" + m).val() + "|" + $("#pop" + m).val() + "|" + $("#fop" + m).val());
		}
		$("#allMatches").val(dv.join("^"));
		alert($("#allMatches").val());
	}

   	
   	function clean() 
   	{
		document.all.type.value=0;
   		document.all.term.value="";
   		$("#term").val($.get);
   		for(var m=0;m<14;m++) {
   			$("#gametime"+m).val("");
   			$("#hname"+m).val("");
   			$("#lsname"+m).val("");
   			$("#vname"+m).val("");
		}
		return false;
	}
	
	function onChoose()
	{
		$("#action_id").val("index");
		$("#form_id").submit();
		
	}
	
	function zq()
	{
		if(termNo=="")
		{
			alert("彩期号码不能为空！");
			return false;
		}
		alert(termNo);
		$("#action_id").val("fetchMatchList");
		$("#form_id").submit();
	}
</script>
</head>
<body>

<form id="form_id" action="/oss/football/sfcManager.htm" method="post">
	<s:hidden name="action" id="action_id" value="saveMathces"/>
    <table border="0" cellspacing="0" cellpadding="2" width="100%">
		<tr>
			<td align="center" colspan="2" >胜负彩编辑</td>
		</tr>
		<tr bgcolor="#FFFFFF">
        	<td width="10%" align="right">&nbsp;足彩期号：</td>
        	<td width="90%" align="left">&nbsp;
        		<select name="termNo" id="termNo" onchange="onChoose()">
					<option value="">请选择..</option>
					<s:iterator value="currentTermList">	
						<option value="<s:property value="termNo"/>"><s:property/></option>	
					</s:iterator>
    			</select>
       		</td>
      	</tr>
	
			<div style="height:20px;padding:10px 0 10px 260px;">
			<label>**********<s:property value="type"/>-彩期编辑**********</label>
			<input type="submit" name="submit2" value="抓取" onclick="return zq()" />
			</div>
       <tr>
			<td width="10%" align="right"></td>
			<td width="90%" align="left">
           	<table border="1">
           	<s:if test="matchArrangelist.size() == 0">
           			<% 
           	for(int i = 1; i < 15; i++) { %>
	           	<% 	if(i%2 == 0) {%>
					<tr bgcolor='#ECE1Ef'><td width='80' bgcolor=#ECECEC>&nbsp;第<%=i %>场：</td>
				<% } else {%>
					<tr bgcolor='#FFFFFF'><td width='80' bgcolor=#ECECEC>&nbsp;第<%=i %>场：</td>
				<% } %>
					<td>
	    	       		<table>
	    	          		<tr>
	    	               		<td>比赛时间:<input type="text" id="gametime<%=i %>"  name="gametime<%=i %>" style='width:200' value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});" />
	    	               		</td>
	    	               		<td>赛事名称:&nbsp;<input type="text" id="lsname<%=i %>" name='lsname<%=i %>' style='width:200' />
	    	               		
	    	               		</td>
	    	          		</tr>
	    	          		<tr>
	    	          			<td>主队名称:&nbsp;<input type="text" id="hname<%=i %>" name='hname<%=i %>' style='width:200' />
	    	          			
	    	          			</td>
	    	           			<td>客队名称:&nbsp;<input type="text" id="vname<%=i %>" name='vname<%=i %>' style='width:200' />
	    	           			
	    	           			</td>
	    	          		</tr>
	    	        	</table>
	    	        </td>
    	   		</tr>
    	   	<% } %>
           	</s:if>
           	<s:else>
	           	<s:iterator value="matchArrangelist" status="st">

	           		<tr bgcolor='#ECE1Ef'><td width='80' bgcolor=#ECECEC>&nbsp;第<s:property value="boutIndex"/> 场：</td>
		           		<td>
		    	       		<table>
		    	          		<tr>
		    	               		
		    	               		<td>比赛时间：<input type="text" id="gametime<s:property value="boutIndex"/>"  name="gametime<s:property value="boutIndex"/>" style='width:200' value="<s:date name="matchTime" format="yyyy-MM-dd HH:mm"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true});" />
		    	               		</td>
		    	               		<td>赛事名称:&nbsp;<input type="text" id="lsname<s:property value="boutIndex"/>" name='lsname<s:property value="boutIndex"/>' style='width:200' value="<s:property value="matchName"/>"/>
		    	               		
		    	               		</td>
		    	          		</tr>
		    	          		<tr>
		    	          			<td>主队名称:&nbsp;<input type="text" id="hname<s:property value="boutIndex"/>" name='hname<s:property value="boutIndex"/>' style='width:200' value="<s:property value="homeTeam"/>"/>
		    	          			
		    	          			</td>
		    	           			<td>客队名称:&nbsp;<input type="text" id="vname<s:property value="boutIndex"/>" name='vname<s:property value="boutIndex"/>' style='width:200' value="<s:property value="awaryTeam"/>"/>
		    	           			
		    	           			</td>
		    	          		</tr>
		    	        	</table>
							<input type="hidden" id="sop<s:property value="boutIndex"/>" value="<s:property value="sop"/>"/>
   	           				<input type="hidden" id="pop<s:property value="boutIndex"/>" value="<s:property value="pop"/>"/>
   	           				<input type="hidden" id="fop<s:property value="boutIndex"/>" value="<s:property value="fop"/>"/>
		    	        </td>
	    	   		</tr>
	           	</s:iterator>
           	</s:else>
           
    	   
       </table>
       
       <p style="border-bottom:"></p>

			</td>
        </tr>
        <tr>
            <td width="10%" align="right">&nbsp;</td>
            <td width="90%" align="left">
                <s:hidden name="allMatches" id="allMatches"/>
                <s:submit name="edit" onclick="return doSubmit()" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>