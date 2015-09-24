
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">
            <h1>Inscripci&oacute;n a Ex&aacute;menes Finales </h1>
            <h2>Demo by:<span>Alejandra Cabrera </span> </h2>
            <div class="content">
                <div id="form_wrapper" class="form_wrapper">
                    <form class="login active" action="login" method="POST">
                        <h3>Login</h3>
                        <div>
                            <label>Usuario:</label>
                            <input type="text" name="username" value ="${requestScope.usuario.username}"/>
                            
                            <c:if test="${requestScope.nameError ne null}">
                                <span class="error"><c:out value="${requestScope.nameError}"/></span>
                            </c:if>    
                        </div>
                        <div>
                            <label>Contrase&ntilde;a: </label>
                            <input type="password"  name="password" value="${requestScope.usuario.password}"/>
                            <c:if test="${requestScope.passwordError ne null}">
                                <span class="error"><c:out value="${requestScope.passwordError}"/></span>
                            </c:if>   
                        </div>
                       <c:if test="${requestScope.loginError ne null}">
                                <span class="error"><c:out value="${requestScope.loginError}"/></span>
                            </c:if>  
                        <div class="bottom">
                            <input type="submit" value="Ingresar"></input>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
