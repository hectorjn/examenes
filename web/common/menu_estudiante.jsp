
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <body>
        <div class="header" id="header-estudiante">
            
             <div>
                <div id="logo"></div>
                <p id="text" >Inscripci&oacute;n</p>
                <p id="text" >Ex&aacute;menes Finales</p>
               
            </div>
            <div id="menu-container">
                <ul id="menu-bar">
                    <li><a href="#home">Home</a></li>
                    <li><a href="#news">Ex&aacute;menes</a>
                        <ul>
                            <li><a href="./examen?">Inscripci&oacute;n</a><li>
                            <li><a href="./examen?accion=misexamenes">Mis Ex&aacute;menes</a><li>  
                            <li><a href="./examen?accion=examenesMateria">Ex&aacute;menes por Materias</a><li>    
                        </ul>
                    </li>
                    <li><a href="./materia?accion=mismaterias">Mis Materias</a></li>
                    <li><a  class="logout" href="./login">Salir</a></li>
                  </ul>
            </div> 
            <div id="user-container">
                <label style="color: transparent; font-size: 20px">${sessionScope.usuario.nombreCompleto} </label>
            </div> 
             
        </div>    
        
    </body>
</html>
