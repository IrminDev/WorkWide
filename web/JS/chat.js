//Declaramos las variables que guardarán los componentes de HTML
//Formulario para el envío de mensajes
const form = document.querySelector(".typing-area"),
//ID del usuario al que le enviaremos mensaje
receptor_id = form.querySelector(".incoming_id").value,
//Input donde se obtendrá el mensaje
inputField = form.querySelector(".input-field"),
//Botón de enviar mensjae
sendBtn = form.querySelector("button"),
//Div del chat
chatBox = document.querySelector(".chat-box");

//Cuando se suba el formulario, se evitará que lo haga ya que no lo enviaremos mediante HTML, sino mediante JS
form.onsubmit = (e)=>{
    e.preventDefault();
};

//Por comodidas, ubicaremos automaticamente el cursor al input del mensaje a enviar
inputField.focus();

//Cada que se esciba algo, se ejecutará este método
inputField.onkeyup = ()=>{
    //Si lo que está dentro del input es diferente de vacío, le garegaremos al boton de enviar mensaje la clase active
    if(inputField.value !== ""){
        sendBtn.classList.add("active");
    }else{
        //En caso contrario removeremos la clase active
        sendBtn.classList.remove("active");
    }
};

//Cada que se dé click en enviar, se ejecutará este método
sendBtn.onclick = ()=>{
    //Creamos un objeto XMLHttpRequest
    let xhr = new XMLHttpRequest();
    //Con método POST abrimos el servlet enviarMsg (revisar servlet enviarMsg.java)
    xhr.open("POST", "../../enviarMsg", true);
    //Cuando sea ejecutado el objeto XHR se ejecutará este método
    xhr.onload = ()=>{
        //Comprobamos que está listo
        if(xhr.readyState === XMLHttpRequest.DONE){
            //Comprobamos que la comunicación se llevó exitosamente
            if(xhr.status === 200){
                //Una vez se haya enviado el mensaje, limpiamos el cuadro de texto y hacemos un scroll hasta ese último mensaje
                inputField.value = "";
                scrollToBottom();
            }
        }
    };
    
    //Creamos un objeto FormData que guardará los datos dentro del formulario
    let formData = new FormData(form);
    //Mandamos al servlet la solicitud pasando el formulario como parámetro
    xhr.send(formData);
};

//Si está subiendo para ver mensajes antiguos, se agrega la clase active al div con la caja de chat
chatBox.onmouseenter = ()=>{
    chatBox.classList.add("active");
};

//Si deja de hacer scroll, se elimina la clase active del div del chatbox
chatBox.onmouseleave = ()=>{
    chatBox.classList.remove("active");
};

//Creamos una función que se ejecutará cada 500 milisegundos
setInterval(() =>{
    //Creamos un objeto XMLHttpRequest
    let xhr = new XMLHttpRequest();
    //Con método POST abrimos el servlet obtenerChat (revisar servlet obtenerChat.java)
    xhr.open("POST", "../../obtenerChat", true);
    //Cuando sea ejecutado el objeto XHR se ejecutará este método
    xhr.onload = ()=>{
        //Comprobamos que está listo
        if(xhr.readyState === XMLHttpRequest.DONE){
            //Comprobamos que la comunicación se llevó exitosamente
            if(xhr.status === 200){
                //Guardamos la información que respondió el servlet
                let data = xhr.response;
                //Imprimimos la información dentro del div de chat
                chatBox.innerHTML = data;
                //Si el div de la caja contiene no contiene la clase active, lo scrollea hasta el último mensaje
                if(!chatBox.classList.contains("active")){
                    scrollToBottom();
                }
            }
        }
    };
    
    //Codificamos la información que será enviada de manera que sea enviada con POST
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //Enviamos la información, en este caso el id de a quién le enviaremos mensaje
    xhr.send("receptor="+receptor_id);
}, 500);


//Función que hace un scroll hasta el útlimo mensaje
function scrollToBottom(){
    chatBox.scrollTop = chatBox.scrollHeight;
};
