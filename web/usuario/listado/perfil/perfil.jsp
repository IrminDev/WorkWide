<%@page import="com.modelo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="Stylesheet" href="style.css" type="text/css">
    </head>
    <body>
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        OpcUsuario aux = new OpcUsuario();
        int idPerfil = 0;
        Trabajador traba = new Trabajador();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("2")){
                id = objSesion.getAttribute("id").toString();
                idPerfil = Integer.parseInt(request.getParameter("id"));
                traba = aux.listarPerfilTrabajador(idPerfil);
                if(traba.getNombre() == null){
                    response.sendRedirect("../Encuentra.jsp");
                }
            }
            else{
                 response.sendRedirect("../../../trabajador/index/index.jsp");
            }
        }
        else{
            response.sendRedirect("../../../index/index.jsp");
        }
    %>

    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
    <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="../Encuentra.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../Encuentra.jsp" class="nav_link active-link">
                            <i class='bx bx-home-alt nav_icon' ></i>
                            <span class="nav_name">Inicio</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../chat/users.jsp" class="nav_link">
                            <i class='bx bx-chat nav_icon' ></i>
                            <span class="nav_name">Mensajes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../trabajos/trabajos.jsp" class="nav_link">
                            <i class='bx bx-briefcase-alt-2 nav_icon' ></i>
                            <span class="nav_name">Trabajos</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../solicitudes/solicitudes.jsp" class="nav_link">
                            <i class='bx bx-receipt nav_icon'></i>
                            <span class="nav_name">Solicitudes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../perfil/MiPerfil.jsp" class="nav_link">
                            <i class='bx bx-user-circle nav_icon' ></i>
                            <span class="nav_name">Perfil</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ICONO DEL USUARIO -->
            <img src="../../../perfilAndroid?id=<% out.print(id); %>" alt="user" class="nav_img" onerror=this.src="../../../user.svg">
        </nav>
    </header>
        
        
    <div class="container-card">
        <style>
            .profile-header{
                background: url("../../../portadaAndroid?id=<% out.print(idPerfil); %>");
                background-size: cover;
            }
        </style>
        <div class="profile-header">
            <div class="profile-img">
                <img src="../../../perfilAndroid?id=<% out.print(idPerfil); %>" alt="perfil">
            </div>
            <div class="profile-nav-info">
                <h3 class="username"><% out.print(traba.getNombre()); %> <% out.print(traba.getApellido()); %></h3>
                <div class="address">
                    <p class="state"><% out.print(traba.getRegionNombre()); %>,</p>
                    <span class="country"> México.</span>
                </div>
            </div>
        </div>
        <div class="main-bd">
            <div class="left-side">
                <div class="profile-side">
                    <%
                            if(traba.getTelefono() != null && !traba.getTelefono().equals("")){
                                out.print("<p class=\"mobile-number\"><i class='bx bxs-phone'></i>+52 " + traba.getTelefono() + "</p>");
                            }
                    %>
                    <p><i class='bx bxs-envelope' ></i><% out.print(traba.getCorreoUsu().length() > 20 ? traba.getCorreoUsu().substring(0, 20).concat("...") : traba.getCorreoUsu()); %></p>
                    <div class="user-work">
                        <h3>Trabajo</h3>
                        <p class="work">
                            <% out.print(traba.getTrabajoNombre()); %>
                        </p>
                    </div>
                    <div class="profile-btn">
                        <a href="../../chat/chat.jsp?id=<% out.print(idPerfil); %>" class="profile-chat">
                            <i class='bx bxs-message' ></i>Chat
                        </a>
                        <a href="../../Form/solicitud.jsp?idEnviar=<% out.print(idPerfil); %>" class="profile-req" >
                            <i class='bx bxs-file-plus' ></i>Solicitud
                        </a>
                    </div>
                </div>
            </div>
            <div class="right-side">
                <div class="nav-profile">
                    <ul>
                        <li onclick="tabs(0)" class="user-info">Acerca de</li>
                    </ul>
                </div>
                <div class="profile-body">
                    <div class="profile-info tab">
                        <h1>Acerca de <% out.print(traba.getNombre()); %></h1>
                        <p><% out.print(traba.getDescripcion()); %></p>
                        <br><br>
                        <p class="advertencia-desc">Esta descripción fue proporcionada por <% out.print(traba.getNombre()); %> <% out.print(traba.getApellido()); %>, si detectas alguna irregularidad o algo que incumpla nuestras normas, haznos saber acerca de este perfil reportándolo.</p>
                    </div> 
                </div>
            </div>
        </div>
    </div>
    <script src="main.js"></script>
</body>
</html>
