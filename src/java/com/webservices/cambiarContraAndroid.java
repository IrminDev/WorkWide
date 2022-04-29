package com.webservices;

import com.controlador.cambiarContra;
import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "cambiarContraAndroid", urlPatterns = {"/cambiarContraAndroid"})
public class cambiarContraAndroid extends HttpServlet {
    OpcUsuario AUX = new OpcUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contra = request.getParameter("contra");
        int id = Integer.parseInt(request.getParameter("id"));
        
        JSONObject obj =  new JSONObject();
                  
        try {
                  System.out.println(contra);
            String contraBd = new Cifrado().encriptar(contra);
            AUX.cambiarContra(id, contraBd);
            obj.put("bandera", "true");
        } 
        catch (Exception ex) {
            Logger.getLogger(cambiarContra.class.getName()).log(Level.SEVERE, null, ex);
            obj.put("bandera", "false");
        }
        
        response.getWriter().write(obj.toJSONString());
            
        PrintWriter pw = response.getWriter();
        pw.write(obj.toJSONString());
        pw.print(obj.toJSONString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
