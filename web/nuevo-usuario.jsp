
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META HTTP-EQUIV="Expires" CONTENT="no-cache">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">
            <div class="content" id="content">
                <div id="form_container" class="form_container box">

                    <form id="formulario" action="usuario" method="POST" autocomplete="off">
                        <h2>Nuevo Usuario</h2>
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
                        <div>
                            <label>Password</label>
                            <input type="text" name="password" value =""/>
                        </div>
                        <div>
                            <label>Nombre Usuario</label>
                            <input type="text" name="username" value =""/>
                        </div>
                        <div>
                            <label>Tipo</label>
                            <select name="tipo" id="tipo" onchange="toggleCarrera(this);">
                                <option value="1">Estudiante </option>
                                <option value="2">Administrador</option>                   
                            </select>
                        </div>
                        <div id="carrera"  >
                            <label>Carrera</label>
                            <select name="carrera" >
                                <option value="">- Seleccione Uno -</option>
                                <c:forEach items="${requestScope.carreras}" var="carrera" >
                                    <option value="${carrera.id}">${carrera.nombre}</option>   
                                </c:forEach>
                            </select>    
                        </div>
                        <div class="bottom">
                            <input type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="nuevo"/>
                            <div class="clear"></div>        
                        </div>
                    </form>
                </div>
            </div>  
        </div>
        <script type="text/javascript">

            $(function () {
                $("#formulario").validate({
                    rules: {
                        nombre: "required",
                        apellido: "required",
                        dni: { required: true,
				minlength: 3,
				maxlength: 15,
				number: true},
                        password: "required",
                        username: "required",
                        carrera :{
                                required: function(element) {
                                    return $("#tipo").val() === '1';
                                }
                        }    
                    },
                    messages: {
                        nombre: "Por favor ingrese un Nombre",
                        apellido: "Por favor ingrese un Apellido",
                        dni: "Por favor ingrese un DNI válido",
                        password: "Por favor ingrese un Password",
                        username: "Por favor ingrese un Nombre de Usuario", 
                        carrera:"Por favor seleccione una carrera"
                    }
                });
            });
            
            function toggleCarrera(elem){
                if(elem.value && elem.value === '1'){
                    $("#carrera").show(); 
                }else{
                    $("#carrera").hide(); 
                }                
                
            }
        </script>
    </body>
</html>