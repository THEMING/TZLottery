<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改真实姓名</title>
<script src="../../skin/01/js/jquery-1.4.2.min.js" type=text/javascript></script>
<script src="../skin/01/js/area.js"></script>
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
		 <script language="javascript">
		 jQuery(document).ready(function(){
				setup();
			});
                          
         </script>
		
	</head>
	<body>
		<label>用户</label>
		<br/>
		${sysmsg }
		<br />
		<s:form action="manageEditCustomer" method="post">
		<s:hidden name="action" value="edit" />
		<s:hidden name="top" value="bank" />
		<s:hidden name="id" />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<h1><label>开户地址：</label>
                    <select id="province" runat="server" style="width:90px;" name="province" >
                      </select>
                       <select id="city" runat="server" style="width:100px;" name="city" >
                          </select>
                        <script language="javascript">
                          setup()
                        </script>
                                    请选择开户所在地区</h1>
		<br />
		<h1>开户银行： <select name="banks" id="banks"  onchange="checkbanks()">
                                        <s:if test="status.length()>0"><option value="${banks}" selected="selected">${banks}</option></s:if>
                                         <option value="0" >请选择...</option>
                                         <s:iterator id="rs" value="bank" >
                                         <option value="${rs}" >${rs}</option>
                                         </s:iterator>
                                   </select> 
                                    请选择开户的银行名称</h1>
		<br/>
		<h1>
		银行支行：<input type="text" name="subbranch" id="subbranch" class="text_b" />
        </h1>
        <br/>
        <h1>银行卡号：<input type="text" name="bankNumber" id="bankNumber" class="text_b"  />
                                    银行卡的号码</h1>                            
		<br/>
		<h1>确认银行卡号：<input type="text" name="rebankNumber" id="rebankNumber" class="text_b" />
        </h1>                            
		<br/>
		<h1><input type="submit" value="确定"/> <a href="manageCustomer.aspx?action=view&customerId=${id }">返回</a></h1>
		<br/>
		</s:form>
	</body>
</html>