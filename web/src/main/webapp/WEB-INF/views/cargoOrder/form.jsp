<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.cargoOrder" /></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargoRouteId" type="text" disabled="${readonly}" />
                <form:errors path="cargoRouteId" cssClass="red-text" />
                <label for="cargoRouteId"><mytaglib:i18n key="page.content.cargoRoute" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="customerId" type="text" disabled="${readonly}" />
                <form:errors path="customerId" cssClass="red-text" />
                <label for="customerId"><mytaglib:i18n key="page.content.customer" /></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargoType" type="text" disabled="${readonly}" />
                <form:errors path="cargoType" cssClass="red-text" />
                <label for="cargoType"><mytaglib:i18n key="page.content.cargoType" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationFromId" type="text" disabled="${readonly}" />
                <form:errors path="stationFromId" cssClass="red-text" />
                <label for="stationFromId"><mytaglib:i18n key="page.content.stationFrom" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationToId" type="text" disabled="${readonly}" />
                <form:errors path="stationToId" cssClass="red-text" />
                <label for="stationToId"><mytaglib:i18n key="page.content.stationTo" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="date" type="text" disabled="${readonly}" />
                <form:errors path="date" cssClass="red-text" />
                <label for="date"><mytaglib:i18n key="page.content.date" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="weight" type="text" disabled="${readonly}" />
                <form:errors path="weight" cssClass="red-text" />
                <label for="weight"><mytaglib:i18n key="page.content.weight" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="price" type="text" disabled="${readonly}" />
                <form:errors path="price" cssClass="red-text" />
                <label for="price"><mytaglib:i18n key="page.content.price" /></label>
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
