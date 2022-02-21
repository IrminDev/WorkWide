package com.modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author IrminDev
 * 
 * Clase OpcChat encargada exclusivamente para la ejecución de métodos SQL
 */
public class OpcChat extends conexion{
    
    //Método enviarMsg que servirá para enviar mensaje a partir de un objeto mensaje
    public void enviarMsg(Mensaje msg){
        //Declaramos la sentencia SQL que dará de alta el mensaje en la BD
        String sql = "CALL enviarMsg(?, ?, ?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con el string ya delcarado y con la BD que se hará
            ps = this.getCon().prepareCall(sql);
            //Asignamos los valores que vamos a dar de alta con el procedimiento almcenado
            ps.setInt(1, msg.getIdEmisor());
            ps.setInt(2, msg.getIdReceptor());
            ps.setString(3, msg.getMensaje());
            //Ejecutamos la sentencia SQL
            ps.executeUpdate();
        }
        catch(Exception e){
            //Si hubo algún error en la ejecución, mostramos la excepción
            e.printStackTrace();
        }
    }
    
    //Método getMensajes que obtiene todos los mensajes que se han compartido entre dos usuarios y los retorna en una lista
    public List getMensajes(int emisor, int receptor){
        //Declaramos la lista de tipo mensaje que vamos a retornar
        List<Mensaje> lista = new ArrayList<>();
        //Declaramos la sentencia SQL que obtendrá los mensajes
        String sql = "CALL getMsgs(?, ?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con el string ya delcarado y con la BD que se hará
            ps = this.getCon().prepareCall(sql);
            //Asignamos los valores que vamos a hacer la consulta con el procedimiento almcenado
            ps.setInt(1, emisor);
            ps.setInt(2, receptor);
            //Realizamos la búsqueda y los resultados los guardamos en el ResultSet
            rs = ps.executeQuery();
            //Mientras haya filas en el resultSet (resultados de búsqueda)
            while(rs.next()){
                //Creamos un objeto mensaje
                Mensaje msg = new Mensaje();
                //Asignamos los datos obtenidos al objeto mensaje
                msg.setMensaje(rs.getString(2));
                msg.setIdEmisor(rs.getInt(3));
                msg.setIdReceptor(rs.getInt(4));
                //Agregamos el mensaje a la lista
                lista.add(msg);
            }
        }
        catch(Exception e){
            //Si hubo algún error en la ejecución, mostramos la excepción
            e.printStackTrace();
        }
        
        //Regresamos la lista con todos los mensajes que se obtuvieron
        return lista;
    }
    
    //Método listar chats que listará a todos los usuarios a los que les podrá enviar un mensaje, retornando dicha lista de usuarios
    public List listarChats(int tipo, String entrada){
        //Declaramos una lista de tipos usuario
        List<Usuario>  lista = new ArrayList<>();
        //Delcaramos la variable SQL del tipo string, que la usaremos adelante para saber que sentencia va a guardar
        String sql = "";
        if(entrada.equals("")){
            //Si la entrada de búsqueda es nula, listamos a todos los usuarios del tipo que corresponden
            sql = "CALL listarChats(" + tipo + ")";
        }
        else{
            //En caso de que haya una entrada de búsqueda, seleccionamos a los usuarios que coincidan con la busqueda y el tipo
            sql = "CALL buscarUsuarios('" + entrada + "', " + tipo + ")";
        }
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con el string ya delcarado y con la BD que se hará
            ps = this.getCon().prepareCall(sql);
            //Realizamos la búsqueda y los resultados los guardamos en el ResultSet
            rs = ps.executeQuery();
            //Mientras haya filas en el resultSet (resultados de búsqueda)
            while(rs.next()){
                //Creamos un objeto usuario
                Usuario usu = new Usuario();
                //Asignamos los datos que obtuvimos de una fila al objeto usuario
                usu.setIdUsu(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setEstado(rs.getString(4));
                //Agregamos al objeto usuario a la lista
                lista.add(usu);
            }
        }
        catch(Exception e){
            //Si hubo algún error en la ejecución, mostramos la excepción
            e.printStackTrace();
        }
        
        //Regresamos la lista con todos los usuarios que encontramos
        return lista;
    }
    
    
    //Método ultimMsg que recupera el último mensaje compartido entre dos usuarios, mediante sus ids, regresando un objeto mensaje
    public Mensaje ultimMsg(int idEmisor, int idReceptor){
        //Creamos nuestro objeto mensaje
        Mensaje msg = new Mensaje();
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Declaramos la sentencia SQL que obtendrá el mensaje
        String sql = "CALL ultimoMsg(?, ?)";
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con el string ya delcarado y con la BD que se hará
            ps = this.getCon().prepareCall(sql);
            //Agreamos los parámetros con los que el procedimiento alamcenado buscará el último mensaje
            ps.setInt(1, idEmisor);
            ps.setInt(2, idReceptor);
            //Realizamos la búsqueda y los resultados los guardamos en el ResultSet
            rs = ps.executeQuery();
            //Si encontró un resulktado, ejecuta lo siguiente
            if(rs.next()){
                //Asignamos los datos que obtuvimos de una fila al objeto mensaje
                msg.setMensaje(rs.getString(2));
                msg.setIdEmisor(rs.getInt(3));
                msg.setIdReceptor(rs.getInt(4));
            }
        }
        catch(Exception e){
            //Si hubo algún error en la ejecución, mostramos la excepción
            e.printStackTrace();
        }
        
        //Retornamos el mensaje que obtuvimos de la búsqueda
        return msg;
    }
}
