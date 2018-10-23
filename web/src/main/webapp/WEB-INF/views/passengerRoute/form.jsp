<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />

<h4 class="header">Edit passenger route</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationFrom" type="text" disabled="${readonly}" />
                <form:errors path="stationFrom" cssClass="red-text" />
                <label for="stationFrom">Station from id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationTo" type="text" disabled="${readonly}" />
                <form:errors path="stationTo" cssClass="red-text" />
                <label for="stationTo">Station to id</label>
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
                <form:input path="arrival" type="text" disabled="${readonly}" />
                <form:errors path="arrival" cssClass="red-text" />
                <label for="arrival">Arrival</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="train" type="text" disabled="${readonly}" />
                <form:errors path="train" cssClass="red-text" />
                <label for="train">Train id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="passengerRoutetype" type="text" disabled="${readonly}" />
                <form:errors path="passengerRoutetype" cssClass="red-text" />
                <label for="passengerRoutetype">Passenger route type</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="isActual" type="text" disabled="${readonly}" />
                <form:errors path="isActual" cssClass="red-text" />
                <label for="isActual">Actuality</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="frequency" type="text" disabled="${readonly}" />
                <form:errors path="frequency" cssClass="red-text" />
                <label for="frequency">Frequency</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="places" type="text" disabled="${readonly}" />
                <form:errors path="places" cssClass="red-text" />
                <label for="places">Number of places</label>
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
