package com.webservices;

import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import java.io.IOException;
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

@WebServlet(name = "listarPerfilAndroid", urlPatterns = {"/listarPerfilAndroid"})
public class listarPerfilAndroid extends HttpServlet {

         @Override
         protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                  
         }

         @Override
         protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
                   //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
                  response.setContentType("text/plain");
                  response.setCharacterEncoding("UTF-8");
                  request.setCharacterEncoding("UTF-8");
                  
                  int id = Integer.parseInt(request.getParameter("id"));
                  
                  Trabajador trab = new OpcUsuario().listarPerfilTrabajador(id);
                  JSONObject obj = new JSONObject();
                  obj.put("nombre", trab.getNombre());
                  obj.put("apellido", trab.getApellido());
                  obj.put("telefono", trab.getTelefono());
                  obj.put("region", trab.getRegionNombre());
                  obj.put("descripcion", trab.getDescripcion());

                  response.getWriter().write(obj.toJSONString());
                  
                  System.out.println(obj.toJSONString());
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }
}
