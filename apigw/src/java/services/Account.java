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
 *        DouanierRest1 client = new DouanierRest1();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author MES
 */
public class Account {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://meylans.emf-informatique.ch/javaAccount/webresources";

    public Account() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("db");
    }

    public String signIn(String login, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (login != null) {
            resource = resource.queryParam("login", login);
        }
        if (password != null) {
            resource = resource.queryParam("password", password);
        }
        resource = resource.path("login");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        //return "<user>"+login+"</user>";
    }


    public void close() {
        client.close();
    }

}
