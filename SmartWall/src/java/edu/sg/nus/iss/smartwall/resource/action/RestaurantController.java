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
    
    public ApiResponse process() {
        
        restaurants = Constants.getRestaurants();
             
        StringBuilder sp= new StringBuilder();
        StringBuilder dp= new StringBuilder();
        
        sp.append("speech: Restaurants at NUS are ");
        dp.append("display: Restaurants at NUS are: \n");
           
        restaurants.keySet().stream().map((name) -> {
            sp.append(name)
                    .append(", ");
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
