/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.EstudianteMateria;
import ar.edu.unpa.programacionjava.entities.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 */
public class MateriaDAO {

    public static boolean registrarMateria(Connection conn, Materia materia) throws SQLException {
        String laConsulta = "INSERT INTO MATERIA (NOMBRE, PROFESOR, CARRERA_ID) VALUES(?,?,?)";
        PreparedStatement stmtConsulta = null;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setString(1, materia.getNombreMateria());
            stmtConsulta.setString(2, materia.getNombreProfesor());
            stmtConsulta.setInt(3, materia.getIdCarrera());
            System.out.println(laConsulta);
            stmtConsulta.executeUpdate();
        } catch (Exception ex) {
            return false;
        } finally {
            // Cierra el Statement y la Connection
            stmtConsulta.close();
            conn.close();

        }
        return true;
    }

    public static List<Materia> obtenerTodos(Connection conn) throws SQLException {
        String laConsulta = "SELECT M.*, C.NOMBRE NOMBRE_C  FROM MATERIA M INNER"
                + " JOIN CARRERA C ON C.CARRERA_ID = M.CARRERA_ID "
                + " ORDER BY C.NOMBRE ASC, M.NOMBRE ASC";
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
        System.out.println(laConsulta);
        // Obtiene los datos
        List<Materia> materias = new ArrayList<Materia>();
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setId(rs.getInt("materia_id"));
            materia.setNombreMateria(rs.getString("nombre"));
            materia.setNombreProfesor(rs.getString("profesor"));
            materia.setIdCarrera(rs.getInt("carrera_id"));
            materia.setNombreCarrera(rs.getString("NOMBRE_C"));
            materias.add(materia);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return materias;
    }

    public static List<EstudianteMateria> listarParaInscripcion(Connection conn, Integer idEstudiante) throws SQLException {
        String laConsulta = "SELECT M.*,U.USER_ID, U.NOMBRE AS NOMBRE_E, U.APELLIDO,\n"
                + "		C.NOMBRE AS NOMBRE_C, EM.ID, EM.APROBADO\n"
                + "FROM MATERIA M\n"
                + "INNER JOIN CARRERA C ON C.CARRERA_ID =M.CARRERA_ID\n"
                + "INNER JOIN USUARIO U ON U.CARRERA_ID = C.CARRERA_ID\n"
                + "LEFT  JOIN ESTUDIANTE_MATERIA EM ON M.MATERIA_ID = EM.MATERIA_ID \n"
                + "				AND EM.ESTUDIANTE_ID = U.USER_ID \n"
                + "WHERE U.USER_ID = ?";

        System.out.println(laConsulta);
        PreparedStatement stmtConsulta = conn.prepareStatement(laConsulta);
        stmtConsulta.setInt(1, idEstudiante);
        ResultSet rs = stmtConsulta.executeQuery();

        // Obtiene los datos
        List<EstudianteMateria> resultado = new ArrayList<EstudianteMateria>();
        while (rs.next()) {
            EstudianteMateria estudianteMateria = new EstudianteMateria();
            estudianteMateria.setApellidoEstudiante(rs.getString("APELLIDO"));
            estudianteMateria.setAprobado(rs.getBoolean("APROBADO"));
            estudianteMateria.setIdEstudiante(rs.getInt("USER_ID"));
            estudianteMateria.setIdEstudianteMateria(rs.getInt("ID"));
            estudianteMateria.setIdMateria(rs.getInt("MATERIA_ID"));
            estudianteMateria.setNombreEstudiante(rs.getString("NOMBRE_E"));
            estudianteMateria.setNombreMateria(rs.getString("NOMBRE"));
            estudianteMateria.setNombreProfesor(rs.getString("PROFESOR"));
            resultado.add(estudianteMateria);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return resultado;
    }

    public static List<Materia> buscarMaterias(Connection conn, Materia _materia) throws SQLException {
        String laConsulta = "SELECT M.*, C.NOMBRE NOMBRE_C  FROM MATERIA M INNER"
                + " JOIN CARRERA C ON C.CARRERA_ID = M.CARRERA_ID "
                + " WHERE MATERIA_ID IS NOT NULL ";

        if (_materia.getIdCarrera() > 0) {
            laConsulta += " AND M.CARRERA_ID = " + _materia.getIdCarrera();
        }
        if (_materia.getNombreMateria()!=null && !_materia.getNombreMateria().isEmpty()) {
            laConsulta += " AND M.NOMBRE LIKE '%" + _materia.getNombreMateria() + "%'";
        }
        if (_materia.getNombreProfesor()!=null && !_materia.getNombreProfesor().isEmpty()) {
            laConsulta += " AND PROFESOR LIKE '%" + _materia.getNombreProfesor() + "%'";
        }
        laConsulta += " ORDER BY C.NOMBRE ASC, M.NOMBRE ASC";
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
        System.out.println(laConsulta);
        // Obtiene los datos
        List<Materia> materias = new ArrayList<Materia>();
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setId(rs.getInt("materia_id"));
            materia.setNombreMateria(rs.getString("nombre"));
            materia.setNombreProfesor(rs.getString("profesor"));
            materia.setIdCarrera(rs.getInt("carrera_id"));
            materia.setNombreCarrera(rs.getString("NOMBRE_C"));
            materias.add(materia);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return materias;
    }

    public static List<EstudianteMateria> listarInscritosPorMateria(Connection conn, Integer idMateria) throws SQLException {
        String laConsulta = "SELECT M.*,U.USER_ID, U.NOMBRE AS NOMBRE_E, U.APELLIDO,\n"
                + "		C.NOMBRE AS NOMBRE_C, EM.ID, EM.APROBADO\n"
                + "FROM MATERIA M\n"
                + "INNER JOIN CARRERA C ON C.CARRERA_ID =M.CARRERA_ID\n"
                + "INNER JOIN USUARIO U ON U.CARRERA_ID = C.CARRERA_ID\n"
                + "INNER JOIN ESTUDIANTE_MATERIA EM ON M.MATERIA_ID = EM.MATERIA_ID \n"
                + "				AND EM.ESTUDIANTE_ID = U.USER_ID \n"
                + "WHERE M.MATERIA_ID = ?";

        System.out.println(laConsulta);
        PreparedStatement stmtConsulta = conn.prepareStatement(laConsulta);
        stmtConsulta.setInt(1, idMateria);
        ResultSet rs = stmtConsulta.executeQuery();

        // Obtiene los datos
        List<EstudianteMateria> resultado = new ArrayList<EstudianteMateria>();
        while (rs.next()) {
            EstudianteMateria estudianteMateria = new EstudianteMateria();
            estudianteMateria.setApellidoEstudiante(rs.getString("APELLIDO"));
            estudianteMateria.setAprobado(rs.getBoolean("APROBADO"));
            estudianteMateria.setIdEstudiante(rs.getInt("USER_ID"));
            estudianteMateria.setIdEstudianteMateria(rs.getInt("ID"));
            estudianteMateria.setIdMateria(rs.getInt("MATERIA_ID"));
            estudianteMateria.setNombreEstudiante(rs.getString("NOMBRE_E"));
            estudianteMateria.setNombreMateria(rs.getString("NOMBRE"));
            estudianteMateria.setNombreProfesor(rs.getString("PROFESOR"));
            resultado.add(estudianteMateria);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return resultado;
    }

    public static void actualizarAprobaciones(Connection conn, String[] estudiantes, Integer idMateria) throws SQLException {

        PreparedStatement stmtConsulta = null;
        try {
            conn.setAutoCommit(Boolean.FALSE);
            String laConsulta = "UPDATE ESTUDIANTE_MATERIA SET  APROBADO=? WHERE MATERIA_ID=?";
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setBoolean(1, Boolean.FALSE);
            stmtConsulta.setInt(2, idMateria);
            System.out.println(laConsulta);
            stmtConsulta.executeUpdate();
            if (estudiantes != null && estudiantes.length > 0) {
                String estudiantesAprobados = Arrays.toString(estudiantes).replaceAll("\\[|\\]", "");
                laConsulta = "UPDATE ESTUDIANTE_MATERIA SET  APROBADO=? WHERE MATERIA_ID=? AND ID IN (?)";
                stmtConsulta = conn.prepareStatement(laConsulta);
                stmtConsulta.setBoolean(1, Boolean.TRUE);
                stmtConsulta.setInt(2, idMateria);
                stmtConsulta.setString(3, estudiantesAprobados);
                System.out.println(laConsulta);
                stmtConsulta.executeUpdate();
            }
            stmtConsulta.close();
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();

        } finally {
            // Cierra el Statement y la Connection
            conn.setAutoCommit(Boolean.TRUE);
            conn.close();
        }

    }

    public static List<Materia> buscarMateriasEstudiante(Connection conn, Integer idEstudiante) throws SQLException {
        String laConsulta = "SELECT M.*,  C.NOMBRE NOMBRE_C  FROM MATERIA M "
                + " INNER JOIN ESTUDIANTE_MATERIA EM ON M.MATERIA_ID = EM.MATERIA_ID \n"
                + " INNER JOIN CARRERA C ON C.CARRERA_ID =M.CARRERA_ID\n"
                + " WHERE EM.ESTUDIANTE_ID = ?"
                + " ORDER BY M.NOMBRE ASC";
        System.out.println(laConsulta);
        PreparedStatement stmtConsulta = conn.prepareStatement(laConsulta);
        stmtConsulta.setInt(1, idEstudiante);
        ResultSet rs = stmtConsulta.executeQuery();
        // Obtiene los datos
        List<Materia> materias = new ArrayList<Materia>();
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setId(rs.getInt("materia_id"));
            materia.setNombreMateria(rs.getString("nombre"));
            materia.setNombreProfesor(rs.getString("profesor"));
            materia.setIdCarrera(rs.getInt("carrera_id"));
            materia.setNombreCarrera(rs.getString("NOMBRE_C"));
            materias.add(materia);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return materias;
    }

}
