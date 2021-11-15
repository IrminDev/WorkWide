
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Necesitamos más información</title>
</head>
<body>
    <div class="content">
        <h1>Necesitamos más información...</h1>
        <p>Como trabajador, brindanos la siguiente información, es necesario que llenes todos los campos correctamente para que puedas tener una buena experiencia en potenciales empleos.</p>
        <form action="../../ControlUsuarios" method="POST" id="Completar" enctype="multipart/form-data">
            <div class="row">
                <div class="column">
                    <label for="work">Trabajo</label>
                    <select name="work" id="work">
                        <optgroup label="Escoge una profesión">
                            <option value="1">Abogado</option>
                            <option value="2">Asesor</option>
                            <option value="3">Asistente</option>
                            <option value="4">Bibliotecario</option>
                            <option value="5">Bioquímico</option>
                            <option value="6">Camarógrafo</option>
                            <option value="7">Campesino</option>
                            <option value="8">Carpintero</option>
                            <option value="9">Cartógrafo</option>
                            <option value="10">Chef</option>
                            <option value="11">Chófer</option>
                            <option value="12">Científico</option>
                            <option value="13">Conserje</option>
                            <option value="14">Criminólogo</option>
                            <option value="15">Cuidador</option>
                            <option value="16">Dermatólogo</option>
                            <option value="17">Dibujante</option>
                            <option value="18">Docente</option>
                            <option value="19">Doctor</option>
                            <option value="20">Economista</option>
                            <option value="21">Electricista</option>
                            <option value="22">Estilista</option>
                            <option value="23">Fabricante</option>
                            <option value="24">Farmacéutico</option>
                            <option value="25">Guía</option>
                            <option value="26">Guarda</option>
                            <option value="27">Herborista</option>
                            <option value="28">Informático</option>
                            <option value="29">Inegeniero agrónomo</option>
                            <option value="30">Instructor</option>
                            <option value="30">Mecánico</option>
                            <option value="32">Médico</option>
                            <option value="33">Neumólogo</option>
                            <option value="34">Nutriólogo</option>
                            <option value="35">Obrero</option>
                            <option value="36">Oculista</option>
                            <option value="37">Odontólogo</option>
                            <option value="38">Ortopedista</option>
                            <option value="39">Periodista</option>
                            <option value="40">Plomero</option>
                            <option value="41">Profesor</option>
                            <option value="42">Programador</option>
                            <option value="43">Psicólogo</option>
                            <option value="44">Químico</option>
                            <option value="45">Técnico</option>
                            <option value="46">Tesorero</option>
                            <option value="47">Veterinario</option>
                            <option value="48">Vigilante</option>
                            <option value="49">Zapatero</option>
                        </optgroup>
                    </select>
                </div>
                <div class="column">
                    <label for="ubication">Región</label>
                    <select name="ubication" id="ubication">
                        <optgroup label="Escoge una región">
                            <option value="1">Aguascalientes</option>
                            <option value="2">Baja California</option>
                            <option value="3">Baja California Sur</option>
                            <option value="4">Campeche</option>
                            <option value="5">Chiapas</option>
                            <option value="6">Chihuahua</option>
                            <option value="7">Ciudad de México</option>
                            <option value="8">Coahuila</option>
                            <option value="9">Colima</option>
                            <option value="10">Durango</option>
                            <option value="11">Estado de México</option>
                            <option value="12">Guanajuato</option>
                            <option value="13">Guerrero</option>
                            <option value="14">Hidalgo</option>
                            <option value="15">Jalisco</option>
                            <option value="16">Michoacán</option>
                            <option value="17">Morelos</option>
                            <option value="18">Nayarit</option>
                            <option value="19">Nuevo León</option>
                            <option value="20">Oaxaca</option>
                            <option value="21">Puebla</option>
                            <option value="22">Querétaro</option>
                            <option value="23">Quintana Roo</option>
                            <option value="24">San Luis Potosí</option>
                            <option value="25">Sinaloa</option>
                            <option value="26">Sonora</option>
                            <option value="27">Tabasco</option>
                            <option value="28">Tamaulipas</option>
                            <option value="29">Tlaxcala</option>
                            <option value="30">Veracruz</option>
                            <option value="31">Yucatán</option>
                            <option value="32">Zacatecas</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="work" id="cperfil">Foto de perfil</label>
                    <input type="file" name="profile" id="profile">
                    <div class="warning" id="cwperfil">
                        <p id="warning-perfil"></p>
                    </div>
                </div>
                <div class="column">
                    <label for="ubication" id="cportada">Foto de portada</label>
                    <input type="file" name="banner" id="banner">
                    <div class="warning" id="cwportada">
                        <p id="warning-portada"></p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="description" id="cdescripcion">Regálanos una descripción de tí</label>
                    <textarea form="Completar" rows="10" name="description" id="description" placeholder="Describe tu persona y tu trabajo, esta descripción será la que muestres a tus potenciales clientes."></textarea>
                    <div class="warning" id="cwdescripcion">
                        <p id="warning-descripcion"></p>
                    </div>
                </div>
            </div>
            <button class="form-button" name="accion" type="submit" value="Completar" onclick="return enviarInfo()">Completar registro</button>
        </form>
    </div>

    <script src="script.js"></script>
</body>
</html>