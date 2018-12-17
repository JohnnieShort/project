<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
    <ul id="dropdown-admin" class="dropdown-content">
        <li><a href="${baseUrl}/station"><mytaglib:i18n key="menu.dataDropDown.stations" /></a></li>
        <li><a href="${baseUrl}/wagon"><mytaglib:i18n key="menu.dataDropDown.wagons" /></a></li>
    <!--<li><a href="${baseUrl}/cargoOrder"><mytaglib:i18n key="menu.dataDropDown.cargoOrdes" /></a></li>-->
    <!--<li><a href="${baseUrl}/cargoRoute"></a><mytaglib:i18n key="menu.dataDropDown.cargoRoutes" /></li>-->
    <!--<li><a href="${baseUrl}/customer"></a><mytaglib:i18n key="menu.dataDropDown.customers" /></a></li>-->
        <li><a href="${baseUrl}/locomotive"><mytaglib:i18n key="menu.dataDropDown.locomotives" /></a></li>
        <li><a href="${baseUrl}/passenger"><mytaglib:i18n key="menu.dataDropDown.passengers" /></a></li>
        <li><a href="${baseUrl}/passengerRoute"><mytaglib:i18n key="menu.dataDropDown.passengerRoutes" /></a></li>
        <li><a href="${baseUrl}/routeItem"><mytaglib:i18n key="menu.dataDropDown.items" /></a></li>
        <li><a href="${baseUrl}/ticket"><mytaglib:i18n key="menu.dataDropDown.tickets" /></a></li>
        <li><a href="${baseUrl}/train"><mytaglib:i18n key="menu.dataDropDown.trains" /></a></li>
        <li><a href="${baseUrl}/userAccount"><mytaglib:i18n key="menu.dataDropDown.userAccounts" /></a></li>
    </ul>
    <ul id="dropdown-languages" class="dropdown-content">
        <li><a href="${baseUrl}?language=ru">RU</a></li>
        <li class="divider"></li>
        <li><a href="${baseUrl}?language=en">EN</a></li>
    </ul>
    <nav>
        <div class="nav-wrapper  purple lighten-4">
            <ul class="left hide-on-med-and-down">
                <li><a href="${baseUrl}/"><mytaglib:i18n key="menu.link.home" /><i class="material-icons left">home</i></a></li>
                <li><a href="${baseUrl}/schedule"><mytaglib:i18n key="menu.link.schedule" /><i class="material-icons left">train</i></a></li>
                <sec:authorize access="!isAnonymous()">
                <!--<li><a href="${baseUrl}/cargoOrder/doOrder"><mytaglib:i18n key="menu.link.cargoOrder" /> <i class="material-icons left">local_grocery_store</i></a></li>-->
                    <li><a href="${baseUrl}/personalPage"><mytaglib:i18n key="menu.link.personalPage" /> <i class="material-icons left">face</i></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-admin"><mytaglib:i18n key="menu.link.dataManaging" /><i
                            class="material-icons right">arrow_drop_down</i></a></li>
                </sec:authorize>
            </ul>
            <ul class=" right">
                <li><a class="dropdown-trigger" href="#!" data-target="dropdown-languages"><mytaglib:i18n key="menu.link.language" /><i
                        class="material-icons right">arrow_drop_down</i></a></li>
                <sec:authorize access="isAnonymous()">
                    <li><a href="${baseUrl}/login" title=<mytaglib:i18n key="menu.link.login"/>><mytaglib:i18n key="menu.link.logIn" /></a></li>
                </sec:authorize>
                <sec:authorize access="!isAnonymous()">
                    <li><mytaglib:i18n key="menu.link.hello" /> , <sec:authentication property="name" /></li>
                    <li><a href="${baseUrl}/execute_logout" title=<mytaglib:i18n key="menu.link.logout"/>><i class="material-icons">arrow_upward</i></a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>
