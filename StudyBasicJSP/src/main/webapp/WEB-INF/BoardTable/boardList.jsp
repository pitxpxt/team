<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 보기</title>
</head>
<body>


<table>
<tr><td>번호</td><td>제목</td><td>쓴이</td><td>조회</td><td>등록일</td></tr>
<c:forEach var="brd" items="${list}">
	

<tr>
<td>${brd.boardid} </td>
<td><a href="boardtable?cmd=boardDetail&boardid=${brd.boardid}">${brd.title}</a> </td>
<td>${brd.author} </td>
<td>${brd.hitcnt} </td>
<td>${brd.regdate} </td>
</tr>

<c:forEach var="n" begin="1" end="${ttlpages}">

<a href="?cmd=list&pageNum=${n}">${n}</a>
</c:forEach>


</c:forEach>
</table>
<button type="button" onclick="location.href='boardtable?cmd=addBoard';">등록</button> &nbsp;
<button type="button">선택삭제</button>

</body>
</html>