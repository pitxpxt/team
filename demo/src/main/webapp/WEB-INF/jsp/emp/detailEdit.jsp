<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

function updateMem()
{
	var serData = $('#updateForm').serialize();
	$.ajax({
		url : '/user/detailEdit',
		method : 'post',
		data : serData ,
		dataType : 'json',
		success : function(res) //응답을 res 변수로 받겠다.  //{"uplist" : true}
		{
			alert(res.updated? '수정 성공' : '수정 실패');
			if(res.updated){
				location.href='/test/detail/${emp.empno}'; 
			}else{
				alert(res.msg);
				location.href=res.url;
			}
		},
		error : function(xhr,status,err){
			alert(err);
		}
	});
	}
</script>
<html>
<head>
<meta charset="utf-8">
<title>상세보기 </title>
</head>
<body>

<h3>사원 정보 수정</h3> <br>

<form id="updateForm">
<input type="hidden" id="empno" name="empno" value="${emp.empno}">
사원번호 ${emp.empno}<br>
사원이름 ${emp.ename}<br>
부서번호 ${emp.deptno}<br>
사원봉급 <input type="number" id="sal" name="sal" value="${emp.sal}"><br>
입사일자 ${emp.hiredate}<br>
<br>

<button type="button" onclick="javascript:updateMem();">적용</button> &nbsp;
<button type="reset">취소</button>
</form>
</body>
</html>