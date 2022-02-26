
package com.webservices;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author IrminDev
 */

@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "iniciarAndroid", urlPatterns = {"/iniciarAndroid"})
public class iniciarAndroid extends HttpServlet {
    Cifrado DES = new Cifrado();
    OpcUsuario AUX = new OpcUsuario();


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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //Obtenemos datos
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contra");
        
        Trabajador traba = new Trabajador();
        
        //Ciframos contraseña
        try{
            String contraBd = DES.encriptar(contrasena);
            //Obtenemos el tipo y id de usuario
            int[] data = AUX.iniciarSesion(correo, contraBd);
            //Si la información estaba bien guardamos la información del usuario en un objeto
            if(data[0] != 0){
                if(data[1] == 1){
                    Usuario user = AUX.iniciarUsuario(data[0]);
                    traba.setNombre(user.getNombre());
                    traba.setApellido(user.getApellido());
                    traba.setCorreoUsu(user.getCorreoUsu());
                    traba.setIdUsu(user.getIdUsu());
                    traba.setDescripcion("");
                    traba.setTrabajoNombre("");
                    traba.setRegionNombre("");
                }
                else{
                    if(data[1] == 2){
                        traba = AUX.datosAntiguosTrabajador(data[0]);
                        if(traba.getRegionNombre() == null){
                            traba.setRegionNombre("");
                        }
                    }
                }
            }
            else{
                traba.setIdUsu(0);
                traba.setTipoUsu(0);
            }
            
            JSONObject obj = new JSONObject();
            obj.put("nombre", traba.getNombre());
            obj.put("apellido", traba.getApellido());
            obj.put("id", data[0]);
            obj.put("region", traba.getRegionNombre());
            obj.put("trabajo", traba.getTrabajoNombre());
            obj.put("descripcion", traba.getDescripcion());
            obj.put("tipo", data[1]);
            
            response.getWriter().write(obj.toString());
            
            PrintWriter pw = response.getWriter();
            pw.write(obj.toJSONString());
            pw.print(obj.toJSONString());
            
            System.out.println(obj.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
