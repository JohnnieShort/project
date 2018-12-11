<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h4 class="header">Edit Ticket</h4>


<div style="margin: 10px;">
    <form:form class="col s12" method="POST" action="${pageContext.request.contextPath}/ajax-samples" modelAttribute="ticketForm">
    <form:input path="id" type="hidden" />
        <div class="row">
            <div class="col s12">
                <form:select path="passengerRouteId" id="passengerRouteId" cssClass="browser-default" />
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <form:select path="stationFromId" cssClass="browser-default" />
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <form:select path="stationToId" cssClass="browser-default" />
            </div>
        </div>
        <input type="submit" />
    </form:form>
</div>

<script src="${contextPath}/resources/js/init-combos.js"></script>
<script>
initComboboxes('${pageContext.request.contextPath}');
</script>