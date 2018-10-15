<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/locomotive" />
<h4 class="header">Locomotives</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>power</th>
			
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="locomotive" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${locomotive.id}" /></td>
				<td><c:out value="${locomotive.name}" /></td>
				<td><c:out value="${locomotive.power}" /></td>
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${locomotive.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${locomotive.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${locomotive.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${locomotive.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${locomotive.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
