<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/customer" />
<h4 class="header"><c:if test="${!readonly}">Edit</c:if> customer</h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${baseUrl}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="userAccount" type="text" disabled="${readonly}" />
                <form:errors path="userAccount" cssClass="red-text" />
                <label for="userAccount">User account id</label>
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
                <a class="btn waves-effect waves-light right light-blue" href="${baseUrl}">к списку<i class="material-icons right"></i>
                </a>
            </div>
        </div>
    </form:form>
</div>
