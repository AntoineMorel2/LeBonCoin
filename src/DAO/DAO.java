package DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.Collection;

public abstract class DAO<T> {

    private static volatile EntityManagerFactory sessionFactory;

    public abstract boolean create(T obj);
    public abstract T fetch(int id);
    public abstract Collection<T> fetchAll();
    public abstract Collection<T> fetchByName();
    public abstract boolean update(T obj);
    public abstract boolean delete(T obj);
    public abstract void close(); // Connection closure of the data source

    private static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }


    private static void buildSessionFactory() throws PersistenceException{
        sessionFactory = Persistence.createEntityManagerFactory("leBonCoinEM");
    }

    protected static EntityManager getEntityManager() throws PersistenceException{
        if(getSessionFactory() == null){
            buildSessionFactory();
        }
        return getSessionFactory().createEntityManager();
    }
}

