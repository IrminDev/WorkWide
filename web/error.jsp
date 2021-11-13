<%-- 
    Document   : error.jsp
    Created on : 13/11/2021, 11:47:54 AM
    Author     : IRMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession objError = request.getSession();
            String e = objError.getAttribute("error").toString();
        %>
        <h1><% out.print(e); %></h1>
    </body>
</html>
