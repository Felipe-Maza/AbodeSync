const btnAddDeseo = document.querySelectorAll('.btnAddDeseo');
document.addEventListener('DOMContentLoaded',function () {
    for (let i = 0; i < btnAddDeseo.length; i++) {
        btnAddDeseo[i].addEventListener('click',function(){
            let idCondominio = btnAddDeseo[i].getAttribute('prod'); 
            alert(idCondominio);
            //agregarDeseo();
        })
        
    }
})