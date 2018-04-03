package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.Service;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * NewsController class is the usecase controller class for displaying the
 * top-5 news headlines.
 *
 * @author Moushumi Seal
 *
 */
@Stateless
public class NewsController {

    public NewsController() {

    }

    public ApiResponse process() {

        String URL = Constants.NEWS_URL + "?sources=" + Constants.SOURCE + "&apiKey=" + Constants.NEWS_API_KEY;
        String speech, displayText;
        JsonObject result = Service.getHttpResponse(URL);

        JsonArray articles = result.getJsonArray(Constants.ARTICLES);

        displayText = "Top five headlines from BBC News. ";

        for (int i = 0; i < 5; i++) {
            displayText += Constants.ORDINAL[i] + " Headlines " + "- " + articles.getJsonObject(i).getString(Constants.TITLE)
                    + ". "
                    + articles.getJsonObject(i).getString(Constants.DESCRIPTION) + "\n\n";
        }

        speech = Constants.ORDINAL[0] + " Headlines " + "- " + articles.getJsonObject(0).getString(Constants.TITLE)
                + ". "
                + articles.getJsonObject(0).getString(Constants.DESCRIPTION);

        speech = "speech:" + speech + ", display:" + displayText;

        return new ApiResponse(speech, displayText, Constants.ACTION_NEWS);
    }

}
