const char1 = document.getElementById("char1");
const char2 = document.getElementById("char2");
const char3 = document.getElementById("char3");
const char4 = document.getElementById("char4");
const char5 = document.getElementById("char5");
const char6 = document.getElementById("char6");
const char7 = document.getElementById("char7");
const char8 = document.getElementById("char8");

const wcode = document.getElementById("warning-code");

const cwcode = document.getElementById("cwcode");

char1.onkeyup = () => {
    if(char1.value.length > 1){
        char1.value = char1.value.toString().substring(0, 1);
    }
    
    if(char1.value.length === 1){
        char2.focus();
    }
};

char2.onkeyup = () => {
    if(char2.value.length >= 1){
        char2.value = char2.value.toString().substring(0, 1);
    }
    
    if(char2.value.length === 1){
        char3.focus();
    }
};

char3.onkeyup = () => {
    if(char3.value.length >= 1){
        char3.value = char3.value.toString().substring(0, 1);
    }
    
    if(char3.value.length === 1){
        char4.focus();
    }
};

char4.onkeyup = () => {
    if(char4.value.length >= 1){
        char4.value = char4.value.toString().substring(0, 1);
    }
    
    if(char4.value.length === 1){
        char5.focus();
    }
};

char5.onkeyup = () => {
    if(char5.value.length >= 1){
        char5.value = char5.value.toString().substring(0, 1);
    }
    
    if(char5.value.length === 1){
        char6.focus();
    }
};

char6.onkeyup = () => {
    if(char6.value.length >= 1){
        char6.value = char6.value.toString().substring(0, 1);
    }
    
    if(char6.value.length === 1){
        char7.focus();
    }
};

char7.onkeyup = () => {
    if(char7.value.length >= 1){
        char7.value = char7.value.toString().substring(0, 1);
    }
    
    if(char7.value.length === 1){
        char8.focus();
    }
};

char8.onkeyup = () => {
    if(char8.value.length >= 1){
        char8.value = char8.value.toString().substring(0, 1);
    }
};

function comprobar(){
    
    let entrada = true;
    
    limpiar();
    
    if(char1.value.length < 1){
        char1.classList.add("incorrect");
        entrada = false;
    }
    
    if(char2.value.length < 1){
        char2.classList.add("incorrect");
        entrada = false;
    }
    
    if(char3.value.length < 1){
        char3.classList.add("incorrect");
        entrada = false;
    }
    
    if(char4.value.length < 1){
        char4.classList.add("incorrect");
        entrada = false;
    }
    
    if(char5.value.length < 1){
        char5.classList.add("incorrect");
        entrada = false;
    }
    
    if(char6.value.length < 1){
        char6.classList.add("incorrect");
        entrada = false;
    }
    
    if(char7.value.length < 1){
        char7.classList.add("incorrect");
        entrada = false;
    }
    
    if(char8.value.length < 1){
        char8.classList.add("incorrect");
        entrada = false;
    }
    
    if(!entrada){
        wcode.innerHTML = "Inserta un código válido";
        cwcode.classList.add("active");
    }
    
    return entrada;
}

function limpiar(){
    char1.classList.remove("incorrect");
    char2.classList.remove("incorrect");
    char3.classList.remove("incorrect");
    char4.classList.remove("incorrect");
    char5.classList.remove("incorrect");
    char6.classList.remove("incorrect");
    char7.classList.remove("incorrect");
    char8.classList.remove("incorrect");
    
    cwcode.classList.remove("active");
    wcode.innerHTML = "";
}

function fechas(){
    cwcode.classList.add("active");
    wcode.innerHTML = "El códgio que colocas ya ha expirado, genera uno nuevo y vuelve a colocarlo.";
}

function codigo(){
    cwcode.classList.add("active");
    wcode.innerHTML = "El código no coincide con el último código que se te fue enviado, asegúrese que esté correcto.";
}

function error(){
    cwcode.classList.add("active");
    wcode.innerHTML = "Ocurrió un  error interno en el sistema, inténtelo de nuevo o genere un nuvevo código.";
}