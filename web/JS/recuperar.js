const btnRecuperar = document.querySelector(".form-button");
const form = document.querySelector(".content form");

form.onsubmit = (e)=>{
    e.preventDefault();
};

btnRecuperar.onclick = ()=>{
    let entrada = validarEmail();
    if(entrada){
        limpiar();
        btnRecuperar.disabled = true;
        let xhr = new XMLHttpRequest();
            xhr.open("POST", "../../enviarEmail", true);
            xhr.onload = ()=>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status === 200){
                        let data = xhr.response;
                        if(data === "Listo"){
                            location.href = "codigo.jsp";
                        }
                        else{
                            if(data === "Errores"){
                                errorCorreo();
                                btnRecuperar.disabled = false;
                            }
                            else{
                                correoNoExiste();
                                btnRecuperar.disabled = false;
                            }
                        }
                    }
                }
            };

        let formData = new FormData(form);
        console.log(formData)
        xhr.send(formData);
    }
};