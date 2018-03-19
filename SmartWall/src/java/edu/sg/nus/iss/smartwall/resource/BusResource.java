/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.rest;

import edu.sg.nus.iss.smartwall.business.BusBean;
import edu.sg.nus.iss.smartwall.model.Bus;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Dhruv
 */
@RequestScoped
@Path("/bus")
public class BusResource  {
@EJB
    private BusBean busBean;

    @GET
    @Path("{name}") // Getting test by Id
    public Response get(@PathParam("name") String name) {

        List<Bus> buses = busBean.findByName(name);

        JsonArrayBuilder builder = Json.createArrayBuilder();

        buses.forEach((t) -> {
            builder.add(t.toJson());
        });

        return (Response.ok(builder.build()).build());
    }

    @GET // Fetching all tests
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {

        List<Bus> events = busBean.getAllBus();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        events.forEach((t) -> {
            builder.add(t.toJson());
        });

        return (Response.ok(builder.build()).build());
    }
}
