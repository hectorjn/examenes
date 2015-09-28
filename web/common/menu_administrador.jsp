
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    
    <body>
       <div class="header" id="header-admin">
            <div>
                <div id="logo"></div>
                <p id="text">Inscripci&oacute;n</p>
                <p id="text">Ex&aacute;menes Finales</p>
               
            </div>
            <div id="menu-container">
                <ul id="menu-bar">
                    <li><a href="./welcome.jsp">Home</a></li>
                    <li><a href="#">Usuarios</a>
                        <ul>
                            <li><a href="./usuario?accion=nuevo">Nuevo</a><li>
                            <li><a href="./usuario?accion=buscar">Buscar Estudiantes</a><li>    
                        </ul>
                    </li>
                    <li><a href="#">Carrera</a>
                        <ul>
                            <li><a href="./nueva-carrera.jsp">Nuevo</a><li>
                            <li><a href="./carrera?accion=listar">Listar</a><li>    
                        </ul>
                    </li>
                    <li><a href="#">Materias</a>
                        <ul>
                            <li><a href="./materia?accion=nuevo">Nuevo</a><li>
                            <li><a href="./materia?accion=buscar">Buscar</a><li>    
                        </ul>
                    </li>
                    <li><a href="#about">Ex&aacute;menes</a>
                        <ul>
                            <li><a href="./examen?accion=nuevo">Nuevo</a><li>
                            <li><a href="./examen?accion=buscar">Buscar</a><li>    
                        </ul>
                    </li>
                    
                    <li class="active"><a href="./login">Salir</a></li>
                  </ul>
            </div> 
            <div id="user-container">
                <label style="color: transparent; font-size: 20px">${sessionScope.usuario.nombreCompleto} </label>
            </div> 
             
        </div>    
    </body>
</html>
