function chargementOK(data) {
	
	//var obj = JSON.parse(JSON.stringify(data));
	//console.log("return :: "+data.getElementsByTagName("user")[0].childNodes[0].nodeValue);
	console.log("test");
	console.log(data);
	


	data = JSON.stringify(data);

	  // (B) PARSE THE JSON STRING INTO OBJECT FIRST
	  data = JSON.parse(data);

	  // (C) GENERATE TABLE
	  var table = "<table>";
	  for (let key in data) {
		table += `<tr><td>${key}</td><td>${data[key]}</td></tr>`;
	  }
	  table += "</table>";
	  document.getElementById("tableClassement").innerHTML = table;
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

/**
 * Méthode "start" appelée après le chargement complet de la page
 */
$(document).ready(function() {

   $.getScript("./js/services/servicesHttp.js", function() {
        console.log("servicesHttp.js chargé !");
		showData(chargementOK, CallbackError);
    });
    
  
});

