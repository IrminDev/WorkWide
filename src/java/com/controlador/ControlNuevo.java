
package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControlNuevo", urlPatterns = {"/ControlNuevo"})
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
