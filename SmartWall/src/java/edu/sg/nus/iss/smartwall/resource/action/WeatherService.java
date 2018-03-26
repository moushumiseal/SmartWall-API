package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.resource.helper.ApiHelper;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author ethi
 */
@Stateless
public class WeatherService{ 

    private String geocity;

    public static final String CITY = "geo-city";
    public static final String QUERY = "query";
    public static final String RESULTS = "results";
    public static final String CHANNEL = "channel";
    public static final String ITEM = "item";
    public static final String CONDITIONS = "condition";
    public static final String TEMP = "temp";
    public static final String TEXT = "text";
    public static final String CODE = "code";
    public static final String FORECAST = "forecast";
    public static final String DATE = "date";
    public static final String HIGH = "high";
    public static final String LOW = "low";
    
        
    public String getGeocity() {
        
        return geocity;
    }

    public void setGeocity(String geocity) {
        
        this.geocity = geocity;
    }

    public WeatherService() {
      
    }    
    
    public ApiResponse process() {
        
        StringBuffer speech = new StringBuffer();
        StringBuffer displayText = new StringBuffer();
        
        if(geocity.equalsIgnoreCase("singapore") || geocity == null || geocity.isEmpty()){
            geocity = "Clementi";
        }
        
        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + geocity + "\")";
        
        String URL = ApiHelper.getURL(Constants.TEMPERATURE_URL + "?format=json&q=" , query);
        
        JsonObject result = ApiHelper.getHttpResponse(URL);
        speech.append("speech:");
        displayText.append(", display:");
        System.out.println(result);
        if(result.getJsonObject(QUERY).isNull(RESULTS)){
            speech.append("I didn't get that. Can you say it again?");
            
            displayText.append("Sorry, something went wrong while fetching the weather details for ")
                    .append(geocity);
        }else {
            
            if(geocity.equalsIgnoreCase("Clementi")){
                geocity = "Singapore";
            }
            String temperature = result.getJsonObject(QUERY)
                              .getJsonObject(RESULTS)
                              .getJsonObject(CHANNEL)
                              .getJsonObject(ITEM)
                              .getJsonObject(CONDITIONS)
                              .getString(TEMP);
            
            String weather = result.getJsonObject(QUERY)
                                  .getJsonObject(RESULTS)
                                  .getJsonObject(CHANNEL)
                                  .getJsonObject(ITEM)
                                  .getJsonObject(CONDITIONS)
                                  .getString(TEXT);

            String code = result.getJsonObject(QUERY)
                                  .getJsonObject(RESULTS)
                                  .getJsonObject(CHANNEL)
                                  .getJsonObject(ITEM)
                                  .getJsonObject(CONDITIONS)
                                  .getString(CODE);

            JsonArray sevenDaysForcast =  result.getJsonObject(QUERY)
                                        .getJsonObject(RESULTS)
                                        .getJsonObject(CHANNEL)
                                        .getJsonObject(ITEM)
                                        .getJsonArray(FORECAST);
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
            
            for(int i = 1; i <= 7; i++){
                double highTempInCelsius = fahrenheitToCelsius(Double.parseDouble(sevenDaysForcast.getJsonObject(i).getString(HIGH)));
                double lowTempInCelsius = fahrenheitToCelsius(Double.parseDouble(sevenDaysForcast.getJsonObject(i).getString(LOW)));
                displayText.append("\nDate : ")
                           .append(sevenDaysForcast.getJsonObject(i).getString(DATE))
                           .append(", High : ")
                           .append(highTempInCelsius)
                           .append(" Celsius")
                           .append(", Low : ")
                           .append(lowTempInCelsius)
                           .append(" Celsius")
                           .append(", Weather : ")
                           .append(sevenDaysForcast.getJsonObject(i).getString(TEXT));
            }

            speech.append(displayText.toString());
        }

        return new ApiResponse(speech.toString() , displayText.toString() , Constants.ACTION_TEMPERATURE);
    }
    
    private double fahrenheitToCelsius(double f) {
        double celsius = 5 * (f-32)/9;
        return Math.round(celsius * 100.0) / 100.0;
    }
}
