<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/passenger" />
<h4 class="header">Edit passenger</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="input-field col s12">
                <form:input path="userAccountId" type="text" disabled="${readonly}" />
                <form:errors path="userAccountId" cssClass="red-text" />
                <label for="userAccountId">User account id</label>
            </div>
        </div>
        </sec:authorize>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="Street" type="text" disabled="${readonly}" />
                <form:errors path="Street" cssClass="red-text" />
                <label for="Street">Street</label>
            </div>
        </div>
        <div class="row">
        <div class="input-field col s2">
                <form:input path="building" type="text" disabled="${readonly}" />
                <form:errors path="building" cssClass="red-text" />
                <label for="building">Building</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s2">
                <form:input path="apartments" type="text" disabled="${readonly}" />
                <form:errors path="apartments" cssClass="red-text" />
                <label for="apartments">Apartments</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s4">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone">Phone number</label>
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
                <a class="btn waves-effect waves-light right" href="${url}">back<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
