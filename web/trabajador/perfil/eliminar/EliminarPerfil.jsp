
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Edita tu perfil</title>
</head>
    <%
        HttpSession objSesion = request.getSession();
        String id = objSesion.getAttribute("id").toString();
    %>
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
                        <a href="" class="nav_link">
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
                        <a href="../../../ControlUsuarios?accion=MiPerfilTrabajador" class="nav_link active-link">
                            <i class='bx bx-user-circle nav_icon' ></i>
                            <span class="nav_name">Perfil</span>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- ICONO DEL USUARIO -->
            <img src="../../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img">
        </nav>
    </header>
    <div class="content">
        <h1>Elimina tu perfil</h1>
        <p>Podrás eliminar toda información que se haya dejado en la aplicación. Si realizas la opción de eliminar no podrás recuperar tus datos ¿Estás seguro de eliminar?</p>
        <form action="../../../ControlUsuarios" method="POST">
            <div class="row">
                <div class="column">
                    <label for="contra" id="ccontra">Contraseña</label>
                    <input type="password" name="contran" id="contra">
                    <div class="warning" id="cwcontra">
                        <p id="warning-contra"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="contraconfirm" id="ccontraconfirm">Confirmar contraseña</label>
                    <input type="password" id="contraconfirm">
                    <div class="warning" id="cwcontraconfirm">
                        <p id="warning-contraconfirm"></p>
                    </div>
                </div>
            </div>
            <button class="form-button" name="accion" value="EliminarPerfil" onclick="return eliminarPerfil()">Eliminar perfil</button>
        </form>
    </div>

    <script src="script.js"></script>
</body>
</html>