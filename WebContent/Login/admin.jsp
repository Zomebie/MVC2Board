<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Dto" %>
<%@ page import="java.util.Iterator" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>


<h1 align="center">가입자 목록</h1>




<%
	request.setCharacterEncoding("utf-8");
	ArrayList<Dto> list = (ArrayList<Dto>)request.getAttribute("list");
	Iterator iter = list.iterator();
	
	String str = "yaho";
	String name = null;
	while (iter.hasNext()) {
	   Dto dto = null;
	   dto = (Dto)iter.next();
	   String username = dto.getName();
	   %>  
	  
	   
	   
	   
	   
	   <a href="info.do?yaho=<%=str %>&name=<%=username%>">
	   <%out.println(username);%></a>
	   
	   
	   
	   <a href="delete.do?name=<%=username%>">삭제</a>
	   
	   
	   </br>
	   
	   <%}%>
	
	


</body>
</html>