package com.controlador;

import com.modelo.Cifrado;
import com.modelo.CodeFactory;
import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
/**
 *
 * @author IrminDev
 */
@MultipartConfig(location = "G:/tmp", fileSizeThreshold=1024*1024*5, maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
@WebServlet(name = "enviarEmail", urlPatterns = {"/enviarEmail"})
public class enviarEmail extends HttpServlet {
       final String correo = "workwide.soporte";
       final String contra = "Pochoclo";
       Cifrado DES = new Cifrado();
       OpcUsuario AUX = new OpcUsuario();

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

              String remitente  = request.getParameter("emailn");

              int id = AUX.obtenerIdCorreo(remitente);
              
              if(id == 0){
                     response.getWriter().write("El correo no existe");
                     System.out.println(remitente);
              }
              else{
                     Date fecha = new Date();
                     HttpSession objSesion = request.getSession();
                     objSesion.setAttribute("id_prov", id);
                     
                     fecha.setTime(System.currentTimeMillis());
                     Trabajador traba = AUX.datosAntiguosTrabajador(id);                     
                     
                     
                     Properties props = System.getProperties();
                     props.put("mail.smtp.auth", "true");
                     props.put("mail.smtp.starttls.enable", "true");
                     props.put("mail.smtp.user", correo);
                     props.put("mai.smtp.clave", contra);
                     props.put("mail.smtp.host", "smtp.gmail.com");
                     props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                     props.put("mail.smtp.port", "465");
                     props.put("mail.smtp.socketFactory.port", "465");
                     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                     props.put("mail.smtp.socketFactory.fallback", "false");
                     props.put("mail.smtp.debug", "true");
                     
                     Session sesion = Session.getDefaultInstance(props, null);
                     sesion.setDebug(true);
                     
                     try{
                            CodeFactory token = new CodeFactory(fecha, id);
                            AUX.setToken(token);
                            
                            MimeMessage message = new MimeMessage(sesion);

                            message.setFrom(new InternetAddress(correo));
                            message.setSubject("**CÓDIGO DE RECUPERACIÓN**");
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));
                            message.setContent("<h1 align=\"center\">¡Hola " + traba.getNombre() + "!</h1>\n" +
                "        <h2>Fuimos notificados acerca de que has olvidado tu contraseña, para recuperarlas deberás usar este código:</h2>\n" +
                "        <h1 align=\"center\">" + DES.desencriptar(token.getUniqueId()) + "</h1>\n" +
                "        <h3>Recuerda que debes usarlo antes de que pasen 10 minutos.</h3>\n" +
                "        <p>En WorkWide estamos para ayudar a nuestros usuarios.</p>", "text/html; charset=utf-8");
                            
                            
                            Transport transport = sesion.getTransport("smtp");
                            transport.connect("smtp.gmail.com", correo, contra);
                            transport.sendMessage(message, message.getAllRecipients());
                            transport.close();

                            response.getWriter().write("Listo");
                     }      
                     catch(Exception e){
                            System.out.println("Algo sucedió mal");
                            response.getWriter().write("Errores");
                            System.out.println(e);
                            e.printStackTrace();
                     }
              }
       }

       @Override
       public String getServletInfo() {
              return "Short description";
       }

}
