<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="baseUrl" value="${contextPath}/ticket" />

<h4 class="header">Edit Ticket</h4>


<div style="margin: 10px;">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
    <form:input path="id" type="hidden" />
    	
        <div class="row">
            <div class="input-field col s12">
                <form:select path="passengerId" disabled="${readonly}">
					<form:option value="0" label="Select passenger" />
					<form:options items="${passengerChoices}" />
				</form:select>
                <form:errors path="passengerId" cssClass="red-text" />
                <label for="passengerId">passenger id</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
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
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">Next</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${url}">back<i class="material-icons right"></i>
                </a>
            </div>
    </form:form>
</div>

<script src="${contextPath}/resources/js/init-combos.js"></script>
<script>
initComboboxes('${pageContext.request.contextPath}');
</script>