//Declaramos las variables que guardarán los componentes de HTML
//Botón que enviará el formulario
const btnCambiar = document.querySelector(".form-button");
//Formulario que contiene toda la información
const form = document.querySelector(".content form");

//Cuando se suba el formulario, se evitará que lo haga ya que no lo enviaremos mediante HTML, sino mediante JS
form.onsubmit = (e)=>{
    e.preventDefault();
};

//Cuando le dé click al botón de enviar,se ejecutará este método
btnCambiar.onclick = ()=>{
    //Creamos una variable que nos dirá si el formulario tiene los datos con la estructura correcta
    let entrada = enviarCambios();
    //Si la entrada fue verdadera (ya que la validación nos regresa un boolean)
    if(entrada){
        //Limpiamos todas las adevrtencias de errores si es que había antes
        limpiar();
        //Creamos un objeto XMLHttpRequest
        let xhr = new XMLHttpRequest();
        //Con método POST abrimos el servlet cambiarTrab (revisar servlet cambiarTrab.java)
        xhr.open("POST", "../../../cambiarTrab", true);
        //Cuando sea ejecutado el objeto XHR se ejecutará este método
        xhr.onload = ()=>{
            //Comprobamos que está listo
            if(xhr.readyState === XMLHttpRequest.DONE){
                //Comprobamos que la comunicación se llevó exitosamente
                if(xhr.status === 200){
                    //Guardamos la respuesta que nos haya dado el servlet
                    let data = xhr.response;
                    //Si la respuesta fue listo, lo regresa a su perfil
                    if(data === "Listo"){
                        location.href = "../MiPerfil.jsp";
                    }
                    //En caso contrario, significa que la contraseña estaba incorrecta y se lo mostramos al usuario
                    else{
                        contraIncorrecta();
                    }
                }
            }
        };

        //Creamos un objeto FormData que guardará los datos dentro del formulario
        let formData = new FormData(form);
        //Mandamos al servlet la solicitud pasando el formulario como parámetro
        xhr.send(formData);
    }
};