<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String login= (String) session.getAttribute("login");
    %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>로그인 폼</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

function loginC()
{
	var login = $('#logincheck').serialize();
	
	$.ajax({
		url : '/user/logon',
		method : 'post',
		data : login,
		cache : false, 
		dataType : 'json' , 
		success : function(res)
		{
			if(res.login)
				{
				alert('로그인 성공');
				location.href="/test/list";
				}else{
					alert('로그인 실패');
				}
		},error : function(xhr,status,err){
			//xhr : XMLHttpRequest(비동기요청을 위한 객체)
			//status : 응답 상태코드(200:정상, 500:실행오류, 400:url 요청 오류)
			alert(err);
		}
		
	});
}

function formCheck()
{
	var id =$('input[name=id]').val();
	var pw = $('input[name=pw]').val();
	
	if(id==''||pw=='')
		{
		alert('모든 항목을 입력해주세요!');
		return false;
		}
	alert('로그인 체크 중입니다...');
	loginC();
	 return false;
}
	
</script>
<body>
	<h3>로그인 하기</h3>
	
	<form id="logincheck" name="logincheck" onsubmit="return formCheck();">
	id <input type="text" name="id"> <br>
	pw <input type="password" name="pw"> <br>
	
	<button type="submit">login</button>
	</form>

</body>
</html>