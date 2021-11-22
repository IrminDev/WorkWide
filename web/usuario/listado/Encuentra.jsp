<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>WorkWide</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

        <link rel="stylesheet" href="usuario/listado/style.css" type="text/css"/>
      


    </head>
    <body>
            <!-- HEADER CON EL QUE SE TRABAJARÁ -->
        <header class="header scroll-header" id="header">
            <nav class="nav container">
                <!-- LOGO DE LA APLICACIÓN -->
                <a href="" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

                <!-- LISTA DE LOS LINKS DEL NAV -->
                <div class="nav_menu">
                    <ul class="nav_list">
                        <li class="nav_item">
                            <a href="ControlUsuarios?accion=Perfiles" class="nav_link active-link">
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
                            <a href="ControlNuevo?accion=MiPerfil" class="nav_link">
                                <i class='bx bx-user-circle nav_icon' ></i>
                                <span class="nav_name">Perfil</span>
                            </a>
                        </li>
                    </ul>
                </div>

                <!-- ICONO DEL USUARIO -->
                <img src="user.jpg" alt="user" class="nav_img">
            </nav>
        </header>
        <main>
            
              <!-- ICONO DE BUSQUEDA -->
              <form action="ControlUsuarios" method="POST">
                <div class="form">
                    <div class="search">
                        <div class="icon">
                            
                        </div>
                        <div class="input">
                            <input type="text" name="Busqueda" placeholder="Coloca tu búsqueda" id="mysearch">
                        </div>
                        <span class="clear" onclick="document.getElementById('mysearch').value = '' "></span>
                    </div>
                    <button type="submit" class="buscar" name="accion" value="Buscar">Buscar</button>
                </div>
            </form>
                
                
        
             <!-- Cartas -->
            
            <section class="profiles container">
                <div class="card__grid">
                    <c:forEach var="trabajador" items="${Perfiles}">
                        <div class="card">
                            <div class="card__cover usu${trabajador.getIdUsu()}" id="${trabajador.getIdUsu()}"></div>
                            <style>
                                .card__cover.usu${trabajador.getIdUsu()}{
                                    width: 100%;
                                    height: 12rem;
                                    background-image: linear-gradient(120deg, #37ecba7e 0%, #72afd398 100%), url("ControladorPortada?id=${trabajador.getIdUsu()}");
                                    background-size: cover;
                                    clip-path: polygon(0 0, 100% 0, 100% 80%, 0% 100%);
                                }
                            </style>
                            <div class="card__contents">

                                <!-- imagen-->
                                <div class="card__user-pic">
                                    <img src="ControladorImagen?id=${trabajador.getIdUsu()}" alt="perfil">
                                </div>
                                <!-- datos del trabajador-->
                                <h1 class="heading">
                                    <a href="ControlPerfiles?id=${trabajador.getIdUsu()}" class="heading--sup">${trabajador.getNombre()} ${trabajador.getApellido()}</a>
                                    <span class="heading--sub">${trabajador.getTrabajoNombre()}</span>
                                    <span class="heading--sub">${trabajador.getRegionNombre()}</span>
                                    <span class="heading--suc">Contáctame a: ${trabajador.getCorreoUsu()}</span>

                                </h1>
                                <!-- botones-->
                                <div class="btns">
                                    <a href="#" class="btn btn--msg">Mensaje</a>
                                    <a href="ControlSolicitudes?id=${trabajador.getIdUsu()}" class="btn btn--follow">Solicitud</a>

                                </div>


                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>  
        </main>
        
         <script src="usuario/listado/script.js"></script>
    </body>
</html>
