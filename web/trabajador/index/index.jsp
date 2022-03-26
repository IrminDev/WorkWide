<%-- 
    Document   : index
    Created on : 28/11/2021, 10:22:29 AM
    Author     : IRMIN
--%>

<%@page import="com.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="style.css">
        <%
            HttpSession objSesion = request.getSession();
            String id = "";
            String nombre = "";
            
            if(objSesion.getAttribute("id") == null){
                response.sendRedirect("../../index/index.jsp");
            }
            else{
                if(objSesion.getAttribute("tipont").toString().equals("1")){
                    id = objSesion.getAttribute("id").toString();
                    OpcUsuario aux = new OpcUsuario();
                    Trabajador trab = aux.datosAntiguosTrabajador(Integer.parseInt(id));
                    nombre = trab.getNombre();
                }
                else{
                    response.sendRedirect("../../usuario/listado/Encuentra.jsp");
                }
            }
        %>
    </head>
    <body>
        <!-- HEADER CON EL QUE SE TRABAJARÁ -->
        <header class="header scroll-header" id="header">
            <nav class="nav container">
                <!-- LOGO DE LA APLICACIÓN -->
                <a href="#" class="nav_logo"><span>W</span>ork<span>W</span>ide</a>
    
                <!-- LISTA DE LOS LINKS DEL NAV -->
                <div class="nav_menu">
                    <ul class="nav_list">
                        <li class="nav_item">
                            <a href="../index/index.jsp" class="nav_link active-link">
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
                            <a href="../trabajos/trabajos.jsp" class="nav_link">
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
                <img src="../../ControladorImagen?id=<% out.print(id); %>" alt="user" class="nav_img" onerror=this.src="../../user.svg">
            </nav>
        </header>
        <main>
        
        <div class="hero">
            
            <div class="container">
                
            <section class="hero__container">
                <div class="hero__texts">
                    <h1 class="hero__title">Bienvenido <% out.print(nombre); %> a un mundo de oportunidades</h1>
                    <h2 class="hero__subtitle">Si quieres acceder a uno de nuestros apartados puedes acceder a nuestra barra de navegación</h2>
                    
                </div>
            </section>
                </div>
            
            
            <div class="hero__wave" style="overflow: hidden;"><svg viewBox="0 0 500 150" preserveAspectRatio="none" style="height: 100%; width: 100%;">
                <path d="M0.00,49.98 C150.00,150.00 349.20,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z" 
                style="stroke: none; fill: #fff;"></path></svg></div>
        
        </div>
        
        
            <section class="presentation container">
                <img src="images/perfil.png" alt="" class="presentation__picture">
                <h2  class="subtitle"> Desempleo en México</h2>
                <p class="presentation__copy">  La población sin trabajo en el país al término del mes de abril ha aumentado en 574.000 personas respecto al mismo mes de 2020. 
                    En total, hay 2,7 millones de personas desocupadas, según la última Encuesta Nacional de Ocupación y Empleo del Instituto Nacional de Estadística y Geografía (Inegi).
                    La tasa de desempleo se sitúa en el 4,7% y la tasa de trabajo informal aumentó un 7,7% hasta situarse en el 55,6% de la población ocupada</p>
                <a href="https://www.forbes.com.mx/tasa-desempleo-mexico-baja-segundo-trimestre-2021/" class="presentation__cta">Más información sobre esto</a>
            </section>
            <section class="about container">
                <div class="about__texts">
                    <a name="importancia"></a>
                    <h2 class="subtitle">¿Porque fue una decisión correcta estar con nosotro?</h2>
                    <p class="about__paragraph"> Usted como trabajador podrá tener acceso a las solicitudes que sus clientes quieran pedirle aparte de que podrá tener una mensaje en el cuál podrá establecer una comunicación  con ellos</p>
                    <p class="about__paragraph">Podrá ver sus trabajos pendientes o los que ya realizo esto le permitirá tener una mejor organización en sus tiempos, esto le proporcionará más experiencia en el ambito laboral, podrá visualizar contenido de los trabajos y ver cuando tiene que entregarlos el contacto del que solicito dicho trabajo.
                    Esto le ayudará a saber cualquier detalle o especificación de lo que tiene que realizar</p>
                </div>
                <figure class="about__img about__img--left">
                    <img src="images/img2.png" alt="" class="about__picture">
                    </figure>
                
                <figure class="about__img about__img--left">
                    <img src="images/img3.png" alt="" class="about__picture">
                    </figure>
                    <div class="about__texts">
                    <h2 class="subtitle">Mensajeria</h2>
                    <p class="about__paragraph">Como sabemos la comunicación es algo importante en la actualidad y como sabemos eso, usted como trabajador podrá comunicarse con su cliente por mensajeria (chats)</p>
                    <p class="about__paragraph">Está herramienta le será muy útil ya que le permitirá hablar más con el cliente acerca de su solicitud, dar informes de sus servicio, etc. Es un herramienta muy útil que le permitirá entregar trabajos mas eficientes.</p>
                </div>
            </section>
            
            <section class="projects">
                <div class="container">
                    <a name="datoscuriosos"></a>
                    <h2 class="subtitle">Herramientas a los que podrá acceder</h2>
                    <div class="projects__grid">
                        <article class="projects__items">
                        <img src="images/img4.jpg" alt="" class="projects__img">
                        <div class="projects__hover">
                            <h2 class="projects__title">Más organización en reaizar sus actividades</h2>   
                            <i class="far fa-file-alt projects__icon"></i>
                        </div>
                        </article>
                        
                        <article class="projects__items">
                        <img src="images/img5.jpg" alt="" class="projects__img">
                        <div class="projects__hover">
                            <h2 class="projects__title">Listado de sus trabajos </h2>   
                            <i class="far fa-file-alt projects__icon"></i>
                        </div>
                        </article>  
                        
                        <article class="projects__items">
                        <img src="images/img.jpg" alt="" class="projects__img">
                        <div class="projects__hover">
                            <h2 class="projects__title">Listado de las solicitudes entrantes</h2>   
                            <i class="far fa-file-alt projects__icon"></i>
                        </div>
                        </article>  
                        
                        <article class="projects__items">
                        <img src="images/img6.jpg" alt="" class="projects__img">
                        <div class="projects__hover">
                            <h2 class="projects__title">Mensajería</h2>   
                            <i class="far fa-file-alt projects__icon"></i>
                        </div>
                        </article>  
                        
                        <article class="projects__items">
                        <img src="images/img7.jpg" alt="" class="projects__img">
                        <div class="projects__hover">
                            <h2 class="projects__title">Tener más organización en sus trabajos</h2>   
                            <i class="far fa-file-alt projects__icon"></i>
                        </div>
                        </article>  
                    </div>
                    
                </div>   
            </section>
            
            <section class="testimony container">
                <a name="recomendaciones"></a>
                <h2 class="subtitle">Usarios que pueden estar en contacto contigo</h2>
                
                <div class="testimony__grid">
                    <article class="testimony__item">
                        <div class="testimony__person">
                            <img src="images/img8.jpg" class="testimony__img">
                            <div class="testimony__texts">
                                <h3 class="testimony__name">Usuarios comunes</h3>
                                <p class="testimony__verification">Para solicitar un servicio</p>
                                <p class="testimony__review">Como bien sabes en la aplicación podrá acceder usuarios comunes que soliciten algún servicio en particular
                                , con esto podrás será solamente un trabajo o depende de lo que te pida el usuario</p>
                            </div>
                        </div>
                    </article>
                    <article class="testimony__item">
                        <div class="testimony__person">
                            <img src="images/img9.jpg" class="testimony__img">
                            <div class="testimony__texts">
                                <h3 class="testimony__name">Empresas</h3>
                                <p class="testimony__verification">Solicitar servicio/ contratarte</p>
                                <p class="testimony__review">Una empresa puede contactarse contigo para solicitarte un servicio o se puede dar el caso de que te contraten para un puesto, no aseguramos mucho la segunda opción ya que no a todos les puede pasar.</p>
                            </div>
                        </div>
                    </article>
                    
                </div>
                    
            </section>
        </main>
        
        
        
            
        
        <script src="https://kit.fontawesome.com/65bd9ef00c.js" crossorigin="anonymous"></script>

        <script src="main.js"></script>
    </body>
</html>
