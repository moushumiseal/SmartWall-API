/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource;

import edu.sg.nus.iss.smartwall.resource.action.Weather;
import edu.sg.nus.iss.smartwall.resource.helper.ApiAction;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.json.JsonObject;
import javax.ws.rs.POST;

/**
 *
 * @author ethi
 */
@RequestScoped
@Path("/dialogflowwebhook")
public class DailogFlowWebhookResource {

    public static final String PARAM_RESULT = "result";
    public static final String PARAM_ACTION = "action";
    public static final String PARAM_PARAMETERS = "parameters";
    
    @POST
    public Response post(JsonObject body) {
        
        ApiResponse apiResponse = null;
        JsonObject result = body.getJsonObject(PARAM_RESULT);
        String apiAction = result.getString(PARAM_ACTION);

        /*try { TODO
            ApiAction actionClass = (ApiAction) Class.forName("edu.sg.nus.iss.smartwall.resource.action." + apiAction)
                    .getConstructor(JsonObject.class)
                    .newInstance(result.getJsonObject("parameters"));

        } catch (Exception ex) {
            Logger.getLogger(DailogFlowWebhookResource.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        switch (apiAction) {

            case Constants.ACTION_WEATHER:
                apiResponse = new Weather(result.getJsonObject(PARAM_PARAMETERS)).process();
                break;
                
            default:
                break;
        }

        Response response = null;
        
        if(null != apiResponse){
        
            response =  Response.ok(apiResponse.getContent()).build();
        }else{
        
            response = Response.noContent().build();
        }
        
        return response;
    }

}
