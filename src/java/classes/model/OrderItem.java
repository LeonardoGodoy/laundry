package classes.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    
    private Order order;
    private Vesture vesture;
    
    public OrderItem() {
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
}
