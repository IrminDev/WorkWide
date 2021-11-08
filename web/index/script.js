window.onload = function(){
    const togle = document.querySelector('#nav-toggle');
    const nav = document.querySelector('#nav-menu');
    togle.onclick = (() =>{
        nav.classList.toggle('show-menu');
    });

    const openPop = document.querySelectorAll('.highlight_video-content');
    const closPop = document.querySelector('.highlights_popup-close');
    const highlightPopup = document.getElementById('popup');

    function popUp(){
        highlightPopup.classList.add('show-popup');
    }

    openPop.forEach(b => b.addEventListener('click', popUp));

    closPop.addEventListener('click', ()=>{
        highlightPopup.classList.remove('show-popup');
    });

    let galleryThumbs = new Swiper(".gallery-thumbs", {
        spaceBetween: 0,
        slidesPerView: 0,
    })
    let galleryTop = new Swiper(".gallery-top", {
        effect: 'fade',
        loop: true,
        thumbs: {
          swiper: galleryThumbs,
        },
    })

    const controlImg = document.querySelectorAll('.controls_img');
    function scrollAnimation(){
        gsap.from('.highlight_subtitle', {opacity: 0, duration: .2, delay: .2, y:-20});
        gsap.from('.highlight_title', {opacity: 0, duration: .3, delay: .3, y:-20});
        gsap.from('.highlight_desc', {opacity: 0, duration: .4, delay: .4, y:-20});
        gsap.from('.highlight_button', {opacity: 0, duration: .5, delay: .5, y:-20});
        gsap.from('.highlight_video-content', {opacity: 0, duration: .6, delay: .6, y:-20});

        highlightPopup.classList.remove('show-popup')
    }

    controlImg.forEach(c => c.addEventListener('click', scrollAnimation));
}