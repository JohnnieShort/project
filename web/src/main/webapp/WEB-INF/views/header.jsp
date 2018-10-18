<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>

				<li><a href="${baseUrl}/station">Stations</a></li>
				<li><a href="${baseUrl}/wagon">Wagons</a></li>
				<li><a href="${baseUrl}/cargoOrder">Cargo orders</a></li>
				<li><a href="${baseUrl}/cargoRoute">Cargo routes</a></li>
				<li><a href="${baseUrl}/customer">Customers</a></li>
				<li><a href="${baseUrl}/locomotive">Locomotives</a></li>
			<%--<li><a href="${baseUrl}/passenger">Passengers</a></li>--%>
			<%--<li><a href="${baseUrl}/passengerRoute">Passenger Routes</a></li>--%>
			<%--<li><a href="${baseUrl}/routeItem">Items of routs</a></li>--%>
				<li><a href="${baseUrl}/ticket">Tickets</a></li>
				<li><a href="${baseUrl}/train">Trains</a></li>
				<li><a href="${baseUrl}/userAccount">Users Accounts</a></li>
				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${baseUrl}/execute_logout" title="logout"><i
						class="material-icons">arrow_forward</i></a>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</header>