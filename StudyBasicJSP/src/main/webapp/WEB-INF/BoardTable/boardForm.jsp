<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

$(function(){

    if(${bvo.boardid==0}){
       alert("본글 쓰기");
 
    }else{
       alert("답글 쓰기: pid=" + ${bvo.boardid});
       
       var parentid = ${bvo.boardid};
       var title = '${bvo.title}';

       $('#title').val('Re:'+title);
       $('#parentid').val(parentid);
    }
 })
 
function addForm(){
	
	if(!confirm("등록하시겠습니까?")) return;
	
	var add = $('#addform').serialize();
	alert(add);
	
$.ajax({
		
		url:'boardtable',
		method : 'post',
		cache : false,
		data : add,
		dataType: 'json',
		success:function(res){
			alert(res.added? '작성 완료' : '등록 실패');
			//if(res.added) location.href='boardtable?cmd=list';
			if(res.added) location.href='boardtable?cmd=list';
		},
		error:function(xhr,status,err){
			alert("에러:" + err);
		}
	});
	
}

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 쓰기 </title>
</head>
<body>

<form id="addform" >

<h3>게시글 작성</h3>
<input type="hidden" name="cmd" id="cmd" value="added">
<input type="hidden" name="parentid" id="parentid">
<div>제목<input type="text" name="title" id="title"></div>
<div>쓴이<input type="text" name="author" id="author"></div>
<div>내용<textarea cols="30" rows="10" name="contents" id="contents"></textarea></div>

<button type="button" onclick="javascript:addForm();">등록</button>
<button type="reset">재작성</button>
</form>
</body>
</html>