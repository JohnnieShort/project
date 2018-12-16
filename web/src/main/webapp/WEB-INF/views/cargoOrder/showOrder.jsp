<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/cargoOrder" />
<h4 class="header">Edit cargo order</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
          <form:input path="cargoRouteId" type="hidden"  />
          <form:input path="customerId" type="hidden"  />
          <form:input path="price" type="hidden" />
        <div class="row">
            <div class="input-field col s12" >
               <form:select path="cargoType" disabled="${readonly}">
            		<form:option value="0" label="Select type of cargo" />
            		<form:options items="${cargoTypes}" />
            	</form:select>
            	<form:errors path="cargoType" cssClass="red-text" />
            	 <label for="cargoType">Train id</label>
            </div>
        </div>              
      
        <div class="row">
			<div class="input-field col s12">
				<form:select path="stationFromId" disabled="${readonly}">
					<form:option value="0" label="Select station from" />
					<form:options items="${stationChoices}" />
				</form:select>
				<form:errors path="stationFromId" cssClass="red-text" />
				<label for="stationFromId">Station from</label>
			</div>
		</div>
        
        <div class="row">
			<div class="input-field col s12">
				<form:select path="stationToId" disabled="${readonly}">
					<form:option value="0" label="Select station to" />
					<form:options items="${stationChoices}" />
				</form:select>
				<form:errors path="stationToId" cssClass="red-text" />
				<label for="stationToId">Station to</label>
			</div>
		</div>
        
        <div class="input-field col s6">
			<form:input path="date" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">date</label>
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
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="col s3">
                <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
            </sec:authorize>
        </div>
    </form:form>
</div>
