<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page
	import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/personalPage" />
<c:set var="userUrl" value="${contextPath}/userAccount" />
<c:set var="passengerUrl" value="${contextPath}/passenger" />
<c:set var="ticketUrl" value="${contextPath}/ticket" />


<h4 class="header">Personal page</h4>
<h5 class="header">Personal data</h5>
<div class="row">
<c:if test = "${not empty user}">
	<form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="user">
        <form:input path="id" type="hidden" />
                
        <div class="row">
            <div class="input-field col s3">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName">First nam</label>
            </div>
        
        
        
            <div class="input-field col s4">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName">Last name</label>
            </div>
        
            <div class="input-field col s4">
                <form:input path="Mail" type="text" disabled="${readonly}" />
                <form:errors path="Mail" cssClass="red-text" />
                <label for="eMail">E-Mail</label>
            </div>
            <a class="btn-floating" href="${userUrl}/${user.id}/edit"><i
						class="material-icons">edit</i></a>
        </div>
        
       
    </form:form>
    </c:if>
    <c:if test = "${not empty passenger}">
    	<h5 class="header">Adress</h5>
    		<form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="passenger">
    
    
        		<form:input path="id" type="hidden" />
        	
                
        
        		<div class="row">
           			<div class="input-field col s3">
               		 	<form:input path="Street" type="text" disabled="${readonly}" />
                		<form:errors path="Street" cssClass="red-text" />
                	<label for="Street">Street</label>
            	</div>
        
           		<div class="input-field col s2">
                	<form:input path="building" type="text" disabled="${readonly}" />
                	<form:errors path="building" cssClass="red-text" />
                	<label for="building">Building</label>
            	</div>
       
               	<div class="input-field col s2">
                	<form:input path="apartments" type="text" disabled="${readonly}" />
                	<form:errors path="apartments" cssClass="red-text" />
                	<label for="apartments">Apartments</label>
            	</div>
        
            	<div class="input-field col s4">
                	<form:input path="phone" type="text" disabled="${readonly}" />
                	<form:errors path="phone" cssClass="red-text" />
                	<label for="phone">Phone number</label>
            	</div>
            		<a class="btn-floating" href="${passengerUrl}/${passenger.id}/edit"><i
						class="material-icons">edit</i></a>
        		</div>
        
    </form:form>
    </c:if>
    
    <div class="col s4">
		<ul class="collection with-header">
			<li class="collection-header"><h4>Tickets</h4></li>
			<c:forEach var="item" items="${tickets}" varStatus="loopCounter">
				<li class="collection-item"> 
						<c:forEach var="entry" items="${depArr}" varStatus="loopCounter">
							<c:if test = "${item.key == entry.key}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
				
						</c:forEach>
					<div>
						"${item.value}"<a href="${ticketUrl}/${item.key}/delete"
							class="secondary-content"><i class="material-icons">delete</i></a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
