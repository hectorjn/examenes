
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
            <div class="content" id="content">
                <div id="form_container" class="form_container">

                    <form id="formulario" action="carrera" method="POST">
                        <h2>Nueva Carrera</h2>
                        <div>
                            <label>Nombre</label>
                            <input type="text" name="carrera" value="${requestScope.carrera.nombre}" />
                        </div>
                        
                        <div>
                            <input type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="${requestScope.accion ne null?requestScope.accion:'nuevo' }"/>
                            <input type="hidden" name="id" value="${requestScope.carrera.id}"/>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>
            </div>                   
        </div>  
        <script type="text/javascript">

            $(function () {
                $("#formulario").validate({
                    rules: {
                        carrera: "required"
                    },
                    messages: {
                        carrera: "Por favor ingrese un Nombre de Carrera"
                    }
                });
            });
        </script>
    </body>
</html>
