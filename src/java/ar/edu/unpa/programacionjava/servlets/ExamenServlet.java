/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import ar.edu.unpa.programacionjava.daos.ExamenDAO;
import ar.edu.unpa.programacionjava.daos.MateriaDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.Examen;
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
public class ExamenServlet extends HttpServlet {

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
            if (accion.equals("nuevo")) {
                listarMateria(request, response);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nuevo-examen.jsp");
                dispatcher.forward(request,response);
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

    private void listarMateria(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection conn = ConnectionManager.getConnection();
            List<Materia> materias = MateriaDAO.obtenerTodos(conn);
            request.setAttribute("materias", materias);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void registarNuevo(HttpServletRequest request, HttpServletResponse response) {
          boolean resultado = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            Examen examen = new Examen();
            examen.setMateriaId(Integer.parseInt(request.getParameter("nombre_materia")));
            examen.setNombre(request.getParameter("nombre_examen"));
            examen.setFechaString(request.getParameter("fecha"));
            examen.setHora(request.getParameter("hora"));
            resultado = ExamenDAO.registrarNuevo(conn, examen);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
