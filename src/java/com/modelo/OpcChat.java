package com.modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author IRMIN
 */
public class OpcChat extends conexion{
    
    public void enviarMsg(Mensaje msg){
        String sql = "CALL enviarMsg(?, ?, ?)";
        PreparedStatement ps;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, msg.getIdEmisor());
            ps.setInt(2, msg.getIdReceptor());
            ps.setString(3, msg.getMensaje());
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    
    public List getMensajes(int emisor, int receptor){
        List<Mensaje> lista = new ArrayList<>();
        String sql = "CALL getMsgs(?, ?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, emisor);
            ps.setInt(2, receptor);
            rs = ps.executeQuery();
            while(rs.next()){
                Mensaje msg = new Mensaje();
                msg.setMensaje(rs.getString(2));
                msg.setIdEmisor(rs.getInt(3));
                msg.setIdReceptor(rs.getInt(4));
                lista.add(msg);
            }
        }
        catch(Exception e){
            
        }
        
        return lista;
    }
    
    public List listarChats(int tipo, String entrada){
        List<Usuario>  lista = new ArrayList<>();
        String sql = "";
        if(entrada.equals("")){
            sql = "CALL listarChats(" + tipo + ")";
        }
        else{
            sql = "CALL buscarUsuarios('" + entrada + "', " + tipo + ")";
        }
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setIdUsu(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setEstado(rs.getString(4));
                lista.add(usu);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return lista;
    }
    
    public Mensaje ultimMsg(int idEmisor, int idReceptor){
        Mensaje msg = new Mensaje();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "CALL ultimoMsg(?, ?)";
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, idEmisor);
            ps.setInt(2, idReceptor);
            rs = ps.executeQuery();
            if(rs.next()){
                msg.setMensaje(rs.getString(2));
                msg.setIdEmisor(rs.getInt(3));
                msg.setIdReceptor(rs.getInt(4));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return msg;
    }
}
