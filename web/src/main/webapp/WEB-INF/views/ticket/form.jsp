<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/ticket" />
<h4 class="header">Edit ticket</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="passengerId" type="text" disabled="${readonly}" />
                <form:errors path="passengerId" cssClass="red-text" />
                <label for="passengerId">Passenger id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="passengerRouteId" type="text" disabled="${readonly}" />
                <form:errors path="passengerRouteId" cssClass="red-text" />
                <label for="passengerRouteId">Passenger route id</label>
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
                <form:input path="price" type="text" disabled="${readonly}" />
                <form:errors path="price" cssClass="red-text" />
                <label for="price">Price</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
               	<input id="quantity" name="quantity" type="text" class="validate">
          <label for="quantity">quantity</label>
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
