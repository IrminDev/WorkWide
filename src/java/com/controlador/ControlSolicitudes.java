package com.controlador;

import com.modelo.OpcSolicitud;
import com.modelo.OpcUsuario;
import com.modelo.Solicitud;
import com.modelo.Trabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IRMIN
 */
@WebServlet(name = "ControlSolicitudes", urlPatterns = {"/ControlSolicitudes"})
public class ControlSolicitudes extends HttpServlet {
    OpcUsuario aux = new OpcUsuario();
    OpcSolicitud auxSoli = new OpcSolicitud();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String id = request.getParameter("idEnviar");
        if(id != null){
            Trabajador traba = aux.datosAntiguosTrabajador(Integer.parseInt(id));
            HttpSession objTmp = request.getSession();
            objTmp.setAttribute("correoReceptor", traba.getCorreoUsu());
            request.getRequestDispatcher("usuario/Form/solicitud.jsp").forward(request, response);
            System.out.println("Debe redirigir");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion != null){
            if(accion.equals("enviar")){
                String correoRem = request.getParameter("correo");
                String correoDes = request.getParameter("corrreo2");
                String titulo = request.getParameter("titulo");
                String descripcion = request.getParameter("description");
                String cadFechaIn = request.getParameter("fecha");
                String cadFechaFin = request.getParameter("fecha2");
                try {
                    Date fechaInit = Date.valueOf(cadFechaIn);                
                    Date fechaFin = Date.valueOf(cadFechaFin);
                    int idDes = aux.obtenerIdCoreeo(correoDes);
                    HttpSession objSesion = request.getSession();
                    int idEmisor = Integer.parseInt(objSesion.getAttribute("id").toString());
                    System.out.println(idDes);
                    System.out.println(idEmisor);
                    Solicitud soli = new Solicitud();
                    soli.setDescripcion(descripcion);
                    soli.setFin(fechaFin);
                    soli.setIdEmisor(idEmisor);
                    soli.setIdReceptor(idDes);
                    soli.setInicio(fechaInit);
                    soli.setTitulo(titulo);
                    auxSoli.altaSolicitud(soli);
                    response.sendRedirect("ControlUsuarios?accion=Perfiles");
                } catch (Exception ex) {
                    request.getRequestDispatcher("usuario/Form/solicitud.jsp").forward(request, response);
                }
                
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
