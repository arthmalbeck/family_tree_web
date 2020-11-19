

var idPai = String(document.getElementById("conferePai").value);
var idMae = String(document.getElementById("confereMae").value);
var nomeUser = String(document.getElementById("confereUser").value);

if (idPai === "1") {
	document.getElementById("editPai1").style.display = 'none';
	document.getElementById("deletePai1").style.display = 'none';
	document.getElementById("linkComPai1").style.display = 'none';
	document.getElementById("linkSemPai1").style.display = 'block';
}else{
	document.getElementById("cadPai1").style.display = 'none';
}

if (idMae === "1") {
	document.getElementById("editMae1").style.display = 'none';
	document.getElementById("deleteMae1").style.display = 'none';
	document.getElementById("linkComMae1").style.display = 'none';
	document.getElementById("linkSemMae1").style.display = 'block';
}else{
	document.getElementById("cadMae1").style.display = 'none';
}

if(nomeUser === "Visitante"){
	document.getElementById("editPai1").style.display = 'none';
	document.getElementById("deletePai1").style.display = 'none';
	document.getElementById("editMae1").style.display = 'none';
	document.getElementById("deleteMae1").style.display = 'none';
	document.getElementById("cadPai1").style.display = 'none';
	document.getElementById("cadMae1").style.display = 'none';
	document.getElementById("cadFilhos1").style.display = 'none';
	document.getElementById("cadIrmaos1").style.display = 'none';
	document.getElementById("deleteFilhos1").style.display = 'none';
	document.getElementById("editIrmaos1").style.display = 'none';
	document.getElementById("deleteIrmaos1").style.display = 'none';
	document.getElementById("editFilhos1").style.display = 'none';
	
}else{
	document.getElementById("solicitacoesCad").style.display = 'block';
	document.getElementById("solicitarCad").style.display = 'none';
	document.getElementById("logout").style.display = 'block';
	document.getElementById("login").style.display = 'none';
}

