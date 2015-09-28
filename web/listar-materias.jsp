
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    
                        <h2>Lista de Materias</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Carrera</th>
                                    <th>Profesor</th>
                                    <c:if test="${sessionScope.usuario.tipoUsuario eq 2}">
                                        <th class="acciones">Acciones</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.materias}" var="materia" >
                                    <tr>
                                        <td>
                                            ${materia.nombreMateria}
                                        </td>
                                        <td>
                                             ${materia.nombreCarrera}
                                        </td>
                                        <td>
                                             ${materia.nombreProfesor}
                                        </td>
                                        <c:if test="${sessionScope.usuario.tipoUsuario eq 2}">
                                            <td class="acciones">
                                                <a href="./materia?accion=estudiantes&materia=${materia.id}">Aprobar Estudiantes</a> 
                                                <a href="./materia?accion=examenesPorMateria&materia=${materia.id}">Exámenes</a>  
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>   
                </div>        
            </div>                   
        </div>  
    </body>
</html>
