<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
 	<%
		String domainType = (String)request.getSession().getAttribute("customer_domainType");
		System.out.println(domainType);
		if(domainType.equals("unknown"))
		{
	%>
		<%@include  file="indexold.jsp"%>
	<%		
		}
		else if(domainType.equals("fucai"))
		{
	%>
		<%@include  file="fucaiindex.jsp"%>
	<%		
		}
		else
		{
	%>
		<%@include  file="indexold.jsp"%>
	<%		
		}
	%>
<body>

</body>
</html>

