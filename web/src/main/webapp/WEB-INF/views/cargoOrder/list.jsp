<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Cargo Orders</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
		<%--<th>cargo route id</th>--%>
		<%--<th>customer id</th>--%>
			<th>cargo type</th>
		<%--<th>station from</th>--%>
		<%--<th>station to</th>--%>
			<th>date</th>
			<th>weight</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="cargoOrder" items="${list}" varStatus="loopCounter">
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
					class="btn-floating red disabled"
					href="${baseUrl}/${cargoOrder.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
