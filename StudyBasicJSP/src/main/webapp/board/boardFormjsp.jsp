
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

function formcheck()
{
	var name = $('#name').val();
	var passward = $('#passward').val();
	var title = $('#title').val();
	var email = $('#email').val();
	var contents = $('#contents').val();

	
	if(name=='' || passward=='' || title=='' || email=='' || contents=='')
		{
		alert('모든 값을 입력하세요');
		return false;
		}
	return send();
}

function send()
{
	
	
	$.ajax({
		url : 'Form_proc.jsp',
		method : 'post',
		data : $('#board').serialize(),
		cache : false,
		dataType : 'json',
		success : function(res)
		{
			alert(res.added? '작성성공' : '작성실패');
		},
		err : function(xhr,status,err){
			alert('에러:' + err);
		}

	});
	return false;
}
</script>
</head>
<body>

<form id='board' onsubmit='return formcheck();'>

	<div>작성자 <input id='name' type='text' name='name' value='seul'></div>
	<div>비밀번호<input id='passward' type='text' name='passward' value='hsg5292'></div>
	<div>제목 <input id='title' type='text' name='title'></div>
	<div>이메일<input id='email' type='text' name='email' value='soundkkm@naver.com'></div>
	<div>글 내용<textarea id='contents' name='contents' rows="10" cols="50" placeholder="입력해주세요."></textarea></div>
	
	<div><button type='submit'>저장</button></div>
</form>
</body>
</html>