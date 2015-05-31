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
<link href="/resources/styles/common.css" rel="stylesheet">
<script
	src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>


<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<script
	src=""></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>

	<tilesx:useAttribute name="current" />
	<div id="wrapper">
		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="/">Bookcrossing</a></li>
				<li><a href="/">Home</a></li>
				<security:authorize access="isAuthenticated()">
					<li><a href='<spring:url value="/account" />'>Account</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="/users">Users</a></li>
				</security:authorize>
				<security:authorize access="! isAuthenticated()">
					<li><a href="/login">Login</a></li>
				</security:authorize>
				<security:authorize access="! isAuthenticated()">
					<li><a href="/register">Register</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li><a href='<spring:url value="/logout" />'>Log out</a></li>
				</security:authorize>



			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<tiles:insertAttribute name="body" />
						<!-- <div class="btn btn-default" id="menu-toggle">Toggle Menu</div> -->
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Menu Toggle Script -->
	<!-- <script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
	<br /> -->
	<footer>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</footer>
</body>
</html>


