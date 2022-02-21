
package com.controlador;

import com.modelo.OpcSolicitud;
import com.modelo.OpcUsuario;
import com.modelo.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "enviarSolicitud", urlPatterns = {"/enviarSolicitud"})
public class enviarSolicitud extends HttpServlet {
    
    OpcUsuario aux = new OpcUsuario();
    OpcSolicitud auxSoli = new OpcSolicitud();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        
        String correoDes = request.getParameter("corrreo2");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("description");
        String cadFechaIn = request.getParameter("fecha");
        String cadFechaFin = request.getParameter("fecha2");
        
        try {
            System.out.println(cadFechaIn);
            System.out.println(cadFechaFin);
            System.out.println(correoDes);
            System.out.println(titulo);
            
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date fechaInitaux = df.parse(cadFechaIn);
            java.util.Date fechaFinaux = df.parse(cadFechaFin);
            
            
            Date fechaInit = new Date(fechaInitaux.getTime());            
            Date fechaFin = new Date(fechaFinaux.getTime()); 
            
            System.out.println("Fechas convertidas");
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
            System.out.println("Datos recibidos");
            response.getWriter().write("Enviado");
        } catch (Exception ex) {
            System.out.println(ex);
            response.getWriter().write(ex.toString());
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
