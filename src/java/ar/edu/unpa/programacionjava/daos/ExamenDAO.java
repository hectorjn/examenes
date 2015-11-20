/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.daos;

import ar.edu.unpa.programacionjava.entities.Carrera;
import ar.edu.unpa.programacionjava.entities.Examen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<Examen> listarExamenes(Connection conn, Examen examen) throws SQLException {
        String laConsulta = " SELECT E.EXAMEN_ID, E.MATERIA_ID,M.NOMBRE AS NOMBRE_M,E.FECHA_HORA, E.NOMBRE, COUNT(EX.ESTUDIANTE_ID) AS INSCRITOS \n" +
                            " FROM EXAMEN E\n" +
                            " INNER JOIN MATERIA M ON M.MATERIA_ID = E.MATERIA_ID \n"+
                            " LEFT JOIN ESTUDIANTE_EXAMEN EX ON E.EXAMEN_ID = EX.EXAMEN_ID\n"+
                            " WHERE E.MATERIA_ID IS NOT NULL \n" ;
        if(examen.getMateriaId() != null && examen.getMateriaId() >0){
            laConsulta += " AND  E.MATERIA_ID = " + examen.getMateriaId();
        }
        if(examen.getNombre()!=null && !examen.getNombre().isEmpty()){
            laConsulta += " AND  E.NOMBRE LIKE '%" + examen.getNombre()+"%'";
        }
        laConsulta += "  GROUP BY E.EXAMEN_ID, E.MATERIA_ID,M.NOMBRE, E.FECHA_HORA, E.NOMBRE"
                +" ORDER BY M.NOMBRE ASC , E.NOMBRE ASC";
        System.out.println(laConsulta);
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
       
        // Obtiene los datos
        List<Examen> examenes = new ArrayList<Examen>();
        Timestamp stamp;
        while (rs.next()) {
            Examen ex  = new Examen();
            ex.setCantidadInscritos(rs.getInt("INSCRITOS"));
            ex.setId(rs.getInt("EXAMEN_ID"));
            ex.setMateriaId(rs.getInt("E.MATERIA_ID"));
            ex.setNombreMateria(rs.getString("NOMBRE_M"));
            ex.setNombre(rs.getString("NOMBRE"));
            stamp = rs.getTimestamp("FECHA_HORA");
            ex.setFecha(new Date(stamp.getTime()));
            examenes.add(ex);
        }
        stmtConsulta.close();
        conn.close();
        
       
        return examenes;
    }

    public static List<Examen> buscarExamenesEstudiante(Connection conn, Integer id) throws SQLException {
        String laConsulta = " SELECT E.EXAMEN_ID, E.MATERIA_ID,M.NOMBRE AS NOMBRE_M,E.FECHA_HORA, E.NOMBRE" +
                            " FROM EXAMEN E\n" +
                            " INNER JOIN MATERIA M ON M.MATERIA_ID = E.MATERIA_ID \n"+
                            " INNER JOIN ESTUDIANTE_EXAMEN EX ON E.EXAMEN_ID = EX.EXAMEN_ID\n"+
                            " WHERE EX.ESTUDIANTE_ID  ="+id ;
      
        System.out.println(laConsulta);
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
       
        // Obtiene los datos
        List<Examen> examenes = new ArrayList<Examen>();
        Timestamp stamp;
        while (rs.next()) {
            Examen ex  = new Examen();
            ex.setId(rs.getInt("EXAMEN_ID"));
            ex.setMateriaId(rs.getInt("E.MATERIA_ID"));
            ex.setNombreMateria(rs.getString("NOMBRE_M"));
            ex.setNombre(rs.getString("NOMBRE"));
            stamp = rs.getTimestamp("FECHA_HORA");
            ex.setFecha(new Date(stamp.getTime()));
            examenes.add(ex);
        }
        stmtConsulta.close();
        conn.close();
       
        return examenes;
    }

    public static List<Examen> buscarExamenesPorMateria(Connection conn, Integer id) throws SQLException{
         String laConsulta = " SELECT E.EXAMEN_ID, E.MATERIA_ID,M.NOMBRE AS NOMBRE_M,E.FECHA_HORA, E.NOMBRE" +
                            " FROM EXAMEN E\n" +
                            " INNER JOIN MATERIA M ON M.MATERIA_ID = E.MATERIA_ID \n"+
                            " INNER JOIN ESTUDIANTE_MATERIA EM ON EM.MATERIA_ID = M.MATERIA_ID\n"+
                            " WHERE EM.ESTUDIANTE_ID  ="+id ;
      
        System.out.println(laConsulta);
        Statement stmtConsulta = conn.createStatement();
        ResultSet rs = stmtConsulta.executeQuery(laConsulta);
       
        // Obtiene los datos
        List<Examen> examenes = new ArrayList<Examen>();
        Timestamp stamp;
        while (rs.next()) {
            Examen ex  = new Examen();
            ex.setId(rs.getInt("EXAMEN_ID"));
            ex.setMateriaId(rs.getInt("E.MATERIA_ID"));
            ex.setNombreMateria(rs.getString("NOMBRE_M"));
            ex.setNombre(rs.getString("NOMBRE"));
            stamp = rs.getTimestamp("FECHA_HORA");
            ex.setFecha(new Date(stamp.getTime()));
            examenes.add(ex);
        }
        stmtConsulta.close();
        conn.close();
       
        return examenes;
    }
    
}
