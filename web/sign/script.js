//Valores que recibe del registro
const nombre = document.getElementById("nombre");
const correo = document.getElementById("correo");
const contra = document.getElementById("contra");
const apellido = document.getElementById("apellido");
const telefono = document.getElementById("telefono");

//Inputs que señalaran el error
const cajanombre = document.getElementById("cnombre");
const cajacorreo = document.getElementById("ccorreo");
const cajacontra = document.getElementById("ccontra");
const cajaapellido = document.getElementById("capellido");
const cajatelefono = document.getElementById("ctelefono");

//Cajas de texto con las advertencias
const wnombre = document.getElementById("warning-nombre");
const wcorreo = document.getElementById("warning-correo");
const wcontra = document.getElementById("warning-contra");
const wapellido = document.getElementById("warning-apellido");
const wtelefono = document.getElementById("warning-telefono");

const cwnombre = document.getElementById("cwnombre");
const cwapellido = document.getElementById("cwapellido");
const cwcorreo = document.getElementById("cwcorreo");
const cwcontra = document.getElementById("cwcontra");
const cwtelefono = document.getElementById("cwtelefono");


//Función que será ejecutada al enviar el registro
function enviarRegistro(){

    //Advertencia de cada campo (cadenas) 
    let warnombre = "";
    let warcorreo = "";
    let warcontra = "";
    let warapellido = "";
    let wartelefono = "";

    //Variable que indicará el acceso al sistema
    let entrada = true;

    //Expresiones regulares
    let regexmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	let regexname = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/;
    let regextel =  /^[0-9]+$/;

    //Limpiamos las advertencias anteriores para esperar las nuevas
    wapellido.innerHTML = "";
    wnombre.innerHTML = "";
    wcorreo.innerHTML = "";
    wcontra.innerHTML = "";
    wtelefono.innerHTML = "";

    cajanombre.classList.remove("incorrect");
    cajaapellido.classList.remove("incorrect");
    cajacorreo.classList.remove("incorrect");
    cajacontra.classList.remove("incorrect");
    cajatelefono.classList.remove("incorrect");

    cwnombre.classList.remove("active");
    cwapellido.classList.remove("active");
    cwcorreo.classList.remove("active");
    cwcontra.classList.remove("active");
    cwtelefono.classList.remove("active");

    //Algoritmo que comprueba cada campo
    //Comprueba la longitud y caracteres del nombre
    if(nombre.value.length < 3 || nombre.value.length > 20){
        warnombre = "Escribe una longitud correcta para el nombre";
        entrada = false;
        cajanombre.classList.add("incorrect");
        cwnombre.classList.add("active");
    }
    else{
        if(!regexname.test(nombre.value)){
            warnombre = "Escribe un nombre válido";
            entrada = false;
            cajanombre.classList.add("incorrect");
            cwnombre.classList.add("active");
        }
    }

    //Comprueba la longitud y caracteres del apellido
    if(apellido.value.length < 2 || apellido.value.length > 20){
        warapellido = "Escribe una longitud válida en el apellido";
        entrada = false;
        cajaapellido.classList.add("incorrect");
        cwapellido.classList.add("active");
    }
    else{
        if(!regexname.test(apellido.value)){
            warapellido = "Escribe un apellido válido";
            entrada = false;
            cajaapellido.classList.add("incorrect");
            cwapellido.classList.add("active");
        }
    }

    //Comprueba la longitud y formato del correo
    if(correo.value.length < 8 || correo.value.length > 40){
        warcorreo = "Escribe una longitud válida en el correo";
        entrada = false;
        cajacorreo.classList.add("incorrect");
        cwcorreo.classList.add("active");
    }
    else{
        if(!regexmail.test(correo.value)){
            warcorreo = "Escribe un correo válido";
            entrada = false;
            cajacorreo.classList.add("incorrect");
            cwcorreo.classList.add("active");
        }
    }

    if(telefono.value.length > 0){
        if(telefono.value.length < 10 || telefono.value.length > 11){
            wartelefono = "Escribe una longitud válida en el telefono";
            entrada = false;
            cajatelefono.classList.add("incorrect");
            cwtelefono.classList.add("active");
        }
        else{
            if(!regextel.test(telefono.value)){
                wartelefono = "Escribe un numero de telefono válido";
                entrada = false;
                cwtelefono.classList.add("active");
            }
        }
    }

    //Comprueba la longitud de la contraseña
    if(contra.value.length < 4 || contra.value.length > 20){
        warcontra = "Escribe una longitud válida en la contraseña";
        entrada = false;
        cajacontra.classList.add("incorrect");
        cwcontra.classList.add("active");
    }

    if(!entrada){
        wnombre.innerHTML = warnombre;
        wapellido.innerHTML = warapellido;
        wtelefono.innerHTML = wartelefono;
        wcorreo.innerHTML = warcorreo;
        wcontra.innerHTML = warcontra;
    }

    return entrada;
}