<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/styles/common.css"
	type="text/css">
<link rel="stylesheet" href="/resources/styles/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="/resources/styles/journal.bootstrap.min.css" type="text/css">
<link href="/resources/styles/simple-sidebar.css" rel="stylesheet">
<script src="/resources/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<tilesx:useAttribute name="current" />
	<div id="wrap">
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>

		<div id="footer">
			<div class="container centered">
				<tiles:insertAttribute name="footer"></tiles:insertAttribute>
			</div>
		</div>
	</div>

</body>
</html>