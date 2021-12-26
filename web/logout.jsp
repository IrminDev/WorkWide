<%@page import="com.modelo.OpcUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar Sesión</title>
    </head>
    <body>
        <%
            HttpSession objSesion = request.getSession();
            if(objSesion.getAttribute("id") != null){
                OpcUsuario aux = new OpcUsuario();
                aux.CerrarSesion(Integer.parseInt(objSesion.getAttribute("id").toString()));
                objSesion.invalidate();
            }
            response.sendRedirect("index/index.jsp");
        %>
    </body>
</html>
