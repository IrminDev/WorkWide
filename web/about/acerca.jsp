<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acerca de</title>

    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/typed.js/2.0.12/typed.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="scroll-up-btn">
        <i class="fas fa-angle-up"></i>
    </div>
    <nav class="navbar">
        <div class="max-width">
            <div class="logo"><a href="#"><span>W</span>ork<span>W</span>ide</a></div>
            <ul class="menu">
                <li class="nav_item"><a href="../index/index.jsp" class="nav_link">Inicio</a></li>
                <li class="nav_item"><a href="../about/acerca.jsp" class="nav_link">Acerca de</a></li>
                <li class="nav_item"><a href="../quienesomos/quienesomos.jsp" class="nav_link">Quiénes somos</a></li>
                <li class="nav_item"><a href="../sign/identificate.jsp" class="nav_link">Idéntificate</a></li>
            </ul>
            <div class="menu-btn">
                <i class="fas fa-bars"></i>
            </div>
        </div>
    </nav>


    <section class="home" id="home">
        <div class="max-width">
            <div class="home-content">
                <div class="text-1">¡Hola! Esto es</div>
                <div class="text-2">WorkWide</div>
                <div class="text-3">Y te ayudaremos a <span class="typing"></span></div>
                <a href="../index/index.jsp">Inicio</a>
            </div>
        </div>
    </section>
    
    
    <section class="about" id="about">
        <div class="max-width">
            <h2 class="tittle">Acerca de WorkWide</h2>
            <div class="about-content">
                <div class="column left">
                    <img src="images/img1.jpg" alt="">
                </div>
                <div class="column right">
                    <div class="text">Es una aplicación que te ayudará a <span class="typing2">crecer.</span></div>
                    <p>WorkWide es una aplicación enfocada a solucionar una de las principales problemáticas que hay en México durante los últimos años, el desempleo. Es preocupante que estos números crezcan constantemente a pesar de que la población activa económicamente cada vez sea más. Nuestra aplicación consiste en hacer llegar a distintos trabajadores a un mercado más amplio en el que puedan tener abiertas las puertas a un puesto de trabajo, dentro de nuestra aplicación podrás conectarte con posibles clientes interesados en tus servicios, podrás ponerte de acuerdo con ellos en cómo realizar el trabajo y se llevará una ista acerca de los trabajos pendientes y que estás haciendo. Buscamos la mayor facilidad para los usuarios y que el trabajo no sea un estrés mayor.</p>
                    <a href="#">Descarga nuestra app móvil</a>
                </div>
            </div>
        </div>
    </section>



    <section class="services" id="services">
        <div class="max-width">
            <h2 class="tittle">Servicios</h2>
            <div class="services-content">
                <div class="card">
                    <div class="box">
                        <i class="fas fa-shield-alt"></i>
                        <div class="text">Seguridad</div>
                        <p>Tu infromación es importante, por ello nos hacemos responsables de esta resguardándola.</p>
                    </div>
                </div>

                <div class="card">
                    <div class="box">
                        <i class="fas fa-bullhorn"></i>
                        <div class="text">Alcance</div>
                        <p>Haz que tus servicios puedan llegar al mayor número de personas posibles con tu perfil de trabajo.</p>
                    </div>
                </div>

                <div class="card">
                    <div class="box">
                        <i class="fas fa-comments"></i>
                        <div class="text">Comunicación</div>
                        <p>Sabemos que para un trabajo es importante la comunicación con tus clientes, por eso implementamos un sistema de mensajería.</p>
                    </div>
                </div>

                <div class="card">
                    <div class="box">
                        <i class="fas fa-inbox"></i>
                        <div class="text">Organización</div>
                        <p>Podrás administrar y organizar tus tiempos mediante nuestra aplicación de tal forma que sea lo más sencillo para tí.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>



    <section class="skills" id="skills">
        <div class="max-width">
            <h2 class="tittle">Mixco Tech</h2>
            <div class="skills-content">
                <div class="column left">
                    <div class="text">¿En qué nos especializamos?</div>
                    <p>Somos una empresa mexicana de software que trabaja en propuestas de solución a problemas de la actualidad en la sociedad. Nuestra misión es innovar desde la perspectiva funcional, dando lo mejor de nosotros para alcanzar un buen resultado de nuestro trabajo y además que pueda ser accesible para nuestros cliente. Manejamos algunas tecnologías para desarrollo fornt-end, back-end y para el desarrollo de software en plataformas móviles y de ordenador.</p>
                    <a href="../quienesomos/quienesomos.jsp">Ver más de nosotros</a>
                </div>
                <div class="column right">
                    <div class="bars">
                        <div class="info">
                            <span>HTML</span>
                            <span>80%</span>
                        </div>
                        <div class="line html"></div>
                    </div>
                    <div class="bars">
                        <div class="info">
                            <span>CSS</span>
                            <span>70%</span>

                        </div>
                        <div class="line css"></div>
                    </div>
                    <div class="bars">
                        <div class="info">
                            <span>Javascript</span>
                            <span>50%</span>
                        </div>
                        <div class="line js"></div>
                    </div>
                    <div class="bars">
                        <div class="info">
                            <span>Java</span>
                            <span>90%</span>
                        </div>
                        <div class="line java"></div>
                    </div>
                    <div class="bars">
                        <div class="info">
                            <span>MySQL</span>
                            <span>60%</span>
                        </div>
                        <div class="line sql"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <section class="team" id="team">
        <div class="max-width">
            <h2 class="tittle">Equipo de trabajo</h2>
            <div class="team-content">
                <div class="carousel owl-carousel">

                    <div class="card">
                        <div class="box">
                            <img src="https://scoent-dfw5-2.xx.fbcdn.net/v/t39.30808-6/242006014_1018173102314958_3695927718000661905_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=-E2PCCerFqsAX9ztFAS&_nc_ht=scontent-dfw5-2.xx&oh=e00707e0f15e79547770f3fe75433cdc&oe=615D023E" alt="">
                            <div class="text">Irmin Hernández</div>
                            <p>Líder de proyectos encargado de manejar y administar a nuestro equipo de trabajo.</p>
                        </div>
                    </div>

                    <div class="card">
                        <div class="box">
                            <img src="https://scoent.fmex1-1.fna.fbcdn.net/v/t1.6435-1/p200x200/169071137_3362947030471753_1766311584333381886_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=7206a8&_nc_ohc=keHlVrcn8tIAX-If3x5&_nc_ht=scontent.fmex1-1.fna&oh=e1b2d18923425567f764f55f3f936f9b&oe=61805EB7" alt="">
                            <div class="text">Luis Contreras</div>
                            <p>Arquitecto de software encargado del diseño y desarrollo de alto nivel del software.</p>
                        </div>
                    </div>

                    <div class="card">
                        <div class="box">
                            <img src="https://scontt.fmex1-2.fna.fbcdn.net/v/t1.6435-9/128821445_1172254069843228_4363270663948802992_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=qMhQPY6vZssAX8-V4a-&_nc_ht=scontent.fmex1-2.fna&oh=4d041bb4339aee58ab330b8fdcefebcf&oe=617FCC33" alt="">
                            <div class="text">Montserrat Rivas</div>
                            <p>Desarrolladora enfocada en SQL encargada de la administración de las bases de datos.</p>
                        </div>
                    </div>

                    <div class="card">
                        <div class="box">
                            <img src="https://scontt-dfw5-2.xx.fbcdn.net/v/t1.6435-1/p200x200/215210294_939594963491828_8308269095607619236_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=7206a8&_nc_ohc=xuzDK9jOzO0AX_Kj5Ht&_nc_ht=scontent-dfw5-2.xx&oh=e163c045dce197dae76a5f54a295bc85&oe=617E9E9F" alt="">
                            <div class="text">Daniela Sosa</div>
                            <p>Diseñadora web encargada del mantenimiento constante y desarrollo del front-end.</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

    <footer>
        <span>Creado por <a href="../quienesomos/quienesomos.jsp">Mixco Tech</a> | <span class="fas fa-copyright"></span> 2021 Todos los derechos reservados</span>
    </footer>

    <script src="script.js"></script>
</body>
</html>