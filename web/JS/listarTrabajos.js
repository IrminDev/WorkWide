const wList = document.querySelector(".trabajos-container"),
countList = document.querySelector(".contador");

setInterval(() => {
   let xhr = new XMLHttpRequest();
   xhr.open("GET", "../../listarTrabajos", true);
   xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                wList.innerHTML = data;
                console.log(data);
            }
        }
    };
    xhr.send();
}, 1000);

setInterval(() => {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "../../listarContadoresTrabajos", true);
    xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                countList.innerHTML = data;
            }
        }
    };
    xhr.send();
}, 1000)