package DAO;

import hibernate.CategoryEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

/**
 * @author AMorel / VPingrenon
 * Cette DAO est utilisée pour toutes les requêtes en rapport avec les Categories
 */
public class CategoryDAO extends DAO<CategoryEntity> {

    /**
     * Créé un nouvel objet Category dans la base de donnée
     *
     * @param obj l'objet a créer dans la base de donnée
     * @return un booléen si la création a fonctionné
     */
    @Override
    public boolean create(CategoryEntity obj) {
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
    public CategoryEntity fetch(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            return entityManager.find(CategoryEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch the CategoryEntity with the following id " + id);
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
    public List<CategoryEntity> fetchAll() {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM CategoryEntity a", CategoryEntity.class);
            return (List<CategoryEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch all the CategoryEntity");
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Recherche d'une catégorie par nom
     *
     * @param name nom de la catégorie
     * @return une liste de catégorie
     */
    @Override
    public List<CategoryEntity> fetchByName(String name) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM CategoryEntity a WHERE name LIKE :name", CategoryEntity.class);
            query.setParameter("name", "%" + name + "%");
            return (List<CategoryEntity>) query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Unable to fetch the CategoryEntity with the following name " + name);
            return null;
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Met a jour l'objet passé en paramètre dans la base de donnée
     *
     * @param obj l'objet a mettre a jour
     * @return True si la mise à jour s'est bien passée, false sinon
     */
    @Override
    public boolean update(CategoryEntity obj) {
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
    public boolean delete(CategoryEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            final Query query;
            query = entityManager.createQuery("DELETE FROM CategoryEntity WHERE idCategory LIKE :idToDelete ");
            query.setParameter("idToDelete", obj.getIdCategory());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (RollbackException r) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Could not delete the Category with name " + obj.getName());
            return false;
        } catch (PersistenceException e) {
            System.out.println("Could not delete the Category with name " + obj.getName());
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
