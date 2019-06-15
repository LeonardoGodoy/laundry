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

public class OrderFacade {
    
      
    public static Order findOrderById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "from Order where id = :id";    
        Query select = session.createQuery(hql);
        select.setParameter("id", id);
        
        Order order = (Order) select.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return order;
    }
    
    public static List<Order> listAllOrders() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query select = session.createQuery("from Order");
        List<Order> orders = select.list();
        
        session.getTransaction().commit();
        session.close();
        
        return orders;
    }
    
    public static List<Order> listUserOrders(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query query = session.createQuery("from Order order where order.user.id = :id");
        query.setInteger("id", user.getId());
      
        List<Order> orders = query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return orders;
    }
    
}
