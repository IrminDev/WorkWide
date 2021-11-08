<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WorkWide</title>
    <link rel="stylesheet" href="swiper-bundle.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="style.css">

    <script src="script.js"></script>
</head>
<body>
    <header class="header">
        <nav class="nav">
            <a href="#" class="nav_logo"><span>W</span>ork<span>W</span>ide</span></a>
            <div class="nav_menu" id="nav-menu">
                <ul class="nav_list">
                    <li class="nav_item"><a href="#" class="nav_link">Inicio</a></li>
                    <li class="nav_item"><a href="../quienesomos/quienesomos.jsp" class="nav_link">Quiénes somos</a></li>
                    <li class="nav_item"><a href="../about/acerca.jsp" class="nav_link">Acerca de</a></li>
                    <li class="nav_item"><a href="../sign/identificate.jsp" class="nav_link">Idéntificate</a></li>
                </ul>
            </div>

            <div class="nav_toggle" id="nav-toggle">
                <i class="bx bx-menu"></i>
            </div>
        </nav>
    </header>

    <main class="main">
        <div class="swiper-container gallery-top">
            <div class="swiper-wrapper">

                <section class="highlights swiper-slide">
                    <img src="images/pexels-yugantar-sambhangphe-3459979.jpg" alt="" class="highlight_bg">
                    <div class="highlight_container bd-container">
                        <div class="highlight_data">
                            <h2 class="highlight_subtitle">WorkWide</h2>
                            <h1 class="highlight_title">Llega a más gente</h1>
                            <p class="highlight_desc">Con WorkWide podrás acercarte a más gente sin la necesidad de invertir un montón de dinero, llegará al objetivo de personas a las que te enfocas.</p>
                            <a href="../sign/identificate.jsp" class="highlight_button">Vamos allá <i class="bx bx-right-arrow-alt highlight_button-icon"></i></a>
                        </div>

                        <div class="highlight_video">
                            <div class="highlight_video-content">
                                <i class='bx bx-play-circle highlight_video-icon'></i>
                            </div>
                        </div>
                    </div>
                </section>
                


                <section class="highlights swiper-slide">
                    <img src="images/pexels-kulik-stepan-4384141.jpg" alt="" class="highlight_bg">
                    <div class="highlight_container bd-container">
                        <div class="highlight_data">
                            <h2 class="highlight_subtitle">WorkWide</h2>
                            <h1 class="highlight_title">Demuestra tus habilidades</h1>
                            <p class="highlight_desc">Enséñale al mundo de lo que eres capaz con tuperfil de trabajo, entre más habilidades más posibilidades dde gente que quiera trabajar junto a tí.</p>
                            <a href="../sign/identificate.jsp" class="highlight_button">Vamos allá <i class="bx bx-right-arrow-alt highlight_button-icon"></i></a>
                        </div>

                        <div class="highlight_video">
                            <div class="highlight_video-content">
                                <i class='bx bx-play-circle highlight_video-icon'></i>
                            </div>
                        </div>
                    </div>
                </section>



                <section class="highlights swiper-slide">
                    <img src="images/pexels-kulik-stepan-4384147.jpg" alt="" class="highlight_bg">
                    <div class="highlight_container bd-container">
                        <div class="highlight_data">
                            <h2 class="highlight_subtitle">WorkWide</h2>
                            <h1 class="highlight_title">Organiza tus tiempos</h1>
                            <p class="highlight_desc">Que trabajar no se vuelva una tarea más estresante, podrás administrar tus tiempos de cada trabajo que tengas pendiente por hacer visualizando tus fechas.</p>
                            <a href="../sign/identificate.jsp" class="highlight_button">Vamos allá <i class="bx bx-right-arrow-alt highlight_button-icon"></i></a>
                        </div>

                        <div class="highlight_video">
                            <div class="highlight_video-content">
                                <i class='bx bx-play-circle highlight_video-icon'></i>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="highlights_popup" id="popup">
                    <div>
                        <div class="highlights_popup-close" id="popup-close">
                            <i class="bx bx-x"></i>
                        </div>
                        <iframe class="highlights_popup-video" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>
                </section>
            </div>
        </div>

        <div class="controls gallery-thumbs">
            <div class="controls_container swiper-wrapper">
                <img src="images/pexels-yugantar-sambhangphe-3459979.jpg" alt="" class="controls_img swiper-slide">
                <img src="images/pexels-kulik-stepan-4384141.jpg" alt="" class="controls_img swiper-slide">
                <img src="images/pexels-kulik-stepan-4384147.jpg" alt="" class="controls_img swiper-slide">
            </div>
        </div>
    </main>

    <script src="gsap.min.js"></script>
    <script src="script.js"></script>
    <script src="swiper-bundle.min.js"></script>
</body>
</html>
