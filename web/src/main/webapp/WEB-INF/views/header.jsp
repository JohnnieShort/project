<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>

				<li><a href="${baseUrl}/schedule">Schedule</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${baseUrl}/administratorPage">Data managing</a></li>
				</sec:authorize>
				
				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${baseUrl}/execute_logout" title="logout"><i
						class="material-icons">arrow_upward</i></a>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<a class="right" href="${baseUrl}/login" title="log in"><i
						class="material-icons">arrow_downward</i></a>
				</sec:authorize>
				hello, <sec:authentication property="principal" />
			</ul>
		</div>
	</nav>
</header>