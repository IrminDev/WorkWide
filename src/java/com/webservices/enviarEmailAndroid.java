package com.webservices;

import com.modelo.Cifrado;
import com.modelo.CodeFactory;
import com.modelo.OpcUsuario;
import com.modelo.Trabajador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author irminDev
 */
@WebServlet(name = "enviarEmailAndroid", urlPatterns = {"/enviarEmailAndroid"})
public class enviarEmailAndroid extends HttpServlet {
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
        
        String remitente  = request.getParameter("correo");

        int id = AUX.obtenerIdCorreo(remitente);
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        
        if(id == 0){
            response.getWriter().write("El correo no existe");
            System.out.println(remitente);
            obj.put("bandera", "undefined");
        }
        else{
            Date fecha = new Date();

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

                obj.put("bandera", "true");
                
            }      
            catch(Exception e){
                   System.out.println("Algo sucedió mal");
                   System.out.println(e);
                   obj.put("bandera", "false");
                   e.printStackTrace();
            }
        }
        response.getWriter().write(obj.toJSONString());
            
        PrintWriter pw = response.getWriter();
        pw.write(obj.toJSONString());
        pw.print(obj.toJSONString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
