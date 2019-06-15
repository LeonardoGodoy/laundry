package classes.mb;

import classes.facade.AddressFacade;
import classes.facade.OrderFacade;
import classes.facade.UserFacade;
import classes.model.Address;
import classes.model.Order;
import classes.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "authorizationMB")
@SessionScoped
public class AuthorizationMB implements Serializable {
    
    private User user;
    
    public AuthorizationMB() {
    
    }
    
    @PostConstruct 
    public void init() {
        user = new User();
        System.out.println("classes.mb.AuthorizationMB.init()");
        this.user = UserFacade.findUserById(1);
    }   
    
    public String login() {
        Integer id = Integer.parseInt(user.getEmail());
        
        User user = UserFacade.findUserById(id);
        System.out.println(user);
        
        if (true) {
            this.user = user;
            return "/index?faces-redirect=true";
        } else {
            return "/users/login?faces-redirect=true";
        }

    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/users/login?faces-redirect=true";
    }
    
    public boolean isLogged() {
        return this.user.getId() != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public static AuthorizationMB getAuthInstance(){
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        return application.evaluateExpressionGet(context, "#{authorizationMB}", AuthorizationMB.class);
    }
    
}
