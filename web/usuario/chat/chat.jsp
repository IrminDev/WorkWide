<%-- 
    Document   : chat
    Created on : 24/12/2021, 03:37:51 PM
    Author     : IRMIN
--%>

<%@page import="com.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <%
        HttpSession objSesion = request.getSession();
        String id = "";
        String receptor = "";
        
        OpcUsuario aux = new OpcUsuario(); 
        Usuario usu = new Usuario();
        
        if(objSesion.getAttribute("id") != null){
            if(objSesion.getAttribute("tipont").toString().equals("2")){
                if(request.getParameter("id") != null){
                    id = objSesion.getAttribute("id").toString();
                    receptor = request.getParameter("id");
                    usu = aux.iniciarUsuario(Integer.parseInt(receptor));
                    if(usu.getNombre() == null){
                        response.sendRedirect("users.jsp");
                    }
                }
                else{
                    response.sendRedirect("users.jsp");
                }
            }
            else{
                response.sendRedirect("../../trabajador/index/index.jsp");
            }
        }
        else{
            response.sendRedirect("../../index/index.jsp");
        }
    %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><% out.print(usu.getNombre() + " " + usu.getApellido()); %></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style.css">
    
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
                        <a href="users.jsp" class="nav_link active-link">
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
                        <a href="../perfil/MiPerfil.jsp" class="nav_link">
                            <i class='bx bx-user-circle nav_icon' ></i>
                            <span class="nav_name">Perfil</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ICONO DEL USUARIO -->
            <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="perfil" onerror="this.src='../../user.svg'" class="nav_img">
        </nav>
    </header>

    <!-- CONTENIDO DENTRO DE LA WEB -->
    
    <div class="wrapper">
        <section class="chat-area">
            <div class="head">
                <a href="users.jsp" class="back-icon"><i class="fa fa-arrow-left"></i></a>
                <img src="../../ControladorImagen?id=<% out.print(receptor); %>" alt="">
                <div class="details">
                    <span><% out.print(usu.getNombre() + " " + usu.getApellido()); %></span>
                    <p><% out.print(usu.getEstado()); %></p>
                </div>
            </div>
            
            
            <div class="chat-box">
                

                
            </div>
            
            
            <form action="" class="typing-area">
                <input type="text" class="incoming_id" name="receptor" value="<% out.print(receptor); %>" hidden>
                <input type="text" name="message" class="input-field" placeholder="Escribe un mensaje aquí..." autocomplete="off">
                <button><i class="fa fa-paper-plane"></i></button>
            </form>
            
        </section>
    </div>
    <script src="../../JS/chat.js"></script>
</body>
</html>