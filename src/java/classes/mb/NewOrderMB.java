package classes.mb;

import classes.facade.AddressFacade;
import classes.facade.OrderFacade;
import classes.facade.VestureFacade;
import classes.model.Address;
import classes.model.City;
import classes.model.Order;
import classes.model.OrderItem;
import classes.model.User;
import classes.model.Vesture;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "newOrderMB")
@RequestScoped
public class NewOrderMB implements Serializable {
    
    private Order order;
    private Vesture vesture;
    private Address address;
    
    private List<Vesture> vestures;
    private List<OrderItem> items;

    private String ids;
    private Integer cityId;
    
    @PostConstruct
    public void init() {
        this.order = new Order();
        this.vesture = new Vesture();
        this.vestures = VestureFacade.listVesture();
        
        this.address = new Address();
    }
    
    public String save(){        
        if(ids.isEmpty()) {
            return "/orders/new";
        }
        
        String[] list = ids.split(",");
        for (String id : list) {
            System.out.println("\n" + id);
            
            Vesture vesture = VestureFacade.findVestureById(Integer.parseInt(id));
            order.getVestures().add(vesture);
        }
        
        order.setUser(currentUser());
        City city = AddressFacade.findCityById(cityId);
        address.setCity(city);        
        order.setAddress(address);

        OrderFacade.createOrder(order);
        
        return "/orders/my_orders";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Vesture getVesture() {
        return vesture;
    }

    public void setVesture(Vesture vesture) {
        this.vesture = vesture;
    }

    public List<Vesture> getVestures() {
        return vestures;
    }

    public void setVestures(List<Vesture> vestures) {
        this.vestures = vestures;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    } 

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
    public User currentUser(){
      return AuthorizationMB.getAuthInstance().getUser();
    } 

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
   
}
