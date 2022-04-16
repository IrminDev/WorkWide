package com.controlador;

import com.modelo.Cifrado;
import com.modelo.CodeFactory;
import com.modelo.OpcUsuario;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IrminDev
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "verificarCodigo", urlPatterns = {"/verificarCodigo"})
public class verificarCodigo extends HttpServlet {
         OpcUsuario AUX = new OpcUsuario();

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
              
                  String codigo = "";
                  try{
                           int id = Integer.parseInt(request.getSession().getAttribute("id_prov").toString());
                  
                           for(int i=0; i<8; i++){
                                    codigo += request.getParameter("char" + (i+1));
                           }

                           CodeFactory token = AUX.getToken(id);
                           if(codigo.equals(new Cifrado().desencriptar(token.getUniqueId()))){
                                    if(token.getExp().after(new Timestamp(System.currentTimeMillis()))){
                                             response.getWriter().write("Coinciden");
                                             HttpSession objSesion = request.getSession();
                                             objSesion.setAttribute("id", id);
                                    }
                                    else{
                                             response.getWriter().write("Expirado");
                                    }
                           }
                           else{
                                    System.out.println(new Cifrado().desencriptar(token.getUniqueId()) + " " + codigo);
                                    response.getWriter().write("No coinciden");
                           }
                  }
                  catch(Exception e){
                           e.printStackTrace();
                  }
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }

}
