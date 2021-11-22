<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="Stylesheet" href="usuario/listado/perfil/style.css" type="text/css">
    </head>
    <body>
    <%
        HttpSession objSesion = request.getSession();
        String id = objSesion.getAttribute("id").toString();
    %>

    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
    <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="ControlUsuarios?accion=Perfiles" class="nav_link">
                            <i class='bx bx-home-alt nav_icon' ></i>
                            <span class="nav_name">Inicio</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="" class="nav_link">
                            <i class='bx bx-chat nav_icon' ></i>
                            <span class="nav_name">Mensajes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="" class="nav_link">
                            <i class='bx bx-briefcase-alt-2 nav_icon' ></i>
                            <span class="nav_name">Trabajos</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="" class="nav_link">
                            <i class='bx bx-receipt nav_icon'></i>
                            <span class="nav_name">Solicitudes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="" class="nav_link active-link">
                            <i class='bx bx-user-circle nav_icon' ></i>
                            <span class="nav_name">Perfil</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ICONO DEL USUARIO -->
            <img src="ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img">
        </nav>
    </header>

    
    <c:forEach var="dato" items="${PerfilTrabajador}">
        <div class="container-card">
            <style>
                .profile-header{
                    background: url("ControladorPortada?id=${dato.getIdUsu()}");
                    background-size: cover;
                }
            </style>
            <div class="profile-header">
                <div class="profile-img">
                    <img src="ControladorImagen?id=${dato.getIdUsu()}" alt="perfil">
                </div>
                <div class="profile-nav-info">
                    <h3 class="username">${dato.getNombre()} ${dato.getApellido()}</h3>
                    <div class="address">
                        <p class="state">${dato.getRegionNombre()},</p>
                        <span class="country"> México.</span>
                    </div>
                </div>
            </div>
            <div class="main-bd">
                <div class="left-side">
                    <div class="profile-side">
                        <p class="mobile-number"><i class='bx bxs-phone'></i>+52 ${dato.getTelefono()}</p>
                        <p><i class='bx bxs-envelope' ></i>${dato.getCorreoUsu()}</p>
                        <div class="user-work">
                            <h3>Trabajo</h3>
                            <p class="work">
                                ${dato.getTrabajoNombre()}
                            </p>
                        </div>
                        <div class="profile-btn">
                            <button class="profile-chat">
                                <i class='bx bxs-message' ></i>Chat
                            </button>
                            <button class="profile-req">
                                <i class='bx bxs-file-plus' ></i>Solicitud
                            </button>
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
                            <h1>Acerca de ${dato.getNombre()}</h1>
                            <p>${dato.getDescripcion()}</p>
                            <br><br>
                            <p class="advertencia-desc">Esta descripción fue proporcionada por ${dato.getNombre()} ${dato.getApellido()}, si detectas alguna irregularidad o algo que incumpla nuestras normas, haznos saber acerca de este perfil reportándolo.</p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <script src="usuario/listado/perfil/main.js"></script>
</body>
</html>
