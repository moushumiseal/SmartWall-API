package edu.sg.nus.iss.smartwall.util;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 */
public class Constants {
    
    public static final String UTF = "UTF-8";

    // API URLs
    public static final String WEATHER_URL = "https://query.yahooapis.com/v1/public/yql";
    public static final String NEWS_URL = "http://newsapi.org/v2/top-headlines";
    public static final String DICTIONARY_URL = "https://od-api.oxforddictionaries.com:443/api/v1/entries";

    // Service Actions   
    public static final String ACTION_WEATHER = "weather";
    public static final String ACTION_RESTAURANT = "restaurant";
    public static final String ACTION_NEWS = "news";
    public static final String ACTION_DICTIONARY = "meaning";
    public static final String ACTION_BUSSTOP = "busstop";

    //API Keys
    public static final String NEWS_API_KEY = "068abd2372b14b219fa6255efca4994f";
    public static final String DICTIONARY_API_KEY = "03e5acbc1e20bb65a7052f36b85d3a5b";
    public static final String DICTIONARY_APP_ID = "04ad3240";

    // Response parameters
    public static final String PARAM_RESULT = "result";
    public static final String PARAM_ACTION = "action";
    public static final String PARAM_PARAMETERS = "parameters";
    public static final String PARAM_RESOLVED_QUERY = "resolvedQuery";
    public static final String RESULTS = "results";

    //Bus Controller parameters
    public static final String PARAM_BUS_STOP = "busstop-name";

    //Dictionary Controller parameters
    public static final String WORD = "word";
    public static final String LANGUAGE = "en";
    public static final String LEXICAL_ENTRIES = "lexicalEntries";
    public static final String SENSES = "senses";
    public static final String ENTRIES = "entries";
    public static final String DEFINITIONS = "definitions";

    //News Controller parameters
    public static final String SOURCE = "bbc-news";
    public static final String ARTICLES = "articles";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String[] ORDINAL = {"First", "Second", "Third", "Fourth", "Fifth"};

    //Weather Controller parameters
    public static final String CITY = "geo-city";
    public static final String QUERY = "query";
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
    
    public static final Map<String, String> restaurants = new HashMap<>();
    
     public static final Map<String, LocalTime> buses = new HashMap<>();

       
    
    public static Map<String, String> getRestaurants(){
        
        if(restaurants.isEmpty()){
            
            restaurants.put("Flavors", "Utown");
            restaurants.put("Techno edge", "Computer Center");
            restaurants.put("The Terrace", "COM2");
            restaurants.put("The Deck", "COM2");
            restaurants.put("Fine Food", "Utown");
            restaurants.put("Humble Origins", "Ventus");
        }
        
        return restaurants;
    }
    
     public static Map<String, LocalTime> getbuses(){
        
        if(buses.isEmpty()){
            
            buses.put("A1", LocalTime.parse("07:00:00"));
            buses.put("B1", LocalTime.parse("07:05:00"));
            buses.put("D1", LocalTime.parse("07:10:00"));
            buses.put("A2", LocalTime.parse("07:15:00"));
        }
        
        return buses;
    }
}
