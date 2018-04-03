package edu.sg.nus.iss.smartwall.resource.helper;

import edu.sg.nus.iss.smartwall.resource.DialogFlowWebhookResource;
import edu.sg.nus.iss.smartwall.util.Constants;
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
 * @author Ashish
 */
public class Service {
    
    
    public static String getURL(String basepath , String query){
        
        String url = null;
        
        try {
            
            url = basepath + URLEncoder.encode(query,Constants.UTF);
            
        } catch (UnsupportedEncodingException ex) {
            
            Logger.getLogger(DialogFlowWebhookResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return url;
    }
    
    /**
     * This method is used to call the respective API to get the response.
     * 
     * @param url
     * @return JSON Response obtained from the API.
     */
    public static JsonObject getHttpResponse(String url){
        
        Logger.getLogger(DialogFlowWebhookResource.class.getName()).log(Level.INFO, url);
        System.out.println("___***********____"+url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
        return invocation.get(JsonObject.class);
    }
}
