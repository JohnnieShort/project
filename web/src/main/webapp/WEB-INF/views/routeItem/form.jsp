<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/routeItem" />
<h4 class="header">Edit item of route</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="arrival" type="text" disabled="${readonly}" />
                <form:errors path="arrival" cssClass="red-text" />
                <label for="arrival">Arrival</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="departure" type="text" disabled="${readonly}" />
                <form:errors path="departure" cssClass="red-text" />
                <label for="departure">Departure</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="ordinalNum" type="text" disabled="${readonly}" />
                <form:errors path="ordinalNum" cssClass="red-text" />
                <label for="ordinalNum">Ordinal number</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="isFirst" type="text" disabled="${readonly}" />
                <form:errors path="isFirst" cssClass="red-text" />
                <label for="isFirst">Is it first item of route</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="isLast" type="text" disabled="${readonly}" />
                <form:errors path="isLast" cssClass="red-text" />
                <label for="isLast">Is it last item of route</label>
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
