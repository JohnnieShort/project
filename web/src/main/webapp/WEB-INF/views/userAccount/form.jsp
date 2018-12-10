<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/userAccount" />
<h4 class="header">Edit user account</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="Mail" type="text" disabled="${readonly}" />
                <form:errors path="Mail" cssClass="red-text" />
                <label for="eMail">E-Mail</label>
            </div>
        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" type="password" disabled="${readonly}" />
                <form:errors path="password" cssClass="red-text" />
                <label for="password">Password</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="role" type="text" disabled="${readonly}" />
                <form:errors path="role" cssClass="red-text" />
                <label for="role">Role</label>
            </div>
        </div>
        </sec:authorize>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName">First nam</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName">Last name</label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">сохранить</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
