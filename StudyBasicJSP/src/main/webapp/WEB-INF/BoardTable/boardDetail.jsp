<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <script>
    
    function deleteBoard(num){

    	if(!confirm("정말로 삭제하시겠습니까?")) return;
    	
    	var obj = {"cmd" : "deleteBoard", "boardid" : num };
    	$.ajax({
    		
    		url: 'boardtable',
    		method : 'post',
    		cache:false,
    		data:obj,
    		dataType:'json',
    		success: function(res){
    			
    			alert(res.deleted? '삭제성공' : '삭제실패');
    			if(res.deleted) location.href='boardtable?cmd=list';
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
<title>게시판 상세보기</title>
</head>
<body>
<h3>게시글 조회</h3>
<br>

<div>글 번호 ${bvo.boardid}</div>
<div>글 제목 ${bvo.title}</div>
<div>글 내용 ${bvo.contents}</div>
<div>글쓴이 ${bvo.author}</div>
<div>조회 수 ${bvo.hitcnt}</div>
<div>등록일자 ${bvo.regdate}</div>

<div id="btns">
<button type="button" onclick="location.href='boardtable?cmd=updateBoard&boardid=${bvo.boardid}';">수정</button>
<button type="button" onclick="javascript:deleteBoard(${bvo.boardid});">삭제</button>
</div>
<br>
<a href='boardtable?cmd=list'>목록으로</a><br>
<a href='boardtable?cmd=rePost&boardid=${bvo.boardid}'>답글달기</a>
</body>
</html>