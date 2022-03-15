package com.webservices;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author IrminDev
 */
public class registrarAndroid extends HttpServlet {

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
        //Configuramos el response(respuesta del servlet) y request(petición del servlet) en caracteres UTF-8
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

                JSONObject obj = new JSONObject();
                
                
                //Si el usuario ya estaba registrado lo manda a que inicie sesión
                
                if(estado[0].equals("registrado")){
                    obj.put("estado", 0);
                }
                else{
                    aux.cambiarEstado(Integer.parseInt(estado[1]));
                    //Si el usuario es un trabajador, lo mandamos a completar su registro
                    if(tipoUsu == 2){
                        obj.put("estado", 2);
                    }
                    else{
                        //Si el usuario es un usuario, lo llevamos al lsitado de los trabajadores
                        if(tipoUsu == 1){
                            obj.put("estado", 1);
                        }
                    }
                }
                //Mandamos al response el objeto JSON como un string
                response.getWriter().write(obj.toString());
                
                //Imprimimos en la página el objeto JSON
                PrintWriter pw = response.getWriter();
                pw.write(obj.toJSONString());
                pw.print(obj.toJSONString());
            }
            catch(Exception e){
                //Si hay error al registar el suaurio, imprimimos el error
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
