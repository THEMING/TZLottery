<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    int n=((List<String>)request.getAttribute("list")).size();
    List<String> list2=(List<String>)request.getAttribute("list");
    for(int i=0;i<n;i++)
    {
    if(i!=0&&(i+1)%4==0)
    {
     out.write(list2.get(i)+"\r\n");
    }
    else{
      out.write(list2.get(i)+"       ");
      }
    }
     %>
  <%
  response.setContentType("application/msword;charset=utf-8");
  response.setHeader("content-disposition","attachment;filename=369cai.txt");
  %> 
 
