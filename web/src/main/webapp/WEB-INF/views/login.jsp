<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<h2>Login with Username and Password</h2>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <form name='loginForm' action="<c:url value='login' />" method='POST'>
            <div class="row">
                <div class="input-field col s12 center">
                    <input type='text' name='username' value=''> <label for="username">E-Mail:</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 center">
                    <input type='password' name='password' /><label for="password">Password:</label>
                </div>
            </div>
            <c:if test="${not empty error}">
                <div class="row">
                    <div class="col s12 center">
                        <div class="error">${error}</div>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="row">
                    <div class="col s12 center">
                        <div class="msg">${msg}</div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col s12 center">
                    <button class="btn waves-effect waves-light " type="submit">войти</button>
                </div>
            </div>
            <div class="row">
                <div class="col s12 center">
                    <a href="${baseUrl}/register">Register</a>
                </div>
            </div>
        </form>
    </div>
    <div class="col s3"></div>
</div>
