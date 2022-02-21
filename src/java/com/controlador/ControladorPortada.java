/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controlador;

import com.modelo.OpcUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IrminDev
 * 
 * Servlet encargado de mostrar las imágenes de portada que sean requeridas
 */

@WebServlet(name = "ControladorPortada", urlPatterns = {"/ControladorPortada"})
public class ControladorPortada extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 
         * MÉTODO DENTRO DEL GET YA QUE NO RECIBIMOS INFORMACIÓN DE UN FORMULARIO
         * 
         */
        
        //Instanciamos la clase opcUsuario para tener acceso a los métodos que nos serán de ayuda
        OpcUsuario ayuda = new OpcUsuario();
        //Obtenemos el id que es pasado apartir de la solicitud de un recurso (Ej. src="ControladorPortada?id=19" donde el id es 19)
        int id = Integer.parseInt(request.getParameter("id"));
        //Abrimos un try-ctach para hacer la conversión de bytes a una imágen
        try{
            //Ejecutamos el método que mostrará la imágen de portada
            ayuda.mostrarPortada(id, response);
        }
        catch(Exception e){
            //En caso de una excepción, mostramos la excepción en pantalla
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
