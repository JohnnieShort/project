<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/wagon" />
<h4 class="header">Wagons</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="wagon_type">wagon_type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="train_id">train_id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="freight_price">freight_price</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="capacity">capacity</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="wagon" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${wagon.id}" /></td>
				<td><c:out value="${wagon.wagonType}" /></td>
				<td><c:out value="${wagon.trainId}" /></td>
				<td><c:out value="${wagon.freightPrice}" /></td>
				<td><c:out value="${wagon.capacity}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${wagon.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${wagon.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${wagon.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${wagon.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${wagon.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />
