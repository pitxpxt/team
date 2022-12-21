<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>emp form </title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
 <script>

    function saveList(){
    	
    	var forminfo= $('#empinfo').serialize();
    	$.ajax({
    	
    		url : '/user/add',
    		method : 'post',
    		data : forminfo,
    		cache : false,
    		dataType : 'json',
    		success : function(res)
    		{
    			alert(res.added? '저장 완료':'저장 실패');
    			if(res.added)
    				{
    				location.href='/test/list';
    				}else{
    					alert(res.msg);
    					location.href= res.url;
    				}
    		},
			error : function(xhr,status,err){
				alert(err);
			}
    	});
	return false;
    } 
    </script>
</head>
<body>
<main>
	<form id="empinfo" onsubmit="return saveList();">
	<div>
		사원번호 <input type="number" id="empno" name="empno" value="1">
	</div>
	<div>
		사원이름 <input type="text" id="ename" name="ename" value="김효진">
	</div>
	<div>
		부서번호 <input type="number" id="deptno" name="deptno" value="1">
	</div>
	<div>
		급여 <input type="number" id="sal" name="sal" value="1800000">
	</div>
	<div>
		입사일 <input type="date" id="hiredate" name="hiredate">
	</div>
	<br>
	<hr>
	<br>
	<div>
	<button type="submit" >저장</button> &nbsp;
	<button type="button" onclick="location.href='/test/list'">취소/목록가기</button>
	</div>
	</form>

</main>
</body>
</html>