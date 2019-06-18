package classes.mb;

import classes.facade.OrderFacade;
import classes.model.Order;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "orderMB")
@RequestScoped
public class OrderMB implements Serializable {
    
    private List<Order> orders;
    private Order order;

    private Integer userId;
    private String reason;
    
    @PostConstruct
    public void init() {
        order = new Order();
        order.setId(1);
        setOrderList();
    }
    
    public String save() {
        System.out.println("save: " + this.order);
        //OrderFacade.createOrder(this.order);
        return "/orders/show";
    } 
    
    public String show(Integer id) {
        getOrderById(id);
        return "/orders/show";
    }
    
    public String paid(Integer id) {
        getOrderById(id);
        
        OrderFacade.paidOrder(order);
        return "/orders/show";
    }
    
    public String ready(Integer id) {
        getOrderById(id);
        
        OrderFacade.readyOrder(order);
        
        return "/orders/show";
    }
        
    public String cancel(Integer id) {
        getOrderById(id);
        String reason = "Setar motivo...";
        
        OrderFacade.cancelOrder(order, reason);
        return "/orders/index?faces-redirect=true";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    private void setOrderList(){
        this.orders = OrderFacade.listAllOrders();
    }

    public static Map<String, String> getParams() {
        return FacesContext.getCurrentInstance()
                           .getExternalContext()
                           .getRequestParameterMap();
    }

    public void getOrderById(Integer id) {
        this.order = OrderFacade.findOrderById(id);
    }
}
