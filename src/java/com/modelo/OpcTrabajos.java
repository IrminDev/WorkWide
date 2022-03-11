package com.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IrminDev
 */
public class OpcTrabajos extends conexion{
    
    public List listarTrabajosUsuario(int id){
        List<Trabajo> lista = new ArrayList<>();
        String sql = "CALL listarTrabajosUsuario(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajo work = new Trabajo();
                work.setIdTrabajo(rs.getInt(1));
                work.setTitulo(rs.getString(2));
                work.setDescripcion(rs.getString(3));
                work.setFechaInicio(rs.getDate(4));
                work.setFechaFin(rs.getDate(5));
                work.setEstado(rs.getString(6));
                work.setNombreEpleado(rs.getString(7) + " " + rs.getString(8));
                work.setIdEmpleado(rs.getInt(9));
                lista.add(work);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    
    public List listarTrabajosTrabajador(int id){
        List<Trabajo> lista = new ArrayList<>();
        String sql = "CALL listarTrabajosTrabajador(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajo work = new Trabajo();
                work.setIdTrabajo(rs.getInt(1));
                work.setTitulo(rs.getString(2));
                work.setDescripcion(rs.getString(3));
                work.setFechaInicio(rs.getDate(4));
                work.setFechaFin(rs.getDate(5));
                work.setEstado(rs.getString(6));
                work.setNombreEpleador(rs.getString(7) + " " + rs.getString(8));
                work.setIdEmpleador(rs.getInt(9));
                lista.add(work);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public int[] getContadoresUsuario(int id){
        int[] data = new int[3];
        String sql = "CALL listarTrabajosUsuario(?)";
        PreparedStatement ps;
        ResultSet rs;
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(6).equals("Pendiente")){
                    data[0]++;
                }
                else{
                    if(rs.getString(6).equals("En progreso")){
                        data[1]++;
                    }
                    else{
                        if(rs.getString(6).equals("Finalizado")){
                            data[2]++;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return data;
    }
    
    public int[] getContadoresTrabajador(int id){
        int[] data = new int[3];
        String sql = "CALL listarTrabajosTrabajador(?)";
        PreparedStatement ps;
        ResultSet rs;
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(6).equals("Pendiente")){
                    data[0]++;
                }
                else{
                    if(rs.getString(6).equals("En progreso")){
                        data[1]++;
                    }
                    else{
                        if(rs.getString(6).equals("Finalizado")){
                            data[2]++;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return data;
    }
    
    public void cambiarEstado(int idTrabajo, int idEstado){
        String sql = "CALL cambiarEstadoTrabajo(?, ?)";
        PreparedStatement ps;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, idTrabajo);
            ps.setInt(2, idEstado);
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
