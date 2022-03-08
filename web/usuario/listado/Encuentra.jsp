<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>WorkWide</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

        <link rel="stylesheet" href="style.css" type="text/css"/>
      
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("2")){
                id = objSesion.getAttribute("id").toString();
            }
            else{
                response.sendRedirect("../../trabajador/index/index.jsp");
            }
        }
        else{
            response.sendRedirect("../../index/index.jsp");
        }
    %>

    </head>
    <body>
            <!-- HEADER CON EL QUE SE TRABAJARÁ -->
        <header class="header scroll-header" id="header">
            <nav class="nav container">
                <!-- LOGO DE LA APLICACIÓN -->
                <a href="#" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

                <!-- LISTA DE LOS LINKS DEL NAV -->
                <div class="nav_menu">
                    <ul class="nav_list">
                        <li class="nav_item">
                            <a href="#" class="nav_link active-link">
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
                            <a href="" class="nav_link">
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
                <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img" onerror=this.src="../../user.svg">
            </nav>
        </header>
        <main>
            
              <!-- ICONO DE BUSQUEDA -->
            <form action="#" method="POST">
                <div class="form">
                    <div class="search">
                        <div class="icon">
                            
                        </div>
                        <div class="input">
                            <input type="text" name="Busqueda" placeholder="Coloca tu búsqueda" id="mysearch">
                        </div>
                        <span class="clear" onclick="document.getElementById('mysearch').value = '' "></span>
                    </div>
                </div>
            </form>
                
                
        
             <!-- Cartas -->
            
            <section class="profiles container">
                <div class="card__grid">
                    
                </div>
            </section>
             
        </main>
        
         <script src="script.js"></script>
         <script src="../../JS/buscar.js"></script>
    </body>
</html>
