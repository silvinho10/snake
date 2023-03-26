var BASE_URL = "https://meylans.emf-informatique.ch/javaApiGateway/Servlet";  


function showData(successCallback, errorCallback) {

    $.ajax({
        type: "GET",
        dataType: "json",
        url: BASE_URL,
        data: 'action=classement',
        success: successCallback,
        error: errorCallback
    });
	
	//console.log("asdfsda "+$email);
	//alert("email " + email + " password " + passwd);
	
}
