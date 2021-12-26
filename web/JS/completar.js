const formulario = document.querySelector("#Completar"),
BtnIniciar = document.querySelector(".form-button");

formulario.onsubmit = (e)=>{
    e.preventDefault();
}

BtnIniciar.onclick = ()=>{
    let entrada = enviarInfo();
    if(entrada){
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../../completar", true);
        xhr.onload = ()=>{
            if(xhr.readyState === XMLHttpRequest.DONE){
                if(xhr.status === 200){
                    let data = xhr.response;
                    if(data === "Listo"){
                        location.href = "../../trabajador/index/index.jsp";
                    }
                    else{
                        
                    }
                }
            }
        };

        let formData = new FormData(formulario);
        xhr.send(formData);
    }
};