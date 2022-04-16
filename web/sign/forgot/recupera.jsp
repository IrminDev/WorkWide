<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Recupera tu contraseña</title>
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
        <h1>Recupera tu contraseña</h1>
        <p>Podrá recuperar tu contraseña en caso de que la hayas eliminado, para esto deberás tener acceso a tu correo electrónico. Se te enviará un código que deberás colocar en un plazo máximo de 10 minutos*</p>
        <form action="#" class="" method="POST">
            <div class="row">
                <div class="column">
                    <label for="email" id="cemail">Correo electrónico</label>
                    <input type="email" name="emailn" id="email">
                    <div class="warning" id="cwemail">
                        <p id="warning-email"></p>
                    </div>
                </div>
            </div>
            <button class="form-button" name="accion" value="recuperarContra" onclick="return validarEmail()">Enviar código</button>
        </form>
    </div>

    <script src="validar.js"></script>
    <script src="../../JS/recuperar.js"></script>
</body>
</html>