<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<c:set var="baseUrl" value="${contextPath}/passenger" />
<h4 class="header"><mytaglib:i18n key="page.content.passengers" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id"><mytaglib:i18n key="page.content.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="street"><mytaglib:i18n key="page.content.street" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="building"><mytaglib:i18n key="page.content.building" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="apartments"><mytaglib:i18n key="page.content.apartments" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="phone"><mytaglib:i18n key="page.content.phone" /></mytaglib:sort-link></th>
			
			
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created"><mytaglib:i18n key="page.content.save" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated"><mytaglib:i18n key="page.content.toList" /></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="passenger" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${passenger.id}" /></td>
				<td><c:out value="${passenger.street}" /></td>
				<td><c:out value="${passenger.building}" /></td>
				<td><c:out value="${passenger.apartments}" /></td>
				<td><c:out value="${passenger.phone}" /></td>
				
				
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passenger.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${passenger.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${passenger.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${passenger.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${passenger.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />
