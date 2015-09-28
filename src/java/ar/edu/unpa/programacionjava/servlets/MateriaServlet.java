/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import ar.edu.unpa.programacionjava.daos.CarreraDAO;
import ar.edu.unpa.programacionjava.daos.ExamenDAO;
import ar.edu.unpa.programacionjava.daos.MateriaDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.*;
import ar.edu.unpa.programacionjava.servlets.util.Util;
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
public class MateriaServlet extends HttpServlet {

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
                forwardNuevaMateria(request, response);
            }
            if (accion.equals("buscar")) {
                forwardBuscar(request, response);
            }
            if (accion.equals("estudiantes")) {
                listarEstudiantes(request, response);
            }
            if (accion.equals("mismaterias")) {
                misMaterias(request, response);
            }
            if (accion.equals("examenesPorMateria")) {
                forwardBuscarExamenes(request, response);
            }

        }

    }

    private void forwardNuevaMateria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarCarreras(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nueva-materia.jsp");
        dispatcher.forward(request, response);
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
            if (accion.equals("aprobar")) {
                aprobar(request, response);
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
            Materia materia = new Materia();
            materia.setNombreMateria(request.getParameter("materia"));
            materia.setNombreProfesor(request.getParameter("profesor"));
            materia.setIdCarrera(Integer.valueOf(request.getParameter("carrera")));
            resultado = MateriaDAO.registrarMateria(conn, materia);
            Util.agregarMensajes(request, "Se registró la materia con éxito");
            forwardNuevaMateria(request, response);
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

    private void forwardBuscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listarCarreras(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/buscar-materias.jsp");
        dispatcher.forward(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("materias", null);
            session.setAttribute("materias", null);
            Connection conn = ConnectionManager.getConnection();
            Materia materia = new Materia();
            materia.setNombreMateria(request.getParameter("materia"));
            materia.setNombreProfesor(request.getParameter("profesor"));
            materia.setIdCarrera(Integer.valueOf(request.getParameter("carrera")));
            List<Materia> materias = MateriaDAO.buscarMaterias(conn, materia);
            if (materias.size() > 0) {
                session.setAttribute("materia", materia);
                session.setAttribute("materias", materias);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-materias.jsp");
                dispatcher.forward(request, response);
            } else {
                Util.agregarMensajes(request, "No se encontraron registros");
                forwardBuscar(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void listarEstudiantes(HttpServletRequest request, HttpServletResponse response) {
        Integer idMateria = Integer.parseInt(request.getParameter("materia"));
        try {
            HttpSession session = request.getSession();
            Connection conn = ConnectionManager.getConnection();
            List<EstudianteMateria> materiasEstudiante = MateriaDAO.listarInscritosPorMateria(conn, idMateria);
            if (materiasEstudiante.size() > 0) {
                request.setAttribute("materiasEstudiante", materiasEstudiante);
                session.setAttribute("idMateria", idMateria);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/aprobar-estudiantes.jsp");
                dispatcher.forward(request, response);

            } else {
                Util.agregarMensajes(request, "No se encontraron registros");
                conn = ConnectionManager.getConnection();
                Materia materia = (Materia) session.getAttribute("materia");
                List<Materia> materias = MateriaDAO.buscarMaterias(conn, materia);
                session.setAttribute("materias", materias);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-materias.jsp");;
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void aprobar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String[] estudiantes = request.getParameterValues("estudianteMateria");
            Integer idMateria = Integer.parseInt(request.getParameter("materia"));
            Connection conn = ConnectionManager.getConnection();
            MateriaDAO.actualizarAprobaciones(conn, estudiantes, idMateria);
            Util.agregarMensajes(request, "Se registró la aprobación de Materias");

        } catch (Exception e) {
            e.printStackTrace();
        }
        listarEstudiantes(request, response);
    }

    private void misMaterias(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Connection conn = ConnectionManager.getConnection();
            List<Materia> materias = MateriaDAO.buscarMateriasEstudiante(conn, usuario.getId());
            if (materias.size() > 0) {
                session.setAttribute("materias", materias);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-materias.jsp");
                dispatcher.forward(request, response);
            } else {
                Util.agregarMensajes(request, "No se encontraron registros");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     private void forwardBuscarExamenes(HttpServletRequest request, HttpServletResponse response) {
       try {
            HttpSession session = request.getSession();
            Connection conn = ConnectionManager.getConnection();
            Examen examen = new Examen();
            examen.setMateriaId(Integer.parseInt(request.getParameter("materia")));
            List<Examen> examenes = ExamenDAO.listarExamenes(conn, examen);
            if(examenes.size()>0){
                session.setAttribute("examen", examen);
                session.setAttribute("examenes", examenes);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-examenes.jsp");
                dispatcher.forward(request, response);
            }else{
                conn = ConnectionManager.getConnection();
                Materia materia = (Materia) session.getAttribute("materia");
                List<Materia> materias = MateriaDAO.buscarMaterias(conn, materia);
                session.setAttribute("materias", materias);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-materias.jsp");;
                dispatcher.forward(request, response);
            }
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
