<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心 -- 追号查询</title>
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
	$.zhuihaoTongji.callback = function(json){
		$("#buymoney").text(json.buymoney);
	};
	$.zhuihaoTongji._request({f_name:encodeURI($("#f_name").val()),f_serchTerm:$("#f_serchTerm").val(),f_serchTerm1:$("#f_serchTerm1").val(),f_stime:$("#f_stime").val(),f_etime:$("#f_etime").val(),f_statu:encodeURI($("#f_statu option:selected").val()),f_type:encodeURI($("#f_type option:selected").val())});

}

function check(){
    var chaseids = "";
    $("input:checked").each(function(){  
    	chaseids += $(this).val()+",";
     });
    $("#action").val("stop");
    alert($("#action").val());
    alert(chaseids); 
    
    $("#chaseids").val(chaseids);

    alert($("#chaseids").val());
    
    $("form").submit();
    
    alert("完成");
}

</script>
</head>
<body>
	<div>
        <!--bd begin-->
      <div class="bd">
        	<div class="bd">
                 <!--user_main begin-->
            <s:form  action="manageChaseQuery" method="post">
            	<s:hidden id="action" name="action" value="index"></s:hidden>
            	<s:hidden id="chaseids" name="chaseids" value=""></s:hidden>
                <div class="user_main"> 
                    <h3><strong>追号查询</strong></h3>
                    <div class="user_bor">
                        <p class="u_serch">
                      用户名:<input type="text" name="f_name" id="f_name" value="${f_name }"/>
					  彩种: <s:select list="types" name="f_type" id="f_type" />
					  时间:<input type="text" name="f_stime" id="f_stime" value="<s:date name="f_stime" id="f_stime" format="yyyy-MM-dd HH:mm:ss"/>"                                onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
                         <input type="text" name="f_etime" id="f_etime" value="<s:date name="f_etime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
						  状态: <select name="f_statu" id="f_statu">
                          <s:if test="f_statu.length()>0"><option value="${f_statu}" selected="selected">${f_statu}</option></s:if>
                               <option value="" >全部</option>
                             <s:iterator id="rs" value="status" >
                              <option value="${rs}" >${rs}</option>
                              </s:iterator>
                           </select> 
						   <input type="submit" value="查询" />
                        </p>
                        <p class="u_serch">
                        <input type="button" value="统计" onclick="tj();"/>方案金额:<span id="buymoney">0</span>元
                        <input type="button" value="停止追号" onclick="check()" style="float: right;"/>　
                        </p>
                       <div class="bg_w">
                          <table>
                                <tr>
                                   <th></th>
								   <th>用户名</th>
                                   <th>彩种</th>
                                   <th>投注时间</th>
                                   <th>方案金额</th>
                                   <th>期数</th>
                                   <th>追号状态</th>
                                   <th>停止条件</th>
                                   <th>中奖状态</th>
                                   <th>操作</th>
                                </tr>
                                 <s:iterator id="rs" value="page.result">
	  <tr>
	  	  <td><input type="checkbox" value="${rs.id }" /></td>
	      <td ><a href="manageCustomer.aspx?action=view&customerId=${rs.customer.id }">${rs.customer.nickName}</a></td>
	      <td  >${rs.lotteryType}</td>
	      <td  ><s:date name="#rs.plan.buildTime" format="yyyy-MM-dd HH:mm"/></td>
	      <td  >${rs.money}</td>
	      <td  >${rs.oktermNum}/${rs.termNum}</td>
	      <td  >${rs.status}</td>
	      <td  ><c:if test="${rs.stopMoney=='0.00'}">无</c:if></td>
	      <td  >
	      <s:if test="winprize">已中奖</s:if>
	      <s:else>未中奖</s:else>
	      </td>
	      <td  ><a href="manageChaseItemQuery.aspx?chaseId=${rs.id }">追号详情</a></td>
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

