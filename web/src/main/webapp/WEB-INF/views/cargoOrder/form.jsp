<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Edit cargo order</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargoRouteId" type="text" disabled="${readonly}" />
                <form:errors path="cargoRouteId" cssClass="red-text" />
                <label for="cargoRouteId">Cargo route id</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="customerId" type="text" disabled="${readonly}" />
                <form:errors path="customerId" cssClass="red-text" />
                <label for="customerId">Customer id</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargoType" type="text" disabled="${readonly}" />
                <form:errors path="cargoType" cssClass="red-text" />
                <label for="cargoType">Type of cargo</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationFromId" type="text" disabled="${readonly}" />
                <form:errors path="stationFromId" cssClass="red-text" />
                <label for="stationFromId">Station from id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationToId" type="text" disabled="${readonly}" />
                <form:errors path="stationToId" cssClass="red-text" />
                <label for="stationToId">Station to id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="date" type="text" disabled="${readonly}" />
                <form:errors path="date" cssClass="red-text" />
                <label for="date">date</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="weight" type="text" disabled="${readonly}" />
                <form:errors path="weight" cssClass="red-text" />
                <label for="weight">weight</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="price" type="text" disabled="${readonly}" />
                <form:errors path="price" cssClass="red-text" />
                <label for="price">Price</label>
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
                <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
