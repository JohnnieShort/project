<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/routeItem" />
<h4 class="header">Items of Routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
		<%--<th>station from</th>--%>
		<%--<th>station to</th>--%>
		<%--<th>passenger route id</th>--%>
			<th>arrival</th>
			<th>departure</th>
			
			<th>ordinal number</th>
		
			<th>is first</th>
			<th>is last</th>
			
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="routeItem" items="${list}" varStatus="loopCounter">
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
					class="btn-floating red disabled"
					href="${baseUrl}/${routeItem.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
