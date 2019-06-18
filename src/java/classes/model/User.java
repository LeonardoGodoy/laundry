package classes.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_user")
@SequenceGenerator(name = "sequence", sequenceName = "tb_user_id_seq")
public class User implements Serializable {
    
    private Integer id;
    private String role;
    private String name;
    private String email;
    private String password;
    private String sex;
    private String document;
    private String phone;
    private String registration; 
    
    private Address address;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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
    
    
    
    @Transient
    public boolean isAdmin() {
        return this.role.equals("admin");
    }  
    
    @Transient
    public String getReadableRole(){
        if(isAdmin()){
            return "Funcionário";
        }
        return "Cliente";
    }
    
    @Override
    public String toString() {
        return "\n" + 
        "id: " + getId() + "\n" + 
        "role: " + getRole() + "\n" +
        "name: " + getName() + "\n" +
        "email: " + getEmail() + "\n" +
        "password: " + getPassword() + "\n" +
        "sex: " + getSex() + "\n" +
        "document: " + getDocument() + "\n" +
        "phone: " + getPhone() + "\n" +
        "registration: " + getRegistration() + "\n";
    }
  
}
