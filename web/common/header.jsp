

<%@page import="ar.edu.unpa.programacionjava.entities.Usuario"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/menu.css" />
        <link rel="stylesheet" href="css/jquery-ui.css"/>
        <script src="js/jquery.js"></script>
        <script src="js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <script type="text/javascript">
                    
              $.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: '<Ant',
                nextText: 'Sig>',
                currentText: 'Hoy',
                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
                dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
                };
                $.datepicker.setDefaults($.datepicker.regional['es']);
            $(function() {
              $( "#datepicker" ).datepicker({
                showOtherMonths: true,
                selectOtherMonths: true
              });
            });
            
            function hideAlert(){
                $('#lightbox').hide();
                $('.dialog').hide();
            }
            
        </script>
    </head>
    <body>
        <% 
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            if(usuario == null || usuario.getId() ==null){
                session.invalidate();
                response.sendRedirect("index.jsp");
                return;
            }
        %>
        <c:choose>
            <c:when test="${sessionScope.usuario.tipoUsuario eq 1}">
                <c:import url="common/menu_estudiante.jsp"/>
            </c:when>
            <c:when test="${sessionScope.usuario.tipoUsuario eq 2}">
                <c:import url="common/menu_administrador.jsp"/>
            </c:when>
            <c:otherwise>
                <c:redirect url="index.jsp"/>
            </c:otherwise>
            
        </c:choose>
        <c:if test="${not empty requestScope.mensajes}">
            <div id="lightbox"> </div>
            <div class="dialog">
                <div class="ui-dialog-titlebar">
                    <span class="ui-dialog-title">Atenci&oacute;n</span>
                </div>
                <div class="ui-dialog-content">
                    <c:forEach items="${requestScope.mensajes}" var="mensaje" >
                        <label>${mensaje}</label>  
                    </c:forEach>

                </div>
                <div class="ui-dialog-buttons">
                    <input type="button" value="Aceptar" onclick="hideAlert()"></input>
                </div>
            </div>
        </c:if>
    </body>
</html>
