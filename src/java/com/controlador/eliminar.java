package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
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
 * Servlet de eliminar el perfil de un usuario, mediante la contraseña y su ID se verifica que la información sea la correcta
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "eliminar", urlPatterns = {"/eliminar"})
public class eliminar extends HttpServlet {
    
    //Instanciamos la clase opcUsuario para ejecutar métodos que nos serán útiles
    OpcUsuario aux = new OpcUsuario();

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
        
        //Obtenemos la contraseña del usuario
        String contra = request.getParameter("contran");
        
        //Creamos un boolean que nos indicará si la contraseña coincide o no
        boolean verificar = false;
        
        //Obtenemos la sesión del usuario
        HttpSession objSesion = request.getSession();
        
        //Obtenemos el id de usuario que será eliminado
        int idUsuario = Integer.parseInt(objSesion.getAttribute("id").toString());
        
        //Abrimos un try ctach para cifrar la contraseña
        try{
            //Instanciamos la clase cifrado para utilizar sus métodos
            Cifrado cifrar = new Cifrado();
            //Encriptamos la contraseña proporcionada
            String contraEnBD = cifrar.encriptar(contra);

            //Creamos un nuevo try catch para ahora comprobar que la información coincide
            try{
                //Verificamos si la información es correcta
                verificar = aux.validarContrasena(idUsuario, contraEnBD);
                
                //En caso verdarero se elimina el perfil
                if(verificar == true){
                    aux.eliminarPerfil(idUsuario);
                    //Destruimos la sesión
                    objSesion.invalidate();
                    //Respondemos a javascript con el mensaje (revisar el archivo eliminar.js)
                    response.getWriter().write("Listo");
                }
                else{
                    //En caso que la información no sea consistente, respondemos a javascript con otro mensaje (revisar el archivo eliminar.js)
                    response.getWriter().write("No pasó");
                }
            }
            catch(Exception e){
                //Imprimimos posibles excepciones
                e.printStackTrace();
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
