<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/routeItem" />
<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.routeItem" /></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
         <div class="row">
            <div class="input-field col s12">
                <form:input path="passengerRouteId" type="text" disabled="${readonly}" />
                <form:errors path="passengerRouteId" cssClass="red-text" />
                <label for="passengerRouteId"><mytaglib:i18n key="page.content.passengerRoute" /></label>
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
        
        <div class="input-field col s6">
			<form:input path="departureDate" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="departureDate" cssClass="red-text" />
				<label for="departureDate"><mytaglib:i18n key="page.content.departureDate" /></label>
		</div>
        
        <div class="input-field col s6">
			<form:input path="departureTime" type="text" disabled="${readonly}" cssClass="timepicker" />
				<form:errors path="departureTime" cssClass="red-text" />
				<label for="departureTime"><mytaglib:i18n key="page.content.departureTime" /></label>
		</div>
        
        <div class="input-field col s6">
			<form:input path="arrivalDate" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="arrivalDate" cssClass="red-text" />
				<label for="arrivalDate"><mytaglib:i18n key="page.content.arrivalDate" /></label>
		</div>
		
        <div class="input-field col s6">
			<form:input path="arrivalTime" type="text" disabled="${readonly}" cssClass="timepicker" />
				<form:errors path="arrivalTime" cssClass="red-text" />
				<label for="arrivalTime"><mytaglib:i18n key="page.content.arrivalTime" /></label>
		</div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="ordinalNum" type="text" disabled="${readonly}" />
                <form:errors path="ordinalNum" cssClass="red-text" />
                <label for="ordinalNum"><mytaglib:i18n key="page.content.ordinal" /></label>
            </div>
        </div>
        
        
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit"><mytaglib:i18n key="page.content.save" /></button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right light-blue" href="${url}"><mytaglib:i18n key="page.content.toList" /><i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
