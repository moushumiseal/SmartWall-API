/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource;

import edu.sg.nus.iss.smartwall.business.EventBean;
import edu.sg.nus.iss.smartwall.business.RestaurantBean;
import edu.sg.nus.iss.smartwall.resource.action.BusService;
import edu.sg.nus.iss.smartwall.resource.action.DictionaryService;
import edu.sg.nus.iss.smartwall.resource.action.NewsService;
import edu.sg.nus.iss.smartwall.resource.action.RestaurantService;
import edu.sg.nus.iss.smartwall.resource.action.TemperatureService;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

    public static final String PARAM_RESULT = "result";
    public static final String PARAM_ACTION = "action";
    public static final String PARAM_PARAMETERS = "parameters";

    public static final String PARAM_CITY = "geo-city";
    public static final String PARAM_EVENT_NAME = "event-name";
    public static final String PARAM_WORD = "word";
    public static final String PARAM_BUS_STOP = "busstop-name";

    // Injections
    @EJB private TemperatureService temperatureService;
    @EJB private EventBean eventBean;
    @EJB private NewsService newsService;
    @EJB private DictionaryService dictionaryService;
    @EJB private RestaurantService restaurantService;
    @EJB private BusService busService;
   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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
        switch (apiAction.toLowerCase()) {

            case Constants.ACTION_TEMPERATURE:

                System.out.println(Constants.ACTION_TEMPERATURE);
                temperatureService.setGeocity(result.getJsonObject(PARAM_PARAMETERS).getString(PARAM_CITY).toLowerCase());
                apiResponse = temperatureService.process();
                break;

            case Constants.ACTION_EVENT:

                System.out.println(Constants.ACTION_EVENT);

                apiResponse = eventBean.process(result.getJsonObject(PARAM_PARAMETERS).getString(PARAM_EVENT_NAME));
                break;

            case Constants.ACTION_NEWS:

                System.out.println(Constants.ACTION_NEWS);

                apiResponse = newsService.process();
                break;

            case Constants.ACTION_DICTIONARY:

                System.out.println(Constants.ACTION_DICTIONARY);
                dictionaryService.setWord(result.getJsonObject(PARAM_PARAMETERS).getString(PARAM_WORD));
                apiResponse = dictionaryService.process();
                break;   
            case Constants.ACTION_RESTAURANT:
                
                System.out.println(Constants.ACTION_RESTAURANT);
                
                apiResponse = restaurantService.process();
                break;
                
            case Constants.ACTION_BUSSTOP:

                System.out.println(Constants.ACTION_BUSSTOP);
                busService.setBusstopName(result.getJsonObject(PARAM_PARAMETERS).getString(PARAM_BUS_STOP).toLowerCase());
                apiResponse = busService.process();
                break;  
            default:
                break;
        }

        Response response = null;

        if (null != apiResponse) {

            response = Response.ok(apiResponse.getContent()).build();
        } else {

            response = Response.noContent().build();
        }

        return response;
    }

}
