<?php
session_start();
/**
* LORS DE LA CONNEXION
**/
if ($_SERVER['REQUEST_METHOD'] == 'POST'){

	if($_POST['action'] == "connect")
	{
		if ($_POST['password'] == 'emf')
		{
			// Si le mot de passe est 'emf', on écrit dans une variable de session
			$_SESSION['logged'] = 'emf';
			echo '<result>true</result>';
		}
		else
		{
			// Sinon on efface la session
			session_destroy();
			// ou unset($_SESSION['logged']);
			echo '<result>false</result>';
		}
	}

	/**
	* LORS DE LA DECONNEXION
	**/
	if($_POST['action'] == "disconnect")
	{
		session_destroy();
		// ou session_start(); unset($_SESSION['logged']);
		echo '<result>true</result>';
	}

}

/**
* LORS DE DEMANDES DE DONNEES SENSIBLES
**/

if ($_SERVER['REQUEST_METHOD'] == 'GET'){
	if($_GET['action'] == "getInfos")
	{
		if(isset($_SESSION['logged']) == "emf") {
			// si 'emf' est bien dans la session, on retourne les données sensibles
			echo '<users><user><name>Victor Legros</name><salaire>9876</salaire></user><user><name>Marinette Lachance</name><salaire>7540</salaire></user><user><name>Gustave Latuile</name><salaire>4369</salaire></user><user><name>Basile Ledisciple</name><salaire>2384</salaire></user></users>';
		}
		else{
			echo '<message>DROITS INSUFFISANTS</message>';
		}
	}
}

?>