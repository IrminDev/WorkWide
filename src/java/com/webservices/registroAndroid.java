package com.webservices;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author IrminDev
 */
@WebServlet(name = "registroAndroid", urlPatterns = {"/registroAndroid"})
public class registroAndroid extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contra = request.getParameter("contra");
        String tipoUsu = request.getParameter("tipo");
        String telefono = request.getParameter("telefono");

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
                int tipoInt = 0;
                if(tipoUsu.equals("Usuario")){
                    tipoInt = 1;
                }
                else{
                    if(tipoUsu.equals("Trabajador")){
                        tipoInt = 2;
                    }
                }

                //Guardamos en el objeto usuario la información obtenida
                usu.setNombre(nombre);
                usu.setApellido(apellido);
                usu.setCorreoUsu(correo);
                usu.setTipoUsu(tipoInt);
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
                    obj.put("id", estado[1]);
                    //Si el usuario es un trabajador, lo mandamos a completar su registro
                    if(tipoInt == 2){
                        obj.put("estado", 2);
                    }
                    else{
                        //Si el usuario es un usuario, lo llevamos al lsitado de los trabajadores
                        if(tipoInt == 1){
                            obj.put("estado", 1);
                        }
                    }
                }
                response.getWriter().write(obj.toString());
                
                PrintWriter pw = response.getWriter();
                pw.write(obj.toJSONString());
                pw.print(obj.toJSONString());
                
                System.out.println(obj.toJSONString());
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
