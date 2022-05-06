<%@page import="com.modelo.Usuario"%>
<%@page import="com.modelo.OpcUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trabajos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <%
        HttpSession objSesion = request.getSession();
        String id = "";
        
        OpcUsuario aux = new OpcUsuario(); 
        Usuario usu = new Usuario();
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("2")){
                id = objSesion.getAttribute("id").toString();
                usu = aux.listarPerfilUsuario(Integer.parseInt(id));
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
            <a href="" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>

            <!-- LISTA DE LOS LINKS DEL NAV -->
            <div class="nav_menu">
                <ul class="nav_list">
                    <li class="nav_item">
                        <a href="../listado/Encuentra.jsp" class="nav_link">
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
                        <a href="" class="nav_link active-link">
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
            <img src="../../perfilAndroid?id=<% out.print(id); %>" alt="perfil" onerror="this.src='../../user.svg'" class="nav_img">
        </nav>
    </header>
          
        <main>                   
           <!-- CONTADOR -->    
            <section class="contador">
                            
            </section>
           
        <!-- CARTAS DE TRABAJO --> 
              
            <section class="trabajos-container">  
             
                
                
            </section>
        </main>
          
        <script src="../../JS/listarTrabajos.js"></script>
        <script>
            const trunc = document.querySelector('.p-trunc');
            trunc.innerText =
            trunc.innerText.substring(0, 100) +
            '...'
        </script>
  </body>
</html>

