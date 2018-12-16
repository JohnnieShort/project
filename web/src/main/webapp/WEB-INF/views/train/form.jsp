<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/train" />
<h4 class="header">Edit train</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <form:input path="version" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="locomotiveId" disabled="${readonly}" >
                	<form:option value="0" label="Select locomotive" />
					<form:options items="${modelsLocomotive}" />
				</form:select>
				<form:errors path="locomotiveId" cssClass="red-text" />
				<label for="locomotive">locomotive</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:select path="trainType" disabled="${readonly}" >
                	<form:option value="0" label="Select type" />
					<form:options items="${trainTypes}" />
				</form:select>
				<form:errors path="trainType" cssClass="red-text" />
				<label for="trainType">type of train</label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6"></div>
            	<div class="col s3">
                	<c:if test="${!readonly}">
                   	 <button class="btn waves-effect waves-light right green darken-3" type="submit">сохранить</button>
                	</c:if>
            	</div>
            	<div class="col s3">
               	 <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}">к списку<i class="material-icons right"></i>
                	</a>
            	</div>
        	</div>
        </div>
    </form:form>
</div>
