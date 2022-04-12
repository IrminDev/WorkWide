package com.controlador;

import com.modelo.OpcTrabajos;
import com.modelo.Trabajo;
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
@WebServlet(name = "listarTrabajos", urlPatterns = {"/listarTrabajos"})
public class listarTrabajos extends HttpServlet {
    OpcTrabajos AUX = new OpcTrabajos();

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
        
        if(tipont == 1){
            List<Trabajo> lista = AUX.listarTrabajosTrabajador(id);
            int size = lista.size();
            for(int i = 0; i<size; i++){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                if(lista.get(i).getFechaFin().before(date) && lista.get(i).getEstado().equals("Finalizado")){
                    AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 3);
                    output += "<div class=\"container\">\n" +
"                    <div class=\"course\">\n" +
"                        <div class=\"preview\">\n" +
"                            <h6>Trabajo</h6>\n" +
"                            <h2>" + lista.get(i).getNombreEpleador() + "</h2>\n" +
"                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleador() + "\" >Enviar mensaje</a>\n" +
"                        </div>\n" +
"                        <div class=\"info\">\n" +
"                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
"                            <h6>Estado: Finalizado</h6>\n" +
"                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
"                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
"                            <p class=\"p-trunc\">Descripción: </p>\n" +
"                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>";
                }
                else{
                    if(lista.get(i).getFechaInicio().after(date) && lista.get(i).getEstado().equals("En progreso")){
                        AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 2);
                        output += "<div class=\"container\">\n" +
    "                    <div class=\"course\">\n" +
    "                        <div class=\"preview\">\n" +
    "                            <h6>Trabajo</h6>\n" +
    "                            <h2>" + lista.get(i).getNombreEpleador() + "</h2>\n" +
    "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleador() + "\" >Enviar mensaje</a>\n" +
    "                        </div>\n" +
    "                        <div class=\"info\">\n" +
    "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
    "                            <h6>Estado: En progreso</h6>\n" +
    "                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
    "                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
    "                            <p class=\"p-trunc\">Descripción: </p>\n" +
    "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>";
                    }
                    else{
                        output += "<div class=\"container\">\n" +
    "                    <div class=\"course\">\n" +
    "                        <div class=\"preview\">\n" +
    "                            <h6>Trabajo</h6>\n" +
    "                            <h2>" + lista.get(i).getNombreEpleador() + "</h2>\n" +
    "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleador() + "\" >Enviar mensaje</a>\n" +
    "                        </div>\n" +
    "                        <div class=\"info\">\n" +
    "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
    "                            <h6>Estado: " + lista.get(i).getEstado() + "</h6>\n" +
    "                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
    "                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
    "                            <p class=\"p-trunc\">Descripción: </p>\n" +
    "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>";
                    }
                }
            }
        }
        else{
            if(tipont == 2){
                List<Trabajo> lista = AUX.listarTrabajosUsuario(id);
                int size = lista.size();
                for(int i = 0; i<size; i++){
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    if(lista.get(i).getFechaFin().before(date) && lista.get(i).getEstado().equals("Finalizado")){
                        AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 3);
                        output += "<div class=\"container\">\n" +
    "                    <div class=\"course\">\n" +
    "                        <div class=\"preview\">\n" +
    "                            <h6>Trabajo</h6>\n" +
    "                            <h2>" + lista.get(i).getNombreEpleado()+ "</h2>\n" +
    "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleado() + "\" >Enviar mensaje</a>\n" +
    "                        </div>\n" +
    "                        <div class=\"info\">\n" +
    "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
    "                            <h6>Estado: Finalizsdo</h6>\n" +
    "                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
    "                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
    "                            <p class=\"p-trunc\">Descripción: </p>\n" +
    "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>";
                    }
                    else{
                        if(lista.get(i).getFechaInicio().after(date) && lista.get(i).getEstado().equals("En progreso")){
                            AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 2);
                            output += "<div class=\"container\">\n" +
        "                    <div class=\"course\">\n" +
        "                        <div class=\"preview\">\n" +
        "                            <h6>Trabajo</h6>\n" +
        "                            <h2>" + lista.get(i).getNombreEpleado() + "</h2>\n" +
        "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleado() + "\" >Enviar mensaje</a>\n" +
        "                        </div>\n" +
        "                        <div class=\"info\">\n" +
        "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
        "                            <h6>Estado: En progreso</h6>\n" +
        "                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
        "                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
        "                            <p class=\"p-trunc\">Descripción: </p>\n" +
        "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>";
                        }
                        else{
                            output += "<div class=\"container\">\n" +
        "                    <div class=\"course\">\n" +
        "                        <div class=\"preview\">\n" +
        "                            <h6>Trabajo</h6>\n" +
        "                            <h2>" + lista.get(i).getNombreEpleado() + "</h2>\n" +
        "                            <a href=\"../chat/chat.jsp?id=" + lista.get(i).getIdEmpleado() + "\" >Enviar mensaje</a>\n" +
        "                        </div>\n" +
        "                        <div class=\"info\">\n" +
        "                            <h2>" + lista.get(i).getTitulo() + "</h2>\n" +
        "                            <h6>Estado: " + lista.get(i).getEstado() + "</h6>\n" +
        "                            <h6>Fecha de inicio: " + lista.get(i).getFechaInicio() + "</h6>\n" +
        "                            <h6>Fecha de fin: " + lista.get(i).getFechaFin() + "</h6>\n" +
        "                            <p class=\"p-trunc\">Descripción: </p>\n" +
        "                            <p class=\"p-trunc\">" + lista.get(i).getDescripcion() + "</p>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>";
                        }
                    }
                }
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
