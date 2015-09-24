/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.Carrera;
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
public class CarreraDAO {
    public static boolean registrarNuevo(Connection conn, Carrera carrera) throws SQLException {
        String laConsulta = "INSERT INTO CARRERA ( NOMBRE) VALUES(?)";
        PreparedStatement stmtConsulta = null;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setString(1, carrera.getNombre());
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
    
    public static List<Carrera> obtenerTodos(Connection conn) throws SQLException {
        String laConsulta = "SELECT * FROM CARRERA";
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
        System.out.println(laConsulta);
        // Obtiene los datos
        List<Carrera> carreras = new ArrayList<Carrera>();
        while (rs.next()) {
            Carrera carrera = new Carrera();
            carrera.setId(rs.getInt("carrera_id"));
            carrera.setNombre(rs.getString("nombre"));
            carreras.add(carrera);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return carreras;
    }

   
    public static Carrera obtener(Connection conn, Integer id) throws SQLException {
        String laConsulta = "SELECT * FROM CARRERA WHERE CARRERA_ID = ?";
        PreparedStatement stmtConsulta = null;
        Carrera carrera =null ;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setInt(1, id);
            System.out.println(laConsulta);
            ResultSet rs = stmtConsulta.executeQuery();
            if (rs.next()) {
                carrera = new Carrera();
                carrera.setId(rs.getInt("carrera_id"));
                carrera.setNombre(rs.getString("nombre"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Cierra el Statement y la Connection
            stmtConsulta.close();
            conn.close();
        }
        return carrera;
    }
    

    public static boolean actualizar(Connection conn, Carrera carrera) throws SQLException {
        String laConsulta = "UPDATE CARRERA SET  NOMBRE=? WHERE CARRERA_ID=?";
        PreparedStatement stmtConsulta = null;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setString(1, carrera.getNombre());
            stmtConsulta.setInt(2, carrera.getId());
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
