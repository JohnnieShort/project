<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Edit cargo order</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="cargoType" type="text" disabled="${readonly}" />
                <form:errors path="cargoType" cssClass="red-text" />
                <label for="name">Type of cargo</label>
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
