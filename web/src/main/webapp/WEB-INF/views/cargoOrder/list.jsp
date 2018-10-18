<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Cargo Orders</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="cargo_route_id">cargo route id</mytaglib:sort-link></th>--%>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="customer_id">customer id</mytaglib:sort-link>--%></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="cargo_type">cargo type</mytaglib:sort-link></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from">station from</mytaglib:sort-link>--%></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to">station to</mytaglib:sort-link>--%></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="weight">weight</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="cargoOrder" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${cargoOrder.id}" /></td>
			<%--<td><c:out value="${cargoOrder.cargoRoute.id}" /></td>--%>
			<%--<td><c:out value="${cargoOrder.customer.id}" /></td>--%>
				<td><c:out value="${cargoOrder.cargoType}" /></td>
			<%--<td><c:out value="${cargoOrder.stationFrom.id}" /></td>--%>
			<%--<td><c:out value="${cargoOrder.StationTo.id}" /></td>--%>
				<td><c:out value="${cargoOrder.date}" /></td>
				<td><c:out value="${cargoOrder.weight}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoOrder.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoOrder.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${cargoOrder.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${cargoOrder.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red "
					href="${baseUrl}/${cargoOrder.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
