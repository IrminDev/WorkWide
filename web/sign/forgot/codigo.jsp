<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style1.css">
    <title>Recupera tu contraseña</title>
    <%
        if(request.getSession().getAttribute("id_prov") == null){
                 response.sendRedirect("recupera.jsp");
        }
    %>
</head>
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
        <h1>Ingresa el código que se te proporcionó</h1>
        <p>Revisa tu bandeja de entrada de tu correo, tienes 10 minutos para escribirlo, en caso de no recibirlo, da clic <a href="">aquí</a> para generar un nuevo código, recuerda respetar mayúsculas.</p>
        <form action="#" class="" method="POST">
            <div class="row">
                <div class="column">
                    <input type="text" name="char1" id="char1">
                </div>
                <div class="column">
                    <input type="text" name="char2" id="char2">
                </div>
               <div class="column">
                    <input type="text" name="char3" id="char3">
                </div>
                <div class="column">
                    <input type="text" name="char4" id="char4">
                </div>
                <div class="column">
                    <input type="text" name="char5" id="char5">
                </div>
               <div class="column">
                    <input type="text" name="char6" id="char6">
                </div>
                 <div class="column">
                    <input type="text" name="char7" id="char7">
                </div>
               <div class="column">
                    <input type="text" name="char8" id="char8">
                </div>
            </div>
            <div class="row">
                <div class="warning" id="cwcode">
                    <p id="warning-code"></p>
                </div>
            </div>
            <button class="form-button" name="accion" value="enviarCodigo" onclick="return comprobar()">Enviar código</button>
        </form>
    </div>

    <script src="code.js"></script>
    <script src="../../JS/codigo.js"></script>
</body>
</html>