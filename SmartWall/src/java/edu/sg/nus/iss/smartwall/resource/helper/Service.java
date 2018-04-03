/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.helper;

import edu.sg.nus.iss.smartwall.resource.DailogFlowWebhookResource;
import static edu.sg.nus.iss.smartwall.resource.helper.ApiAction.UTF;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author SmartWall
 */
public class Service {
    
    public static String getURL(String basepath , String query){
        
        String url = null;
        
        try {
            
            url = basepath + URLEncoder.encode(query,UTF);
            
        } catch (UnsupportedEncodingException ex) {
            
            Logger.getLogger(DailogFlowWebhookResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return url;
    }
    
    public static JsonObject getHttpResponse(String url){
        
        Logger.getLogger(DailogFlowWebhookResource.class.getName()).log(Level.INFO, url);
        System.out.println("___***********____"+url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        return invocation.get(JsonObject.class);
    }
}
