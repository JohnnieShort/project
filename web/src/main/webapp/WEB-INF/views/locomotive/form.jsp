<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/locomotive" />
<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.locomotive" /></h4>
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
                <form:input path="power" type="text" disabled="${readonly}" />
                <form:errors path="power" cssClass="red-text" />
                <label for="power"><mytaglib:i18n key="page.content.power" /></label>
            </div>
        </div>
        
        
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="page.content.save" /></button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}"><mytaglib:i18n key="page.content.toList" /><i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
