
package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "ControlNuevo", urlPatterns = {"/ControlNuevo"})

@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
public class ControlNuevo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        OpcUsuario auxiliar = new OpcUsuario();
        if(accion != null){
            if(accion.equals("MiPerfil")){
                HttpSession objSesion = request.getSession();
                int id = Integer.parseInt(objSesion.getAttribute("id").toString());
                List<Usuario> lista = auxiliar.listarPerfilUsuario(id);
                request.setAttribute("PerfilUsuario", lista);
                request.getRequestDispatcher("usuario/perfil/MiPerfil.jsp").forward(request, response);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        OpcUsuario auxiliar = new OpcUsuario();
        if(accion.equals("Eliminar")){
            response.sendRedirect("usuario/perfil/eliminar/EliminarPerfil.jsp");
        }
        else{
            if(accion.equals("Cerrar")){
                HttpSession objSesion = request.getSession();
                objSesion.invalidate();
                response.sendRedirect("index/index.jsp");
            }
            else{
                if(accion.equals("Cambiar")){
                    response.sendRedirect("usuario/perfil/editar/EditarPerfil.jsp");
                }
                else{
                    if(accion.equals("EliminarPerfil")){
                        String contra = request.getParameter("contran");
                        boolean verificar = false;
                        HttpSession objSesion = request.getSession();
                        int idUsuario = Integer.parseInt(objSesion.getAttribute("id").toString());
                        try{
                            Cifrado cifrar = new Cifrado();
                            String contraEnBD = cifrar.encriptar(contra);
                            try{
                                verificar = auxiliar.validarContrasena(idUsuario, contraEnBD);
                                if(verificar){
                                    auxiliar.eliminarPerfil(idUsuario);
                                    objSesion.invalidate();
                                    response.sendRedirect("index/index.jsp");
                                }
                                else{
                                    response.sendRedirect("usuario/perfil/eliminar/EliminarPerfil.jsp");
                                }
                            }
                            catch(Exception e){

                            }
                        }
                        catch(Exception e){

                        }
                    }
                    else{
                        if(accion.equals("HacerCambios")){
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

                                        objSesion.setAttribute("nombre", nombre);
                                        objSesion.setAttribute("apellido", apellido);
                                        objSesion.setAttribute("telefono", telefono);
                                        response.sendRedirect("ControlNuevo?accion=MiPerfil");
                                    }
                                    catch(Exception e){
                                        response.sendRedirect("ControlNuevo?accion=MiPerfil");
                                        System.out.println(e);
                                    }
                                }
                                else{
                                    System.out.println("No coinciden las contraseñas");
                                }
                            }
                            catch(Exception e){
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
