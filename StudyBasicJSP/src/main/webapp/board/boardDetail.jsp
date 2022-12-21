<%@page import="javass.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <jsp:useBean id="svc" class="javass.BoardService" scope="session"/>
      <% Board b = svc.getBoard(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 보기</title>
</head>
<body>

<h3> 이용자 상세 보기</h3>

<div>번호 <%=b.getNum() %></div>
</body>
</html>