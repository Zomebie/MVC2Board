<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	

<%
request.setCharacterEncoding("utf-8");
if(session.getAttribute("username")==null){
	RequestDispatcher dis = request.getRequestDispatcher("./login.do");
	dis.forward(request, response);
}
else{
	out.println(session.getAttribute("username")+"님 환영합니다.<br><br>"); 
}
%>

<%

out.println("<a href="+"./BoardList.bo"+">게시판으로 이동</a> <br><br>");


%>

<%
	String username = (String)session.getAttribute("username");

	if(username.equals("admin")){
		out.println("<a href="+"list.do"+">관리자 페이지로 이동</a><br><br>");
		
	}
		
	
	


%>
<input type="submit" value="logout" onclick="location.href='./logout.do'" />

</body>
</html>