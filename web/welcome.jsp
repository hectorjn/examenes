

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>

        <div class="content" id="content">
            <div id="form_container" class="form_container">
                <br/>
                <br/>
                <br/>
                <br/>
                <form>
                    <h2>Bienvenido:
                        <span><c:out value="${sessionScope.usuario.nombreCompleto}"/> </span> 
                    </h2>
                </form>
            </div>
        </div>  

    </body>
</html>
