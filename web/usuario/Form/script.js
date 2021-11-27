//Valores de entrada del formulario
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");

const fecha = document.getElementById("fecha");
const fecha2 = document.getElementById("fecha2");
const profile = document.getElementById("profile");
const banner = document.getElementById("banner");
const contrasenaold = document.getElementById("contrasenaold");
const description = document.getElementById("description");
const soli = document.getElementById("soli");
const correo = document.getElementById("correo");
const correo2 = document.getElementById("correo2");

//Labels de los inputs
const cajacorreo = document.getElementById("ccorreo");
const cajacorreo2 = document.getElementById("ccorreo2");
const cajanombre = document.getElementById("cnombre");
const cajaapellido = document.getElementById("capellido");
const cajafecha = document.getElementById("cfecha");
const cajafecha2 = document.getElementById("cfecha2");

const cajaprofile = document.getElementById("cperfil");
const cajabanner = document.getElementById("cportada");

const cajadescription = document.getElementById("cdescripcion");
const cajasoli = document.getElementById("csoli");

//textos de las advertencias
const wnombre = document.getElementById("warning-nombre");
const wapellido = document.getElementById("warning-apellido");
const wcorreo = document.getElementById("warning-correo");
const wcorreo2 = document.getElementById("warning-correo2");

const wfecha = document.getElementById("warning-fecha");
const wfecha2 = document.getElementById("warning-fecha2");

const wbanner = document.getElementById("warning-portada");
const wcontrasenaold = document.getElementById("warning-contravieja");
const wdescription = document.getElementById("warning-descripcion");
const wsoli = document.getElementById("warning-soli");

//Cajas de las advertencias
const cwnombre = document.getElementById("cwnombre");
const cwapellido = document.getElementById("cwapellido");
const cwcorreo = document.getElementById("cwcorreo");
const cwcorreo2 = document.getElementById("cwcorreo2");



const cwfecha = document.getElementById("cwfecha");
const cwfecha2 = document.getElementById("cwfecha2");

const cwbanner = document.getElementById("cwportada");
const cwcontrasenaold = document.getElementById("cwcontravieja");
const cwdescription = document.getElementById("cwdescripcion");
const cwsoli = document.getElementById("cwsoli");

//Función ejecutada al enviar el formulario
function enviarCambios(){
    //Advertencias de cada campo (Strings)
    let warnombre = "";
    let warapellido = "";
    let warcorreo = "";
    let warcorreo2 = "";
    let warfecha = "";
    let warfecha2 = "";
    let warsoli = "";

    //Preparaciones para comparar fechas
    var fecha_intput = document.getElementById('fecha').value;
    var fecha_intput2 = document.getElementById('fecha2').value;

    console.log(fecha);
    var f =new Date();
    var mes= (f.getMonth()+1).toString();
    var dia = (f.getDate()).toString();
    if (mes.length <= 1){
        mes ="0"+mes;
    }
     if (dia.length <= 1){
        dia ="0"+dia;
    }
    console.log(mes);
    console.log(dia);
    fecha_actual=f.getFullYear()+ "-" +mes+ "-"+ dia;
    console.log(fecha_actual);
    let wardescripcion = "";

    //Variable que indicará el acceso
    let entrada = true;

    //Expresiones regulares
    let regexname = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/;
    let regexmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    let regexdesc = /(<([^>]+)>)/i;


    //Limpiamos advertencias anteriores para esperar las nuevas
    wapellido.innerHTML = "";
    wnombre.innerHTML = "";
    wcorreo.innerHTML = "";
    wcorreo2.innerHTML = "";
    wfecha.innerHTML = "";
    wfecha2.innerHTML = "";



    wdescription.innerHTML = "";
    wsoli.innerHTML = "";
    
    cajanombre.classList.remove("incorrect");
    cajaapellido.classList.remove("incorrect");
    cajacorreo.classList.remove("incorrect");;
    cajacorreo2.classList.remove("incorrect");;
    cajafecha.classList.remove("incorrect");;
    cajafecha2.classList.remove("incorrect");;


   
    cajasoli.classList.remove("incorrect");  
    cajadescription.classList.remove("incorrect");

    nombre.classList.remove("incorrect");
    apellido.classList.remove("incorrect");
    correo.classList.remove("incorrect");
    correo2.classList.remove("incorrect");
    fecha.classList.remove("incorrect");
    fecha2.classList.remove("incorrect");
    

    soli.classList.remove("incorrect");
    description.classList.remove("incorrect");

    cwnombre.classList.remove("active");
    cwapellido.classList.remove("active");
    cwcorreo.classList.remove("active");
    cwcorreo2.classList.remove("active");
    cwfecha.classList.remove("active");
    cwfecha2.classList.remove("active");

    cwsoli.classList.remove("active"); 
    cwdescription.classList.remove("active");

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

    //Comprueba el correo
   if(correo.value.length < 8 || correo.value.length > 40){
        warcorreo = "Escribe una longitud válida en el correo";
        entrada = false;
        cajacorreo.classList.add("incorrect");
        correo.classList.add("incorrect");
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
        //Comprueba el correo 2
   if(correo2.value.length < 8 || correo2.value.length > 40){
        warcorreo2 = "Escribe una longitud válida en el correo";
        entrada = false;
        cajacorreo2.classList.add("incorrect");
        correo2.classList.add("incorrect");
        cwcorreo2.classList.add("active");
    }
    else{
        if(!regexmail.test(correo.value)){
            warcorreo2 = "Escribe un correo válido";
            entrada = false;
            cajacorreo2.classList.add("incorrect");
            cwcorreo2.classList.add("active");
        }
    }
    //Comprueba fecha de inicio
    if(fecha_intput<fecha_actual && fecha_intput > fecha_intput2 ){
        warfecha="Selecciona una fecha mayor a la actual y menor que la de finalizacion";
        entrada = false;
        cajafecha.classList.add("incorrect");
        fecha.classList.add("incorrect");
        cwfecha.classList.add("active");
    }
    else if(fecha_intput<fecha_actual){
        warfecha="Selecciona una fecha mayor a la actual";
        entrada = false;
        cajafecha.classList.add("incorrect");
        fecha.classList.add("incorrect");
        cwfecha.classList.add("active");
    }
    else if(fecha_intput > fecha_intput2){
        warfecha="Selecciona una fecha menor a la fecha de finalizacion";
        entrada = false;
        cajafecha.classList.add("incorrect");
        fecha.classList.add("incorrect");
        cwfecha.classList.add("active"); 
        
    }
    //Comprueba fecha de finalización
     if(fecha_intput2 < fecha_intput && fecha_intput2<fecha_actual ){
        warfecha2="Selecciona una fecha mayor a la fecha actual y a la de inicio";
        entrada = false;
        cajafecha2.classList.add("incorrect");
        fecha2.classList.add("incorrect");
        cwfecha2.classList.add("active"); 
        }
    else if(fecha_intput2<fecha_intput){
        warfecha2="Selecciona una fecha mayor a la fecha de inicio";
        entrada = false;
        cajafecha2.classList.add("incorrect");
        fecha2.classList.add("incorrect");
        cwfecha2.classList.add("active");
    }
    else if(fecha_intput2<fecha_actual){
        warfecha2="Selecciona una fecha mayor a la fecha actual";
        entrada = false;
        cajafecha2.classList.add("incorrect");
        fecha2.classList.add("incorrect");
        cwfecha2.classList.add("active"); 
        }
       
    
    
   
   

  




    //Comprueba la descripción
    if(description.value.length > 500){
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
        //Comprueba el titulo
    if(soli.value.length > 50){
        wardescripcion = "Escoge una longitud correcta";
        entrada = false;
        cajasoli.classList.add("incorrect");
        soli.classList.add("incorrect");
        cwsoli.classList.add("active");
    }
    else{
        if(regexdesc.test(soli.value)){
            warsoli = "No insertes etiquetas HTML";
            entrada = false;
            cajasoli.classList.add("incorrect");
            soli.classList.add("incorrect");
            cwsoli.classList.add("active");
        }
    }

    if (!entrada) {
        wapellido.innerHTML = warapellido;
        wnombre.innerHTML = warnombre;
        wfecha.innerHTML = warfecha;
        wfecha2.innerHTML = warfecha2;      
        wcorreo.innerHTML = warcorreo;
        wcorreo2.innerHTML = warcorreo2;
        wdescription.innerHTML = wardescripcion;
        wsoli.innerHTML = warsoli;

    }
    return entrada;
}