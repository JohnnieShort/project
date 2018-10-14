<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/wagon" />
<h4 class="header">Wagons</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>wagon_type</th>
			<%--<th>train_id</th>--%>
			<th>freight_price</th>
			<th>capacity</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="wagon" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${wagon.id}" /></td>
				<td><c:out value="${wagon.wagonType}" /></td>
				<%--<td><c:out value="${wagon.Train.id}" /></td>--%>
				<td><c:out value="${wagon.freightPrice}" /></td>
				<td><c:out value="${wagon.capacity}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${wagon.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${wagon.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${wagon.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${wagon.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${wagon.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
