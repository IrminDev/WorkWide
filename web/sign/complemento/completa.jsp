
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
        <form action="">
            <div class="row">
                <div class="column">
                    <label for="work">Trabajo</label>
                    <select name="work" id="work">
                        <optgroup label="Escoge una profesión">
                            <option value="">Abogado</option>
                            <option value="">Asesor</option>
                            <option value="">Asistente</option>
                            <option value="">Bibliotecario</option>
                            <option value="">Bioquímico</option>
                            <option value="">Camarógrafo</option>
                            <option value="">Campesino</option>
                            <option value="">Carpintero</option>
                            <option value="">Cartógrafo</option>
                            <option value="">Chef</option>
                            <option value="">Chófer</option>
                            <option value="">Científico</option>
                            <option value="">Conserje</option>
                            <option value="">Criminólogo</option>
                            <option value="">Cuidador</option>
                            <option value="">Dermatólogo</option>
                            <option value="">Dibujante</option>
                            <option value="">Docente</option>
                            <option value="">Doctor</option>
                            <option value="">Economista</option>
                            <option value="">Electricista</option>
                            <option value="">Estilista</option>
                            <option value="">Fabricante</option>
                            <option value="">Farmacéutico</option>
                            <option value="">Guía</option>
                            <option value="">Guarda</option>
                            <option value="">Herborista</option>
                            <option value="">Informático</option>
                            <option value="">Inegeniero agrónomo</option>
                            <option value="">Instructor</option>
                            <option value="">Mecánico</option>
                            <option value="">Médico</option>
                            <option value="">Neumólogo</option>
                            <option value="">Nutriólogo</option>
                            <option value="">Obrero</option>
                            <option value="">Oculista</option>
                            <option value="">Odontólogo</option>
                            <option value="">Ortopedista</option>
                            <option value="">Periodista</option>
                            <option value="">Plomero</option>
                            <option value="">Profesor</option>
                            <option value="">Programador</option>
                            <option value="">Psicólogo</option>
                            <option value="">Químico</option>
                            <option value="">Técnico</option>
                            <option value="">Tesorero</option>
                            <option value="">Veterinario</option>
                            <option value="">Vigilante</option>
                            <option value="">Zapatero</option>
                        </optgroup>
                    </select>
                </div>
                <div class="column">
                    <label for="ubication">Región</label>
                    <select name="ubication" id="ubication">
                        <optgroup label="Escoge una región">
                            <option value="Aguascalientes">Aguascalientes</option>
                            <option value="Baja California">Baja California</option>
                            <option value="Baja California Sur">Baja California Sur</option>
                            <option value="Campeche">Campeche</option>
                            <option value="Chiapas">Chiapas</option>
                            <option value="Chihuahua">Chihuahua</option>
                            <option value="CDMX">Ciudad de México</option>
                            <option value="Coahuila">Coahuila</option>
                            <option value="Colima">Colima</option>
                            <option value="Durango">Durango</option>
                            <option value="Estado de México">Estado de México</option>
                            <option value="Guanajuato">Guanajuato</option>
                            <option value="Guerrero">Guerrero</option>
                            <option value="Hidalgo">Hidalgo</option>
                            <option value="Jalisco">Jalisco</option>
                            <option value="Michoacán">Michoacán</option>
                            <option value="Morelos">Morelos</option>
                            <option value="Nayarit">Nayarit</option>
                            <option value="Nuevo León">Nuevo León</option>
                            <option value="Oaxaca">Oaxaca</option>
                            <option value="Puebla">Puebla</option>
                            <option value="Querétaro">Querétaro</option>
                            <option value="Quintana Roo">Quintana Roo</option>
                            <option value="San Luis Potosí">San Luis Potosí</option>
                            <option value="Sinaloa">Sinaloa</option>
                            <option value="Sonora">Sonora</option>
                            <option value="Tabasco">Tabasco</option>
                            <option value="Tamaulipas">Tamaulipas</option>
                            <option value="Tlaxcala">Tlaxcala</option>
                            <option value="Veracruz">Veracruz</option>
                            <option value="Yucatán">Yucatán</option>
                            <option value="Zacatecas">Zacatecas</option>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="work">Foto de perfil</label>
                    <input type="file">
                </div>
                <div class="column">
                    <label for="ubication">Foto de portada</label>
                    <input type="file">
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="description">Regálanos una descripción de tí</label>
                    <textarea name="" rows="10" id="description" placeholder="Describe tu persona y tu trabajo, esta descripción será la que muestres a tus potenciales clientes."></textarea>
                </div>
            </div>
            <button class="form-button">Completar registro</button>
        </form>
    </div>
</body>
</html>