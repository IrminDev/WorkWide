package com.controlador;

import com.modelo.*;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author IrminDev
 * 
 * Servlet para hacer cambios en el usuario mediante la información proporcionada en el formulario de cambios para el usuario
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "cambiarUsu", urlPatterns = {"/cambiarUsu"})
public class cambiarUsu extends HttpServlet {
    
    //Instanciamos la clase opcUsuario para utilizar ciertos métodos que nos serán útiles
    OpcUsuario auxiliar = new OpcUsuario();

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
        
        //Instanciamos la clase usuario donde guardaremos toda la información nueva
        Usuario usu = new Usuario();
        
        //Recuperamos los datos proporcionado en el formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String contranueva = request.getParameter("contranueva");
        String contravieja = request.getParameter("contravieja");
        //Preparamos un objeto Part para los archivos de imágen que serán guardados en bytes
        Part perfil = request.getPart("perfil");
        Part portada = request.getPart("portada");
        
        //Declaramos los bytes que usaremos temporalmente para convertir el objeto Part
        InputStream bytesPerfil = null;
        InputStream bytesPortada = null;

        //Si hay imágen de perfil, convertimos
        if(perfil.getSize() != 0){
            //Convertimos el objeto part del perfil en bytes
            bytesPerfil = perfil.getInputStream();
            System.out.println("Hay perfil");
        }
        else{
            //En caso contrario, abrimos un try-catch
            try{
                //Instanciamos la clase usuario donde guardaremos datos temporalmente
                Usuario datosPerfil = new Usuario();
                
                //Instanciamos la clase sesión y obtenemos la sesión del usuario
                HttpSession objSesion = request.getSession();
                
                //Obtenemos el id de usuario asociado con la sesión
                int id = Integer.parseInt(objSesion.getAttribute("id").toString());
                
                //Obtenemos los datos de ese usuario
                datosPerfil = auxiliar.iniciarUsuario(id);
                
                //Tomamos su foto de perfil y la guardamos en bytes
                bytesPerfil = datosPerfil.getPerfil();

            }
            catch(Exception e){
                //Si hay un error, lo imprimos
                System.out.println(e);
            }
        }
        
        //Si hay imágen de portada, convertimos
        if(portada.getSize() != 0){
            //Convertimos el objeto part del portada en bytes
            bytesPortada = portada.getInputStream();
        }
        else{
            //En caso contrario, abrimos un try-catch
            try{
                //Instanciamos la clase usuario donde guardaremos datos temporalmente
                Usuario datosPortada = new Usuario();
                
                //Instanciamos la clase sesión y obtenemos la sesión del usuario
                HttpSession objSesion = request.getSession();
                
                //Obtenemos el id de usuario asociado con la sesión
                int id = Integer.parseInt(objSesion.getAttribute("id").toString());
                //Obtenemos los datos de ese usuario
                datosPortada = auxiliar.iniciarUsuario(id);
                //Tomamos su foto de portada y la guardamos en bytes
                bytesPortada = datosPortada.getPortada();
            }
            catch(Exception e){
                //Si hay un error, lo imprimos
                System.out.println(e);
            }
        }

        //Creamos un try-catch para cifrar la contraseña
        try{
            //Instanciamos la clase cifrado
            Cifrado objCifrar = new Cifrado();
            
            //Encriptamos ambas contraseñas, la nueva y la vieja
            String contraViejaBD = objCifrar.encriptar(contravieja);
            String contraNuevaBD = objCifrar.encriptar(contranueva);
            
            //Obtenemos la sesión del usuario
            HttpSession objSesion = request.getSession();
            
            //Obtenemos el id del usuario apartir de la sesión
            int idUser = Integer.parseInt(objSesion.getAttribute("id").toString());
            
            //Verificamos que la contraseña antigua sea coincidiente con la guardada en la BD
            boolean verificado = auxiliar.validarContrasena(idUser, contraViejaBD);
            
            //Si la contraseña si coincide, se empiezan a realizar los cambios
            if(verificado == true){
                
                //Abrimos un try-catch para hacer los cambios en la BD
                try{
                    //Guardamos toda la información en el objeto usuario
                    usu.setNombre(nombre);
                    usu.setApellido(apellido);
                    usu.setContraUsu(contraNuevaBD);
                    usu.setPerfil(bytesPerfil);
                    usu.setPortada(bytesPortada);
                    usu.setTelefono(telefono);
                    usu.setIdUsu(idUser);
                    
                    //Editamos el perfil copn la información proporcionada
                    auxiliar.actualizarUsuario(usu);
                    //Mandamos el mensaje de listo al javascript (revisar el archivo cambiarUsuario.js)
                    response.getWriter().write("Listo");
                }
                catch(Exception e){
                    //Si hay erores, los imprimimos en consola
                    System.out.println(e);
                }
            }
            else{
                //Si las contraseñas no coinciden, mandamos un mensaje a javascript (revisar el archivo cambiarTrabajador.js
                response.getWriter().write("No coinciden");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
