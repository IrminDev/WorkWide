
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
    public Trabajador listarPerfilTrabajador(int id){
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
    
    //Metodo para obtener la información del perfil de un usuario
    public Usuario listarPerfilUsuario(int id){
        //Creamos el objeto usuario que tendrá toda la información
        Usuario usu = new Usuario();
        //Creamos la sentencia SQL que vamos a ejecutar
        String sql = "CALL perfilUsuario("+ id +")";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia dentro de la base de datos que vamos a usar
            ps = this.getCon().prepareCall(sql);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay información en el resultSet, guardaremos la información del usuario
            if(rs.next()){
                //Guardamos los datos del resultado en el objeto usuario
                usu.setIdUsu(id);
                usu.setNombre(rs.getString(1));
                usu.setApellido(rs.getString(2));
                usu.setCorreoUsu(rs.getString(3));
                usu.setTelefono(rs.getString(4));
            }
        }
        catch(Exception e){
            
        }
        
        //Retornamos el objeto usuario
        return usu;
    }
    
    //Método que busca perfiles a partir de una palabra clave y retorna una lista con los usuarios que coinciden con la busqueda
    public List busquedaPefiles(String texto){
        //Creamos la lista de trabajadores, que serán los tipos de usuario a listar
        List<Trabajador> lista = new ArrayList<>();
        //Creamos la sentencia SQL en un string con la sentencia que vamos a ejecutar
        String sql = "CALL buscar(?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia dentro de la base de datos que vamos a usar
            ps = this.getCon().prepareCall(sql);
            //Agregamos los parámetros necesarios para ejecutar nuestra sentencia SQL
            ps.setString(1, texto);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Mientras haya una fila de resultado (recordemos que el rs.next pasa a la siguiente fila y regresa un booleano si hay otra fila)
            while(rs.next()){
                //Comprobamos que los usuarios sean trabajadores
                if(rs.getString(6) != null){
                    //Creamos un objeto trabajador donde guardaremos la información de un registro
                    Trabajador traba = new Trabajador();
                    //Guardamos en el objeto trabajador la información del registro
                    traba.setNombre(rs.getString(1));
                    traba.setApellido(rs.getString(2));
                    traba.setPerfil(rs.getBinaryStream(3));
                    traba.setPortada(rs.getBinaryStream(4));
                    traba.setDescripcion(rs.getString(5));
                    traba.setTrabajoNombre(rs.getString(6));
                    traba.setRegionNombre(rs.getString(7));
                    traba.setIdUsu(rs.getInt(8));
                    traba.setCorreoUsu(rs.getString(9));
                    //Agregamos al trabajador a la lista
                    lista.add(traba);
                }
            }
        }
        catch(Exception e){
            
        }
        
        //Retornamos la lista con todos los trabajadores que coinciden en la búsqueda
        return lista;
    }
    
    
    //Método para guardar a todos los usuarios del tipo trabajador en una lista que será retornada
    public List mostrarPerfiles(){
        //Creamos la lista de trabajadores, que serán los tipos de usuario a listar
        List<Trabajador> lista = new ArrayList<>();
        //Creamos la sentencia SQL en un string con la sentencia que vamos a ejecutar
        String sql = "CALL desplegarUsuarios()";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia dentro de la base de datos que vamos a usar
            ps = this.getCon().prepareCall(sql);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Mientras haya una fila de resultado (recordemos que el rs.next pasa a la siguiente fila y regresa un booleano si hay otra fila)
            while(rs.next()){
                //Comprobamos que se trate de un trabajador para continuar
                if(rs.getString(4) != null){
                    //Creamos un objeto trabajador
                    Trabajador plantilla = new Trabajador();
                    //Guardamos toda la información del registro en el objeto trabajador
                    plantilla.setNombre(rs.getString(1));
                    plantilla.setApellido(rs.getString(2));
                    plantilla.setIdUsu(rs.getInt(3));
                    plantilla.setTrabajoNombre(rs.getString(4));
                    plantilla.setRegionNombre(rs.getString(5));
                    plantilla.setCorreoUsu(rs.getString(6));
                    //Agregamos el objeto trabajador a la lista
                    lista.add(plantilla);
                }
            }
        }
        catch(Exception e){
            
        }
        
        //Regresamos la lista con todos los trabajadores encontrados
        return lista;
    }
    
    //Método que convierte un blob de la base de datos en una imágen, en este caso la foto de perfil a partir del id y respondemos con la imágen
    public void mostrarPerfil(int id, HttpServletResponse response){
        //Creamos nuestra sentencia SQL que va a ser ejecutada
        String sql = "SELECT * FROM usuario WHERE id_usu=" + id;
        //Creamos un inputStream que guardará los bytes de la foto de perfil, proveniente de la BD
        InputStream perfil = null;
        //Creamos un objeto OutputStream que serán los bytes traducidos a imágen
        OutputStream perfilSalida = null;
        //Creamos objetos bufer que guardarán en la memoria temporal los bytes que se irán convirtiendo
        BufferedInputStream buferPerfilEntrada = null;
        BufferedOutputStream buferPerfilSalida = null;
        //Ajustamos la respuesta en un formato de imágen
        response.setContentType("image/*");
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Creamos un try-catch para la conversión de los bytes del perfil y la conexión a la BD
        try{
            //Ajsuatmos a la foto de perfil de salida como lo que deberá mostrar la respuesta en su salida (a nivel de bytes)
            perfilSalida = response.getOutputStream();
            //Conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con la bd a la que nos conectaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareStatement(sql);
            //Ejecutamos la sentencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay información, se continua
            if(rs.next()){
                //Guardamos en los bytes del perfil los obtenidos de la base de datos
                perfil = rs.getBinaryStream(7);   
            }
            //Hacemos que los bufers correspondan con la información que hasta el momentos ha sido configurada (bytes de entrada y de salida)
            buferPerfilEntrada = new BufferedInputStream(perfil);
            buferPerfilSalida = new BufferedOutputStream(perfilSalida);
            //Variable que nos ayudará para reescribir los bytes
            int escrito = 0;
            //Mientras haya bytes que leer, se ejecuta lo siguiente
            while((escrito=buferPerfilEntrada.read()) != -1){
                //En la memoria temporal vamos escribiendo los bytes que están siendo leidos (ya directamente sobre lo que muestra la respuesta)
                buferPerfilSalida.write(escrito);
            }
        }
        catch(Exception e){
            
        }
    }
    
    //Método que convierte un blob de la base de datos en una imágen, en este caso la foto de portada a partir del id y respondemos con la imágen
    public void mostrarPortada(int id, HttpServletResponse response){
        //Creamos nuestra sentencia SQL que va a ser ejecutada
        String sql = "SELECT * FROM usuario WHERE id_usu=" + id;
        //Creamos un inputStream que guardará los bytes de la foto de perfil, proveniente de la BD
        InputStream portada = null;
        //Creamos un objeto OutputStream que serán los bytes traducidos a imágen
        OutputStream portadaSalida = null;
        //Creamos objetos bufer que guardarán en la memoria temporal los bytes que se irán convirtiendo
        BufferedInputStream buferPortadaEntrada = null;
        BufferedOutputStream buferPortadaSalida = null;
        //Ajustamos la respuesta en un formato de imágen
        response.setContentType("image/*");
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Creamos un try-catch para la conversión de los bytes del perfil y la conexión a la BD
        try{
            //Ajsuatmos a la foto de portada de salida como lo que deberá mostrar la respuesta en su salida (a nivel de bytes)
            portadaSalida = response.getOutputStream();
            //Conectamos a la base de datos
            this.conectar();
            //Preparamos nuestra sentencia SQL con la bd a la que nos conectaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareStatement(sql);
            //Ejecutamos la sentencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay información, se continua
            if(rs.next()){
                //Guardamos en los bytes de la portada los obtenidos de la base de datos
                portada = rs.getBinaryStream(8);   
            }
            //Hacemos que los bufers correspondan con la información que hasta el momentos ha sido configurada (bytes de entrada y de salida)
            buferPortadaEntrada = new BufferedInputStream(portada);
            buferPortadaSalida = new BufferedOutputStream(portadaSalida);
            //Variable que nos ayudará para reescribir los bytes
            int escrito = 0;
            //Mientras haya bytes que leer, se ejecuta lo siguiente
            while((escrito=buferPortadaEntrada.read()) != -1){
                //En la memoria temporal vamos escribiendo los bytes que están siendo leidos (ya directamente sobre lo que muestra la respuesta)
                buferPortadaSalida.write(escrito);
            }
        }
        catch(Exception e){
            
        }
    }
    
    
    //Método para editar la información del trabajador apartir de un objeto trabajador dado
    public void editarPerfilTrabajador(Trabajador trab){
        //Creamos nuestra sentencia SQL en una cadena, la cuál vamos a ejecutar posteriormente
        String sql = "CALL editarPerfilComp(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Asignamos los valores del objeto trabajador dado para actualizar la información
            ps.setString(1, trab.getNombre());
            ps.setString(2, trab.getApellido());
            ps.setString(3, trab.getContraUsu());
            ps.setBlob(4, trab.getPerfil());
            ps.setBlob(5, trab.getPortada());
            ps.setString(6, trab.getDescripcion());
            ps.setString(7,trab.getTelefono());
            ps.setInt(8, trab.getIdUsu());
            ps.setInt(9, trab.getRegion());
            //Ejecutamos la actualización de los datos del trabajador
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    
    //Método que verifica si una contraseña coincide con la de la base de datos, apartir de la contraseña a verificar y el id del usuario
    public boolean validarContrasena(int id, String contra){
        //Creamos nuestro boolean que inicialmente será falso (el estado de la verificacíón)
        boolean validado = false;
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL comprobarContrasena(?, ?);";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Asignamos los datos necesarios para que se pueda hacer la verifación de la contraseña
            ps.setInt(1, id);
            ps.setString(2, contra);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay un reusltado en el resultset, se ejecuta lo siguiente
            if(rs.next()){
                //Se valida que la contraseña corresponde al id colocado
                validado = true;
            }
        }
        catch(Exception e){
            
        }
        
        //Retornamos el boolean que describía el estado de verificiación
        return validado;
    }
    
    //Método para obtener los datos del trabajador a partir de su ID
    public Trabajador datosAntiguosTrabajador(int id){
        //Creamos un objeto trabajador
        Trabajador traba = new Trabajador();
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL desplegarPerfilPropioTrabajador("+ id +")";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay un resultado, se ejecuta lo siguiente
            if(rs.next()){
                //En el objeto trabajador se guarda toda la información obtenida
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
        
        //Retornamos el objeto trabajador en el que se guardó la información
        return traba;
    }
    
    //Método para eliminar un registro de la base de datos
    public void eliminarPerfil(int id){
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL eliminarPerfil(?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Colocamos los datos necesarios para que se realice el borrado
            ps.setInt(1, id);
            //Ejecutamos la sentencia SQL que borra el registro
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    
    //Método para comprobar credenciales de un usuario y retornar su tipo de usuario y su ID
    public int[] iniciarSesion(String correo, String contra){
        //Creamos el array de enteros, 0 para su ID y 1 para el tipo de usuario
        int[] datos = new int[2];
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL comprobarRegistro(?, ?);";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Asiganmos los valores necesarios para comprobar un inicio de sesión
            ps.setString(1, correo);
            ps.setString(2, contra);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay un resultado, se ejecuta lo siguiente
            if(rs.next()){
                //En el array de enteros, se guardan los datos obtenidos de la busqueda
                datos[0] = rs.getInt(1);
                datos[1] = rs.getInt(2);
            }
            else{
                //Si no se encontró nada, se dejan como 0
                datos[0] = 0;
                datos[1] = 0;
            }
        }
        catch(Exception e){
            //Imprimimos posibles errores
            System.out.println(e);
        }
        
        //Retornamos el array de enteros con los ids
        return datos;
    }
    
    //Método para obtener la info de un usuario y retornarla en un objeto usuario
    public Usuario iniciarUsuario(int id){
        //Creamos nuestro objeto usuario
        Usuario datosUsuario = new Usuario();
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL iniciarUsuario(?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Colocamos el id del usuario del que obtendremos su información
            ps.setInt(1, id);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay un resultado, se ejecuta lo siguiente
            if(rs.next()){
                //En el objeto usuario guardamos todos los datos obtenidos del resultado
                datosUsuario.setNombre(rs.getString(1));
                datosUsuario.setApellido(rs.getString(2));
                datosUsuario.setCorreoUsu(rs.getString(3));
                datosUsuario.setTelefono(rs.getString(4));
                datosUsuario.setPerfil(rs.getBinaryStream(5));
                datosUsuario.setPortada(rs.getBinaryStream(6));
                datosUsuario.setEstado(rs.getString(7));
            }
        }
        catch(Exception e){
            //Imprimimos los posibles errores
            System.out.println(e);
        }
        
        //Retornamos el objeto usuario con los datos que obtuvimos
        return datosUsuario;
    }
    
    
    //Método para actualizar los datos de un usuario del tipo usuario 
    public void actualizarUsuario(Usuario usu){
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL editarUsuarioPerfil(?, ?, ?, ?, ?, ?, ?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Configuramos los datos necesarios para ejecutar la sentencia SQL
            ps.setInt(1, usu.getIdUsu());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getApellido());
            ps.setString(4, usu.getTelefono());
            ps.setBlob(5, usu.getPortada());
            ps.setBlob(6, usu.getPerfil());
            ps.setString(7, usu.getContraUsu());
            //Ejecutamos la actualización de los cambios
            ps.executeUpdate();
        }
        catch(Exception e){
             
        }
    }
    
    //Método para obtener el id de un usuraio a partir de su correo
    public int obtenerIdCoreeo(String correo){
        //Declaramos el id inicialmente como 0
        int id = 0;
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "call idApartirCorreo(?)";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Instanciamos la clase ResultSet que nos ayudará para guardar los datos que regrese SQL
        ResultSet rs;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Confiugramos el correo que se usará para obtener el id
            ps.setString(1, correo);
            //Ejecutamos la senteencia SQL y los resultados los guardamos en el resultSet
            rs = ps.executeQuery();
            //Si hay un resultado, se ejecuta lo siguiente
            if(rs.next()){
                //Guardamos el id obtenido en la variable id
                id = rs.getInt(1);
            }
        }
        catch(Exception e){
            //Imprimimos posibles errores
            System.out.println(e);
        }
        
        //Retornamos el id obtenido
        return id;
    }
    
    //Método con el que cambiamos el estado de un usuario de inactivo a activo, cuando inicie sesión
    public void cambiarEstado(int id){
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL iniciarSesion(" + id + ")";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Ejecutamos el cambio
            ps.executeUpdate();
        }
        catch(Exception e){
            //En caso de un error lo imprimimos
            e.printStackTrace();
        }
    }
    
    //Método que al cerrar sesión se ejecuta para cambiar el esatdo de un usuario
    public void CerrarSesion(int id){
        //Preparamos nuestra sentencia SQL en una cadena
        String sql = "CALL cerrarSesion(" + id + ")";
        //Instanciamos la clase preparedStatement, donde vamos a prepar nuestra sentencia SQL adelante
        PreparedStatement ps;
        //Iniciamos un try-catch para la ejecución del método SQL
        try{
            //Nos conectamos a la BD
            this.conectar();
            //Preparamos la sentencia SQL apartir de la BD que usaremos y la sentencia SQL que ejecutaremos
            ps = this.getCon().prepareCall(sql);
            //Ejecutamos el cambio
            ps.executeUpdate();
        }
        catch(Exception e){
            //En caso de un error lo imprimimos
            e.printStackTrace();
        }
    }
    
}
