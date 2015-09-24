/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 *
 */
public class UsuarioDAO {

    public static void getExisteUsuario(Connection conn, Usuario usuario) throws Exception {
        // Arma la consulta y la ejecuta
        String laConsulta = "SELECT * FROM USUARIO WHERE LOGIN ='" + usuario.getUsername() + "' AND PASSWORD ='" + usuario.getPassword() + "'";
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
        System.out.println(laConsulta);
        // Obtiene los datos
        if (rs.next()) {
            usuario.setId(rs.getInt("user_id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setTipoUsuario(rs.getInt("tipo"));
            usuario.setDni(rs.getString("dni"));
            usuario.setIdCarrera(rs.getInt("carrera_id"));
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();

    }

    public static boolean registrarUsuario(Connection conn, Usuario usuario) throws SQLException {
        String laConsulta = "INSERT INTO USUARIO (LOGIN, PASSWORD, NOMBRE, APELLIDO,TIPO, DNI, CARRERA_ID) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmtConsulta = null;
        try {
            stmtConsulta = conn.prepareStatement(laConsulta);
            stmtConsulta.setString(1, usuario.getUsername());
            stmtConsulta.setString(2, usuario.getPassword());
            stmtConsulta.setString(3, usuario.getNombre());
            stmtConsulta.setString(4, usuario.getApellido());
            stmtConsulta.setInt(5, usuario.getTipoUsuario());
            stmtConsulta.setString(6, usuario.getDni());
             stmtConsulta.setInt(7, usuario.getIdCarrera());
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
