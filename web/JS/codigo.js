const btnRecuperar = document.querySelector(".form-button");
const form = document.querySelector(".content form");

form.onsubmit = (e)=>{
    e.preventDefault();
};

btnRecuperar.onclick = ()=>{
    let entrada = comprobar();
    if(entrada){
        limpiar();
        btnRecuperar.disabled = true;
        let xhr = new XMLHttpRequest();
            xhr.open("POST", "../../verificarCodigo", true);
            xhr.onload = ()=>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status === 200){
                        let data = xhr.response;
                        if(data === "Coinciden"){
                            location.href = "cambio.jsp";
                        }
                        else{
                            if(data === "Expirado"){
                                fechas();
                                btnRecuperar.disabled = false;
                            }
                            else{
                                if(data === "No coinciden"){
                                    codigo();
                                    btnRecuperar.disabled = false;
                                }
                                else{
                                    error();
                                    btnRecuperar.disabled = false;
                                }
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