/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.facade;

import classes.HibernateUtil;
import classes.model.Vesture;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VestureFacade {
    
    public static void createVesture(Vesture vesture) {
        saveVesture(vesture);
    }
        
    public static Vesture findVestureById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "from Vesture where id = :id";    
        Query select = session.createQuery(hql);
        select.setParameter("id", id);
        
        Vesture vesture = (Vesture) select.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return vesture;
    }
    
    public static List<Vesture> listVesture() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query select = session.createQuery("from Vesture");
        List<Vesture> vestures = select.list();
        
        session.getTransaction().commit();
        session.close();
        
        return vestures;
    }
    
    public static void saveVesture(Vesture vesture) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
       
        session.save(vesture);
        
        transaction.commit();
        session.close();
    }
    
}
