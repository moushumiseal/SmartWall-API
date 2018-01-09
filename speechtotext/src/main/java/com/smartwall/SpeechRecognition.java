package com.smartwall;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.smartwall.speech.microphone.Microphone;
import com.smartwall.speech.recognizer.GSpeechDuplex;
import com.smartwall.speech.recognizer.GSpeechResponseListener;
import com.smartwall.speech.recognizer.GoogleResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class SpeechRecognition implements GSpeechResponseListener {
	
        public Microphone mic;
        public GSpeechDuplex duplex;
        public AIConfiguration configuration;
        public AIDataService dataService;
        
        public StringBuffer responseBuffer = new StringBuffer();
        
        public static final String SPEECH_KEY = "AIzaSyAnFkBRqX4bb6JWZQfRqrApVNrp0NlUd34";
        public static final String DIALOGFLOW_KEY = "b751aacf5b804a77813bea00b8530208";
        
        private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "http://localhost:8079/";
        
        public SpeechRecognition(){

                mic = new Microphone(FLACFileWriter.FLAC);
                
		duplex = new GSpeechDuplex(SPEECH_KEY);
		
                configuration = new AIConfiguration(DIALOGFLOW_KEY);

                dataService = new AIDataService(configuration);
                
		duplex.setLanguage("en");
        }
    
//	public static void main(String[] args) throws IOException {
//            
//            SpeechRecognition speechRecognition = new SpeechRecognition();
//            
//            while(true){
//                
//                speechRecognition.startListningForSmartWall();
//            }
//	}
        
        public boolean startListningForSmartWall(){
        		
		final JButton record = new JButton("Record");
		final JButton stop = new JButton("Stop");
		stop.setEnabled(false);
		
		final Thread listen = new Thread(new Runnable() {
                    
                    @Override
                    public void run(){
                        
                        try {
                                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                                
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }					
			}
                }
                );
                                
                Thread responseHandler = new Thread(new Runnable() {
                    
                    @Override
                    public void run(){
                        
                        try {
                                Thread.sleep(5000);
                                listen.interrupt();
                                System.out.println("***************** Request : "+responseBuffer.toString());
                                if(responseBuffer.toString().length() > 1){

                                    mic.close();
                                    duplex.stopSpeechRecognition();
                                    AIRequest request = new AIRequest(responseBuffer.toString());

                                    AIResponse response = dataService.request(request);

                                    if (response.getStatus().getCode() == 200) {
                                        String text=response.getResult().getFulfillment().getSpeech();
                                      System.out.println("***************** ASKASK : "+text);
                                      sendGET(text);
                                      Thread.sleep(4000);
                                    } else {
                                      System.err.println("***************** ASKASK err : "+response.getStatus().getErrorDetails());
                                    }
                                }else{

                                    System.err.println("***************** ASKASK Empty : ");
                                }
                                
                        } catch (Exception ex) {

                            ex.printStackTrace();
                        }
                }
            }
            );
                
            listen.start();            
            responseHandler.start();
                
            duplex.addResponseListener(new GSpeechResponseListener() {

                String old_text = "";

                public void onResponse(GoogleResponse gr) {

                        String output = "";
                        output = gr.getResponse();

                        if (gr.getResponse() == null) {

                                this.old_text = responseBuffer.toString();

                                if (this.old_text.contains("(")) {
                                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                                }

                                System.out.println("Paragraph Line Added");
                                this.old_text = ( responseBuffer.toString() + "\n" );
                                this.old_text = this.old_text.replace(")", "").replace("( ", "");
                                responseBuffer = new StringBuffer(this.old_text);
                                return;
                        }
                        if (output.contains("(")) {
                                output = output.substring(0, output.indexOf('('));
                        }
                        if (!gr.getOtherPossibleResponses().isEmpty()) {

                                output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                        }
                        System.out.println(output);
                        responseBuffer = new StringBuffer(this.old_text);                                
                        responseBuffer.append(output);                                
                }
            });
            
            while(responseHandler.isAlive()){
            
            }
            
            return true;
        }
        
        public void startListning(){
        
            
		
		JFrame frame = new JFrame("Jarvis Speech API DEMO");
		frame.setDefaultCloseOperation(3);
		final JTextArea response = new JTextArea();
		response.setEditable(false);
		response.setWrapStyleWord(true);
		response.setLineWrap(true);
		
		final JButton record = new JButton("Record");
		final JButton stop = new JButton("Stop");
		stop.setEnabled(false);
		
		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Thread(new Runnable() {
                                            @Override
                                            public void run(){
					try {
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}}).start();
                                
				record.setEnabled(false);
				stop.setEnabled(true);
			}
		});
                
                
		stop.addActionListener(new ActionListener() {
                    
			public void actionPerformed(ActionEvent arg0) {
                            
				mic.close();
				duplex.stopSpeechRecognition();
				record.setEnabled(true);
				stop.setEnabled(false);
                                
                                try {
                                        AIRequest request = new AIRequest(response.getText());

                                        AIResponse response = dataService.request(request);

                                        if (response.getStatus().getCode() == 200) {
                                          System.out.println("***************** ASKASK : "+response.getResult().getFulfillment().getSpeech());
                                        } else {
                                          System.err.println("***************** ASKASK err : "+response.getStatus().getErrorDetails());
                                        }
                                } catch (Exception ex) {
                                  ex.printStackTrace();
                                }
			}
		});
		JLabel infoText = new JLabel(
				"<html><div style=\"text-align: center;\">Just hit record and watch your voice be translated into text.\n<br>Only English is supported by this demo, but the full API supports dozens of languages.<center></html>",
				
				0);
		frame.getContentPane().add(infoText);
		infoText.setAlignmentX(0.5F);
		JScrollPane scroll = new JScrollPane(response);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
		frame.getContentPane().add(scroll);
		JPanel recordBar = new JPanel();
		frame.getContentPane().add(recordBar);
		recordBar.setLayout(new BoxLayout(recordBar, 0));
		recordBar.add(record);
		recordBar.add(stop);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		
		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";
			
			public void onResponse(GoogleResponse gr) {
                            
				String output = "";
				output = gr.getResponse();
				if (gr.getResponse() == null) {
					this.old_text = response.getText();
					if (this.old_text.contains("(")) {
						this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
					}
					System.out.println("Paragraph Line Added");
					this.old_text = ( response.getText() + "\n" );
					this.old_text = this.old_text.replace(")", "").replace("( ", "");
					response.setText(this.old_text);
					return;
				}
				if (output.contains("(")) {
					output = output.substring(0, output.indexOf('('));
				}
				if (!gr.getOtherPossibleResponses().isEmpty()) {
                                    
					output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
				}
				System.out.println(output);
				response.setText("");
				response.append(this.old_text);
				response.append(output);                                
			}
		});
        }
	
	@Override
	public void onResponse(GoogleResponse paramGoogleResponse) {
		// TODO Auto-generated method stub
		
	}      
      
        private static void sendGET(String text) throws IOException {
            
           
		URL obj = new URL(GET_URL+text.replace(" ", "%20"));
                System.out.println("********URL=="+obj.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		
                con.connect();
                con.getResponseCode();
                
                 con.disconnect();
//		System.out.println("GET Response Code :: " + responseCode);
//		if (responseCode == HttpURLConnection.HTTP_OK) { // success
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			System.out.println(response.toString());
//		} else {
//			System.out.println("GET request failed");
//		}
	}
}