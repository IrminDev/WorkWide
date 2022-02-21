const formulario = document.querySelector("#solicitudForm"),
btnEnviar = document.querySelector("#enviarSoli");

formulario.onsubmit = (e)=>{
    e.preventDefault();
}

btnEnviar.onclick = ()=>{
    let entrada = comprobarSolicitud();
    if(entrada){
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../../enviarSolicitud", true);
        xhr.onload = ()=>{
            if(xhr.readyState === XMLHttpRequest.DONE){
                if(xhr.status === 200){
                    let data = xhr.response;
                    if(data === "Enviado"){
                        location.href = "../listado/Encuentra.jsp"
                    }
                    else{
                        console.log(data)
                    }
                }
            }
        };
        let formData = new FormData(formulario);
        xhr.send(formData);
    }
}