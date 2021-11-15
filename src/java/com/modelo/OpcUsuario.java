
package com.modelo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
        
//Clase que ejecutará el CRUD de usuario
public class OpcUsuario extends conexion{
    
    //Método que registra al usuario
    public String[] registrar(Usuario usu) throws Exception{
        //Declaramos nuestras variables fuera del try/catch
        String[] estado = new String[2];
        Statement stm;
        ResultSet rs;
        
        //Intentamos conectar a la base de datos
        try{
            //Ejecutamos el método conectar de la clase conexión
            this.conectar();
            //Creamos la sentencia de SQL obteniendo la conexión
            stm = this.getCon().createStatement();
            //El resultado será lo que regrese la sentencia cuando ejecuta el select
            rs = stm.executeQuery("SELECT * FROM usuario WHERE correo_usu = '" + usu.getCorreoUsu() + "';");
            //Si no hay un registro previo (Que el select está vacío)
            if(!rs.next()){
                //La sentencia ejecuta una alta llamando al procedimiento almacenado
                stm.executeUpdate("CALL registrarUsuario('" + usu.getNombre() + "','" + usu.getApellido() + "','" + usu.getCorreoUsu()+ "','" + usu.getContraUsu()+ "'," + usu.getTipoUsu()+ ",'" + usu.getTelefono()+ "'" + ");");
                //Volvemos a ejecutar un select mediante la sentencia, para observar que ID va a tener esa nueva alta
                rs = stm.executeQuery("SELECT id_usu FROM usuario WHERE correo_usu = '" + usu.getCorreoUsu() + "';");
                
                //Si existe un resultado de nuestro anterior Select, guardaremos el id de usuario
                if(rs.next()){
                    estado[1] = String.valueOf(rs.getInt("id_usu"));
                    estado[0] = "";
                }
            }
            //Si hub oun registro previo con el mismo correo, regresaremos un registrado
            else{
                estado[0] = "registrado";
                estado[1] = "";
            }
        }
        //Imprimimos y iramos la excepción
        catch(Exception e){
            System.out.println(e);
            throw e;
        }
        
        //Regresamos el arreglo estado que nos iba a dar la información
        return estado;
    }
    
    //Metodo especial para completar registro
    public void completar(Trabajador trab) throws Exception{
        //Declaramos nuestras variables a ocupar
        //Sentencia SQL que va a esperar los parametros
        String sql = "CALL complementarRegistro(?, ?, ?, ?, ?, ?);";
        //Variable que va a preparar nuestra sentencia
        PreparedStatement ps;
        //Intentamos la conexión
        try{
            //Conectamos a la base de datos
            this.conectar();
            //A partir de la conexión preparamos nuestro procedimiento almacenado
            ps = this.getCon().prepareCall(sql);
            //Asignamos las variables necesarias a subir
            ps.setInt(1, trab.getTrabajo());
            ps.setString(2, trab.getDescripcion());
            ps.setBlob(3, trab.getPerfil());
            ps.setBlob(4, trab.getPortada());
            ps.setInt(5, trab.getRegion());
            ps.setInt(6, trab.getIdUsu());
            //Ejecutamos nuestra sentencia ya lista
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.print(e);
            throw e;
        }
    }
    
    //Método para obtener los datos del perfil
    public List listarPerfilTrabajador(int id){
        List<Trabajador> lista = new ArrayList<>();
        String sql = "CALL desplegarPerfilPropioTrabajador("+ id +")";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                Trabajador traba = new Trabajador();
                traba.setIdUsu(id);
                traba.setNombre(rs.getString(1));
                traba.setApellido(rs.getString(2));
                traba.setPerfil(rs.getBinaryStream(3));
                traba.setPortada(rs.getBinaryStream(4));
                traba.setTelefono(rs.getString(5));
                traba.setCorreoUsu(rs.getString(6));
                traba.setTrabajoNombre(rs.getString(7));
                traba.setRegionNombre(rs.getString(8));
                traba.setDescripcion(rs.getString(9));
                lista.add(traba);
            }
        }
        catch(Exception e){
            
        }
        
        return lista;
    }
    
    public void mostrarPerfil(int id, HttpServletResponse response){
        String sql = "SELECT * FROM usuario WHERE id_usu=" + id;
        InputStream perfil = null;
        OutputStream perfilSalida = null;
        BufferedInputStream buferPerfilEntrada = null;
        BufferedOutputStream buferPerfilSalida = null;
        response.setContentType("image/*");
        PreparedStatement ps;
        ResultSet rs;
        try{
            perfilSalida = response.getOutputStream();
            this.conectar();
            ps = this.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                perfil = rs.getBinaryStream(7);   
            }
            buferPerfilEntrada = new BufferedInputStream(perfil);
            buferPerfilSalida = new BufferedOutputStream(perfilSalida);
            int escrito = 0;
            while((escrito=buferPerfilEntrada.read()) != -1){
                buferPerfilSalida.write(escrito);
            }
        }
        catch(Exception e){
            
        }
    }
    
    public void mostrarPortada(int id, HttpServletResponse response){
        String sql = "SELECT * FROM usuario WHERE id_usu=" + id;
        InputStream portada = null;
        OutputStream portadaSalida = null;
        BufferedInputStream buferPortadaEntrada = null;
        BufferedOutputStream buferPortadaSalida = null;
        response.setContentType("image/*");
        PreparedStatement ps;
        ResultSet rs;
        try{
            portadaSalida = response.getOutputStream();
            this.conectar();
            ps = this.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                portada = rs.getBinaryStream(8);   
            }
            buferPortadaEntrada = new BufferedInputStream(portada);
            buferPortadaSalida = new BufferedOutputStream(portadaSalida);
            int escrito = 0;
            while((escrito=buferPortadaEntrada.read()) != -1){
                buferPortadaSalida.write(escrito);
            }
        }
        catch(Exception e){
            
        }
    }
    
    public void editarPerfilTrabajador(Trabajador trab){
        String sql = "CALL editarPerfilComp(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setString(1, trab.getNombre());
            ps.setString(2, trab.getApellido());
            ps.setString(3, trab.getContraUsu());
            ps.setBlob(4, trab.getPerfil());
            ps.setBlob(5, trab.getPortada());
            ps.setString(6, trab.getDescripcion());
            ps.setString(7,trab.getTelefono());
            ps.setInt(8, trab.getIdUsu());
            ps.setInt(9, trab.getRegion());
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    
    public boolean validarContrasena(int id, String contra){
        boolean validado = false;
        String sql = "CALL comprobarContrasena(?, ?);";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            if(rs.next()){
                validado = true;
            }
        }
        catch(Exception e){
            
        }
        return validado;
    }
    
    public Trabajador datosAntiguosTrabajador(int id){
        Trabajador traba = new Trabajador();
        String sql = "CALL desplegarPerfilPropioTrabajador("+ id +")";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                traba.setIdUsu(id);
                traba.setNombre(rs.getString(1));
                traba.setApellido(rs.getString(2));
                traba.setPerfil(rs.getBinaryStream(3));
                traba.setPortada(rs.getBinaryStream(4));
                traba.setTelefono(rs.getString(5));
                traba.setCorreoUsu(rs.getString(6));
                traba.setTrabajoNombre(rs.getString(7));
                traba.setRegionNombre(rs.getString(8));
                traba.setDescripcion(rs.getString(9));
            }
        }
        catch(Exception e){
            
        }
        
        return traba;
    }
    
    public void eliminarPerfil(int id){
        String sql = "CALL eliminarPerfil(?)";
        PreparedStatement ps;
        
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    
    public int[] iniciarSesion(String correo, String contra){
        int[] datos = new int[2];
        String sql = "CALL comprobarRegistro(?, ?);";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setString(1, correo);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            if(rs.next()){
                datos[0] = rs.getInt(1);
                datos[1] = rs.getInt(2);
            }
            else{
                datos[0] = 0;
                datos[1] = 0;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return datos;
    }
    
    public Usuario iniciarUsuario(int id){
        Usuario datosUsuario = new Usuario();
        String sql = "CALL iniciarUsuario(?)";
        PreparedStatement ps;
        ResultSet rs;
        try{
            this.conectar();
            ps = this.getCon().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                datosUsuario.setNombre(rs.getString(1));
                datosUsuario.setApellido(rs.getString(2));
                datosUsuario.setCorreoUsu(rs.getString(3));
                datosUsuario.setTelefono(rs.getString(4));
                datosUsuario.setPerfil(rs.getBinaryStream(5));
                datosUsuario.setPortada(rs.getBinaryStream(6));
            }
        }
        catch(Exception e){
            
        }
        
        return datosUsuario;
    }
}