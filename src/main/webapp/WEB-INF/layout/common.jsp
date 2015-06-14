<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../layout/resources.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
					</div>

				</div>
			</div>
		</div>
		
		
		
		


	</div>




	<footer>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</footer>
</body>
</html>


