<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty users }">
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User id</th>
			<th>User name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.name}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>