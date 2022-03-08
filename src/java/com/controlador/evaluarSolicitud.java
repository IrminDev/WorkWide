package com.controlador;

import com.modelo.OpcSolicitud;
import com.modelo.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "evaluarSolicitud", urlPatterns = {"/evaluarSolicitud"})
public class evaluarSolicitud extends HttpServlet {
    OpcSolicitud AUX = new OpcSolicitud();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        int id = Integer.parseInt(request.getParameter("id"));
        
        if(accion.equals("2")){
            Solicitud soli = new Solicitud();
            soli = AUX.getSolicitud(id);
            AUX.aceptarSolicitud(soli);
            response.sendRedirect("trabajador/solicitudes/solicitudes.jsp");
        }
        else{
            if(accion.equals("3")){
                AUX.rechazarSolicitud(id);
                response.sendRedirect("trabajador/solicitudes/solicitudes.jsp");
            }
        }
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
