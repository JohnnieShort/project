<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>
				
				<li><a href="${baseUrl}/station">Stations</a></li>
				<li><a href="${baseUrl}/wagon">Wagons</a></li>
				
				
			</ul>
		</div>
	</nav>
</header>