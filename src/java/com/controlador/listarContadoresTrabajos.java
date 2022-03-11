package com.controlador;

import com.modelo.OpcTrabajos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "listarContadoresTrabajos", urlPatterns = {"/listarContadoresTrabajos"})
public class listarContadoresTrabajos extends HttpServlet {
    OpcTrabajos AUX = new OpcTrabajos();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Contadores para solicitudes y trabajos
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession objSesion = request.getSession();
        int tipont = Integer.parseInt(objSesion.getAttribute("tipont").toString());
        int datos[] = {0,0,0};
        if(tipont ==  1){
            datos = AUX.getContadoresTrabajador(Integer.parseInt(objSesion.getAttribute("id").toString()));
        }
        else{
            datos = AUX.getContadoresUsuario(Integer.parseInt(objSesion.getAttribute("id").toString()));
        }
        String output = "<div class=\"cards\">\n" +
"                    <div class=\"card-sigle\">\n" +
"                        <div>\n" +
"                            <h1>" + datos[0] + "</h1>  \n" +
"                            <span>Pendientes</span>\n" +
"                        </div>\n" +
"                        <div>\n" +
"                            <span class=\"las la-users\"></span> \n" +
"                        </div>\n" +
"                    </div>    \n" +
"                        <div class=\"card-sigle\" id=\"entrenadores\">\n" +
"                        <div>\n" +
"                            <h1>" + datos[1] + "</h1>  \n" +
"                            <span>En progreso</span>\n" +
"                        </div>\n" +
"                        <div>\n" +
"                            <span class=\"las la-users\"></span> \n" +
"                        </div>\n" +
"                        </div>    \n" +
"                    <div class=\"card-sigle\">\n" +
"                        <div>\n" +
"                            <h1>" + datos[2] + "</h1>  \n" +
"                            <span>Completados</span>\n" +
"                        </div>\n" +
"                        <div>\n" +
"                            <span class=\"las la-clipboard-list\"></span> \n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>  ";
        
        response.getWriter().write(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
