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
@WebServlet(name = "cambiarTrab", urlPatterns = {"/cambiarTrab"})
public class cambiarTrab extends HttpServlet {
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
        Trabajador trab = new Trabajador();
        String nombre = request.getParameter("nombren");
        String apellido = request.getParameter("apellidon");
        String telefono = request.getParameter("telefonon");
        String contraNueva = request.getParameter("contranuevan");
        String contraVieja = request.getParameter("contraviejan");
        String descripcion = request.getParameter("descripcionn");
        descripcion = descripcion.trim();
        int region = Integer.parseInt(request.getParameter("ubicationn"));
        Part profile = null;
        Part banner = null;
        try{
            profile = request.getPart("perfiln");
            banner = request.getPart("bannern");
            System.out.println("Guardó las fotos");
            System.out.println(profile);
            System.out.println(banner);
        }
        catch(Exception e){
            System.out.println("No se pudieron guardar las imágenes");
        }

        InputStream bytesPerfil = null;
        InputStream bytesPortada = null;

        if(profile.getSize() != 0){
            System.out.println("El perfil no estaba vacío");
            bytesPerfil = profile.getInputStream();
        }
        else{
            try{
                System.out.println("Saca el perfil anteior");
                Trabajador datos;
                HttpSession objSesion = request.getSession();
                int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
                datos = auxiliar.datosAntiguosTrabajador(idTrabajador);
                bytesPerfil = datos.getPerfil();
            }
            catch(Exception e){
                System.out.println("Problema con la imagen de perfil antigua " + e);
            }
        }
        if(banner.getSize() != 0){
            System.out.println("La portada no estaba vacía");
            bytesPortada = banner.getInputStream();
        }
        else{
            try{
                System.out.println("Saca la portada anteior");
                Trabajador datos;
                HttpSession objSesion = request.getSession();
                int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
                datos = auxiliar.datosAntiguosTrabajador(idTrabajador);
                bytesPortada = datos.getPortada();
            }
            catch(Exception e){
                System.out.println("Problema con la imagen de portada antigua " + e);
            }
        }

        try{
            Cifrado objCifrar = new Cifrado();
            String contraViejaCifrada = objCifrar.encriptar(contraVieja);
            String contraNuevaCifrada = objCifrar.encriptar(contraNueva);
            HttpSession objSesion = request.getSession();
            int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
            boolean verificado = auxiliar.validarContrasena(idTrabajador, contraViejaCifrada);
            if(verificado == true){
                try{
                    trab.setNombre(nombre);
                    trab.setApellido(apellido);
                    trab.setDescripcion(descripcion);
                    trab.setTelefono(telefono);
                    trab.setContraUsu(contraNuevaCifrada);
                    trab.setPerfil(bytesPerfil);
                    trab.setPortada(bytesPortada);
                    trab.setRegion(region);
                    trab.setIdUsu(idTrabajador);
                    trab.setDescripcion(trab.getDescripcion().replace("\n", "<br>"));
                    auxiliar.editarPerfilTrabajador(trab);
                    response.getWriter().write("Listo");
                }
                catch(Exception e){

                }
            }
            else{
                System.out.println("las contraseñas no coinciden");
                response.getWriter().write("No coinciden");
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
