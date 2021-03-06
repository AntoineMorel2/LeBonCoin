package DAO;

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

    public abstract Collection<T> fetchByName(String name);

    public abstract boolean update(T obj);

    public abstract boolean delete(T obj);

    private static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * crée une entityManagerFactory appelée sessionFactory
     * permet la liaison à la base de données
     *
     * @throws PersistenceException lance une exception lorsque que la création d'une entityManagerFactory est impossible
     */
    private static void buildSessionFactory() throws PersistenceException {
        sessionFactory = Persistence.createEntityManagerFactory("leBonCoinEM");
    }


    /**
     * crée une EntityManager permettant la manipulation de données en base
     *
     * @return EntityManager
     * @throws PersistenceException lance une exception lorsque que la création d'une entityManager est impossible
     */
    static EntityManager getEntityManager() throws PersistenceException {
        if (getSessionFactory() == null) {
            buildSessionFactory();
        }
        return getSessionFactory().createEntityManager();
    }
}

