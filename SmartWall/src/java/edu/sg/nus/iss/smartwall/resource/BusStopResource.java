/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.rest;

import edu.sg.nus.iss.smartwall.business.BusStopBean;
import edu.sg.nus.iss.smartwall.model.BusStop;
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
@Path("/busstop")
public class BusStopResource  {
@EJB
    private BusStopBean busStopBean;     
    @GET
    @Path("{name}") // Getting test by Id
    public Response get(@PathParam("name") String name) {

        List<BusStop> busStops = busStopBean.findByName(name);

        JsonArrayBuilder builder = Json.createArrayBuilder();

        busStops.forEach((t) -> {
            //builder.add(t.toJson());
            List<Bus> buses= busStopBean.findBusByName(t.getBus());
            for(Bus b:buses)
                {
                    builder.add(b.toJson());
                }
        });
        return (Response.ok(builder.build()).build());
    }

    @GET // Fetching all tests
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {

        List<BusStop> busStops = busStopBean.getAllBusStops();
        

        JsonArrayBuilder builder = Json.createArrayBuilder();

        busStops.forEach((t) -> {
            builder.add(t.toJson());
            
        });

        return (Response.ok(builder.build()).build());
    }
}
