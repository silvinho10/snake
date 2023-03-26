/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import wrk.Wrk;
import org.json.simple.JSONValue;

/**
 * REST Web Service
 */
@Path("db")
public class DB {

    private Wrk wrk;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DB
     */
    public DB() {
        this.wrk = new Wrk();
    }

    @GET
    @Path("scores")
    @Produces(MediaType.APPLICATION_JSON)
    public String scores() {
        HashMap<String, String> resultats = new HashMap<>();
        resultats = wrk.getScores();
        String jsonText = JSONValue.toJSONString(resultats);
        return jsonText;
    }
    
    @POST
    @Path("insertScore")
    @Produces(MediaType.TEXT_PLAIN)
    public String insertScores(@QueryParam("login") String login, @QueryParam("score") String score) {
        String resultat = wrk.insertScore(login, Integer.valueOf(score));

        return resultat;
    }

}
