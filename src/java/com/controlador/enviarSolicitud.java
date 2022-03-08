
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
 * 
 * Servlet encargado de enviar solicitudesy tomar los datos enviados por el formulario de solicitud
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "enviarSolicitud", urlPatterns = {"/enviarSolicitud"})
public class enviarSolicitud extends HttpServlet {
    
    //Objetos útiles para la ejecución de métodos
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
        //Codificiación UTF-8 del request y response
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Datos del formulario
        String correoDes = request.getParameter("corrreo2");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("description");
        String cadFechaIn = request.getParameter("fecha");
        String cadFechaFin = request.getParameter("fecha2");
        
        System.out.print(cadFechaIn);
        System.out.print(cadFechaFin);
        
        try {
            
            //Convertimos las fechas a formato de fecha de SQL
            Date fechaInit = Date.valueOf(cadFechaIn);
            Date fechaFin = Date.valueOf(cadFechaFin);
            
            System.out.println(fechaInit);
            System.out.println(fechaFin);
            
            //Obtenemos IDs del receptor y emisor, respectivamente
            int idDes = aux.obtenerIdCoreeo(correoDes);
            HttpSession objSesion = request.getSession();
            int idEmisor = Integer.parseInt(objSesion.getAttribute("id").toString());
            
            //Creamosel objeto solicitud
            Solicitud soli = new Solicitud();
            
            //Guardamos los datos en el objeto solicitud
            soli.setDescripcion(descripcion);
            soli.setFin(fechaFin);
            soli.setIdEmisor(idEmisor);
            soli.setIdReceptor(idDes);
            soli.setInicio(fechaInit);
            soli.setTitulo(titulo);
            
            //Damos de alta la solicitud
            auxSoli.altaSolicitud(soli);
            
            //Mandamos el estado de enviado
            response.getWriter().write("Enviado");
        } catch (Exception ex) {
            //Impresión de errores
            System.out.println(ex);
            response.getWriter().write(ex.toString());
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
