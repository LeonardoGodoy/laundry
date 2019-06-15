package classes.facade;

import classes.HibernateUtil;
import classes.model.Address;
import classes.model.City;
import classes.model.State;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddressFacade {

    public static List<State> listState() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query select = session.createQuery("from State");
        List<State> states = select.list();
        
        session.getTransaction().commit();
        session.close();
        
        return states;
    }
    
    public static List<City> listCitiesByState(Integer stateId) { 
    Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query select = session.createQuery("from City c where c.state.id = :id");
        select.setInteger("id", stateId);
        List<City> cities = select.list();
        
        session.getTransaction().commit();
        session.close();
        
        return cities;
    }
    
    public static City findCityById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "from City where id = :id";    
        Query select = session.createQuery(hql);
        select.setParameter("id", id);
        
        City city = (City) select.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return city;
    }
    
    public static Address findAddressById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "from Address where id = :id";    
        Query select = session.createQuery(hql);
        select.setParameter("id", id);
        
        Address address = (Address) select.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return address;
    }
    
}
