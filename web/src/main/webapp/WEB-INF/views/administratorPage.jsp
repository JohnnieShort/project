<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />

	<h3 class="header">Data managing</h3>
	
		
	<div class="collection">
        <a href="${baseUrl}/station" class="collection-item active">Stations</a>
        <a href="${baseUrl}/wagon" class="collection-item">Wagons</a>
        <a href="${baseUrl}/cargoOrder" class="collection-item active">Cargo Orders</a>
        <a href="${baseUrl}/cargoRoute" class="collection-item">Cargo routes</a>
        <a href="${baseUrl}/customer" class="collection-item active">Customers</a>
        <a href="${baseUrl}/locomotive" class="collection-item">Locomotives</a>
        <a href="${baseUrl}/passenger" class="collection-item active">Passengers</a>
        <a href="${baseUrl}/passengerRoute" class="collection-item">Passenger Routes</a>
        <a href="${baseUrl}/routeItem" class="collection-item active">Items of routs</a>
        <a href="${baseUrl}/ticket" class="collection-item">Tickets</a>
        <a href="${baseUrl}/train" class="collection-item active">Trains</a>
        <a href="${baseUrl}/userAccount" class="collection-item">Users Accounts</a>
    </div>