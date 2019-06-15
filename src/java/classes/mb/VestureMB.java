package classes.mb;

import classes.facade.VestureFacade;
import classes.model.Vesture;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "vestureMB")
@RequestScoped
public class VestureMB implements Serializable {
    
    private List<Vesture> vestures;
    private Vesture vesture;
    
    @PostConstruct
     public void init(){
        this.vestures = VestureFacade.listVesture();
        this.vesture = new Vesture();
    }   
    
    public String save(){
        VestureFacade.createVesture(this.vesture);
        
        return "/vestures/index?faces-redirect=true";
    }
    
    public String show(Vesture vesture) {
        this.vesture = vesture;
        return "/vestures/show";
    }
   
    public List<Vesture> getVestures() {
        return vestures;
    }

    public void setVestures(List<Vesture> vestures) {
        this.vestures = vestures;
    }

    public Vesture getVesture() {
        return vesture;
    }

    public void setVesture(Vesture vesture) {
        this.vesture = vesture;
    }
}
