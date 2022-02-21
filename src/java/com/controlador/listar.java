package com.controlador;

import com.modelo.*;
import java.io.IOException;
import java.util.List;
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
 * Servlet para listar a los usuarios en la pa´gina de chat.jsp, en este servlet se listan los usuarios y el útlimo mensaje que se compartió.
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "listar", urlPatterns = {"/listar"})
public class listar extends HttpServlet {
    
    //Creamos nuestros objetos que serán útiles para ejecutar ciertos métodos
    OpcUsuario aux = new OpcUsuario();
    OpcChat auxChat = new OpcChat();
    Cifrado DES = new Cifrado();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
        *
        * MÉTODO DENTRO DE doGet YA QUE PARA LISTAR LOS USUARIOS NO NECESITAMOS DE INFORMACIÓN DE UN FORMULARIO
        * 
        **/
        
        //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Obtenemos la sesión de nuestro usuario
        HttpSession objSesion = request.getSession();
        //Obtenemos el tipo de usaurio al que nos interesa listar (si es usuario vamos a listar a trabajadores y viceversa) y su ID de usuario
        int tipo = Integer.parseInt(objSesion.getAttribute("tipont").toString());
        int id = Integer.parseInt(objSesion.getAttribute("id").toString());
        
        //Creamos una lista para tomar a todos los usuarios, para ello mandamos el tipo de usuarios que vamos a listar, y una entrada de búsqueda, que en este caso no hay por ser la lista inicial
        List<Usuario> lista = auxChat.listarChats(tipo, "");
        //Obtenemos el tamaño de la lista, para optimizar el for
        int indice = lista.size();
        
        //Preparamos la salida, que recibirá javascript
        String output = "";
        
        //Creamos nuestro ciclo for para cada usuario
        for(int i = 0; i<indice; i++){
            //Si en la lista no estamos nosotros mismos, podemos continuar
            if(id != lista.get(i).getIdUsu()){
                
                //Obtenemos el último mensaje enviado por el usuario de la lista, para ello tomamos nuestro ID y el ID del usuario de la lista
                Mensaje msg = auxChat.ultimMsg(id, lista.get(i).getIdUsu());
                
                //Creamos las variables que servirán para listar al usuario
                String mensaje = "";
                String estado = "";
                
                //Si el mensaje es diferente de null o de un string vacío, continuamos el flujo
                if(msg.getMensaje() != null && !msg.getMensaje().equals("")){
                    //Intentamos desencriptar el mensaje
                    try{
                        //La variable mensaje antes delcarada será el mensaje desencriptado
                        mensaje = DES.desencriptar(msg.getMensaje());
                        //Si nosotros enviamos el mensaje, mostraremos un texto específico
                        if(id == msg.getIdEmisor()){
                            mensaje = "Tú: " + mensaje;
                        }
                        //En caos que nosotro no hayamos enviado el mensaje, se mostrará el nombre de quién lo envió y el mensaje
                        else{
                            mensaje = lista.get(i).getNombre() + ": " + mensaje;
                        }
                    }
                    //Si hay una excepción la imprimimos
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                
                //Si no hay mensaje, mostramos que no se ha compartido ningún mensaje con esa persona
                else{
                    mensaje = "No hay mensajes disponibles con " + lista.get(i).getNombre();
                }
                
                //Si el usuario no está activo (cerró sesión), el esatdo se verá reflejado en la lista
                if(!lista.get(i).getEstado().equals("Activo")){
                    estado = "offline";
                }
                
                //Si el mensaje final es muy largo, lo recortaremos
                if(mensaje.length() > 33){
                    mensaje = mensaje.substring(0, 33) + "...";
                }
                
                //Agregamos a la salida al usuario junto con el mensaje útlimo que se haya compartido
                output += "\n<a href=\"chat.jsp?id=" + lista.get(i).getIdUsu() + "\">\n" +
"                        <div class=\"content\">\n" +
"                            <img src=\"../../ControladorImagen?id=" + lista.get(i).getIdUsu() + "\" alt=\"user\" onerror=\"this.src='../../user.svg'\">\n" +
"                            <div class=\"details\">\n" +
"                                <span>" + lista.get(i).getNombre() + " " + lista.get(i).getApellido() + "</span>\n" +
"                                <p>" + mensaje + "</p>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <div class=\"status-dot " + estado + "\"><i class=\"fa fa-circle\"></i></div>\n" +
"                    </a>";
            }
        }
        
        //Mandamos a javascript la lista de usuarios para el chat (Revisar el archivo listar.js en la función setInterval)
        response.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
