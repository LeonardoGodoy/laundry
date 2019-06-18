package classes.mb;

import classes.facade.AddressFacade;
import classes.model.City;
import classes.model.State;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "addressMB")
@ViewScoped
public class AddressMB implements Serializable {
    
    private List<State> states;
    private List<City> cities;
    private Integer stateId;
    
    @PostConstruct
    public void init() {
        states = AddressFacade.listState();
    }
    
    public void fetchCities() {
        cities = AddressFacade.listCitiesByState(this.stateId);
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
    
}
