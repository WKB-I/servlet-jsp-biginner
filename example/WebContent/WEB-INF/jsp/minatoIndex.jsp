<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SiteEV" %>
<%
SiteEV siteEV = (SiteEV) application.getAttribute("siteEV");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>湊君のページ</title>
</head>
<body>
<h1>湊くんのページへようこそ</h1>
<p>
<a href="/example/MinatoIndex?action=like">よいね</a>:
<%= siteEV.getLike() %>人
<a href="/example/MinatoIndex?action=dislike">よくないね</a>:
<%= siteEV.getDisLike() %>人
</p>
<h2>湊くんとは！？</h2>
<p>・・・</p>
</body>
</html>