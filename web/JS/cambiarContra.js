const btnCambiar = document.querySelector(".form-button");
const form = document.querySelector(".content form");

form.onsubmit = (e)=>{
    e.preventDefault();
};

btnCambiar.onclick = ()=>{
    let entrada = cambiarContra();
    if(entrada){
        limpiar();
        let xhr = new XMLHttpRequest();
            xhr.open("POST", "../../cambiarContra", true);
            xhr.onload = ()=>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status === 200){
                        let data = xhr.response;
                        if(data === "Listo"){
                                location.href = "../identificate.jsp";
                        }
                        else{
                            contraIncorrecta();
                        }
                    }
                }
            };

        let formData = new FormData(form);
        xhr.send(formData);
    }
};