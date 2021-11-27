package com.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.modelo.*;
import java.io.InputStream;
import java.util.List;
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
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
public class ControlUsuarios extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
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
        
        /*
            METODO GET
            ESTE METODO GET PODRÁ ATENDER FUNCIONES QUE VENGAN DE LINKS O QUE VENGAN DE FORMULARIOS CON EL METODO GET
            RECORDAMOS QUE GET ENVÍA LOS PARÁMETROS EXPLÍCITOS EN EL LINK Y EL POST LOS ADJUNTA
        */
        
        String accion;
        OpcUsuario auxiliar = new OpcUsuario();
        accion = request.getParameter("accion");
        if(accion != null){
            if(accion.equals("MiPerfilTrabajador")){
                System.out.println("entró al flujo GET");
                HttpSession objSesion = request.getSession();
                int idTrabajador = Integer.parseInt(objSesion.getAttribute("id").toString());
                try{
                    List<Trabajador> lista = auxiliar.listarPerfilTrabajador(idTrabajador);
                    request.setAttribute("PerfilTrabajador", lista);
                    request.getRequestDispatcher("trabajador/perfil/MiPerfil.jsp").forward(request, response);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                if(accion.equals("Perfiles")){
                    try{
                        List<Trabajador> perfiles = auxiliar.mostrarPerfiles();
                        request.setAttribute("Perfiles", perfiles);
                        request.getRequestDispatcher("usuario/listado/Encuentra.jsp").forward(request, response);
                    }
                    catch(Exception e){

                    }
                }
            }
        }
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
        
        /*
            METODO POST
            ESTE METODO POST SOLO ATENDERÁ INDICACIONES QUE VENGAN DE UN FORMULARIOS (CON EL METODO POST)
            TODOS LOS BOTONES QUE SE REQUIERAN MANDAR A ESTE METODO DEBEN TENER EL name = "accion"
        */
        
        String accion;
        OpcUsuario auxiliar = new OpcUsuario();
        try{
            accion = request.getParameter("accion");
            if(accion.equals("Registrar")){
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
                                    response.sendRedirect("ControlUsuarios?accion=Perfiles");
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
                if(accion.equals("Iniciar")){
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
                                response.sendRedirect("ControlUsuarios?accion=Perfiles");
                            }
                            else{
                                if(datos[1] == 2){
                                    System.out.println("Usuario tipo trabajador");
                                    Trabajador traba = auxiliar.datosAntiguosTrabajador(datos[0]);
                                    HttpSession objSesion = request.getSession();
                                    objSesion.setAttribute("nombre", traba.getNombre());
                                    objSesion.setAttribute("apellido", traba.getApellido());
                                    objSesion.setAttribute("correo", traba.getCorreoUsu());
                                    objSesion.setAttribute("tipo", datos[1]);
                                    objSesion.setAttribute("telefono", traba.getTelefono());
                                    objSesion.setAttribute("id", datos[0]);  
                                    if(traba.getRegionNombre() != null){
                                        objSesion.setAttribute("descripcion", traba.getDescripcion());
                                        response.sendRedirect("ControlUsuarios?accion=MiPerfilTrabajador");
                                        return;
                                    }
                                    else{
                                        response.sendRedirect("sign/complemento/completa.jsp");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Usuario no existe");
                            response.sendRedirect("sign/identificate.jsp");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Error");
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                else{
                    if(accion.equals("Completar")){
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
                                    objSesion.setAttribute("descripcion", description);
                                    //Obtenemos la ID del usuario desde donde está registrando sus cambios
                                    int idTrab = Integer.parseInt(objSesion.getAttribute("id").toString());
                                    Trabajador traba = auxiliar.datosAntiguosTrabajador(idTrab);
                                    objSesion.setAttribute("nombre", traba.getNombre());
                                    objSesion.setAttribute("apellido", traba.getApellido());
                                    objSesion.setAttribute("correo", traba.getCorreoUsu());
                                    objSesion.setAttribute("tipo", 2);
                                    objSesion.setAttribute("telefono", traba.getTelefono());

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
                                        response.sendRedirect("ControlUsuarios?accion=MiPerfilTrabajador");
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
                    else{
                        if(accion.equals("Cambiar")){
                            response.sendRedirect("trabajador/perfil/editar/EditarPerfil.jsp");
                        }
                        else{
                            if(accion.equals("Eliminar")){
                                response.sendRedirect("trabajador/perfil/eliminar/EliminarPerfil.jsp");
                            }
                            else{
                                if(accion.equals("Cerrar")){
                                    HttpSession objSesion = request.getSession();
                                    objSesion.invalidate();
                                    response.sendRedirect("index/index.jsp");
                                }
                                else{
                                    if(accion.equals("HacerCambio")){
                                        Trabajador trab = new Trabajador();
                                        String nombre = request.getParameter("nombren");
                                        String apellido = request.getParameter("apellidon");
                                        String telefono = request.getParameter("telefonon");
                                        String contraNueva = request.getParameter("contranuevan");
                                        String contraVieja = request.getParameter("contraviejan");
                                        String descripcion = request.getParameter("descripcionn");
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
                                                    auxiliar.editarPerfilTrabajador(trab);
                                                    response.sendRedirect("ControlUsuarios?accion=MiPerfilTrabajador");
                                                    
                                                    objSesion.setAttribute("nombre", nombre);
                                                    objSesion.setAttribute("apellido", apellido);
                                                    objSesion.setAttribute("descripcion", descripcion);
                                                    objSesion.setAttribute("telefono", telefono);
                                                }
                                                catch(Exception e){
                                                    System.out.println("No se pudo guardar los cambios" + e);
                                                    response.sendRedirect("ControlUsuarios?accion=MiPerfilTrabajador");
                                                }
                                            }
                                            else{
                                                System.out.println("las contraseñas no coinciden");
                                                response.sendRedirect("trabajador/perfil/editar/EditarPerfil.jsp");
                                            }
                                        }
                                        catch(Exception e){
                                            
                                        }
                                        
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
                                                    if(verificar = true){
                                                        auxiliar.eliminarPerfil(idUsuario);
                                                        objSesion.invalidate();
                                                        response.sendRedirect("index/index.jsp");
                                                    }
                                                    else{
                                                        response.sendRedirect("trabajador/perfil/eliminar/EliminarPerfil.jsp");
                                                    }
                                                }
                                                catch(Exception e){
                                                    
                                                }
                                            }
                                            catch(Exception e){
                                                
                                            }
                                        }
                                        else{
                                            if(accion.equals("Buscar")){
                                                String busqueda = request.getParameter("Busqueda");
                                                if(!busqueda.equals("")){
                                                    List<Trabajador> perfiles = auxiliar.busquedaPefiles(busqueda);
                                                    request.setAttribute("Perfiles", perfiles);
                                                    request.getRequestDispatcher("usuario/listado/Encuentra.jsp").forward(request, response);
                                                }
                                                else{
                                                    response.sendRedirect("ControlUsuarios?accion=Perfiles");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            throw e;
        }
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
