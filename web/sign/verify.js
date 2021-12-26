const correol = document.getElementById("correol"),
contral = document.getElementById("contral");

const cajacorreol = document.getElementById("ccorreol"),
cajacontral = document.getElementById("ccontral");

const wcorreol = document.getElementById("warning-correol"),
wcontral = document.getElementById("warning-contral");

const cwcorreol = document.getElementById("cwcorreol"),
cwcontral = document.getElementById("cwcontral");

function verificarLogin(){
    let warcorreo = "";
    let warcontra = "";
    
    let entrada = true;
    let regexmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    
    wcorreol.innerHTML = "";
    wcontral.innerHTML = "";
    
    cajacorreol.classList.remove("incorrect");
    cajacontral.classList.remove("incorrect");
    
    cwcorreol.classList.remove("active");
    cwcontral.classList.remove("active");
    
    if(correol.value.length < 8 || correol.value.length > 40){
        warcorreo = "Escribe una longitud válida en el correo";
        entrada = false;
        cajacorreol.classList.add("incorrect");
        cwcorreol.classList.add("active");
    }
    else{
        if(!regexmail.test(correol.value)){
            warcorreo = "Escribe un correo válido";
            entrada = false;
            cajacorreol.classList.add("incorrect");
            cwcorreol.classList.add("active");
        }
    }
    
    if(contral.value.length < 4 || contral.value.length > 20){
        warcontra = "Escribe una longitud válida en la contraseña";
        entrada = false;
        cajacontral.classList.add("incorrect");
        cwcontral.classList.add("active");
    }
    
    if(!entrada){
        wcorreol.innerHTML = warcorreo;
        wcontral.innerHTML = warcontra;
    }

    return entrada;
}