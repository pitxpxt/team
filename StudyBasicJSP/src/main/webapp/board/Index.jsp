<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<table align="center" border=1 width="50%" height="300">
<tr><td align="center"><%@include file='/board/Top.jsp'%></td></tr>
<tr>
<td>
<section>
<h3> Today's pitxpxt</h3>
<form>

<div><input type='checkbox' id='chk' name='chk'> 글 내용 </div>

</form>
</section>
</td>
</tr>
<tr><td align="right"><%@include file='/board/Bottom.jsp'%></td></tr>
</table>
</body>
</html>