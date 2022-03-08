package com.controlador;

import com.modelo.OpcSolicitud;
import com.modelo.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "listarSolicitudesUsuario", urlPatterns = {"/listarSolicitudesUsuario"})
public class listarSolicitudesUsuario extends HttpServlet {
    OpcSolicitud AUX = new OpcSolicitud();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Obtenemos la sesión de nuestro usuario
        HttpSession objSesion = request.getSession();
        //Obtenemos el tipo de usaurio al que nos interesa listar (si es usuario vamos a listar a trabajadores y viceversa) y su ID de usuario
        int tipont = Integer.parseInt(objSesion.getAttribute("tipont").toString());
        int id = Integer.parseInt(objSesion.getAttribute("id").toString());
        
        String output = "";
        //Si no es un usuario...
        if(tipont == 1){
            List<Solicitud> lista = AUX.listarSoliTrabajador(id);
            int index = lista.size();
            for(int i=0; i<index; i++){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                if(lista.get(i).getInicio().before(date)){
                    AUX.rechazarSolicitud(lista.get(i).getIdSoli());
                }
                else{
                    output += "<div class=\"container\">\n" +
        "                <div class=\"course\">\n" +
        "                    <div class=\"preview\">\n" +
        "                        <h6>Solicitud</h6>\n" +
        "                        <h2>" + lista.get(i).getNombreEmisor() + "</h2>\n" +
        "                        <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmisor() + "\" >Mensaje</a>\n" +
        "                    </div>\n" +
        "                    <div class=\"info\">\n" +
        "                        <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
        "                        <h3><span>Fecha de inicio: " + lista.get(i).getInicio() + "</span> </h3>\n" +
        "                        <h3>Fecha de finalización: " + lista.get(i).getFin() + "</h3>\n" +
        "                        <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
        "                        <a href=\"../../evaluarSolicitud?id=" + lista.get(i).getIdSoli() + "&accion=2\" class=\"btn\">Aceptar</a>\n" +
        "                        <a href=\"../../evaluarSolicitud?id=" + lista.get(i).getIdSoli() + "&accion=3\" class=\"btn2\">Rechazar</a>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";

                }
            }
        }
        else{
            List<Solicitud> lista = AUX.listarSoliUsuario(id);
            int size = lista.size();
            for(int i=0; i<size; i++ ){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                System.out.print(date + " junto con " + lista.get(i).getInicio());
                if(lista.get(i).getInicio().before(date)){
                    AUX.rechazarSolicitud(lista.get(i).getIdSoli());
                    output += "<div class=\"container\">\n" +
            "                    <div class=\"course\">\n" +
            "                        <div class=\"preview\">\n" +
            "                            <h6>Solicitud</h6>\n" +
            "                            <h2>" + lista.get(i).getNombreReceptor() + "</h2>\n" +
            "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdReceptor() + "\" >Mensaje</a>\n" +
            "                        </div>\n" +
            "                        <div class=\"info\">\n" +
            "                            <h6>Contacto</h6>\n" +
            "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
            "                            <h3>Estado: <span class=\"Status\">Rechazado</span></h3>\n" +
            "                            <h3><span>Fecha de inicio: </span>" + lista.get(i).getInicio() + "</h3>\n" +
            "                            <h3>Fecha de finalización: " + lista.get(i).getFin() + "</h3>\n" +
            "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
            "\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>";
                }
                else{
                    output += "<div class=\"container\">\n" +
            "                    <div class=\"course\">\n" +
            "                        <div class=\"preview\">\n" +
            "                            <h6>Solicitud</h6>\n" +
            "                            <h2>" + lista.get(i).getNombreReceptor() + "</h2>\n" +
            "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdReceptor() + "\" >Mensaje</a>\n" +
            "                        </div>\n" +
            "                        <div class=\"info\">\n" +
            "                            <h6>Contacto</h6>\n" +
            "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
            "                            <h3>Estado: <span class=\"Status\">" + lista.get(i).getEstado() + "</span></h3>\n" +
            "                            <h3><span>Fecha de inicio: </span>" + lista.get(i).getInicio() + "</h3>\n" +
            "                            <h3>Fecha de finalización: " + lista.get(i).getFin() + "</h3>\n" +
            "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
            "\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>";
                }
            }
        }
        
        response.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
