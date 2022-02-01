//Valores de entrada del formulario
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const telefono = document.getElementById("telefono");
const contrasenanew = document.getElementById("contrasenanew");
const profile = document.getElementById("profile");
const banner = document.getElementById("banner");
const contrasenaold = document.getElementById("contrasenaold");
const description = document.getElementById("description");

//Labels de los inputs
const cajanombre = document.getElementById("cnombre");
const cajaapellido = document.getElementById("capellido");
const cajatelefono = document.getElementById("ctelefono");
const cajacontrasenanew = document.getElementById("ccontranueva");
const cajaprofile = document.getElementById("cperfil");
const cajabanner = document.getElementById("cportada");
const cajacontrasenaold = document.getElementById("ccontravieja");
const cajadescription = document.getElementById("cdescripcion");

//textos de las advertencias
const wnombre = document.getElementById("warning-nombre");
const wapellido = document.getElementById("warning-apellido");
const wtelefono = document.getElementById("warning-telefono");
const wcontrasenanew = document.getElementById("warning-contranueva");
const wprofile = document.getElementById("warning-perfil");
const wbanner = document.getElementById("warning-portada");
const wcontrasenaold = document.getElementById("warning-contravieja");
const wdescription = document.getElementById("warning-descripcion");

//Cajas de las advertencias
const cwnombre = document.getElementById("cwnombre");
const cwapellido = document.getElementById("cwapellido");
const cwtelefono = document.getElementById("cwtelefono");
const cwcontrasenanew = document.getElementById("cwcontranueva");
const cwprofile = document.getElementById("cwperfil");
const cwbanner = document.getElementById("cwportada");
const cwcontrasenaold = document.getElementById("cwcontravieja");
const cwdescription = document.getElementById("cwdescripcion");

//Función ejecutada al enviar el formulario
function enviarCambios(){
    //Advertencias de cada campo (Strings)
    let warnombre = "";
    let warapellido = "";
    let wartelefono = "";
    let warcontranueva = "";
    let warperfile = "";
    let warportada = "";
    let warcontravieja = "";
    let wardescripcion = "";

    //Variable que indicará el acceso
    let entrada = true;

    //Expresiones regulares
    let regexname = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/;
    let regextel =  /^[0-9]+$/;
    let regexdesc = /(<([^>]+)>)/i;
    let regexfiles = /(.jpg|.jpeg|.png|.gif)$/i;

    //Limpiamos advertencias anteriores para esperar las nuevas
    
    limpiar();

    //Algoritmo que comprueba cada campo
    //Comprueba nombre
    if(nombre.value.length < 3 || nombre.value.length > 20){
        warnombre = "Escoge una longitud correcta";
        entrada = false;
        cajanombre.classList.add("incorrect");
        nombre.classList.add("incorrect");
        cwnombre.classList.add("active");
    }
    else{
        if(!regexname.test(nombre.value)){
            warnombre = "Escoge un nombre válido";
            entrada = false;
            cajanombre.classList.add("incorrect");
            nombre.classList.add("incorrect");
            cwnombre.classList.add("active");
        }
    }

    //Comprueba el apellido
    if(apellido.value.length < 3 || apellido.value.length > 20){
        warapellido = "Escoge una longitud correcta";
        entrada = false;
        cajaapellido.classList.add("incorrect");
        apellido.classList.add("incorrect");
        cwapellido.classList.add("active");
    }
    else{
        if(!regexname.test(apellido.value)){
            warapellido = "Escoge un apellido válido";
            entrada = false;
            cajaapellido.classList.add("incorrect");
            apellido.classList.add("incorrect");
            cwapellido.classList.add("active");
        }
    }

    //Comprueba el telefono
    if(telefono.value.length !== 0){
        if(telefono.value.length < 10 || telefono.value.length > 11){
            wartelefono = "Escoge una longitud correcta";
            entrada = false;
            cajatelefono.classList.add("incorrect");
            telefono.classList.add("incorrect");
            cwtelefono.classList.add("active");
        }
        else{
            if(!regextel.test(telefono.value)){
                wartelefono = "Escoge un teléfono válido";
                entrada = false;
                cajatelefono.classList.add("incorrect");
                telefono.classList.add("incorrect");
                cwtelefono.classList.add("active");
            }
        }
    }

    //Comprueba la contraseña
    if(contrasenaold.value.length < 4 || contrasenaold.value.length > 20){
        warcontravieja = "Escoge una longitud correcta";
        entrada = false;
        cajacontrasenaold.classList.add("incorrect");
        contrasenaold.classList.add("incorrect");
        cwcontrasenaold.classList.add("active");
    }

    //Comprueba los archivos (perfil y portada respectivamente)
    if (profile.value.length !== 0) {
        if (!regexfiles.exec(profile.value)) {
            warperfile = "Ingresa un archivo en formato de imágen";
            entrada = false;
            cajaprofile.classList.add("incorrect");
            profile.classList.add("incorrect");
            cwprofile.classList.add("active");
        }
    }

    if (banner.value.length !== 0) {
        if(!regexfiles.exec(banner.value)){
            warportada = "Ingresa un archivo en formato de imágen";
            entrada = false;
            cajabanner.classList.add("incorrect");
            banner.classList.add("incorrect");
            cwbanner.classList.add("active");
        }
    }

    //Comprueba la contraseña nueva
    if(contrasenanew.value.length < 4 || contrasenanew.value.length > 20){
        warcontranueva = "Escoge una longitud correcta";
        entrada = false;
        cajacontrasenanew.classList.add("incorrect");
        contrasenanew.classList.add("incorrect");
        cwcontrasenanew.classList.add("active");
    }

    //Comprueba la descripción
    if(description.value.length > 5000){
        wardescripcion = "Escoge una longitud correcta";
        entrada = false;
        cajadescription.classList.add("incorrect");
        description.classList.add("incorrect");
        cwdescription.classList.add("active");
    }
    else{
        if(regexdesc.test(description.value)){
            wardescripcion = "No insertes etiquetas HTML";
            entrada = false;
            cajadescription.classList.add("incorrect");
            description.classList.add("incorrect");
            cwdescription.classList.add("active");
        }
    }

    if (!entrada) {
        wapellido.innerHTML = warapellido;
        wnombre.innerHTML = warnombre;
        wtelefono.innerHTML = wartelefono;
        wcontrasenanew.innerHTML = warcontranueva;
        wprofile.innerHTML = warperfile;
        wbanner.innerHTML = warportada;
        wcontrasenaold.innerHTML = warcontravieja;
        wdescription.innerHTML = wardescripcion;

    }
    return entrada;
}

function contraIncorrecta(){
    limpiar();
    cajacontrasenaold.classList.add("incorrect");
    contrasenaold.classList.add("incorrect");
    cwcontrasenaold.classList.add("active");
    
    wcontrasenaold.innerHTML = "La contraseña es incorrecta";
}

function limpiar(){
    wapellido.innerHTML = "";
    wnombre.innerHTML = "";
    wtelefono.innerHTML = "";
    wcontrasenanew.innerHTML = "";
    wprofile.innerHTML = "";
    wbanner.innerHTML = "";
    wcontrasenaold.innerHTML = "";
    wdescription.innerHTML = "";

    cajanombre.classList.remove("incorrect");
    cajaapellido.classList.remove("incorrect");
    cajatelefono.classList.remove("incorrect");
    cajacontrasenanew.classList.remove("incorrect");
    cajaprofile.classList.remove("incorrect");
    cajabanner.classList.remove("incorrect");
    cajacontrasenaold.classList.remove("incorrect");
    cajadescription.classList.remove("incorrect");

    nombre.classList.remove("incorrect");
    apellido.classList.remove("incorrect");
    telefono.classList.remove("incorrect");
    contrasenanew.classList.remove("incorrect");
    profile.classList.remove("incorrect");
    banner.classList.remove("incorrect");
    contrasenaold.classList.remove("incorrect");
    description.classList.remove("incorrect");

    cwnombre.classList.remove("active");
    cwapellido.classList.remove("active");
    cwtelefono.classList.remove("active");
    cwcontrasenanew.classList.remove("active");
    cwprofile.classList.remove("active");
    cwbanner.classList.remove("active");
    cwcontrasenaold.classList.remove("active");
    cwdescription.classList.remove("active");
}

