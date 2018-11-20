<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/train" />
<h4 class="header">Trains</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="locomotive_id">locomotive id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="track">track</mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="train" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${train.id}" /></td>
				<td><c:out value="${train.locomotive.id}" /></td>	
				<td><c:out value="${train.track}" /></td>	
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${train.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${train.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${train.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${train.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red "
					href="${baseUrl}/${train.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
