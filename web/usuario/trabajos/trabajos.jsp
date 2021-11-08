
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    
         <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
         <link rel="stylesheet" href="style.css" type="text/css"/>
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
                            <a href="#home" class="nav__link active-link">
                                <i class='bx bx-home-alt nav__icon'></i>
                                <span class="nav__name">Home</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#about" class="nav__link">
                                <i class='bx bx-user nav__icon' ></i>
                                <span class="nav__name">About</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#skills" class="nav__link">
                                <i class='bx bx-book-alt nav__icon' ></i>
                                <span class="nav__name">Skills</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#portfolio" class="nav__link">
                                <i class='bx bx-briefcase-alt nav__icon' ></i>
                                <span class="nav__name">Portfolio</span>
                            </a>
                        </li>
                        
                         <li class="nav__item">
                            <a href="#contactme" class="nav__link">
                                <i class='bx bx-message-square-detail nav__icon'></i>
                                <span class="nav__name">Contactme</span>
                            </a>
                        </li>
                    </ul>
               <!-- ICONO DEL USUARIO -->     
                </div>
                <img src="../images/perfil.png" alt="" class="nav__img">
                
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
            <script>
            const icon = document.querySelector('.icon');
            const search = document.querySelector('.search');
            icon.onclick = function(){
                search.classList.toggle('active')
            }
        </script>
              
              
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
                <!-- DATOS DEL TRABAJO --> 
               <div class="preview">
                    <h6>Trabajo</h6>
                    <h2>Raul Gonzáles</h2>
                    <a href="#" >Enviar mensaje</a>
                </div>
                <div class="info">
                    <div class="progress-wrapper">
                        <!-- BARRA DE PROGRESO--> 
                        <div class="progress1"></div>
                        <span class="progress-text">Completado</span>
                        
                    </div>
                    
                    <h2>Reparación de tuberia</h2>
                    <h6>Contacto: tel:56787545</h6>
                    <h6>Guadalajara</h6>
                    <h6>Fecha:12/01/2021</h6>
                    <p class="p-trunc">Descripción: </p>
                    <p class="p-trunc">Ir a la diección proporcionada a arreglar fuja de agua, es urgente  </p>
                    <button class="btn">Completada</button>
                    
                </div>
            </div>
        </div>
              <div class="container">
            <div class="course">
                <div class="preview">
                    <h6>Trabajo</h6>
                    <h2>Nombre del jefe</h2>
                    <a href="#" >Mensaje</a>
                </div>
                <div class="info">
                    <div class="progress-wrapper">
                        <div class="progress"></div>
                        <span class="progress-text">Completado</span>
                        
                    </div>
                    <h6>Contacto</h6>
                    <h2>Titulo del trabajo</h2>
                    <p class="p-trunc">Descripción</p>
                    <button class="btn">Hacer</button>
                    
                </div>
            </div>
        </div>
        
             <!-- SCRIPT DE LAS CARTAS DE TRABAJO-->    
            <script>
           const trunc = document.querySelector('.p-trunc');
            trunc.innerText =
            trunc.innerText.substring(0, 100) +
            '...'
            </script>
            
            <div class="container">
            <div class="course">
               <div class="preview">
                    <h6>Trabajo</h6>
                    <h2>Nombre del jefe</h2>
                    <a href="#" >Mensaje</a>
                </div>
                <div class="info">
                    <div class="progress-wrapper">
                        <div class="progress1"></div>
                        <span class="progress-text">Completado</span>
                        
                    </div>
                    <h6>Contacto</h6>
                    <h2>Titulo del trabajo</h2>
                    <p class="p-trunc">Descripción</p>
                    <button class="btn">Completada</button>
                    
                </div>
            </div>
        </div>
            
            
         <div class="container">
            <div class="course">
                <div class="preview">
                    <h6>Trabajo</h6>
                    <h2>Nombre del jefe</h2>
                    <a href="#" >Mensaje</a>
                </div>
                <div class="info">
                    <div class="progress-wrapper">
                        <div class="progress"></div>
                        <span class="progress-text">Completado</span>
                        
                    </div>
                    <h6>Contacto</h6>
                    <h2>Titulo del trabajo</h2>
                    <p class="p-trunc">Descripción</p>
                    <button class="btn">Hacer</button>
                    
                </div>
            </div>
        </div>
        <script>
            
            const trunc = document.querySelector('.p-trunc');
            trunc.innerText =
            trunc.innerText.substring(0, 100) +
            '...'
            </script>
            
        </section>
          </main>
          <!-- SCRIPT DEL HEADER--> 
         <script src="JS/main.js"></script>
  </body>
</html>

