//Valores de entrada del formulario
const contra = document.getElementById("contra");
const contraconfirm = document.getElementById("contraconfirm");

//Labels de los inputs
const cajacontra = document.getElementById("ccontra");
const cajacontraconfirm = document.getElementById("ccontraconfirm");

//Textos de las advertencias
const wcontra = document.getElementById("warning-contra");
const wcontraconfirm = document.getElementById("warning-contraconfirm");

//Cajas de las advertencias
const cwcontra = document.getElementById("cwcontra");
const cwcontraconfirm = document.getElementById("cwcontraconfirm");

//Función a enviar al formulario
function cambiarContra(){
    //Adevrtencia de cada campo (String)
    let warcontra = "";
    let warcontraconfirm ="";

    //Variable que indica el acceso
    let entrada = true;

    //Limpiamos advertencias
    limpiar();

    //Algoritmo que comprueba las contraseñas
    if(contra.value.length < 4 || contra.value.length > 20){
        warcontra = "Escoge una longitud correcta";
        entrada = false;
        cajacontra.classList.add("incorrect");
        contra.classList.add("incorrect");
        cwcontra.classList.add("active");
    }

    if(contraconfirm.value.length < 4 || contraconfirm.value.length > 20){
        warcontraconfirm = "Escoge una longitud correcta";
        entrada = false;
        cajacontraconfirm.classList.add("incorrect");
        contraconfirm.classList.add("incorrect");
        cwcontraconfirm.classList.add("active");
    }
    else{
        if(contra.value !== contraconfirm.value){
            warcontraconfirm = "Las contraseñas no coinciden";
            entrada = false;
            cajacontraconfirm.classList.add("incorrect");
            contraconfirm.classList.add("incorrect");
            cwcontraconfirm.classList.add("active");
        }
    }

    if(!entrada){
        wcontra.innerHTML = warcontra;
        wcontraconfirm.innerHTML = warcontraconfirm;
    }

    return entrada;
}

function error(){
    limpiar();
    cajacontra.classList.add("incorrect");
    contra.classList.add("incorrect");
    cwcontra.classList.add("active");
    
    wcontra.innerHTML = "Hubo un error con el tiempo de espera, vuelva a generar un código e ingrese de nuevo";
}

function limpiar(){
    wcontra.innerHTML = "";
    wcontraconfirm.innerHTML = "";

    cajacontra.classList.remove("incorrect");
    cajacontraconfirm.classList.remove("incorrect");

    contra.classList.remove("incorrect");
    contraconfirm.classList.remove("incorrect");

    cwcontra.classList.remove("active");
    cwcontraconfirm.classList.remove("active");
}