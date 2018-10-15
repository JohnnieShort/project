<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/passenger" />
<h4 class="header">Passengers</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
		<%--<th>user account id</th>--%>
			
			
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="passenger" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${passenger.id}" /></td>
			<%--<td><c:out value="${passenger.userAccount.id}" /></td>--%>
				
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passenger.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passenger.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${passenger.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${passenger.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${passenger.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
