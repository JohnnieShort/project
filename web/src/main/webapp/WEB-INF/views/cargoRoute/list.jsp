<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/cargoRoute" />
<h4 class="header">Cargo routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
		<th><mytaglib:sort-link pageUrl="${baseUrl}" column="train_id">train id</mytaglib:sort-link></th>
			
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="cargoRoute" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${cargoRoute.id}" /></td>
			<td><c:out value="${cargoRoute.train.id}" /></td>
				
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoRoute.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${cargoRoute.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${ticket.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${ticket.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${ticket.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
