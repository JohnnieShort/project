<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<c:set var="baseUrl" value="${contextPath}/routeItem" />
<h4 class="header">Items of Routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from">station from</mytaglib:sort-link></th>--%>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to">station to</mytaglib:sort-link></th>--%>
		<%--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_id">passenger route id</mytaglib:sort-link></th>--%>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="arrival">arrival</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="departure">departure</mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="ordinal_num">ordinal number</mytaglib:sort-link></th>
		
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="is_first">is first</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="is_last">is last</mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="routeItem" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${routeItem.id}" /></td>
			<%--<td><c:out value="${routeItem.stationFrom.id}" /></td>--%>
			<%--<td><c:out value="${routeItem.stationTo.id}" /></td>--%>
			<%--<td><c:out value="${routeItem.passengerRoute.id}" /></td>--%>
				<td><c:out value="${routeItem.arrival}" /></td>
				<td><c:out value="${routeItem.departure}" /></td>
				
				<td><c:out value="${routeItem.ordinalNum}" /></td>
			
				<td><c:out value="${routeItem.isFirst}" /></td>
			
				<td><c:out value="${routeItem.isLast}" /></td>
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${routeItem.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${routeItem.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${routeItem.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${routeItem.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red "
					href="${baseUrl}/${routeItem.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
