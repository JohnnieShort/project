<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />

<header>
	<nav>
		<div class="nav-wrapper container purple lighten-4">
				<ul id="dropdown1" class="dropdown-content">
					<li><a href="${baseUrl}?language=ru">RU</a></li>
					<li class="divider"></li>
					<li><a href="${baseUrl}?language=en">EN</a></li>
				
				</ul>
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/"><mytaglib:i18n key="menu.link.home"/><i class="material-icons left" >home</i></a></li>

				<li><a href="${baseUrl}/schedule"><mytaglib:i18n key="menu.link.schedule"/><i class="material-icons left">train</i></a></li>
				
				<li><a href="${baseUrl}/cargoOrder/doOrder"><mytaglib:i18n key="menu.link.cargoOrder"/> <i class="material-icons left">local_grocery_store</i></a></li>
				<li><a href="${baseUrl}/personalPage"><mytaglib:i18n key="menu.link.personalPage"/> <i class="material-icons left">face</i></a></li>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${baseUrl}/administratorPage"><mytaglib:i18n key="menu.link.dataManaging"/></a></li>
				</sec:authorize>
				
				<sec:authorize access="!isAnonymous()">
					<li><a class="right" href="${baseUrl}/execute_logout" title=<mytaglib:i18n key="menu.link.logout"/>><i
						class="material-icons">arrow_upward</i></a></li>
				</sec:authorize>
				
				<sec:authorize access="isAnonymous()">
					<li><a class="right" href="${baseUrl}/login" title=<mytaglib:i18n key="menu.link.login"/>><i
						class="material-icons">arrow_downward</i></a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
				<li><div class="chip"><mytaglib:i18n key="menu.link.hello"/>, <sec:authentication property="principal" /></div></li>
				</sec:authorize>
				<sec:authorize access="!isAnonymous()">
				<li><div class="chip"><mytaglib:i18n key="menu.link.hello"/>, <sec:authentication property="name" /></div></li>
				</sec:authorize>
				<li><a class="dropdown-trigger" href="#!" data-target="dropdown1"><mytaglib:i18n key="menu.link.language"/><i
						class="material-icons right">arrow_drop_down</i></a></li>
			</ul>
		</div>
	</nav>
</header>



  
        