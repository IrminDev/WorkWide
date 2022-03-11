package com.modelo;

import java.sql.Date;

/**
 *
 * @author IrminDev
 */
public class Trabajo {
    private int idTrabajo;
    private int idEmpleado;
    private int idEmpleador;
    private String titulo;
    private String estado;
    private String descripcion;
    private String nombreEpleado;
    private String nombreEpleador;
    private Date fechaInicio;
    private Date fechaFin;

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleador() {
        return idEmpleador;
    }

    public void setIdEmpleador(int idEmpleador) {
        this.idEmpleador = idEmpleador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreEpleado() {
        return nombreEpleado;
    }

    public void setNombreEpleado(String nombreEpleado) {
        this.nombreEpleado = nombreEpleado;
    }

    public String getNombreEpleador() {
        return nombreEpleador;
    }

    public void setNombreEpleador(String nombreEpleador) {
        this.nombreEpleador = nombreEpleador;
    }
    
    
}
