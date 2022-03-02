const reqList = document.querySelector(".trabajos-container");

setInterval(() => {
   let xhr = new XMLHttpRequest();
   xhr.open("GET", "../../listarSolicitudesUsuario", true);
   xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                reqList.innerHTML = data;
            }
        }
    };
    xhr.send();
}, 2000);