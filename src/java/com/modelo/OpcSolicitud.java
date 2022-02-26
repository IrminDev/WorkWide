
package com.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    
    public List listarSoliUsuario(int id){
        List<Solicitud> lista = new ArrayList<>();
        String sql = "CALL listarSolicitudesUsuario(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Solicitud soli = new Solicitud();
                soli.setIdSoli(rs.getInt(1));
                soli.setInicio(rs.getDate(2));
                soli.setFin(rs.getDate(3));
                soli.setDescripcion(rs.getString(4));
                soli.setTitulo(rs.getString(5));
                soli.setNombreReceptor(rs.getString(6) + " " + rs.getString(7));
                soli.setIdReceptor(rs.getInt(8));
                soli.setEstado(rs.getString(9));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List listarSoliTrabajador(int id){
        List<Solicitud> lista = new ArrayList<>();
        String sql = "CALL listarSolicitudesTrabajador(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Solicitud soli = new Solicitud();
                soli.setIdSoli(rs.getInt(1));
                soli.setInicio(rs.getDate(2));
                soli.setFin(rs.getDate(3));
                soli.setDescripcion(rs.getString(4));
                soli.setTitulo(rs.getString(5));
                soli.setNombreEmisor(rs.getString(6) + " " + rs.getString(7));
                soli.setIdEmisor(rs.getInt(8));
                soli.setEstado(rs.getString(9));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
}
