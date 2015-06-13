<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<script>
	var cityName = "${user.city.name}";
</script>
<script src="/resources/js/select2/countryList.js"></script>
<script src="/resources/js/select2/select2.js"></script>

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
				<c:if test="${not empty user.city.country}">
					<div class="col-md-12">
						<div>
							<c:out value="Country: " />
						</div>
						<div>
							<c:out value="${user.city.country.name}" />
						</div>
					</div>
				</c:if>
				<c:if test="${not empty user.city}">
					<div class="col-md-12">
						<div>
							<c:out value="City: " />
						</div>
						<div>
							<c:out value="${user.city.name}" />
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


<form:form commandName="user" cssClass="form-horizontal userEditForm">
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit info</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label for="firstName" class="col-sm-2 control-label">
							First Name: </label>
						<div class="col-sm-10">
							<form:input path="firstName" cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="lastName" class="col-sm-2 control-label"> Last
							Name: </label>
						<div class="col-sm-10">
							<form:input path="lastName" cssClass="form-control" />
						</div>
					</div>
				</div>
				
				<div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label"> Country name:
						</label>
						<div class="col-sm-10">
							<select class="country-select" style="width: 100%">
								<option></option>
							</select>
						</div>
					</div>
				</div>
				<form:hidden path="country.id" id="country-hidden"/>
				
				<div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label"> City name:
						</label>
						<div class="col-sm-10">
							<select class="city-select" style="width: 100%">
								<option></option>
							</select>
						</div>
					</div>
				</div>
				<form:hidden path="city.id" id="city-hidden"/>
							
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
				
			</div>
			<div class="somediv">	
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
