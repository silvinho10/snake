var BASE_URL = "https://meylans.emf-informatique.ch/javaApiGateway/Servlet";  


/**
 * Fonction permettant de charger les données d'équipe.
 * @param {type} teamid, id de l'équipe dans laquelle trouver les joueurs
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function connect(email, passwd, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "xml",
        url: BASE_URL,
        data: 'action=signIn&login='+email+'&password=' + passwd,
        success: successCallback,
        error: errorCallback
    });
	
	//console.log("asdfsda "+$email);
	//alert("email " + email + " password " + passwd);
	console.log("login "+email);
	
}

/**
 * Fonction permettant de charger les données d'équipe.
 * @param {type} teamid, id de l'équipe dans laquelle trouver les joueurs
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function score(email, score, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
		xhrFields: { withCredentials: true },
        dataType: "text",
        url: BASE_URL,
        data: 'action=score&login='+email+'&score=' + score,
        success: successCallback,
        error: errorCallback
    });
	
	console.log("score for " + email + " point " + score);
	
}


/**
 * Fonction permettant de charger les données d'équipe.
 * @param {type} teamid, id de l'équipe dans laquelle trouver les joueurs
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function disconnect(successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "xml",
        url: BASE_URL,
        data: 'action=signOut',
        success: successCallback,
        error: errorCallback
    });
	console.log("disconnect ");
	document.getElementById("disconnect-button").style.display = "none";
	document.getElementById("score-button").style.display = "none";
	document.getElementById("open-button").style.display = "block";
	closeForm();
	
}