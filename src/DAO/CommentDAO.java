package DAO;

import hibernate.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

/**
 * @author AMorel / VPingrenon
 * Cette DAO est utilisée pour toutes les requêtes en rapport avec les Commentaires
 */
public class CommentDAO extends DAO<CommentEntity> {
    /**
     * Créé un nouvel objet Comment dans la base de donnée
     *
     * @param obj l'objet a créer dans la base de donnée
     * @return un booléen si la création a fonctionné
     */
    @Override
    public boolean create(CommentEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } catch (PersistenceException e) {
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Recupère une CategoryEntité liée a l'id fournie
     *
     * @param id l'id dont nous voulons l'CategoryEntité
     * @return Une CategoryEntité ou null.
     */
    @Override
    public CommentEntity fetch(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            return entityManager.find(CommentEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch the CommentEntity with the following id " + id);
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Recupère une Liste de toutes les CategoryEntité dans la base de donnée
     *
     * @return Une liste de CategoryEntité ou null
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CommentEntity> fetchAll() {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM CommentEntity a", CommentEntity.class);
            return (List<CommentEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch all the CommentEntity");
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Ne pas utiliser cette méthode pour les commentaires
     *
     * @param name Ne pas utiliser
     * @return null
     */
    @Override
    public List<CommentEntity> fetchByName(String name) {
        // DO NOT USE
        System.out.println("This method isn't implemented, do not use it");
        return null;
    }

    /**
     * Met a jour l'objet passé en paramètre dans la base de donnée
     *
     * @param obj l'objet a mettre a jour
     * @return True si la mise à jour s'est bien passée, false sinon
     */
    @Override
    public boolean update(CommentEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } catch (PersistenceException e) {
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Supprime l'objet donné en paramètre dans la base de donnée
     *
     * @param obj L'objet a supprimé dans la base de donnée
     * @return True si la suppression a été reussie, false sinon
     */
    @Override
    public boolean delete(CommentEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            final Query query;
            query = entityManager.createQuery("DELETE FROM CategoryEntity WHERE :comment LIKE :idToDelete ", CommentEntity.class);
            query.setParameter("comment", "idcomment");
            query.setParameter("idToDelete", obj.getIdComment());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Could not delete the Comment with id " + obj.getIdComment());
            return false;
        } catch (PersistenceException e) {
            System.out.println("Could not delete the Comment with id " + obj.getIdComment());
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
