<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

function formcheck(){
	
	var name = $('#name').val();
	var birth = $('#birth').val();
	var email = $('#email').val();
	var careerT = $('#careerT').val();
	var introduce = $('#introduce').val();
	var gender = '';
	var subject ='';
	
	$('input[name=gender]').each(function(index, item){
		if(item.checked){
			gender=$(item).val();
		}
	});
	
	$('input[name=subject]').each(function(index, item){
		if(item.checked){
			subject=$(item).val();
		}
	});
	
	if(name==''||birth==''||email==''||careerT==''||introduce==''||gender==''||subject=='')
		{
			alert('모든 값을 입력하여야 합니다.');
			return false;
		}
		send();
	
	
	
}
function send()
{
	var add = $('addMem').serialize();
	
	$.ajax({
		
		url: 'join_proc.jsp',
		method : 'post',
		data : add,
		cache : false,
		dataType : 'json',
		success :function(res)
		{
			alert(res.added? '가입 성공':'가잆 실패');
		},
		error : function(xhr,status,err){
			alert('에러:' + err);
		}
		
	});
	}
</script>
</head>
<body>

<form id="addMem" onsubmit="formcheck();">
	<div>이름<input id="name" type="text" name="name"></div>
	<div>
	남자 <input type="radio" name="gender" value="male">
	여자 <input type="radio" name="gender" value="female">
	</div>
	<div>생일 <input id="birth" type="date" name="birth"></div>
	<div>메일 <input id="email" type="text" name="email"></div>
	<div>경력 <input id="careerT" type="number" name="careerT" min="1" max="100" step="1"></div>
	<div>관심과목
	<input type="checkbox" name="subject" value="java"> JAVA
	<input type="checkbox" name="subject" value="jsp"> JSP
	<input type="checkbox" name="subject" value="html"> HTML
	<input type="checkbox" name="subject" value="python"> PYTHON
	<input type="checkbox" name="subject" value="sql"> SQL
	</div>
	<div><textarea id="introduce" rows="5" cols="20" placeholder="입력해주세요"></textarea></div>
	
	<div><button type="submit">가 입</button></div>
</form>
</body>
</html>