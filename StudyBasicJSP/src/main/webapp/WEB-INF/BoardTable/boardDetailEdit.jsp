<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>

function editDetail()
{	
	
	var obj =$('#editDetail').serialize();
	
	$.ajax({
		url:'boardtable',
		method:'post',
		cache:false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.updated? '수정완료' : '수정실패');
			if(res.updated) location.href='boardtable?cmd=boardDetail&boardid=${bvo.boardid}';
		},
		error:function(xhr,status,err){
 			alert("에러:" + err);
 		}
	});
	return false;
}

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 수정</title>
</head>
<body>
<h3>게시글 수정</h3>

<form id="editDetail" onsubmit="return editDetail();">
<input type="hidden" id="cmd" name="cmd" value="updated">
<div>글 번호 <input type="hidden" id="boardid" name="boardid" value="${bvo.boardid}">${bvo.boardid}</div>
<div>글 제목 <input type="text" id="title" name="title" value="${bvo.title}"></div>
<div>글 내용 <textarea cols="30" rows="10" id="contents" name="contents">${bvo.contents}</textarea></div>
<div>글쓴이 ${bvo.author}</div>
<div>조회 수 ${bvo.hitcnt}</div>
<div>등록일자 ${bvo.regdate}</div>

<button type="submit">수정완료</button>
<button type="reset">수정취소</button>

</form>
<br>
<a href='boardtable?cmd=list'>목록으로</a>
</body>
</html>