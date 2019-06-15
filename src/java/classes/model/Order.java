package classes.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_order")
@SequenceGenerator(name = "sequence", sequenceName = "tb_order_id_seq")
public class Order implements Serializable {  
    private Integer id;
    private String status;
    private BigDecimal price;
    private Date deadline;
    private Date createdAt;
    private Date paymentDate;
   
    private User user;
    private Address address;
    
    public Order() {
        this.status = "Aguardando";
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "dead_line")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    
    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    // mapear
    @Transient
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
    @Transient
    public boolean isWaiting() {
        return this.status.equals("Aguardando");
    }
    
    @Transient
    public boolean isShipping() {
        return this.status.equals("Em entrega");
    }
    
    @Override
    public String toString() {
        String name = "None";
        if(getUser() != null) {
            name = getUser().getName();
        }
      
        return "\n" + 
            "id: " + getId() + "\n" +
            "status: " + getStatus() + "\n" +
            "deadline: " + getDeadline() + "\n" +
            "createdAt: " + getCreatedAt() + "\n";
    }
}