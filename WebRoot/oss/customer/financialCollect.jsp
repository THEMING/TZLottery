<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<head>
<title>财务统计管理</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.4.2.min.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm() {
    var f_stime=$("#f_stime").val();
    var f_etime=$("#f_etime").val();
    if((f_stime==null || f_stime=="") && (f_etime!=null && f_etime!="")){
        alert("开始时间不能为空！");
        return;
    }
    if((f_stime!=null && f_stime!="") && (f_etime==null || f_etime=="")){
        alert("结束时间不能为空！");
        return;
    }
     if((f_stime!=null && f_stime!="") && (f_etime!=null && f_etime!="")){
        if(f_stime>f_etime){
            alert("开始时间不能大于结束时间！");
            return;
        }
    }
    
    $("#action").val("dayExportExcel");
    $("#myform").submit();
}
function subm1(){
    var f_stime=$("#f_stime").val();
    var f_etime=$("#f_etime").val();
    if((f_stime==null || f_stime=="") && (f_etime!=null && f_etime!="")){
        alert("开始时间不能为空！");
        return;
    }
    if((f_stime!=null && f_stime!="") && (f_etime==null || f_etime=="")){
        alert("结束时间不能为空！");
        return;
    }
     if((f_stime!=null && f_stime!="") && (f_etime!=null && f_etime!="")){
        if(f_stime>f_etime){
            alert("开始时间不能大于结束时间！");
            return;
        }
    }
    
    $("#action").val("weekExportExcel");
    $("#myform").submit();
}
function seach(){
    var f_stime=$("#f_stime").val();
    var f_etime=$("#f_etime").val();
    if((f_stime!=null && f_stime!="") && (f_etime!=null && f_etime!="")){
        if(f_stime>f_etime){
            alert("开始时间不能大于结束时间！");
            return;
        }
    }
   $("#action").val("index");
    $("#myform").submit();
}
</script>
</head>
<body>
 <div class="tab">
<s:form  action="manageFinancialStatisticsQuery" method="post" enctype="multipart/form-data" id="myform">
<s:hidden name="action" id="action" value="index"/>
<table width="60%">
<caption class="redbold">财务统计管理</caption>
  <tr>
    <td>查询时间:<input type="text" id="f_stime" name="f_stime" value="<s:date name="f_stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" id="f_etime" name="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/></td>
    <td>
                  出票商：<select name="ticketThirdName" id="ticketThirdName">
                   <option value="">请选择..</option>
                   <option value="我中啦">我中啦</option>
                   <option value="大赢家">大赢家</option>
               </select>
    </td>
    <td>
               购彩类型：<s:select name="source" id="source" list="sources" headerKey="" headerValue="请选择.."></s:select>
    </td>
  </tr>
  <tr>
   <td width="150" height="25" bgcolor="#ffffff"><div align="left">
	  <input type="button" value="查询" onclick="seach()" />
	  </div></td>
	<td ><div >
	  <input type="button" onclick="subm()" value="日报统计导出" />
	  </div></td>
	<td ><div >
	  <input type="button" onclick="subm1()" value="周报统计导出" />
	  </div></td>
  </tr>
</table>

<br>
<table>
  <tr>
    <td>预付款金额统计：<font color="red">${prePayMoney}</font></td>
    <td>提现金额统计：<font color="red">${tixianMoney}</font></td>
    <td>消耗金额统计：<font color="red">${consumptionMoney}</font></td>
    <td>用户余额统计：<font color="red">${balance}</font></td>
    <td>用户被冻结金额统计：<font color="red">${freezeMoney}</font></td>
  </tr>
</table>
<table >
<tr><td colspan="6">冲值统计</td></tr>
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">支付宝手机充值</div> </td>
    <td height="25"><div align="center">快钱充值</div> </td>  
    <td height="25"><div align="center">系统扣款</div> </td>
    <td height="25"><div align="center">直接充值</div> </td>  
    <td height="25"><div align="center">总充值</div> </td>  
  </tr>
   <tr>
      <td height="25" ><s:property value="zhifubaoMoney"/></td>
      <td height="25" ><s:property value="kuaiqianMoney"/></td>
      <td height="25" ><s:property value="xitongkoukuanMoney"/></td>
      <td height="25" ><s:property value="zhijiechongzhiMoney"/></td>
      <td height="25" ><s:property value="allMoney"/></td>
   </tr>
  
</table>
<table >
  <tr><td colspan="5">销售统计</td></tr>
  <tr><!--（成功，失败）-->
    <td height="25"><div align="center">销售彩种</div></td>
   <!--  <td height="25"><div align="center">销售金额</div></td>    -->
    <td height="25"><div align="center">出票金额</div></td>
    <td height="25"><div align="center">中奖金额</div></td>
    <td height="25"><div align="center">税后金额</div></td>
  </tr>
  	<s:iterator id="rs" value="orders">
	  <tr>
      <td height="25" align="center">${rs[4]}</td>
     <!--  <td height="25">${rs[0]}</td> -->
      <td height="25">${rs[1]}</td>
      <td height="25">${rs[2]}</td>
      <td height="25">${rs[3]}</td>
     </tr>
    </s:iterator>
    <s:iterator id="sm" value="sumMoney">
	  <tr>
      <td height="25" align="center">彩种总金额</td>
      <!-- <td height="25">${sm[0]}</td> -->
      <td height="25">${sm[1]}</td>
      <td height="25">${sm[2]}</td>
      <td height="25">${sm[3]}</td>
     </tr>
    </s:iterator>
    
</table>
</s:form>
</div>

</body>
