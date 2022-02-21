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

/**
 *
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "busqueda", urlPatterns = {"/busqueda"})
public class busqueda extends HttpServlet {
    OpcUsuario aux = new OpcUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        List<Trabajador> lista = aux.mostrarPerfiles();
        String output = "";
        int index = lista.size();
        for(int i = 0; i < index; i++){
            output += "\n<div class=\"card\">\n" +
"                            <div class=\"card__cover usu" + lista.get(i).getIdUsu() + "\" id=\"" + lista.get(i).getIdUsu() + "\"></div>\n" +
"                            <style>\n" +
"                                .card__cover.usu" + lista.get(i).getIdUsu() + "{\n" +
"                                    width: 100%;\n" +
"                                    height: 12rem;\n" +
"                                    background-image: linear-gradient(120deg, #37ecba7e 0%, #72afd398 100%), url(\"../../ControladorPortada?id=" + lista.get(i).getIdUsu() + "\");\n" +
"                                    background-size: cover;\n" +
"                                    clip-path: polygon(0 0, 100% 0, 100% 80%, 0% 100%);\n" +
"                                }\n" +
"                            </style>\n" +
"                            <div class=\"card__contents\">\n" +
"\n" +
"                                <!-- imagen-->\n" +
"                                <div class=\"card__user-pic\">\n" +
"                                    <img src=\"../../ControladorImagen?id=" + lista.get(i).getIdUsu() + "\" alt=\"perfil\">\n" +
"                                </div>\n" +
"                                <!-- datos del trabajador-->\n" +
"                                <h1 class=\"heading\">\n" +
"                                    <a href=\"perfil/perfil.jsp?id=" + lista.get(i).getIdUsu() + "\" class=\"heading--sup\">" + lista.get(i).getNombre() + " " + lista.get(i).getApellido()+ "</a>\n" +
"                                    <span class=\"heading--sub\">" + lista.get(i).getTrabajoNombre() + "</span>\n" +
"                                    <span class=\"heading--sub\">" + lista.get(i).getRegionNombre() + "</span>\n" +
"                                    <span class=\"heading--suc\">Contáctame a: " + lista.get(i).getCorreoUsu()+ "</span>\n" +
"\n" +
"                                </h1>\n" +
"                                <!-- botones-->\n" +
"                                <div class=\"btns\">\n" +
"                                    <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdUsu() + "\" class=\"btn btn--msg\">Mensaje</a>\n" +
"                                    <a href=\"../Form/solicitud.jsp?idEnviar=" + lista.get(i).getIdUsu() + "\" class=\"btn btn--follow\">Solicitud</a>\n" +
"\n" +
"                                </div>\n" +
"\n" +
"\n" +
"                            </div>\n" +
"                        </div>";
        }
        
        response.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String buscar = request.getParameter("searchKey");
        buscar += "%";
        List<Trabajador> perfiles = aux.busquedaPefiles(buscar);
        int indice = perfiles.size();
        String output = "";
        for(int i=0; i<indice; i++){
            output += "\n<div class=\"card\">\n" +
"                            <div class=\"card__cover usu" + perfiles.get(i).getIdUsu() + "\" id=\"" + perfiles.get(i).getIdUsu() + "\"></div>\n" +
"                            <style>\n" +
"                                .card__cover.usu" + perfiles.get(i).getIdUsu() + "{\n" +
"                                    width: 100%;\n" +
"                                    height: 12rem;\n" +
"                                    background-image: linear-gradient(120deg, #37ecba7e 0%, #72afd398 100%), url(\"../../ControladorPortada?id=" + perfiles.get(i).getIdUsu() + "\");\n" +
"                                    background-size: cover;\n" +
"                                    clip-path: polygon(0 0, 100% 0, 100% 80%, 0% 100%);\n" +
"                                }\n" +
"                            </style>\n" +
"                            <div class=\"card__contents\">\n" +
"\n" +
"                                <!-- imagen-->\n" +
"                                <div class=\"card__user-pic\">\n" +
"                                    <img src=\"../../ControladorImagen?id=" + perfiles.get(i).getIdUsu() + "\" alt=\"perfil\">\n" +
"                                </div>\n" +
"                                <!-- datos del trabajador-->\n" +
"                                <h1 class=\"heading\">\n" +
"                                    <a href=\"perfil/perfil.jsp?id=" + perfiles.get(i).getIdUsu() + "\" class=\"heading--sup\">" + perfiles.get(i).getNombre() + " " + perfiles.get(i).getApellido()+ "</a>\n" +
"                                    <span class=\"heading--sub\">" + perfiles.get(i).getTrabajoNombre() + "</span>\n" +
"                                    <span class=\"heading--sub\">" + perfiles.get(i).getRegionNombre() + "</span>\n" +
"                                    <span class=\"heading--suc\">Contáctame a: " + perfiles.get(i).getCorreoUsu()+ "</span>\n" +
"\n" +
"                                </h1>\n" +
"                                <!-- botones-->\n" +
"                                <div class=\"btns\">\n" +
"                                    <a href=\"#\" class=\"btn btn--msg\">Mensaje</a>\n" +
"                                    <a href=\"Form/solicitud.jsp?idEnviar=" + perfiles.get(i).getIdUsu() + "\" class=\"btn btn--follow\">Solicitud</a>\n" +
"\n" +
"                                </div>\n" +
"\n" +
"\n" +
"                            </div>\n" +
"                        </div>";
        }
        
        if(output.equals("")){
            output += "No hay usuarios coincidientes a tu búsqueda";
        }
        response.getWriter().write(output);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
