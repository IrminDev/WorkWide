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
 * @author IrminDev
 * 
 * Servlet para la búsqueda de usuarios, que se hará mediante intervalos de tiempo de tal forma que se actualice la lista constantemente, así cómo poder realizar búsquedas mediante palabras clave
 */

//Anotación multipart para la comunicación con javascript
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "busqueda", urlPatterns = {"/busqueda"})
public class busqueda extends HttpServlet {
    //Instanciamos la clase opcUsuario para utilizar ciertos métodos que nos serán útiles
    OpcUsuario aux = new OpcUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /** 
        * 
        * ESTE MÉTODO SE ENCUENTRA EN EL doGet YA QUE SE EJECUTA SIN NECESIDAD DE RECIBIR INFORMACIÓN DE UN FORMULARIO
        * 
        */
        
        //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Creamos una lista con los trabajadores que vamos a mostrar
        List<Trabajador> lista = aux.mostrarPerfiles();
        
        //Preparamos la salida que será enviada a javascript
        String output = "";
        
        //Obtenemos el tamaño de la lista para optimizar el ciclo for
        int index = lista.size();
        
        //Inicio del ciclo for
        for(int i = 0; i < index; i++){
            //Creamos la tarjeta del trabajador por cada trabajador de la lista y lo agregamos al mensaje que será enviado a javascript
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
        
        //Una vez hayamos juntado toda la lsita de usurios en el output, lo enviaremos a javascript (revisar el archvio buscar.js en el método setInterval)
        response.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Obtenemos la palabra de búsqueda colocada en el buscador
        String buscar = request.getParameter("searchKey");
        
        //Le agregamos el comodín % para la búsqueda de SQL
        buscar += "%";
        
        //Creamos la lista de los trabajadores con aquellos trabajadores que coincidan con la búsqueda
        List<Trabajador> perfiles = aux.busquedaPefiles(buscar);
        
        //Obtenemos el tamaño de la lista para optimizar el ciclo for
        int indice = perfiles.size();
        
        //Preparamos la salida que será enviada a javascript
        String output = "";
        
        //Inicio del ciclo for
        for(int i=0; i<indice; i++){
            //Creamos la tarjeta del trabajador por cada trabajador de la lista y lo agregamos al mensaje que será enviado a javascript
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
            //En caso de que la lista de usuarios haya estado vacía, mandaremos el mensaje a javascript de que no hay trabajadores que coincidan con la búsqueda
            output += "No hay usuarios coincidientes a tu búsqueda";
        }
        
        //Mandamos el mensaje del output a javascript (revisar el archivo buscar.js en el método onKeyUp)
        response.getWriter().write(output);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
