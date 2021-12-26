package com.controlador;

import com.modelo.*;
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
@WebServlet(name = "iniciar", urlPatterns = {"/iniciar"})
public class iniciar extends HttpServlet {
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
        System.out.println("Entrando a iniciar sesion");
        String correo = request.getParameter("correol");
        String contra = request.getParameter("contral");

        try{
            System.out.println("Cifrando contraseña");
            Cifrado cifrar = new Cifrado();
            String contraEnBD = cifrar.encriptar(contra);
            int datos[] = auxiliar.iniciarSesion(correo, contraEnBD);
            System.out.println("Datos obtenidos");
            if(datos[1] != 0){
                auxiliar.IniciarSesion(datos[0]);
                System.out.println("Checando tipo de usuario");
                if(datos[1] == 1){
                    System.out.println("Usuario tipo usuario");
                    Usuario data = auxiliar.iniciarUsuario(datos[0]);
                    HttpSession objSesion = request.getSession();
                    objSesion.setAttribute("nombre", data.getNombre());
                    objSesion.setAttribute("apellido", data.getApellido());
                    objSesion.setAttribute("correo", data.getCorreoUsu());
                    objSesion.setAttribute("tipo", datos[1]);
                    objSesion.setAttribute("telefono", data.getTelefono());
                    objSesion.setAttribute("id", datos[0]);
                    //response.sendRedirect("ControlUsuarios?accion=Perfiles");
                    response.getWriter().write("Usuario");
                    objSesion.setAttribute("tipont", 2);
                }
                else{
                    if(datos[1] == 2){
                        System.out.println("Usuario tipo trabajador");
                        Trabajador traba = auxiliar.datosAntiguosTrabajador(datos[0]);
                        HttpSession objSesion = request.getSession();
                        objSesion.setAttribute("id", datos[0]);
                        response.getWriter().write("Trabajador");
                        objSesion.setAttribute("tipont", 1);
                        if(traba.getRegionNombre() != null){
                            objSesion.setAttribute("descripcion", traba.getDescripcion());
                            //response.sendRedirect("trabajador/index/index.jsp");
                        }
                        else{
                            response.getWriter().write("Incompleto");
                            //response.sendRedirect("sign/complemento/completa.jsp");
                        }
                    }
                }
            }
            else{
                response.getWriter().write("Las credenciales introducidas no coinciden o el usuario no está dado de alta.");
            }
        }
        catch(Exception e){
            System.out.println("Error");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
