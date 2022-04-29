package com.webservices;

import com.modelo.Cifrado;
import com.modelo.CodeFactory;
import com.modelo.OpcUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
@WebServlet(name = "verificarCodigoAndroid", urlPatterns = {"/verificarCodigoAndroid"})
public class verificarCodigoAndroid extends HttpServlet {
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
              
        String codigo = request.getParameter("codigo");
        int id = Integer.parseInt(request.getParameter("id"));
        
        JSONObject obj = new JSONObject();
        
        try{
            CodeFactory token = AUX.getToken(id);
            if(codigo.equals(new Cifrado().desencriptar(token.getUniqueId()))){
                if(token.getExp().after(new Timestamp(System.currentTimeMillis()))){
                    obj.put("bandera", "true");
                }
                else{
                    obj.put("bandera", "false");
                }
            }
            else{
                System.out.println(new Cifrado().desencriptar(token.getUniqueId()) + " " + codigo);
                obj.put("bandera", "undefined");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            obj.put("bandera", "error");
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
