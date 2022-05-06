package com.webservices;

import com.google.gson.Gson;
import com.modelo.OpcSolicitud;
import com.modelo.Solicitud;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "listarSolicitudesAndroid", urlPatterns = {"/listarSolicitudesAndroid"})
public class listarSolicitudesAndroid extends HttpServlet {
    OpcSolicitud AUX =new OpcSolicitud();
    
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
        
        List<Solicitud> lista = new ArrayList<>();
        if(tipo == 1){
            lista = AUX.listarSoliUsuario(id);
            System.out.println(lista + " usuario");
        }
        else{
            if(tipo == 2){
                lista = AUX.listarSoliTrabajador(id);
                System.out.println(lista + " trabajador");
            }
        }
        
        int size = lista.size();
        
        for(int i=0; i<size; i++ ){
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            if(lista.get(i).getInicio().before(date)){
                AUX.rechazarSolicitud(lista.get(i).getIdSoli());
                lista.get(i).setEstado("Rechazada");
            }
        }
        
        String jsonOp = new Gson().toJson(lista);
            
        response.getWriter().write(jsonOp);

        System.out.println(jsonOp);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
