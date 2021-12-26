package com.controlador;

import com.modelo.*;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "cambiarUsu", urlPatterns = {"/cambiarUsu"})
public class cambiarUsu extends HttpServlet {
    OpcUsuario auxiliar = new OpcUsuario();
    
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
        Usuario usu = new Usuario();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String contranueva = request.getParameter("contranueva");
        String contravieja = request.getParameter("contravieja");
        Part perfil = request.getPart("perfil");
        Part portada = request.getPart("portada");
        InputStream bytesPerfil = null;
        InputStream bytesPortada = null;
        System.out.println(contranueva);
        System.out.println(contravieja);
        System.out.println(nombre);
        System.out.println(apellido);
        System.out.println(telefono);
        if(perfil.getSize() != 0){
            bytesPerfil = perfil.getInputStream();
            System.out.println("Hay perfil");
        }
        else{
            try{
                Usuario datosPerfil = new Usuario();
                HttpSession objSesion = request.getSession();
                int id = Integer.parseInt(objSesion.getAttribute("id").toString());
                datosPerfil = auxiliar.iniciarUsuario(id);
                bytesPerfil = datosPerfil.getPerfil();
                System.out.println("Perfil antiguo " + id + datosPerfil.getPerfil());
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        if(portada.getSize() != 0){
            bytesPortada = portada.getInputStream();
            System.out.println("Hay portada");
        }
        else{
            try{
                Usuario datosPortada = new Usuario();
                HttpSession objSesion = request.getSession();
                int id = Integer.parseInt(objSesion.getAttribute("id").toString());
                datosPortada = auxiliar.iniciarUsuario(id);
                bytesPortada = datosPortada.getPortada();
                System.out.println("Portada antigua " + id + datosPortada.getPortada());
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        try{
            Cifrado objCifrar = new Cifrado();
            String contraViejaBD = objCifrar.encriptar(contravieja);
            String contraNuevaBD = objCifrar.encriptar(contranueva);
            HttpSession objSesion = request.getSession();
            int idUser = Integer.parseInt(objSesion.getAttribute("id").toString());
            boolean verificado = auxiliar.validarContrasena(idUser, contraViejaBD);
            if(verificado == true){
                try{
                    usu.setNombre(nombre);
                    usu.setApellido(apellido);
                    usu.setContraUsu(contraNuevaBD);
                    usu.setPerfil(bytesPerfil);
                    usu.setPortada(bytesPortada);
                    usu.setTelefono(telefono);
                    usu.setIdUsu(idUser);

                    auxiliar.actualizarUsuario(usu);
                    response.getWriter().write("Listo");
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                response.getWriter().write("No coinciden");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
