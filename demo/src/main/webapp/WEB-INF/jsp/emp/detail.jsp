<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>상세보기 </title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script type="text/javascript">


function deleteMem(empno)
{
	if(!confirm ("정말로 삭제하시겠습니까?")) return;
	alert('empno' + empno);
	var obj = {"empno" : empno};
	$.ajax({
		
		url:'/user/delete',
		method : 'post',
		data : obj,
		cache : false ,
		dataType : 'json',
		success : 
		function(res)
		{
			alert(res.deleted? '삭제완료':'삭제 실패');
			if(res.deleted)
				{
				location.href='/test/list';
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

<h3>사원 상세보기</h3> <br>
<c:forEach var="row" items="${list}">
사원번호 ${row.empno}<br>
사원이름 ${row.ename}<br>
부서번호 ${row.deptno}<br>
사원봉급 ${row.sal}<br>
입사일자 ${row.hiredate}<br>
</c:forEach>
<br>
	<br>
	<hr>
	<br>
	<div>
<button onclick="javascript:deleteMem(${emp.empno});">삭제</button> &nbsp;
<button type="button" onclick="location.href='/test/detailEdit/${emp.empno}'">수정</button> &nbsp;
	<button type="button" onclick="location.href='/test/list'">목록</button>
	</div>
</body>
</html>