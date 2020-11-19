


var nomeUserCad = String(document.getElementById("confereUser_edit").value);


if(nomeUserCad === "Visitante"){
	
}else{
	document.getElementById("solicitacoesCad_edit").style.display = 'block';
	document.getElementById("solicitarCad_edit").style.display = 'none';
	document.getElementById("logout_edit").style.display = 'block';
	document.getElementById("login_edit").style.display = 'none';
}