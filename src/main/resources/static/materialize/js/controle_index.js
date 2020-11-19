


var nomeUserIndex = String(document.getElementById("confereUser_index").value);


if(nomeUserIndex === "Visitante"){
	
}else{
	document.getElementById("solicitacoesCad_index").style.display = 'block';
	document.getElementById("solicitarCad_index").style.display = 'none';
	document.getElementById("logout_index").style.display = 'block';
	document.getElementById("login_index").style.display = 'none';
}