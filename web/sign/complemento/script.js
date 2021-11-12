//Valores de entrada del formulario
const profile = document.getElementById("profile");
const banner = document.getElementById("banner");
const description = document.getElementById("description");

//Labels de los inputs
const cajaprofile = document.getElementById("cperfil");
const cajabanner = document.getElementById("cportada");
const cajadescription = document.getElementById("cdescripcion");

//textos de las advertencias
const wprofile = document.getElementById("warning-perfil");
const wbanner = document.getElementById("warning-portada");
const wdescription = document.getElementById("warning-descripcion");

//Cajas de las advertencias
const cwprofile = document.getElementById("cwperfil");
const cwbanner = document.getElementById("cwportada");
const cwdescription = document.getElementById("cwdescripcion");

//Función ejecutada al enviar el formulario
function enviarInfo(){
    //Advertencias de cada campo (Strings)
    let warperfile = "";
    let warportada = "";
    let wardescripcion = "";

    //Variable que indicará el acceso
    let entrada = true;

    //Expresiones regulares
    let regexdesc = /(<([^>]+)>)/i;
    let regexfiles = /(.jpg|.jpeg|.png|.gif)$/i;

    //Limpiamos advertencias anteriores para esperar las nuevas
    wprofile.innerHTML = "";
    wbanner.innerHTML = "";
    wdescription.innerHTML = "";

    cajaprofile.classList.remove("incorrect");
    cajabanner.classList.remove("incorrect");
    cajadescription.classList.remove("incorrect");

    profile.classList.remove("incorrect");
    banner.classList.remove("incorrect");
    description.classList.remove("incorrect");

    cwprofile.classList.remove("active");
    cwbanner.classList.remove("active");
    cwdescription.classList.remove("active");

    //Algoritmo que comprueba cada campo

    //Comprueba los archivos (perfil y portada respectivamente)
    if (profile.value !== 0) {
        if (!regexfiles.exec(profile.value)) {
            warperfile = "Ingresa un archivo en formato de imágen";
            entrada = false;
            cajaprofile.classList.add("incorrect");
            profile.classList.add("incorrect");
            cwprofile.classList.add("active");
        }
    }
    else{
        warperfile = "Ingresa un archivo en formato de imágen";
        entrada = false;
        cajaprofile.classList.add("incorrect");
        profile.classList.add("incorrect");
        cwprofile.classList.add("active");
    }

    if (banner.value !== 0) {
        if(!regexfiles.exec(banner.value)){
            warportada = "Ingresa un archivo en formato de imágen";
            entrada = false;
            cajabanner.classList.add("incorrect");
            banner.classList.add("incorrect");
            cwbanner.classList.add("active");
        }
    }
    else{
        warportada = "Ingresa un archivo en formato de imágen";
        entrada = false;
        cajabanner.classList.add("incorrect");
        banner.classList.add("incorrect");
        cwbanner.classList.add("active");
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
        wprofile.innerHTML = warperfile;
        wbanner.innerHTML = warportada;
        wdescription.innerHTML = wardescripcion;

    }
    return entrada;
}