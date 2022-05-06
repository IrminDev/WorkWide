package com.webservices;

import com.google.gson.Gson;
import com.modelo.OpcSolicitud;
import com.modelo.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "evaluarSolicitudesAndroid", urlPatterns = {"/evaluarSolicitudesAndroid"})
public class evaluarSolicitudesAndroid extends HttpServlet {
         OpcSolicitud AUX = new OpcSolicitud();

         @Override
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                  
         }

         @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                  String accion = request.getParameter("accion");
                  int id = Integer.parseInt(request.getParameter("id"));

                  if(accion.equals("2")){
                      Solicitud soli = AUX.getSolicitud(id);
                      AUX.aceptarSolicitud(soli);
                  }
                  else{
                      if(accion.equals("3")){
                          AUX.rechazarSolicitud(id);
                      }
                  }
                  
                   JSONObject obj = new JSONObject();
                   obj.put("state", "true");
            
                  response.getWriter().write(obj.toJSONString());

                  System.out.println(obj);
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }

}
