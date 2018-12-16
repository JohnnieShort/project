<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Cargo Orders</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
		<th><mytaglib:sort-link pageUrl="${baseUrl}" column="cargo_route_id">cargo route id</mytaglib:sort-link></th>
		<th><mytaglib:sort-link pageUrl="${baseUrl}" column="customer_id">customer id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="cargo_type">cargo type</mytaglib:sort-link></th>
		<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from_id">station from</mytaglib:sort-link></th>
		<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to_id">station to</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="weight">weight</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="price">price</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="cargoOrder" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${cargoOrder.id}" /></td>
			<td><c:out value="${cargoOrder.cargoRouteId}" /></td>
			<td><c:out value="${cargoOrder.customerId}" /></td>
				<td><c:out value="${cargoOrder.cargoType}" /></td>
			<td><c:out value="${cargoOrder.stationFromId}" /></td>
			<td><c:out value="${cargoOrder.StationToId}" /></td>
				<td><c:out value="${cargoOrder.date}" /></td>
				<td><c:out value="${cargoOrder.weight}" /></td>
				<td><c:out value="${cargoOrder.price}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoOrder.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoOrder.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${cargoOrder.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${cargoOrder.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${cargoOrder.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />