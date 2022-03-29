<%-- 
    Document   : EditarPerfil
    Created on : 4 nov. 2021, 19:29:21
    Author     : IRMIN
--%>

<%@page import="com.modelo.Trabajador"%>
<%@page import="com.modelo.OpcUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
        String descripcion = "";
        OpcUsuario aux = new OpcUsuario(); 
        Trabajador trab = new Trabajador();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("1")){
                id = objSesion.getAttribute("id").toString();
                trab = aux.listarPerfilTrabajador(Integer.parseInt(id));
                nombre = trab.getNombre();
                apellido = trab.getApellido();
                telefono = trab.getTelefono();
                descripcion = trab.getDescripcion();
            }
            else{
                response.sendRedirect("../../../usuario/listado/Encuentra.jsp");
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
            <a href="../../index/index.jsp" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../../index/index.jsp" class="nav_link">
                            <i class='bx bx-home-alt nav_icon' ></i>
                            <span class="nav_name">Inicio</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../chat/users.jsp" class="nav_link">
                            <i class='bx bx-chat nav_icon' ></i>
                            <span class="nav_name">Mensajes</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../trabajos/trabajos.jsp" class="nav_link">
                            <i class='bx bx-briefcase-alt-2 nav_icon' ></i>
                            <span class="nav_name">Trabajos</span>
                        </a>
                    </li>

                    <li class="nav_item">
                        <a href="../../solicitudes/solicitudes.jsp" class="nav_link">
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
        <form action="../../../ControlUsuarios" method="POST" id="Cambios" enctype="multipart/form-data">
            <div class="row">
                <div class="column">
                    <label for="nombre" id="cnombre">Nombre</label>
                    <input type="text" name="nombren" value="<% out.print(nombre); %>" id="nombre">
                    <div class="warning" id="cwnombre">
                        <p id="warning-nombre"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="apellido" id="capellido">Apellido</label>
                    <input type="text" value="<% out.print(apellido); %>" name="apellidon" id="apellido">
                    <div class="warning" id="cwapellido">
                        <p id="warning-apellido"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="telefono" id="ctelefono">Teléfono</label>
                    <input type="text" value="<% out.print(telefono); %>" name="telefonon" id="telefono">
                    <div class="warning" id="cwtelefono">
                        <p id="warning-telefono"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="contrasenanew" id="ccontranueva">Contraseña nueva</label>
                    <input type="password" name="contranuevan" id="contrasenanew" placeholder="Si no deseas cambiarla coloca la misma">
                    <div class="warning" id="cwcontranueva">
                        <p id="warning-contranueva"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="profile" id="cperfil">Foto de perfil</label>
                    <input type="file" name="perfiln" id="profile">
                    <div class="warning" id="cwperfil">
                        <p id="warning-perfil"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="banner" id="cportada">Foto de portada</label>
                    <input type="file" name="bannern" id="banner">
                    <div class="warning" id="cwportada">
                        <p id="warning-portada"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="contrasenaold"  id="ccontravieja">Contraseña antigua</label>
                    <input type="password" name="contraviejan" id="contrasenaold" placeholder="Escribe la contraseña antigua">
                    <div class="warning" id="cwcontravieja">
                        <p id="warning-contravieja"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="ubication" id="cregion">Región</label>
                    <select name="ubicationn" id="ubication">
                        <optgroup label="Escoge una región">
                            <option value="1">Aguascalientes</option>
                            <option value="2">Baja California</option>
                            <option value="3">Baja California Sur</option>
                            <option value="4">Campeche</option>
                            <option value="5">Chiapas</option>
                            <option value="6">Chihuahua</option>
                            <option value="7">Ciudad de México</option>
                            <option value="8">Coahuila</option>
                            <option value="9">Colima</option>
                            <option value="10">Durango</option>
                            <option value="11">Estado de México</option>
                            <option value="12">Guanajuato</option>
                            <option value="13">Guerrero</option>
                            <option value="14">Hidalgo</option>
                            <option value="15">Jalisco</option>
                            <option value="16">Michoacán</option>
                            <option value="17">Morelos</option>
                            <option value="18">Nayarit</option>
                            <option value="19">Nuevo León</option>
                            <option value="20">Oaxaca</option>
                            <option value="21">Puebla</option>
                            <option value="22">Querétaro</option>
                            <option value="23">Quintana Roo</option>
                            <option value="24">San Luis Potosí</option>
                            <option value="25">Sinaloa</option>
                            <option value="26">Sonora</option>
                            <option value="27">Tabasco</option>
                            <option value="28">Tamaulipas</option>
                            <option value="29">Tlaxcala</option>
                            <option value="30">Veracruz</option>
                            <option value="31">Yucatán</option>
                            <option value="32">Zacatecas</option>
                        </optgroup>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="column">
                    <label for="description" id="cdescripcion">Regálanos una descripción de tí</label>
                    <textarea form="Cambios" name="descripcionn" rows="10" id="description" placeholder="Describe tu persona y tu trabajo, esta descripción será la que muestres a tus potenciales clientes.">
                        <% out.print(descripcion); %>
                    </textarea>
                    <div class="warning" id="cwdescripcion">
                        <p id="warning-descripcion"></p>
                    </div>
                </div>
            </div>
            <button class="form-button" type="submit" name="accion" onclick="return enviarCambios()">Editar perfil</button>
        </form>
    </div>

    <script src="script.js"></script>
    <script src="../../../JS/cambiarTrabajador.js"></script>
</body>
</html>