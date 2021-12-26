const formulario = document.querySelector(".inicioForm"),
BtnIniciar = document.getElementById("BtnIniciar"),
error = formulario.querySelector(".error-text");

formulario.onsubmit = (e)=>{
    e.preventDefault();
}

BtnIniciar.onclick = ()=>{
    let entrada = verificarLogin();
    if(entrada){
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../iniciar", true);
        xhr.onload = ()=>{
            if(xhr.readyState === XMLHttpRequest.DONE){
                if(xhr.status === 200){
                    let data = xhr.response;
                    if(data === "Usuario"){
                        location.href="../usuario/listado/Encuentra.jsp";
                    }
                    else{
                        if(data === "Trabajador"){
                            location.href = "../trabajador/index/index.jsp";
                        }
                        else{
                            if(data === "Incompleto"){
                                 location.href = "complemento/completa.jsp";
                            }
                            else{
                                error.textContent = data;
                                error.style.display = "block";
                            }
                        }
                    }
                }
            }
        };

        let formData = new FormData(formulario);
        xhr.send(formData);
    }
};