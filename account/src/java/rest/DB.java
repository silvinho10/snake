/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import wrk.Wrk;

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
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@QueryParam("login") String login, @QueryParam("password") String password) {
        String result = "KO";
        String log = wrk.login(login, password);
        if (!log.equals("")) {
            result = log;
        }
        return result;
    }

}
