

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
                <div id="form_container" class="form_container">
                
                    <form id="formulario" action="examen" method="POST" autocomplete="off">
                        <h2>Nuevo Ex&aacute;men</h2>
                        <div>
                            <label>Materia</label>
                            <select name="nombre_materia" >
                                <option value="">- Seleccione Uno -</option>
                                <c:forEach items="${requestScope.materias}" var="materia" >
                                    <option value="${materia.id}">${materia.nombreCarrera} - ${materia.nombreMateria}</option>   
                                </c:forEach>
                            </select>    
                        </div>
                        <div>
                            <label>Nombre Ex&aacute;men</label>
                            <input type="text" name="nombre_examen" />
                        </div>
                        <div>
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <label>Fecha</label>
                                            <input type="text" name="fecha" id="datepicker" value ="${requestScope.usuario.username}"/>
                                        </td>
                                        <td>
                                            <label>Hora</label>
                                            <select name="hora" >
                                                <option value="">- Seleccione Uno -</option>
                                                <c:forEach  begin="0" end="23" step="1" var="hora">
                                                    <option value="${hora}:00">${hora}:00</option>   
                                                </c:forEach>
                                            </select>    

                                        </td>
                                    </tr>
                                </tbody>    
                            </table>  

                        </div>
                        <div>
                            <input class="submit" type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="nuevo"/>
                            <div class="clear"></div> 
                        </div>
                    </form>
                
            </div>  
        </div> 
        <script type="text/javascript">
            
            $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+12M +10D" });
            $(function () {
                $("#formulario").validate({
                    rules: {
                        nombre_materia: "required",
                        nombre_examen: "required",
                        fecha: "required",
                        hora: "required"
                    },
                    messages: {
                        nombre_materia: "Por favor seleccione una  Materia",
                        nombre_examen: "Por favor ingrese un Nombre de Exámen",
                        fecha: "Por favor ingrese una Fecha",
                        hora: "Por favor seleccione una hora"
                    }
                });


            });
        </script>
    </body>
</html>
