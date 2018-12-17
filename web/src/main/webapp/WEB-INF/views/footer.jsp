<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<footer class="page-footer purple lighten-4">
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />	
		
	
    <div class="container ">
        <div class="row">
            
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text"><mytaglib:i18n key="footer.content.links" /></h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="https://github.com/dzhivushko/G-JD2-09-11_ikarotki">Source files</a></li>
                    <li><a class="grey-text text-lighten-3" href="https://raw.githubusercontent.com/dzhivushko/G-JD2-09-11_ikarotki/master/docs/Demorwmanager.png?token=Akhairy89If54gxJXBhmG0Sr9LXNz4CXks5cF7hqwA%3D%3D">Mind map</a></li>
                    <li><a class="grey-text text-lighten-3" href="https://raw.githubusercontent.com/dzhivushko/G-JD2-09-11_ikarotki/master/docs/DMrwmanager.png?token=AkhaivDj2YkuIuqpNDmZJ017uAuDmR7aks5cF7hDwA%3D%3D">Data model</a></li>
                    <li><a class="grey-text text-lighten-3" href="https://github.com/dzhivushko/G-JD2-09-11_ikarotki/blob/master/docs/Roles.txt">Roles</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright  purple lighten-3">
        <div class="container ">
             <mytaglib:i18n key="footer.content.copyright" />
        </div>
    </div>
</footer>