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
 * Servlet dedicado exclusivamente para el envío de mensajes, así como la enciptación de los mismos
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "enviarMsg", urlPatterns = {"/enviarMsg"})
public class enviarMsg extends HttpServlet {
    
    //Instanciamos las clases que nos serán útiles para ejecutar ciertos métodos
    Cifrado DES = new Cifrado();
    OpcChat aux= new OpcChat();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Configuramos el request(petición del servlet) en caracteres UTF-8
        request.setCharacterEncoding("UTF-8");
        
        //Obtenemos la información del chat acerca del mensaje y a quién será enviado
        String msgPlain = request.getParameter("message");
        String receptor = request.getParameter("receptor");
        
        //Instanciamos la sesión y obtenemos la sesión si es que hay alguna activa
        HttpSession objSesion = request.getSession();
        
        //Creamos un try-ctach para enciptar el mensaje
        try{
            //Guardamos el mensaje encriptado en una variable
            String mensaje = DES.encriptar(msgPlain);
            
            //Instanciamos la clase mensaje para guardar la info del mensaje que será subida ala BD
            Mensaje msg = new Mensaje();
            //Colocamos el id del emisor al ID asociado a la sesión
            msg.setIdEmisor(Integer.parseInt(objSesion.getAttribute("id").toString()));
            //Colocamos el id del recpetor recuperado a través de la´página del chat
            msg.setIdReceptor(Integer.parseInt(receptor));
            
            //Colocamos en el mensaje nuestro mensaje encriptado
            msg.setMensaje(mensaje);
            
            //Enviamos el mensaje
            aux.enviarMsg(msg);
        }
        catch(Exception e){
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
