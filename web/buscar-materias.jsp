
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
            <div class="content" id="content">
                <div id="form_container" class="form_container">

                    <form id="formulario" action="materia" method="POST" autocomplete="off">
                        <h2>Buscar Materia</h2>
                        <div>
                            <label>Carrera</label>
                            <select name="carrera" >
                                <option value="0">- Seleccione Una -</option>
                                <c:forEach items="${requestScope.carreras}" var="carrera" >
                                    <option value="${carrera.id}">${carrera.nombre}</option>   
                                </c:forEach>
                            </select>    
                        </div>
                        <div>
                            <label>Nombre</label>
                            <input type="text" name="materia" value="" />
                        </div>
                        <div>
                            <label>Profesor</label>
                            <input type="text" name="profesor" value="" />
                        </div>
                        <div>
                            <input type="submit" value="Buscar"></input>
                            <input type="hidden" name="accion" value="buscar"/>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>
            </div>                   
        </div>  
       
    </body>
</html>
