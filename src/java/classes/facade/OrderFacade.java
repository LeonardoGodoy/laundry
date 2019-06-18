/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.facade;

import classes.HibernateUtil;
import classes.dao.DeliveryOrderDao;
import classes.model.Order;
import classes.model.User;
import classes.model.Vesture;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderFacade {
    
     
    public static void createOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
       
        List<Integer> durations = new ArrayList<>();
        BigDecimal value = new BigDecimal(0);
        for (Vesture vesture : order.getVestures()) {
            value = value.add(vesture.getPrice());
            durations.add(vesture.getDays());
        }
        Integer max = Collections.max(durations);
        
        order.setPrice(value);
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.add(Calendar.DATE, max);
        date = (Date) calendar.getTime();
        
        order.setDeadline(date);
        session.save(order);
        
        transaction.commit();
        session.close();
    }
    
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
    
    public static void readyOrder(Order order) {
        try {
            DeliveryOrderDao.sendOrder(order);
            // order.setStatus("Pronto"); // Aguardando entrega...
            // saveOrder(order);
            
        } catch (Exception exception) {
            
            Logger.getLogger(OrderFacade.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    public static void paidOrder(Order order) {
        order.setPaymentDate(new Date());
        saveOrder(order);
    }
    
    
    
    public static void saveOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
       
        session.update(order);
        
        transaction.commit();
        session.close();
    }

    public static void cancelOrder(Order order, String reason) {
        order.setStatus("Cancelado");
        saveOrder(order);
    }
    
}
