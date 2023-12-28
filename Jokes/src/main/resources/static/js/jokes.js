'use strict'
document.addEventListener("DOMContentLoaded", ocultar);

let select= document.getElementById("tp");

let text = document.getElementById("text");
let texto = document.getElementById("text2");

select.addEventListener("change",ocultar);


function ocultar(){
	let tipo = select.options[select.selectedIndex].value;
	if( tipo == 2){
		text.style.display="table-row";
		console.log("hola");
	}else if(tipo==1){
		console.log(tipo);
		texto.value="";
		text.style.display="none";
	}
}
