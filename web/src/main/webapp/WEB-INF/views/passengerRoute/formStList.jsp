<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />

<h4 class="header">Edit items of passenger route</h4>
 <div class="row">
      <div class="col s8"><div id="map" style="width: 600px; height: 400px"></div></div>
      <div class="col s4">
      	<ul class="collection with-header">
        	<li class="collection-header"><h4>Route items</h4></li>
        	<c:forEach var="item" items="${routeItems}" varStatus="loopCounter">
        		<li class="collection-item" ><div>"${item.value}"<a href="${baseUrl}/${item.key}/deleteItem" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        	</c:forEach>
     	 </ul>
      </div>
      
    </div>
   
 <div class="row">   
    <form:form class="col s12" method="POST" action="${baseUrl}/saveItem" modelAttribute="routeItemFormModel">
    
        <form:input path="id" type="hidden" />
          
        
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
        
        <div class="row">
			<div class="input-field col s12">
				<form:input path="passengerRouteId" type="text" value="${passengerRouteFormModelId}" disabled="true" />
				<form:errors path="passengerRouteId" cssClass="red-text" />
				<label for="name">Passenger route id</label>
			</div>
		</div>
		
        
        <div class="row">
			<div class="input-field col s12">
				<form:input path="arrival" type="text" disabled="${readonly}" cssClass="timepicker"/>
				<form:errors path="arrival" cssClass="red-text" />
				<label for="name">arrival time for "to"-station</label>
			</div>
		</div>
        
         <div class="row">
			<div class="input-field col s12">
				<form:input path="departure" type="text" disabled="${readonly}" cssClass="timepicker" />
				<form:errors path="departure" cssClass="red-text" />
				<label for="name">departure time for "from"-station</label>
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
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">Add route item</button>
                </c:if>
            </div>
            
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>

<script src="${contextPath}/resources/js/init-map.js"></script>
