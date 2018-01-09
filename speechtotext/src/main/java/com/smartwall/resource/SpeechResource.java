/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartwall.resource;

import com.smartwall.SpeechRecognition;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author SmartWall
 */
@ApplicationScoped
@Path("/speech")
public class SpeechResource {
         
     private final Thread startThread = new Thread(new Runnable() {
                    
                @Override
                public void run(){

                    try {

                        SpeechRecognition speechRecognition = new SpeechRecognition();
                        
                        while(true){
                            
                            speechRecognition.startListningForSmartWall();
                        }

                    } catch (Exception ex) {
                            ex.printStackTrace();
                    }					
                    }
            }
            );
    
    @Path("/start")
    @GET
    public void start(){
        
        System.out.println("ASKASK ********************* Started");
            startThread.start();
    }
    
    @Path("/stop")
    @GET
    public void stop(){
      
         System.out.println("ASKASK ********************* Stoped");
        
        startThread.stop();
    }
       
    
}
