/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.facade;

import classes.HibernateUtil;
import classes.model.Order;
import classes.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserFacade {
    
    public static void createUser(User user) {
        saveUser(user);
    }
        
    public static User findUserById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "from User where id = :id";    
        Query select = session.createQuery(hql);
        select.setParameter("id", id);
        
        User user = (User) select.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return user;
    }
    
    public static List<User> listUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query select = session.createQuery("from User");
        List<User> users = select.list();
        
        session.getTransaction().commit();
        session.close();
        
        return users;
    }
    
    public static void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
       
        session.save(user);
        
        transaction.commit();
        session.close();
    }
    
}
