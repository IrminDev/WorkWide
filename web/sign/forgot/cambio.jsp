<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Elimina tu perfil</title>
</head>
    <%
        if(request.getSession().getAttribute("id") == null){
                 response.sendRedirect("codigo.jsp");
        }
    %>
<body>
    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
 <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="../../index/index.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../../index/index.jsp" class="nav_link">
                            <i class='bx bx-home nav_icon' ></i>
                            <span class="nav_name">Inicio</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../about/acerca.jsp" class="nav_link">
                            <i class='bx bx-info-circle nav_icon' ></i>
                            <span class="nav_name">Acerca de</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../quienesomos/quienesomos.jsp" class="nav_link">
                            <i class='bx bxs-user-detail nav_icon'></i>
                            <span class="nav_name">Quiénes somos</span>
                        </a>
                    </li>
                    
                    <li class="nav_item">
                        <a href="../identificate.jsp" class="nav_link">
                            <i class='bx bx-log-in nav_icon'></i>
                            <span class="nav_name">Identifícate</span>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- ICONO DEL USUARIO -->
        </nav>
    </header>
    <div class="content">
        <h1>Cambia tu contraseña</h1>
        <p>Estás a un paso de recuperar el acceso a tu cuenta, coloca una contraseña nueva que sea mayor a 4 caracteres y menor a 20, por seguridad recomendamos que tenga mayúsculas, minúsculas, números y símbolos</p>
        <form action="#" method="POST">
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
            <button class="form-button" name="accion" value="EliminarPerfil" onclick="return cambiarContra()">Cambiar contraseña</button>
        </form>
    </div>

    <script src="contra.js"></script>
    <script src="../../JS/cambiarContra.js"></script>
</body>
</html>