<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/ticket" />
<h4 class="header">Edit ticket</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="passengerId" type="hidden" value="${passengerId}"/>
        <form:input path="passengerRouteId" type="hidden" value="${passengerRouteId}"/>
       
        <c:if test = "${empty passengerId}">
        
      	
        	<div class="row">
            	<div class="input-field col s6">
               		<input id="street" name="street" type="text" value="${street}" class="validate">
          			<label for="street">street:</label>
           		</div>
        
           		<div class="input-field col s3">
               		<input id="building" name="building" type="number" value="${building}" class="validate">
          			<label for="building">building:</label>
            	</div>
            	<div class="input-field col s3">
               		<input id="apartments" name="apartments" type="number" value="${apartments}" class="validate">
          			<label for="apartments">apartments:</label>
            	</div>
        	</div>   
        	<div class="row">
           		<div class="input-field col s6">
               		<input id="phone" name="phone" type="text" value="${phone}" class="validate">
          			<label for="phone">phone number:</label>
            	</div>
       		</div>  
        </c:if>
        
        <div class="row">
			<div class="input-field col s6">
				<form:select path="stationFromId" disabled="${readonly}">
					<form:option value="0" label="Select station from" />
					<form:options items="${fromItems}" />
				</form:select>
				<form:errors path="stationFromId" cssClass="red-text" />
				<label for="stationFromId">Station from</label>
			</div>
		

		
			<div class="input-field col s6">
				<form:select path="stationToId" disabled="${readonly}">
					<form:option value="0" label="Select station to" />
					<form:options items="${toItems}" />
				</form:select>
				<form:errors path="stationToId" cssClass="red-text" />
				<label for="stationToId">Station to</label>
			</div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
               	<input id="quantity" name="quantity" type="text" value="${quantity}" class="validate">
          		<label for="quantity">quantity</label>
            </div>
        </div>
        
        
        <div class="row">
            <div class="col s6"></div>
            	<div class="col s3">
                	<c:if test="${!readonly}">
                    	<button class="btn waves-effect waves-light right" type="submit">next</button>
                	</c:if>
            	</div>
            	<div class="col s3">
                	<a class="btn waves-effect waves-light right" href="${url}">back<i class="material-icons right"></i>
                	</a>
            	</div>
        	</div>
        </div>
    </form:form>
</div>
