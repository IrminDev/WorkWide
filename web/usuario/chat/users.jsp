<%-- 
    Document   : users
    Created on : 23/12/2021, 08:08:48 PM
    Author     : IRMIN
--%>

<%@page import="com.modelo.Usuario"%>
<%@page import="com.modelo.OpcUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            HttpSession objSesion = request.getSession();
            String id = "";

            OpcUsuario aux = new OpcUsuario(); 
            Usuario usu = new Usuario();
            if(objSesion.getAttribute("id") != null){
                id = objSesion.getAttribute("id").toString();
                usu = aux.listarPerfilUsuario(Integer.parseInt(id));
            }
            else{
                response.sendRedirect("../../index/index.jsp");
            }
        %>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="style.css">
        <title>Mensajería</title>
    </head>
    <body>
        <!-- HEADER CON EL QUE SE TRABAJARÁ -->
        <header class="header scroll-header" id="header">
            <nav class="nav container">
                <!-- LOGO DE LA APLICACIÓN -->
                <a href="../listado/Encuentra.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

                <!-- LISTA DE LOS LINKS DEL NAV -->
                <div class="nav_menu">
                    <ul class="nav_list">
                        <li class="nav_item">
                            <a href="../listado/Encuentra.jsp" class="nav_link">
                                <i class='bx bx-home-alt nav_icon' ></i>
                                <span class="nav_name">Inicio</span>
                            </a>
                        </li>

                        <li class="nav_item">
                            <a href="../chat/users.jsp" class="nav_link active-link">
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

                        <li class="nav_item">
                            <a href="../perfil/MiPerfil.jsp" class="nav_link">
                                <i class='bx bx-user-circle nav_icon' ></i>
                                <span class="nav_name">Perfil</span>
                            </a>
                        </li>
                    </ul>
                </div>

                <!-- ICONO DEL USUARIO -->
                <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="perfil" onerror="this.src='../../user.svg'" class="nav_img">
            </nav>
        </header>
        
        
        
        <div class="wrapper">
            <section class="users">
                <div class="head">
                    <div class="content">
                        <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="perfil" onerror="this.src='../../user.svg'">
                        <div class="details">
                            <span><% out.print(usu.getNombre() + " " + usu.getApellido() ); %></span>
                            <p>Activo</p>
                        </div>
                    </div>
                    <a href="../../logout.jsp" class="logout">Cerrar sesión</a>
                </div>
                <div class="search">
                    <span class="text">Selecciona un usuario para chatear</span>
                    <input type="text" placeholder="Introduce un nombre a buscar...">
                    <button><i class="fa fa-search"></i></button>
                </div>
                <div class="users-list">
                    
                </div>
            </section>
        </div>
    </body>
    <script src="../../JS/listar.js"></script>
</html>
