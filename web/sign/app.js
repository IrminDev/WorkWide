const inicio_btn = document.querySelector("#inicio-btn");
const registro_btn = document.querySelector("#registro-btn");
const registro_link = document.querySelector(".registro-link a");
const inicio_link = document.querySelector(".inicio-link a");

const container = document.querySelector(".container");

registro_btn.onclick = (() =>{
    container.classList.add("registro-mode");
});

inicio_btn.onclick = (() =>{
    container.classList.remove("registro-mode");
});

inicio_link.onclick = (()=>{
    inicio_btn.click();
    return false;
});

registro_link.onclick = (()=>{
    registro_btn.click();
    return false;
});