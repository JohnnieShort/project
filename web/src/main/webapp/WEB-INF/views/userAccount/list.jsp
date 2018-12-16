<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/userAccount" />
<h4 class="header">Users accounts</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="e_mail">e-mail</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="password">password</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="role">role</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="first_name">first name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="last_name">last name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${baseUrl}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="userAccount" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${userAccount.id}" /></td>
				<td><c:out value="${userAccount.mail}" /></td>
				<td><p class="truncate"><c:out value="${userAccount.password}" /></p></td>
				<td><c:out value="${userAccount.role}" /></td>
				<td><c:out value="${userAccount.firstName}" /></td>
				<td><c:out value="${userAccount.lastName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.updated}" /></td>
				<td class="right">
					<a class="btn-floating"	href="${baseUrl}/${userAccount.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating yellow darken-1" href="${baseUrl}/${userAccount.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red "href="${baseUrl}/${userAccount.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right purple darken-1" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
<jspFragments:paging />