<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>修改真实姓名</title>
		<style>
			body,h1,h2,h3,h4,h5,table,tr,td,th{font-size:12px;padding:0;margin:0;font-weight:normal;}
		</style>
<script src="../../skin/01/js/jquery-1.3.2.js"></script>
<script>
function subm() {
   var pass= $("#passwords").val();
   var repass=$("#realPassword").val();
   if(pass!=repass){
      alert("密码输入不一致");
      return false;
   }
}

</script>
	</head>
	<body>
		<label>用户</label>
		<br/>
		${sysmsg }
		<br />
		<s:form action="manageEditCustomer" method="post">
		<s:hidden name="action" value="edit" />
		<s:hidden name="top" value="password" />
		<s:hidden name="id" />
		<br />
		<h1>用户名：${customer.nickName }</h1>
		<br />
		<h1>密码：<input type="text" name="passwords" id="passwords"/></h1>
		<br />
		<h1>重复密码：<input type="text" name="realPassword" id="realPassword"/></h1>
		<br/>
		<h1><input type="submit" onclick="return subm();" value="确定"/>  <a href="manageCustomer.aspx?action=view&customerId=${id }">返回</a></h1>
		<br/>
		</s:form>
	</body>
</html>