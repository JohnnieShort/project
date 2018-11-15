<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />

<h4 class="header">Edit points of passenger route</h4>
<div class="row">

<form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
    <div id="map" style="width: 800px; height: 400px"></div>
    <form:input path="id" type="hidden" />
        <div class="row">
        	<div class="col s3">
        		<form:textarea path="stationFrom" disabled="true"/>
        	</div>
            <div class="input-field col s12">
            	
            	<form:select path="stationFrom">
            		<form:option value="0" label="Select station of departure" />
            			<c:forEach var="station" items="${stationsList}">
    					<option value="${station}">"${station.name}"</option>
					</c:forEach>
            	</form:select>
            </div>	
            <div class="col s3">
            		<a class="waves-effect waves-light btn right" href="${baseUrl}/addStation"><i
					class="material-icons">add</i></a>
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
<script src="${contextPath}/resources/js/init-map.js"></script>