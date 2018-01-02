/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiHelper;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Moushumi Seal
 * 
 */
@Stateless
public class NewsService {

    public static final String SOURCE = "bbc-news";
    public static final String ARTICLES = "articles";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    
    public NewsService(){
        
    }
    
    public ApiResponse process() {
        
        String URL = Constants.NEWS_URL + "?sources=" + SOURCE + "&apiKey=" + Constants.NEWS_API_KEY;
        String speech, displayText;
        JsonObject result = ApiHelper.getHttpResponse(URL);
        
        JsonArray articles =  result.getJsonArray(ARTICLES);
        
        speech = "Top five headlines from BBC News. ";
        displayText = "Top five headlines from BBC News. '\n'";
        
        for (int i = 0; i < 5; i++) {
            speech += articles.getJsonObject(i).getString(TITLE)
                    + ". "
                    + articles.getJsonObject(i).getString(DESCRIPTION)
                    + ". ";
            displayText += "Headlines " + i + " " + articles.getJsonObject(i).getString(TITLE)
                    + ". "
                    + articles.getJsonObject(i).getString(DESCRIPTION)
                    + ". ";
        }
                
        return new ApiResponse(speech , displayText , Constants.ACTION_NEWS);
    }
    
}
