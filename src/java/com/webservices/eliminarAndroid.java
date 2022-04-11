package com.webservices;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
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
@WebServlet(name = "eliminarAndroid", urlPatterns = {"/eliminarAndroid"})
public class eliminarAndroid extends HttpServlet {
    OpcUsuario aux = new OpcUsuario();

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
        
        int id = Integer.parseInt(request.getParameter("id"));
        String contra = request.getParameter("contra");
        
        boolean verificar;
        String state = "";
        JSONObject obj = new JSONObject();
        
        try{
            //Instanciamos la clase cifrado para utilizar sus métodos
            Cifrado cifrar = new Cifrado();
            //Encriptamos la contraseña proporcionada
            String contraEnBD = cifrar.encriptar(contra);

            //Creamos un nuevo try catch para ahora comprobar que la información coincide
            try{
                //Verificamos si la información es correcta
                verificar = aux.validarContrasena(id, contraEnBD);
                
                //En caso verdarero se elimina el perfil
                if(verificar == true){
                    aux.eliminarPerfil(id);
                    state = "yes";
                }
                else{
                    state = "no";
                }
            }
            catch(Exception e){
                //Imprimimos posibles excepciones
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        obj.put("estado", state);
        response.getWriter().write(obj.toString());
            
        PrintWriter pw = response.getWriter();
        pw.write(obj.toJSONString());
        pw.print(obj.toJSONString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
