


var nomeUserLogin = String(document.getElementById("confereUser_login").value);


if(nomeUserLogin === "Visitante"){
	
}else{
	document.getElementById("solicitacoesCad_login").style.display = 'block';
	document.getElementById("solicitarCad_login").style.display = 'none';
	document.getElementById("logout_login").style.display = 'block';
	document.getElementById("login_login").style.display = 'none';
}