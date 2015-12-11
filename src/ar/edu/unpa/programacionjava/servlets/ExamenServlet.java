/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.unpa.programacionjava.daos.ExamenDAO;
import ar.edu.unpa.programacionjava.daos.MateriaDAO;
import ar.edu.unpa.programacionjava.database.ConnectionManager;
import ar.edu.unpa.programacionjava.entities.Examen;
import ar.edu.unpa.programacionjava.entities.Materia;
import ar.edu.unpa.programacionjava.entities.Usuario;
import ar.edu.unpa.programacionjava.servlets.util.Util;

/**
 *
 *
 */
public class ExamenServlet extends HttpServlet {

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
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
				forwardNuevoExamen(request, response);
			}
			if (accion.equals("buscar")) {
				forwardBuscarExamen(request, response);
			}
			if (accion.equals("misexamenes")) {
				forwardMisExamenes(request, response);
			}
			if (accion.equals("examenesMateria")) {
				forwardExamenesPorMateria(request, response);
			}
			if (accion.equals("examenesPorInscribir")) {
				forwardExamenesPorInscribir(request, response);
			}
		}
	}

	private void forwardNuevoExamen(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		listarMateria(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nuevo-examen.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
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
				buscarExamenes(request, response);
			}
			if (accion.equals("inscribir")) {
				inscribirParaExamen(request, response);
			}

		}
	}

	private void inscribirParaExamen(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = ConnectionManager.getConnection();
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			String[] examenes = request.getParameterValues("examen");
			ExamenDAO.inscribirEstudiante(conn, usuario.getId(), examenes);
			Util.agregarMensajes(request, "Se registró la inscripción con éxito");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
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
			Util.agregarMensajes(request, "Se registró el exámen con éxito");
			forwardNuevoExamen(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void forwardBuscarExamen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listarMateria(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/buscar-examen.jsp");
		dispatcher.forward(request, response);
	}

	private void buscarExamenes(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Connection conn = ConnectionManager.getConnection();
			Examen examen = new Examen();
			examen.setMateriaId(Integer.parseInt(request.getParameter("materia")));
			examen.setNombre(request.getParameter("nombre_examen"));
			List<Examen> examenes = ExamenDAO.listarExamenes(conn, examen);
			if (examenes.size() > 0) {
				session.setAttribute("examen", examen);
				session.setAttribute("examenes", examenes);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-examenes.jsp");
				dispatcher.forward(request, response);
			} else {
				Util.agregarMensajes(request, "No se encontraron resultados");
				forwardBuscarExamen(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void forwardMisExamenes(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			Connection conn = ConnectionManager.getConnection();
			List<Examen> examenes = ExamenDAO.buscarExamenesEstudiante(conn, usuario.getId());
			if (examenes.size() > 0) {
				session.setAttribute("examenes", examenes);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-examenes.jsp");
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

	private void forwardExamenesPorMateria(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			Connection conn = ConnectionManager.getConnection();
			List<Examen> examenes = ExamenDAO.buscarExamenesPorMateria(conn, usuario.getId());
			if (examenes.size() > 0) {
				session.setAttribute("examenes", examenes);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listar-examenes.jsp");
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

	private void forwardExamenesPorInscribir(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			Connection conn = ConnectionManager.getConnection();
			List<Examen> examenes = ExamenDAO.buscarExamenesParaInscribir(conn, usuario.getId());
			if (examenes.size() > 0) {
				session.setAttribute("examenes", examenes);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/examen-por-inscribir.jsp");
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

}
