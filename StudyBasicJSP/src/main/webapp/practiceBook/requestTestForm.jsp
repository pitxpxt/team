<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="inputForm" Action="requestTestPro.jsp">
<div>학번 : <input id="sNum" type="text" name="sNum"></div>
<div>이름 : <input id="sName" type="text" name="sName"></div>
<div>학년 : </div>
<div>
<input id="sGrade" type="radio" name="sGrade" value="1">1학년<br>
<input id="sGrade" type="radio" name="sGrade" value="2">2학년<br>
<input id="sGrade" type="radio" name="sGrade" value="3">3학년<br>
<input id="sGrade" type="radio" name="sGrade" value="4">4학년<br>
</div>
<div> 선택 과목 :<div>
<div>
<input type="checkbox" id="subject" name="subject" value="java"> java
<input type="checkbox" id="subject" name="subject" value="jsp"> jsp
<input type="checkbox" id="subject" name="subject" value="html"> html
<input type="checkbox" id="subject" name="subject" value="xml"> xml
</div>
<input type="submit">

</form>
</body>
</html>