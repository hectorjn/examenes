
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                    
                        <h2>Lista de Carreras</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de carrera</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.carreras}" var="carrera" >
                                    <tr>
                                        <td>
                                            ${carrera.nombre}
                                        </td>
                                        <td>
                                            <a href="./carrera?accion=editar&id=${carrera.id}">Editar</a>  
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
