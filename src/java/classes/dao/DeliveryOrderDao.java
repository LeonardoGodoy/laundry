package classes.dao;

import classes.model.Address;
import classes.model.Order;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class DeliveryOrderDao {
    
    private static final String POST_URL = "http://localhost:8080/DeliverySystem/orders";
        
    public static void sendOrder(Order order) throws JSONException, IOException {        
        JSONObject orderJSON = orderJSON(order);
        
        JSONObject response = sendPOST(orderJSON);
        
        if(response != null) {
            String external_reference = response.getString("id");
            order.setExternalReference(external_reference);
        } else {
            System.err.println("LOG: Response null\n");
        }
    }
    
    public static JSONObject orderJSON(Order order) throws JSONException {
        JSONObject addressJSON = new JSONObject();
        
        Address address = order.getAddress();
        if (address != null) {
            addressJSON.put("number", address.getNumber());
            addressJSON.put("street", address.getStreet());
            addressJSON.put("city", address.getCity().getName());
            addressJSON.put("state", address.getCity().getState().getName());

        }
        JSONObject orderJSON = new JSONObject();
        orderJSON.put("id", order.getId().toString());
        orderJSON.put("recipient", order.getUser().getName());
        orderJSON.put("deadline", order.getDeadline().getTime());
        orderJSON.put("description", order.getDescription());
        orderJSON.put("address", addressJSON);
        
        return orderJSON;
    }
    
    private static JSONObject sendPOST(JSONObject data) throws IOException, JSONException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();

        os.write(data.toString().getBytes());

        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            return new JSONObject(response.toString());
        } else {
            System.err.println("Erro de request...");
            System.err.println("Response Code: " + responseCode);
        }
        
        return null;
    }
}
