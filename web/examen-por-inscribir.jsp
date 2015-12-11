
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%@include file="common/header.jsp" %>
        <div class="wrapper">

            <div  class="form_container">
                <div class="data_container">
                	<h2>Lista de Ex&aacute;menes</h2>
                	<form id="formulario" action="examen" method="POST" autocomplete="off">
                        <table>
                            <thead>
                                <tr>
                               		<th style="width: 5px"> </th>
                                    <th>Materia</th>
                                    <th>Nombre</th>
                                    <th>Fecha</th>
                                    <th>Hora</th>
                               </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.examenes}" var="examen" >
                                    <tr>
                                     	<td>
                                            <input type="checkbox" name="examen" value="${examen.id}"  >
                                        </td>
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
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table> 
                        <div>
                        	<label for="examen" class="error"></label>
                            <label>DNI</label>
                            <input type="text" id="dni" name="dni" value=""/>
                        </div>
                         <div>
                         	<input type="submit" value="Registrar"></input>
                            <input type="hidden" name="accion" value="inscribir"/>
                            <input type="hidden" name="estudiante" value="${sessionScope.usuario.id}"/>
                            <input type="hidden" id="dni_" name="dni_" value="${sessionScope.usuario.dni}"/>
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
                        dni:{
                        	required:true,
                        	equalTo: "#dni_"
                        },
                        examen: {
        					required: true,
        					minlength: 1
        				}
                    },
                    messages: {
                        dni: {
                        		required:"Por favor ingrese su DNI",
                        		equalTo:"El DNI ingresado no coincide con el registrado en el sistema"
                        	},
                        examen:"Por favor seleccione al menos un examen"	
                    }
                });
            });
        </script>
    </body>
</html>
