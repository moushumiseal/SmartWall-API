package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.resource.helper.Service;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * WeatherController class is the usecase controller class for displaying the
 * weather forecast of a given city .
 *
 * @author Aakash
 *
 */
@Stateless
public class WeatherController {

    private String geocity;

    /**
     * getter
     * @return String
     */
    public String getGeocity() {

        return geocity;
    }

    /**
     * setter
     * @param geocity 
     */
    public void setGeocity(String geocity) {

        this.geocity = geocity;
    }

    /**
     * process request
     * @return ApiResponse
     */
    public ApiResponse process() {

        StringBuffer speech = new StringBuffer();
        StringBuffer displayText = new StringBuffer();

        if (geocity.equalsIgnoreCase("singapore") || geocity == null || geocity.isEmpty()) {
            geocity = "Clementi";
        }

        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + geocity + "\")";

        String URL = Service.getURL(Constants.WEATHER_URL + "?format=json&q=", query);

        JsonObject result = Service.getHttpResponse(URL);
        speech.append("speech:");
        displayText.append(", display:");
        System.out.println(result);
        if (result.getJsonObject(Constants.QUERY).isNull(Constants.RESULTS)) {
            speech.append("I didn't get that. Can you say it again?");

            displayText.append("Sorry, something went wrong while fetching the weather details for ")
                    .append(geocity);
        } else {

            if (geocity.equalsIgnoreCase("Clementi")) {
                geocity = "Singapore";
            }
            String temperature = result.getJsonObject(Constants.QUERY)
                    .getJsonObject(Constants.RESULTS)
                    .getJsonObject(Constants.CHANNEL)
                    .getJsonObject(Constants.ITEM)
                    .getJsonObject(Constants.CONDITIONS)
                    .getString(Constants.TEMP);

            String weather = result.getJsonObject(Constants.QUERY)
                    .getJsonObject(Constants.RESULTS)
                    .getJsonObject(Constants.CHANNEL)
                    .getJsonObject(Constants.ITEM)
                    .getJsonObject(Constants.CONDITIONS)
                    .getString(Constants.TEXT);

            String code = result.getJsonObject(Constants.QUERY)
                    .getJsonObject(Constants.RESULTS)
                    .getJsonObject(Constants.CHANNEL)
                    .getJsonObject(Constants.ITEM)
                    .getJsonObject(Constants.CONDITIONS)
                    .getString(Constants.CODE);

            JsonArray sevenDaysForcast = result.getJsonObject(Constants.QUERY)
                    .getJsonObject(Constants.RESULTS)
                    .getJsonObject(Constants.CHANNEL)
                    .getJsonObject(Constants.ITEM)
                    .getJsonArray(Constants.FORECAST);
            double tempInCelsius = fahrenheitToCelsius(Double.parseDouble(temperature));
            speech.append("The temperature at ")
                    .append(geocity)
                    .append(" is ")
                    .append(tempInCelsius)
                    .append(" Celsius")
                    .append(" and the weather is ")
                    .append(weather);
            /*speech.append(", code: ")
                    .append(code);*/

            displayText.append("The weather forecast for next seven days -");

            for (int i = 1; i <= 7; i++) {
                double highTempInCelsius = fahrenheitToCelsius(Double.parseDouble(sevenDaysForcast.getJsonObject(i).getString(Constants.HIGH)));
                double lowTempInCelsius = fahrenheitToCelsius(Double.parseDouble(sevenDaysForcast.getJsonObject(i).getString(Constants.LOW)));
                displayText.append("\nDate : ")
                        .append(sevenDaysForcast.getJsonObject(i).getString(Constants.DATE))
                        .append(", High : ")
                        .append(highTempInCelsius)
                        .append(" Celsius")
                        .append(", Low : ")
                        .append(lowTempInCelsius)
                        .append(" Celsius")
                        .append(", Weather : ")
                        .append(sevenDaysForcast.getJsonObject(i).getString(Constants.TEXT));
            }

            speech.append(displayText.toString());
        }

        return new ApiResponse(speech.toString(), displayText.toString(), Constants.ACTION_WEATHER);
    }

    /**
     * This method is used to convert the temperature from Fahrenheit to
     * Celsius.
     *
     * @param f Temperature in Fahrenheit
     * @return Temperature in Celsius
     */
    private double fahrenheitToCelsius(double f) {
        double celsius = 5 * (f - 32) / 9;
        return Math.round(celsius * 100.0) / 100.0;
    }
}
