
package classes.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_city")
@SequenceGenerator(name = "sequence", sequenceName = "tb_city_id_seq")
public class City {
    
    private Integer id;
    private String name;
    private State state;

    public City() {
        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="state_id")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
