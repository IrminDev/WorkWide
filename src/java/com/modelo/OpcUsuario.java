
package com.modelo;
import java.sql.*;
        
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
}
