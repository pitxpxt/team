

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%

	
	String cmd = request.getParameter("cmd");
	if (cmd==null) cmd="Index";
	
	switch(cmd)
	{
	case "Index":
%>
		<jsp:forward page="/webapp/WEB-INF/TEST_ans/index.jsp"/>
<%
		break;
	}
		%>