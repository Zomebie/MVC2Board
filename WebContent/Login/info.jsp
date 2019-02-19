<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dto.Dto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
		Dto dto = (Dto)request.getAttribute("dto");

		out.println(dto.getId()+"</br>");
		out.println(dto.getPw()+"</br>");
		out.println(dto.getEmail()+"</br>");
		out.println(dto.getName()+"</br>");
		out.println(dto.getAddress()+"</br>");
		out.println(dto.getRrd()+"</br>");



		
%>

</body>
</html>