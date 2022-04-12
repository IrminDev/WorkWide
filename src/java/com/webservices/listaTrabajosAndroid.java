package com.webservices;

import com.google.gson.Gson;
import com.modelo.OpcTrabajos;
import com.modelo.Trabajo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "listaTrabajosAndroid", urlPatterns = {"/listaTrabajosAndroid"})
public class listaTrabajosAndroid extends HttpServlet {
    OpcTrabajos AUX = new OpcTrabajos();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        int id = Integer.parseInt(request.getParameter("id"));

        List<Trabajo> lista = new ArrayList<>();
        if(tipo == 1){
            lista = AUX.listarTrabajosUsuario(id);
        }
        else{
            if(tipo == 2){
                lista = AUX.listarTrabajosTrabajador(id);
            }
        }
        
        int size = lista.size();        
        for(int i = 0; i<size; i++){
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                if(lista.get(i).getFechaFin().before(date)){
                    // AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 3);
                    lista.get(i).setEstado("Finalizado");
                }
                else{
                    if(lista.get(i).getFechaInicio().after(date)){
                        // AUX.cambiarEstado(lista.get(i).getIdTrabajo(), 2);
                        lista.get(i).setEstado("En progreso");
                    }
                }
            }
        String jsonOp = new Gson().toJson(lista);
            
        response.getWriter().write(jsonOp);

        PrintWriter pw = response.getWriter();
        pw.print(jsonOp);
        System.out.println(jsonOp);
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
