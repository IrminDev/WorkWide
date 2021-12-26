const btnEliminar = document.querySelector(".form-button");
const form = document.querySelector(".content form");

form.onsubmit = (e)=>{
    e.preventDefault();
};

btnEliminar.onclick = ()=>{
    let entrada = eliminarPerfil();
    if(entrada){
        limpiar();
        let xhr = new XMLHttpRequest();
            xhr.open("POST", "../../../eliminar", true);
            xhr.onload = ()=>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status === 200){
                        let data = xhr.response;
                        if(data === "Listo"){
                            location.href = "../../../index/index.jsp";
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