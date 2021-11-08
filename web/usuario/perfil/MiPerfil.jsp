<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
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

    
    <div class="container-card">
        <div class="profile-header">
            <div class="profile-img">
                <img src="user.jpg" alt="">
            </div>
            <div class="profile-nav-info">
                <h3 class="username">Irmin Jiménez</h3>
                <div class="address">
                    <p class="state">Estado de México,</p>
                    <span class="country"> México.</span>
                </div>
            </div>
        </div>
        <div class="main-bd">
            <div class="left-side">
                <div class="profile-side">
                    <p class="mobile-number"><i class='bx bxs-phone'></i>+52 5547762643</p>
                    <p><i class='bx bxs-envelope' ></i>irminrt@gmail.com</p>
                    <div class="user-work">
                        <h3>Trabajo</h3>
                        <p class="work">
                            Programación
                        </p>
                    </div>
                    <div class="profile-btn">
                        <button class="profile-chat">
                            <i class='bx bxs-message' ></i>Chat
                        </button>
                        <button class="profile-req">
                            <i class='bx bxs-file-plus' ></i>Solicitud
                        </button>
                    </div>
                </div>
            </div>
            <div class="right-side">
                <div class="nav-profile">
                    <ul>
                        <li onclick="tabs(0)" class="user-info">Acerca de</li>
                        <li onclick="tabs(1)" class="user-info">Opciones</li>
                    </ul>
                </div>
                <div class="profile-body">
                    <div class="profile-info tab">
                        <h1>Acerca de Irmin</h1>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam est similique dolor officia, vel mollitia possimus cumque quis adipisci autem iste, hic eaque aliquid labore, minus facere ea molestias fuga!
                        Veniam animi quidem, aut cumque consequatur corrupti dolorum voluptas et nobis architecto corporis consequuntur ducimus pariatur doloremque rem nulla accusantium sint omnis libero exercitationem inventore tenetur! Magnam accusantium culpa nemo!
                        Modi, laboriosam earum! Hic quibusdam unde nostrum maiores odit doloremque delectus dolorum natus, repellendus voluptatum aut accusamus, illum eius recusandae iusto explicabo id? Nulla reprehenderit quisquam, eaque eius iste assumenda.
                        Facilis necessitatibus mollitia enim, quasi ratione eius alias, ad reiciendis ex a quae atque quaerat! Nam maxime exercitationem quidem est minus voluptatibus soluta error natus libero nisi adipisci, laudantium officia.
                        Omnis voluptas a esse porro et delectus, culpa debitis nobis, dolorem optio pariatur magnam ipsam assumenda eaque reiciendis odit atque voluptatum repellat dolore quas! Sint non deserunt quasi ipsum ea?</p>
                    </div>
                    <div class="profile-info tab profile-btns">
                        <h1>Opciones</h1>
                        <button class="profile-conf"><i class='bx bxs-cog' ></i>Configuración de cuenta</button>
                        <button class="profile-del"><i class='bx bxs-trash '></i>Eliminar perfil</button>
                        <button class="profile-close"><i class='bx bx-log-out  ' ></i>Cerrar sesión</button>
                        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Sequi enim eum eligendi consequuntur tempore assumenda aut nulla nisi eaque quo corrupti, laboriosam id impedit quibusdam aliquam! Possimus illo dignissimos aut?
                        Consequuntur accusamus ullam optio veritatis fugiat obcaecati voluptatibus illo tempore, ipsam temporibus vitae maiores rerum sed soluta ex similique modi voluptate corrupti quam atque! Eum hic inventore magnam cumque! Cum?
                        Iusto id autem, neque hic, ea facilis necessitatibus omnis quo culpa modi dolorum ducimus quibusdam! Unde, tempore ipsam deserunt iste tempora quod. Similique qui molestias, facere nulla aperiam nemo a.
                        Saepe modi enim assumenda est id alias eius molestiae commodi! Voluptatibus eligendi maxime quas ad voluptatum, cum illum sequi qui ducimus nihil pariatur optio incidunt. Eligendi necessitatibus commodi odio? Illum!
                        Dolorum animi aut est sapiente hic, illum non placeat porro minus repellendus atque expedita, unde ipsum ad debitis a consequuntur similique nostrum, cupiditate vel. Incidunt a aspernatur distinctio porro veniam?</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="script.js"></script>
</body>
</html>
