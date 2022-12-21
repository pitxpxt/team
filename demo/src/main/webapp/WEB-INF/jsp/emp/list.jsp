<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 목록 보기 </title>
</head>
<body>
<%@include file='/WEB-INF/jsp/emp/user_inc.jsp'%>
<h3> 사원 목록</h3>
<c:forEach var="line" items="${list}">
	${line.empno} &nbsp; 
	<a href="/test/detail/${line.empno}">${line.ename}</a> &nbsp;
	${line.deptno}&nbsp;
	${line.sal}&nbsp;
	${line.hiredate}&nbsp;
	<br>
</c:forEach>
<br>
<hr>
<br>
<div>
<form action="/test/find" method="post">
<input type="text" name="ename">
<button type="submit">이름으로 검색</button> &nbsp;
<button type="button" onclick="location.href='/test/add'">사원 추가</button>
</form>
</div>
</body>
</html>