package edu.sg.nus.iss.smartwall.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * DialogFlowWebhookResource class is a boundary class that receives service
 * request from the actor and passes it to the ServiceMainController for
 * processing of that request.
 *
 * @author Moushumi Seal
 */
@RequestScoped
@Path("/dialogflowwebhook")
public class DialogFlowWebhookResource {

    /**
     * object
     */
    @Inject
    private ServiceMainController serviceMainController;

    /**
     * post method
     * @param body
     * @return Response
     */ 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(JsonObject body) {

        Response response = serviceMainController.requestService(body);

        return response;
    }

}
