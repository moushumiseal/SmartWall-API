/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.model.BusStop;
import edu.sg.nus.iss.smartwall.model.Restaurant;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author Moushumi Seal
 */
@Stateless
public class BusService {

    private Map<String, LocalTime> buses;
    private String busstopName;
    private static final long INTERVAL = 15; // Buses arrive at intervals of 15 minutes

    public BusService() {

    }

    public String getBusstopName() {
        return busstopName;
    }

    public void setBusstopName(String busstopName) {
        this.busstopName = busstopName;
    }

    private void init() {
        this.buses = new HashMap<>();

        this.buses.put("A1", LocalTime.parse("07:00:00"));
        this.buses.put("B1", LocalTime.parse("07:05:00"));
        this.buses.put("D1", LocalTime.parse("07:10:00"));
        this.buses.put("A2", LocalTime.parse("07:15:00"));

    }

    public ApiResponse process() {
        init();
        StringBuilder sp = new StringBuilder();
        StringBuilder dp = new StringBuilder();
        long remainder = 0;
        sp.append("speech: To go to ")
                .append(this.busstopName)
                .append(" you can take ");

        dp.append("display: ");

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
                break;
            case "utown":
                sp.append("B1 or D1.");
                dp.append("B1").append(computeArrivingTime(buses.get("B1")));
                dp.append("\nD1 ").append(computeArrivingTime(buses.get("D1")));
                break;
            case "computer centre":
            case "computer center":
                sp.append("A2, B1 or D1.");

                dp.append("A2").append(computeArrivingTime(buses.get("A2")));
                dp.append("\nB1").append(computeArrivingTime(buses.get("B1")));
                dp.append("\nD1 ").append(computeArrivingTime(buses.get("D1")));

                break;
            default:
                sp.setLength(0);
                dp.setLength(0);
                sp.append("speech: Sorry, I don't know which bus to take for ")
                        .append(this.busstopName);

                dp.append("display: ")
                        .append("Sorry, I don't know which bus to take for ")
                        .append(this.busstopName);;
                break;
        }

        return new ApiResponse(sp.toString(), dp.toString(), Constants.ACTION_BUSSTOP);
    }

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
                    .append("minutes")
                    .append("\nSubsequent bus: ")
                    .append(INTERVAL + (INTERVAL - (diff % INTERVAL)))
                    .append(" minutes");
        }

        return sb.toString();
    }

}
