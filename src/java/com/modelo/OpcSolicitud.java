
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
                lista.add(soli);
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
                if(rs.getString(9).equals("Pendiente")){
                    Solicitud soli = new Solicitud();
                    soli.setIdSoli(rs.getInt(1));
                    soli.setInicio(rs.getDate(2));
                    soli.setFin(rs.getDate(3));
                    soli.setDescripcion(rs.getString(4));
                    soli.setTitulo(rs.getString(5));
                    soli.setNombreEmisor(rs.getString(6) + " " + rs.getString(7));
                    soli.setIdEmisor(rs.getInt(8));
                    soli.setEstado(rs.getString(9));
                    lista.add(soli);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public void rechazarSolicitud(int idSoli){
        String SQL = "CALL rechazarSolicitud(?)";
        PreparedStatement ps;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(SQL);
            ps.setInt(1, idSoli);
            ps.executeQuery();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void aceptarSolicitud(Solicitud soli){
        String SQL = "CALL aceptarSolicitud(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(SQL);
            ps.setInt(1, soli.getIdSoli());
            ps.setString(2, soli.getTitulo());
            ps.setString(3, soli.getDescripcion());
            ps.setDate(4, soli.getInicio());
            ps.setDate(5, soli.getFin());
            ps.setInt(6, soli.getIdEmisor());
            ps.setInt(7, soli.getIdReceptor());
            ps.executeQuery();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Solicitud getSolicitud(int idSoli){
        Solicitud soli = new Solicitud();
        String SQL = "CALL getSolicitud(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(SQL);
            ps.setInt(1, idSoli);
            rs = ps.executeQuery();
            if(rs.next()){
                soli.setIdSoli(idSoli);
                soli.setInicio(rs.getDate(1));
                soli.setFin(rs.getDate(2));
                soli.setDescripcion(rs.getString(3));
                soli.setTitulo(rs.getString(4));
                soli.setIdEmisor(rs.getInt(5));
                soli.setIdReceptor(rs.getInt(6));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return soli;
    }
    
    public int[] getCounters(int idUser){
        int[] data = new int[3];
        String sql = "CALL listarSolicitudesUsuario(?)";
        PreparedStatement ps;
        ResultSet rs;
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(9).equals("Pendiente")){
                    data[0]++;
                }
                else{
                    if(rs.getString(9).equals("Aceptada")){
                      data[1]++;  
                    }
                    else{
                        data[2]++;
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return data;
    }
}
