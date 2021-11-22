<%-- 
    Document   : EditarPerfil
    Created on : 4 nov. 2021, 19:29:21
    Author     : IRMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Enviar Solicitud</title>
</head>
<body>
    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
    <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="" class="nav_logo"><span class="Form_span">W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="" class="nav_link active-link">
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
                        <a href="" class="nav_link">
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
    <div class="content">
        <h1><span class="Form_span">E</span>nvia tu solicitud</h1>
        <p>Envia una solicitud de manera rapida y sencilla desde aquí, llena los datos solicitados debajo y al instante le haremos llegar tu solicitud.</p>
        <form action="../ControlSolicitudes" method="POST">
            <div class="row">
                <div class="column">
                    <label for="nombre" id="cnombre">Nombre</label>
                    <input type="text" id="nombre">
                    <div class="warning" id="cwnombre">
                        <p id="warning-nombre">Apoco</p>
                    </div>
                </div>
                <div class="column">
                    <label for="apellido" id="capellido">Apellido</label>
                    <input type="text" id="apellido">
                    <div class="warning" id="cwapellido">
                        <p id="warning-apellido">Apoco</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="correo" id="ccorreo">Correo remitente</label>
                    <input type="email" id="correo" placeholder="Escriba el correo desde el cual deseas enviar la solicitud">
                    <div class="warning" id="cwcorreo">
                        <p id="warning-correo">Apoco</p>
                    </div>
                </div>
                <div class="column">
                    <label for="correo2" id="ccorreo2">Correo del destinatario</label>
                    <input type="email" id="correo2" placeholder="Escriba el correo al deseea enviar su solicitud">
                    <div class="warning" id="cwcorreo2">
                        <p id="warning-correo2">Apoco</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="fecha" id="cfecha">Fecha de inicio</label>
                    <input type="date" id="fecha">
                    <div class="warning" id="cwfecha">
                        <p id="warning-fecha">Apoco</p>
                    </div>
                </div>
                <div class="column">
                    <label for="fecha2" id="cfecha2">Fecha de finalización</label>
                    <input type="date" id="fecha2">
                   <div class="warning" id="cwfecha2">
                        <p id="warning-fecha2">Apoco</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="contrasenaold"  id="ccontravieja">Título de la solicitud</label>
                    <input type="password" id="contrasenaold" placeholder="Escribe un título para tu solicirud.">
                    <div class="warning" id="cwcontravieja">
                        <p id="warning-contravieja">Apoco</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="description" id="cdescripcion">Descripción</label>
                    <textarea name="" rows="10" id="description" placeholder="Describe tu persona,tu trabajo, tus isteréses, esta descripción será la leéra el destinatario."></textarea>
                    <div class="warning" id="cwdescripcion">
                        <p id="warning-descripcion">Apoco</p>
                    </div>
                </div>
            </div>
            <button class="form-button" type="submit" onclick="return enviarCambios()">Enviar solicitud</button>
        </form>
    </div>

    <script src="script.js"></script>
</body>
</html>