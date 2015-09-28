

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
                        <h2>Buscar Ex&aacute;men</h2>
                        <div>
                            <label>Materia</label>
                            <select name="materia" >
                                <option value="0">- Seleccione Uno -</option>
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
                            <input class="submit" type="submit" value="Buscar"></input>
                            <input type="hidden" name="accion" value="buscar"/>
                            <div class="clear"></div> 
                        </div>
                    </form>
                
            </div>  
        </div> 
       
    </body>
</html>
