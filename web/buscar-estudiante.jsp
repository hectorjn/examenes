
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
            <div class="content" id="content">
                <div id="form_container" class="form_container">

                   <form id="formulario" action="usuario" method="POST">
                       <h2>Buscar Estudiante</h2>
                        <div>
                            <label>Nombre</label>
                            <input type="text" name="nombre" value =""/>
                        </div>
                        <div>
                            <label>Apellido</label>
                            <input type="text" name="apellido" value =""/>
                        </div>
                        <div>
                            <label>DNI</label>
                            <input type="text" name="dni" value =""/>
                        </div>
                        
                        <div id="carrera"  >
                            <label>Carrera</label>
                            <select name="carrera" >
                                <option value="0">- Seleccione Uno -</option>
                                <c:forEach items="${requestScope.carreras}" var="carrera" >
                                    <option value="${carrera.id}">${carrera.nombre}</option>   
                                </c:forEach>
                            </select>    
                        </div>
                        <div class="bottom">
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
