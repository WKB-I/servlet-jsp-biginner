<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
//getUserInfoInSessionScope
User loginUser = (User) session.getAttribute("loginUser");
//getTweetlistRegistedInApplicationscope
List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
//getTweetlistSavedInApplicationscope
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<!-- <%= loginUser.getName() %>さん、ログイン中 -->
<c:out value="${loginUser.name}"/>さん、ログイン中
<a href="/docoTsubu/Logout">ログアウト</a>
</p>
<p><a href="/docoTsubu/Main">更新</a></p>
<form action="/docoTsubu/Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<!-- <%if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %> -->
<c:if test="${not empty errorMsg }">
	<p>${errorMsg}</p>
</c:if>
<c:forEach var="mutter" items="${mutterList}">
	<p><c:out value="${mutter.userName}"/>:<c:out value="${mutter.text}"/>:</p>
</c:forEach>
<!-- <% for(Mutter mutter:mutterList) {%>
	<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
<%} %> -->
</body>
</html>