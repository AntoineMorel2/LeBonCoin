package DAO;

import hibernate.ImageEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.Collection;
import java.util.List;

/**
 * @author AMorel / VPingrenon
 * Cette DAO est utilisée pour toutes les requêtes en rapport avec les Images
 */
public class ImageDAO extends DAO<ImageEntity> {

    /**
     * crée une entité Image en base de données
     *
     * @return boolean
     */
    @Override
    public boolean create(ImageEntity img) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.getTransaction().begin();
            em.persist(img);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to create ImageEntity " + img);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Cherche une entité Image depuis la base de données
     *
     * @return ImageEntity
     */
    @Override
    public ImageEntity fetch(int id) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            return em.find(ImageEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Unable to find image with id " + id);
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Cherche toutes les entités Image depuis la base de données
     *
     * @return List de ImageEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ImageEntity> fetchAll() {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            final Query query;
            query = em.createQuery("SELECT i FROM ImageEntity i", ImageEntity.class);
            return (List<ImageEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch all ImageEntity");
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * méthode non implémenté pour la classe image
     *
     * @return null
     */
    @Override
    public Collection<ImageEntity> fetchByName(String name) {
        System.out.println("Méthode fetchByName non implémentée sur la classe ImageDAO.");
        return null;
    }

    /**
     * Modifie l'entité Image avec les valeurs de img
     *
     * @return boolean
     */
    @Override
    public boolean update(ImageEntity img) {
        EntityManager em = null;
        try {
            em = DAO.getEntityManager();
            em.getTransaction().begin();
            em.merge(img);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Unable to update ImageEntity: " + img);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Supprime l'entité Image img
     *
     * @return boolean
     */
    @Override
    public boolean delete(ImageEntity img) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            final Query query;
            query = em.createQuery("DELETE FROM ImageEntity WHERE idImage LIKE :idToDelete ");
            query.setParameter("idToDelete", img.getIdImage());
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Could not delete the Image with idImage " + img.getIdImage());
            return false;
        } catch (PersistenceException e) {
            System.out.println("Could not delete the Image with idImage " + img.getIdImage());
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
