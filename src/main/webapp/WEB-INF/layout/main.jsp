<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

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

	<tilesx:useAttribute name="current"/>

	<div id="wrapper">
		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="/">Bookcrossing</a></li>
				<li><a href="/users">Users</a></li>
				<li><a href='<spring:url value="/logout" />'>Log out</a></li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<tiles:insertAttribute name="body" />
						<div class="btn btn-default" id="menu-toggle">Toggle Menu</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

	<br />
	<center>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</center>
</body>
</html>