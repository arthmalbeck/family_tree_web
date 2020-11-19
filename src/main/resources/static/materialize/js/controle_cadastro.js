


var nomeUserCad = String(document.getElementById("confereUser_cad").value);


if(nomeUserCad === "Visitante"){
	
}else{
	document.getElementById("solicitacoesCad_cad").style.display = 'block';
	document.getElementById("solicitarCad_cad").style.display = 'none';
	document.getElementById("logout_cad").style.display = 'block';
	document.getElementById("login_cad").style.display = 'none';
}