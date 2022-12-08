<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String sNum =request.getParameter("sNum");
    	String sName =request.getParameter("sName");
    	String sGrade =request.getParameter("sGrade");
    	String subject =request.getParameter("subject");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>학생 정보</h3>

<td><th>학번</th></td><td><th>이름</th></td><td><th>학년</th></td><td><th>선택 과목</th></td>
<br>
<td><%=sNum %></td>
<td><%=sName %></td>
<td><%=sGrade %></td>
<td><%=subject %></td>

</body>
</html>