<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType"%>
<c:set var="baseUrl" value="${contextPath}/passengerRoute" />
<c:set var="scheduleUrl" value="${contextPath}/schedule" />
<c:set var="itemUrl" value="${contextPath}/routeItem" />
<h4 class="header">Details</h4>
<div class="row">
    <div class="row">
        <div class="col s8">
            <div id="map" style="width: 600px; height: 400px"></div>
        </div>
        <div class="col s4">
            <ul class="collection with-header">
                <li class="collection-header"><h4>Route items</h4></li>
                <c:forEach var="item" items="${routeItems}" varStatus="loopCounter">
                    <li class="collection-item"><div>
                            "${item.value}"<a href="${itemUrl}/${item.key}" class="secondary-content"><i class="material-icons">info</i></a>
                        </div></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <form:form class="col s12" method="POST" action="${baseUrl}/addItem" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:select path="passengerRouteType" disabled="${readonly}">
                    <form:option value="0" label="Select type of route" />
                    <form:options items="${passengerRouteTypes}" />
                </form:select>
                <form:errors path="passengerRouteType" cssClass="red-text" />
                <label for=passengerRouteType>Type</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="frequency" disabled="${readonly}">
                    <form:option value="0" label="Select frequency" />
                    <form:options items="${frequency}" />
                </form:select>
                <form:errors path="frequency" cssClass="red-text" />
                <label for=frequency>frequency</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <select id="from">
                    <c:forEach var="myMap" items="${fromItems}">
                        <option label="from" value="${myMap.key}"><c:out value="${myMap.value}" /></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-field col s6">
                <select id="to">
                <c:forEach var="myMap" items="${toItems}">
                        <option label="from" value="${myMap.key}"><c:out value="${myMap.value}" /></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col s6">Price: <span id="price">0</span><span>руб.</span></div>
            <div class="col s3">
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${scheduleUrl}">к расписанию<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
<script src="${contextPath}/resources/js/init-map.js"></script>
<script src="${contextPath}/resources/js/route-details.js"></script>
<script type="text/javascript">
//     var points=${points};
//     var avgLat=${avgLat};
// 	var avgLong=${avgLong};
initMap(${points},${avgLat} ,${avgLong})
	
initComboboxes('${pageContext.request.contextPath}')
</script>