<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

        <link rel="stylesheet" href="CSS/style3.css" type="text/css"/>
      


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
        <main>
            
             <!-- ICONO DE BUSQUEDA -->
            <div class="search">
                <div class="icon">
                    
                </div>
                <div class="input">
                    <input type="text" placeholder="Search" id="mysearch">
                </div>
                <span class="clear" onclick="document.getElementById('mysearch').value = '' "></span>
            </div>
                
                
        
             <!-- Cartas -->
            
            <section class="profiles container">
                <div class="card__grid">
                    <div class="card">
                
                <!-- 3 cartas x sección-->
                <div class="card__cover"></div>
                <div class="card__contents">
                    <!-- imagen-->
                    <div class="card__user-pic">
                        <img src="images/img7.jpg" alt="">
                    </div>
                        <!-- datos del trabajador-->
                    <h1 class="heading">
                        <a href="#" class="heading--sup">Oscar Mendoza</a>
                        <span class="heading--sub">Diseñador Gráfico</span>
                        <span class="heading--sub">Guadalajara</span>
                        <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                    </h1>
                    <!-- botones-->
                    <div class="btns">
                        
                        <a href="#" class="btn btn--msg">Message</a>
                        <a href="#" class="btn btn--follow">Follow</a>
                        
                    </div>
                    
                
                </div>
                    </div>
            <!-- 3 cartas x sección-->
                    <div class="card">
                        <div class="card__cover"></div>
                        <div class="card__contents">
                            
                            <!-- imagen-->
                            <div class="card__user-pic">
                                <img src="images/img7.jpg" alt="">
                            </div>
                            <!-- datos del trabajador-->
                            <h1 class="heading">
                                <a href="#" class="heading--sup">Oscar Mendoza</a>
                                <span class="heading--sub">Diseñador Gráfico</span>
                                <span class="heading--sub">Guadalajara</span>
                                <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                            
                            </h1>
                            <!-- botones-->
                            <div class="btns">
                                <a href="#" class="btn btn--msg">Message</a>
                                <a href="#" class="btn btn--follow">Follow</a>
                                
                            </div>
                            
                        
                        </div>
                    </div>
            <!-- 3 cartas x sección-->     
                    <div class="card">
                        <div class="card__cover"></div>
                        <div class="card__contents">
                            <!-- imagen-->
                            <div class="card__user-pic">
                                <img src="images/img7.jpg" alt="">
                            </div>
                            <!-- datos del trabajador-->
                            <h1 class="heading">
                            <a href="#" class="heading--sup">Oscar Mendoza</a>
                                <span class="heading--sub">Diseñador Gráfico</span>
                                <span class="heading--sub">Guadalajara</span>
                                <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                            </h1>
                            <!-- botones-->
                            <div class="btns">
                                <a href="#" class="btn btn--msg">Message</a>
                                <a href="#" class="btn btn--follow">Follow</a>
                                
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card__cover"></div>
                        <div class="card__contents">
                            <!-- imagen-->
                            <div class="card__user-pic">
                                <img src="images/img7.jpg" alt="">
                            </div>
                            <!-- datos del trabajador-->
                            <h1 class="heading">
                            <a href="#" class="heading--sup">Oscar Mendoza</a>
                                <span class="heading--sub">Diseñador Gráfico</span>
                                <span class="heading--sub">Guadalajara</span>
                                <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                            </h1>
                            <!-- botones-->
                            <div class="btns">
                                <a href="#" class="btn btn--msg">Message</a>
                                <a href="#" class="btn btn--follow">Follow</a>
                                
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card__cover"></div>
                        <div class="card__contents">
                            <!-- imagen-->
                            <div class="card__user-pic">
                                <img src="images/img7.jpg" alt="">
                            </div>
                            <!-- datos del trabajador-->
                            <h1 class="heading">
                            <a href="#" class="heading--sup">Oscar Mendoza</a>
                                <span class="heading--sub">Diseñador Gráfico</span>
                                <span class="heading--sub">Guadalajara</span>
                                <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                            </h1>
                            <!-- botones-->
                            <div class="btns">
                                <a href="#" class="btn btn--msg">Message</a>
                                <a href="#" class="btn btn--follow">Follow</a>
                                
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card__cover"></div>
                        <div class="card__contents">
                            <!-- imagen-->
                            <div class="card__user-pic">
                                <img src="images/img7.jpg" alt="">
                            </div>
                            <!-- datos del trabajador-->
                            <h1 class="heading">
                            <a href="#" class="heading--sup">Oscar Mendoza</a>
                                <span class="heading--sub">Diseñador Gráfico</span>
                                <span class="heading--sub">Guadalajara</span>
                                <span class="heading--suc">Soy Alguien comprometido en lo que le gusta</span>
                            </h1>
                            <!-- botones-->
                            <div class="btns">
                                <a href="#" class="btn btn--msg">Message</a>
                                <a href="#" class="btn btn--follow">Follow</a>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </section>  
        </main>
        
         <script src="script.js"></script>
    </body>
</html>
