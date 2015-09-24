
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    
                        <h2>Lista de Estudiantes</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre </th>
                                    <th>Apellido</th>
                                    <th>Carrera</th>
                                    <th class="acciones">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.estudiantes}" var="estudiante" >
                                    <tr>
                                        <td>${estudiante.nombre}</td>
                                        <td>${estudiante.apellido}</td>
                                        <td>${estudiante.nombreCarrera}</td>
                                        <td class="acciones">
                                            <a href="./carrera?accion=editar&id=">Exámenes</a>  <br/>
                                            <a href="./carrera?accion=editar&id=">Inscribir Materias</a>  
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>   
                </div>        
            </div>                   
        </div>  
    </body>
</html>
