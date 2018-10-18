<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/station" />
<h4 class="header">Edit station</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name">Name</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="longitude" type="text" disabled="${readonly}" />
                <form:errors path="longitude" cssClass="red-text" />
                <label for="name">Longitude</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="latitude" type="text" disabled="${readonly}" />
                <form:errors path="latitude" cssClass="red-text" />
                <label for="name">Latitude</label>
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
