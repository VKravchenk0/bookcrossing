<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>



<div class="container">
	<div class="row">
		<div class="col-md-3">
			<img id="accountImage"
				src='/resources/img/user/${not empty user.imageRef ? user.imageRef : "user_pic.png" }' />
		</div>

		<div class="col-md-6">
			<div class="row">
				<div class="col-md-12">
					<div>
						<c:out value="${user.firstName} ${user.lastName}" />
					</div>
				</div>
				<c:if test="${owner }">
					<div class="col-md-12">
						<div>
							<button type="button" class="btn btn-primary btn-lg"
								data-toggle="modal" data-target="#myModal">Change info</button>
						</div>
					</div>
				</c:if>


				<div class="col-md-12">
					<div>
						<c:out value="Email: " />
					</div>
					<div>
						<c:out value="${user.email}" />
					</div>
				</div>
				<c:if test="${not empty user.country}">
					<div class="col-md-12">
						<div>
							<c:out value="Country: " />
						</div>
						<div>
							<c:out value="${user.country}" />
						</div>
					</div>
				</c:if>
				<c:if test="${not empty user.city}">
					<div class="col-md-12">
						<div>
							<c:out value="City: " />
						</div>
						<div>
							<c:out value="${user.city}" />
						</div>
					</div>
				</c:if>
				<c:if test="${not empty user.dateOfBirth}">
					<div class="col-md-12">
						<div>
							<c:out value="Date of birth: " />
						</div>
						<div>
							<c:out value="${user.dateOfBirth}" />
						</div>
					</div>
				</c:if>
			</div>

		</div>
	</div>




</div>


<form:form commandName="user" cssClass="form-horizontal blogForm">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">New blog</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label for="firstName" class="col-sm-2 control-label">
							First Name: </label>
						<div class="col-sm-10">
							<form:input path="firstName" cssClass="form-control" />
							<%-- <form:errors path="name" /> --%>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="lastName" class="col-sm-2 control-label"> Last
							Name: </label>
						<div class="col-sm-10">
							<form:input path="lastName" cssClass="form-control" />
							<%-- <form:errors path="name" /> --%>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label"> Country:
						</label>
						<div class="col-sm-10">
							<form:input path="country" cssClass="form-control" />
							<%-- <form:errors path="url" /> --%>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">
							Password: </label>
						<div class="col-sm-10">
							<form:password path="password" cssClass="form-control" />
							<%-- <form:errors path="url" /> --%>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>


<script>
	$(document).ready(function() {
		var width = $('#accountImage').width();
		var height = $('#accountImage').height();
		var scale = width / 200;
		$('#accountImage').width(width / scale);
		$('#accountImage').height(height / scale);
		$(window).trigger('resize');
	});
</script>
