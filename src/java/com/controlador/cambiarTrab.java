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
 * Servlet para hacer cambios en el trabajador mediante la información proporcionada en el formulario de cambios para el trabajador
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "cambiarTrab", urlPatterns = {"/cambiarTrab"})
public class cambiarTrab extends HttpServlet {
    
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
        
        //Instanciamos la clase trabajador donde guardaremos toda la información nueva
        Trabajador trab = new Trabajador();
        
        //Recuperamos los datos proporcionado en el formulario
        String nombre = request.getParameter("nombren");
        String apellido = request.getParameter("apellidon");
        String telefono = request.getParameter("telefonon");
        String contraNueva = request.getParameter("contranuevan");
        String contraVieja = request.getParameter("contraviejan");
        String descripcion = request.getParameter("descripcionn");
        //Si la decripción tiene espacios al principio o al final, nos deshacemos de ellos
        descripcion = descripcion.trim();
        int region = Integer.parseInt(request.getParameter("ubicationn"));
        //Preparamos un objeto Part para los archivos de imágen que serán guardados en bytes
        Part profile = null;
        Part banner = null;
        
        //Creamos un try-catch para guardar las imagenes proporcionadas en el formulario
        try{
            //Guardamos las imágenes en nustros objetos Part
            profile = request.getPart("perfiln");
            banner = request.getPart("bannern");

        }
        catch(Exception e){
            //Imprimos un me4nsaje si hubo un error
            System.out.println("No se pudieron guardar las imágenes");
        }

        //Declaramos los bytes que usaremos temporalmente para convertir el objeto Part
        InputStream bytesPerfil = null;
        InputStream bytesPortada = null;

        //Si hay imágen de perfil, convertimos
        if(profile.getSize() != 0){
            //Convertimos el objeto part del perfil en bytes
            bytesPerfil = profile.getInputStream();
        }
        else{
            //En caso contrario, abrimos un try-catch
            try{
                System.out.println("Saca el perfil anteior");
                
                //Instanciamos la clase trabajador donde guardaremos datos temporalmente
                Trabajador datos;
                
                //Instanciamos la clase sesión y obtenemos la sesión del usuario
                HttpSession objSesion = request.getSession();
                
                //Obtenemos el id de usuario asociado con la sesión
                int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
                //Obtenemos los datos de ese usuario
                datos = auxiliar.datosAntiguosTrabajador(idTrabajador);
                //Tomamos su foto de perfil y la guardamos en bytes
                bytesPerfil = datos.getPerfil();
            }
            catch(Exception e){
                //Si hubo un error al obtener la foto de perfil antigua, mostramos el siguiente mensaje
                System.out.println("Problema con la imagen de perfil antigua " + e);
            }
        }
        //Si hay imágen de portada, convertimos
        if(banner.getSize() != 0){
            //Convertimos el objeto part del portada en bytes
            bytesPortada = banner.getInputStream();
        }
        else{
            //En caso contrario, abrimos un try-catch
            try{
                System.out.println("Saca la portada anteior");
                
                //Instanciamos la clase trabajador donde guardaremos datos temporalmente
                Trabajador datos;
                
                //Instanciamos la clase sesión y obtenemos la sesión del usuario
                HttpSession objSesion = request.getSession();
                
                //Obtenemos el id de usuario asociado con la sesión
                int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
                //Obtenemos los datos de ese usuario
                datos = auxiliar.datosAntiguosTrabajador(idTrabajador);
                //Tomamos su foto de portada y la guardamos en bytes
                bytesPortada = datos.getPortada();
            }
            catch(Exception e){
                //Si hubo un error al obtener la foto de portada antigua, mostramos el siguiente mensaje
                System.out.println("Problema con la imagen de portada antigua " + e);
            }
        }

        //Creamos un try-catch para cifrar la contraseña
        try{
            //Instanciamos la clase cifrado
            Cifrado objCifrar = new Cifrado();
            
            //Encriptamos ambas contraseñas, la nueva y la vieja
            String contraViejaCifrada = objCifrar.encriptar(contraVieja);
            String contraNuevaCifrada = objCifrar.encriptar(contraNueva);
            
            //Obtenemos la sesión del usuario
            HttpSession objSesion = request.getSession();
            
            //Obtenemos el id del trabajador apartir de la sesión
            int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
            
            //Verificamos que la contraseña antigua sea coincidiente con la guardada en la BD
            boolean verificado = auxiliar.validarContrasena(idTrabajador, contraViejaCifrada);
            
            //Si la contraseña si coincide, se empiezan a realizar los cambios
            if(verificado == true){
                
                //Abrimos un try-catch para hacer los cambios en la BD
                try{
                    
                    //Guardamos toda la información en el objeto trabajador
                    trab.setNombre(nombre);
                    trab.setApellido(apellido);
                    trab.setDescripcion(descripcion);
                    trab.setTelefono(telefono);
                    trab.setContraUsu(contraNuevaCifrada);
                    trab.setPerfil(bytesPerfil);
                    trab.setPortada(bytesPortada);
                    trab.setRegion(region);
                    trab.setIdUsu(idTrabajador);
                    //En la descripción cambiamos los saltos de línea por etiquestas "<br>" y que se vea reflejado en el HTML
                    trab.setDescripcion(trab.getDescripcion().replace("\n", "<br>"));
                    
                    //Editamos el perfil copn la información proporcionada
                    auxiliar.editarPerfilTrabajador(trab);
                    
                    //Mandamos el mensaje de listo al javascript (revisar el archivo cambiarTrabajador.js)
                    response.getWriter().write("Listo");
                }
                catch(Exception e){

                }
            }
            else{
                //Si las contraseñas no coinciden, mandamos un mensaje a javascript (revisar el archivo cambiarTrabajador.js)
                System.out.println("las contraseñas no coinciden");
                response.getWriter().write("No coinciden");
            }
        }
        catch(Exception e){

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
