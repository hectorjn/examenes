/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unpa.programacionjava.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 */
public class Examen {

    private Integer id;
    private Integer materiaId;
    private Integer cantidadInscritos;
    private String nombre;
    private String nombreMateria;
    private String fechaString;
    private String hora;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fecha) {
        this.fechaString = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        try {
            String fecha_hora = this.fechaString + " " + this.hora;
            date = formatter.parse(fecha_hora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public void setFecha(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        this.hora= formatter.format(date);
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaString = formatter.format(date);
        
    }

    public Integer getCantidadInscritos() {
        return cantidadInscritos;
    }

    public void setCantidadInscritos(Integer cantidadInscritos) {
        this.cantidadInscritos = cantidadInscritos;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
    

}
