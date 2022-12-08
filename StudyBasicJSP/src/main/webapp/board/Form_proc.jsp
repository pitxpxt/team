<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean id="board" class="javass.Board">
    <jsp:setProperty name="board" property="*"/>
    </jsp:useBean>
    
    <jsp:useBean id="svc" class="javass.BoardService" scope="session"/>
    <jsp:setProperty name="svc" property="board" value="<%=board %>"/>


	<%
	boolean added = svc.addBoard();
	System.out.println(added);
	JSONObject jsObj = new JSONObject();
	jsObj.put("added", added);
	%>

<%=jsObj.toJSONString()%>
