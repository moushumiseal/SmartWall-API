/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource;

import edu.sg.nus.iss.smartwall.business.TestBean;
import edu.sg.nus.iss.smartwall.model.Test;
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
 * @author Moushumi Seal
 */
@RequestScoped
@Path("/test")
public class TestResource {

    @EJB
    private TestBean testBean;

    @GET
    @Path("{id}") // Getting test by Id
    public Response get(@PathParam("id") Integer testId) {

        Test test = testBean.findById(testId);

        if (null == test) {
            return (Response.status(Response.Status.NOT_FOUND).build());
        }

        return (Response.ok(test.toJson()).build());
    }

    @GET // Fetching all tests
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {

        List<Test> tests = testBean.getAllTests();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        tests.forEach((t) -> {
            builder.add(t.toJson());
        });

        return (Response.ok(builder.build()).build());
    }

}
