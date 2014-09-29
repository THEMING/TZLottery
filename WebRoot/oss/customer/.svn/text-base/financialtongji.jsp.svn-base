<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资金明细</title>
<link href="../styles/user.css" rel="stylesheet" type="text/css" />
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/lottery.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(cmd,strId) {
    $("#customerId").val(strId);
    $("#action2").val(cmd);
	$("form").submit();
}
function tj(){
	$.mxTongji.callback = function(json){
		$("#sr").text(json.sr);
		$("#zc").text(json.zc);
		$("#srmoney").text(json.srmoney);
		$("#zcmoney").text(json.zcmoney);
		$("#jdmoney").text(json.jdmoney);
	};
	$.mxTongji._request({f_type:encodeURI($("#f_type option:selected").val()),f_name:encodeURI($("#f_name").val()),f_stime:$("#f_stime").val(),f_etime:$("#f_etime").val()});
}

</script>
</head>
<body>
	<div>
        <!--bd begin-->
      <div class="bd">
        	<div class="bd">
                 <!--user_main begin-->
 <s:form  action="manageFinanialQuery" method="post" >
                <div class="user_main"> 
                    <h3><strong>资金明细</strong></h3>
                    <div class="user_bor">
                        <p class="u_serch">
						查询类型
    <select name="f_type" id="f_type">
    <s:if test="f_type.length()>0"><option value="${f_type}" selected="selected">${f_type}</option></s:if>
     <option value="" >请选择..</option>
    <!--   <optgroup label="支出">
     <c:forEach items="${types}" var="rs" begin="0" end="9">
    <option value="${rs }" ><c:out value="${rs }" /></option>
    </c:forEach>
    <option value="套餐消费" >套餐消费</option>
    </optgroup>
    <optgroup label="收 入">
     <c:forEach items="${types}" var="rs" begin="10" end="23">
    <option value="${rs }" ><c:out value="${rs }" /></option>
    </c:forEach>
    </optgroup>-->
    <c:forEach items="${types}" var="rs">
       <option value="${rs }" ><c:out value="${rs }" /></option>
    </c:forEach>
    </select>
	用户名:<input type="text" name="f_name" id="f_name" value="${f_name }" />
	 时间:<input type="text" name="f_stime" id="f_stime" value="<s:date name="f_stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="f_etime" id="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
	 <input type="submit" value="查询" />
                        </p>
                        <p class="u_serch">
                       <input type="button" value="统计" onclick="tj();"/>收入:<span id="sr">0</span>　支出:<span id="zc">0</span>　收入总额:<span id="srmoney">0</span>元  支出总额:<span id="zcmoney">0</span>元
                        	 解冻减少金额：<span id="jdmoney">0</span>
                        </p>
                       <div class="bg_w">
                          <table>
                                <tr>
                                   <th>用户名 </th>
    <th>时间 </th>
    <th>余额 </th>
    <th>状态 </th>
    <th>交易金额 </th>
    <th>冻结/解冻 </th>
    <th>交易类型 </th>
    <th>描述 </th>
    <th>方案号 </th>
                                </tr>
                                 <s:iterator id="rs" value="page.result">
	  <tr>
    <td height="25" >${rs.wallet.customer.nickName}</td>
      <td height="25" ><s:date name="#rs.time" format="yyyy-MM-dd HH:mm:ss"/></td>
      <td height="25" >${rs.currentMoney}</td>
      <td height="25" >${rs.businessType}</td>
      <td height="25" >${rs.inMoney }/-${rs.outMoney }</td>
      <td height="25" >${rs.inFreezeMoney }/-${rs.outFreezeMoney }</td>
      <td height="25" >${rs.type}</td>
      <td height="25" >${rs.description}</td>
      <td height="25" >
     
     <c:if test="${fn:indexOf(rs.type,'追号') >-1 or fn:indexOf(rs.description,'追号') >-1}"><a href="manageChaseItemQuery.aspx?action=list&numberNo=${rs.serialNumber}"> ${rs.serialNumber}</a></c:if>
     <c:if test="${fn:indexOf(rs.type,'合买') >-1 or fn:indexOf(rs.description,'合买') >-1}"><a href="manageGroupBuyQuery.aspx?action=detail&numberNo=${rs.serialNumber}"> ${rs.serialNumber}</a></c:if>
     <c:if test="${fn:indexOf(rs.type,'购买') >-1 or fn:indexOf(rs.description,'代购') >-1 or fn:indexOf(rs.type,'代购') >-1}"><a href="manageBuyLotteryQuery.aspx?action=detail&numberNo=${rs.serialNumber}">${rs.serialNumber}</a></c:if>
     </td>
      </tr>
    </s:iterator>
   
                            </table>
                        </div>
                        <div ><jsp:include page="../../util/page.jsp"></jsp:include></div>
                    </div>
                </div>
                </s:form>
                 <!--user_main end-->
                 <div class="c_l"></div>
             </div>
        </div>
        <!--bd end-->
    </div>
</body>

