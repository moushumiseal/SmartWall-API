/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.helper;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Moushumi seal
 */
public class ApiResponse {
    
    public static final String SPEECH = "speech";
    public static final String DISPLAY_TEXT = "displayText";
    public static final String SOURCE = "source";

    private String speech;
    private String displayText;
    private String source;

    /**
     * constructor
     * @param speech
     * @param displayText
     * @param source 
     */
    public ApiResponse(String speech, String displayText, String source) {
        
        this.speech = speech;
        this.displayText = displayText;
        this.source = source;
    }

    /**
     * getter String
     * @return 
     */
    public String getSpeech() {
        return speech;
    }

    /**
    * setter
    * @param speech 
    */
    public void setSpeech(String speech) {
        this.speech = speech;
    }

    /**
     * getter String
     * @return 
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
    * setter
    * @param displayText 
    */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    /**
     * getter String
     * @return 
     */
    public String getSource() {
        return source;
    }

   /**
    * setter
    * @param source 
    */
    public void setSource(String source) {
        this.source = source;
    }
    
    /**
     * getter String
     * @return 
     */
    public JsonObject getContent(){
        JsonObject json = Json.createObjectBuilder()
            .add(SPEECH, speech)
            .add(DISPLAY_TEXT, displayText)
            .add(SOURCE,source)
            .build();
        return json;
    }
}
