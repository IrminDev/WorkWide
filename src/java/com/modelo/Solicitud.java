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
    private String titulo;
    private String descripcion;

    //Setters and getters de los atributos de la clase
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
