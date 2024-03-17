window.onload = function () {
    titleDisplay();
    dotAnimation();
}

/**
 * 监听事件
 */
function titleDisplay(){
    const titleElement = document.querySelector(".main-container-title");
    const ob = new IntersectionObserver((entries)=>{
        if(entries[0].isIntersecting){
            titleElement.style.animation = "span-rotate 1s linear forwards";
        }
    },{
        root: null,
        threshold: 0
    });
    ob.observe(titleElement);
}
function dotAnimation(){
    const circle = document.querySelector(".circle-box");
    window.onmousemove = function(ev) {
        let ofLeft = document.documentElement.offsetLeft || document.body.offsetLeft;
        let ofTop = document.documentElement.offsetTop || document.body.offsetTop;
        let oLeft = ev.clientX + ofLeft;
        let oTop = ev.clientY + ofTop + window.pageYOffset;
        circle.style.display = "block";
        circle.style.left = oLeft + "px";
        circle.style.top = oTop + "px";
    }
}




