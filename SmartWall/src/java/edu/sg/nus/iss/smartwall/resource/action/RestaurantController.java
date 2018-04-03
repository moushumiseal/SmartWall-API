package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 * RestaurantController class is the usecase controller class for displaying the
 * nearby food courts.
 *
 * @author Aakash
 *
 */
@Stateless
public class RestaurantController {    
    
    Map<String, String> restaurants;
    
    public RestaurantController(){
        
    }
    
    /**
     * Initializing the list of food courts and their nearest bus-stops.
     */
    private void init() {
        this.restaurants = new HashMap<>();

        this.restaurants.put("Flavors", "Utown");
        this.restaurants.put("Techno edge", "Computer Center");
        this.restaurants.put("The Terrace", "COM2");
        this.restaurants.put("The Deck", "COM2");
        this.restaurants.put("Fine Food", "Utown");
        this.restaurants.put("Humble Origins", "Ventus");

    }
    
    
    public ApiResponse process() {
        
        init();
             
        StringBuilder sp= new StringBuilder();
        StringBuilder dp= new StringBuilder();
        
        sp.append("speech: Restaurants at NUS are ");
        dp.append(", display: Restaurants at NUS are: \n");
           
        restaurants.keySet().stream().map((name) -> {
            sp.append(name)
                    .append(" ");
            return name;
        }).forEachOrdered((name) -> {
            dp.append(name)
                    .append(" at ")
                    .append(restaurants.get(name))
                    .append("\n");
        });
        return new ApiResponse(sp.toString()+ dp.toString(),dp.toString(),Constants.ACTION_RESTAURANT);
    }
    
}
