package edu.sg.nus.iss.smartwall.resource;

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
 * ServiceMainController class is a controller class that receives a service
 * request and passes it to that usecase controller for processing of that
 * request.
 *
 * @author Moushumi Seal
 */
@RequestScoped
public class ServiceMainController implements Serializable {

    // Injections
    @EJB
    private WeatherController temperatureController;
    @EJB
    private NewsController newsController;
    @EJB
    private DictionaryController dictionaryController;
    @EJB
    private RestaurantController restaurantController;
    @EJB
    private BusController busController;

    public ServiceMainController() {

    }

    public Response requestService(JsonObject body) {
        ApiResponse apiResponse = null;
        Response response = null;
        JsonObject result = body.getJsonObject(Constants.PARAM_RESULT);
        String apiAction = result.getString(Constants.PARAM_ACTION);

        switch (apiAction.toLowerCase()) {

            case Constants.ACTION_WEATHER:

                System.out.println(Constants.ACTION_WEATHER);
                temperatureController.setGeocity(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.CITY).toLowerCase());
                apiResponse = temperatureController.process();
                break;

            case Constants.ACTION_NEWS:

                System.out.println(Constants.ACTION_NEWS);

                apiResponse = newsController.process();
                break;

            case Constants.ACTION_DICTIONARY:

                System.out.println(Constants.ACTION_DICTIONARY);
                dictionaryController.setWord(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.WORD));
                apiResponse = dictionaryController.process();
                break;
            case Constants.ACTION_RESTAURANT:

                System.out.println(Constants.ACTION_RESTAURANT);

                apiResponse = restaurantController.process();
                break;

            case Constants.ACTION_BUSSTOP:

                System.out.println(Constants.ACTION_BUSSTOP);
                busController.setBusstopName(result.getJsonObject(Constants.PARAM_PARAMETERS).getString(Constants.PARAM_BUS_STOP).toLowerCase());
                apiResponse = busController.process();
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
