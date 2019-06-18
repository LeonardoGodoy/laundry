package classes.mb;

import classes.facade.OrderFacade;
import classes.model.Order;
import classes.model.User;
import classes.model.Vesture;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "myOrderMB")
@RequestScoped
public class MyOrderMB {
    
    private Order order;
    private List<Order> orders;
    private List<Vesture> vestures;
    private String reason;
            
    @PostConstruct
    public void init() { 
        User user = AuthorizationMB.getAuthInstance().getUser();
        this.orders = OrderFacade.listUserOrders(user);
        this.reason = "Setar motivo...";
        vestures = new ArrayList<Vesture>();
    }
    

    public String show(Integer id) {
        getOrderById(id);
        return "/orders/show";
    }
        
    public String leave(Integer id) {
        getOrderById(id);
        //OrderFacade.leaveOrder(this.order, this.reason);
        return "/orders/my_orders?faces-redirect=true";
    }
    
    public String deliver(Integer id) {
        getOrderById(id);
        //OrderFacade.deliverOrder(this.order);
        return "/orders/my_orders?faces-redirect=true";
    }
    
    public void test(){
        Vesture v = new Vesture();
        v.setDays(3);
        v.setDescription("Teste");
        v.setPrice(new BigDecimal(200.0));
        vestures.add(v);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public void getOrderById(Integer id) {
        this.order = OrderFacade.findOrderById(id);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Vesture> getVestures() {
        return vestures;
    }

    public void setVestures(List<Vesture> vestures) {
        this.vestures = vestures;
    }
}
