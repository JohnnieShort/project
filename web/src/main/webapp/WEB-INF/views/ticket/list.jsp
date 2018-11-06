<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/ticket" />
<h4 class="header">Tickets</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_id">passenger id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_id">passenger route id</mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from">station from</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to">station to</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="price">price</mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="ticket" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${ticket.id}" /></td>
				<td><c:out value="${ticket.passenger.id}" /></td>
				<td><c:out value="${ticket.passengerRoute.id}" /></td>
			
				<td><c:out value="${ticket.stationFrom.id}" /></td>
				<td><c:out value="${ticket.StationTo.id}" /></td>
				<td><c:out value="${ticket.price}" /></td>
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${ticket.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${ticket.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${ticket.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${ticket.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red "
					href="${baseUrl}/${ticket.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
