<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/register" />
<c:set var="homeUrl" value="${contextPath}/index" />
<h4 class="header"><mytaglib:i18n key="register.content.header" /></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="mail" type="text" disabled="${readonly}" />
                <form:errors path="mail" cssClass="red-text" />
                <label for="mail"><mytaglib:i18n key="register.content.eMAil" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" type="password" disabled="${readonly}" />
                <form:errors path="password" cssClass="red-text" />
                <label for="password"><mytaglib:i18n key="register.content.pass" /></label>
            </div>
        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="row">
                <div class="input-field col s12">
                    <form:input path="role" type="text" disabled="${readonly}" />
                    <form:errors path="role" cssClass="red-text" />
                    <label for="role"><mytaglib:i18n key="register.content.role" /></label>
                </div>
            </div>
        </sec:authorize>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName"><mytaglib:i18n key="register.content.name" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName"><mytaglib:i18n key="register.content.lastName" /></label>
            </div>
        </div>
        <div class="row">
            <div class="col s6 right-align">
                <button class="btn waves-effect waves-light" type="submit"><mytaglib:i18n key="register.content.submit" /></button>
            </div>
            <div class="col s6 left-align">
                <button class="btn waves-effect waves-light light-blue " href="${homeUrl}" type="button"><mytaglib:i18n key="register.content.home" /></button>
            </div>
        </div>
    </form:form>
</div>
