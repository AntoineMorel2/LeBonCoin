package DAO;

import hibernate.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class UserDAO extends DAO<UserEntity> {

    @Override
    /**crée une entité User en base de données
     * @return boolean
     */
    public boolean create(UserEntity user) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.persist(user);
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to create UserEntity " + user);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    /**Cherche une entité User depuis la base de données
     * @return UserEntity
     */
    public UserEntity fetch(int id) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            return em.find(UserEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Unable to find user with id " + id);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    /**Cherche toutes les entités User depuis la base de données
     * @return List de UserEntity
     */
    public List<UserEntity> fetchAll() {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            final Query query;
            query = em.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
            return (List<UserEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fecth all UserEntity");
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    /**Cherche tous les utilisateurs dont le nom ou le prénom contient la variable name
     * @Return List de UserEntity
     */
    public List<UserEntity> fetchByName(String name) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            final Query query;
            query = em.createQuery("SELECT u FROM UserEntity u WHERE nom like :name OR prenom like :name");
            query.setParameter("name", "%" + name + "%");
            return (List<UserEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch UserEntity with name: " + name);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    /**Modifie l'entité User avec les valeurs de user
     * @return boolean
     */
    public boolean update(UserEntity user) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.merge(user);
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to update UserEntity: " + user);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    /**
     * Supprime l'entité User user
     * @return boolean
     */
    public boolean delete(UserEntity user) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            ;
            em.remove(user);
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to delete UserEnity: " + user);
            return false;
        } finally {
            em.close();
        }
    }

}
