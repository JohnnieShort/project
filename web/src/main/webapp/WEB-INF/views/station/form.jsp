<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/station" />
<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.station" /></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name"><mytaglib:i18n key="page.content.name" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="longitude" type="text" disabled="${readonly}" />
                <form:errors path="longitude" cssClass="red-text" />
                <label for="longitude"><mytaglib:i18n key="page.content.longitude" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="latitude" type="text" disabled="${readonly}" />
                <form:errors path="latitude" cssClass="red-text" />
                <label for="latitude"><mytaglib:i18n key="page.content.latitude" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit">сохранить</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
