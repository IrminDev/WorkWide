package com.modelo;

import java.io.InputStream;


public class Usuario {
    public InputStream perfil;
    public InputStream portada;
    public int idUsu;
    public int tipoUsu;
    public String nombre;
    public String apellido;
    public String contraUsu;
    public String correoUsu;
    public String telefono;
    public String estado;

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
