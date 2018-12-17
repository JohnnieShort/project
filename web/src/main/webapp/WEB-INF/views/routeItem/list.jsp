<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/routeItem" />
<h4 class="header"><mytaglib:i18n key="page.content.routeItems" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id"><mytaglib:i18n key="page.content.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_from"><mytaglib:i18n key="page.content.stationFrom" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="station_to"><mytaglib:i18n key="page.content.stationTo" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_id"><mytaglib:i18n key="page.content.passengerRoute" /></mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="departure"><mytaglib:i18n key="page.content.departure" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="arrival"><mytaglib:i18n key="page.content.arrival" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="ordinal_num"><mytaglib:i18n key="page.content.ordinal" /></mytaglib:sort-link></th>
		
			
			
			
			<th></th>
		</tr>
		<c:forEach var="routeItem" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${routeItem.id}" /></td>
				<td><c:out value="${routeItem.stationFromName}" /></td>
				<td><c:out value="${routeItem.stationToName}" /></td>
				<td><c:out value="${routeItem.passengerRouteId}" /></td>
				
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${routeItem.departureDate}"/> 
							<fmt:formatDate pattern="hh-mm" value="${routeItem.departureTime}"/></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${routeItem.arrivalDate}"/> 
							<fmt:formatDate pattern="hh-mm" value="${routeItem.arrivalTime}"/></td>
				<td><c:out value="${routeItem.ordinalNum}" /></td>
			
				
				
				
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${routeItem.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${routeItem.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${routeItem.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />