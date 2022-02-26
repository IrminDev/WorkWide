//Variables que guardarán los componentes de HTML
//Barra de búsqueda
const searchBar = document.querySelector("#mysearch"),
//Div donde se insertará la lista de usuarios
usersList = document.querySelector(".card__grid");

//Método que se ejecuta cada que se teclea un caracter
searchBar.onkeyup = ()=>{
    //Tomamos el dato que se tiene de la barra de búsqueda
    let searchKey = searchBar.value;
    //Si no está vacía se le garega la clase active
    if(searchKey !== ""){
        searchBar.classList.add("active");
    }
    //En caso contrario, se le remueve la clase active
    else{
        searchBar.classList.remove("active");
    }
    
    //Creamos un objeto XMLHttpRequest
    let xhr = new XMLHttpRequest();
    //Con método POST abrimos el servlet busqueda (revisar servlet busqueda.java)
    xhr.open("POST", "../../busqueda", true);
    
    //Cuando sea ejecutado el objeto XHR se ejecutará este método
    xhr.onload = ()=>{
        //Comprobamos que está listo
        if(xhr.readyState === XMLHttpRequest.DONE){
            //Comprobamos que la comunicación se llevó exitosamente
            if(xhr.status === 200){
                //Guardamos la respuesta del servlet en la variable data
                let data = xhr.response;
                //Escribimos la información dentro del div UserList
                usersList.innerHTML = data;
            }
        }
    };
    
    //Codificamos la información que será enviada de manera que sea enviada con POST
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //Enviamos la información, en este caso la palabra de búsqueda
    xhr.send("searchKey=" + searchKey);
};

//Método qie se ejecutará cada 1000 milisegundos
setInterval(()=>{
    //Creamos un objeto XMLHttpRequest
    let xhr = new XMLHttpRequest();
    //Con método GET abrimos el servlet busqueda (revisar servlet busqueda.java)
    xhr.open("GET", "../../busqueda", true);
    //Cuando sea ejecutado el objeto XHR se ejecutará este método
    xhr.onload = ()=>{
        //Comprobamos que está listo
        if(xhr.readyState === XMLHttpRequest.DONE){
            //Comprobamos que la comunicación se llevó exitosamente
            if(xhr.status === 200){
                //Guardamos la información respondida del servlet a la variable data
                let data = xhr.response;
                //Obtenemos la info que hay en la barra de búsqueda
                let searchKey = searchBar.value;
                //En caso de que haya información le pondremos la clase active
                if(searchKey !== ""){
                    searchBar.classList.add("active");
                }
                //Caso contrario, se la removeremos si aún la tiene
                else{
                    searchBar.classList.remove("active");
                }
                //En caso de que NO haya una búsqueda activa, imprimimos la información de los usuarios en el div de UserList
                if(!searchBar.classList.contains("active")){
                    usersList.innerHTML = data;
                }
            }
        }
    };
    
    //Enviamos la petición al servlet
    xhr.send();
  }, 1000);