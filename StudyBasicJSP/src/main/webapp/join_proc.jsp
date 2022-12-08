<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:useBean id="mem" class="javass.Member">
    <jsp:setProperty name="mem" property="*"/></jsp:useBean>
    
    <jsp:useBean id="svc" class="javass.MemberService">
    <jsp:setProperty name="svc" property="Member" value="<%=mem%>"/></jsp:useBean>
    
    <%
    
    boolean added = svc.addMember();
    JSONObject jsObj = new JSONObject();
    jsObj.put("added",added);
    %>
    
    <%=jsObj.toJSONString()%>