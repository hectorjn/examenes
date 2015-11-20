/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 */
public class Util {
    
    public static void agregarMensajes(HttpServletRequest request, String ... mensaje ){
    
        List<String> mensajes = (ArrayList<String>)request.getAttribute("mensajes");
        if(mensajes == null){
            mensajes = new ArrayList<String>();
        }
        for(String mess : mensaje){
            mensajes.add(mess);
        }
        request.setAttribute("mensajes", mensajes);
    
    }
    
}
