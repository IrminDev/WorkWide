<%@page import="com.modelo.Usuario"%>
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
        <header class="header" id="header">
            <nav class="nav container1">
                 <!-- LOGO DE LA APLICACIÓN -->
                  <a href="" class="nav__logo"><span>W</span>ork<span>W</span>ide</a>
               <!-- LISTA DE LOS LINKS DEL NAV -->  
                <div class="nav__menu" id="nav-menu">
                    <ul class="nav__list">
                        <li class="nav__item">
                            <a href="#home" class="nav__link">
                                <i class='bx bx-home-alt nav_icon'></i>
                                <span class="nav__name">Inicio</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#about" class="nav__link">
                                <i class='bx bx-chat nav_icon' ></i>
                                <span class="nav__name">Mensajes</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#skills" class="nav__link active-link">
                                <i class='bx bx-briefcase-alt-2 nav_icon' ></i>
                                <span class="nav__name">Trabajos</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#portfolio" class="nav__link">
                                <i class='bx bx-receipt nav_icon' ></i>
                                <span class="nav__name">Solicitudes</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#contactme" class="nav__link">
                                <i class='bx bx-user-circle nav_icon'></i>
                                <span class="nav__name">Perfil</span>
                            </a>
                        </li>
                    </ul>
               <!-- ICONO DEL USUARIO -->     
                </div>
                <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img" onerror=this.src="../../user.svg">
                
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

