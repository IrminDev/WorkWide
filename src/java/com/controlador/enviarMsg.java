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
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "enviarMsg", urlPatterns = {"/enviarMsg"})
public class enviarMsg extends HttpServlet {
    Cifrado DES = new Cifrado();
    OpcChat aux= new OpcChat();

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
        request.setCharacterEncoding("UTF-8");
        String msgPlain = request.getParameter("message");
        String receptor = request.getParameter("receptor");
        
        HttpSession objSesion = request.getSession();
        
        try{
            String mensaje = DES.encriptar(msgPlain);
            Mensaje msg = new Mensaje();
            msg.setIdEmisor(Integer.parseInt(objSesion.getAttribute("id").toString()));
            msg.setIdReceptor(Integer.parseInt(receptor));
            msg.setMensaje(mensaje);
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
