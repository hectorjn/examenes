/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.Examen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * 
 */
public class ExamenDAO {

    public static boolean registrarNuevo(Connection conn, Examen examen) throws SQLException {
        String laConsulta = "INSERT INTO EXAMEN (MATERIA_ID, NOMBRE, FECHA_HORA) VALUES(?,?,?)";
        PreparedStatement stmtConsulta = null;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setInt(1, examen.getMateriaId());
            stmtConsulta.setString(2, examen.getNombre());
            stmtConsulta.setTimestamp(3, new Timestamp(examen.getFecha().getTime()));
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
    
}
