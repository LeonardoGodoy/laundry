package classes.mb;

import classes.facade.AddressFacade;
import classes.facade.UserFacade;
import classes.model.Address;
import classes.model.City;
import classes.model.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "userMB")
@RequestScoped
public class UserMB implements Serializable {
    
    private List<User> users;
    private User user;
    private Address address;
    
    private Integer cityId;
    
    @PostConstruct
     public void init(){
        this.users = UserFacade.listUser();
        this.user = new User();
        this.address = new Address();
     }   
    
    public String save(){
        City city = AddressFacade.findCityById(cityId);
        this.address.setCity(city);
        this.user.setAddress(this.address);
        UserFacade.createUser(this.user);
        
        return "/users/index?faces-redirect=true";
    }
    
    public String saveCustomer(){
        user.setRole("customer");        
        
        City city = AddressFacade.findCityById(cityId);
        this.address.setCity(city);
        this.user.setAddress(this.address);
        
        UserFacade.createUser(this.user);
        AuthorizationMB.getAuthInstance().setUser(this.user);
        
        return "/index?faces-redirect=true";
    }
    
    public String show(User user) {
        this.user = user;
        return "/users/show";
    }
   
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
