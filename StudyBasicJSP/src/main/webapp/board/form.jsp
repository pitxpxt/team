<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 회원등록</title>
</head>
<style>

body {font-family: '굴림체';}
#s{
background-color: lightgray;
height:520px;
width:100%;
padding-left: 30px;
padding-top: 30px;
}
</style>
<body>
<div id="s">
<center>
<h2>쇼핑몰 회원등록</h2>             
<table border=1 width=50% height=300>

<tr><td align=center width=150>회원번호(자동발생)</td><td width=250> <input type="text" name="custno" value="100007" size=10></td></tr>
<tr> <td align=center>회원성명</td><td> <input type="text" name="custname" size=10 ></td></tr>
<tr><td align=center>회원전화</td><td> <input type="text" name="phone" size=15></td></tr>
<tr><td align=center>회원주소 </td><td><input type="text" name="address" size=30></td></tr>
<tr><td align=center>가입일자 </td><td><input type="text" name="joindate" value="20220303" size=10></td></tr>
<tr><td align=center>고객등급[A:VIP, B:일반 ,C:직원]</td> <td><input type="text" name="grade" size=10></td></tr>
<tr><td align=center>도시코드</td><td> <input type="text" name="city" size=10 ></td></tr>
<tr><td colspan=2 align=center ><input type="submit" value="등록">  <input type="button" value="조회"></td></tr>
</table>
</center>
</div>
</body>
</html>