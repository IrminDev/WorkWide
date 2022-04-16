package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IrminDev
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "cambiarContra", urlPatterns = {"/cambiarContra"})
public class cambiarContra extends HttpServlet {
         OpcUsuario AUX = new OpcUsuario();

         @Override
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                  
         }


         @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                  String contra = request.getParameter("contran");
                  
                  try {
                           contra = new Cifrado().encriptar(contra);
                           int id = Integer.parseInt(request.getSession().getAttribute("id").toString());
                           AUX.cambiarContra(id, contra);
                           request.getSession().invalidate();
                           response.getWriter().write("Listo");
                  } 
                  catch (Exception ex) {
                           Logger.getLogger(cambiarContra.class.getName()).log(Level.SEVERE, null, ex);
                           response.getWriter().write("Error");
                  }
                  
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }

}
