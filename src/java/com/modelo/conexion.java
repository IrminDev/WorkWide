package com.modelo;
import java.sql.*;
/**
 * 
 * @author IrminDev
 * Clase conexión encargada de conectarse a la base de datos donde se trabajará con su información
*/
public class conexion {
    //Atributo de conexión de la clase
    private Connection con;
    
    //Getter para obtener la conexión
    public Connection getCon() {
        return con;
    }
    
    //Método para conectarse a la base de datos mediante servicios web
    public void conectar() throws Exception{
        try{
           //Cragamos la clase con el driver que conectará el sistema a la BD
           Class.forName("com.mysql.jdbc.Driver");
           //Colocamos la información necesaria para hacer la conexión
           String url = "jdbc:mysql://localhost:3306/workwide?allowPublicKeyRetrieval=true&useSSL=false&zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
           String user = "root";
           String pass = "n0m3l0";
           //Obtenemos la conexión apartir de los datos que colocamos anteriormente
           con = DriverManager.getConnection(url, user, pass);
        }
        catch(ClassNotFoundException | SQLException e){
            //En caso de un error, lo pasamos
            throw e;
        }
    }
    
    //Método para desconectarse de la base de datos
    public void desconectar() throws Exception{
        try{
            //Si la conexión existe, se sigue con la secuencia
            if(con != null){
                //Si la conexión no esyá cerra, continua con la secuencia
                if(con.isClosed() == false){
                    //Cerramos la conexión
                    con.close();
                }
            }
        }
        catch(SQLException e){
            //En caso de una excepción, la pasamos
            throw e;
        }
    }
}
