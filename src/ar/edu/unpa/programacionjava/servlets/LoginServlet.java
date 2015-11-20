/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import ar.edu.unpa.programacionjava.daos.UsuarioDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.Usuario;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jn
 */
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        boolean valid = true;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        usuario.setUsername(username.toLowerCase());
        usuario.setPassword(password);
        if (username.isEmpty()) {
            request.setAttribute("nameError", "No puede estar vacio");
            valid = false;
        }
        if (password.isEmpty()) {
            request.setAttribute("passwordError", "No puede estar vacio");
            valid = false;
        }
        if (valid) {
            ExisteUsuario(usuario);
            if(usuario.getId()==null){
                request.setAttribute("loginError", "Usuario o Password Incorrectos");
                valid = false;
            }
        } 
        if(valid){
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("welcome.jsp");
        }else {
            request.setAttribute("usuario", usuario);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
    }


    private void ExisteUsuario(Usuario usuario) {
        try{
            Connection conn = ConnectionManager.getConnection();
            UsuarioDAO.getExisteUsuario(conn, usuario);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
