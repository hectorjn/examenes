/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;


import ar.edu.unpa.programacionjava.entities.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                + " JOIN CARRERA C ON C.CARRERA_ID = M.MATERIA_ID "
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
    
}
