/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import ar.edu.unpa.programacionjava.daos.CarreraDAO;
import ar.edu.unpa.programacionjava.daos.UsuarioDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.Carrera;
import ar.edu.unpa.programacionjava.entities.Usuario;
import ar.edu.unpa.programacionjava.servlets.util.Util;
import com.mysql.jdbc.EscapeTokenizer;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *
 */
public class UsuarioServlet extends HttpServlet {

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
        String accion = request.getParameter("accion");
        if (accion == null) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            if (accion.equals("nuevo")) {
                forwardNuevoUsuario(request, response);
            }
            if (accion.equals("buscar")) {
                forwardBuscar(request, response);
            
            }
            
        }
    }

    private void forwardBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        listarCarreras(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/buscar-estudiante.jsp");
        dispatcher.forward(request,response);
    }

    private void forwardNuevoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarCarreras(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nuevo-usuario.jsp");
        dispatcher.forward(request,response);
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
        String accion = request.getParameter("accion");
        if (accion == null) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            if (accion.equals("nuevo")) {
                registarNuevo(request, response);
            }
            if (accion.equals("buscar")) {
                buscar(request, response);
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void registarNuevo(HttpServletRequest request, HttpServletResponse response) {
        boolean resultado = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            Usuario usuario = new Usuario();
            usuario.setUsername(request.getParameter("username"));
            usuario.setPassword(request.getParameter("password"));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setDni(request.getParameter("dni"));
            usuario.setTipoUsuario(Integer.parseInt(request.getParameter("tipo")));
            if(usuario.getTipoUsuario() == Usuario.ESTUDIANTE){
                usuario.setIdCarrera(Integer.parseInt(request.getParameter("carrera")));
            }
            resultado = UsuarioDAO.registrarUsuario(conn, usuario);
            Util.agregarMensajes(request, "Se registó el usuario con éxito");
            forwardNuevoUsuario(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void listarCarreras(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection conn = ConnectionManager.getConnection();
            List<Carrera> carreras = CarreraDAO.obtenerTodos(conn);
            request.setAttribute("carreras", carreras);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection conn = ConnectionManager.getConnection();
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setApellido(request.getParameter("apellido"));
            usuario.setDni(request.getParameter("dni"));
            usuario.setIdCarrera(Integer.parseInt(request.getParameter("carrera")));
            List<Usuario> estudiantes = UsuarioDAO.buscarEstudiantes(conn, usuario);
            if(estudiantes.size() >0){
                request.setAttribute("estudiantes", estudiantes);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-estudiantes.jsp");
                dispatcher.forward(request,response);
            }else{
                Util.agregarMensajes(request, "No se encontraron registros");
                forwardBuscar(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
