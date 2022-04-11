package com.modelo;

import java.io.InputStream;

/**
 * 
 * @author IRMIN
 * Clase usuario exclusivamente con los atributos de usuario y sus respectivos setters and getters
 */

public class Usuario {
    //Atributos de la clase usuario
    private InputStream perfil;
    private InputStream portada;
    private int idUsu;
    private int tipoUsu;
    private String nombre;
    private String apellido;
    private String contraUsu;
    private String correoUsu;
    private String telefono;
    private String estado;
    
    //Setters and getters de los atributos de la clase usuario
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public InputStream getPerfil() {
        return perfil;
    }

    public void setPerfil(InputStream perfil) {
        this.perfil = perfil;
    }

    public InputStream getPortada() {
        return portada;
    }

    public void setPortada(InputStream portada) {
        this.portada = portada;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public int getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(int tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraUsu() {
        return contraUsu;
    }

    public void setContraUsu(String contraUsu) {
        this.contraUsu = contraUsu;
    }

    public String getCorreoUsu() {
        return correoUsu;
    }

    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
