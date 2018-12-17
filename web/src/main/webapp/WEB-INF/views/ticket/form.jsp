<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/ticket" />
<h4 class="header"><c:if test="${!readonly}">Edit</c:if> ticket</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="passengerId" type="hidden" value="${passengerId}" />
        <form:input path="passengerRouteId" type="hidden" value="${passengerRouteId}" />
        <h5 class="header">Please check additional info for the ticket order</h5>
        <div class="row">
            <div class="input-field col s6">
                <form:input path="passenger.street" type="text" disabled="${readonly}" />
                <form:errors path="passenger.street" cssClass="red-text" />
                <label for="passenger.street">street:</label>
            </div>
            <div class="input-field col s3">
                <form:input path="passenger.building" type="number" disabled="${readonly}" />
                <form:errors path="passenger.building" cssClass="red-text" />
                <label for="passenger.building">building:</label>
            </div>
            <div class="input-field col s3">
                <form:input path="passenger.apartments" type="number" disabled="${readonly}" />
                <form:errors path="passenger.apartments" cssClass="red-text" />
                <label for="passenger.apartments">apartments:</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <form:input path="passenger.phone" type="number" disabled="${readonly}" />
                <form:errors path="passenger.phone" cssClass="red-text" />
                <label for="passenger.phone">phone number:</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <form:select path="stationFromId" disabled="${readonly}">
                    <form:options items="${fromItems}" />
                </form:select>
                <form:errors path="stationFromId" cssClass="red-text" />
                <label for="stationFromId">Station from</label>
            </div>
            <div class="input-field col s6">
                <form:select path="stationToId" disabled="${readonly}">
                    <form:options items="${toItems}" />
                </form:select>
                <form:errors path="stationToId" cssClass="red-text" />
                <label for="stationToId">Station to</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="quantity" type="number" disabled="${readonly}" />
                <form:errors path="quantity" cssClass="red-text" />
                <label for="quantity">quantity:</label>
            </div>
        </div>
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit">next</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right  light-blue" href="${url}">back<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
