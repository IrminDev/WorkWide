//Valores de entrada del formulario
const correo = document.getElementById("email");

//Labels de los inputs
const cajacorreo = document.getElementById("cemail");

//Textos de las advertencias
const wcorreo = document.getElementById("warning-email");

//Cajas de las advertencias
const cwcorreo = document.getElementById("cwemail");

//Función a enviar al formulario
function validarEmail(){
    //Advertencia de cada campo (String)
    let warcorreo = "";
    
    let regexmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    //Variable que indica el acceso
    let entrada = true;

    //Limpiamos advertencias
    limpiar();

    //Algoritmo que comprueba las contraseñas
    //Comprueba la longitud y formato del correo
    if(correo.value.length < 8 || correo.value.length > 40){
        warcorreo = "Escribe una longitud válida en el correo";
        entrada = false;
        correo.classList.add("incorrect");
        cajacorreo.classList.add("incorrect");
        cwcorreo.classList.add("active");
    }
    else{
        if(!regexmail.test(correo.value)){
            warcorreo = "Escribe un correo válido";
            entrada = false;
            correo.classList.add("incorrect");
            cajacorreo.classList.add("incorrect");
            cwcorreo.classList.add("active");
        }
    }

    if(!entrada){
        wcorreo.innerHTML = warcorreo;
    }

    return entrada;
}

function correoNoExiste(){
    limpiar();
    cajacorreo.classList.add("incorrect");
    correo.classList.add("incorrect");
    cwcorreo.classList.add("active");
    
    wcorreo.innerHTML = "El correo no existe";
}

function errorCorreo(){
    limpiar();
    cwcorreo.classList.add("active");
    
    wcorreo.innerHTML = "Hubo un problema interno en el servidor";
}

function limpiar(){
    wcorreo.innerHTML = "";

    cajacorreo.classList.remove("incorrect");

    correo.classList.remove("incorrect");

    cwcorreo.classList.remove("active");
}