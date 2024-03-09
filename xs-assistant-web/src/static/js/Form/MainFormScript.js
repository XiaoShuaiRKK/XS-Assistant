window.onload = function () {
    titleDisplay();
}

/**
 * 监听事件
 */
function titleDisplay(){
    let isDisplay = false;
    const titleElement = document.querySelector(".main-container-title");
    const ob = new IntersectionObserver((entries)=>{
        if(entries[0].isIntersecting && !isDisplay){
            titleElement.style.animation = "span-rotate 1s linear forwards";
            isDisplay = true;
        }
    },{
        root: null,
        threshold: 0
    });
    ob.observe(titleElement);
}

