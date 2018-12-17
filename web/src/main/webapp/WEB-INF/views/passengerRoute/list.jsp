<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/passengerRoute" />
<h4 class="header"><mytaglib:i18n key="page.content.passengerRoutes" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id"><mytaglib:i18n key="page.content.id" /></mytaglib:sort-link></th>
			<th><mytaglib:i18n key="page.content.stationFrom" /></th>
			<th><mytaglib:i18n key="page.content.stationTo" /></th>
			<th><mytaglib:i18n key="page.content.departure" /></th>
			<th><mytaglib:i18n key="page.content.arrival" /></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_type"><mytaglib:i18n key="page.content.passRouteType" /></mytaglib:sort-link></th>
			<!--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="train_id"><mytaglib:i18n key="page.content.train" /></mytaglib:sort-link></th>-->
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="is_actual"><mytaglib:i18n key="page.content.actual" /></mytaglib:sort-link></th>
			<!--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="frequency"><mytaglib:i18n key="page.content.frequency" /></mytaglib:sort-link></th>-->
			<!--<th><mytaglib:sort-link pageUrl="${baseUrl}" column="places"><mytaglib:i18n key="page.content.places" /></mytaglib:sort-link></th>-->
			
			<th></th>
		</tr>
		<c:forEach var="passengerRoute" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${passengerRoute.id}" /></td>
				<td><c:forEach var="entry" items="${firstStations}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
						</c:forEach></td> <!--"${passengerRoutefrom.id}"-->
				<td><c:forEach var="entry" items="${lastStations}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
						</c:forEach></td> 
				<td><c:forEach var="entry" items="${departures}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<fmt:formatDate pattern="yyyy-MM-dd hh-mm" value="${entry.value}" />
     						</c:if>
						</c:forEach></td> 
				<td><c:forEach var="entry" items="${arrivals}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<fmt:formatDate pattern="yyyy-MM-dd hh-mm" value="${entry.value}" />
     						</c:if>
						</c:forEach></td> 
				<td><c:out value="${passengerRoute.passengerRouteType}" /></td>
				<!--<td><c:out value="${passengerRoute.trainId}" /></td>-->
				<td><c:out value="${passengerRoute.isActual}" /></td>
			
				<!--<td><c:out value="${passengerRoute.frequency}" /></td>-->
				<!--<td>
						<c:forEach var="entry" items="${places}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.trainId}">
         						<fmt:formatNumber pattern="#,##0" value="${entry.value}" />
     						</c:if>
						</c:forEach>-->
				</td> 
				
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${passengerRoute.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${passengerRoute.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${passengerRoute.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />