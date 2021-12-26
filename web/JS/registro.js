const form = document.querySelector(".registroForm"),
continueBtn = form.querySelector(".grupo input"),
errorText = form.querySelector(".error-text");

form.onsubmit = (e)=>{
    e.preventDefault();
};

continueBtn.onclick = ()=>{
    let entrada = enviarRegistro();
    if(entrada){
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../registrar", true);
        xhr.onload = ()=>{
            if(xhr.readyState === XMLHttpRequest.DONE){
                if(xhr.status === 200){
                    let data = xhr.response;
                    if(data === "Usuario"){
                        location.href="../usuario/listado/Encuentra.jsp";
                    }
                    else{
                        if(data === "Trabajador"){
                            location.href = "complemento/completa.jsp";
                        }
                        else{
                            errorText.style.display = "block";
                            errorText.textContent = data;
                        }
                    }
                }
            }
        };
        let formData = new FormData(form);
        xhr.send(formData);
    }
};