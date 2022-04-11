package com.webservices;

import com.modelo.OpcSolicitud;
import com.modelo.OpcTrabajos;
import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import com.modelo.Usuario;
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
@WebServlet(name = "contadoresAndroid", urlPatterns = {"/contadoresAndroid"})
public class contadoresAndroid extends HttpServlet {
    OpcTrabajos AUXT = new OpcTrabajos();
    OpcSolicitud AUXS = new OpcSolicitud();
    OpcUsuario AUXU = new OpcUsuario();

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
        
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        int id = Integer.parseInt(request.getParameter("id"));
        Trabajador trab = new Trabajador();
        Usuario usu = new Usuario();
        JSONObject obj = new JSONObject();
        
        int totalSoli = 0;
        int totalTrab = 0;
        
        if(tipo == 1){
            int datosT[] = AUXT.getContadoresUsuario(id);
            totalTrab = datosT[0] + datosT[1];
            int datosS[] = AUXS.getCountersUsuario(id);
            totalSoli = datosS[0];
            usu = AUXU.listarPerfilUsuario(id);
            obj.put("nombre", usu.getNombre() + " " + usu.getApellido());
            obj.put("correo", usu.getCorreoUsu());
            obj.put("telefono", usu.getTelefono());
            obj.put("region", "No aplica");
            obj.put("trabajo", "Empleador");
        }
        else{
            if(tipo == 2){
                int datosT[] = AUXT.getContadoresTrabajador(id);
                totalTrab = datosT[0] + datosT[1];
                int datosS[] = AUXS.getCountersTrabajador(id);
                totalSoli = datosS[0];
                trab = AUXU.datosAntiguosTrabajador(id);
                obj.put("nombre", trab.getNombre() + " " + trab.getApellido());
                obj.put("correo", trab.getCorreoUsu());
                obj.put("telefono", trab.getTelefono());
                obj.put("region", trab.getRegionNombre());
                obj.put("trabajo", trab.getTrabajoNombre());
            }
        }
        
        
        obj.put("numSoli", totalSoli);
        obj.put("numTrab", totalTrab);
        
        response.getWriter().write(obj.toString());        
        PrintWriter pw = response.getWriter();
        pw.write(obj.toJSONString());
        pw.print(obj.toJSONString());
        System.out.println(obj.toJSONString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
