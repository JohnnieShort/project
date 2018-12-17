<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/passenger" />
<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.passenger" /></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <%-- <form:input path="userAccountId" type="hidden" /> --%>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="Street" type="text" disabled="${readonly}" />
                <form:errors path="Street" cssClass="red-text" />
                <label for="Street"><mytaglib:i18n key="page.content.street" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s2">
                <form:input path="building" type="text" disabled="${readonly}" />
                <form:errors path="building" cssClass="red-text" />
                <label for="building"><mytaglib:i18n key="page.content.building" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s2">
                <form:input path="apartments" type="text" disabled="${readonly}" />
                <form:errors path="apartments" cssClass="red-text" />
                <label for="apartments"><mytaglib:i18n key="page.content.apartments" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s4">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone"><mytaglib:i18n key="page.content.phone" /></label>
            </div>
        </div>
        <div class="row">
            <div class="col s12 center">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light  green darken-3" type="submit"><mytaglib:i18n key="page.content.save" /></button>
                </c:if>
                <a class="btn waves-effect waves-light  light-blue" href="${url}"><mytaglib:i18n key="page.content.toList" /><i class="material-icons "></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
