/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource;

import edu.sg.nus.iss.smartwall.business.EventBean;
import edu.sg.nus.iss.smartwall.business.RestaurantBean;
import edu.sg.nus.iss.smartwall.resource.action.BusController;
import edu.sg.nus.iss.smartwall.resource.action.DictionaryController;
import edu.sg.nus.iss.smartwall.resource.action.NewsController;
import edu.sg.nus.iss.smartwall.resource.action.RestaurantController;
import edu.sg.nus.iss.smartwall.resource.action.WeatherController;
import edu.sg.nus.iss.smartwall.resource.helper.Service;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ethi
 */
@RequestScoped
@Path("/dialogflowwebhook")
public class DailogFlowWebhookResource {

    @Inject
    private ServiceMainController serviceMainController;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(JsonObject body) {
       
        Response response = serviceMainController.requestService(body);

        return response;
    }

}
