/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.resource.helper.ApiAction;
import edu.sg.nus.iss.smartwall.resource.helper.ApiHelper;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.Stateless;
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
        
    public String getGeocity() {
        
        return geocity;
    }

    public void setGeocity(String geocity) {
        
        this.geocity = geocity;
    }

    public WeatherService() {
      
    }    
    
    public ApiResponse process() {
        
        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + geocity + "\")";
        
        String URL = ApiHelper.getURL(Constants.WEATHER_URL + "?format=json&q=" , query);
        
        JsonObject result = ApiHelper.getHttpResponse(URL);
        
        String output = result.getJsonObject(QUERY)
                              .getJsonObject(RESULTS)
                              .getJsonObject(CHANNEL)
                              .getJsonObject(ITEM)
                              .getJsonObject(CONDITIONS)
                              .getString(TEMP);
        
        String speech = "The temperature at " + geocity + " is " + output + "F";
        
        String displayText = "The temperature at " + geocity + " is " + output + "F";
                
        return new ApiResponse(speech , displayText , Constants.ACTION_WEATHER);
    }
    
}
