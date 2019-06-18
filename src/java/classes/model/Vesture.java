package classes.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vesture")
@SequenceGenerator(name = "sequence", sequenceName = "tb_vesture_id_seq")
public class Vesture implements Serializable {
    
    private Integer id;
    private String description;
    private BigDecimal price;
    private Integer days;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    @Override
    public String toString() {
        return "\n" + 
        "id: " + getId() + "\n" + 
        "price: " + getPrice() + "\n" +
        "days: " + getDays()+ "\n" +
        "description: " + getDescription() + "\n";
    }

    public void setPrice(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
