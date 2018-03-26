package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.business.RestaurantBean;
import edu.sg.nus.iss.smartwall.model.Restaurant;
import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import edu.sg.nus.iss.smartwall.util.Constants;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Aakash
 * 
 */
@Stateless
public class RestaurantService {    
    @EJB private RestaurantBean restaurantBean;
    
    public RestaurantService(){
        
    }
    
    public ApiResponse process() {
        
        List<Restaurant> restaurants = restaurantBean.getAllRestaurants();
        
        StringBuilder sp= new StringBuilder();
        StringBuilder dp= new StringBuilder();
        
        sp.append("speech: Restaurants at NUS are ");
        dp.append(", display: Restaurants at NUS are: \n");
        
        for(Restaurant r : restaurants){
        
            sp.append(r.getName());
            dp.append(r.getName()+" at "+r.getStall()+"\n");
        }
           
        return new ApiResponse(sp.toString()+ dp.toString(),dp.toString(),Constants.ACTION_RESTAURANT);
    }
    
}
