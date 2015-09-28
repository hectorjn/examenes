
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    <h2>Inscribir Materias</h2>
                    <form id="formulario" action="usuario" method="POST" autocomplete="off">
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 5px"> </th>
                                    <th>Materia </th>
                                    <th>Profesor</th>                                    
                                    <th class="acciones">Aprobado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.materiasEstudiante}" var="materia" >
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="materia" value="${materia.idMateria}" ${materia.idEstudianteMateria gt 0? 'checked':''} >
                                        </td>
                                        <td>${materia.nombreMateria}</td>
                                        <td>${materia.nombreProfesor}</td>
                                        <c:if test="${materia.idEstudianteMateria gt 0}">
                                            <td>${materia.aprobado? 'Aprobada':'Desaprobado'}</td>   
                                        </c:if>
                                        <c:if test="${materia.idEstudianteMateria le 0}">
                                            <td>-</td>   
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table> 
                        <div>
                            <input type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="inscribir"/>
                            <input type="hidden" name="estudiante" value="${sessionScope.idEstudiante}"/>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>        
            </div>                   
        </div>  
    </body>
</html>
