package classes.util;


import classes.facade.AddressFacade;
import classes.facade.OrderFacade;
import classes.model.Address;
import classes.model.City;
import classes.model.Order;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;


public class UpdateOrderUtil {
    
    private JSONObject params;
    
    public UpdateOrderUtil(JSONObject params) {
        this.params = params;
    }
    
    public Order getOrder() throws JSONException {
        String id = (String) tryToGet(params, "id");
        String status = (String) tryToGet(params, "status");
        String reason = (String) tryToGet(params, "reason");
        
        Order order = OrderFacade.findOrderByExternalReference(id);
        if(order == null) { return null; }
        
        order.setStatus(status);        
        System.out.println(order);
        
        return order;
    }
    
    public static Object tryToGet(JSONObject jsonObj, String key) {
        if (jsonObj.has(key)) {
            return jsonObj.opt(key);
        } else {
            System.out.println("Param " + key + " não informado.");
            return null;
        }   
    }
}
