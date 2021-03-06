<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/schedule" />
<c:set var="routeUrl" value="${contextPath}/passengerRoute" />
<c:set var="loginUrl" value="${contextPath}/login" />
<c:set var="ticketUrl" value="${contextPath}/ticket" />
<c:set var="passengerId" value=""  />
<h2 class="header"><mytaglib:i18n key="schedule.title.header"/></h2>
<table class="bordered highlight">
	<tbody>
		<tr>
			
			<th><mytaglib:i18n key="schedule.title.columnStationFrom"/></th>
			<th><mytaglib:i18n key="schedule.title.columnStationTo"/></th>
			<th><mytaglib:i18n key="schedule.title.departure"/></th>
			<th><mytaglib:i18n key="schedule.title.arrival"/></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="passenger_route_type"><mytaglib:i18n key="schedule.title.routeType"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="frequency"><mytaglib:i18n key="schedule.title.frequency"/></mytaglib:sort-link></th>
			
			
			<th></th>
		</tr>
		<c:forEach var="passengerRoute" items="${gridItems}" varStatus="loopCounter">
			<tr>
				
				<td><c:forEach var="entry" items="${firstStations}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
						</c:forEach></td> <%--"${passengerRoutefrom.id}"--%>
				<td><c:forEach var="entry" items="${lastStations}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
						</c:forEach></td> <%--"${passengerRoute.to.id}"--%>
				<td><c:forEach var="entry" items="${departures}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<fmt:formatDate pattern="yyyy-MM-dd hh-mm" value="${entry.value}" />
     						</c:if>
						</c:forEach></td> <%--"${passengerRoute.departure}"--%>
				<td><c:forEach var="entry" items="${arrivals}" varStatus="loopCounter">
							<c:if test = "${entry.key == passengerRoute.id}">
         						<fmt:formatDate pattern="yyyy-MM-dd hh-mm" value="${entry.value}" />
     						</c:if>
						</c:forEach></td> <%--"${passengerRoute.arrival}"--%>
				<td><c:out value="${passengerRoute.passengerRouteType}" /></td>
				
				
			
				<td><c:out value="${passengerRoute.frequency}" /></td>
				
				
				<td class="right"><a class="btn-floating  green lighten-3"
					href="${ticketUrl}/${passengerRoute.id}/buy"><i class="material-icons" title="<mytaglib:i18n key="schedule.title.buy"/>">attach_money</i></a>
				</td>
				<td class="right"><a class="btn-floating yellow darken-1"
					href="${routeUrl}/${passengerRoute.id}/details"><i class="material-icons" title="<mytaglib:i18n key="schedule.title.details"/>">info</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jspFragments:paging />