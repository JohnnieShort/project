<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<%@page
	import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/personalPage" />
<c:set var="userUrl" value="${contextPath}/userAccount" />
<c:set var="passengerUrl" value="${contextPath}/passenger" />
<c:set var="ticketUrl" value="${contextPath}/ticket" />


<h4 class="header"><mytaglib:i18n key="personalPage.content.header" /></h4>
<h5 class="header"><mytaglib:i18n key="personalPage.content.header2" /></h5>
<div class="row">
<c:if test = "${not empty user}">
	<form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="user">
        <form:input path="id" type="hidden" />
                
        <div class="row">
            <div class="input-field col s3">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName"><mytaglib:i18n key="personalPage.content.nameLabel" /></label>
            </div>
        
        
        
            <div class="input-field col s4">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName"><mytaglib:i18n key="personalPage.content.lastNameLabel" /></label>
            </div>
        
            <div class="input-field col s4">
                <form:input path="Mail" type="text" disabled="${readonly}" />
                <form:errors path="Mail" cssClass="red-text" />
                <label for="eMail"><mytaglib:i18n key="personalPage.content.eMailLabel" /></label>
            </div>
            <a class="btn-floating yellow darken-1" href="${userUrl}/${user.id}/edit"><i
						class="material-icons">edit</i></a>
        </div>
        
       
    </form:form>
    </c:if>
    <c:if test = "${not empty passenger}">
    	<h5 class="header"><mytaglib:i18n key="personalPage.content.Adress" /></h5>
    		<form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="passenger">
    
    
        		<form:input path="id" type="hidden" />
        	
                
        
        		<div class="row">
           			<div class="input-field col s3">
               		 	<form:input path="Street" type="text" disabled="${readonly}" />
                		<form:errors path="Street" cssClass="red-text" />
                	<label for="Street"><mytaglib:i18n key="personalPage.content.streetLabel" /></label>
            	</div>
        
           		<div class="input-field col s2">
                	<form:input path="building" type="text" disabled="${readonly}" />
                	<form:errors path="building" cssClass="red-text" />
                	<label for="building"><mytaglib:i18n key="personalPage.content.bldLabel" /></label>
            	</div>
       
               	<div class="input-field col s2">
                	<form:input path="apartments" type="text" disabled="${readonly}" />
                	<form:errors path="apartments" cssClass="red-text" />
                	<label for="apartments"><mytaglib:i18n key="personalPage.content.flatLabel" /></label>
            	</div>
        
            	<div class="input-field col s4">
                	<form:input path="phone" type="text" disabled="${readonly}" />
                	<form:errors path="phone" cssClass="red-text" />
                	<label for="phone"><mytaglib:i18n key="personalPage.content.phoneLabel" /></label>
            	</div>
            		<a class="btn-floating yellow darken-1" href="${passengerUrl}/${passenger.id}/edit"><i
						class="material-icons">edit</i></a>
        		</div>
        
    </form:form>
    </c:if>
    
    <div class="col s4">
		<ul class="collection with-header">
			<li class="collection-header"><h4><mytaglib:i18n key="personalPage.content.tableLabel" /></h4></li>
			<c:forEach var="item" items="${tickets}" varStatus="loopCounter">
				<li class="collection-item"> 
						<c:forEach var="entry" items="${departure}" varStatus="loopCounter">
							<c:if test = "${item.key == entry.key}">
         						<c:out value = "${entry.value}"/>
     						</c:if>
				
						</c:forEach>
						<c:forEach var="entry" items="${arrival}" varStatus="loopCounter">
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
