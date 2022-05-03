package com.webservices;

import com.modelo.Cifrado;
import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.simple.JSONObject;

/**
 *
 * @author IrminDev
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "cambiarAndroid", urlPatterns = {"/cambiarAndroid"})
public class cambiarAndroid extends HttpServlet {
         //Instanciamos la clase opcUsuario para utilizar ciertos métodos que nos serán útiles
         OpcUsuario auxiliar = new OpcUsuario();

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
                  
                  int tipo = Integer.parseInt(request.getParameter("tipo"));
                  int id = Integer.parseInt(request.getParameter("id"));
                  
                  JSONObject resp = new JSONObject();
                  if(tipo == 1){
                           Usuario usu = new Usuario();

                           //Recuperamos los datos proporcionado en el formulario
                           String nombre = request.getParameter("nombre");
                           String apellido = request.getParameter("apellido");
                           String telefono = request.getParameter("telefono");
                           String contranueva = request.getParameter("contranueva");
                           String contravieja = request.getParameter("contravieja");
                           //Preparamos un objeto Part para los archivos de imágen que serán guardados en bytes
                           Part perfil = request.getPart("perfil");
                           Part portada = request.getPart("portada");

                           //Declaramos los bytes que usaremos temporalmente para convertir el objeto Part
                           InputStream bytesPerfil = null;
                           InputStream bytesPortada = null;

                           //Si hay imágen de perfil, convertimos
                           System.out.println(perfil);
                           
                           if(perfil != null){
                                    //Convertimos el objeto part del perfil en bytes
                                    bytesPerfil = perfil.getInputStream();
                                    System.out.println("si hay");
                           }
                           else{
                                    //En caso contrario, abrimos un try-catch
                                    try{
                                             //Instanciamos la clase usuario donde guardaremos datos temporalmente
                                             Usuario datosPerfil = new Usuario();
                                             System.out.println("no hay");
                                             //Obtenemos los datos de ese usuario
                                             datosPerfil = auxiliar.iniciarUsuario(id);

                                             //Tomamos su foto de perfil y la guardamos en bytes
                                             bytesPerfil = datosPerfil.getPerfil();
                                    }
                                    catch(Exception e){
                                             //Si hay un error, lo imprimos
                                             System.out.println(e);
                                    }
                           }
                           //Si hay imágen de portada, convertimos
                           if(portada != null){
                                    //Convertimos el objeto part del portada en bytes
                                    bytesPortada = portada.getInputStream();
                           }
                           else{
                                    //En caso contrario, abrimos un try-catch
                                    try{
                                             //Instanciamos la clase usuario donde guardaremos datos temporalmente
                                             Usuario datosPortada = new Usuario();
                                             //Obtenemos los datos de ese usuario
                                             datosPortada = auxiliar.iniciarUsuario(id);
                                             //Tomamos su foto de portada y la guardamos en bytes
                                             bytesPortada = datosPortada.getPortada();
                                    }
                                    catch(Exception e){
                                             //Si hay un error, lo imprimos
                                             System.out.println(e);
                                    }
                           }

                           //Creamos un try-catch para cifrar la contraseña
                           try{
                                    //Instanciamos la clase cifrado
                                    Cifrado objCifrar = new Cifrado();

                                    //Encriptamos ambas contraseñas, la nueva y la vieja
                                    String contraViejaBD = objCifrar.encriptar(contravieja);
                                    String contraNuevaBD = objCifrar.encriptar(contranueva);

                                    //Verificamos que la contraseña antigua sea coincidiente con la guardada en la BD
                                    boolean verificado = auxiliar.validarContrasena(id, contraViejaBD);

                                    //Si la contraseña si coincide, se empiezan a realizar los cambios
                                    if(verificado == true){
                                             //Abrimos un try-catch para hacer los cambios en la BD
                                             try{
                                                      //Guardamos toda la información en el objeto usuario
                                                      usu.setNombre(nombre);
                                                      usu.setApellido(apellido);
                                                      usu.setContraUsu(contraNuevaBD);
                                                      usu.setPerfil(bytesPerfil);
                                                      usu.setPortada(bytesPortada);
                                                      usu.setTelefono(telefono);
                                                      usu.setIdUsu(id);

                                                      //Editamos el perfil copn la información proporcionada
                                                      auxiliar.actualizarUsuario(usu);
                                                      //Mandamos el mensaje de listo al javascript (revisar el archivo cambiarUsuario.js)
                                                      resp.put("state", "true");
                                                      System.out.println("Listo");
                                             }
                                             catch(Exception e){
                                                      //Si hay erores, los imprimimos en consola
                                                      System.out.println(e);
                                                      resp.put("state", "error");
                                                      System.out.println("error");
                                             }
                                    }
                                    else{
                                             //Si las contraseñas no coinciden, mandamos un mensaje a javascript (revisar el archivo cambiarTrabajador.js
                                             resp.put("state", "false");
                                              System.out.println("No coinciden");
                                    }
                           }
                           catch(Exception e){
                                    System.out.println(e);
                                    resp.put("state", "error");
                           }
                  }
                  else{
                           if(tipo == 2){
                                    //Instanciamos la clase trabajador donde guardaremos toda la información nueva
                                    Trabajador trab = new Trabajador();

                                    //Recuperamos los datos proporcionado en el formulario
                                    String nombre = request.getParameter("nombren");
                                    String apellido = request.getParameter("apellidon");
                                    String telefono = request.getParameter("telefonon");
                                    String contraNueva = request.getParameter("contranuevan");
                                    String contraVieja = request.getParameter("contraviejan");
                                    String descripcion = request.getParameter("descripcionn");
                                    
                                    //Si la decripción tiene espacios al principio o al final, nos deshacemos de ellos
                                    descripcion = descripcion.trim();
                                    
                                    int region = Integer.parseInt(request.getParameter("ubicationn"));
                                    //Preparamos un objeto Part para los archivos de imágen que serán guardados en bytes
                                    Part profile = null;
                                    Part banner = null;

                                    //Creamos un try-catch para guardar las imagenes proporcionadas en el formulario
                                    try{
                                             //Guardamos las imágenes en nustros objetos Part
                                             profile = request.getPart("perfiln");
                                             banner = request.getPart("bannern");
                                    }
                                    catch(Exception e){
                                             //Imprimos un me4nsaje si hubo un error
                                             System.out.println("No se pudieron guardar las imágenes");
                                    }

                                    //Declaramos los bytes que usaremos temporalmente para convertir el objeto Part
                                    InputStream bytesPerfil = null;
                                    InputStream bytesPortada = null;

                                    //Si hay imágen de perfil, convertimos
                                    if(profile != null){
                                             //Convertimos el objeto part del perfil en bytes
                                             bytesPerfil = profile.getInputStream();
                                    }
                                    else{
                                             //En caso contrario, abrimos un try-catch
                                             try{
                                                      System.out.println("Saca el perfil anteior");
                                                      //Instanciamos la clase trabajador donde guardaremos datos temporalmente
                                                      Trabajador datos;
                                                      //Obtenemos los datos de ese usuario
                                                      datos = auxiliar.datosAntiguosTrabajador(id);
                                                      //Tomamos su foto de perfil y la guardamos en bytes
                                                      bytesPerfil = datos.getPerfil();
                                             }
                                             catch(Exception e){
                                                      //Si hubo un error al obtener la foto de perfil antigua, mostramos el siguiente mensaje
                                                      System.out.println("Problema con la imagen de perfil antigua " + e);
                                             }
                                    }
                                    //Si hay imágen de portada, convertimos
                                    if(banner != null){
                                             //Convertimos el objeto part del portada en bytes
                                             bytesPortada = banner.getInputStream();
                                    }
                                    else{
                                             //En caso contrario, abrimos un try-catch
                                             try{
                                                      System.out.println("Saca la portada anteior");
                                                      //Instanciamos la clase trabajador donde guardaremos datos temporalmente
                                                      Trabajador datos;
                                                      //Obtenemos los datos de ese usuario
                                                      datos = auxiliar.datosAntiguosTrabajador(id);
                                                      //Tomamos su foto de portada y la guardamos en bytes
                                                      bytesPortada = datos.getPortada();
                                             }
                                             catch(Exception e){
                                                      //Si hubo un error al obtener la foto de portada antigua, mostramos el siguiente mensaje
                                                      System.out.println("Problema con la imagen de portada antigua " + e);
                                             }
                                    }

                                    //Creamos un try-catch para cifrar la contraseña
                                    try{
                                             //Instanciamos la clase cifrado
                                             Cifrado objCifrar = new Cifrado();

                                             //Encriptamos ambas contraseñas, la nueva y la vieja
                                             String contraViejaCifrada = objCifrar.encriptar(contraVieja);
                                             String contraNuevaCifrada = objCifrar.encriptar(contraNueva);

                                             //Verificamos que la contraseña antigua sea coincidiente con la guardada en la BD
                                             boolean verificado = auxiliar.validarContrasena(id, contraViejaCifrada);

                                             //Si la contraseña si coincide, se empiezan a realizar los cambios
                                             if(verificado == true){
                                                      //Abrimos un try-catch para hacer los cambios en la BD
                                                      try{
                                                               //Guardamos toda la información en el objeto trabajador
                                                               trab.setNombre(nombre);
                                                               trab.setApellido(apellido);
                                                               trab.setDescripcion(descripcion);
                                                               trab.setTelefono(telefono);
                                                               trab.setContraUsu(contraNuevaCifrada);
                                                               trab.setPerfil(bytesPerfil);
                                                               trab.setPortada(bytesPortada);
                                                               trab.setRegion(region);
                                                               trab.setIdUsu(id);
                                                               //En la descripción cambiamos los saltos de línea por etiquestas "<br>" y que se vea reflejado en el HTML
                                                               trab.setDescripcion(trab.getDescripcion().replace("\n", "<br>"));

                                                               //Editamos el perfil copn la información proporcionada
                                                               auxiliar.editarPerfilTrabajador(trab);

                                                               //Mandamos el mensaje de listo al javascript (revisar el archivo cambiarTrabajador.js)
                                                               resp.put("state", "true");
                                                      }
                                                      catch(Exception e){
                                                               resp.put("state", "error");
                                                      }
                                             }
                                             else{
                                                      //Si las contraseñas no coinciden, mandamos un mensaje a javascript (revisar el archivo cambiarTrabajador.js)
                                                      System.out.println("las contraseñas no coinciden");
                                                      resp.put("state", "false");
                                             }
                                    }
                                    catch(Exception e){
                                             resp.put("state", "error");
                                    }
                           }
                  }
                  response.getWriter().write(resp.toJSONString());
            
                  System.out.println(resp.toJSONString());
         }

         @Override
         public String getServletInfo() {
                  return "Short description";
         }

}
