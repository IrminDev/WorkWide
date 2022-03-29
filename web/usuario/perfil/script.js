$(document).ready(function(){
    $('.nav-profile ul li').click(function(){
        $(this).addClass('active').siblings().removeClass('active');
    });
});

const tabBtn = document.querySelectorAll('.nav ul li');
    const tab = document.querySelectorAll('.tab');

    function tabs(panelIndex){
        tab.forEach(function(node){
            node.style.display = 'none';
        });
        tab[panelIndex].style.display = 'block';
    }
tabs(0);

const formulario = document.querySelector(".profile-info form");

const eliminar = document.querySelector(".profile-del"),
cambiar = document.querySelector(".profile-conf"),
cerrar = document.querySelector(".profile-close"),
chat = document.querySelector(".profile-chat"),
solicitud = document.querySelector(".profile-req");

formulario.onsubmit = (e)=>{
    e.preventDefault();
};

eliminar.onclick = ()=>{
    location.href = "eliminar/EliminarPerfil.jsp";  
};

cambiar.onclick = ()=>{
    location.href = "editar/EditarPerfil.jsp"; 
};

cerrar.onclick = ()=>{
    location.href = "../../logout.jsp";
};

chat.onclick = ()=>{
  location.href = "../chat/users.jsp";  
};

solicitud.onclick = ()=>{
  location.href = "../solicitudes/solicitudes.jsp";
};