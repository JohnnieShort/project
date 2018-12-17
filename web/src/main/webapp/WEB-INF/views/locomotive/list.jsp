<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/locomotive" />
<h4 class="header"><mytaglib:i18n key="page.content.locomotives" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id"><mytaglib:i18n key="page.content.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="name"><mytaglib:i18n key="page.content.name" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="power"><mytaglib:i18n key="page.content.power" /></mytaglib:sort-link></th>
			
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created"><mytaglib:i18n key="page.content.created" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated"><mytaglib:i18n key="page.content.updated" /></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="locomotive" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${locomotive.id}" /></td>
				<td><c:out value="${locomotive.name}" /></td>
				<td><c:out value="${locomotive.power}" /></td>
				
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${locomotive.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${locomotive.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${locomotive.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${locomotive.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${locomotive.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
    class="material-icons">add</i></a>
<jspFragments:paging />