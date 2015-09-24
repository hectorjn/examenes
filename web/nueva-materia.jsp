
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
            <div class="content" id="content">
                <div id="form_container" class="form_container">

                    <form id="formulario" action="materia" method="POST">
                        <h2>Nueva Materia</h2>
                        <div>
                            <label>Carrera</label>
                            <select name="carrera" >
                                <option value="">- Seleccione Uno -</option>
                                <c:forEach items="${requestScope.carreras}" var="carrera" >
                                    <option value="${carrera.id}">${carrera.nombre}</option>   
                                </c:forEach>
                            </select>    
                        </div>
                        <div>
                            <label>Nombre</label>
                            <input type="text" name="materia" />
                        </div>
                        <div>
                            <label>Profesor</label>
                            <input type="text" name="profesor" />
                        </div>
                        <div>
                            <input type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="nuevo"/>
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
                        carrera:"required",
                        materia: "required",
                        profesor: "required"
                    },
                    messages: {
                        carrera: "Por favor seleccione una Carrera",
                        materia: "Por favor ingrese un Nombre de Materia",
                        profesor: "Por favor ingrese un Nombre de Profesor"
                    }
                });


            });
        </script>
    </body>
</html>
