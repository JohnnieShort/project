<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />

<h4 class="header">Edit passenger route</h4>
<div class="row">


   
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
    
    
        <form:input path="id" type="hidden" />
          
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="train" type="text" disabled="${readonly}" />
                <form:errors path="train" cssClass="red-text" />
                <label for="train">Train id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
               <form:select path="passengerRouteType">
            		<form:option value="0" label="Select type of route" />
            		<c:forEach var="enum" items="${routeTypeList}">
    					<option value="${enum}">"${enum}"</option>
					</c:forEach>
            	</form:select>
            </div>
        </div>
        
        <div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> not actual <form:checkbox path="isActual" disabled="${readonly}" /> <span
						class="lever"></span> actual
					</label>
				</div>
				<label class="switch-label">is actual</label> <br />
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