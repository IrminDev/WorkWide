package com.modelo;

import java.sql.Date;
/**
 * 
 * @author IrminDev
 * 
 * Clase soliciutd que contiene esclusivamente los atributos de cada solicitud y sus setters and getters
 */
public class Solicitud {
    //Atributos de la clase solicitud
    private Date inicio;
    private Date fin;
    private int idEmisor;
    private int idReceptor;
    private int idSoli;
    private String estado;
    private String nombreEmisor;
    private String nombreReceptor;
    private String titulo;
    private String descripcion;

    //Setters and getters de los atributos de la clase
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }
    
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public int getIdSoli() {
        return idSoli;
    }

    public void setIdSoli(int idSoli) {
        this.idSoli = idSoli;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
