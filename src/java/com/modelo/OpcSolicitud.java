
package com.modelo;

import java.sql.PreparedStatement;
/**
 * 
 * @author IrminDev
 * 
 * Clase esclusiva para los métodos para las solicitudes de usuario
 */
public class OpcSolicitud extends conexion{
    
    //Método altaSolicitud para dar de alta nuevas solicitudes
    public void altaSolicitud(Solicitud soli) throws Exception{
        //Declaramos la sentencia SQL en string, que será laque vamos a jecutar
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
            //Imprimimos posibles errores
            System.out.println(e);
            throw e;
        }
    }
}
