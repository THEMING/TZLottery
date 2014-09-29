<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>软件注册 — 一彩票网</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="../css/common.css" type="text/css" />
	<link href="../css/customer_center.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/lottery/common.js"></script>
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<script src="../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
		
			var tab_num = 3;
	
	function allTogOff() {
		for(var i = 1; i <= tab_num; i++) {
			$("#li"+i).attr("class", "off");
			$("#main"+i).hide();
		}
	}
	
	function togOn(index) {
		allTogOff();
		$("#li"+index).attr("class", "on");
		$("#main"+index).show();
		iframeResize(index);
	}
	
	$(document).ready(function()
		{
			var index = "1";
			togOn(index);
		}
	);
	
	var clipBoardContent = "http://www.369cai.com/?reference=${customer.id}";
   function copyclipboard()
   {  

   window.clipboardData.setData("Text",clipBoardContent);
   alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友！\r\n\r\n内容如下：\r\n" + clipBoardContent);
   }
	
	
		function iframeResize() {
			var obj = parent.document.getElementById("cur_content_frame");
			obj.height = $("#material_panel").height() + 20;
			if(obj.height < 275) obj.height = 275;
		}
	</script>
</head>
<body>
	<div id="material_panel" class="tab_box">
	<form action="/customer/MyRecommenders.htm" method="post">
		<div class="tab_top">
			<ul id="material_panel_ul">
				<li id="li1" onclick="togOn(1)">推荐列表</li>
				<li id="li2" onclick="togOn(2)">提成查询</li>
				<li id="li3" onclick="togOn(3)">邀请好友</li>
			</ul>
		</div>
        <div class="clear"></div>
       <div id="main2" class="main">
	<div id="recommend_box">
	
		<div class="zjmx_title">
　            <label>被推荐人：</label>
            <input type="text" name="recommendName" id="recommendName" value="${recommendName}"/>
            <label style="margin-left:100px">日期：</label>
            <input type="text" name="startTime" id="startTime" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>至
			<input type="text" name="endTime" id="endTime" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
     		<input type="submit" class="btn_ok" value="确 定" />
   		</div>
      	
        <table class="customer_table" style="margin-top:10px" width="100%">
			<tr>
				<td class="title">被推荐人</td>
				<td class="title">交易时间</td>
				<td class="title">交易金额</td>
				<td class="title">推荐级别</td>
				<td class="title">提成比例</td>
				<td class="title">可获提成</td>
			</tr>
            <s:iterator id="rs" value="page.result" >
           	<tr>
				<td class="center">${rs.wallet.customer.nickName}</td>
				<td class="center"><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="center">
	                <s:if test="inMoney>0">-${inMoney}</s:if>
	                <s:elseif test="outMoney>0">${outMoney}</s:elseif>
                </td>
                <c:if test="${rs.wallet.customer.superior == customer}">
                	<td class="center">一级推荐</td>
                	<td class="center">${customer.superRatio}</td>
                	<td class="center"><s:if test="inMoney>0">-${inMoney * customer.superRatio}</s:if>
	                <s:elseif test="outMoney>0">${outMoney * customer.superRatio}</s:elseif></td>
                </c:if>
	            <c:if test="${rs.wallet.customer.ssuperior == customer}">
	            	<td class="center">二级推荐</td>
	            	<td class="center">${customer.ssuperRatio}</td>
	            	<td class="center"><s:if test="inMoney>0">-${inMoney * customer.ssuperRatio}</s:if>
	                <s:elseif test="outMoney>0">${outMoney * customer.ssuperRatio}</s:elseif></td>
	            </c:if>
            </tr>
            </s:iterator>
        </table>
        <div class="page" style="margin-top:10px">
        	<p><div><jsp:include page="../util/page.jsp"></jsp:include></div></p>
           
            <p>总计 ：一级推荐提成：<span class="red">￥${totalSumMoney1}</span>元，二级推荐提成：<span class="red">￥${totalSumMoney2}</span>元，总提成：<span class="red">￥${totalSumMoney1 + totalSumMoney2}</span>元</p>
        </div>
	
   	<div class="clear"></div>
   	</div>
   	</div>
   	
   	<div id="main1" class="main">
   	<table class="customer_table" style="margin-top:10px" width="100%">
			<tr>
				<td class="title">被推荐人</td>
				<td class="title">级别</td>
			</tr>
            <s:iterator id="rs2" value="page2.result" >
           	<tr>
				<td class="center">${rs2.nickName}</td>
				
                <c:if test="${rs2.superior == customer}">
                	<td class="center">一级推荐</td>
                	
                </c:if>
                <c:if test="${rs2.ssuperior == customer}">
                	<td class="center">二级推荐</td>
                	
                </c:if>
                
            </tr>
            </s:iterator>
        </table>
        <div class="page" style="margin-top:10px">
        	<p><div><jsp:include page="../util/page4.jsp"></jsp:include></div></p>
            
        </div>
   	</div>
   	
   	<div id="main3" class="main">
     	<table class="customer_table" style="margin-top:10px" width="100%">
			<tr>
				<td class="title">推荐链接</td>
				<td class="title">拷贝</td>
			</tr>
            
           	<tr>
				<td class="center">http://www.369cai.com/?reference=${customer.id}</td>
				
               
                <td class="center"><a href="javascript:void(0)" onclick="copyclipboard()">拷贝到粘贴版</a></td>
                	
           
            </tr>
            
        </table>
        <div class="customer-beizhu">
             <div class="ticheng">
                 <div class="ticheng-img">
            	 	 <img src="../images/img/ticheng.png" alt="提成比例高">
                 </div>
                 <div class="ticheng-con">
                    <span style="font-size:13px; font:'微软雅黑'; font-weight:bold; color:red; padding-bottom:7px;">提成比例高</span><br /><br/>
                    1	普通客户	 一级推荐1%自动开通			帐户当月投注20元以上方计算佣金。<br />
                    2	网站及合作伙伴	约 4%		具体请咨询商务合作。<br />
                    3	代理商	约 4%		具体请咨询商务合作。
                 </div>
             </div>
             <div class="ticheng2">
                 <div class="ticheng-img">
            	 	 <img src="../images/img/zhongshen.png" alt="长期">
                 </div>
                 <div class="ticheng-con">
                   <span style="font-size:13px; font:'微软雅黑'; font-weight:bold; color:red;">终身从属</span><br />
                   您一旦推广好友注册成功后、发展的会员将长期为您创造收益！
                 </div>
             </div>
             <div class="ticheng2">
                 <div class="ticheng-img">
            	 	 <img src="../images/img/dollar.png" alt="长期">
                 </div>
                 <div class="ticheng-con">
                 <span style="font-size:13px; font:'微软雅黑'; font-weight:bold; color:red;">返款无下限</span><br />
                   无论您推广的用户该月消费多少，收益都将于次月10日返还到您的账户中。
                 </div>
             </div>
             <div class="ticheng2">
                 <div class="ticheng-img">
            	 	 <img src="../images/img/shake-hand.png" alt="终身">
                 </div>
                 <div class="ticheng-con">
                 <span style="font-size:13px; font:'微软雅黑'; font-weight:bold; color:red;;">二级提成制</span><br />
                   当二级客户投注总额月度超过50000元，方计算二级提成。
                 </div>
             </div>
        </div>
   	</div>
   </form>
</div>
</body>
</html>