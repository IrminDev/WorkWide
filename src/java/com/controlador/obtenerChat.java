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
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "obtenerChat", urlPatterns = {"/obtenerChat"})
public class obtenerChat extends HttpServlet {
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
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int receptor = Integer.parseInt(request.getParameter("receptor"));
        HttpSession objSesion = request.getSession();
        int emisor = Integer.parseInt(objSesion.getAttribute("id").toString());
        String output = "";
        List<Mensaje> lista = aux.getMensajes(emisor, receptor);
        int size = lista.size();
        for(int i=0; i<size; i++){
            try{
                String mensaje = DES.desencriptar(lista.get(i).getMensaje());
                
                if(emisor == lista.get(i).getIdEmisor()){
                    output += "\n<div class=\"chat outgoing\">\n" +
"                    <div class=\"details\">\n" +
"                        <p>" + mensaje + "</p>\n" +
"                    </div>\n" +
"                </div>";
                }
                else{
                    output += "\n<div class=\"chat incoming\">\n" +
"                    <img src=\"../../ControladorImagen?id=" + receptor + "\" alt=\"perfil\" onerror=\"this.src='../../user.svg'\">\n" +
"                    <div class=\"details\">\n" +
"                        <p>" + mensaje + "</p>\n" +
"                    </div>\n" +
"                </div>";
                }
                
            }
            catch(Exception e){
                
            }
        }
        
        response.getWriter().write(output);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
