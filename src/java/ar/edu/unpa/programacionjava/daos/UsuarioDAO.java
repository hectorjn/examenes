/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.Usuario;
import java.sql.Array;
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

    public static List<Usuario> buscarEstudiantes(Connection conn, Usuario usuario) throws SQLException  {
        List<Usuario> estudiantes = new ArrayList<Usuario>();
        // Arma la consulta y la ejecuta
        String laConsulta = "SELECT U.*, C.NOMBRE AS NOMBRE_C FROM USUARIO U \n" +
                            "INNER JOIN CARRERA C ON C.CARRERA_ID = U.CARRERA_ID\n" +
                            "WHERE U.TIPO = "+ Usuario.ESTUDIANTE;
        if(!usuario.getNombre().isEmpty()){
            laConsulta += " AND U.NOMBRE LIKE '%"+usuario.getNombre()+"%'";
        }
        if(!usuario.getApellido().isEmpty()){
            laConsulta += " AND U.APELLIDO LIKE '%"+usuario.getApellido()+"%'";
        }
        if(!usuario.getDni().isEmpty()){
            laConsulta += " AND U.DNI LIKE '%"+usuario.getDni()+"%'";
        }
        if(usuario.getIdCarrera() > 0){
            laConsulta += " AND U.CARRERA_ID = "+usuario.getIdCarrera();
        }
        laConsulta += " ORDER BY U.NOMBRE DESC , NOMBRE_C DESC ";
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
        System.out.println(laConsulta);
        // Obtiene los datos
        while (rs.next()) {
            Usuario estudiante = new Usuario();
            estudiante.setId(rs.getInt("user_id"));
            estudiante.setNombre(rs.getString("nombre"));
            estudiante.setApellido(rs.getString("apellido"));
            estudiante.setTipoUsuario(rs.getInt("tipo"));
            estudiante.setDni(rs.getString("dni"));
            estudiante.setIdCarrera(rs.getInt("carrera_id"));
            estudiante.setNombreCarrera(rs.getString("nombre_c"));
            estudiantes.add(estudiante);
        }
        // Cierra el Statement y la Connection
        stmtConsulta.close();
        conn.close();
        return estudiantes;
    }

    public static void borrarInscripciones(Connection conn, Integer idEstudiante) throws SQLException  {
        String laConsulta = "DELETE FROM EXAMENES.ESTUDIANTE_MATERIA WHERE ESTUDIANTE_ID = ? ";
        PreparedStatement stmtConsulta = null;
        stmtConsulta = conn.prepareStatement(laConsulta);
        stmtConsulta.setInt(1, idEstudiante);
        System.out.println(laConsulta);
        stmtConsulta.executeUpdate();
        stmtConsulta.close();

    }

    public static void inscribir(Connection conn, Integer idEstudiante, String[] materiasId) throws SQLException  {
        String materias = Arrays.toString(materiasId).replaceAll("\\[|\\]", "");
        String laConsulta = "INSERT INTO ESTUDIANTE_MATERIA(ESTUDIANTE_ID,MATERIA_ID, APROBADO) " +
                "SELECT ? , MATERIA_ID, FALSE FROM MATERIA WHERE MATERIA_ID IN("+materias+")";
        PreparedStatement stmtConsulta = null;
        stmtConsulta = conn.prepareStatement(laConsulta);
        stmtConsulta.setInt(1, idEstudiante);
        System.out.println(laConsulta);
        stmtConsulta.executeUpdate();
        stmtConsulta.close();
    }
    


}
