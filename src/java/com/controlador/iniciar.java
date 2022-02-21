package com.controlador;

import com.modelo.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IrminDev
 * 
 * Servlet para iniciar sesión encargado de verificar las credenciales introducidas en el formulario de inicio de sesión
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "iniciar", urlPatterns = {"/iniciar"})
public class iniciar extends HttpServlet {
    //Objeto usuario que nos será útil para ejecutar ciertos métodos
    OpcUsuario auxiliar = new OpcUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        System.out.println("Entrando a iniciar sesion");
        
        //Obtenemos las credenciales proporcionadas por el usuario
        String correo = request.getParameter("correol");
        String contra = request.getParameter("contral");
        
        //Abrimos un try-catch para el cifrado de la contraseña
        try{
            
            System.out.println("Cifrando contraseña");
            
            //Creamos el objeto cifrar
            Cifrado cifrar = new Cifrado();
            
            //Obtenemos la contraseña ya cifrada
            String contraEnBD = cifrar.encriptar(contra);
            
            //Obtenemos los datos de la BD que correspondan con el usuario y contraseña porpocionados
            int datos[] = auxiliar.iniciarSesion(correo, contraEnBD);
            System.out.println("Datos obtenidos");
            
            //Si el id de usuario es diferente de 0 (proporcionado por el método iniciarSesión) continuamos el flujo
            if(datos[1] != 0){
                //Cambiamos el estado del usuario que inició sesión (de inactivo a activo)
                auxiliar.cambiarEstado(datos[0]);
                System.out.println("Checando tipo de usuario");
                
                //Comprobamos si el usuario es del tipo usuario
                if(datos[1] == 1){
                    System.out.println("Usuario tipo usuario");
                    
                    //Instanciamos la clase usuario para guardar la info del usuario que obtendremos mediante el método iniciarUsuario
                    Usuario data = auxiliar.iniciarUsuario(datos[0]);
                    
                    //Creamos una sesión
                    HttpSession objSesion = request.getSession();
                    
                    //Guardamos toda la información en nuestra sesión
                    objSesion.setAttribute("nombre", data.getNombre());
                    objSesion.setAttribute("apellido", data.getApellido());
                    objSesion.setAttribute("correo", data.getCorreoUsu());
                    objSesion.setAttribute("tipo", datos[1]);
                    objSesion.setAttribute("telefono", data.getTelefono());
                    objSesion.setAttribute("id", datos[0]);
                    
                    //Respondemos a javascript que se trata de un usuario tipo usuario
                    response.getWriter().write("Usuario");
                    
                    //Guardamos en la sesión a que tipo de usuario le mandaremos mensaje (revisar lógica de la mensajería)
                    objSesion.setAttribute("tipont", 2);
                }
                else{
                    //Si el usuario es un tipo trabajador, se realizan otras acciones
                    if(datos[1] == 2){
                        System.out.println("Usuario tipo trabajador");
                        
                        //Instanciamos la clase trabajador para guardar los datos del trabajador
                        Trabajador traba = auxiliar.datosAntiguosTrabajador(datos[0]);
                        
                        //Creamos su sesión
                        HttpSession objSesion = request.getSession();
                        
                        //Guardamos la información básica del trabajador en la sesión
                        objSesion.setAttribute("id", datos[0]);
                        
                        //Respondemos a javascript que se trata de un usuario tipo trabajador
                        response.getWriter().write("Trabajador");
                        
                        //Guardamos en la sesión a que tipo de usuario le mandaremos mensaje (revisar lógica de la mensajería)
                        objSesion.setAttribute("tipont", 1);
                        
                        //Si el campo de su región no está vació, significa que finalizó su registro 
                        if(traba.getRegionNombre() != null){
                            objSesion.setAttribute("descripcion", traba.getDescripcion());
                           
                        }
                        //Si el campo de descripción está vacío, significa que no finalizó el registro, por lo tanto lo redireccionamos
                        else{
                            response.getWriter().write("Incompleto");
                            
                        }
                    }
                }
            }
            else{
                //Si las credenciales no coincidieron con ninguna busqueda, le respondemos al javascript el mensaje
                response.getWriter().write("Las credenciales introducidas no coinciden o el usuario no está dado de alta.");
            }
        }
        catch(Exception e){
            //Imprimimos posibles excepciones
            System.out.println("Error");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
