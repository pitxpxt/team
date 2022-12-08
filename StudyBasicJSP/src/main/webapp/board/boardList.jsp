<%@page import="javass.Board"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:useBean id="svc" class="javass.BoardService" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
<table>
<tr><th>num</th><th>name</th><th>email</th><th>title</th>

<%
	List<Board> list = svc.getList();

%>
</table>
</body>
</html>