<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/resources/styles/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="/resources/styles/journal.bootstrap.min.css" type="text/css">
<link href="/resources/styles/simple-sidebar.css" rel="stylesheet">
<script src="/resources/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>

	<tilesx:useAttribute name="current" />

	<tiles:insertAttribute name="body" />

	<br />
	<center>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</center>
</body>
</html>