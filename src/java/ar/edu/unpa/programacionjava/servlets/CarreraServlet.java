/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import ar.edu.unpa.programacionjava.servlets.util.Util;
import ar.edu.unpa.programacionjava.daos.CarreraDAO;
import ar.edu.unpa.programacionjava.daos.MateriaDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.Carrera;
import ar.edu.unpa.programacionjava.entities.Materia;
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
public class CarreraServlet extends HttpServlet {


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
        String accion = request.getParameter("accion");
        if (accion == null) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            if (accion.equals("listar")) {
                listarCarreras(request, response);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-carreras.jsp");
                dispatcher.forward(request,response);
            }
            if(accion.equals("editar")) {
                editarCarrera(request, response);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nueva-carrera.jsp");
                dispatcher.forward(request,response);
            }
            if (accion.equals("materias")) {
                listarMaterias(request, response);
            }
        }
       
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
            if (accion.equals("editar")) {
                actualizar(request, response);
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
        try{
            Connection conn = ConnectionManager.getConnection();
            Carrera carrera= new Carrera();
            carrera.setNombre(request.getParameter("carrera"));
            CarreraDAO.registrarNuevo(conn, carrera);
            Util.agregarMensajes(request, "Se registró la carrera con éxito");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nueva-carrera.jsp");
            dispatcher.forward(request,response);
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

    private void editarCarrera(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection conn = ConnectionManager.getConnection();
            Integer id = Integer.valueOf(request.getParameter("id"));
            Carrera carrera = CarreraDAO.obtener(conn,id);
            request.setAttribute("carrera", carrera);
            request.setAttribute("accion", "editar");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        try{
            Connection conn = ConnectionManager.getConnection();
            Carrera carrera= new Carrera();
            carrera.setNombre(request.getParameter("carrera"));
            carrera.setId(Integer.valueOf(request.getParameter("id")));
            CarreraDAO.actualizar(conn, carrera);
            Util.agregarMensajes(request, "Se actualizó la carrera con éxito");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nueva-carrera.jsp");
            dispatcher.forward(request,response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
       private void listarMaterias(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("materias", null);
            session.setAttribute("materias", null);
            Connection conn = ConnectionManager.getConnection();
            Materia materia = new Materia();
            materia.setIdCarrera(Integer.valueOf(request.getParameter("carrera")));
            List<Materia> materias = MateriaDAO.buscarMaterias(conn, materia);
            if (materias.size() > 0) {
                session.setAttribute("materia", materia);
                session.setAttribute("materias", materias);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-materias.jsp");
                dispatcher.forward(request, response);
            } else {
                Util.agregarMensajes(request, "No se encontraron registros");
                listarCarreras(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
