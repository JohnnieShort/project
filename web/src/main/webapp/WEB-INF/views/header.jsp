<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<%-- <fmt:setLocale value="${key}" />
<fmt:setBundle basename="text" /> --%>
<header>
	<nav>
		<div class="nav-wrapper container">
		<ul id="dropdown1" class="dropdown-content">
				<li><mytaglib:i18n key="menu.link.ru"/></li>
				<li class="divider"></li>
				<li><mytaglib:i18n key="menu.link.en"/></li>
				
			</ul>
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>

				<li><a href="${baseUrl}/schedule">Schedule</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${baseUrl}/administratorPage">Data managing</a></li>
				</sec:authorize>
				
				<sec:authorize access="!isAnonymous()">
					<li><a class="right" href="${baseUrl}/execute_logout" title="logout"><i
						class="material-icons">arrow_upward</i></a></li>
				</sec:authorize>
				
				<sec:authorize access="isAnonymous()">
					<li><a class="right" href="${baseUrl}/login" title="log in"><i
						class="material-icons">arrow_downward</i></a></li>
				</sec:authorize>
				
				<li>hello, <sec:authentication property="principal" /></li>
				<li><a class="dropdown-trigger" href="#!" data-target="dropdown1">Language<i
						class="material-icons right">arrow_drop_down</i></a></li>
			</ul>
		</div>
	</nav>
</header>