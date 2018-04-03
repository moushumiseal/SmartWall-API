package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 * BusController class is the use case controller class for displaying the
 * Shuttle Bus details.
 *
 * @author Moushumi Seal
 *
 */
@Stateless
public class BusController {

    private Map<String, LocalTime> buses;
    private String busstopName;
    private static final long INTERVAL = 15; // Buses arrive at intervals of 15 minutes

    /**
     * return bus stop names
     * @return  String
     */
    public String getBusstopName() {
        return busstopName;
    }

    /**
     * setter
     * @param busstopName 
     */
    public void setBusstopName(String busstopName) {
        this.busstopName = busstopName;
    }

    /**
     * process request
     * @return ApiResponse 
     */
    public ApiResponse process() {
        
        buses = Constants.getbuses();
        
        StringBuilder sp = new StringBuilder();
        StringBuilder dp = new StringBuilder();
     
        sp.append("speech: To go to ")
                .append(this.busstopName)
                .append(" you can take ");

        dp.append(", display: ");

        /*
         * Busstops and Buses:
         * Kent Ridge - A2
         * Utown - B1 and D1
         * Computer Centre - A2, B1 and D1
         * @return response
         */
        switch (this.busstopName) {
            case "kent ridge":
                sp.append("A2.");
                dp.append("A2").append(computeArrivingTime(buses.get("A2")));
                sp.append(dp.toString());

                break;
            case "utown":
                sp.append("B1 or D1.");
                dp.append("B1").append(computeArrivingTime(buses.get("B1")));
                dp.append("\nD1 ").append(computeArrivingTime(buses.get("D1")));
                sp.append(dp.toString());

                break;
            case "computer centre":
            case "computer center":
                sp.append("A2, B1 or D1.");

                dp.append("A2").append(computeArrivingTime(buses.get("A2")));
                dp.append("\nB1").append(computeArrivingTime(buses.get("B1")));
                dp.append("\nD1 ").append(computeArrivingTime(buses.get("D1")));
                sp.append(dp.toString());

                break;
            default:
                sp.setLength(0);
                dp.setLength(0);
                sp.append("I didn't get that. Can you say it again?");

                dp.append("display: ")
                        .append("Sorry, I don't know which bus to take for ")
                        .append(this.busstopName);
                ;
                break;
        }

        return new ApiResponse(sp.toString(), dp.toString(), Constants.ACTION_BUSSTOP);
    }

    /**
     * compute arrival time
     * @param startTime
     * @return  String
     */
    private String computeArrivingTime(LocalTime startTime) {
        LocalTime now = LocalTime.now();
        StringBuilder sb = new StringBuilder();
        long diff = ChronoUnit.MINUTES.between(startTime, now);
        
        if (diff % INTERVAL == 0) {
            
            sb.append("\nNext Bus : Arriving")
                    .append("\nSubsequent bus: ")
                    .append(INTERVAL)
                    .append(" minutes");
        } else {
            
            sb.append("\nNext Bus : ")
                    .append((INTERVAL - (diff % INTERVAL)))
                    .append(" minutes")
                    .append("\nSubsequent bus: ")
                    .append(INTERVAL + (INTERVAL - (diff % INTERVAL)))
                    .append(" minutes");
        }

        return sb.toString();
    }

}
