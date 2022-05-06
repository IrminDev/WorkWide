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
 * Servlet encargado de tomar todos los mensajes que se han enviado, así como de desencriptar los mismos y finalmente colocarlos en estructura de texto HTML
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "obtenerChat", urlPatterns = {"/obtenerChat"})
public class obtenerChat extends HttpServlet {
    
    //Creamos objetos que nos serán de utilidad para obtener los chats
    OpcChat aux = new OpcChat();
    Cifrado DES = new Cifrado();

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
        
        //Obtenemos el id de quien es el receptor del chat, o a quien le estaremos enviando mensajes
        int receptor = Integer.parseInt(request.getParameter("receptor"));
        
        //Obtenemos la sesión activa 
        HttpSession objSesion = request.getSession();
        
        //Obtenemos el id de usuario guardado en la sesión, oara saber quién estará enviando esos mensajes
        int emisor = Integer.parseInt(objSesion.getAttribute("id").toString());
        
        //Preparamos el string que guardará la información del chat
        String output = "";
        
        //Creamos una lista para obtener los mensajes en el orden que corresponden al emisor y receptor
        List<Mensaje> lista = aux.getMensajes(emisor, receptor);
        
        //Obtenemos el largo de la lista para el ciclo for
        int size = lista.size();
        
        //Creamos un ciclo for que se encargará de examinar cada mensaje
        for(int i=0; i<size; i++){
            //Creamos un try-catch para desencriptar el chat
            try{
                //Desencriptamos el mensaje que corresponde a la posición "i" de la lista
                String mensaje = DES.desencriptar(lista.get(i).getMensaje());
                
                //Si el id del emisor (el de la sesión activa) es igual al que corresponde en la base de datos, agregamos el mensaje con un estilo específico
                if(emisor == lista.get(i).getIdEmisor()){
                    
                    //Agregamos al string el nuevo mensaje con la estructura de HTML
                    output += "\n<div class=\"chat outgoing\">\n" +
"                    <div class=\"details\">\n" +
"                        <p>" + mensaje + "</p>\n" +
"                    </div>\n" +
"                </div>";
                }
                
                //En caso contrario, que el emisor para el chat abierto no sea quién envió el mensaje, agregamos el mensaje con otro estilo
                else{
                    
                    //Agregamos al string el nuevo mensaje con la estructura de HTML
                    output += "\n<div class=\"chat incoming\">\n" +
"                    <img src=\"../../perfilAndroid?id=" + receptor + "\" alt=\"perfil\" onerror=\"this.src='../../user.svg'\">\n" +
"                    <div class=\"details\">\n" +
"                        <p>" + mensaje + "</p>\n" +
"                    </div>\n" +
"                </div>";
                }
                
            }
            catch(Exception e){
                
            }
        }
        
        //Respondemos a javscript con el string que contiene a todos los mensajes (mirar archivo chat.js, en la función setInterval)
        response.getWriter().write(output);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
