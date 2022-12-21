<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 찾기 </title>
</head>
<body>

<h3> 사원 찾기</h3>
<c:forEach var="line" items="${list}">
	${line.empno} &nbsp; 
	${line.ename} &nbsp;
	${line.deptno}&nbsp;
	${line.sal}&nbsp;
	${line.hiredate}&nbsp;
	<br>
	</c:forEach>

<br>
<hr>
<br>
<div>
<button type="button" onclick="location.href='/test/list'">목록</button>
</div>
</body>
</html>