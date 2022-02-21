package com.modelo;

/**
 * 
 * @author IrminDev
 * 
 * Clase Mensaje que contendrá solo los atributos de un mensaje con sus respectivos Setters and Getters
 */
public class Mensaje {
    
    //Atributos de cada mensaje
    private int idMensaje;
    private int idReceptor;
    private int idEmisor;
    private String mensaje;
    
    //Setters and getters
    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }
    
    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
