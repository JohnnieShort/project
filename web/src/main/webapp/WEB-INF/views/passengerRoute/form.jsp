<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />

<h4 class="header">Edit passenger route</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
    <div id="map" style="width: 600px; height: 400px"></div>
    
    <script type="text/javascript">
    <%--// Функция ymaps.ready() будет вызвана, когда--%>
    <%--// загрузятся все компоненты API, а также когда будет готово DOM-дерево.--%>
    ymaps.ready(init);
    function init(){ 
        <%--// Создание карты. --%>   
        var myMap = new ymaps.Map("map", {
           <%-- // Координаты центра карты.--%>
            <%--// Порядок по умолчанию: «широта, долгота».--%>
           <%-- // Чтобы не определять координаты центра карты вручную,--%>
           <%-- // воспользуйтесь инструментом Определение координат.--%>
            center: [53.90, 27.56],
            <%--// Уровень масштабирования. Допустимые значения:--%>
            <%--// от 0 (весь мир) до 19.--%>
            zoom: 7
        });
       	var myGeoObject = new ymaps.GeoObject({
    	geometry: {
        type: "Point", // тип геометрии - точка
        coordinates: [55.8, 37.8] // координаты точки
    	}
		});
		var myPlacemark = new ymaps.Placemark([55.8, 37.6]);
		<%-- Размещение геообъекта на карте.--%>
		myMap.geoObjects.add(myGeoObject); 
		myMap.geoObjects.add(myPlacemark); 
 	   }
	</script>
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
            	<form:select path="stationFrom">
            		<form:option value="0" label="Select station of departure" />
            			<c:forEach var="station" items="${stationsList}">
    						<option value="${station}">"${station.name}"</option>
						</c:forEach>
            	</form:select>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="stationTo" type="text" disabled="${readonly}" />
                <form:errors path="stationTo" cssClass="red-text" />
                <label for="stationTo">Station to id</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="departure" type="text" disabled="${readonly}" />
                <form:errors path="departure" cssClass="red-text" />
                <label for="departure">Departure</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="arrival" type="text" disabled="${readonly}" />
                <form:errors path="arrival" cssClass="red-text" />
                <label for="arrival">Arrival</label>
            </div>
        </div>
        
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
                <form:input path="isActual" type="text" disabled="${readonly}" />
                <form:errors path="isActual" cssClass="red-text" />
                <label for="isActual">Actuality</label>
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
            <div class="input-field col s12">
                <form:input path="places" type="text" disabled="${readonly}" />
                <form:errors path="places" cssClass="red-text" />
                <label for="places">Number of places</label>
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
