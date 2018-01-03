/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiHelper;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Moushumi Seal
 */
@Stateless
public class DictionaryService {

    public static final String LANGUAGE = "en";
    public static final String RESULTS = "results";
    public static final String LEXICAL_ENTRIES = "lexicalEntries";
    public static final String SENSES = "senses";
    public static final String ENTRIES = "entries";
    public static final String DEFINITIONS = "definitions";

    private String word;

    public DictionaryService() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ApiResponse process() {
        URL url;
        String speech = "", displayText = "";
        try {
            url = new URL(Constants.DICTIONARY_URL + "/" + LANGUAGE + "/" + word);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", Constants.DICTIONARY_APP_ID);
            urlConnection.setRequestProperty("app_key", Constants.DICTIONARY_API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            JSONObject result = new JSONObject(response.toString());
            
            String output = result.getJSONArray(RESULTS).getJSONObject(0)
                            .getJSONArray(LEXICAL_ENTRIES).getJSONObject(0)
                            .getJSONArray(ENTRIES).getJSONObject(0)
                            .getJSONArray(SENSES).getJSONObject(0)
                            .getJSONArray(DEFINITIONS).toString();

            speech = "According to the Oxford Dictionary, the meaning of "
                    + word
                    + " is "
                    + output.substring(2, output.length()-2);
            
            displayText = speech;

        } catch (IOException  | JSONException ex) {
            Logger.getLogger(DictionaryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ApiResponse(speech, displayText, Constants.ACTION_DICTIONARY);
    }

}