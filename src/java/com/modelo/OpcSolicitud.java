
package com.modelo;

import java.sql.PreparedStatement;

public class OpcSolicitud extends conexion{
    
    public void altaSolicitud(Solicitud soli) throws Exception{
        String sql = "CALL altaSolicitud(?, ?, ?, ?, ?, ?);";
       //Variable que va a preparar nuestra sentencia
        PreparedStatement ps;
       //Intentamos conectar a la base de datos
        try{
            //Ejecutamos el método conectar de la clase conexión            
            this.conectar();
            //A partir de la conexión preparamos nuestro procedimiento almacenado
            ps = this.getCon().prepareCall(sql);
            //Asignamos las variables necesarias a subir
            ps.setInt(1, soli.getIdEmisor());
            ps.setInt(2, soli.getIdReceptor());
            ps.setDate(3, soli.getInicio());
            ps.setDate(4, soli.getFin());
            ps.setString(5, soli.getTitulo());
            ps.setString(6, soli.getDescripcion());
            
            //Ejecutamos nuestra sentencia ya lista
            ps.executeUpdate();            
        } 
        catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
