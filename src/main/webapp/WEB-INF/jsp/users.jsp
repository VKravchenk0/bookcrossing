<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty users }">
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

	</c:when>
	<c:otherwise>
		<div class="alert alert-warning" role="alert">There are no such
			page</div>
	</c:otherwise>
</c:choose>
<nav>
	<ul class="pagination">
		<li class="${page == 0 ? 'disabled' : ''}"><a
			href="/users?page=${page - 1}" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>

		<c:forEach items="${paginationList}" var="pageLink">
			<li class="${pageLink == page ? 'active' : ''}"><a
				href="/users?page=${pageLink}">${pageLink + 1}</a></li>
		</c:forEach>

		<li><a href="/users?page=${page + 1}" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a></li>

	</ul>
</nav>

