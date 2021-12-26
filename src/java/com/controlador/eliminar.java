package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "eliminar", urlPatterns = {"/eliminar"})
public class eliminar extends HttpServlet {
    OpcUsuario aux = new OpcUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

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
        String contra = request.getParameter("contran");
        System.out.println("Eliminando..." + contra);
        boolean verificar = false;
        HttpSession objSesion = request.getSession();
        int idUsuario = Integer.parseInt(objSesion.getAttribute("id").toString());
        try{
            Cifrado cifrar = new Cifrado();
            String contraEnBD = cifrar.encriptar(contra);
            System.out.println(contraEnBD);
            System.out.println(contra);
            try{
                verificar = aux.validarContrasena(idUsuario, contraEnBD);
                if(verificar == true){
                    aux.eliminarPerfil(idUsuario);
                    objSesion.invalidate();
                    response.getWriter().write("Listo");
                }
                else{
                    response.getWriter().write("No pasó");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception e){

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
