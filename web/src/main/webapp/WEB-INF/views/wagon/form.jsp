<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/wagon" />
<h4 class="header">Edit wagon</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="wagonType" type="text" disabled="${readonly}" />
                <form:errors path="wagonType" cssClass="red-text" />
                <label for="name">wagonType</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="freightPrice" type="text" disabled="${readonly}" />
                <form:errors path="freightPrice" cssClass="red-text" />
                <label for="name">freightPrice</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="capacity" type="text" disabled="${readonly}" />
                <form:errors path="capacity" cssClass="red-text" />
                <label for="name">capacity</label>
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
