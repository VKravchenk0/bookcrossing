<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">-->
<link rel="stylesheet" href="/resources/styles/bootstrap.min.css" type="text/css">

<!-- Optional theme 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">-->
<link rel="stylesheet"
	href="/resources/styles/journal.bootstrap.min.css"
	type="text/css">
	
<!-- Latest compiled and minified JavaScript 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>-->
<script src="/resources/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<tiles:insertAttribute name="body" />
	<br />
	<center>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</center>
</body>
</html>