package com.webservices;

import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author IRMIN
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "completarAndroid", urlPatterns = {"/completarAndroid"})
public class completarAndroid extends HttpServlet {


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
        
        //Creamos un objeto trabajador ya que para este formulario solo serán trabajadores
        Trabajador trab = new Trabajador();
        //Obtenemos los datos primitivos de Java que están en el formulario
        System.out.print(request.getParameter("trabajo") + " " + request.getParameter("region") + " " + request.getParameter("descripcion"));
        int trabajo = Integer.parseInt(request.getParameter("trabajo"));
        int region = Integer.parseInt(request.getParameter("region"));
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("descripcion");

        //Intentamos capturar las imágenes 
        try{
            
            //Guardamos en un objeto Part las imágenes
            Part profile = request.getPart("perfil");
            Part banner = request.getPart("portada");
            //Guardamos la descripción del trabajador

            //Intentamos convertir las imágenes
            try{
                //Convertimos las imágenes a bytes para que sean guardads en la BD
                InputStream bytesPerfil = profile.getInputStream();
                InputStream bytesPortada = banner.getInputStream();

                //Intentamos establecer la sesión de usuario
                try{
                    //Guardamos en el objeto trab(Trabajador) la información recopilada
                    trab.setPerfil(bytesPerfil);
                    trab.setPortada(bytesPortada);
                    trab.setDescripcion(description);
                    trab.setTrabajo(trabajo);
                    trab.setRegion(region);
                    trab.setIdUsu(id);
                    trab.setDescripcion(trab.getDescripcion().replace("\n", "<br>"));
                    //Intentamos guardar en la BD
                    try{
                        //Creamos el objeto que nos va a ayudar a hacer el guardado en la BD
                        OpcUsuario ayuda = new OpcUsuario();
                        //Ejecutamos el método completar de nuestro objeto
                        ayuda.completar(trab);
                        response.getWriter().write("Listo");
                    }
                    catch(Exception e){//Si falla al guardar en la BD imprime y redirecciona al error
                        e.printStackTrace();
                    }
                }
                catch(Exception e){//Si falla al establecer la sesión de usuario imprime y redirecciona el error
                    e.printStackTrace();
                }
            }
            catch(Exception e){//Si falla al convertir a bytes, imprime y redirecciona el error
                e.printStackTrace();
            }
        }
        catch(Exception e){//Si falla al guardar la imágen en el Part, imprime y redirecciona al mismo formulario
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
