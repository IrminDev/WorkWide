const searchBar = document.querySelector("#mysearch"),
usersList = document.querySelector(".card__grid");

searchBar.onkeyup = ()=>{
    let searchKey = searchBar.value;
    if(searchKey !== ""){
        searchBar.classList.add("active");
    }
    else{
        searchBar.classList.remove("active");
    }
    
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "../../busqueda", true);
    xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                usersList.innerHTML = data;
            }
        }
    };
    
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("searchKey=" + searchKey);
};

setInterval(()=>{
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "../../busqueda", true);
    xhr.onload = ()=>{
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status === 200){
                let data = xhr.response;
                let searchKey = searchBar.value;
                if(searchKey !== ""){
                    searchBar.classList.add("active");
                }
                else{
                    searchBar.classList.remove("active");
                }
                if(!searchBar.classList.contains("active")){
                    usersList.innerHTML = data;
                }
            }
        }
    };
    
    xhr.send();
  }, 1000);