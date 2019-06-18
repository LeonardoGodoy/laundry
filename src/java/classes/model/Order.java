package classes.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private String externalReference;
   
    private User user;
    private Address address;
    private List<Vesture> vestures;
    
    public Order() {
        this.status = "Aguardando";
        this.createdAt = new Date();
        this.vestures = new ArrayList<>();
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
    
    @Column(name = "external_reference")
    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }
        
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    //@Transient
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToMany(
        targetEntity=Vesture.class,
        cascade={CascadeType.PERSIST, CascadeType.MERGE},
        fetch=FetchType.EAGER
    )
    @JoinTable(
        name="tb_order_item",
        joinColumns={@JoinColumn(name="order_id")},
        inverseJoinColumns={@JoinColumn(name="vesture_id")}
    )
    public List<Vesture> getVestures() {
        return vestures;
    }

    public void setVestures(List<Vesture> vestures) {
        this.vestures = vestures;
    }
    
    @Transient
    public boolean isWaiting() {
        return this.status.equals("Aguardando");
    }
    
    @Transient
    public boolean isShipping() {
        return this.status.equals("Em entrega");
    }
    
    @Transient
    public boolean isPaid() {
        return this.paymentDate != null;
    }
    
    @Transient
    public boolean isCanceled() {
        return this.status.equals("Cancelado");
    }
    
    @Transient
    public boolean isExported() {
        return this.externalReference != null;
    }
    
    @Transient
    public String getPaymentStatus() {
        return isPaid() ? "Pago" : "Pendente";
    }
    
    @Transient
    public String getDescription(){
        Integer size = getVestures().size();
        return size + " peças de roupa.";
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