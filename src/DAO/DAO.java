package DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;

public abstract class DAO<T> {
    public abstract boolean create(T obj);
    public abstract T fetch(int id);
    public abstract Collection<T> fetchAll();
    public abstract Collection<T> fetchByName();
    public abstract boolean update(T obj);
    public abstract boolean delete(T obj);
    public abstract void close(); // Connection closure of the data source

    protected static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    protected static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}

