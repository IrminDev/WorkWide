package com.webservices;

import com.modelo.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IRMIN
 */
@WebServlet(name = "perfilAndroid", urlPatterns = {"/perfilAndroid"})
public class perfilAndroid extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "SELECT * FROM usuario WHERE id_usu=" + id;
        PreparedStatement ps;
        ResultSet rs;
        try{
            Conexion con = new Conexion();
            con.conectar();
            ps = con.getCon().prepareCall(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                byte[] content = rs.getBytes(7);
                response.setContentType("image/jpeg");
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                System.out.print("Mostrado: " + id);
            }
            else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                System.out.print("Error: " + id);
            }
        }
        catch(Exception e){
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
