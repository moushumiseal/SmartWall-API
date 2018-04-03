package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DictionaryController class is the usecase controller class for displaying the
 * meaning of a given word.
 *
 * @author Moushumi Seal
 *
 */
@Stateless
public class DictionaryController {

    private String word;

    /**
     * word
     * @return String
     */
    public String getWord() {
        return word;
    }

    /**
     * word
     * @param word 
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * process
     * @return ApiResponse
     */
    public ApiResponse process() {

        URL url;
        String speech, displayText;
        displayText = "";

        try {
            url = new URL(Constants.DICTIONARY_URL + "/" + Constants.LANGUAGE + "/" + word);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", Constants.DICTIONARY_APP_ID);
            urlConnection.setRequestProperty("app_key", Constants.DICTIONARY_API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String line;
            String output;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject result = new JSONObject(response.toString());

            output = result.getJSONArray(Constants.RESULTS).getJSONObject(0)
                    .getJSONArray(Constants.LEXICAL_ENTRIES).getJSONObject(0)
                    .getJSONArray(Constants.ENTRIES).getJSONObject(0)
                    .getJSONArray(Constants.SENSES).getJSONObject(0)
                    .getJSONArray(Constants.DEFINITIONS).toString();

            speech = "speech: According to the Oxford Dictionary, the meaning of "
                    + word
                    + " is "
                    + output.substring(2, output.length() - 2);

            displayText = speech;

        } catch (IOException | JSONException ex) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, ex);
            speech = "I didn't get that. Can you say it again?";
        }

        return new ApiResponse(speech, displayText, Constants.ACTION_DICTIONARY);
    }

}
