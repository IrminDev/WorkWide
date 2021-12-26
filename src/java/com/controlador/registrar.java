package com.controlador;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Usuario;
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
@WebServlet(name = "registrar", urlPatterns = {"/registrar"})
public class registrar extends HttpServlet {

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
                    response.getWriter().write("El correo " + correo + " ya se encuentra registrado");
                }
                else{
                    //Si el usuario todavía no estaba registrado, creamos su sesión
                    objSesion.setAttribute("id", estado[1]);
                    aux.IniciarSesion(Integer.parseInt(estado[1]));
                    //Si el usuario es un trabajador, lo mandamos a completar su registro
                    if(tipoUsu == 2){
                        response.getWriter().write("Trabajador");
                        objSesion.setAttribute("tipont", 1);
                    }
                    else{
                        //Si el usuario es un usuaerio, lo llevamos al lsitado de los trabajadores
                        if(tipoUsu == 1){
                            response.getWriter().write("Usuario");
                            objSesion.setAttribute("tipont", 2);
                        }
                    }
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception ex){

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
