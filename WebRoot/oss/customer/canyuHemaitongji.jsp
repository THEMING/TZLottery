<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心 -- 代购查询</title>
<link href="../styles/user.css" rel="stylesheet" type="text/css" />
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="../skin/01/js/lottery.js" type="text/javascript"></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
function subm(cmd,strId) {
    $("#customerId").val(strId);
    $("#action2").val(cmd);
	$("form").submit();
}
function tj(){
	$.canyuTongji.callback = function(json){
		$("#buymoney").text(json.buymoney);
		$("#zjmoney").text(json.zjmoney);
	};
	$.canyuTongji._request({f_serch:encodeURI($("#f_serch option:selected").val()),f_value:encodeURI($("#f_value").val()),f_serchTerm:$("#f_serchTerm").val(),f_serchTerm1:$("#f_serchTerm1").val(),f_stime:$("#f_stime").val(),f_etime:$("#f_etime").val(),f_okPart1:$("#f_okPart1").val(),f_okPart2:$("#f_okPart2").val(),f_sstatu:encodeURI($("#f_sstatu option:selected").val()),f_type:encodeURI($("#f_type option:selected").val())});

}
</script>
</head>
<body>
	<div>
        <!--bd begin-->
      <div class="bd">
        	<div class="bd">
                 <!--user_main begin-->
              <s:form  action="manageJoinGroupBuyQuery" method="post">
                <div class="user_main"> 
                    <h3><strong>购彩查询</strong></h3>
                    <div class="user_bor">
                        <p class="u_serch">
                     <select name="f_serch" id="f_serch">
                            <s:if test="f_serch.length()>0"><option value="${f_serch}" selected="selected">${f_serch}</option></s:if>
                           <option value="" >请选择..</option>
                           <option value="参与人" >参与人</option>
                           <option value="发起人" >发起人</option>
                           <option value="方案号" >方案号</option>
                     </select>:<input type="text" name="f_value" id="f_value" value="${f_value }"/>｜
					 彩种: <s:select list="types" name="f_type" id="f_type" />
                     ｜期次:<input type="text" name="f_serchTerm" id="f_serchTerm" value="${f_serchTerm }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text"         name="f_serchTerm1" id="f_serchTerm1" value="${f_serchTerm1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
                        </p>
                        <p class="u_serch">
                         状态: <select name="f_sstatu" id="f_sstatu">
                                <s:if test="f_sstatu.length()>0"><option value="${f_sstatu}" selected="selected">${f_sstatu}</option></s:if>
                                 <option value="" >全部</option>
                                <s:iterator id="rs" value="statu" >
                                  <option value="${rs}" >${rs}</option>
                               </s:iterator>
                              </select> 
						｜时间:<input type="text" name="f_stime" id="f_stime" value="<s:date name="f_stime" id="f_stime" format="yyyy-MM-dd HH:mm:ss"/>"                              onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>- 
                            <input type="text" name="f_etime" id="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						｜完成进度范围:<input type="text" name="f_okPart1" id="f_okPart1" value="${f_okPart1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text" name="f_okPart2" id="f_okPart2" value="${f_okPart2 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
						<input type="submit" value="确 定" />
                        </p>
                        <p class="u_serch"> <input type="button" value="统计" onclick="tj();"/></td><td>购买金额:<span id="buymoney">0</span>元　中奖金额:<span id="zjmoney">0</span>元　</p>
                        <div class="bg_w">
                          <table>
                                <tr>
								 <th>参与人</th>
                                 <th>发起人</th>
                                 <th>彩种</th>
                                 <th>期次</th>
                                 <th>投注时间</th>
                                 <th>方案总额</th>
                                 <th>认购金额</th>
                                 <th>进度</th>
                                 <th>状态</th>
                                 <th>奖金</th>
                                 <th>方案号</th>
                                </tr>
                                 <s:iterator id="rs" value="page.result">
	  <tr>
     <td  ><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }">${rs.customer.nickName}</a></td>
      <td  ><a href="manageCustomer.aspx?action=view&customerId=${rs.community.customer.id }">${rs.community.customer.nickName}</a></td>
      <td >${rs.community.term.type}</td>
      <td >${rs.community.term.termNo}</td>
      <td ><s:date name="#rs.joinTime" format="yyyy-MM-dd HH:mm"/></td>
      <td>${rs.community.plan.money}</td>
      <td>${rs.money}</td>
      <td ><fmt:formatNumber type="percent" value="${rs.community.okPart/rs.community.totalPart}"  minFractionDigits="2"/></td>
      <td >${rs.community.communityType}</td>
      <td >${rs.winTaxMoney}</td>
      <td><a href="manageGroupBuyQuery.aspx?action=detail&numberNo=${rs.community.plan.numberNo}">${rs.community.plan.numberNo}</a></td>
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

