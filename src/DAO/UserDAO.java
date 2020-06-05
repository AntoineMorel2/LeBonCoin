package DAO;

import hibernate.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

/**
 * @author AMorel / VPingrenon
 * Cette DAO est utilisée pour toutes les requêtes en rapport avec les Users
 */
public class UserDAO extends DAO<UserEntity> {

    /**crée une entité User en base de données
     * @return boolean
     */
    @Override
    public boolean create(UserEntity user) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to create UserEntity " + user);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**Cherche une entité User depuis la base de données
     * @return UserEntity
     */
    @Override
    public UserEntity fetch(int id) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            return em.find(UserEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Unable to find user with id " + id);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    /**
     * Cherche toutes les entités User depuis la base de données
     *
     * @return List de UserEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> fetchAll() {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            final Query query;
            query = em.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
            return (List<UserEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch all UserEntity");
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    /**
     * Cherche tous les utilisateurs dont le nom ou le prénom contient la variable name
     *
     * @return List de UserEntity
     */
    @Override
    @SuppressWarnings("unchecked")
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
            if (em != null) {
                em.close();
            }
        }
    }


    /**Modifie l'entité User avec les valeurs de user
     * @return boolean
     */
    @Override
    public boolean update(UserEntity user) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to update UserEntity: " + user);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Supprime l'entité User user
     * @return boolean
     */
    @Override
    public boolean delete(UserEntity user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            final Query query;
            query = em.createQuery("DELETE FROM UserEntity WHERE idUser LIKE :idToDelete ");
            query.setParameter("idToDelete", user.getIdUser());
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Could not delete the User with idUser " + user.getIdUser());
            return false;
        } catch (PersistenceException e) {
            System.out.println("Could not delete the User with idUser " + user.getIdUser());
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
