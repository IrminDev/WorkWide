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
            System.out.println(id);
            List<Solicitud> lista = AUX.listarSoliTrabajador(id);
            int index = lista.size();
            for(int i=0; i<index; i++){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                if(lista.get(i).getInicio().before(date)){
                    
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
        "                        <a href=\"\" class=\"btn\">Aceptar</a>\n" +
        "                        <a href=\"\" class=\"btn2\">Rechazar</a>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";

                }
            }
        }
        else{
            List<Solicitud> lista = AUX.listarSoliUsuario(id);
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
