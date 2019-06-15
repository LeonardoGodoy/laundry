package classes.mb;

import classes.facade.OrderFacade;
import classes.model.Order;
import classes.model.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "myOrderMB")
@RequestScoped
public class MyOrderMB {
    
    private Order order;
    private List<Order> orders;
    private String reason;
            
    @PostConstruct
    public void init() { 
        User user = AuthorizationMB.getAuthInstance().getUser();
        this.orders = OrderFacade.listUserOrders(user);
        this.reason = "Setar motivo...";
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
}
