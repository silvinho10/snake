function connectSuccess(data, text, jqXHR) {
	
	//var obj = JSON.parse(JSON.stringify(data));
	console.log("logged in");
	console.log("return :: "+data.getElementsByTagName("user")[0].childNodes[0].nodeValue);
	document.getElementById("disconnect-button").textContent  = data.getElementsByTagName("user")[0].childNodes[0].nodeValue;
	document.getElementById("disconnect-button").style.display = "block";
	//document.getElementById("score-button").style.display = "block";
	document.getElementById("open-button").style.display = "none";
	closeForm();
}

function scoreSuccess(data, text, jqXHR) {
	//var obj = JSON.parse(JSON.stringify(data));
	console.log("scored");
}

function disconnectSuccess(data, text, jqXHR) {
    alert("Utilisateur déconnecté");
}


/**
 * Méthode appelée en cas d'erreur lors de la lecture du webservice
 * @param {type} data
 * @param {type} text
 * @param {type} jqXHR
 */
function CallbackError(request, status, error) {
    alert("erreur : " + error + ", request: " + request + ", status: " + status);
	//todo change this by a card on the html (a red card to show)
}


function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}
/**
 * Méthode "start" appelée après le chargement complet de la page
 */
$(document).ready(function() {
    var butConnect = $("#connect");
	var butDisconnect = $("#disconnect-button");
	/* var butSaveScore = $("#score-button"); */ 
	var butOpen = $("#open-button");
	
	document.getElementById("disconnect-button").style.display = "none";
	/* document.getElementById("score-button").style.display = "none"; */

    $.getScript("./js/services/servicesHttp.js", function() {
        console.log("servicesHttp.js chargé !");
    });
	
	butOpen.click(function(event) {
		openForm();
    });
	butConnect.click(function(event) {
		var email = document.getElementById("email").value;
		var psw = document.getElementById("psw").value;
        connect(email, psw, connectSuccess, CallbackError);
    });
	butDisconnect.click(function(event) {
        disconnect(disconnectSuccess, CallbackError);
    });
	/* butSaveScore.click(function(event) {
		var email = document.getElementById("email").value;
		score(email, '50', scoreSuccess, CallbackError); //pour tester score
    }); */
});

