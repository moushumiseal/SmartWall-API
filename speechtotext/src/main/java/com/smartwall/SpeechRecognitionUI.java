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

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class SpeechRecognitionUI implements GSpeechResponseListener {
	
        public Microphone mic;
        public GSpeechDuplex duplex;
        public AIConfiguration configuration;
        public AIDataService dataService;
        
        public StringBuffer responseBuffer = new StringBuffer();
        
        public static final String SPEECH_KEY = "AIzaSyAllczAy42DJeYT9taiFshGwxdmgvSiIR4";
        public static final String DIALOGFLOW_KEY = "b751aacf5b804a77813bea00b8530208";
        
        public SpeechRecognitionUI(){

                mic = new Microphone(FLACFileWriter.FLAC);
                
		duplex = new GSpeechDuplex(SPEECH_KEY);
		
                configuration = new AIConfiguration(DIALOGFLOW_KEY);

                dataService = new AIDataService(configuration);
                
		duplex.setLanguage("en");
        }
    
//	public static void main(String[] args) throws IOException {
//            
//            SpeechRecognition speechRecognition = new SpeechRecognition();
//            speechRecognition.startListning();
//	}
        
         
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
      
}