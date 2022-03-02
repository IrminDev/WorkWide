<%@page import="com.modelo.Trabajador"%>
<%@page import="com.modelo.OpcUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="style5.css" type="text/css"/>
    </head>
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        OpcUsuario aux = new OpcUsuario(); 
        Trabajador traba = new Trabajador();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("1")){
                id = objSesion.getAttribute("id").toString();
                traba = aux.listarPerfilTrabajador(Integer.parseInt(id));
            }
            else{
                response.sendRedirect("../../usuario/solicitudes/solicitudes.jsp");
            }
        }
        else{
            response.sendRedirect("../../index/index.jsp");
        }
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
            <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img">
        </nav>
    </header>
          
    <main>
              
              
           <!-- CONTADOR -->    
        <section class="contador">
            <div class="cards">

                <!-- SECCIÓN --> 
                <div class="card-sigle">
                    <div>
                        <h1>54</h1>  
                        <span>Trabajos</span>
                    </div>
                    <div>
                        <span class="las la-users"></span> 
                    </div>
                </div>    
                    <div class="card-sigle" id="entrenadores">
                    <div>
                        <h1>18</h1>  
                        <span>Completadas</span>
                    </div>
                    <div>
                        <span class="las la-users"></span> 
                    </div>
                    </div>    
                  <div class="card-sigle">
                    <div>
                        <h1>3 </h1>  
                        <span>Incompletas</span>
                    </div>
                    <div>
                        <span class="las la-clipboard-list"></span> 
                    </div>
                </div>
           
            </div>                          
        </section>
           
        <!-- CARTAS DE TRABAJO --> 
              
        <section class="trabajos-container">  
             
           <!--DIFERENTES TRABAJOS --> 
        
            <div class="container">
                <div class="course">
                    <div class="preview">
                        <h6>Trabajo</h6>
                        <h2>Nombre del jefe</h2>
                        <a href="#" >Mensaje</a>
                    </div>
                    <div class="info">
                        <h6>Contacto</h6>
                        <h2>Titulo del trabajo</h2>
                        <h3><span>Fecha de inicio: </span> </h3>
                        <h3>Fecha de finalización: </h3>
                        <p class="p-trunc">Descripción</p>
                        <button class="btn">Aceptar</button>
                        <button class="btn2">Rechazar</button>
                    </div>
                </div>
            </div>
            
        </section>
    </main>
    <!-- SCRIPT DE LAS CARTAS DE TRABAJO-->    
    <script>
        const trunc = document.querySelector('.p-trunc');
        trunc.innerText =
        trunc.innerText.substring(0, 100) +
        '...'
    </script>
    <script src="../../JS/listarSolicitudes.js"></script>
  </body>
</html>
