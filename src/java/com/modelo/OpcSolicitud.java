
package com.modelo;

import java.sql.PreparedStatement;

public class OpcSolicitud extends conexion{
    
    public void altaSolicitud(Solicitud soli) throws Exception{
        String sql = "CALL complementarRegistro(?, ?, ?, ?, ?, ?, ?);";
       //Variable que va a preparar nuestra sentencia
        PreparedStatement ps;
       //Intentamos conectar a la base de datos
        try{
            //Ejecutamos el método conectar de la clase conexión            
            this.conectar();
            //A partir de la conexión preparamos nuestro procedimiento almacenado
            ps = this.getCon().prepareCall(sql);
            //Asignamos las variables necesarias a subir
            ps.setDate(1, soli.getInicio());
            ps.setDate(2, soli.getFin());
            ps.setInt(3, soli.getIdEmisor());
            ps.setInt(4, soli.getIdReceptor());
            ps.setInt(5, soli.getIdSoli());
            ps.setString(6, soli.getTitulo());
            ps.setString(7, soli.getDescripcion());
            
            //Ejecutamos nuestra sentencia ya lista
            ps.executeUpdate();            
        } 
        catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
