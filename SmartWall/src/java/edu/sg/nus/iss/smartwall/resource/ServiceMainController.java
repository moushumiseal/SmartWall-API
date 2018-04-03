package edu.sg.nus.iss.smartwall.resource;

import edu.sg.nus.iss.smartwall.business.EventBean;
import edu.sg.nus.iss.smartwall.resource.action.BusController;
import edu.sg.nus.iss.smartwall.resource.action.DictionaryController;
import edu.sg.nus.iss.smartwall.resource.action.NewsController;
import edu.sg.nus.iss.smartwall.resource.action.RestaurantController;
import edu.sg.nus.iss.smartwall.resource.action.WeatherController;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Moushumi Seal
 */
@RequestScoped
public class ServiceMainController implements Serializable {

    // Injections
    @EJB
    private WeatherController temperatureService;
    @EJB
    private EventBean eventBean;
    @EJB
    private NewsController newsService;
    @EJB
    private DictionaryController dictionaryService;
    @EJB
    private RestaurantController restaurantService;
    @EJB
    private BusController busService;

    public ServiceMainController() {

    }

    public Response requestService(JsonObject body) {
        ApiResponse apiResponse = null;
        Response response = null;
        JsonObject result = body.getJsonObject(Constants.PARAM_RESULT);
        String apiAction = result.getString(Constants.PARAM_ACTION);

        switch (apiAction.toLowerCase()) {

            case Constants.ACTION_TEMPERATURE:

                System.out.println(Constants.ACTION_TEMPERATURE);
                temperatureService.setGeocity(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.PARAM_CITY).toLowerCase());
                apiResponse = temperatureService.process();
                break;

            case Constants.ACTION_EVENT:

                System.out.println(Constants.ACTION_EVENT);

                apiResponse = eventBean.process(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.PARAM_EVENT_NAME));
                break;

            case Constants.ACTION_NEWS:

                System.out.println(Constants.ACTION_NEWS);

                apiResponse = newsService.process();
                break;

            case Constants.ACTION_DICTIONARY:

                System.out.println(Constants.ACTION_DICTIONARY);
                dictionaryService.setWord(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.PARAM_WORD));
                apiResponse = dictionaryService.process();
                break;
            case Constants.ACTION_RESTAURANT:

                System.out.println(Constants.ACTION_RESTAURANT);

                apiResponse = restaurantService.process();
                break;

            case Constants.ACTION_BUSSTOP:

                System.out.println(Constants.ACTION_BUSSTOP);
                busService.setBusstopName(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.PARAM_BUS_STOP).toLowerCase());
                apiResponse = busService.process();
                break;
            default:

                break;
        }

        if (null != apiResponse) {
            response = Response.ok(apiResponse.getContent()).build();
        } else {
            response = Response.noContent().build();
        }

        return response;
    }

}
