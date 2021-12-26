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
@WebServlet(name = "listar", urlPatterns = {"/listar"})
public class listar extends HttpServlet {
    OpcUsuario aux = new OpcUsuario();
    Cifrado DES = new Cifrado();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession objSesion = request.getSession();
        int tipo = Integer.parseInt(objSesion.getAttribute("tipont").toString());
        int id = Integer.parseInt(objSesion.getAttribute("id").toString());
        List<Usuario> lista = aux.listarChats(tipo, "");
        int indice = lista.size();
        String output = "";
        for(int i = 0; i<indice; i++){
            if(id != lista.get(i).getIdUsu()){
                Mensaje msg = aux.ultimMsg(id, lista.get(i).getIdUsu());
                String mensaje = "";
                String estado = "";
                if(msg.getMensaje() != null && !msg.getMensaje().equals("")){
                    try{
                        mensaje = DES.desencriptar(msg.getMensaje());
                        if(id == msg.getIdEmisor()){
                            mensaje = "Tú: " + mensaje;
                        }
                        else{
                            mensaje = lista.get(i).getNombre() + ": " + mensaje;
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    mensaje = "No hay mensajes disponibles con " + lista.get(i).getNombre();
                }
                if(!lista.get(i).getEstado().equals("Activo")){
                    estado = "offline";
                }
                if(mensaje.length() > 33){
                    mensaje = mensaje.substring(0, 33) + "...";
                }
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
