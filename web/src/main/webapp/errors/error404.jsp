<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}" />
<html><title>Error Page</title>
	<body>
		Request from ${pageContext.errorData.requestURI} is failed
		<br/>
		Servlet name: ${pageContext.errorData.servletName}
		<br/>
		Status code: ${pageContext.errorData.statusCode}
		<br/>
		Exception: ${pageContext.exception}
		<br/>
		Message from exception: ${pageContext.exception.message}
	</body>
</html>
нееееееееееету

<a class="btn waves-effect waves-light right" href="${baseUrl}">back<i class="material-icons right"></i>
                </a>