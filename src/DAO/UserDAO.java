package DAO;

import com.mysql.cj.Session;
import hibernate.UserEntity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Collection;

public class UserDAO extends DAO<UserEntity> {

    private SessionFactory factory;
    private static Logger logger = LoggerFactory
            .getLogger(UserDAO.class);

    @Override
    public boolean create(UserEntity user) {
        return false;
    }

    @Override
    public UserEntity fetch(int id) {
        return null;
    }

    @Override
    public Collection<UserEntity> fetchAll() {
        return null;
    }

    @Override
    public Collection<UserEntity> fetchByName() {
        return null;
    }

    @Override
    public boolean update(UserEntity user) {
        try{
            factory.getCurrentSession().saveOrUpdate(user);
            return true;
        }catch(Exception e){
            logger.error("Cannot update object " + user + "\nException thrown: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(UserEntity obj) {
        try{
            factory.getCurrentSession().delete(obj);
            return true;
        }catch(Exception e){
            logger.error("Cannot remove object " + obj + "\nException thrown: " + e);
            return false;
        }
    }

    @Override
    public void close() {
        
    }

}
