<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${contextPath}/passengerRoute" />
<h4 class="header">Passenger Routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from">station from</mytaglib:sort-link></th>--%>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to">station to</mytaglib:sort-link></th>--%>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="departure">departure</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="arrival">arrival</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_type">passenger route type</mytaglib:sort-link></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="train_id">train id</mytaglib:sort-link></th>--%>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="is_actual">is actual</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="frequency">frequency</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="places">places</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="passengerRoute" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${passengerRoute.id}" /></td>
			<%--<td><c:out value="${passengerRoutefrom.id}" /></td>--%>
			<%--<td><c:out value="${passengerRoute.to.id}" /></td>--%>
				<td><c:out value="${passengerRoute.departure}" /></td>
				<td><c:out value="${passengerRoute.arrival}" /></td>
			<td><c:out value="${passengerRoute.passengerRoutetype}" /></td>
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
					class="btn-floating red "
					href="${baseUrl}/${passengerRoute.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
