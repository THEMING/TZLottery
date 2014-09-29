<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心 -- 代购查询</title>
<link href="../styles/user.css" rel="stylesheet" type="text/css" />
<script src="../skin/01/js/jquery-1.3.2.js" type=text/javascript></script>
<script src="../skin/01/js/lottery.js" type=text/javascript></script>
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
<script>
function subm(cmd,strId) {
    $("#action").val(cmd);
	$("form").submit();
}
function tj() {
	$.gocaiTongji.callback = function(json){
		$("#outamount").text(json.outamount);
		$("#failur").text(json.failur);
		$("#cplan").text(json.cplan);
		$("#suplan").text(json.suplan);
		$("#zjplan").text(json.zjplan);
		$("#shuiqian").text(json.shuiqian);
		$("#shuihou").text(json.shuihou);
		$("#count").text(json.count);
		$("#allamount").text(json.allamount);
		encodeURI($("#desc").val());
	};
	$.gocaiTongji._request({f_serch:encodeURI($("#f_serch option:selected").val()),f_value:encodeURI($("#f_value").val()),f_name:$("#f_name").val(),f_serchTerm:$("#f_serchTerm").val(),f_serchTerm1:$("#f_serchTerm1").val(),f_stime:$("#f_stime").val(),f_etime:$("#f_etime").val(),f_style:encodeURI($("#f_style option:selected").val()),f_Amount1:$("#f_Amount1").val(),f_Amount2:$("#f_Amount2").val(),f_winTaxMoney1:$("#f_winTaxMoney1").val(),f_winTaxMoney2:$("#f_winTaxMoney2").val(),f_sstatu:encodeURI($("#f_sstatu option:selected").val()),f_rstatu:encodeURI($("#f_rstatu option:selected").val()),f_type:encodeURI($("#f_type option:selected").val()),reg_stime:$("#reg_stime").val(),reg_etime:$("#reg_etime").val(),ticketThirdName:encodeURI($("#ticketThirdName option:selected").val())});

}
</script>
</head>
<body>
	<div>
		<!--bd begin-->
		<div class="bd">
        	<div class="bd">
				<!--user_main begin-->
				<s:form  action="manageBuyLotteryQuery" method="post">
                <div class="user_main"> 
                    <h3><strong>购彩查询</strong></h3>
                    <div class="user_bor">
                        <p class="u_serch">
                                 购彩方式:<select name="f_style" id="f_style">
                             <s:if test="f_style.length()>0"><option value="${f_style}" selected="selected">${f_style}</option></s:if>
                               <option value="全部" >全部</option>
                                <option value="代购" >代购</option>
                               <option value="合买" >合买</option>
                               <option value="追号" >追号</option>
                            </select>｜
                             <select name="f_serch" id="f_serch">
                              <s:if test="f_serch.length()>0"><option value="${f_serch}" selected="selected">${f_serch}</option></s:if>
                               <option value="" >请选择..</option>
                               <option value="用户名" >用户名</option>
                                <option value="订单号" >订单号</option>
                               </select>:<input type="text" name="f_value" id="f_value" value="${f_value }"/>｜
                                              出票状态: <select name="f_sstatu" id="f_sstatu">
                           <s:if test="f_sstatu.length()>0"><option value="${f_sstatu}" selected="selected">${f_sstatu}</option></s:if>
                                  <option value="" >全部</option>
                             <s:iterator id="rs" value="ostatus" >
                                 <option value="${rs}" >${rs}</option>
                            </s:iterator>
                          </select> 
                                                         ｜购彩时间：
                           <input type="text" name="f_stime" id="f_stime" value="<s:date name="f_stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
                            <input type="text" name="f_etime" id="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
                        </p>
                        <p class="u_serch">
                                                    中奖状态:<select name="f_rstatu" id="f_rstatu">
                              <s:if test="f_rstatu.length()>0"><option value="${f_rstatu}" selected="selected">${f_rstatu}</option></s:if>
                                <option value="" >全部</option>
                               <s:iterator id="rs" value="oresult" >
                               <option value="${rs}" >${rs}</option>
                             </s:iterator>
                         </select> 
                                                    ｜方案总额范围:<input type="text" name="f_Amount1" id="f_Amount1" value="${f_Amount1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text" name="f_Amount2" id="f_Amount2" value="${f_Amount2 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
                                                 ｜ 方案奖金范围:<input type="text" name="f_winTaxMoney1" id="f_winTaxMoney1" value="${f_winTaxMoney1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text" name="f_winTaxMoney2" id="f_winTaxMoney2" value="${f_winTaxMoney1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
                        </p>
                        <p class="u_serch">
                         <label>彩种：</label>
                           <s:select list="types" name="f_type" id="f_type" />
                                                        ｜  期次:<input type="text" name="f_serchTerm" id="f_serchTerm" value="${f_serchTerm }" onkeyup="value=value.replace(/[^\d]/g,'1');" />-<input type="text" name="f_serchTerm1" id="f_serchTerm1" value="${f_serchTerm1 }" onkeyup="value=value.replace(/[^\d]/g,'1');" />
                                                           　｜用户注册时间： <input type="text" name="reg_stime" id="reg_stime" value="<s:date name="reg_stime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
                            <input type="text" name="reg_etime" id="reg_etime" value="<s:date name="reg_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>		
                        </p>
                        <p class="u_serch">
                        	用户来源：<s:select list="userTypes" name="userType" headerKey="" headerValue="请选择.."/> 出票商：<s:select name="ticketThirdName" id="ticketThirdName" list="names" headerKey="" headerValue="请选择.."></s:select>  <input type="submit" value="确 定" />
                        </p>
                        <p class="u_serch"><input type="button" value="统计" onclick="tj();"/>总金额： <span id="allamount">0</span>元      出票成功金额: <span id="outamount">0</span>元　出票失败金额:<span id="failur">0</span>元　方案总数:<span id="cplan">0</span>　成功票数:<span id="suplan">0</span>　中奖方案数:<span id="zjplan">0</span>　税前中奖总额:<span id="shuiqian">0</span>元　税后中奖总额:<span id="shuihou">0</span>元　购彩用户:<span id="count">0</span>人</p>
                        <div class="bg_w">
                          <table>
                                <tr>
								 <th>用户名</th>
                                 <th>彩种</th>
                                <th>期次</th>
                                <th>发起人</th>
                                <th>购彩时间</th>
                                <th>方案总额</th>
                                <th>中奖状态</th>
                               <!--   <th>税前奖金</th>-->
                                <th>税后奖金</th>
                                <th>出票商</th>
                                <th>出票状态</th>
                                <th>类型</th>
                                <th>订单号</th>
                                <th>来源</th>
                                </tr>
                                 <s:iterator id="rs" value="page.result">
	  <tr>
      <td  >${rs.customer.nickName}</td>
      <td  >${rs.type}</td>
      <td  >${rs.term.termNo}</td>
      <td >
      <s:if test="#rs.community!=null"><a href="manageCustomer.aspx?action=view&customerId=${rs.community.customer.id }">${rs.community.customer.nickName}</a></s:if>
      <s:else><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }">${rs.customer.nickName}</a></s:else>
      </td>
      <td  >
      <s:date name="#rs.buildTime" format="yyyy-MM-dd HH:mm"/>
      </td>
      <td >${rs.amount}</td>
      <td >${rs.orderResult}</td>
      <!--  <td >${rs.winMoney}</td>-->
      <td >${rs.winTaxMoney}</td>
      <td>${rs.ticketThirdName}</td>
      <td >${rs.status}</td>
      <td > 
      
      <s:if test="#rs.community!=null">合买</s:if>
      <s:elseif test="#rs.chaseItem!=null">追号</s:elseif>
      <s:else>代购</s:else>
      
      </td>
      <td>
       
      <s:if test="#rs.community!=null"><a href="manageGroupBuyQuery.aspx?action=detail&numberNo=${rs.plan.numberNo}">${rs.plan.numberNo}</a></s:if>
      <s:elseif test="#rs.chaseItem!=null"><a href="manageChaseItemQuery.aspx?action=detail&itemId=${rs.chaseItem.id}">${rs.plan.numberNo}</a></s:elseif>
     <s:else><a href="manageBuyLotteryQuery.aspx?action=detail&numberNo=${rs.plan.numberNo}">${rs.plan.numberNo}</a></s:else>
     </td>
     <td>${rs.source }</td>
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

