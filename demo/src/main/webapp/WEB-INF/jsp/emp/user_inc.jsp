<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 상태</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function logoutMem()
{
	$.ajax({
		
		url : '/user/logout',
		method : 'post',
		data : {"id" : "${sessionScope.id}"},
		cache : false,
		dataType : 'json',
		success : function(res){
			alert(res.logout? '로그아웃 성공' : '로그아웃 실패');
			location.href='/test/list';
		},
		error : function(xhr, status, err){
			alert('에러:' + err);}
	});
	return false;
}


</script>
</head>
<body>
<span id="logoutbtn">
${sessionScope.id}
	<a id='Logout_Link' href='javascript:logoutMem();'>로그아웃</a>
</span>
</body>
</html>