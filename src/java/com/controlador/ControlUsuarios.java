package com.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.modelo.*;
import java.io.InputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/*ESTE SERVLET SOLO SERÁ PARA EL CRUD DE USUARIOS SOLO SERÁN OCUPADOS LOS SIGUIENTES MÉTODOS

-REGISTRAR
-INICIAR SESION
-LISTAR USUARIOS
-COMPLEMENTAR REGISTRO
-EDITAR PERFIL
-ELIMINAR PERFIL

*/

@WebServlet(name = "ControlUsuarios", urlPatterns = {"/ControlUsuarios"})
//Si tienes problemas con el servlet para guardar/modificar imágenes checa el location del MultipartConfig y cambia el disco ("G:")
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024, maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class ControlUsuarios extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            if(request.getParameter("BtnRegistrar") != null){
                //Objeto usuario que guardará los datos del registro
                Usuario usu = new Usuario();
                
                //Captura de los datos del primer formulario de registro
                String nombre = request.getParameter("nombreR");
                String apellido = request.getParameter("apellidoR");
                String correo = request.getParameter("correoR");
                String contra = request.getParameter("contraR");
                int tipoUsu = Integer.parseInt(request.getParameter("tipoR"));
                String telefono = request.getParameter("telefonoR");
                
                //Arreglo que nos dará el estado del registro
                String estado[] = new String[2];
                
                //Creamos una sesion de usuario y otra que obtendrá los errores
                HttpSession objSesion = request.getSession();
                HttpSession objError = request.getSession();
                
                //Intentamos cifrar la contraseña
                try{
                    Cifrado ayuda = new Cifrado();
                    String nuevo = ayuda.encriptar(contra);
                    
                    //Intentamos dar de alta al usuario en la bd
                    try{
                        //Creamos el objeto que va a ayudarnos a dar de alta
                        OpcUsuario aux = new OpcUsuario();
                        
                        //Guardamos en el objeto usuario la información obtenida
                        usu.setNombre(nombre);
                        usu.setApellido(apellido);
                        usu.setCorreoUsu(correo);
                        usu.setTipoUsu(tipoUsu);
                        usu.setContraUsu(nuevo);
                        usu.setTelefono(telefono);
                        
                        //Ejecutamos el método registrar
                        estado = aux.registrar(usu);
                        System.out.println(estado[0] + estado[1]);
                        
                        //Si el usuario ya estaba registrado lo manda a que inicie sesión
                        if(estado[0].equals("registrado")){
                            response.sendRedirect("sign/identificate.jsp");
                        }
                        else{
                            //Si el usuario todavía no estaba registrado, creamos su sesión
                            objSesion.setAttribute("nombre", usu.getNombre());
                            objSesion.setAttribute("apellido", usu.getApellido());
                            objSesion.setAttribute("correo", usu.getCorreoUsu());
                            objSesion.setAttribute("tipo", usu.getTipoUsu());
                            objSesion.setAttribute("telefono", usu.getTelefono());
                            objSesion.setAttribute("id", estado[1]);
                            //Si el usuario es un trabajador, lo mandamos a completar su registro
                            if(tipoUsu == 2){
                                response.sendRedirect("sign/complemento/completa.jsp");
                            }
                            else{
                                //Si el usuario es un usuaerio, lo llevamos al lsitado de los trabajadores
                                if(tipoUsu == 1){
                                    response.sendRedirect("usuario/listado/Encuentra.jsp");
                                }
                            }
                        }
                        
                    }
                    catch(Exception e){
                        //Imprimimos los errores
                        objError.setAttribute("error", e);
                        response.sendRedirect("error.jsp");
                        System.out.println(e);
                        System.out.println(nuevo);
                    }
                }
                catch(Exception ex){
                    response.sendRedirect("index/index.jsp?");
                }
            }
            else{
                //Formulario completar registro
                if(request.getParameter("BtnCompletar") != null){
                    //Creamos un objeto trabajador ya que para este formulario solo serán trabajadores
                    Trabajador trab = new Trabajador();
                    //Obtenemos los datos primitivos de Java que están en el formulario
                    int trabajo = Integer.parseInt(request.getParameter("work"));
                    int region = Integer.parseInt(request.getParameter("ubication"));
                    
                    //Intentamos capturar las imágenes 
                    try{
                        //Guardamos en un objeto Part las imágenes
                        Part profile = request.getPart("profile");
                        Part banner = request.getPart("banner");
                        //Guardamos la descripción del trabajador
                        String description = request.getParameter("description");
                        
                        //Obtenemos la sesión de error
                        HttpSession objError = request.getSession();
                        //Intentamos convertir las imágenes
                        try{
                            //Convertimos las imágenes a bytes para que sean guardads en la BD
                            InputStream bytesPerfil = profile.getInputStream();
                            InputStream bytesPortada = banner.getInputStream();
                            
                            //Intentamos establecer la sesión de usuario
                            try{
                                //Obtenemos la sesión de usuario
                                HttpSession objSesion = request.getSession();
                                //Obtenemos la ID del usuario desde donde está registrando sus cambios
                                int idTrab = Integer.parseInt(objSesion.getAttribute("id").toString());
                                
                                //Guardamos en el objeto trab(Trabajador) la información recopilada
                                trab.setPerfil(bytesPerfil);
                                trab.setPortada(bytesPortada);
                                trab.setDescripcion(description);
                                trab.setTrabajo(trabajo);
                                trab.setRegion(region);
                                trab.setIdUsu(idTrab);
                                //Intentamos guardar en la BD
                                try{
                                    //Creamos el objeto que nos va a ayudar a hacer el guardado en la BD
                                    OpcUsuario ayuda = new OpcUsuario();
                                    //Ejecutamos el método ocmpletar de nuestro objeto
                                    ayuda.completar(trab);
                                }
                                catch(Exception e){//Si falla al guardar en la BD imprime y redirecciona al error
                                    objError.setAttribute("error", e);
                                    response.sendRedirect("error.jsp");
                                    System.out.println(e);
                                }
                            }
                            catch(Exception e){//Si falla al establecer la sesión de usuario imprime y redirecciona el error
                                objError.setAttribute("error", e);
                                response.sendRedirect("error.jsp");
                                System.out.println(e);
                            }
                        }
                        catch(Exception e){//Si falla al convertir a bytes, imprime y redirecciona el error
                            objError.setAttribute("error", e);
                            response.sendRedirect("error.jsp");
                            System.out.println(e);
                        }
                    }
                    catch(Exception e){//Si falla al guardar la imágen en el Part, imprime y redirecciona al mismo formulario
                        System.out.println(e);
                        response.sendRedirect("sign/complemento/completa.jsp");
                        throw e;
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            throw e;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
