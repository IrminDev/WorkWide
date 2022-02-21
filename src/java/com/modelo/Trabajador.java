package com.modelo;
/**
 * 
 * @author IrminDev
 * 
 * Clase trabajador que extiende de la clase usuario solo agregando los atributos y setters and getters correpondientes
 */
public class Trabajador extends Usuario{
    //Atributos de la clase trabajador
    private int trabajo;
    private int region;
    private String trabajoNombre;
    private String regionNombre;
    private String descripcion;
    
    //Setters and getters de los atributos de la clase trabajador
    public int getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(int trabajo) {
        this.trabajo = trabajo;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getTrabajoNombre() {
        return trabajoNombre;
    }

    public void setTrabajoNombre(String trabajoNombre) {
        this.trabajoNombre = trabajoNombre;
    }

    public String getRegionNombre() {
        return regionNombre;
    }

    public void setRegionNombre(String regionNombre) {
        this.regionNombre = regionNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
