
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    <h2>Aprobar Estudiantes</h2>
                    <form id="formulario" action="materia" method="POST" autocomplete="off">
                        <table>
                            <thead>
                                <tr>
                                    <th>Apellido</th> 
                                    <th>Nombre </th>
                                    <th class="acciones">Aprobado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.materiasEstudiante}" var="estudiante" >
                                    <tr>
                                       
                                        <td>${estudiante.apellidoEstudiante}</td>
                                        <td>${estudiante.nombreEstudiante}</td>
                                        <td style="text-align: center">
                                            <input type="checkbox" name="estudianteMateria" value="${estudiante.idEstudianteMateria}" ${estudiante.aprobado? 'checked':''} >
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table> 
                        <div>
                            <input type="submit" value="Aprobar"></input>
                            <input type="hidden" name="accion" value="aprobar"/>
                            <input type="hidden" name="materia" value="${sessionScope.idMateria}"/>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>        
            </div>                   
        </div>  
    </body>
</html>
