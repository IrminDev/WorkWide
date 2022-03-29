<%@page import="com.modelo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi perfil</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        OpcUsuario aux = new OpcUsuario(); 
        Trabajador traba = new Trabajador();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("1")){
                id = objSesion.getAttribute("id").toString();
                traba = aux.listarPerfilTrabajador(Integer.parseInt(id));
            }
            else{
                response.sendRedirect("../../usuario/listado/Encuentra.jsp");
            }
        }
        else{
            response.sendRedirect("../../index/index.jsp");
        }
    %>

    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
    <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="../index/index.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../index/index.jsp" class="nav_link">
                            <i class='bx bx-home-alt nav_icon' ></i>
                            <span class="nav_name">Inicio</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../chat/users.jsp" class="nav_link">
                            <i class='bx bx-chat nav_icon' ></i>
                            <span class="nav_name">Mensajes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../trabajos/trabajos.jsp" class="nav_link">
                            <i class='bx bx-briefcase-alt-2 nav_icon' ></i>
                            <span class="nav_name">Trabajos</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../solicitudes/solicitudes.jsp" class="nav_link">
                            <i class='bx bx-receipt nav_icon'></i>
                            <span class="nav_name">Solicitudes</span>
                        </a>
                    </li>
<!-- links funcionales -->
                    <li class="nav_item">
                        <a href="#" class="nav_link active-link">
                            <i class='bx bx-user-circle nav_icon' ></i>
                            <span class="nav_name">Perfil</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ICONO DEL USUARIO -->
            <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img">
        </nav>
    </header>
        <div class="container-card">
            <style>
                .profile-header{
                    background: url("../../ControladorPortada?id=<% out.print(id); %>");
                    background-size: cover;
                }
            </style>
            <div class="profile-header">
                <div class="profile-img">
                    <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="perfil">
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
                        <p><i class='bx bxs-envelope' ></i><% out.print(traba.getCorreoUsu()); %></p>
                        <div class="user-work">
                            <h3>Trabajo</h3>
                            <p class="work">
                                <% out.print(traba.getTrabajoNombre()); %>
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
                            <li onclick="tabs(1)" class="user-info">Opciones</li>
                        </ul>
                    </div>
                    <div class="profile-body">
                        <div class="profile-info tab">
                            <h1>Acerca de <% out.print(traba.getNombre()); %></h1>
                            <p><% out.print(traba.getDescripcion()); %></p>
                            <br><br>
                            <p class="advertencia-desc">Tu descripción es lo que le da idea a los usuarios de quién eres, a qué te dedicas y qué tareas puedes hacer, así como tus habilidades. WorkWide no se hace responsable de usar
                            esta sección como un lugar para ofensas, contenido explícito o cualquier otro contenido que pueda infringir nuestras normas. Si es necesario eliminar tu cuenta por esto, así lo haremos.</p>
                        </div> 
                        <div class="profile-info tab profile-btns">
                            <h1>Opciones</h1>
                            <form method="POST" action="#">
                                <button class="profile-conf"><i class='bx bxs-cog' ></i>Configuración de cuenta</button>
                                <button class="profile-del"><i class='bx bxs-trash '></i>Eliminar perfil</button>
                                <button class="profile-close"><i class='bx bx-log-out  ' ></i>Cerrar sesión</button>
                            </form>
                            <p>En esta sección podrás escoger entre modificar tu perfil, eliminar tu cuenta definitivamente (implica que se borarrán mensajes y solicitudes) y cerrar sesión. En el apartado de modificar perfil no podrás modificar el trabajo al que te dedicas ni tu correo electrónico, esto por cuestiones de seguridad, a su vez si no requieres cambiar tu foto de perfil o portada
                            tendrás que dejar esos espacios en blanco. Si tu contraseña planeas que sea la misma, será necesario que en la nueva contraseña teclees la misma.<br>
                            El apartado de eliminar perfil solicitará tu contraseña dos veces para confirmar, si requieres eliminar tu perfil, recuerda que debes teclear ambas contraseñas correctamente, siendo estas las mismas.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="script.js"></script>
</body>
</html>
