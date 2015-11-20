
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    
                        <h2>Lista de Ex&aacute;menes</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Materia</th>
                                    <th>Nombre</th>
                                    <th>Fecha</th>
                                    <th>Hora</th>
                                    <c:if test="${sessionScope.usuario.tipoUsuario eq 2}">
                                         <th class="acciones">Inscritos</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.examenes}" var="examen" >
                                    <tr>
                                        <td>
                                            ${examen.nombreMateria}
                                        </td>
                                        <td>
                                            ${examen.nombre}
                                        </td>
                                        <td>
                                             ${examen.fechaString}
                                        </td>
                                        <td>
                                             ${examen.hora}
                                        </td>
                                        <c:if test="${sessionScope.usuario.tipoUsuario eq 2}">
                                            <td>
                                                 ${examen.cantidadInscritos}
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
