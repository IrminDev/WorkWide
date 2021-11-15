<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Idéntificate</title>
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="forms-container">
                <div class="inicio-registro">
                    <form class="inicioForm" method="POST" action="../ControlUsuarios">
                        <h2 class="title">Inicio de sesión</h2>
                        <div class="input-field">
                            <i class="fas fa-envelope"></i>
                            <input type="text" name="correol" placeholder="Correo electrónico">
                        </div>
                        <div class="input-field">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="contral" placeholder="Contraseña">
                        </div>
                        <input name="accion" type="submit" value="Iniciar" class="btn solid">
                        <div class="registro-link">
                            <p class="social-text">¿No estás registrado? <a href="" class="link">Regístrate</a></p>
                        </div>
                            <p class="social-text">Para regresar al inicio da clic <a href="../index/index.jsp" class="link">aquí</a></p>
                    </form>

                    <form class="registroForm" action="../ControlUsuarios" method="POST">
                        <h2 class="title">Registro</h2>
                        <div class="input-field" id="cnombre">
                            <i class="fas fa-user"></i>
                            <input type="text" id="nombre" name="nombreR" placeholder="Nombre">
                        </div>
                        <div class="warning" id="cwnombre">
                            <i class="fas fa-exclamation"></i>
                            <p id="warning-nombre"></p>
                        </div>
                        <div class="input-field" id="capellido">
                            <i class="fas fa-user"></i>
                            <input type="text" id="apellido" name="apellidoR" placeholder="Apellido">
                        </div>
                        <div class="warning" id="cwapellido">
                            <i class="fas fa-exclamation"></i>
                            <p id="warning-apellido"></p>
                        </div>
                        <div class="input-field" id="ccorreo">
                            <i class="fas fa-envelope"></i>
                            <input type="email" id="correo" name="correoR" placeholder="Correo electrónico">
                        </div>
                        <div class="warning" id="cwcorreo">
                            <i class="fas fa-exclamation"></i>
                            <p id="warning-correo"></p>
                        </div>
                        <div class="input-field" id="ctelefono">
                            <i class="fas fa-phone"></i>
                            <input type="text" id="telefono" name="telefonoR" placeholder="Teléfono">
                        </div>
                        <div class="warning" id="cwtelefono">
                            <i class="fas fa-exclamation"></i>
                            <p id="warning-telefono"></p>
                        </div>
                        <div class="input-field" id="ccontra">
                            <i class="fas fa-lock"></i>
                            <input type="password" id="contra" name="contraR" placeholder="Contraseña">
                        </div>
                        <div class="warning" id="cwcontra">
                            <i class="fas fa-exclamation"></i>
                            <p id="warning-contra"></p>
                        </div>

                        <div class="grupo">
                            <div class="input-field">
                                <i class="fas fa-tag" id="repair"></i>
                                <select name="tipoR" id="">
                                    <optgroup label="Escoge una opción">
                                        <option value="1">Usuario</option>
                                        <option value="2">Trabajador</option>
                                    </optgroup>
                                </select>
                            </div>
                            <input name="accion" type="submit" onclick="return enviarRegistro()" value="Registrar" class="btn solid">
                        </div>

                        <div class="inicio-link">
                            <p class="social-text">¿Ya tienes una cuenta? <a href="" class="link">Inicia sesión</a></p>
                        </div>
                            <p class="social-text">Para regresar al inicio da clic <a href="../index/index.jsp" class="link" >aquí</a></p>
                    </form>
                </div>
            </div>
            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="content">
                        <h3>¿Eres nuevo aquí?</h3>
                        <p>¡Crea una cuenta ahora mismo! Si solo bucas gente con quien trabajar crea una cuenta de usuario, si buscas ofrecer tus servicios regístrate como un trabajador, después de completar tu registro te haremos otras preguntas para que puedas completar correctamente tu perfil dependiendo tu rol.</p>
                        <button class="btn transparent" id="registro-btn">Regístrate</button>
                    </div>
                    <img src="assets/undraw_Real_time_sync_re_nky7.svg" class="image" alt="registro">
                </div>

                <div class="panel right-panel">
                    <div class="content">
                        <h3>¿Ya tienes una cuenta?</h3>
                        <p>¡Inicia seión con tu cuenta! Entra a tu cuenta para observar a los distintos trabajadores y sus servicios que ofrecen en nuestra plataforma o para seguir potenciando tu negocio con posibles clientes interesados en lo que mejor haces.</p>
                        <button class="btn transparent" id="inicio-btn">Inicia sesión</button>
                    </div>
                    <img src="assets/undraw_Spreadsheets_re_alt0.svg" class="image" alt="inicio">
                </div>
            </div>
        </div>
        <script src="app.js"></script>
        <script src="script.js"></script>
    </body>
</html>