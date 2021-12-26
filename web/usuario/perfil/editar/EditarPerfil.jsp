<%@page import="com.modelo.Usuario"%>
<%@page import="com.modelo.OpcUsuario"%>
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
<body>
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        String nombre = "";
        String apellido = "";
        String telefono = "";
        OpcUsuario aux = new OpcUsuario(); 
        Usuario usu = new Usuario();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("2")){
                id = objSesion.getAttribute("id").toString();
                usu = aux.listarPerfilUsuario(Integer.parseInt(id));
                nombre = usu.getNombre();
                apellido = usu.getApellido();
                telefono = usu.getTelefono();
            }
            else{
               response.sendRedirect("../../../trabajador/index/index.jsp"); 
            }
        }
        else{
            response.sendRedirect("../../../index/index.jsp");
        }
    %>
    <!-- HEADER CON EL QUE SE TRABAJARÁ -->
    <header class="header scroll-header" id="header">
        <nav class="nav container">
            <!-- LOGO DE LA APLICACIÓN -->
            <a href="../../listado/Encuentra.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../../listado/Encuentra.jsp" class="nav_link">
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
                        <a href="../MiPerfil.jsp" class="nav_link active-link">
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
        <h1>Edita tu perfil</h1>
        <p>¿Necesitas hacer algunas modificaciones? Sin problema podrás realizarlas desde aquí, recuerda que sino quieres modificar algunos campos los puedes dejar tal cual o llenarlos con la misma información, para el caso de la contraseña será necesario que la llenes de nuevo.</p>
        <form action="../../../ControlNuevo" method="POST" enctype="multipart/form-data">
            <div class="row">
                <div class="column">
                    <label for="nombre" id="cnombre">Nombre</label>
                    <input type="text" name="nombre" id="nombre" value="<% out.print(nombre); %>">
                    <div class="warning" id="cwnombre">
                        <p id="warning-nombre"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="apellido" id="capellido">Apellido</label>
                    <input type="text" name="apellido" id="apellido" value="<% out.print(apellido); %>">
                    <div class="warning" id="cwapellido">
                        <p id="warning-apellido"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="telefono" id="ctelefono">Teléfono</label>
                    <input type="text" name="telefono" id="telefono" value="<% out.print(telefono); %>">
                    <div class="warning" id="cwtelefono">
                        <p id="warning-telefono"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="contrasenanew" id="ccontranueva">Contraseña nueva</label>
                    <input type="password" name="contranueva" id="contrasenanew" placeholder="Si no deseas cambiarla coloca la misma">
                    <div class="warning" id="cwcontranueva">
                        <p id="warning-contranueva"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="profile" id="cperfil">Foto de perfil</label>
                    <input type="file" name="perfil" id="profile">
                    <div class="warning" id="cwperfil">
                        <p id="warning-perfil"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="banner" id="cportada">Foto de portada</label>
                    <input type="file" name="portada" id="banner">
                    <div class="warning" id="cwportada">
                        <p id="warning-portada"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="contrasenaold"  id="ccontravieja">Contraseña antigua</label>
                    <input type="password" name="contravieja" id="contrasenaold" placeholder="Escribe la contraseña antigua">
                    <div class="warning" id="cwcontravieja">
                        <p id="warning-contravieja"></p>
                    </div>
                </div>
                <button class="form-button" type="submit" onclick="return enviarCambios()" name="accion" value="HacerCambios">Editar perfil</button>
            </div>
        </form>
    </div>

    <script src="script.js"></script>
    <script src="../../../JS/cambiarUsuario.js"></script>
</body>
</html>