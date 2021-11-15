package com.modelo;

public class Trabajador extends Usuario{
    public int trabajo;
    public int region;
    public String trabajoNombre;
    public String regionNombre;
    public String descripcion;

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
