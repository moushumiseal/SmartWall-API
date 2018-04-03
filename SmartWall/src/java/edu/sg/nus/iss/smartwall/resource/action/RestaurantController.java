package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author Aakash
 * 
 */
@Stateless
public class RestaurantController {    
    //@EJB private RestaurantBean restaurantBean;
    Map<String, String> restaurants;
    
    public RestaurantController(){
        
    }
    
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
           
        for(String name: restaurants.keySet()){
            sp.append(name)
              .append(" ");
            dp.append(name)
              .append(" at ")
              .append(restaurants.get(name))
              .append("\n");
        }
        return new ApiResponse(sp.toString()+ dp.toString(),dp.toString(),Constants.ACTION_RESTAURANT);
    }
    
}
