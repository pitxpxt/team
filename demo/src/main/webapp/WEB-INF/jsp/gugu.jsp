<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>구구단 보기</title>
</head>
<body>

<c:forEach var="line" items="${list}">
	${line}<br>
</c:forEach>

</body>
</html>