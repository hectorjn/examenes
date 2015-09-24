
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <body>
        <div class="header" id="header-estudiante">
            
             <div>
                <div id="logo"></div>
                <p id="text" contenteditable="true" spellcheck="false">Inscripci&oacute;n</p>
                <p id="text" contenteditable="true" spellcheck="false">Ex&aacute;menes Finales</p>
               
            </div>
            <div id="menu-container">
                <ul id="menu-bar">
                    <li><a href="#home">Home</a></li>
                    <li><a href="#news">Ex&aacute;men Final</a>
                        <ul>
                            <li><a href="./newuser.jsp">Inscripci&oacute;n</a><li>
                            <li><a href="#home">Consultar Inscritos</a><li>  
                            <li><a href="#home">Fechas de Ex&aacute;menes</a><li>    
                        </ul>
                    </li>
                    <li><a href="#contact">Materias</a></li>
                    <li><a href="#about">Ex&aacute;menes</a></li>
                    <li><a  class="logout" href="./login">Salir</a></li>
                  </ul>
            </div> 
            <div id="user-container">
                <label style="color: #fefd57">${sessionScope.usuario.nombreCompleto} </label>
            </div> 
             
        </div>    
        
    </body>
</html>
