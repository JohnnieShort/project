<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@page
	import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />
<c:set var="itemUrl" value="${contextPath}/routeItem" />

<h4 class="header"><c:if test="${!readonly}"><mytaglib:i18n key="page.content.edit" /></c:if> <mytaglib:i18n key="page.content.routeItems" /></h4>
<div class="row">
	<div class="col s8">
		<div id="map" style="width: 600px; height: 400px"></div>
	</div>
	<div class="col s4">
		<ul class="collection with-header">
			<li class="collection-header"><h4><mytaglib:i18n key="page.content.routeItems" /></h4></li>
			<c:forEach var="item" items="${routeItems}" varStatus="loopCounter">
				<li class="collection-item"><div>
						"${item.value}"<a href="${itemUrl}/${item.key}/delete"
							class="secondary-content"><i class="material-icons">delete</i></a>
						<a href="${itemUrl}/${item.key}/edit" class="secondary-content"><i
							class="material-icons">edit</i></a>
					</div></li>
			</c:forEach>
		</ul>
	</div>

</div>

<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}/saveItem"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:select path="stationFromId" disabled="${readonly}">
					
					<form:options items="${stationChoices}" />
				</form:select>
				<form:errors path="stationFromId" cssClass="red-text" />
				<label for="stationFromId"><mytaglib:i18n key="page.content.stationFrom" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="stationToId" disabled="${readonly}">
					
					<form:options items="${stationChoices}" />
				</form:select>
				<form:errors path="stationToId" cssClass="red-text" />
				<label for="stationToId"><mytaglib:i18n key="page.content.stationTo" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="passengerRouteId"
					value="${passengerRouteFormModelId}" type="hidden" />
				<form:errors path="passengerRouteId" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="page.content.passengerRoute" />
					"${passengerRouteFormModelId}"</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:input path="departureDate" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="departureDate" cssClass="red-text" />
				<label for="departureDate"><mytaglib:i18n key="page.content.departureDateFrom" /></label>
			</div>

			<div class="input-field col s6">
				<form:input path="departureTime" type="text" disabled="${readonly}"
					cssClass="timepicker" />
				<form:errors path="departureTime" cssClass="red-text" />
				<label for="departureTime"><mytaglib:i18n key="page.content.departureTimeFrom" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:input path="arrivalDate" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="arrivalDate" cssClass="red-text" />
				<label for="arrivalDate"><mytaglib:i18n key="page.content.arrivalDateFrom" /></label>
			</div>

			<div class="input-field col s6">
				<form:input path="arrivalTime" type="text" disabled="${readonly}"
					cssClass="timepicker" />
				<form:errors path="arrivalTime" cssClass="red-text" />
				<label for="arrivalTime"><mytaglib:i18n key="page.content.arrivalTimeFrom" /></label>
			</div>
		</div>




		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right green darken-3" type="submit"><mytaglib:i18n key="page.content.save" /></button>
				</c:if>
			</div>

			<div class="col s3">
				<a class="btn waves-effect waves-light right light-blue" href="${baseUrl}"><mytaglib:i18n key="page.content.toList" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>

<script src="${contextPath}/resources/js/init-map.js"></script>

<script type="text/javascript">
//     var points=${points};
//     var avgLat=${avgLat};
// 	var avgLong=${avgLong};
	initMap(${points},${avgLat} ,${avgLong})
</script>
