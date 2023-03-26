/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package services;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:DB [db]<br>
 * USAGE:
 * <pre>
 *        DouanierRest client = new DouanierRest();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author MES
 */
public class Score {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://meylans.emf-informatique.ch/javaScore/webresources";

    public Score() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("db");
    }
    public String score(String login, int score) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (login != null) {
            resource = resource.queryParam("login", login);
            resource = resource.queryParam("score", score);
        }

        resource = resource.path("insertScore");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(null, String.class);
       // return "<score>"+score+"</score>";
    }

    public String classement() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("scores");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }


    public void close() {
        client.close();
    }
    
}
