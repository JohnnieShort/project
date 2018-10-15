<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/passengerRoute" />
<h4 class="header">Passenger Routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
		<%--<th>station from</th>--%>
		<%--<th>station to</th>--%>
			<th>departure</th>
			<th>arrival</th>
			<th>passenger route type</th>
		<%--<th>train id</th>--%>
			<th>is actual</th>
			<th>frequency</th>
			<th>places</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="passengerRoute" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${passengerRoute.id}" /></td>
			<%--<td><c:out value="${passengerRoutefrom.id}" /></td>--%>
			<%--<td><c:out value="${passengerRoute.to.id}" /></td>--%>
				<td><c:out value="${passengerRoute.departure}" /></td>
				<td><c:out value="${passengerRoute.arrival}" /></td>
				<td><c:out value="${passengerRoute.passengerRouteType}" /></td>
			<%--<td><c:out value="${passengerRoute.train.id}" /></td>--%>
				<td><c:out value="${passengerRoute.isActual}" /></td>
			
				<td><c:out value="${passengerRoute.frequency}" /></td>
				<td><c:out value="${passengerRoute.places}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passengerRoute.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passengerRoute.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${passengerRoute.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${passengerRoute.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${passengerRoute.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
